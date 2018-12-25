package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.CommentTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TcommentRepository extends JpaRepository<CommentTopic,Long> {
    CommentTopic getCommentTopicByCtid(Long ctid);
}
