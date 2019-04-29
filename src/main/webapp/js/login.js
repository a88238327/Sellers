window.onload=function() {
    login();
}
var check="false";
function login() {
    var checkbox=document.getElementById("checkbox");
    var phone=document.getElementById("phone");
    var password=document.getElementById("password");
    var button=document.getElementById("button");
    button.addEventListener("click",function () {
        if(checkbox.checked==true)
        {
            check="true";
        }
        if(phone.value==""||password.value=="")
        {
            tip("请检查输入");
        }
        else {
            $.post(
                "loginservlet",
                {
                    phone:phone.value,
                    password:password.value,
                    check:check
                },
                function(result){
                    if(result=="true")
                    {
                        location.href="adduserinfo";
                    }
                    else if(result=="bucunzai")
                    {
                        tip("该用户未注册");
                    }
                    else{
                        tip("账号或密码错误");
                    }
                }
            );
        }
    })
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