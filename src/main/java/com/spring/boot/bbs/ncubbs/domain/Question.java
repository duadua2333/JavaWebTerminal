package com.spring.boot.bbs.ncubbs.domain;

import com.github.rjeschke.txtmark.Processor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "question")
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id//编号 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QID")
    private Long qid;

    //一级分类编号
    @Column(name = "QMSID")
    private Long qmsid;

    //二级分类编号
    @Column(name = "QSID")
    private Long qsid;

    //作者id
    @Column(name="quid")
    private Long quid;

    //帖子名称

    @Size(min=2, max=50)
    @Column(nullable = false, length = 50)
    private String qname;

    //帖子内容
    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @Size(min=2)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String qcontents;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @Size(min=2)
   // @Column(nullable = false) // 映射为字段，值不能为空
    private String htmlcontent; // 将 md 转为 html


    //点击量
    @Column(name = "QclickCount")
    private Integer qclickcount=0;

    //回复量
    @Column(name = "TQeplyCount")
    private Integer qreplycount=0;

    //点赞量
    @Column(name = "QlikeCount")
    private Integer qlikecount=0;

    //时间
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp qtime;

    //是否加精
    @Column(name = "Qstate")
    private boolean qstate;


    @Column(name = "Qtop")
    private boolean qtop;

    @Column(name = "Qhot")
    private boolean qhot;

    @Column(name = "Qscore")
    private int qscore;

   /* @Column(name = "QreplyBetterUID")
    private Long qreplybetteruid;
*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "question_replyquestion", joinColumns = @JoinColumn(name = "question_qid", referencedColumnName = "qid"),
            inverseJoinColumns = @JoinColumn(name = "replyquestion_rqid", referencedColumnName = "rqid"))
    private List<ReplyQuestion> qreplys;

    //无参构造方法
    public Question(){
    }
    public  Question(Long qid, Long qmsid, Long qsid,Long quid,String qname,String qcontents,String htmlcontent,Integer qclickcount,Integer qreplycount,Integer qlikecount
            ,Timestamp qtime,boolean qstate,boolean qtop,boolean qhot,int qscore){
        this.qid=qid;
        this.qmsid=qmsid;
        this.qsid=qsid;
        this.quid=quid;
        this.qname=qname;
        this.qcontents=qcontents;
        this.htmlcontent=htmlcontent;
        this.qclickcount=qclickcount;
        this.qreplycount=qreplycount;
        this.qlikecount=qlikecount;
        this.qstate=qstate;
        this.qhot=qhot;
        this.qtop=qtop;
        this.qtime=qtime;
        this.qscore=qscore;
       /* this.qreplybetteruid=qreplybetteruid;*/
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public Long getQmsid() {
        return qmsid;
    }

    public void setQmsid(Long qmsid) {
        this.qmsid = qmsid;
    }

    public Long getQsid() {
        return qsid;
    }

    public void setQsid(Long qsid) {
        this.qsid = qsid;
    }

    public Long getQuid() {
        return quid;
    }

    public void setQuid(Long quid) {
        this.quid = quid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getQcontents() {
        return qcontents;
    }

    public void setQcontents(String qcontents) {
        this.qcontents = qcontents;
        this.htmlcontent = Processor.process(qcontents);
    }

    public String getHtmlcontent() {
        return htmlcontent;
    }

    public void setHtmlcontent(String htmlcontent) {
        this.htmlcontent = htmlcontent;
    }

    public Integer getQclickcount() {
        return qclickcount;
    }

    public void setQclickcount(Integer qclickcount) {
        this.qclickcount = qclickcount;
    }

    public Integer getQreplycount() {
        return qreplycount;
    }

    public void setQreplycount(Integer qreplycount) {
        this.qreplycount = qreplycount;
    }

    public Integer getQlikecount() {
        return qlikecount;
    }

    public void setQlikecount(Integer qlikecount) {
        this.qlikecount = qlikecount;
    }

    public Timestamp getQtime() {
        return qtime;
    }

    public void setQtime(Timestamp qtime) {
        this.qtime = qtime;
    }

    public boolean isQstate() {
        return qstate;
    }

    public void setQstate(boolean qstate) {
        this.qstate = qstate;
    }

    public boolean isQtop() {
        return qtop;
    }

    public void setQtop(boolean qtop) {
        this.qtop = qtop;
    }

    public boolean isQhot() {
        return qhot;
    }

    public void setQhot(boolean qhot) {
        this.qhot = qhot;
    }

    public int getQscore() {
        return qscore;
    }

    public void setQscore(int qscore) {
        this.qscore = qscore;
    }

   /* public Long getQreplybetteruid() {
        return qreplybetteruid;
    }

    public void setQreplybetteruid(Long qreplybetteruid) {
        this.qreplybetteruid = qreplybetteruid;
    }*/

    public List<ReplyQuestion> getQreplys() {
        return qreplys;
    }

    public void setQreplys(List<ReplyQuestion> qreplys) {
        this.qreplys = qreplys;
        this.qreplycount = this.qreplys.size();
    }

    @Override
    public String toString() {
        return "Question{" +
                "qid=" + qid +
                ", qmsid=" + qmsid +
                ", qsid=" + qsid +
                ", quid=" + quid +
                ", qname='" + qname + '\'' +
                ", qcontents='" + qcontents + '\'' +
                ", htmlcontent='" + htmlcontent + '\'' +
                ", qclickcount=" + qclickcount +
                ", qreplycount=" + qreplycount +
                ", qlikecount=" + qlikecount +
                ", qtime=" + qtime +
                ", qstate=" + qstate +
                ", qtop=" + qtop +
                ", qhot=" + qhot +
                ", qscore=" + qscore +
                ", qreplys=" + qreplys +
                '}';
    }

    /**
     * 添加评论
     * @param comment
     */
    public void addReplyquestion(ReplyQuestion comment) {
        System.out.println(this.qreplys.size());
        this.qreplys.add(comment);
        System.out.println(this.qreplys.size());
        this.qreplycount = this.qreplys.size();
    }


}
