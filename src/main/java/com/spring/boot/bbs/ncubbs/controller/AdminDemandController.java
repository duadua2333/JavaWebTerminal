package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.*;
import com.spring.boot.bbs.ncubbs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("admin/demand")
public class AdminDemandController {
    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private CDRespository cdRespository;
    @Autowired
    private RDRepository rdRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView demand(Model model) {
        //查询所有的需求并实现分页
        List<Demand> list= demandRepository.findAll();
        model.addAttribute("demandList", list);
        return new ModelAndView("admin/demand", "demandModel", model);
    }

    //添加问答
    @GetMapping("/addDemand")
    public ModelAndView add(Model model){
        return new ModelAndView("admin/addDemand","demandmodel",model);
    }


    @GetMapping("/addctDemand/{id}")
    public ModelAndView addcdemand(@PathVariable Long id, Model model) {
        //显示添加帖子评论页面
        return new ModelAndView("admin/addDemand");
    }


    //添加帖子回复的管理
    @GetMapping("/addrtDemand/{id}")
    public ModelAndView addrdemand(@PathVariable Long id, Model model) {
        //显示添加帖子评论页面
        return new ModelAndView("admin/addDemand");
    }

    @GetMapping("/rtt/{id}")
    public ModelAndView reply(@PathVariable Long id, Model model) {
        List<ReplyDemand> replyDemand=rdRepository.findByRdid(id);
        if(replyDemand.isEmpty()){
            model.addAttribute("message","需求回复为0");
            return new ModelAndView("admin/message","demandModel", model);
        }else {
            model.addAttribute("topic", id);
            model.addAttribute("ReplyDemandList",replyDemand);
            return new ModelAndView("admin/ReplyDemand", "demandModel", model);
        }
    }

    @GetMapping("/ctt/{id}")
    public ModelAndView index(@PathVariable Long id, Model model) {
        List<CommentDemand> cd=cdRespository.findByCdid(id);
        if(cd.isEmpty()){
            model.addAttribute("message","需求评论为0");
            return new ModelAndView("admin/message","demandModel", model);
        }else {
            model.addAttribute("demand", id);
            model.addAttribute("CommentDemandList",cd);
            return new ModelAndView("admin/commentDemand", "demandModel", model);
        }
    }

    //添加问答
    @PostMapping(path = "/add/commentDemand")
    //  public  Question(Long qid, Long qmsid, Long qsid,Long quid,String qname,String qcontents,String htmlcontent,
    // Integer qclickcount,Integer qreplycount,Integer qlikecount
    //            ,Timestamp qtime,boolean qstate,boolean qtop,boolean qhot,int qscore,int qreplybetteruid){
    //
    public ModelAndView addmsection(@RequestParam(value = "dname", required = true) String dname,
                                    @RequestParam(value = "dmsid", required = true) String dmsid,
                                    @RequestParam(value = "dsid", required = true) String dsid,
                                    @RequestParam(value = "duid", required = true) String duid,
                                    @RequestParam(value = "dcontents", required = true) String dcontents,
                                    @RequestParam(value = "dclickcount", required = true) String dclickcount,
                                    @RequestParam(value = "dreplycount", required = true) String dreplycount,
                                    @RequestParam(value = "dlikecount", required = true) String dlikecount,
                                    @RequestParam(value = "dscore", required = true) String dscore,
                                   /* @RequestParam(value = "dreplybetteruid", required = true) String dreplybetteruid,*/
                                    //@RequestParam(value = "did", required = true)String did,
                                    Model model) {

        Long di=Long.parseLong(duid);/*写帖子用户id*/
        //Long dri=Long.parseLong(dreplybetteruid);/*回复较好的用户id*/
        if(userRepository.findByUid(di)==null){
            model.addAttribute("message","添加需求失败,请输入有效的用户id");
            return new ModelAndView("admin/message","questionModel", model);
        }

        Demand dp= new Demand(null,Long.parseLong(dmsid),Long.parseLong(dsid),Long.parseLong(duid),dname,dcontents,null,
                Integer.parseInt(dclickcount), Integer.parseInt(dreplycount), Integer.parseInt(dlikecount),null,false,false,false,
                Integer.parseInt(dscore));
        Demand q=demandRepository.saveAndFlush(dp);
        if(q==null){
            model.addAttribute("message","添加需求失败");
            return new ModelAndView("admin/message","questionModel", model);
        }else {
            model.addAttribute("message","添加需求成功");
            return new ModelAndView("admin/message","questionModel", model);
        }
    }

    //删除需求
    @PostMapping(path = "/demand/delete")
    public String delete(@RequestParam(value = "did", required = true) String id) {
        demandRepository.deleteById( Long.parseLong(id));
        return "1";
    }
//删除需求评论
    @PostMapping(path = "/commentdemand/delete")
    public String deletecd(@RequestParam(value = "cdid", required = true) String id) {
        demandRepository.deleteById( Long.parseLong(id));
        return "1";
    }
//删除需求回复
    @PostMapping(path = "/replydemand/delete")
    public String deleterd(@RequestParam(value = "rdid", required = true) String id) {
        demandRepository.deleteById( Long.parseLong(id));
        return "1";
    }

    //修改需求
    @GetMapping(path = "/update/{id}")
    public ModelAndView mupdate(@PathVariable Long id, Model model) {
        Demand demand=demandRepository.findById(id).get();

        model.addAttribute("demand", demand);
        return new ModelAndView("admin/updateDemand", "demandModel", model);
    }

    @PostMapping(path = "/updateDemand")
    public ModelAndView mupdatem(@RequestParam(value = "dname", required = false) String dname,
                                 @RequestParam(value = "dmsid", required = true) String dmsid,
                                 @RequestParam(value = "dsid", required = true) String dsid,
                                 @RequestParam(value = "duid", required = true) String duid,
                                 @RequestParam(value = "dcontents", required = true) String dcontents,
                                 @RequestParam(value = "dclickcount", required = true) String dclickcount,
                                 @RequestParam(value = "dreplycount", required = true) String dreplycount,
                                 @RequestParam(value = "dlikecount", required = true) String dlikecount,
                                 @RequestParam(value = "dscore", required = true) String dscore,
                               /*  @RequestParam(value = "dreplybetteruid", required = true) String dreplybetteruid,*/
                                 @RequestParam(value = "did", required = true) String did, Model model) {
        if(dname.equals("")||dmsid.equals("")||dsid.equals("")||duid.equals("")||dcontents.equals("")
                ||dclickcount.equals("")||dreplycount.equals("")||dlikecount.equals("")||dscore.equals("")){
            model.addAttribute("message","修改需求失败,有未填写的字段");
            return new ModelAndView("admin/message","demandModel", model);
        }
        Long di=Long.parseLong(duid);/*写需求用户id*/
       // Long dri=Long.parseLong(dreplybetteruid);/*回复较好的用户id*/
        Long i=Long.parseLong(did);/*问答id*/
        Long dmsd=Long.parseLong(dmsid);//一级分类编号
        Long dsd=Long.parseLong(dsid);//二级分类编号
        Integer dc=Integer.parseInt(dclickcount);//点击量
        Integer dl=Integer.parseInt(dlikecount);//点赞量
        Integer dr=Integer.parseInt(dreplycount);//回复量
        Integer ds=Integer.parseInt(dscore);//回复量

        if(userRepository.findByUid(di)==null){
            model.addAttribute("message","修改问答失败,请输入有效的用户id");
            return new ModelAndView("admin/message","questionModel", model);
        }
        Demand d=demandRepository.findByDid(i);
        d.setDmsid(dmsd);
        d.setDsid(dsd);
        d.setDuid(di);
        d.setDname(dname);
        d.setDclickcount(dc);
        d.setDlikecount(dl);
        d.setDreplycount(dr);
        d.setDcontents(dcontents);
        d.setDscore(ds);
        //d.setDreplybetteruid(dri);
       demandRepository.saveAndFlush(d);

        if(d==null){
            model.addAttribute("message","修改需求失败");
            return new ModelAndView("admin/message","demandModel", model);
        }else {
            model.addAttribute("message","修改需求成功");
            return new ModelAndView("admin/message","demandModel", model);
        }
    }
}
