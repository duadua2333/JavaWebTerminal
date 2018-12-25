package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.ReplyTopic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TreplyRepository extends JpaRepository<ReplyTopic,Long> {
    //主键 CommentTopic类 Long id类型

    ReplyTopic getReplyTopicByRtid(Long rtid);





}
