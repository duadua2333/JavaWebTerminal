package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.*;
import com.spring.boot.bbs.ncubbs.repository.CQRespository;
import com.spring.boot.bbs.ncubbs.repository.QuestionRepository;
import com.spring.boot.bbs.ncubbs.repository.RQRepository;
import com.spring.boot.bbs.ncubbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RestController
@RequestMapping("admin/question")
public class AdminQuestionController {
        @Autowired
        private QuestionRepository questionRepository;
        @Autowired
        private CQRespository cqRespository;
        @Autowired
        private RQRepository rqRepository;
        @Autowired
        private UserRepository userRepository;

        @GetMapping
        public ModelAndView demand(Model model) {
            //查询所有的问答并实现分页
            List<Question> list= questionRepository.findAll();
            model.addAttribute("questionList", list);
            return new ModelAndView("admin/question", "questionModel", model);
        }

        //添加问答
        @GetMapping("/addQuestion")
        public ModelAndView add(Model model){
            return new ModelAndView("admin/addQuestion","questionmodel",model);
        }

    //添加问答评论的管理
    @GetMapping("/addctQuestioon/{id}")
    public ModelAndView addcquestion(@PathVariable Long id, Model model) {
        //显示添加帖子评论页面
        return new ModelAndView("admin/addQuestion");
    }


    //添加帖子回复的管理
    @GetMapping("/addrtQuestion/{id}")
    public ModelAndView addrquestion(@PathVariable Long id, Model model) {
        //显示添加帖子评论页面
        return new ModelAndView("admin/addQuestion");
    }

    @GetMapping("/rtt/{id}")
    public ModelAndView reply(@PathVariable Long id, Model model) {
            List<ReplyQuestion> replyQuestion=rqRepository.findByRqid(id);
        if(replyQuestion.isEmpty()){
            model.addAttribute("message","问答回复为0");
            return new ModelAndView("admin/message","questionModel", model);
        }else {
            model.addAttribute("topic", id);
            model.addAttribute("ReplyQuestionList",replyQuestion);
            return new ModelAndView("admin/ReplyQuestion", "questionModel", model);
        }
    }

    @GetMapping("/ctt/{id}")
    public ModelAndView index(@PathVariable Long id, Model model) {
        List<CommentQuestion> cq=cqRespository.findByCqid(id);
        if(cq.isEmpty()){
            model.addAttribute("message","问答评论为0");
            return new ModelAndView("admin/message","questionModel", model);
        }else {
            model.addAttribute("question", id);
            model.addAttribute("CommentQuestionList",cq);
            return new ModelAndView("admin/commentQuestion", "questionModel", model);
        }
    }

    //添加问答
    @PostMapping(path = "/add/commentQuestion")
   //  public  Question(Long qid, Long qmsid, Long qsid,Long quid,String qname,String qcontents,String htmlcontent,
    // Integer qclickcount,Integer qreplycount,Integer qlikecount
    //            ,Timestamp qtime,boolean qstate,boolean qtop,boolean qhot,int qscore,int qreplybetteruid){
    //
    public ModelAndView addmsection(@RequestParam(value = "qname", required = false) String qname,
                                    @RequestParam(value = "qmsid", required = true) String qmsid,
                                    @RequestParam(value = "qsid", required = true) String qsid,
                                    @RequestParam(value = "quid", required = true) String quid,
                                    @RequestParam(value = "qcontents", required = true) String qcontents,
                                    @RequestParam(value = "qclickcount", required = true) String qclickcount,
                                    @RequestParam(value = "qreplycount", required = true) String qreplycount,
                                    @RequestParam(value = "qlikecount", required = true) String qlikecount,
                                    @RequestParam(value = "qscore", required = true) String qscore,
                               /*     @RequestParam(value = "qreplybetteruid", required = true) String qreplybetteruid*/
                                    //@RequestParam(value = "qid", required = true) String qid,
                                    Model model) {

        Long qi=Long.parseLong(quid);/*写帖子用户id*/
       // Long qri=Long.parseLong(qreplybetteruid);/*回复较好的用户id*/
        //首先需要判断存不存在这个用户id
        if(userRepository.findByUid(qi)==null){
            model.addAttribute("message","添加问答失败,请输入有效的用户id");
            return new ModelAndView("admin/message","questionModel", model);
        }

        Question qp= new Question(null,Long.parseLong(qmsid),Long.parseLong(qsid),Long.parseLong(quid),qname,qcontents,null,
                Integer.parseInt(qclickcount), Integer.parseInt(qreplycount), Integer.parseInt(qlikecount),null,false,false,false,
                Integer.parseInt(qscore));
        Question q=questionRepository.saveAndFlush(qp);
        if(q==null){
            model.addAttribute("message","添加问答失败");
            return new ModelAndView("admin/message","questionModel", model);
        }else {
            model.addAttribute("message","添加问答成功");
            return new ModelAndView("admin/message","questionModel", model);
        }
    }

    //删除问答
        @PostMapping(path = "/question/delete")
        public String delete(@RequestParam(value = "qid", required = true) String id) {
            questionRepository.deleteById( Long.parseLong(id));
            return "1";
        }
//删除问答评论
    @PostMapping(path = "/commentquestion/delete")
    public String deletecq(@RequestParam(value = "cqid", required = true) String id) {
       cqRespository.deleteById( Long.parseLong(id));
        return "1";
    }
//删除问答回复
    @PostMapping(path = "/replyquestion/delete")
    public String deleterq(@RequestParam(value = "rqid", required = true) String id) {
        rqRepository.deleteById( Long.parseLong(id));
        return "1";
    }

//修改问答
    @GetMapping(path = "/update/{id}")
    public ModelAndView mupdate(@PathVariable Long id, Model model) {
            Question question=questionRepository.findById(id).get();
        model.addAttribute("question", question);
        return new ModelAndView("admin/updateQuestion", "questionModel", model);
    }

    @PostMapping(path = "/updateQuestion")
    public ModelAndView mupdatem(@RequestParam(value = "qname", required = false) String qname,
                                 @RequestParam(value = "qmsid", required = true) String qmsid,
                                 @RequestParam(value = "qsid", required = true) String qsid,
                                 @RequestParam(value = "quid", required = true) String quid,
                                 @RequestParam(value = "qcontents", required = true) String qcontents,
                                 @RequestParam(value = "qclickcount", required = true) String qclickcount,
                                 @RequestParam(value = "qreplycount", required = true) String qreplycount,
                                 @RequestParam(value = "qlikecount", required = true) String qlikecount,
                                 @RequestParam(value = "qscore", required = true) String qscore,
                                 @RequestParam(value = "qid", required = true) String qid,
                                 Model model) {

        if(qname.equals("")||qmsid.equals("")||qsid.equals("")||quid.equals("")||qcontents.equals("")
                ||qclickcount.equals("")||qreplycount.equals("")||qlikecount.equals("")||qscore.equals("")){
            model.addAttribute("message","修改问答失败,有未填写的字段");
            return new ModelAndView("admin/message","questionModel", model);
        }
        Long qi=Long.parseLong(quid);/*写帖子用户id*/
        //Long qri=Long.parseLong(qreplybetteruid);/*回复较好的用户id*/
        Long i=Long.parseLong(qid);/*问答id*/
        Long qmsd=Long.parseLong(qmsid);//一级分类编号
        Long qsd=Long.parseLong(qsid);//二级分类编号
        Integer qc=Integer.parseInt(qclickcount);//点击量
        Integer ql=Integer.parseInt(qlikecount);//点赞量
        Integer qr=Integer.parseInt(qreplycount);//回复量
        Integer qs=Integer.parseInt(qscore);//回复量

        if(userRepository.findByUid(qi)==null){
            model.addAttribute("message","修改问答失败,请输入有效的用户id");
            return new ModelAndView("admin/message","questionModel", model);
        }
        Question q=questionRepository.findByQid(i);
        q.setQmsid(qmsd);
        q.setQsid(qsd);
        q.setQuid(qi);
        q.setQname(qname);
        q.setQclickcount(qc);
        q.setQlikecount(ql);
        q.setQreplycount(qr);
        q.setQcontents(qcontents);
        q.setQscore(qs);
       // q.setQreplybetteruid(qri);
        questionRepository.saveAndFlush(q);

        if(q==null){
            model.addAttribute("message","修改问答失败");
            return new ModelAndView("admin/message","questionModel", model);
        }else {
            model.addAttribute("message","修改问答成功");
            return new ModelAndView("admin/message","questionModel", model);
        }
    }
}
