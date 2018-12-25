window.onload = function() {
    var canvas = document.getElementById("canvas");
    //设置画板
    var ctx = canvas.getContext("2d");
    var s = window.screen;
    var w = s.width;
    var h = s.height;
    canvas.width = w;
    canvas.height = h;
    var points = [];
    function getPoint() {
        var point = new Object();
        point.x = Math.floor(Math.random()*w);
        point.y = Math.floor(Math.random()*h);
        point.r = Math.random()*3;
        point.ratex = Math.floor(Math.random()*3);
        point.ratey = Math.floor(Math.random()*3);
        return point;
    }
    function drawPoints() {
        for(var i=0;i<points.length;i++){
            var point = points[i];
            if(point.x>=0&&point.x<=w&&point.y>=0&&point.y<=h){
                ctx.fillStyle = "#e2bcbf";
                ctx.beginPath();
                ctx.arc(point.x,point.y,point.r,0,Math.PI*2,true);
                /*             ctx.stroke();*/
                ctx.fill();
                point.x += point.ratex;
                point.y += point.ratey;
                points[i]=point;
            }
            else{
                points[i]=getPoint();
            }
        }
    }
    function getDis(point1,point2) {
        var x1 = point1.x;
        var y1 = point1.y;
        var x2 = point2.x;
        var y2 = point2.y;
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    function drawLines() {
        for(var i=0;i<points.length;i++){
            for(var j = 0;j<points.length;j++){
                var dis = getDis(points[i],points[j]);
                if(dis<150){
                    ctx.strokeStyle = "#d4cfca";
                    ctx.lineWidth = 0.8 - dis/150;
                    ctx.beginPath();
                    ctx.moveTo(points[i].x,points[i].y);
                    ctx.lineTo(points[j].x,points[j].y);
                    ctx.stroke();
                }
            }
        }
    }
    for(var i=0;i<100;i++){
        points[i] = getPoint();
    }
    function draw() {
        ctx.clearRect(0,0,w,h);
        drawPoints();
        drawLines();
    }
    window.requestAnimationFrame(draw);
//界面刷新时弹出加载该界面
};

