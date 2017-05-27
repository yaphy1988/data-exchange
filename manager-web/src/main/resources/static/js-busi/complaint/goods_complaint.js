$(function(){
    myOrderList(1);

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
//数据校验
function checkModalData(params){


    if(!params.orderId){
        WEB.msg.info('提示','订单编号不能为空！');
        return false;
    }
    if(!params.complaintContent){
        $('#complaintContent').next().attr('style','visibility: block;');
        return false;
    }
    if(!params.lnkposen){
        $('#lnkposen').next().attr('style','visibility: block;');
        return false;
    }
    if(!WEB.check.isPhone(params.linkPhone)){
        $('#lnkphone').next().attr('style','visibility: block;');
        return false;

    }
    if(!WEB.check.isEmail(params.email)){
        $('#lnkemail').next().attr('style','visibility: block;');
        return false;
    }
    return true;
}
//更新、新增
function modalDataSubmit(){
    var orderId =$('#my_orderId').val();
    var complaintContent = $('#complaintContent').val();
    var linPerson = $('#lnkposen').val();
    var linkPhone = $('#lnkphone').val();
    var email = $('#lnkemail').val();
    var params ={
        orderId:orderId,
        complaintContent:complaintContent,
        linPerson:linPerson,
        linkPhone:linkPhone,
        email:email
    }
    var chekcFlag = checkModalData(params);
    var url = MANAGE_ROOT+'/ordgdsComplaint/modalDataSubmit';
    $.ajax({
        url:url,
        dataType:'json',
        data:params,
        cache:false,
        async:true,
        success:function (data) {
            if(data.success){

            }else{
                WEB.msg.info('提示','操作失败！');
            }
        }
    })
}

function modalDatahiden() {
    $('#myModal').modal('hide');
}
//我要投诉
function iwantComplaint(orderId){
    $.ajax({
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
	var params={pageNo:index};
	$.ajax({
		url:url,
		data:params,
		dataType:'html',
		cache:false,
		async:true,
		success:function(data){
			$('#complant_content').html(data);
			$('#complant_content div').removeClass('active');
			$('#tab02').attr('class','tab-pane active');
            $('.nav.nav-tabs>li').removeClass('active');
            $('.nav.nav-tabs>li').eq(1).attr('class','active');
		}
	});
}

function myOrderList(index){
    var param={pageNo:index};
    $.ajax({
        url:MANAGE_ROOT+'/ordgdsComplaint/myOrderList',
        cache:false,
        async:true,
        dataType:'html',
        data : param,
        success:function(data){
            $('#complant_content').html(data);
            $('#complant_content div').removeClass('active');
            $('#tab01').attr('class','tab-pane active');
            $('.nav.nav-tabs>li').removeClass('active');
            $('.nav.nav-tabs>li').eq(0).attr('class','active');
        }
    });
}