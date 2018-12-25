package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.CommentTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CTRepository extends JpaRepository<CommentTopic,Long> {
    List<CommentTopic> findByCtid(Long id);
}
