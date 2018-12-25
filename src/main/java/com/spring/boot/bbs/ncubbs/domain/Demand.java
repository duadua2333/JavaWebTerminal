package com.spring.boot.bbs.ncubbs.domain;

import com.github.rjeschke.txtmark.Processor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "demand")
public class Demand {
    private static final long serialVersionUID = 1L;
    @Id//编号 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DID")
    private Long did;

    //一级分类编号
    @Column(name = "DMSID")
    private Long dmsid;

    //二级分类编号
    @Column(name = "DSID")
    private Long dsid;

    //作者id
    @Column(name="DUID")
    private Long duid;

    //帖子名称

    @Size(min=2, max=50)
    @Column(nullable = false, length = 50)
    private String dname;

    //帖子内容
    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @NotNull(message = "内容不能为空")
    @Size(min=2)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String dcontents;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载

    @Size(min=2)
    //@Column(nullable = false) // 映射为字段，值不能为空
    private String htmlcontent; // 将 md 转为 html


    //点击量
    @Column(name = "DclickCount")
    private Integer dclickcount=0;

    //回复量
    @Column(name = "DQeplyCount")
    private Integer dreplycount=0;

    //点赞量
    @Column(name = "DlikeCount")
    private Integer dlikecount=0;

    //时间
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp dtime;

    //是否加精
    @Column(name = "Dstate")
    private boolean dstate;


    @Column(name = "Dtop")
    private boolean dtop;

    @Column(name = "Dhot")
    private boolean dhot;

    @Column(name = "Dscore")
    private int dscore;

    /*@Column(name = "DreplyBetterUID")
    private Long dreplybetteruid;*/

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "demand_replydemand", joinColumns = @JoinColumn(name = "demand_did", referencedColumnName = "did"),
            inverseJoinColumns = @JoinColumn(name = "replydemand_rdid", referencedColumnName = "rdid"))
    private List<ReplyDemand> dreplys;


    //无参构造方法
    public Demand(){
    }
    public  Demand(Long did, Long dmsid, Long dsid,Long duid,String dname,String dcontents,String htmlcontent,Integer dclickcount,Integer dreplycount,Integer dlikecount
            ,Timestamp dtime,boolean dstate,boolean dtop,boolean dhot,int dscore){
        this.did=did;
        this.dmsid=dmsid;
        this.dsid=dsid;
        this.duid=duid;
        this.dname=dname;
        this.dcontents=dcontents;
        this.htmlcontent=htmlcontent;
        this.dclickcount=dclickcount;
        this.dreplycount=dreplycount;
        this.dlikecount=dlikecount;
        this.dstate=dstate;
        this.dhot=dhot;
        this.dtop=dtop;
        this.dtime=dtime;
        this.dscore=dscore;
        /*this.dreplybetteruid=dreplybetteruid;*/
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Long getDmsid() {
        return dmsid;
    }

    public void setDmsid(Long dmsid) {
        this.dmsid = dmsid;
    }

    public Long getDsid() {
        return dsid;
    }

    public void setDsid(Long dsid) {
        this.dsid = dsid;
    }

    public Long getDuid() {
        return duid;
    }

    public void setDuid(Long duid) {
        this.duid = duid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDcontents() {
        return dcontents;
    }

    public void setDcontents(String dcontents) {
        this.dcontents = dcontents;
        this.htmlcontent = Processor.process(dcontents);
    }

    public String getHtmlcontent() {
        return htmlcontent;
    }

    public void setHtmlcontent(String htmlcontent) {
        this.htmlcontent = htmlcontent;
    }

    public Integer getDclickcount() {
        return dclickcount;
    }

    public void setDclickcount(Integer dclickcount) {
        this.dclickcount = dclickcount;
    }

    public Integer getDreplycount() {
        return dreplycount;
    }

    public void setDreplycount(Integer dreplycount) {
        this.dreplycount = dreplycount;
    }

    public Integer getDlikecount() {
        return dlikecount;
    }

    public void setDlikecount(Integer dlikecount) {
        this.dlikecount = dlikecount;
    }

    public Timestamp getDtime() {
        return dtime;
    }

    public void setDtime(Timestamp dtime) {
        this.dtime = dtime;
    }

    public boolean isDstate() {
        return dstate;
    }

    public void setDstate(boolean dstate) {
        this.dstate = dstate;
    }

    public boolean isDtop() {
        return dtop;
    }

    public void setDtop(boolean dtop) {
        this.dtop = dtop;
    }

    public boolean isDhot() {
        return dhot;
    }

    public void setDhot(boolean dhot) {
        this.dhot = dhot;
    }

    public int getDscore() {
        return dscore;
    }

    public void setDscore(int dscore) {
        this.dscore = dscore;
    }

   /* public Long getDreplybetteruid() {
        return dreplybetteruid;
    }

    public void setDreplybetteruid(Long dreplybetteruid) {
        this.dreplybetteruid = dreplybetteruid;
    }
*/
    public List<ReplyDemand> getDreplys() {
        return dreplys;
    }

    public void setDreplys(List<ReplyDemand> dreplys) {
        this.dreplys = dreplys;
        this.dreplycount=this.dreplys.size();
    }

    @Override
    public String toString() {
        return "Demand{" +
                "did=" + did +
                ", dmsid=" + dmsid +
                ", dsid=" + dsid +
                ", duid=" + duid +
                ", dname='" + dname + '\'' +
                ", dcontents='" + dcontents + '\'' +
                ", htmlcontent='" + htmlcontent + '\'' +
                ", dclickcount=" + dclickcount +
                ", dreplycount=" + dreplycount +
                ", dlikecount=" + dlikecount +
                ", dtime=" + dtime +
                ", dstate=" + dstate +
                ", dtop=" + dtop +
                ", dhot=" + dhot +
                ", dscore=" + dscore +
                ", dreplys=" + dreplys +
                '}';
    }

    /**
     * 添加评论
     * @param comment
     */
    public void addReplytopic(ReplyDemand comment) {
        System.out.println(this.dreplys.size());
        this.dreplys.add(comment);
        System.out.println(this.dreplys.size());
        this.dreplycount = this.dreplys.size();
    }
}
