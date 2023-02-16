package com.team.final8teamproject;

import com.team.final8teamproject.contact.Comment.entity.ContactComment;
import com.team.final8teamproject.contact.Comment.repository.ContactCommentRepository;
import com.team.final8teamproject.contact.Repository.FaqRepository;
import com.team.final8teamproject.contact.Repository.InquiryRepository;
import com.team.final8teamproject.contact.Repository.NoticeRepository;
import com.team.final8teamproject.contact.entity.Faq;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.entity.Notice;
import com.team.final8teamproject.contact.service.FaqService;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class initData implements ApplicationRunner {
private final PasswordEncoder passwordEncoder;
private final UserRepository userRepository;
private final InquiryRepository inquiryRepository;
private final FaqRepository faqRepository;
private final ContactCommentRepository contactCommentRepository;
private final NoticeRepository noticeRepository;


  @Override
  public void run(ApplicationArguments args) throws Exception {

    User manager = new User( "manager", passwordEncoder.encode("manager1234"), "01011112222","manager@nvaer.com","manager",UserRoleEnum.MANAGER );
    User owner = new User("owner", passwordEncoder.encode("owner1234") ,"01022223333","owner@google.com","owner",UserRoleEnum.OWNER );
    User owner1 = new User("owner1", passwordEncoder.encode("owner11234") ,"01022223333","owner@google.com","owner1",UserRoleEnum.OWNER );
    User member = new User("member", passwordEncoder.encode("member1234"),"01033334444","member@kakao.com","memeber",UserRoleEnum.MEMBER );
    User member1 = new User("member1", passwordEncoder.encode("member11234"),"01033334444","member@kakao.com","memeber1",UserRoleEnum.MEMBER );
    userRepository.save(manager);
    userRepository.save(member);
    userRepository.save(member1);
    userRepository.save(owner);
    userRepository.save(owner1);


    // -------------------고객 센터 관련

    Inquiry ownerInquiry = new Inquiry("owner","사업자 문의","입점가능?",false);
    Inquiry memberInquiry = new Inquiry("member","회원 문의","회원가입?",true); // 문의글 비밀상태
    Inquiry ownerInquiry1 = new Inquiry("owner","아무거나 문의","입점가능?",false);
    Inquiry memberInquiry2 = new Inquiry("member","이것도 문의","회원가입?",true); // 문의글 비밀상태
    inquiryRepository.save(ownerInquiry);
    inquiryRepository.save(memberInquiry);
    inquiryRepository.save(ownerInquiry1);
    inquiryRepository.save(memberInquiry2);

    ContactComment parentComment = new ContactComment("부모댓글",1L,"member",null);
    ContactComment parentComment1 = new ContactComment("부모댓글",1L,"member",null);
    contactCommentRepository.save(parentComment);
    contactCommentRepository.save(parentComment1);
    ContactComment childrenComment = new ContactComment("댓글",1L,"member",parentComment);
    contactCommentRepository.save(childrenComment);
    ContactComment childrenComment1 = new ContactComment("대댓글",1L,"member",childrenComment);
    contactCommentRepository.save(childrenComment1);
    ContactComment childrenComment2 = new ContactComment("댓글",1L,"member",parentComment1);
    contactCommentRepository.save(childrenComment2);

    Notice notice = new Notice(1L,"공지사항","공지사항입니다.");
    Notice notice1 = new Notice(1L," 대체공휴일 휴무 안내","휴무입니다.");
    Notice notice2 = new Notice(1L," 더미1","휴무입니다.");
    Notice notice3 = new Notice(1L," 더미2","휴무입니다.");
    Notice notice4 = new Notice(1L," 더미3","휴무입니다.");
    Notice notice5 = new Notice(1L," 더미4","휴무입니다.");
    Notice notice6 = new Notice(1L,"더미5","공지사항입니다.");
    Notice notice7 = new Notice(1L," 더미6","휴무입니다.");
    Notice notice8 = new Notice(1L,"더미7","휴무입니다.");
    Notice notice9 = new Notice(1L," 더미8","휴무입니다.");
    Notice notice10 = new Notice(1L," 더미9","휴무입니다.");
    Notice notice11= new Notice(1L," 더미10","휴무입니다.");
    Notice notice12= new Notice(1L," 더미11","휴무입니다.");
    Notice notice13 = new Notice(1L," 더미12","휴무입니다.");
    Notice notice14 = new Notice(1L," 더미13","휴무입니다.");
    Notice notice15 = new Notice(1L," 더미14","휴무입니다.");
    Notice notice16= new Notice(1L," 더미15","휴무입니다.");
    Notice notice17= new Notice(1L," 더미16","휴무입니다.");
    noticeRepository.save(notice);
    noticeRepository.save(notice1);
    noticeRepository.save(notice2);
    noticeRepository.save(notice3);
    noticeRepository.save(notice4);
    noticeRepository.save(notice5);
    noticeRepository.save(notice6);
    noticeRepository.save(notice7);
    noticeRepository.save(notice8);
    noticeRepository.save(notice9);
    noticeRepository.save(notice10);
    noticeRepository.save(notice11);
    noticeRepository.save(notice12);
    noticeRepository.save(notice13);
    noticeRepository.save(notice14);
    noticeRepository.save(notice15);
    noticeRepository.save(notice16);
    noticeRepository.save(notice17);


    Faq faq1 = new Faq(1L,"센터 운영 중인데 [ㅇㅇㅇ] 에 등록?입점? 하고 싶습니다.","홈페이지 상단 xx 페이지에서 ~ 진행");
    Faq faq2 = new Faq(1L,"회원 탈퇴는 어떻게 해야 하나요?",
        "1. 홈페이지 xx 메뉴중 '전체' 메뉴를 클릭하시고 '내 정보' 메뉴를 클릭합니다.\n"
        + "2. 하단 좌측에 '탈퇴하기' 버튼을 클릭하고 탈퇴를 진행합니다.\n"
        + "\n"
        + "유의사항\n"
        + "1. 회원 탈퇴 하더라도 모든 정보는 삭제 되지 않습니다. 원할 시 회원 탈퇴 전에 원하는 글에 대해 삭제 하시길 바랍니다. \n"
        + "   단, 게시글 중 댓글이 있는 경우는 삭제 할 수 없습니다. \n"
        + "2. 본인의 의지와 상관없이 이용약관 위반 등으로 탈퇴된 경우 재등록이 제한될 수 있습니다");
    faqRepository.save(faq1);
    faqRepository.save(faq2);
  // -----------------------------------

  }
}
