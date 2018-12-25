/**
 * Created by Administrator on 16-3-12.
 */
/***********定义全局变量和定时器*************/
var t=null;
var n=0;/**动态变化**/
var count;
/************************/
$(function(){
    count=$(".main a").length;/*给动态变化的n备用*/
    /**让不是轮播中的第一个隐藏**/
    $(".main a:not(:first-child)").hide();
    /*点击当前li当前li对应的图片显示出来*/
    $(".main ul li").click(function(){
        var index=$(this).text()-1;
        n=index;
        console.log(n);
        /*****让当前显示的图片0.5S内渐隐，并匹配下一个渐显示*****/
        $(".main a").filter(":visible").fadeOut(500).parent().children().eq(index).fadeIn(1000);
        /*******聚焦，给当前li追加类，改变背景色*******/
        $(this).addClass("selected");
        /****同时移除当前li的所有兄弟的类名为selected，还原背景色*****/
        $(this).siblings().removeClass("selected");
    });
    /***定义定时器3秒执行一次****/
    t=setInterval("autoMove()",2000);
    /****当鼠标进入时候定时器停止，移除时候定时器开启****/
    $(".main").hover(function(){clearInterval(t)}, function(){t = setInterval("autoMove()", 3000);});
});
/***定义自动轮播函数****/
function autoMove(){
    if(n>=(count-1)){
        n=0;
    }else{
        ++n;
    }
    /*****给li执行匹配的事件*****/
    $(".main ul li").eq(n).trigger("click");
}