package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.dto.InquiryRequest;
import com.team.final8teamproject.contact.dto.InquiryResponse;
import com.team.final8teamproject.contact.dto.UpdateInquiryRequest;
import com.team.final8teamproject.contact.entity.Inquiry;
import java.util.List;
import org.springframework.data.domain.Sort.Direction;

public interface InquiryService {

  void createInquiry(InquiryRequest inquiryRequest, String username);
  void updateInquiry(Long id, String username, UpdateInquiryRequest updateInquiryRequest);

  void deleteInquiry(Long id, String username);
  void deleteManager(Long id);
  List<InquiryResponse> getInquiry(int page, int size, Direction direction, String properties);
  List<InquiryResponse> searchByKeyword(String keyword, int page, int size, Direction direction, String properties);
  InquiryResponse getSelectedInquiry(Long id);
  Inquiry findById(Long inquiryId);



}
