window.onload=function () {
    var top=document.getElementById("top");
    var bottom_left=document.getElementById("bottom_left");
    var bottom_right=document.getElementById("bottom_right");
    top.addEventListener("click",function () {
        location.href="start.html";
    })
    bottom_left.addEventListener("click",function () {
        location.href="set.html";
    })
    bottom_right.addEventListener("click",function () {
        location.href="";
    })
}

