package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.CommentTopic;
import com.spring.boot.bbs.ncubbs.domain.ReplyTopic;
import com.spring.boot.bbs.ncubbs.domain.Topic;
import com.spring.boot.bbs.ncubbs.domain.User;
import com.spring.boot.bbs.ncubbs.repository.CTRepository;
import com.spring.boot.bbs.ncubbs.repository.RTRepository;
import com.spring.boot.bbs.ncubbs.repository.TopicRepository;
import com.spring.boot.bbs.ncubbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 帖子控制器
 */

@RestController
@RequestMapping("admin/topic")
public class AdminTopicController {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CTRepository ctRepository;
    @Autowired
    private RTRepository rtRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView topic(Model model) {
        //查询所有的帖子并实现分页
        List<Topic> topic= topicRepository.findAll();
        model.addAttribute("topicList", topic);
        return new ModelAndView("admin/topic", "topicModel", model);
    }

//添加帖子,跳转到添加界面
@GetMapping("/addTopic")
public ModelAndView add(Model model){
    return new ModelAndView("admin/addTopic","topicmodel",model);
}

//添加帖子评论的管理
    @GetMapping("/addctTopic/{id}")
    public ModelAndView addctopic(@PathVariable Long id, Model model) {
        //显示添加帖子评论页面
        return new ModelAndView("admin/addTopic");
    }


    //添加帖子回复的管理
    @GetMapping("/addrtTopic/{id}")
    public ModelAndView addrtopic(@PathVariable Long id, Model model) {
        //显示添加帖子评论页面
        return new ModelAndView("admin/addTopic");
    }

    @GetMapping("/rtt/{id}")
    public ModelAndView reply(@PathVariable Long id, Model model) {
        List<ReplyTopic> replyTopic=rtRepository.findByRtid(id);
        if(replyTopic.isEmpty()){
            model.addAttribute("message","帖子评论为0");
            return new ModelAndView("admin/message","topicModel", model);
        }else {
            model.addAttribute("topic", id);
            model.addAttribute("ReplyTopicList",replyTopic);
            return new ModelAndView("admin/ReplyTopic", "topicModel", model);
        }
    }

    @GetMapping("/ctt/{id}")
    public ModelAndView index(@PathVariable Long id, Model model) {
        //管理主板块里的副板块
        List<CommentTopic> s=ctRepository.findByCtid(id);
        if(s.isEmpty()){
            model.addAttribute("message","帖子回复为0");
            return new ModelAndView("admin/message","topicModel", model);
        }else {
            model.addAttribute("topic", id);
            model.addAttribute("CommentTopicList",s);
            return new ModelAndView("admin/commentTopic", "topicModel", model);
        }
    }
    @PostMapping(path = "/add/commentTopic")
    // public  Topic(Long tid, Long tmsid, Long tsid,Long tuid,String tname,String tcontents,String htmlcontent,
    // Integer tclickcount,Integer treplycount,Integer tlikecount
    //    ,Timestamp ttime,boolean tstate,boolean ttop,boolean thot){
    public ModelAndView addmsection(@RequestParam(value = "tname", required = true) String tname,
                                    @RequestParam(value = "tmsid", required = true) String tmsid,
                                    @RequestParam(value = "tsid", required = true) String tsid,
                                    @RequestParam(value = "tuid", required = true) String tuid,
                                    @RequestParam(value = "tcontents", required = true) String tcontents,
                                    @RequestParam(value = "tclickcount", required = true) String tclickcount,
                                    @RequestParam(value = "treplycount", required = true) String treplycount,
                                    @RequestParam(value = "tlikecount", required = true) String tlikecount,
                                   // @RequestParam(value = "tid", required = true) String tid,
                                    Model model) {

        Long ti=Long.parseLong(tuid);/*写帖子的用户id*/
        if(userRepository.findByUid(ti)==null){
            model.addAttribute("message","添加帖子失败,请输入有效的用户id");
            return new ModelAndView("admin/message","topicModel", model);
        }

        Topic tp= new Topic(null,Long.parseLong(tmsid),Long.parseLong(tsid),Long.parseLong(tuid),tname,tcontents,null,
                Integer.parseInt(tclickcount), Integer.parseInt(treplycount), Integer.parseInt(tlikecount),null,false,false,false);
        Topic t=topicRepository.saveAndFlush(tp);
        if(t==null){
            model.addAttribute("message","添加帖子失败");
            return new ModelAndView("admin/message","topicModel", model);
        }else {
            model.addAttribute("message","添加帖子成功");
            return new ModelAndView("admin/message","topicModel", model);
        }
    }

    //删除帖子
    @PostMapping(path = "/topic/delete")
    public String delete(@RequestParam(value = "tid", required = true) String id) {
        topicRepository.deleteById( Long.parseLong(id));
        return "1";
    }

    //删除评论
    @PostMapping(path = "/commenttopic/delete")
    public String deletec(@RequestParam(value = "tid", required = true) String id) {
        ctRepository.deleteById( Long.parseLong(id));
        return "1";
    }

    //删除回复
    @PostMapping(path = "/replytopic/delete")
    public String deleter(@RequestParam(value = "tid", required = true) String id) {
        rtRepository.deleteById( Long.parseLong(id));
        return "1";
    }

    //修改帖子
    @GetMapping(path = "/update/{id}")
    public ModelAndView mupdate(@PathVariable Long id, Model model) {
        Topic topic=topicRepository.findById(id).get();
        model.addAttribute("topic", topic);
        return new ModelAndView("admin/updateTopic", "topicModel", model);
    }

    @PostMapping(path = "/updateTopic")
    public ModelAndView mupdatem(@RequestParam(value = "tname", required = true) String tname,
                                 @RequestParam(value = "tmsid", required = true) String tmsid,
                                 @RequestParam(value = "tsid", required = true) String tsid,
                                 @RequestParam(value = "tuid", required = true) String tuid,
                                 @RequestParam(value = "tcontents", required = true) String tcontents,
                                 @RequestParam(value = "tclickcount", required = true) String tclickcount,
                                 @RequestParam(value = "treplycount", required = true) String treplycount,
                                 @RequestParam(value = "tlikecount", required = true) String tlikecount,
                                 @RequestParam(value = "TopicState", required = true) Boolean TopicState,
                                 @RequestParam(value = "TopicTop", required = true) Boolean TopicTop,
                                 @RequestParam(value = "tid", required = true) String tid,  Model model) {
        if(tname.equals("")||tmsid.equals("")||tsid.equals("")||tuid.equals("")||tcontents.equals("") ||tclickcount.equals("")
                ||treplycount.equals("")||tlikecount.equals("")){
            model.addAttribute("message","修改帖子失败,有未填写的字段");
            return new ModelAndView("admin/message","topicModel", model);
        }

        Long ti=Long.parseLong(tuid);/*写帖子的用户id*/
        Long i=Long.parseLong(tid);/*板块id*/
        Long tmsd=Long.parseLong(tmsid);//一级分类编号
        Long tsd=Long.parseLong(tsid);//二级分类编号
        Integer tc=Integer.parseInt(tclickcount);//点击量
        Integer tl=Integer.parseInt(tlikecount);//点赞量
        Integer tr=Integer.parseInt(treplycount);//回复量

        if(userRepository.findByUid(ti)==null){
            model.addAttribute("message","修改帖子失败,请输入有效的用户id");
            return new ModelAndView("admin/message","topicModel", model);
        }

        Topic t=topicRepository.findByTid(i);
        t.setTmsid(tmsd);
        t.setTsid(tsd);
        t.setTuid(ti);
        t.setTname(tname);
        t.setTclickcount(tc);
        t.setTlikecount(tl);
        t.setTreplycount(tr);
        t.setTcontents(tcontents);
        t.setTstate(TopicState);
        t.setTtop(TopicTop);
        topicRepository.saveAndFlush(t);

        model.addAttribute("message","修改帖子成功");
        return new ModelAndView("admin/message","topicModel", model);
    }
}
