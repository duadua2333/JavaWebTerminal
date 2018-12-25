package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.MainSection;
import com.spring.boot.bbs.ncubbs.domain.Section;
import com.spring.boot.bbs.ncubbs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/admin/section")
public class AdminSectionController {
    @Autowired
    private com.spring.boot.bbs.ncubbs.repository.MainSectionRepository MainSectionRepository;
    @Autowired
    private com.spring.boot.bbs.ncubbs.repository.SectionRepository SectionRepository;
    @Autowired
    private com.spring.boot.bbs.ncubbs.repository.UserRepository UserRepository;

    @GetMapping
    public ModelAndView index(Model model) {
       List<MainSection> ms=MainSectionRepository.findAll();
       List<Section> s=SectionRepository.findAll();
        model.addAttribute("mainSectionList", ms);
        return new ModelAndView("admin/showsection", "sectionModel", model);
    }
    @GetMapping("/addmsection")
    public ModelAndView addmsection() {
        return new ModelAndView("admin/addmsection");
    }
    @GetMapping("/addsection/{id}")
    public ModelAndView addsection(@PathVariable Long id, Model model) {
        //显示添加副板块页面
        model.addAttribute("mainSection",id);
        return new ModelAndView("admin/addsection","sectionModel", model);
    }
    @GetMapping("/mms/{id}")
    public ModelAndView index(@PathVariable Long id, Model model) {
        //管理主板块里的副板块
        List<Section> s=SectionRepository.findByMsid(id);
            model.addAttribute("mainSection", id);
            model.addAttribute("sectionList", s);
            return new ModelAndView("admin/msection", "sectionModel", model);
    }
    @GetMapping("/mupdate/{id}")
    public ModelAndView mupdate(@PathVariable Long id, Model model) {
        MainSection ms=MainSectionRepository.findById(id).get();
            model.addAttribute("mainSection", ms);
            return new ModelAndView("admin/mupdate", "sectionModel", model);
    }
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id, Model model) {
        Section ms=SectionRepository.findById(id).get();
        model.addAttribute("Section", ms);
        return new ModelAndView("admin/updatesection", "sectionModel", model);
    }
    /*修改板块的页面的表单处理*/
    @PostMapping(path = "/mupdate")
    public ModelAndView mupdatem(@RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "name", required = true) String name,
                                    @RequestParam(value = "msprofile", required = true) String msprofile,
                                    @RequestParam(value = "mid", required = true) String mid, Model model) {
        if(name.equals("")||msprofile.equals("")||mid.equals("")){
            model.addAttribute("message","修改板块失败,有未填写的字段");
            return new ModelAndView("admin/error","sectionModel", model);
        }
        Long mi=Long.parseLong(mid);/*版主id*/
        Long i=Long.parseLong(id);/*板块id*/
        if(UserRepository.findByUid(mi)==null){
            model.addAttribute("message","修改板块失败,请输入有效的用户id");
            return new ModelAndView("admin/error","sectionModel", model);
        }
        MainSection ms=MainSectionRepository.findByMsid(i);
        ms.setMsname(name);ms.setMsmasterid(mi);ms.setMsprofile(msprofile);
        MainSectionRepository.saveAndFlush(ms);
        if(ms==null){
            model.addAttribute("message","修改板块失败");
            return new ModelAndView("admin/error","sectionModel", model);
        }else {
            model.addAttribute("message","修改板块成功");
            return new ModelAndView("admin/error","sectionModel", model);
        }
    }
    /*修改副板块的post表单处理*/
    @PostMapping(path = "/update")
    public ModelAndView updatem(@RequestParam(value = "id", required = true) String id,
                                 @RequestParam(value = "name", required = true) String name,
                                 @RequestParam(value = "msprofile", required = true) String msprofile,
                                 @RequestParam(value = "mid", required = true) String mid, Model model) {
        if(name.equals("")||msprofile.equals("")||mid.equals("")){
            model.addAttribute("message","修改板块失败,有未填写的字段");
            return new ModelAndView("admin/error","sectionModel", model);
        }
        Long mi=Long.parseLong(mid);/*版主id*/
        Long i=Long.parseLong(id);/*板块id*/
        if(UserRepository.findByUid(mi)==null){
            model.addAttribute("message","修改板块失败,请输入有效的用户id");
            return new ModelAndView("admin/error","sectionModel", model);
        }
        Section ms=SectionRepository.findBySid(i);
        ms.setSname(name);ms.setSmasterid(mi);ms.setSprofile(msprofile);
        SectionRepository.saveAndFlush(ms);
        if(ms==null){
            model.addAttribute("message","修改板块失败");
            return new ModelAndView("admin/error","sectionModel", model);
        }else {
            model.addAttribute("message","修改板块成功");
            return new ModelAndView("admin/error","sectionModel", model);
        }
    }
    @PostMapping(path = "/msection/delete")
    public String msectiondelete(@RequestParam(value = "uid", required = true) String id) {
        MainSectionRepository.deleteById( Long.parseLong(id));
        return "1";
    }
    /*添加主版块的post表单处理*/
    @PostMapping(path = "/add/msection")
    public ModelAndView addmsection(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "msprofile", required = true) String msprofile,
                              @RequestParam(value = "mid", required = true) String mid, Model model) {
        if(name.equals("")||msprofile.equals("")||mid.equals("")){
            model.addAttribute("message","添加板块失败,有为填写的字段");
            return new ModelAndView("admin/error","sectionModel", model);
        }
        Long mi=Long.parseLong(mid);
        if(UserRepository.findByUid(mi)==null){
            model.addAttribute("message","添加板块失败,请输入有效的用户id");
            return new ModelAndView("admin/error","sectionModel", model);
        }
        MainSection ms= new MainSection(null,mi,msprofile,name,0);
        MainSection rms=MainSectionRepository.save(ms);
        User u=UserRepository.findByUid(mi);
        u.setUismsmaster(true);
        UserRepository.saveAndFlush(u);
        if(rms==null){
            model.addAttribute("message","添加板块失败");
            return new ModelAndView("admin/error","sectionModel", model);
        }else {
            model.addAttribute("message","添加板块成功");
            return new ModelAndView("admin/error","sectionModel", model);
        }
    }
    /*添加副板块的post表达处理*/
    @PostMapping(path = "/add/section")
    public ModelAndView addsection(@RequestParam(value = "id", required = true) String id,
                                    @RequestParam(value = "name", required = true) String name,
                                    @RequestParam(value = "msprofile", required = true) String msprofile,
                                    @RequestParam(value = "mid", required = true) String mid, Model model) {

        if(name.equals("")||msprofile.equals("")||mid.equals("")){
            model.addAttribute("message","添加板块失败,有为填写的字段");
            return new ModelAndView("admin/error","sectionModel", model);
        }
        Long mi=Long.parseLong(mid);
        Long i=Long.parseLong(id);
        if(UserRepository.findByUid(mi)==null){
            model.addAttribute("message","添加板块失败,请输入有效的用户id");
            return new ModelAndView("admin/error","sectionModel", model);
        }
        Section ms= new Section(null,i,mi,msprofile,name,0);
        Section rms=SectionRepository.saveAndFlush(ms);
        User u=UserRepository.findByUid(mi);
        u.setUismsmaster(true);
        UserRepository.saveAndFlush(u);
        if(rms==null){
            model.addAttribute("message","添加板块失败");
            return new ModelAndView("admin/error","sectionModel", model);
        }else {
            model.addAttribute("message","添加板块成功");
            return new ModelAndView("admin/error","sectionModel", model);
        }
    }



}

