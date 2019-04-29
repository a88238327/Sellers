var sms="false";
window.onload=function () {
    startbox();
    clickb();
}
function startbox() {
    var flag = 0;
    var startx = 0;
    var box_left = document.getElementById("box_left");
    var tishi = document.getElementById("tishi");
    var box = document.getElementById("box");
    var box_right = document.getElementById("box_right");
    box_right.addEventListener("touchstart", function (e) {
        console.log(e)
        startx = e.touches[0].clientX;
    })
    box_right.addEventListener("touchmove", function (e) {
        var movex = e.touches[0].clientX;
        distancex = movex - startx;
        console.log(distancex);
        if (flag == 0) {
            if (distancex >= 0 && distancex <= box.offsetWidth - 30 && flag == 0) {
                console.log(box.offsetWidth);
                box_right.style.left = distancex + "px";
                tishi.innerHTML = "";
                box_left.style.width = distancex + "px";

            }
            if (distancex >= box.offsetWidth - 30) {
                flag = 1;
            }
        }
    })
    box_right.addEventListener("touchend", function (e) {
        if (flag == 0) {
            box_right.style.left = "0px";
            box_left.style.width = "0px";
            tishi.innerHTML = "右滑动获取验证码";
        }
        if (flag == 1) {
            getcode();

        }

    })
    var time = 60;

    function settimer() {
        if (time == 0) {
            box_right.style.left = "0px";
            box_left.style.width = "0px";
            flag = 0;
            time = 60;
            tishi.innerHTML = "右滑动获取验证码";
            return false;
        } else {
            time = time - 1;
            tishi.innerHTML = "重新获取（" + time + ")";
        }
        setTimeout(function () {
            settimer();
        }, 1000);
    }
    function tip(content) {
        var tip=document.getElementById("tip");
        var word=document.getElementById("word");
        tip.style.display="block";
        word.innerHTML=content;
        setTimeout(function (){
            tip.style.display="none";
        },1000);
    }
    function getcode() {
        $.get(
            "changepassword",
            function(result){
                if(result=="true")
                {
                    sms="true";
                    tip("验证码已发送");
                    flag=2;
                    settimer();
                }
                else {
                    tip("请先登录");
                    location.href="login.html";
                }
            }
        );
    }
}
function tip(content) {
    var tip=document.getElementById("tip");
    var word=document.getElementById("word");
    tip.style.display="block";
    word.innerHTML=content;
    setTimeout(function (){
        tip.style.display="none";
    },1000);
}
function clickb() {
    var tijiao=document.getElementById("tijiao");
    var password=document.getElementById("password");
    var code=document.getElementById("code");
    tijiao.addEventListener("click",function () {
        if(sms=="false")
        {
            tip("请先获取验证码");
        }
        else if(password.length<6)
        {
            tip("请输入至少6位数密码");
        }
        else {
            $.post(
                "changepassword",
                {
                    password:password.value,
                    code:code.value
                },
                function(result){
                    if(result=="true")
                    {
                        alert("修改成功");
                        location.href="shouye.html";
                    }
                    else {
                        tip("验证码错误");
                    }
                }
            );
        }

    })
}