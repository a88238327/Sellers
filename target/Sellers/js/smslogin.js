window.onload=function () {
    startbox();
}
var sms="false";
function tip(content) {
    var tip=document.getElementById("tip");
    var word=document.getElementById("word");
    tip.style.display="block";
    word.innerHTML=content;
    setTimeout(function (){
        tip.style.display="none";
    },1000);
}
function isPoneAvailable(str) {
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if(!myreg.test(str)) {
        return false;
    } else {
        return true;
    }
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
        if (flag == 1) {
            if (check()) {
                flag = 2;
                settimer();
                postphone(document.getElementById("phone").value);
            } else {
                flag = 0;
                tip("请输入正确的手机号");
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

            if (check()) {
                flag = 2;
                settimer();
                postphone(document.getElementById("phone").value);
            } else {
                flag = 0;
                tip("请输入正确的手机号");
            }
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
    function check() {
        var phone=document.getElementById("phone");
        if(!isPoneAvailable(phone.value))
        {
            tip("请输入正确的手机号");
            return false;
        }
        else {
            return true;
        }
    }
    function postphone(phone) {
        $.post(
            "smsservlet",
            {
                phone:phone,
                type:"sms"
            },
            function(result){
                if(result=="true")
                {
                    sms="true";
                    tip("验证码已发送");
                }
                else {
                    tip("请输入正确的手机号");
                }
            }
        );
    }
}
function join(){
    var phone=document.getElementById("phone");
    var yanzhengma=document.getElementById("yanzhengma");
    if(sms=="false")
    {
        tip("请先获取验证码");
    }
    else if(yanzhengma.value=="")
    {
        tip("验证码不能为空");
    }
    else if(!isPoneAvailable(phone.value))
    {
        tip("请输入正确的手机号");
    }
    else {
        $.post(
            "smsservlet",
            {
                phone:document.getElementById("phone").value,
                code:yanzhengma.value,
                type:"check"
            },
            function(result){
                if(result=="true")
                {
                    location.href="adduserinfo";
                }
                else if(result=="phone_error")
                {
                    tip("手机号已被修改");
                }
                else if(result=="bucunzai")
                {
                    tip("用户未注册");
                }
                else {
                    tip("请输入正确的验证码");
                }
            }
        );
    }
}