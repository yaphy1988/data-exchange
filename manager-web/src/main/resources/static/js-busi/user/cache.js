function loadDataToCache(busiType){
    $.ajax({
        url:WEB_ROOT+'/cache/init',
        cache:false,
        async:true,
        dataType:'html',
        data : {busiType:busiType},
        success:function(data){
            WEB.msg.info('提示',$(data).html(),null);
        }
    });
}