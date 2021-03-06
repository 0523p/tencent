$(function() {
    mui.ajax('/source/selectPicByMenu?menu=informationCenter',{
        dataType:'json',//服务器返回json格式数据
        type:'post',//HTTP请求类型
        timeout:10000,//超时时间设置为10秒；
        headers:{'Content-Type':'application/json'},
        success:function(data){
            if (data.length == 1) {
                //小于两张图片 不轮询播放
                loadPic(data[0]);
                mui('.mui-slider').slider({
                    interval: 0//自动轮播周期，若为0则不自动播放，默认为0；
                });
            } else {
                //按照412341的顺序进行轮播
                var last = data[data.length - 1];
                var html = '<div class="mui-slider-item"><a href="#"><img id="last" src="?" /></a></div>';
                $('#loop').append(html);
                var address = "http://" + window.location.host + "/source/loadPic?fileId=" + last.pictureId;
                document.getElementById('last').src = address;

                $.each(data, function(i, child) {
                    loadPic(child);
                    var indicator = '';
                    if (i == 0) {
                        indicator = '<div class="mui-indicator mui-active"></div>';
                    } else {
                        indicator = '<div class="mui-indicator"></div>';
                    }
                    $('#slider').append(indicator);
                });

                var first = data[0];
                var html = '<div class="mui-slider-item"><a href="#"><img id="first" src="?" /></a></div>';
                $('#loop').append(html);
                var address = "http://" + window.location.host + "/source/loadPic?fileId=" + first.pictureId;
                document.getElementById('first').src = address;
                mui('.mui-slider').slider({
                    interval: 3000//自动轮播周期，若为0则不自动播放，默认为0；
                });
            }
        },
        error:function(xhr,type,errorThrown){
            //异常处理；
            console.log(type);
        }
    });
})

function loadPic(item) {
    var html = '<div class="mui-slider-item"><a href="#"><img id="' + item.guid + '" src="?" /></a></div>';
    $('#loop').append(html);
    var address = "http://" + window.location.host + "/source/loadPic?fileId=" + item.pictureId;
    document.getElementById(item.guid).src = address;
}

