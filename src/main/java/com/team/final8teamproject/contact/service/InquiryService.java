package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryRequestDto;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.entity.Inquiry;
import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

public interface InquiryService {

  void createInquiry(InquiryRequestDto inquiryRequest, Long userId);

  void updateInquiry(Long id, Long userId, InquiryRequestDto inquiryRequest);

  void deleteInquiry(Long id, Long userIdOrManagerId);
  void deleteManager(Long id);
  List<InquiryResponse> getInquiry(int page, int size, Direction direction, String properties);

  List<InquiryResponse> searchByKeyword(String keyword, int page, int size, Direction direction, String properties);
  InquiryResponse getSelectedInquiry(Long id);
}
