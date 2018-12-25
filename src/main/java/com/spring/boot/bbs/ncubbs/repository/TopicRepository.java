package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.Topic;
import com.spring.boot.bbs.ncubbs.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface TopicRepository extends JpaRepository<Topic,Long> {
    /**
     * 根据用户名、帖子名查询帖子
     * @param tuid
     * @param tname
     * @param pageable
     * @return
     */
    Page<Topic> findByTuidAndTname(User tuid, String tname, Pageable pageable);

    Topic getTopicByTid(Long tid);

    Topic findByTid(Long id);
    Page<Topic> findByTuidAndTname(Long tuid, String tname, Pageable pageable);
    Topic findByTname(String name);
    List<Topic> findByTuid(Long tuid);
    List<Topic> findByTsid(Long tsid);
    @Transactional
    Topic deleteTopicByTid(Long tid);

    

}
