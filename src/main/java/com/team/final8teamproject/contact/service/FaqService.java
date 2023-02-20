package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import com.team.final8teamproject.contact.dto.UpdateFaqRequest;
import com.team.final8teamproject.contact.service.FaqServiceImpl.Result;
import java.util.List;
import org.springframework.data.domain.Sort.Direction;

public interface FaqService {
  void saveFaq(FaqRequest faqRequest, Long managerId);
  void deleteFaq(Long id, Long managerId);
  Result getFaqList(int page, int size, Direction direction, String properties);


  FaqResponse getSelectedFaq(Long id);

  Result searchByKeyword(String keyword, int page, int size, Direction direction, String properties);

  void updateFaq(Long id, Long managerId, UpdateFaqRequest updateFaqRequest);
}
