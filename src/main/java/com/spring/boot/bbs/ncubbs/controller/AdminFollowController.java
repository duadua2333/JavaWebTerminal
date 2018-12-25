package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.Follow;
import com.spring.boot.bbs.ncubbs.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("admin/follow")
public class AdminFollowController {
    @Autowired
    private FollowRepository followRepository;

    @GetMapping
    public ModelAndView follow(Model model) {
        //查询所有的关注信息并实现分页
        List<Follow> list= followRepository.findAll();
        model.addAttribute("followList", list);
        return new ModelAndView("admin/follow", "followModel", model);
    }

    //删除关注
    @PostMapping(path = "/delete")
    public String delete(@RequestParam(value = "fid", required = true) String id) {
       followRepository.deleteById( Long.parseLong(id));
        return "1";
    }
}
