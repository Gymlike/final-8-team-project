package com.team.final8teamproject.contact.service;

import com.team.final8teamproject.contact.dto.FaqRequest;
import com.team.final8teamproject.contact.dto.FaqResponse;
import java.util.List;
import org.springframework.data.domain.Sort.Direction;

public interface FaqService {
  void saveFaq(FaqRequest faqRequest, Long managerId);
  void deleteFaq(Long id, Long managerId);
  List<FaqResponse> getFaqList(int page, int size, Direction direction, String properties);

  FaqResponse getSelectedFaq(Long id);

  List<FaqResponse> searchByKeyword(String keyword, int page, int size, Direction direction, String properties);


}
