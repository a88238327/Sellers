var $url = window.location.href;
var localIds;
var servicename = decodeURI(decodeURI(GetQueryString("service"), "utf-8"));
var biaoti = document.getElementById("biaoti");
biaoti.innerHTML = servicename;
var serverId;
var flag = "false";
var load = document.getElementById("load");
var size = 720;
var dataurl;
$.get(
    "checkuser",
    function (result) {
        if (result == "phone_error") {
            alert("请先登录");
            location.href = "loginservlet";
        }
    }
);
window.onload = function () {
    var upimg = document.getElementById("upimg");
    var tupian = document.getElementById("tupian");
    var tijiao = document.getElementById("tijiao");
    upimg.addEventListener("change", function () {
            load.style.display = "block";
            if (upimg.value) {
                // selectFileImage(upimg,tupian);
                var file = upimg.files[0];
                var file_type = file.type;
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function (e) {

                    var image = new Image();
                    image.src = e.target.result;
                    image.onload = function () {  //创建一个image对象，给canvas绘制使用
                        var scale = 1;
                        if (this.width > size || this.height > size) {  //600只是示例，可以根据具体的要求去设定
                            if (this.width > this.height) {
                                scale = size / this.width;
                            } else {
                                scale = size / this.height;
                            }
                        }
                        var width = this.width * scale;
                        var height = this.height * scale;
                        var newImageData=compress(this, width, height, 0.8);

                        // if (cvs.width < cvs.height) {
                        //     var temp = cvs.height;
                        //     cvs.height = cvs.width;
                        //     cvs.width = temp;
                        //     ctx.rotate(-Math.PI / 2);
                        //     ctx.translate(-cvs.height, 0);
                        // }
                        //ctx.drawImage(this, 0, 0, cvs.width, cvs.height);
                        //var newImageData = cvs.toDataURL(file_type, 0.8);   //重新生成图片，<span style="font-family: Arial, Helvetica, sans-serif;">fileType为用户选择的图片类型</span
                        // console.log(cvs);
                        // console.log(newImageData);
                        tupian.src = newImageData;
                        dataurl = newImageData.replace("data:" + file_type + ";base64,", '');
                        $.post(
                            "getnumber",
                            {
                                serverId: dataurl
                            },
                            function (result) {
                                load.style.display = "none";
                                if (result == "false") {
                                    location.href = "loginservlet";
                                } else if (result == "error") {
                                    alert("识别错误");
                                } else {
                                    var span = document.getElementById("chepai");
                                    span.innerHTML = result;
                                    flag = "true";

                                }

                            }
                        );
                        //     }
                        // );

                    }
                }
            }
        }
    )
    ;
    tijiao.addEventListener("click", function () {
        if (flag == "false") {
            alert("请点击上方拍照");
        } else if (load.style.display == "block") {
            alert("加载中请稍等");
        } else {
            var span = document.getElementById("chepai");
            $.post(
                "startservice",
                {
                    servicename: servicename,
                    number: span.innerHTML
                },
                function (result) {
                    if (result == "bucunzai") {
                        alert("该车辆不存在系统中，请协助客户添加车辆");
                    } else if (result == "phone_error") {
                        alert("请先登录");
                        location.href = "loginservlet";
                    } else {
                        alert("订单已完成，感谢您的热心服务，車家网有您更精彩");
                        location.href = "start.html";
                    }
                }
            );
        }
    })

}
;
function compress(img, width, height, ratio) {
    var canvas, ctx, img64, orient;

    //获取图片方向
    orient = getPhotoOrientation(img);

    canvas = document.createElement('canvas');
    canvas.width = width;
    canvas.height = height;

    ctx = canvas.getContext("2d");

    //如果图片方向等于6 ，则旋转矫正，反之则不做处理
    if (orient == 6) {
        ctx.save();
        ctx.translate(width / 2, height / 2);
        ctx.rotate(90 * Math.PI / 180);
        ctx.drawImage(img, 0 - height / 2, 0 - width / 2, height, width);
        ctx.restore();
    } else {
        ctx.drawImage(img, 0, 0, width, height);
    }

    img64 = canvas.toDataURL("image/jpeg", ratio);
    return img64;
}
function getPhotoOrientation(img) {
    var orient;
    EXIF.getData(img, function () {
        orient = EXIF.getTag(this, 'Orientation');
    });
    return orient;
}
