window.onload = function() {
    var canvas = document.getElementById("canvas");
//设置画板
    var ctx = canvas.getContext("2d");
    var s = window.screen;
    var w = s.width;
    var h = s.height;
    canvas.width = w;
    canvas.height = h;

//设置文字大小
    var fontSize = 10;
//计算一行有多少个文字 取整数 向下取整
    var clos = Math.floor(w / fontSize);
//思考每一个字的坐标
//创建数组把clos 个 0 （y坐标存储起来）
    var drops = [];
    var str = "qwertyuiopasdfghjklzxcvbnmasfhjahsfafassfsd";
//往数组里面添加 clos 个 0
    for (var i = 0; i < clos; i++) {
        drops.push(0);
    }

//绘制文字
    function drawString() {
        ctx.fillStyle = "rgba(0,0,0,0.15)";//没有清除缓存。页面在反复调用这个draw方法，透明度也在叠加，使里面的文字颜色也有变化，所有最开始的越来越透明。
        ctx.fillRect(0, 0, w, h);

        ctx.font = "600 " + fontSize + "px 微软雅黑";
        ctx.fillStyle = "white";

        for (var i = 0; i < clos; i++) {
            var x = i * fontSize;
            var y = fontSize * drops[i];//y一直往下移
            ctx.fillText(str[Math.floor(Math.random() * str.length)], x, y);
            if (y > h && Math.random() > 0.9) {//
                drops[i] = 0;
            }
            drops[i]++;
        }

    }

//定义一个定时器，每隔50毫秒执行一次
    setInterval(drawString, 30);
}