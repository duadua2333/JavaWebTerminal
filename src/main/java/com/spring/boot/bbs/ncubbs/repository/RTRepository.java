package com.spring.boot.bbs.ncubbs.repository;


import com.spring.boot.bbs.ncubbs.domain.ReplyTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RTRepository extends JpaRepository<ReplyTopic,Long> {
    List<ReplyTopic> findByRtid(Long id);

}
