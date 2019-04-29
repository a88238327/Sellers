window.onload=function () {
    getservicelist();
}
function getservicelist() {
    $.get(
        "serviceset",
        function(result){
            if(result=="phone_error")
            {
                alert("请先登录");
                location.href="login.html";
            }
            else {
                var obj=JSON.parse(result);
                create(obj);
            }
        }
    );
}
function create(obj) {
    var content=document.getElementById("content");
    for (var i=0;i<obj.length;i++)
    {(function () {
        var box=document.createElement("div");
        box.setAttribute("class","box");
        var p1=document.createElement("p");
        var button=document.createElement("div");
        button.setAttribute("class","button");
        var p2=document.createElement("p");
        content.appendChild(box);
        box.appendChild(p1);
        box.appendChild(button);
        button.appendChild(p2);
        p1.innerHTML=obj[i].name;
        if(obj[i].enable=="1")
        {
            p2.innerHTML="启用";
            p2.style.color="lime";
        }
        else {
            p2.innerHTML="停用";
            p2.style.color="red";
        }
        p2.addEventListener("click",function () {
            $.post(
                "serviceset",
                {
                    name:p1.innerHTML,
                    enable:p2.innerHTML
                },
                function(result){
                    p2.innerHTML=result;
                    tip("修改成功");
                    if(result=="启用")
                    {
                        p2.style.color="lime";
                    }
                    else {
                        p2.style.color="red";
                    }
                }
            );
        })
    })(i)

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