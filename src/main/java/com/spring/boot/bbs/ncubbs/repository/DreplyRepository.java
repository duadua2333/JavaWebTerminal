package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.ReplyDemand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DreplyRepository extends JpaRepository<ReplyDemand,Long>{

    ReplyDemand getReplyDemandByRdid(Long rdid);

}
