package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    List<Follow> findByUid2(Long uid2);
    List<Follow> findByUid1(Long uid1);
}
