package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.SuperMaster;
import com.spring.boot.bbs.ncubbs.domain.User;
import com.spring.boot.bbs.ncubbs.repository.SuperMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.spring.boot.bbs.ncubbs.repository.UserRepository;
import javax.servlet.http.HttpSession;
import java.util.List;



@RestController
@RequestMapping("/admin")
public class AdminMainController {
    @Autowired
    private SuperMasterRepository SuperMasterRepository;
    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("admin/login");
    }
    @PostMapping(path = "/loginconfim")
    public String loginconfim(@RequestParam(value = "username", required = true) String name,
                            @RequestParam(value = "password", required = true) String pwd,HttpSession session) {
        SuperMaster sm= SuperMasterRepository.findBySmname(name);
       if(sm.getSmpassword().equals(pwd)){
           //登陆成功
           session.setAttribute("user",sm);
           return "1";
       }else{
           //登陆失败
           return "0";
       }
    }
    @GetMapping("/index")
    public ModelAndView index(Model model) {
        //查询所有的用户并实现分页
        List<User> list=UserRepository.findAll();
        model.addAttribute("userList", list);
        return new ModelAndView("admin/index", "userModel", model);
    }
    @GetMapping("/outlogin")
    public ModelAndView loginout(HttpSession session,Model model) {
        session.removeAttribute("user");
        return new ModelAndView("admin/login", "userModel", model);
    }
}
