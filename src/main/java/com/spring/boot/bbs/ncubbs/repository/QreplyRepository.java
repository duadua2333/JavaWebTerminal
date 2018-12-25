package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.ReplyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QreplyRepository extends JpaRepository<ReplyQuestion,Long> {

    ReplyQuestion getReplyQuestionsByRqid(Long rqid);
}
