package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.CommentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CQRespository extends JpaRepository<CommentQuestion,Long> {
    List<CommentQuestion> findByCqid(Long id);

}
