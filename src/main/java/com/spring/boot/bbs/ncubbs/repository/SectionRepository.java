package com.spring.boot.bbs.ncubbs.repository;

import com.spring.boot.bbs.ncubbs.domain.Question;
import com.spring.boot.bbs.ncubbs.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SectionRepository extends JpaRepository<Section,Long> {
    Section findBySname(String sname);
    List<Section> findByMsid(Long id);
    Section findBySid(Long id);
    Section findBySmasterid(Long smasterid);
    Section getSectionBySid(Long sid);
}
