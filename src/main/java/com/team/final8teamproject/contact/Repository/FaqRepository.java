package com.team.final8teamproject.contact.Repository;

import com.team.final8teamproject.contact.entity.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {

  Page<Faq> findAllByQuestionContainingOrAnswerContaining(String question, String answer, PageRequest of);
}
