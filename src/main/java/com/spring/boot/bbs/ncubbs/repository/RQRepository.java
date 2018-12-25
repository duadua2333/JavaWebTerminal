package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.ReplyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RQRepository extends JpaRepository<ReplyQuestion,Long> {
    List<ReplyQuestion> findByRqid(Long id);
}
