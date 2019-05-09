var $url=window.location.href;
var orderID=GetQueryString("orderID");
var load=document.getElementById("load");

var localIds=new Array(0);
var flag="false";
var starnumber=0;
var num=9;
var media_id=new Array(0);

$.post(
    "Servlet",
    {
        type:"getSignature",
        url:$url
    },
    function(result){
        var config=JSON.parse(result);
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId:config.appid, // 必填，公众号的唯一标识
            timestamp:config.timestamp, // 必填，生成签名的时间戳
            nonceStr:config.noncestr, // 必填，生成签名的随机串
            signature:config.signature,// 必填，签名，见附录1
            jsApiList: ['chooseImage','uploadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    }
);

wx.ready(function(){
    $.get(
        "evaluation",
        {
            orderID:orderID
        },
        function(result){
            if(result=="yipingjia")
            {
                alert("该订单已评价");
                wx.closeWindow();
            }
            else {
            
                var obj=JSON.parse(result);
                var touxiang=document.getElementById("touxiang");
                var dianming=document.getElementById("dianming");
                
                touxiang.src=obj.touxiang;
                dianming.innerHTML=obj.name;
            }

        }
    );
});
window.onload = function(){
    var obj_lis = document.getElementById("star").getElementsByTagName("li");
    for(i=0;i<obj_lis.length;i++){
        (function () {
            var j=i;
            obj_lis[j].style.filter="grayscale(100%)";
            obj_lis[i].onclick = function(){
                starnumber=j+1;
                for(var k=0;k<obj_lis.length;k++)
                {
                   obj_lis[k].style.filter="grayscale(100%)";
                }
                for(var l=0;l<=j;l++)
                {
                    obj_lis[l].style.filter="grayscale(0%)";
                }
            }
        })(i)
    }
    var shangchuan=document.getElementById("shangchuan");
    shangchuan.addEventListener("click",function () {
        if(num-localIds.length>0) {
            var zhaopianzu = document.getElementById("zhaopianzu");
            wx.chooseImage({
                count: num - localIds.length, // 默认9
                sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    if (localIds.length == 0) {
                        localIds = res.localIds;
                    } else {
                        localIds = localIds.concat(res.localIds);
                    }

                    zhaopianzu.innerHTML = "";
                    for (var i = 0; i < localIds.length; i++) {
                        (function () {
                            var li = document.createElement("li");
                            var img = document.createElement("img");
                            img.src = localIds[i];
                            li.appendChild(img);
                            zhaopianzu.appendChild(li);
                            var j = i;
                            img.addEventListener("click", function () {
                                localIds.splice(j, 1);
                                zhaopianzu.removeChild(li);
                            })
                        })(i)
                    }
                }


            });
        }
        else {
            alert("最多只能添加9张照片");
        }
    })
    tijiao();

};
function tijiao() {
    var tijiao = document.getElementById("tijiao");
    tijiao.addEventListener("click",function () {
        var text=document.getElementById("text");
        var load=document.getElementById("load");
        load.style.display="block";
        if(flag=="false")
        {
            flag="true";
            if(localIds.length>0)
            {

                upimg(localIds);
            }
            else {
                $.ajaxSettings.traditional=true;

                $.post(
                    "evaluation",
                    {
                        orderID:orderID,
                        starnumber:starnumber,
                        text:text.value,
                        serverId:media_id
                    },
                    function (result) {
                        flag="false";
                        if(result=="true")
                        {
                            location.href="evaluationsuccess.html";
                        }
                    }
                );
            }

        }

    })
    function upimg(localIds) {
        var localId = localIds.pop();
        wx.uploadImage({
            localId: localId.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
            isShowProgressTips: 0, // 默认为1，显示进度提示
            success: function (res) {
                media_id.push(res.serverId);//返回图片的服务器端ID
                if(localIds.length > 0){
                    window.setTimeout(function(){
                        upimg(localIds);
                    },100);
                }
                else
                {
                    $.ajaxSettings.traditional=true;
                    $.post(
                        "evaluation",
                        {
                            orderID:orderID,
                            starnumber:starnumber,
                            text:text.value,
                            serverId:media_id
                        },
                        function (result) {
                            flag="false";
                            if(result=="true")
                            {
                                location.href="evaluationsuccess.html";
                            }
                        }
                    );
                }

            }
        })
    }
}
