package com.team.final8teamproject.user.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.security.redis.RedisUtil;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.dto.*;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.user.repository.RefreshTokenRepository;
import com.team.final8teamproject.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.team.final8teamproject.security.jwt.JwtUtil;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final BaseRepository baseRepository;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    //1. 회원가입
    @Transactional
    public MessageResponseDto signUp(@Valid SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
//        String password2 = passwordEncoder.encode(requestDto.getPassword2());
        String nickName = requestDto.getNickName();
        String email = requestDto.getEmail();
        String phoneNumber = requestDto.getPhoneNumber();
        Long experience = requestDto.getExperience();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }


        UserRoleEnum role = UserRoleEnum.MEMBER;
        User user = User.builder()
                .nickName(nickName).email(email)
                .phoneNumber(phoneNumber).password(password)
                .username(username).role(role).experience(experience)
                .build();
        userRepository.save(user);
        return new MessageResponseDto("회원가입 성공");
    }

    //2. 로그인
    @Transactional
    public LoginResponseDto login(LoginRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        BaseEntity user = baseRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ExceptionStatus.WRONG_USERNAME)
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
        LoginResponseDto loginResponseDto = jwtUtil.createUserToken(user.getUsername(), user.getRole());

//        if(redisUtil.hasKey("RT:" +user.getUsername())){
//            throw new SecurityException("이미 접속중인 사용자 입니다.");
//        }
        redisUtil.setRefreshToken("RT:" + user.getUsername(), loginResponseDto.getRefreshToken(), loginResponseDto.getRefreshTokenExpirationTime());

        return loginResponseDto;
    }


    //3. 로그아웃
    @Transactional
    public String logout(String accessToken, String username) {

        // 레디스에 accessToken 사용못하도록 등록
        Long expiration = jwtUtil.getExpiration(accessToken);
        redisUtil.setBlackList(accessToken, "logout", expiration);

        if (redisUtil.hasKey("RT:" + username)) {
            redisUtil.deleteRefreshToken("RT:" + username);
        } else {
            throw new IllegalArgumentException("이미 로그아웃한 유저입니다.");
        }
        return "로그아웃 완료";
    }

    //4. 유저 아이디 찾기
    @Transactional
    public FindByResponseDto findByUsername(String email){
        BaseEntity user = baseRepository.findByEmail(email).orElseThrow(
                ()->new IllegalArgumentException("이메일을 다시 입력해주시기 바랍니다.")
        );
        if (user.getUsername().isEmpty()){
            throw new NoSuchElementException("소셜 회원 가입자는 찾을수 없습니다.");
        }
        String username = user.getUsername();
        return new FindByResponseDto(username);
    }

    //5. 비밀번호찾기
    @Transactional
    public FindByResponseDto userFindPassword(FindPasswordRequestDto vo){
        BaseEntity user = baseRepository.findByEmail(vo.getEmail()).orElseThrow(
                ()->new IllegalArgumentException("이메일을 다시 입력해주시기 바랍니다.")
        );

        // 가입된 아이디가 없으면
        if(!user.getUsername().equals(vo.getUsername())) {
            throw new IllegalArgumentException("등록되지 않은 사용자입니다.");
        }

        // 가입된 이메일이 아니면
        else if(!vo.getEmail().equals(user.getEmail())) {
            throw new IllegalArgumentException("등록되지 않은 사용자입니다.");

        }else {

            // 임시 비밀번호 생성
            StringBuilder pw = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                pw.append((char) ((Math.random() * 26) + 97));
            }
            user.changePassword(passwordEncoder.encode(pw.toString()));
            // 비밀번호 변경 메일 발송
            sendEmail(vo, pw.toString());

        }
        return new FindByResponseDto("임시 패스워드 발송 성공");
    }

    //5-1.이메일 발송
    public void sendEmail(FindPasswordRequestDto vo, String password){
        // Mail Server 설정
        MimeMessage mail = mailSender.createMimeMessage();
        // 받는 사람 E-Mail 주소
        String userMail = vo.getEmail();
        String htmlStr = "<h2>안녕하세요 '"+ vo.getUsername() +"' 님</h2><br><br>"
                + "<p>비밀번호 찾기를 신청해주셔서 임시 비밀번호를 발급해드렸습니다.</p>"
                + "<p>임시로 발급 드린 비밀번호는 <h2 style='color : blue'>'" + password +"'</h2>이며 로그인 후 마이페이지에서 비밀번호를 변경해주시면 됩니다.</p><br>"
                + "<h3><a href='http://localhost:5500/index.html'>MS :p 홈페이지 접속 ^0^</a></h3><br><br>"
                + "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";

        try {
            mail.setSubject("[MS :p] 임시 비밀번호가 발급되었습니다", "utf-8");
            mail.setText(htmlStr, "utf-8", "html");
            mail.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(userMail));
            mailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getUserNickname(BaseEntity base){

        String username = base.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new CustomException(ExceptionStatus.WRONG_USERNAME));

        return user.getNickName();
    }

}
