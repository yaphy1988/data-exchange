
//tangyk 订单支付


$(function(){

    //展开、收缩详情
    $('#my_button').click(function(){
	if($(".dingopen").is(":visible")){
	    $(".dingopen").slideUp();
	    $(".up").hide();
	    $(".down").show();
	}else{
	    $(".dingopen").slideDown();
	    $(".up").show();
	    $(".down").hide();
	}
    });

    //我的银行卡
    $('.pay_select ul li').click(function(){
    	$(this).addClass("pay_select_imgOn").siblings().removeClass("pay_select_imgOn");
    });

    //关闭支付模态框，弹支付提示模态框
    $('#confirm_purchase .close').click(function(){
    	$("#prompt").modal("show"); 
    });

    //点『确认购买』，弹出支付提示模态框
    $('.goBuy').click(function(){
    	$("#prompt").modal("show"); 
    });




})

