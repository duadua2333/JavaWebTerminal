package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.SuperMaster;
import com.spring.boot.bbs.ncubbs.domain.User;
import com.spring.boot.bbs.ncubbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.spring.boot.bbs.ncubbs.repository.SuperMasterRepository;
import com.spring.boot.bbs.ncubbs.repository.UserRepository;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository UserReopsitory;

    /**
     * 登陆
     *
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }

    @PostMapping(path = "/loginconfim")
    public String loginconfim(@RequestParam(value = "username", required = true) String name,
                              @RequestParam(value = "password", required = true) String pwd, HttpSession session) {
        User user = UserReopsitory.findByUname(name);
        if (user.getUpassword().equals(pwd)) {
            //登陆成功
            if(session.getAttribute("registeruser")!=null){
                session.removeAttribute("registeruser");
            }
            session.setAttribute("registeruser", user);
            return "1";
        } else {
            //登陆失败
            return "0";
        }
    }

    @GetMapping("/index")
    public ModelAndView index(Model model) {
        //查询所有的用户并实现分页
        List<User> list = UserReopsitory.findAll();
        model.addAttribute("userList", list);
        return new ModelAndView("user/index", "userModel", model);
    }

    /**
     * 注册
     */
    @GetMapping("/sign")
    public ModelAndView register() {
        return new ModelAndView("user/sign");
    }

    @PostMapping(path = "/signconfim")
    public ModelAndView signconfim(@RequestParam(value = "password", required = true) String password,
                             @RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "phone", required = true) String phone,
                             @RequestParam(value = "email", required = true) String email,
                             @RequestParam(value = "job", required = true) String job,
                             @RequestParam(value = "workplace", required = true) String workplace,
                             @RequestParam(value = "remark", required = true) String remark,
                             @RequestParam(value = "avatar", required = true) String avatar,
                             @RequestParam(value = "birthday", required = true) String birthday,
                             @RequestParam(value = "sex", required = true) String sex,HttpSession session) {
        User user= new User();
        Date birth=null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birth = sdf.parse(birthday);
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        //user.setUid(null);
        user.setAvatar(avatar);
        user.setPoint(100);
        user.setState(1);
        user.setUbirthday(birth);
        user.setUemail(email);
        user.setUismsmaster(false);
        user.setUjob(job);
        user.setUsex(sex);
        user.setUphone(phone);
        user.setWorkspace(workplace);
        user.setUname(name);
        user.setUpassword(password);
        user.setUremark(remark);

        UserReopsitory.save(user);
        return new ModelAndView("redirect:/main/topic/1");

    }
}
