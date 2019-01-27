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

})

/**
 * 上拉加载具体业务实现
 */
var index = 0;
function pullupRefresh() {
    mui.ajax('/source/selectNoticeByMenu',{
        dataType:'json',//服务器返回json格式数据
        data: { 'index': index, 'menu': 'housingAuthority' },
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
        },
        error:function(xhr,type,errorThrown){
            //异常处理；
            console.log(type);
        }
    });
}