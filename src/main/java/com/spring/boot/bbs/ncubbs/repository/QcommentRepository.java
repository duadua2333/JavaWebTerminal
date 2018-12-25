package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.CommentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QcommentRepository extends JpaRepository<CommentQuestion,Long>{
    CommentQuestion getCommentQuestionByCqid(Long cqid);

}
