package com.spring.boot.bbs.ncubbs.controller;

import com.spring.boot.bbs.ncubbs.domain.*;
import com.spring.boot.bbs.ncubbs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;



@RestController
@RequestMapping("/main")
public class MainController {
    @Autowired
    private MainSectionRepository mainSectionRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TreplyRepository treplyRepository;
    @Autowired
    private TcommentRepository tcommentRepository;
    @Autowired
    private DreplyRepository dreplyRepository;
    @Autowired
    private  QreplyRepository qreplyRepository;

    @GetMapping()
    public ModelAndView shoicdetail(Model model,HttpSession session){
        Long sid = (Long) 1l;
        Section section = sectionRepository.getSectionBySid(sid);
        model.addAttribute("section",section);
        model.addAttribute("TopicList",topicRepository.findAll());
        model.addAttribute("mainSectionList", mainSectionRepository.findAll());
        model.addAttribute("SectionList",sectionRepository.findAll());
        model.addAttribute("UserList",userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("main/main","MainModel",model);
    }
    @GetMapping("/topic/{sid}")
    public ModelAndView showtopicdetail(@PathVariable("sid")Long sid,Model model,HttpSession session){
        Section section = sectionRepository.getSectionBySid(sid);
        model.addAttribute("section",section);
        model.addAttribute("TopicList",topicRepository.findAll());
        model.addAttribute("mainSectionList", mainSectionRepository.findAll());
        model.addAttribute("SectionList",sectionRepository.findAll());
        model.addAttribute("UserList",userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("main/main","MainModel",model);
    }
    @GetMapping("/signs")
    public ModelAndView showtopicdetail(Model model){
        model.addAttribute("message","成功");
        return new ModelAndView("admin/error","MainModel",model);
    }
    @GetMapping("/question/{sid}")
    public ModelAndView showquestiondetail(@PathVariable("sid")Long sid,Model model,HttpSession session){
        Section section = sectionRepository.getSectionBySid(sid);
        model.addAttribute("section",section);
        model.addAttribute("QuestionList",questionRepository.findAll());
        model.addAttribute("mainSectionList", mainSectionRepository.findAll());
        model.addAttribute("SectionList",sectionRepository.findAll());
        model.addAttribute("UserList",userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("main/showquestion","ShowQuestionModel",model);
    }

    @GetMapping("/demand/{sid}")
    public ModelAndView showdemanddetail(@PathVariable("sid")Long sid,Model model,HttpSession session){
        Section section = sectionRepository.getSectionBySid(sid);
        model.addAttribute("section",section);
        model.addAttribute("DemandList",demandRepository.findAll());
        model.addAttribute("mainSectionList", mainSectionRepository.findAll());
        model.addAttribute("SectionList",sectionRepository.findAll());
        model.addAttribute("UserList",userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("main/showdemand","ShowDemandModel",model);
    }

    @GetMapping("/state/{sid}")
    public ModelAndView showstate(@PathVariable("sid")Long sid,Model model,HttpSession session){
        Section section = sectionRepository.getSectionBySid(sid);
        model.addAttribute("section",section);
        model.addAttribute("DemandList",demandRepository.findAll());
        model.addAttribute("TopicList",topicRepository.findAll());
        model.addAttribute("QuestionList",questionRepository.findAll());
        model.addAttribute("mainSectionList", mainSectionRepository.findAll());
        model.addAttribute("SectionList",sectionRepository.findAll());
        model.addAttribute("UserList",userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("main/showstate","ShowStateModel",model);
    }

    @GetMapping("/hot/{sid}")
    public ModelAndView showhot(@PathVariable("sid")Long sid,Model model,HttpSession session){
        Section section = sectionRepository.getSectionBySid(sid);
        model.addAttribute("section",section);
        model.addAttribute("DemandList",demandRepository.findAll());
        model.addAttribute("TopicList",topicRepository.findAll());
        model.addAttribute("QuestionList",questionRepository.findAll());
        model.addAttribute("mainSectionList", mainSectionRepository.findAll());
        model.addAttribute("SectionList",sectionRepository.findAll());
        model.addAttribute("UserList",userRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("main/showhot","ShowHotModel",model);
    }

    @GetMapping("/addtopic")
    public ModelAndView addtopic(Model model,HttpSession session) {
        model.addAttribute("SectionList",sectionRepository.findAll());
        model.addAttribute("usernow",session.getAttribute("registeruser"));
        return new ModelAndView("main/addtopic","SectionModel",model);
    }

    @PostMapping(path = "/addtopicconfim")
    public String addtopicconfim(@RequestParam(value = "tname", required = true) String tname,
                                 @RequestParam(value = "tcontents", required = true) String tcontents,
                                 @RequestParam(value ="sname" ,required = true)String sname, HttpSession session)
    {
        Section section = sectionRepository.findBySname(sname);
        User user = (User) session.getAttribute("registeruser");
        Topic topic = new Topic();
        topic.setTname(tname);
        topic.setTcontents(tcontents);
        topic.setThot(false);
        topic.setTstate(false);
        topic.setTtop(false);
        topic.setTsid(section.getSid());
        topic.setTmsid(section.getMsid());
        if(user==null){
            return "0";
        }else {
            topic.setTuid(user.getUid());
            topicRepository.save(topic);
            return "1";
        }
    }

    @GetMapping("/adddemand")
    public ModelAndView adddemmand(Model model,HttpSession session) {
        model.addAttribute("SectionList",sectionRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("main/adddemand","SectionModel",model);
    }
    @PostMapping(path = "/adddemandconfim")
    public String adddemandconfim(@RequestParam(value = "dname", required = true) String dname,
                                 @RequestParam(value = "dcontents", required = true) String dcontents,
                                 @RequestParam(value ="sname" ,required = true)String sname,
                                 @RequestParam(value ="dscore" ,required = true)int dscore,HttpSession session)
    {
        Section section = sectionRepository.findBySname(sname);
        User user = (User) session.getAttribute("registeruser");
        Demand demand = new Demand();
        demand.setDname(dname);
        demand.setDcontents(dcontents);
        demand.setDhot(false);
        demand.setDstate(false);
        demand.setDtop(false);
        demand.setDsid(section.getSid());
        demand.setDmsid(section.getMsid());
        demand.setDscore(dscore);
        if(user==null){
            return "0";
        }else {
            demand.setDuid(user.getUid());
            demandRepository.save(demand);
            return "1";
        }

    }

    @GetMapping("/addquestion")
    public ModelAndView addquestion(Model model,HttpSession session) {
        model.addAttribute("SectionList",sectionRepository.findAll());
        model.addAttribute("usernow",(User)session.getAttribute("registeruser"));
        return new ModelAndView("main/addquestion","SectionModel",model);
    }
    @PostMapping(path = "/addquestionconfim")
    public String addquestionconfim(@RequestParam(value = "qname", required = true) String qname,
                                    @RequestParam(value = "qcontents", required = true) String qcontents,
                                    @RequestParam(value ="sname" ,required = true)String sname,
                                    @RequestParam(value ="qscore" ,required = true)int qscore,HttpSession session)
    {
        Section section = sectionRepository.findBySname(sname);
        User user = (User) session.getAttribute("registeruser");
        Topic topic = new Topic();
        Question question = new Question();
        question.setQname(qname);
        question.setQcontents(qcontents);
        question.setQhot(false);
        question.setQstate(false);
        question.setQtop(false);
        question.setQsid(section.getSid());
        question.setQmsid(section.getMsid());
        question.setQscore(qscore);
        if(user==null){
            return "0";
        }else {
            question.setQuid(user.getUid());
            questionRepository.save(question);
            return "1";
        }

    }

    public ReplyTopic createCommenttopic(Long topicId, String tcommentContent,HttpSession session) {

        System.out.print("maincontroller");
        ReplyTopic originalTopic= treplyRepository.getReplyTopicByRtid(topicId);
        User user = (User)session.getAttribute("registeruser");
        if(user==null)
            System.out.println("no user");
        CommentTopic tcomment = new CommentTopic(user.getUid(), tcommentContent);
        originalTopic.addCommenttopic(tcomment);
        return treplyRepository.save(originalTopic);
    }

    public Topic createReplytopic(Long topicId, String tcommentContent,HttpSession session) {

        Topic originalTopic = topicRepository.getTopicByTid(topicId);
        User user = (User)session.getAttribute("registeruser");
        if(user==null)
            System.out.println("no user");
        ReplyTopic tcomment = new ReplyTopic(user.getUid(), tcommentContent);
        originalTopic.addReplytopic(tcomment);
        return topicRepository.save(originalTopic);
    }

    public ReplyDemand createCommentdemand(Long topicId, String tcommentContent,HttpSession session) {

        System.out.print("maincontroller");
        ReplyDemand originalTopic= dreplyRepository.getReplyDemandByRdid(topicId);
        User user = (User)session.getAttribute("registeruser");
        if(user==null)
            System.out.println("no user");
        CommentDemand tcomment = new CommentDemand(user.getUid(), tcommentContent);
        originalTopic.addCommentdemand(tcomment);
        return dreplyRepository.save(originalTopic);
    }

    public Demand createReplydemand(Long demandId, String dcommentContent,HttpSession session) {

        Demand originalDemand = demandRepository.getDemandByDid(demandId);
        User user = (User)session.getAttribute("registeruser");
        if(user==null)
            System.out.println("no user");
        ReplyDemand dcomment = new ReplyDemand(user.getUid(), dcommentContent);
        originalDemand.addReplytopic(dcomment);
        return demandRepository.save(originalDemand);
    }
    public ReplyQuestion createCommentquestion(Long topicId, String tcommentContent,HttpSession session) {

        System.out.print("maincontroller");
        ReplyQuestion originalTopic= qreplyRepository.getReplyQuestionsByRqid(topicId);
        User user = (User)session.getAttribute("registeruser");
        if(user==null)
            System.out.println("no user");
        CommentQuestion tcomment = new CommentQuestion(user.getUid(), tcommentContent);
        originalTopic.addCommentquestion(tcomment);
        return qreplyRepository.save(originalTopic);
    }
    public Question createReplyquestion(Long questionId, String qcommentContent,HttpSession session) {

        Question originalQuestion = questionRepository.getQuestionByQid(questionId);
        User user = (User)session.getAttribute("registeruser");
        if(user==null)
            System.out.println("no user");
        ReplyQuestion qcomment = new ReplyQuestion(user.getUid(), qcommentContent);
        originalQuestion.addReplyquestion(qcomment);
        return questionRepository.save(originalQuestion);
    }

}
