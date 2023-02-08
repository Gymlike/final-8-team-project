package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import java.util.List;
import org.springframework.data.domain.Sort.Direction;

public interface InquiryService {

  void createInquiry(InquiryRequest inquiryRequest, Long userId);

  void updateInquiry(Long id, Long userId, InquiryRequest inquiryRequest);

  void deleteInquiry(Long id, Long userIdOrManagerId);
  void deleteManager(Long id);
  List<InquiryResponse> getInquiry(int page, int size, Direction direction, String properties);

  List<InquiryResponse> searchByKeyword(String keyword, int page, int size, Direction direction, String properties);
  InquiryResponse getSelectedInquiry(Long id);
}
