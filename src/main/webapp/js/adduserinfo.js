var biaojiflag = "false";
var mylat;
var mylng;
var markerlat;
var markerlng;
var map;
var $url = window.location.href;
var checkboxvalue = [];
var localIds = "";
var serverId = "";
var city;
var country;
var district;
var province;
var street;
var streetNumber;
var town;
var village;
var dataurl;
var size=720;
$.post(
    "Servlet",
    {
        type: "getSignature",
        url: $url
    },
    function (result) {
        var config = JSON.parse(result);
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: config.appid, // 必填，公众号的唯一标识
            timestamp: config.timestamp, // 必填，生成签名的时间戳
            nonceStr: config.noncestr, // 必填，生成签名的随机串
            signature: config.signature,// 必填，签名，见附录1
            jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    }
);
wx.ready(function () {
    wx.getLocation({
        type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
        success: function (res) {
            mylat = res.latitude;
            mylng = res.longitude;
            markerlat = mylat;
            markerlng = mylng;
            map = new qq.maps.Map(document.getElementById("container"), {
                center: new qq.maps.LatLng(res.latitude, res.longitude),
                zoom: 15,
                disableDefaultUI: true
            });
            var mymarker = new qq.maps.Marker({
                position: new qq.maps.LatLng(mylat, mylng),
                map: map
            });
            qq.maps.event.addListener(map, 'click', function (event) {
                var marker = new qq.maps.Marker({
                    position: event.latLng,
                    map: map
                });
                mymarker.setMap(null);
                markerlat = event.latLng.lat;
                markerlng = event.latLng.lng;

                qq.maps.event.addListener(map, 'click', function (event) {
                    marker.setMap(null);
                });
            });

        },
        cancel: function (res) {
        }
    });
});

window.onload = function () {
    var flag = "false";
    var tijiao = document.getElementById("tijiao");
    var biaoji = document.getElementById("biaoji");
    var map = document.getElementById("map");
    var queren = document.getElementById("queren");
    var dianpuming = document.getElementById("dianpuming");
    var mastername = document.getElementById("mastername");
    var servicephone = document.getElementById("servicephone");
    var address = document.getElementById("address");
    var touxiang = document.getElementById("touxiang");
    biaoji.addEventListener("click", function () {
        map.style.display = "block";
    })
    queren.addEventListener("click", function () {
        map.style.display = "none";
        biaojiflag = "true";
    })
    tijiao.addEventListener("click", function () {
        if (dianpuming.value == "") {
            tip("请输入店铺名");
        } else if (mastername.value == "") {
            tip("请输入负责人姓名");
        } else if (servicephone.value == "") {
            tip("请输入客服手机号");
        } else if (address.value == "") {
            tip("请输入详细地址");
        } else if (biaojiflag == "false") {
            tip("请确认地图标记");
        } else if (dataurl == null) {
            tip("请添加头像");
        } else {
            if (flag == "false") {
                flag = "true";
                var load = document.getElementById("load");
                load.style.display = "block";
                checkboxvalue.length = 0;
                $('input:checkbox:checked').each(function (index, item) {
                    checkboxvalue.push($(this).val());
                });
                var geocoder = new qq.maps.Geocoder({
                    complete: function (result) {
                        city=result.detail.addressComponents.city;
                        country=result.detail.addressComponents.country;
                        district=result.detail.addressComponents.district;
                        province=result.detail.addressComponents.province;
                        street=result.detail.addressComponents.street;
                        streetNumber=result.detail.addressComponents.streetNumber;
                        town=result.detail.addressComponents.town;
                        village=result.detail.addressComponents.village;
                        $.post(
                            "adduserinfo",
                            {
                                city:city,
                                country:country,
                                district:district,
                                province:province,
                                street:street,
                                streetNumber:streetNumber,
                                town:town,
                                village:village,
                                dianpuming:dianpuming.value,
                                mastername:mastername.value,
                                servicephone:servicephone.value,
                                address:address.value,
                                lat:markerlat,
                                lng:markerlng,
                                checkboxvalue:checkboxvalue,
                                dataurl:dataurl
                            },
                            function (result) {
                                if (result == "true") {
                                    tip("添加成功！");
                                    location.href = "shouye.html";
                                } else if (result == "phone_error") {
                                    alert("您还未登录，请先登录");
                                    location.href = "login.html"

                                } else {
                                    flag = "false";
                                    tip("网络错误稍后再试");
                                }
                            }
                        );
                    }
                });

                geocoder.getAddress(new qq.maps.LatLng(markerlat, markerlng));

            }

        }
    })
    var uptouxiang=document.getElementById("uptouxiang");
    uptouxiang.addEventListener("change", function () {
        var touxiang=document.getElementById("touxiang");
        if (uptouxiang.value) {
            var file = uptouxiang.files[0];
            var file_type = file.type;
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                var image = new Image();
                image.src = e.target.result;
                image.onload = function () {  //创建一个image对象，给canvas绘制使用
                    var cvs = document.createElement('canvas');
                    var scale = 1;
                    if (this.width > size || this.height > size) {  //600只是示例，可以根据具体的要求去设定
                        if (this.width > this.height) {
                            scale = size / this.width;
                        } else {
                            scale = size / this.height;
                        }
                    }
                    cvs.width = this.width * scale;
                    cvs.height = this.height * scale;     //计算等比缩小后图片宽高
                    var ctx = cvs.getContext('2d');
                    ctx.drawImage(this, 0, 0, cvs.width, cvs.height);
                    var newImageData = cvs.toDataURL(file_type, 0.8);   //重新生成图片，<span style="font-family: Arial, Helvetica, sans-serif;">fileType为用户选择的图片类型</span
                    touxiang.src = newImageData;
                    dataurl = newImageData.replace("data:" + file_type + ";base64,", '');
                }
            }
        }
    })

}

function tip(content) {
    var tip = document.getElementById("tip");
    var word = document.getElementById("word");
    tip.style.display = "block";
    word.innerHTML = content;
    setTimeout(function () {
        tip.style.display = "none";
    }, 1000);
}