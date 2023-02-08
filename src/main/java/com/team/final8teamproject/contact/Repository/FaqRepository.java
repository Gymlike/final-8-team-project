package com.team.final8teamproject.contact.Repository;

import com.team.final8teamproject.contact.entity.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FaqRepository extends JpaRepository<Faq, Long> {

//  @Query(
//      value = "SELECT f FROM Faq f WHERE f.question LIKE %:title% OR f.answer LIKE %:content%"
//  )
  Page<Faq> findAllBySearch(String question, String answer, PageRequest of);


}
