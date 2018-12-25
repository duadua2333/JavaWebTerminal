package com.spring.boot.bbs.ncubbs.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;



//reply
@Entity
@Table(name = "replytopic")
public class ReplyTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "rtid")
    //需求回复id
    private Long rtid;

    //帖子编号
    @Column(name = "tid")
    private Long tid;

    //回复人id
    @JoinColumn(name="uid")
    private Long uid;

    //回复内容
    @NotNull(message = "评论内容不能为空")
    @Size(min=2, max=500)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String rtcontents;



    //时间
    @Column(name = "rttime")
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp rttime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "replytopic_commentTopic", joinColumns = @JoinColumn(name = "replytopic_rtid", referencedColumnName = "rtid"),
            inverseJoinColumns = @JoinColumn(name = "commentTopic_ctid", referencedColumnName = "ctid"))
    private List<CommentTopic> tcomment;

    //无参构造方法
    protected ReplyTopic(){}
    //有参构造方法
    public ReplyTopic(Long utid, String rtcontents){
        this.uid=utid;
        this.rtcontents=rtcontents;
    }

    public ReplyTopic(Long rtd, Long tid, Long uid, String rtcontent, Timestamp rttime){
        this.rtid = rtid;
        this.tid =tid;
        this.uid = uid;
        this.rtcontents = rtcontent;
        this.rttime = rttime;
    }
    public Long getRtid() {
        return rtid;
    }


    public void setRtid(Long rtid) {
        this.rtid = rtid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getRtcontents() {
        return rtcontents;
    }

    public void setRtcontents(String rtcontents) {
        this.rtcontents = rtcontents;
    }


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Timestamp getRttime() {
        return rttime;
    }

    public void setRttime(Timestamp rttime) {
        this.rttime = rttime;
    }


    public List<CommentTopic> getTcomment() {
        return tcomment;
    }

    public void setTcomment(List<CommentTopic> tcomment) {
        this.tcomment = tcomment;
    }

    @Override
    public String toString() {
        return "ReplyTopic{" +
                "rtid=" + rtid +
                ", uid=" + uid +
                ", rtcontents='" + rtcontents + '\'' +
                ", rttime=" + rttime +
                '}';
    }



    /**
     * 添加对回帖的评论
     * @param comment
     */
    public void addCommenttopic(CommentTopic comment) {
        System.out.println(this.tcomment.size());
        this.tcomment.add(comment);
        System.out.println(this.tcomment.size());
    }
}
