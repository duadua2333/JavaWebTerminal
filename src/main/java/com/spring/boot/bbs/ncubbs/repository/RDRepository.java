package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.ReplyDemand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RDRepository extends JpaRepository<ReplyDemand,Long> {
    List<ReplyDemand> findByRdid(Long id);
}
