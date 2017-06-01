$(function(){
    queryComplaintList(1);

    $('.nav.nav-tabs>li').on('click',function(u){
		if ($(this).children().attr('href')=='#tab01'){
            myOrderList(1);
		}else{
            queryComplaintList(1);
		}
	});


});

function updateComplaint(compId,comStatus) {
    var params ={
        compId:compId,comStatus:comStatus
    };
    var callBack = function (data) {
        $.gridUnLoading();
        if (data.success){
            WEB.msg.info('提示','更新成功！');
            queryComplaintList(1);
        }else{
            WEB.msg.info('提示','更新失败！');
        }
    }
    updateComplaintCommon(params,callBack);
}

//数据校验
function checkModalData(params){


    if(!params.orderId){
        WEB.msg.info('提示','订单编号不能为空！');
        return false;
    }
    if(!params.complaintContent){
        $('#complaintContent').next().attr('style','visibility: block;color:red');
        return false;
    }else{
        $('#complaintContent').next().attr('style','visibility: hidden');
    }
    if(!params.linPerson){
        $('#lnkposen').next().attr('style','visibility: block;color:red');
        return false;
    }else{
        $('#lnkposen').next().attr('style','visibility: hidden');
    }
    if(!WEB.check.isPhone(params.linkPhone)){
        $('#lnkphone').next().attr('style','visibility: block;color:red');
        return false;

    }else{
        $('#lnkphone').next().attr('style','visibility: hidden');
    }
   /* if(!WEB.check.isEmail(params.email)){
        $('#lnkemail').next().attr('style','visibility: block;');
        return false;
    }*/
    return true;
}
//更新、新增
function modalDataSubmit(){
    var orderId =$('#my_orderId').val();
    var complaintContent = $.trim($('#complaintContent').val());
    var complaintItem = $('#complaint_item').val();
    var linPerson = $.trim($('#lnkposen').val());
    var linkPhone = $.trim($('#lnkphone').val());
    // var email = $.trim($('#lnkemail').val());
    var params ={
        orderId:orderId,
        complaintContent:complaintContent,
        complaintItem:complaintItem,
        linPerson:linPerson,
        linkPhone:linkPhone,
        // email:email
    }
    var chekcFlag = checkModalData(params);
    if(chekcFlag){
        var url = MANAGE_ROOT+'/ordgdsComplaint/modalDataSubmit';
        $.appAjax({
            url:url,
            data:params,
            dataType:'json',
            cache:false,
            async:true,
            success:function (data) {
                if(data.success){
                    WEB.msg.info('提示','操作成功！');
                    $('#myModal').modal('hide');
                }else{
                    WEB.msg.info('提示','操作失败！');

                }
            }
        })
    }

}


function myOrderList(index){
    var startTime = $('#ord_startTime').val();
    var endTime = $('#ord_endTime').val();
    var ordStatus = $.trim($('#ord_status').val());
    var param={pageNo:index,startTime:startTime,endTime:endTime,ordStatus:ordStatus};
    $.gridLoading({ message:"正在加载中.." });
    $.appAjax({
        url:MANAGE_ROOT+'/ordgdsComplaint/myOrderList',
        cache:false,
        async:true,
        dataType:'html',
        data : param,
        success:function(data){
            $.gridUnLoading();
            $('#complant_content').html(data);
            $('#tab01').attr('class','tab-pane active');
            dateTimepickerinit('ord_startTime','ord_endTime');
        }
    });
}