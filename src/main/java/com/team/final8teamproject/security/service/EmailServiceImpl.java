package com.team.final8teamproject.security.service;

import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final BaseRepository baseRepository;
    private final JavaMailSender emailSender;
    public static String ePw;

    private MimeMessage createMessage(String to)throws CustomException, Exception{
        ePw = createKey();
        System.out.println("전달 받은 이메일 : " + to);
        System.out.println("인증 번호 : "+ePw);
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
        message.setSubject("짐라이크 이메일인증 코드");//제목

        String msgg="";
        msgg += "<div style='background-color:#f7f7f7; font-family:Verdana, sans-serif;'>";
        msgg += "<div style='max-width:600px; margin:0 auto;'>";
        msgg += "<div style='background-color:#fff; padding:40px;'>";
        msgg += "<div style='display:flex; justify-content:center; align-items:center;'>";
        msgg += "<img src='https://cdn-icons-png.flaticon.com/512/69/69840.png' width='60' height='60' style='margin-right:20px;'>";
        msgg += "<h1 style='font-size:28px; color:#333333; margin:0;'>안녕하세요! 짐라이크입니다.</h1>";
        msgg += "</div>";
        msgg += "<hr style='border:none; height:1px; background-color:#005555; margin:20px 0;'>";
        msgg += "<p style='font-size:16px; color:#333333; margin:0; padding:0;'>아래 코드를 복사해 입력란에 작성해주세요.</p>";
        msgg += "<p style='font-size:16px; color:#333333; margin:0; padding:0;'>감사합니다.</p>";
        msgg += "</div>";
        msgg += "<div style='background-color:#005555; color:#fff; padding:40px;'>";
        msgg += "<h2 style='font-size:24px; margin:0;'>" + "인증 코드" + "</h2>";
        msgg += "<div style='background-color:#fff; padding:20px; margin:20px 0; text-align:center;'>";
        msgg += "<h3 style='font-size:28px; color:#333333; margin:0;'>" + ePw + "</h3>";
        msgg += "</div>";
        msgg += "</div>";
        msgg += "</div>";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("spartafinalproject@gmail.com","짐라이크"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    @Override
    public void sendSimpleMessage(String to) throws CustomException, Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try {//예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new CustomException(ExceptionStatus.IS_NOT_CORRECT_FORMAT);
        }
    }
}