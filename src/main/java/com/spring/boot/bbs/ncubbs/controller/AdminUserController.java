package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.User;
import com.spring.boot.bbs.ncubbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("admin/user")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    //新增用户

@GetMapping("/addUser")
public ModelAndView add(Model model){
    return new ModelAndView("admin/addUser","usermodel",model);
}

@PostMapping(path = "/maddUser")
public ModelAndView add(@RequestParam(value = "uname", required = true) String uname,
                            @RequestParam(value = "upassword", required = true) String upassword,
                        @RequestParam(value = "ubirthday", required = true) String ubirthday,
                        @RequestParam(value = "sex", required = true) String usex,
                            @RequestParam(value = "uphone", required = true) String uphone,
                            @RequestParam(value = "uemail", required = true) String email,
                            @RequestParam(value = "ujob", required = true) String job,
                            @RequestParam(value = "uworkspace", required = true) String workspace,
                           @RequestParam(value = "upoint", required = true) String point,
                        //@RequestParam(value = "avatar", required = true) String avatar,
                       // @RequestParam(value = "uid", required = true) String uid,

                            Model model) {
//public User(Long UID, String Uname,String Upassword,String Uphone, String Uemail, String Ujob,
                //String workspace,String Usex,Date Ubirthday,String avatar,String Uremark,int point,
        //boolean UIsMSmaster){
    Date birth=null;
    try {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        birth=sf.parse(ubirthday);
    }catch(ParseException e){
        System.out.println(e.getMessage());
    }

        if(uname.equals("")||upassword.equals("")||usex.equals("")||uphone.equals("")||email.equals("")
                ||job.equals("")||workspace.equals("")||point.equals("")||ubirthday.equals("")){
            model.addAttribute("message","添加用户失败,有未填写的字段");
            return new ModelAndView("admin/message","userModel", model);
        }


    User user=new User(null,uname,upassword,uphone,email,job,workspace,usex,birth,null,null,Integer.parseInt(point),
          true);
    User us=userRepository.save(user);

   if(us==null){
        model.addAttribute("message","添加用户失败");
        return new ModelAndView("admin/message","usermodel",model);
    }else{
        model.addAttribute("message","添加用户成功");
        return new ModelAndView("admin/message","usermodel",model);
    }
}

    //删除用户
    @PostMapping(path = "/delete")
    public String delete(@RequestParam(value = "uid", required = true) String id) {
        userRepository.deleteById( Long.parseLong(id));
        return "1";
    }

    //修改用户
    @GetMapping(path = "/update/{id}")
    public ModelAndView mupdate(@PathVariable Long id, Model model) {
    User user=userRepository.findById(id).get();
        model.addAttribute("user", user);
        return new ModelAndView("admin/updateUser", "userModel", model);
    }
    @PostMapping(path = "/updateUser")
    public ModelAndView mupdatem(@RequestParam(value = "uname", required = true) String uname,
                                 @RequestParam(value = "upassword", required = true) String upassword,
                                 @RequestParam(value = "sex", required = true) String sex,
                                 @RequestParam(value = "ubirthday", required = true) String ubirthday,
                                 @RequestParam(value = "uphone", required = true) String uphone,
                                 @RequestParam(value = "uemail", required = true) String email,
                                 @RequestParam(value = "ujob", required = true) String job,
                                 @RequestParam(value = "uworkspace", required = true) String workspace,
                                 @RequestParam(value = "upoint", required = true) String point,
                                 @RequestParam(value = "uid", required = true) String uid,
                                 Model model) {

        Date birth=null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            birth=sf.parse(ubirthday);
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        if(uname.equals("")||upassword.equals("")||uid.equals("")||sex.equals("")||uphone.equals("")||email.equals("")
                ||job.equals("")||workspace.equals("")||point.equals("")){
            model.addAttribute("message","修改用户失败,有未填写的字段");
            return new ModelAndView("admin/message","userModel", model);
        }
        Long uid1=Long.parseLong(uid);
        if(userRepository.findByUid(uid1)==null){
            model.addAttribute("message","修改用户失败,请输入有效的用户id");
            return new ModelAndView("admin/message","userModel", model);
        }
        User us=userRepository.findByUid(uid1);
        us.setUname(uname);
       int  p=Integer.parseInt(point);
        us.setPoint(p);
        us.setUpassword(upassword);
        us.setUemail(email);
        us.setUsex(sex);
        us.setUphone(uphone);
        us.setUjob(job);
        us.setWorkspace(workspace);
       // us.setAvatar(avatar);
        us.setUbirthday(birth);
        userRepository.saveAndFlush(us);

        if(us==null){
            model.addAttribute("message","修改用户失败");
            return new ModelAndView("admin/message","userModel", model);
        }else {
            model.addAttribute("message","修改用户成功");
            return new ModelAndView("admin/message","userModel", model);
        }
    }

}

