package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question,Long>{
    Question findByQid(Long id);
    Question findByQname(String name);
    Question getQuestionByQid(Long qid);
    List<Question> findByQuid(Long quid);
    List<Question> findByQsid(Long qsid);
}
