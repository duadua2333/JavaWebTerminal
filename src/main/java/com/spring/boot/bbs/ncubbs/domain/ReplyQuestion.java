package com.spring.boot.bbs.ncubbs.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * 问答回复类
 */
@Entity
@Table(name = "replyquestion")
public class ReplyQuestion {
    @Id//编号 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "rqid")
    private Long rqid;

    //问答编号
    @Column(name = "qid")
    private Long qid;

    //发表回复的人的编号
    @Column(name = "uid")
    private Long uid;

    //发表回复内容
    @Column(name = "rqcontent")
    private String rqcontent;

    //发表回复的时间
    @Column(name = "rqtime")
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Date rqtime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "replyquestion_CommentQuestion", joinColumns = @JoinColumn(name = "replyquestion_rqid", referencedColumnName = "rqid"),
            inverseJoinColumns = @JoinColumn(name = "CommentQuestion_cqid", referencedColumnName = "cqid"))
    private List<CommentQuestion> qcomment;

    protected ReplyQuestion(){
    }

    public ReplyQuestion(Long rqid,Long uid,String rqcontent,Date rqtime){
        this.rqid = rqid;
        this.uid = uid;
        this.rqcontent = rqcontent;
        this.rqtime = rqtime;
    }
    public ReplyQuestion(Long uid,String rdcontents){
        this.uid=uid;
        this.rqcontent=rdcontents;
    }

    public Long getRqid() {
        return rqid;
    }

    public void setRqid(Long rqid) {
        this.rqid = rqid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRqcontent() {
        return rqcontent;
    }

    public void setRqcontent(String rqcontent) {
        this.rqcontent = rqcontent;
    }

    public Date getRqtime() {
        return rqtime;
    }

    public void setRqtime(Date rqtime) {
        this.rqtime = rqtime;
    }


    public List<CommentQuestion> getQcomment() {
        return qcomment;
    }

    public void setQcomment(List<CommentQuestion> qcomment) {
        this.qcomment = qcomment;
    }

    @Override
    public String toString() {
        return "ReplyQuestion{" +
                "rqid=" + rqid +
                ", uid=" + uid +
                ", rqcontent='" + rqcontent + '\'' +
                ", rqtime=" + rqtime +
                '}';
    }

    /**
     * 添加对回帖的评论
     * @param comment
     */
    public void addCommentquestion(CommentQuestion comment) {
        System.out.println(this.qcomment.size());
        this.qcomment.add(comment);
        System.out.println(this.qcomment.size());
    }
}
