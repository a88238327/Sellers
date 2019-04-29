$.get(
    "startservlet",
    function(result){
        if(result=="phone_error")
        {
            alert("请先登录账号");
            location.href="login.html";
        }
        else if(result=="null")
        {
            var tip=document.getElementById("tip");
            tip.style.display="block";
            var tianjia=document.getElementById("tianjia");
            tianjia.addEventListener("click",function () {
                location.href="serviceset.html";
            })
        }
        else {
            var obj=JSON.parse(result);
            create(obj);
        }
    }
);
function create(obj) {
    var content=document.getElementById("content");
    for(var i=0;i<obj.length;i++){
         (function () {
            var service=document.createElement("div");
            var name=document.createElement("p");
            service.setAttribute("class","service");
            name.setAttribute("class","name");
            content.appendChild(service);
            service.appendChild(name);
            name.innerHTML=obj[i];
            var str=obj[i];
            service.addEventListener("click",function () {
                location.href="startservice.html?service="+encodeURI(encodeURI(str));
            })
         })(i)

    }

}