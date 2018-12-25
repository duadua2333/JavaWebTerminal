package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.MainSection;
import com.spring.boot.bbs.ncubbs.domain.Section;
import com.spring.boot.bbs.ncubbs.domain.SuperMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/admin/supermaster")
public class AdminSupermasterController {
    @Autowired
    private com.spring.boot.bbs.ncubbs.repository.SuperMasterRepository SuperMasterRepository;
    @GetMapping
    public ModelAndView index(HttpSession session,Model model) {
        SuperMaster user=(SuperMaster) session.getAttribute("user");
        if(user.getId()!=1|| user==null){
            /*不为超级管理员*/
            model.addAttribute("message", "只有超级管理员才能操作");
            return new ModelAndView("admin/error","sectionModel", model);
        }else {
            List<SuperMaster> sm = SuperMasterRepository.findAll();
            if (sm.isEmpty()) {
                model.addAttribute("message", "表里为空");
                return new ModelAndView("admin/error", "sectionModel", model);
            }
            model.addAttribute("masterList", sm);
            return new ModelAndView("admin/supermaster/showmaster", "sectionModel", model);
        }
    }
    @PostMapping(path = "delete")
    public String msectiondelete(@RequestParam(value = "uid", required = true) String id) {
        SuperMasterRepository.deleteById( Long.parseLong(id));
        return "1";
    }
    @GetMapping(path="/add")
    public ModelAndView add() {
       return new ModelAndView("admin/supermaster/add");
    }
    @PostMapping(path = "/add")
    public ModelAndView add(@RequestParam(value = "name", required = true) String name,
                                    @RequestParam(value = "mid", required = true) String mid, Model model) {
        if(name.equals("")||mid.equals("")){
            model.addAttribute("message","添加失败,有为填写的字段");
            return new ModelAndView("admin/error","sectionModel", model);
        }
       /*mid为password name为name*/
        SuperMaster ms=new SuperMaster(null,name,mid);
        SuperMaster rms=SuperMasterRepository.save(ms);
        if(rms==null){
            model.addAttribute("message","添加失败");
            return new ModelAndView("admin/error","sectionModel", model);
        }else {
            model.addAttribute("message","添加成功");
            return new ModelAndView("admin/error","sectionModel", model);
        }
    }
    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id, Model model) {
        SuperMaster ms=SuperMasterRepository.findById(id).get();
        model.addAttribute("superMaster", ms);
        return new ModelAndView("admin/supermaster/update", "sectionModel", model);
    }
    /*修改板块的页面的表单处理*/
    @PostMapping(path = "/update")
    public ModelAndView mupdatem(@RequestParam(value = "id", required = true) String id,
                                 @RequestParam(value = "name", required = true) String name,
                                 @RequestParam(value = "mid", required = true) String mid, Model model) {
        if(name.equals("")||mid.equals("")){
            model.addAttribute("message","修改失败,有未填写的字段");
            return new ModelAndView("admin/error","sectionModel", model);
        }
        Long i=Long.parseLong(id);/*userid*/
        SuperMaster ms=SuperMasterRepository.findById(i).get();
        ms.setSmname(name);ms.setSmpassword(mid);
        SuperMasterRepository.saveAndFlush(ms);
        if(ms==null){
            model.addAttribute("message","修改失败");
            return new ModelAndView("admin/error","sectionModel", model);
        }else {
            model.addAttribute("message","修改成功");
            return new ModelAndView("admin/error","sectionModel", model);
        }
    }
}
