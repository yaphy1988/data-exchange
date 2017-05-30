$(function(){
    queryComplaintList(1);

    $('.nav.nav-tabs>li').on('click',function(u){
        // $('.nav.nav-tabs>li').removeClass('active');
        // $(this).attr('class','active');
		if ($(this).children().attr('href')=='#tab01'){
            myOrderList(1);
		}else{
            queryComplaintList(1);
		}
	});


});
/**
 * 初始化时间控件
 * @param startTime
 * @param endTime
 */
function dateTimepickerinit(startTime,endTime) {
    $("#"+startTime+"").datetimepicker({
        format: 'yyyy-mm-dd 00:00:00',
        minView:'month',
        language: 'zh-CN',
        clearBtn:true,// 自定义属性,true 显示 清空按钮 false 隐藏 默认:true
        autoclose:true
        // startDate:new Date()
    }).on("click",function(){
        $("#"+startTime+"").datetimepicker("setEndDate",$("#"+endTime+"").val());
    });
    $("#"+endTime+"").datetimepicker({
        format: 'yyyy-mm-dd 23:59:59',
        minView:'month',
        language: 'zh-CN',
        clearBtn:true,// 自定义属性,true 显示 清空按钮 false 隐藏 默认:true
        autoclose:true
        // startDate:new Date()
    }).on("click",function(){
        $("#"+endTime+"").datetimepicker("setStartDate",$("#"+startTime+"").val());
    });
}

function pagerClick(index){
    queryComplaintList(index);
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

function modalDatahiden() {
    $('#myModal').modal('hide');
}
//我要投诉
function iwantComplaint(orderId){
    $.appAjax({
        url:MANAGE_ROOT+'/ordgdsComplaint/iwantComplaint',
        data:{orderId:orderId},
        dataType:'html',
        cache:false,
        async:true,
        success:function (data) {
			$('#myModal>div').html(data);
            $('#myModal').modal('show');
        }
    })

}

function queryComplaintList(index){
	var url = MANAGE_ROOT+'/ordgdsComplaint/queryComplaint';
	var startTime = $('#comp_startTime').val();
    var endTime = $('#comp_endTime').val();
    var compStatus = $.trim($('#comp_status').val());
    var compItem = $.trim($('#comp_item').val());
	var params={pageNo:index,startTime:startTime,endTime:endTime,compStatus:compStatus,compItem:compItem};
    $.gridLoading({ message:"正在加载中.." });
	$.appAjax({
		url:url,
		data:params,
		dataType:'html',
		cache:false,
		async:true,
		success:function(data){
            $.gridUnLoading();
			$('#complant_content').html(data);
			// $('#complant_content div').removeClass('active');
			$('#tab02').attr('class','tab-pane active');
            // $('.nav.nav-tabs>li').removeClass('active');
            // $('.nav.nav-tabs>li').eq(0).attr('class','active');
            dateTimepickerinit('comp_startTime','comp_endTime');
		}
	});
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
            // $('#complant_content div').removeClass('active');
            $('#tab01').attr('class','tab-pane active');
            // $('.nav.nav-tabs>li').removeClass('active');
            // $('.nav.nav-tabs>li').eq(1).attr('class','active');
            dateTimepickerinit('ord_startTime','ord_endTime');
        }
    });
}