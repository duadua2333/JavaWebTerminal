package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.CommentDemand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DcommentRepository extends JpaRepository<CommentDemand,Long> {
    CommentDemand getCommentDemandByCdid(Long cdid);
}
