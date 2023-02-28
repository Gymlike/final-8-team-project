package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.UpdateInquiryRequest;
import com.team.final8teamproject.contact.entity.Inquiry;
import com.team.final8teamproject.contact.service.InquiryServiceImpl.Result;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import java.util.List;
import org.springframework.data.domain.Sort.Direction;

public interface InquiryService {

  void createInquiry(InquiryRequest inquiryRequest, String username, String nickName);
  void updateInquiry(Long id, String username, UpdateInquiryRequest updateInquiryRequest);

  void deleteInquiry(Long id, String username, UserRoleEnum role);
//  void deleteManager(Long id);
  Result getInquiry(int page, int size, Direction direction, String properties);
  Result searchByKeyword(String keyword, int page, int size, Direction direction, String properties);
  InquiryResponse getSelectedInquiry(Long id);
  Inquiry findById(Long inquiryId);



}
