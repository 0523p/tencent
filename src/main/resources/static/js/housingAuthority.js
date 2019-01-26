$(function() {
    mui.ajax('/source/selectNoticeByMenu?menu=housingAuthority',{
        dataType:'json',//服务器返回json格式数据
        type:'post',//HTTP请求类型
        timeout:10000,//超时时间设置为10秒；
        headers:{'Content-Type':'application/json'},
        success:function(data){

            if (data.length == 0) {
                $('#ulobj').append('<h1 id="title" class="mui-title">暂无动态</h1>');
                return;
            }

            //服务器返回响应，根据响应结果，分析是否登录成功；
            $.each(data, function(i, child) {
                var cNode = $('#item').clone(true);
                cNode.css('display', 'block');
                $('#ulobj').append(cNode);
            });

            $.each(data, function(i, child) {
                $(".mui-media-body#title").eq(i).html(child.title)
                $("p#description").eq(i).html(child.description)
                $("span#date").eq(i).html(child.createTime)
                $('a#href').eq(i).attr('href','/showPdf?fileId=' + child.fileId);
            });
        },
        error:function(xhr,type,errorThrown){
            //异常处理；
            console.log(type);
        }
    });
})

