$(function() {

    mui.init({
        swipeBack: false,
        pullRefresh: {
            container: '#pullrefresh',
            up: {
                auto: true,
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });

    mui.ajax('/source/selectPicByMenu?menu=villageInfo',{
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

/**
 * 上拉加载具体业务实现
 */
var index = 0;
function pullupRefresh() {
    mui.ajax('/source/selectNoticeByMenu',{
        dataType:'json',//服务器返回json格式数据
        data: { 'index': index, 'menu': 'villageinfo' },
        type:'post',//HTTP请求类型
        timeout:10000,//超时时间设置为10秒；
        headers:{'Content-Type':'application/json'},
        success:function(data){
            if (data.length == 0) {
                mui('#pullrefresh').pullRefresh().endPullupToRefresh(true);
                return;
            }

            //服务器返回响应，根据响应结果，分析是否登录成功；
            $.each(data, function(i, child) {
                var cNode = $('#item').clone(true);
                cNode.css('display', 'block');
                $('#ulobj').append(cNode);
            });

            $.each(data, function(i, child) {
                $(".mui-media-body#title").eq(index).html(child.title)
                $("p#description").eq(index).html(child.description)
                $('a#href').eq(index).attr('href','/showPdf?fileId=' + child.fileId);
                $("span#date").eq(index).html(moment(child.createTime).format('YYYY-MM-DD'));
                index++;
            });

            mui('#pullrefresh').pullRefresh().endPullupToRefresh(false);

            mui('body').on('tap','a',function(){
                document.location.href = this.href;
            });
        },
        error:function(xhr,type,errorThrown){
            //异常处理；
            console.log(type);
        }
    });
}

function loadPic(item) {
    var html = '<div class="mui-slider-item"><a href="#"><img id="' + item.guid + '" src="?" /></a></div>';
    $('#loop').append(html);
    var address = "http://" + window.location.host + "/source/loadPic?fileId=" + item.pictureId;
    document.getElementById(item.guid).src = address;
}

