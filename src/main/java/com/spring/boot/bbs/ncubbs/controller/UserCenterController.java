package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.*;
import com.spring.boot.bbs.ncubbs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/userCenter")
public class UserCenterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private MainSectionRepository mainSectionRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CommentTopicRepository commentTopicRepository;
    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TreplyRepository treplyRepository;
    @Autowired
    private QreplyRepository qreplyRepository;
    @Autowired
    private DreplyRepository dreplyRepository;
    @GetMapping("/person")
    public ModelAndView showUserInfo(Model model, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        model.addAttribute("User", userRepository.findByUid(user.getUid()));
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/person", "PersonModel", model);
    }

    @GetMapping("/followme")
    public ModelAndView showFollowMe(Model model, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        model.addAttribute("FollowMeList", followRepository.findByUid2(user.getUid()));
        model.addAttribute("UserList", userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/followme", "FollowModel", model);
    }

    @GetMapping("/myfollow")
    public ModelAndView showMyFollow(Model model, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        model.addAttribute("MyFollowList", followRepository.findByUid1(user.getUid()));
        model.addAttribute("UserList", userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/myfollow", "FollowModel", model);
    }

    //我的帖子
    @GetMapping("/mytopic")
    public ModelAndView showMyTopic(Model model, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        model.addAttribute("TopicList", topicRepository.findByTuid(user.getUid()));
        model.addAttribute("UserList", userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/mytopic", "MyTopicModel", model);
    }

    //我的需求
    @GetMapping("/mydemand")
    public ModelAndView showMyDemand(Model model, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        model.addAttribute("DemandList", demandRepository.findByDuid(user.getUid()));
        model.addAttribute("UserList", userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/mydemand", "MyTopicModel", model);
    }

    //我的需求
    @GetMapping("/myquestion")
    public ModelAndView showMyQuestion(Model model, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        model.addAttribute("QuestionList", questionRepository.findByQuid(user.getUid()));
        model.addAttribute("UserList", userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/myquestion", "MyTopicModel", model);
    }

    //账号设置
    @GetMapping("changepassword")
    public ModelAndView showChangePassword(Model model, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        model.addAttribute("User", userRepository.findByUid(user.getUid()));
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/changepassword", "MyTopicModel", model);
    }

    @PostMapping(path = "/changeconfirm")
    public String changeconfirm(@RequestParam(value = "password", required = true) String password, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        User u = userRepository.findByUid(user.getUid());
        u.setUpassword(password);
        User s = userRepository.saveAndFlush(u);
        return "1";
    }
    //帖子内容修改
    @PostMapping(path = "/finalupdatetopic")
    public ModelAndView finalupdatetopic(@RequestParam(value="context",required = true) String text,
                                         @RequestParam(value="topicname",required = true) String name,HttpSession session){
       // Topic topic=(Topic)session.getAttribute("mytopic");
        Topic topic=topicRepository.findByTname(name);
        System.out.println(name);
        topic.setTcontents(text);
        topicRepository.saveAndFlush(topic);
        return new ModelAndView("redirect:/userCenter/mytopic");
    }
    @PostMapping(path = "/finalupdatequestion")
    public ModelAndView finalupdatequestion(@RequestParam(value="context",required = true) String text,
                                         @RequestParam(value="questionname",required = true) String name){
        // Topic topic=(Topic)session.getAttribute("mytopic");
        Question question=questionRepository.findByQname(name);
        question.setQcontents(text);
        questionRepository.saveAndFlush(question);
        return new ModelAndView("redirect:/userCenter/myquestion");
    }
    @PostMapping(path = "/finalupdatedemand")
    public ModelAndView finalupdatedemand(@RequestParam(value="context",required = true) String text,
                                         @RequestParam(value="demandname",required = true) String name){
        // Topic topic=(Topic)session.getAttribute("mytopic");
        //Topic topic=topicRepository.findByTname(name);
        //System.out.println(name);
        Demand demand=demandRepository.findByDname(name);
        demand.setDcontents(text);
        demandRepository.saveAndFlush(demand);
        return new ModelAndView("redirect:/userCenter/mydemand");
    }
    //用户信息修改
    @GetMapping("changeinfo")
    public ModelAndView showChangeInfo(Model model, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        model.addAttribute("User", userRepository.findByUid(user.getUid()));
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/changeinfo", "MyTopicModel", model);
    }

    @PostMapping(path = "/changeinfoconfim")
    public ModelAndView changeinfoconfirm(@RequestParam(value = "name", required = true) String name,
                                    @RequestParam(value = "phone", required = true) String phone,
                                    @RequestParam(value = "email", required = true) String email,
                                    @RequestParam(value = "job", required = true) String job,
                                    @RequestParam(value = "workplace", required = true) String workplace,
                                    @RequestParam(value = "remark", required = true) String remark,
                                    @RequestParam(value = "avatar", required = true) String avatar,
                                    @RequestParam(value = "birthday", required = true) String birthday,
                                    @RequestParam(value = "sex", required = true) String sex, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        User u = userRepository.findByUid(user.getUid());
        u.setUremark(remark);
        u.setUname(name);
        u.setUemail(email);
        u.setUjob(job);
        u.setUsex(sex);
        u.setUphone(phone);
        u.setWorkspace(workplace);
        u.setAvatar(avatar);
        Date birth = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birth = sdf.parse(birthday);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        u.setUbirthday(birth);
        User s = userRepository.saveAndFlush(u);
        return new ModelAndView("redirect:/userCenter/person");
    }
    //版主管理版面
    @GetMapping("/sectionmanager")
    public ModelAndView showManage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("registeruser");
        Section section = sectionRepository.findBySmasterid(user.getUid());
        model.addAttribute("TopicList", topicRepository.findByTsid(section.getSid()));
        model.addAttribute("DemandList", demandRepository.findByDsid(section.getSid()));
        model.addAttribute("QuestionList", questionRepository.findByQsid(section.getSid()));
        model.addAttribute("UserList", userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/sectionmanager", "MyTopicModel", model);
    }

    @GetMapping("/managerdeletopic/{tid}")
    public ModelAndView delete(@PathVariable("tid")Long id,Model model){
        topicRepository.deleteById(id);
        //topicRepository.removeByTid(tid);
        return new ModelAndView("redirect:/userCenter/sectionmanager");
    }

    @GetMapping("/managerdeledemand/{did}")
    public ModelAndView deletedemand(@PathVariable("did")Long id,Model model){
        demandRepository.deleteById(id);
        //topicRepository.removeByTid(tid);
        return new ModelAndView("redirect:/userCenter/sectionmanager");
    }
    //删除帖子回复
    @GetMapping("/deletopicreply/{rtid}")
    public ModelAndView deletetopicreply(@PathVariable("rtid")Long id,Model model){
        treplyRepository.deleteById(id);
        //topicRepository.removeByTid(tid);
        return new ModelAndView("redirect:/userCenter/mytopic");
    }
    //删除问答回复
    @GetMapping("/delequestionreply/{rqid}")
    public ModelAndView deletequestionreply(@PathVariable("rqid")Long id,Model model){
        qreplyRepository.deleteById(id);
        //topicRepository.removeByTid(tid);
        return new ModelAndView("redirect:/userCenter/myquestion");
    }
    //删除需求回复
    @GetMapping("/deledemandreply/{rdid}")
    public ModelAndView deletedemandreply(@PathVariable("rdid")Long id,Model model){
        dreplyRepository.deleteById(id);
        //topicRepository.removeByTid(tid);
        return new ModelAndView("redirect:/userCenter/mydemand");
    }
    //最优问答回复
    @GetMapping("/bestquestionreply/{uid}")
    public ModelAndView bestquestionreply(@PathVariable("uid")Long id,Model model,HttpSession session){
        User addscoreuser=userRepository.findByUid(id);
        User delescoreuser=(User)session.getAttribute("registeruser");
        Question nowquestion = (Question)session.getAttribute("myquestion");
        int xinscore=nowquestion.getQscore();
        int yuanaddscore=addscoreuser.getPoint();
        int yuandelescore=delescoreuser.getPoint();
        addscoreuser.setPoint(yuanaddscore+xinscore);
        delescoreuser.setPoint(yuandelescore-xinscore);
        userRepository.saveAndFlush(addscoreuser);
        userRepository.saveAndFlush(delescoreuser);
        return new ModelAndView("redirect:/userCenter/myquestion");
    }
    //最优需求回复
    @GetMapping("/bestdemandreply/{uid}")
    public ModelAndView bestdemandreply(@PathVariable("uid")Long id,Model model,HttpSession session){
        User addscoreuser=userRepository.findByUid(id);
        User delescoreuser=(User)session.getAttribute("registeruser");
        Demand nowdemand=(Demand)session.getAttribute("mydemand");
        int xinscore=nowdemand.getDscore();
        int yuanaddscore=addscoreuser.getPoint();
        int yuandelescore=delescoreuser.getPoint();
        addscoreuser.setPoint(yuanaddscore+xinscore);
        delescoreuser.setPoint(yuandelescore-xinscore);
        userRepository.saveAndFlush(addscoreuser);
        userRepository.saveAndFlush(delescoreuser);
        return new ModelAndView("redirect:/userCenter/mydemand");
    }
    /**为了重定向
    @GetMapping("/topicdetail")
    public ModelAndView showtopicdetail(Model model){
        return new ModelAndView("userCenter/topicdetail", "MyTopicModel", model);
    }*/
    //管理员删问答
    @GetMapping("/managerdelequestion/{qid}")
    public ModelAndView deletequestion(@PathVariable("qid")Long id,Model model){
        questionRepository.deleteById(id);
        //topicRepository.removeByTid(tid);
        return new ModelAndView("redirect:/userCenter/sectionmanager");
    }
    /**用户修改帖子
    @GetMapping("/managerdeletopic/{tid}")
    public ModelAndView updatetopic(@PathVariable("tid")Long id,Model model){
        Topic topic=topicRepository.findByTid(id);

        return new ModelAndView("redirect:/userCenter/sectionmanager");
    }*/
    //退出登录
    @GetMapping("/outLoginServlet")
    public ModelAndView delete(HttpSession session,Model model){
        session.removeAttribute("registeruser");
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("user/login", "MyTopicModel", model);
    }

    @GetMapping("topicdetail/{tid}")
    public ModelAndView showdetail(@PathVariable("tid")Long tid,Model model,HttpSession session){
        Topic topic = topicRepository.getTopicByTid(tid);
        session.setAttribute("mytopic", topic);
        model.addAttribute("topic",topic);
        model.addAttribute("userlist",userRepository.findAll());
        //  model.addAttribute("replytopiclist",TopicRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/topicdetail","TopicModel",model);
    }

    @GetMapping("demanddetail/{did}")
    public ModelAndView showdetaildemand(@PathVariable("did")Long did,Model model,HttpSession session){
        Demand demand = demandRepository.getDemandByDid(did);
        session.setAttribute("mydemand", demand);
        model.addAttribute("demand",demand);
        model.addAttribute("userlist",userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/demanddetail","DemandModel",model);
    }

    @GetMapping("questiondetail/{qid}")
    public ModelAndView showdetailquestioon(@PathVariable("qid")Long qid,Model model,HttpSession session){
        Question question=questionRepository.getQuestionByQid(qid);
        session.setAttribute("myquestion", question);
        model.addAttribute("question",question);
        model.addAttribute("userlist",userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/questiondetail","QuestionModel",model);
    }

    @GetMapping("updatetopic/{tid}")
    public ModelAndView changetopic(@PathVariable("tid")Long tid,Model model,HttpSession session){
        Topic topic = topicRepository.findByTid(tid);
        model.addAttribute("topic",topic);
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/updatetopic","changetopicModel",model);
    }
    @GetMapping("updatequestion/{qid}")
    public ModelAndView changequestion(@PathVariable("qid")Long qid,Model model,HttpSession session){
        Question question=questionRepository.findByQid(qid);
        model.addAttribute("question",question);
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/updatequestion","changetopicModel",model);
    }
    @GetMapping("updatedemand/{did}")
    public ModelAndView changedemand(@PathVariable("did")Long did,Model model,HttpSession session){
        Demand demand=demandRepository.findByDid(did);
        model.addAttribute("demand",demand);
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("userCenter/updatedemand","changetopicModel",model);
    }
}