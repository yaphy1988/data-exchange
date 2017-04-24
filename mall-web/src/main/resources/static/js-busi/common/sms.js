(function($){
	
	var smsWin = {
		//短信tocken值；初始值设置为1；
		"tocken":"1",
		
		"phoneNo":"", ///号码
		
		"securityCode":"", //securityCode;
		
		"checkPass":false,
		
		"picVerifyCode":"", // 图片验证码
		
		"init":function(){
			smsWin.tocken = "1";
			smsWin.phoneNo = "";
			smsWin.securityCode = "";
			smsWin.checkPass = false;
			smsWin.picVerifyCode = "";
		},

		/*校验登录码*/
		"checksms" : function(seccode,phoneNo,callback){
			if(!seccode || seccode =="" || seccode == "undefined"){
				alert("请输入手机收到的校验码");
				return ;
			}
			
			if(smsWin.tocken=="1" || !smsWin.tocken || smsWin.tocken =="" || smsWin.tocken == "undefined"){
				alert("未获取手机校验码，请先获取之后，再输入校验码");
				return ;
			}
			
	        //验证短信验证码
			$.ajax({
				url : WEB_ROOT+"/security/checkSecurityCode",
				async : true,
				type : "POST",
				data : {"tocken" : smsWin.tocken,"inputSecurityCode":seccode,"phoneNo":phoneNo},
				dataType : 'json',
				success: function (data) {
					if(data.success){
						if($.isFunction(callback)){
							callback(smsWin.tocken,seccode,phoneNo);
						};
					} else {
						alert(data.error_msg);
					}
			    }
			});
		},
		
		/**
		 * 短信发送；
		 * @param phoneNo  接收短信的手机号码
		 * @param busiType 业务类型
		 * @param callback 回调；function  ；
		 */
		"sendsms" : function(phoneNo,busiType,callback){
			if(!phoneNo || phoneNo =="" || phoneNo == "undefined"){
				alert("接收短信手机号码不能为空，请重新输入");
				return ;
			}
			
			var mobileReg=/^1[34578]\d{9}$/;//简单的正则校验
		 	if(!mobileReg.test(phoneNo)){ 
		 	    alert("手机号码格式不正确，请重新输入");
		 	    return ; 
		 	 } 

			$.ajax({
				url : WEB_ROOT+"/security/sendSecurity",
				async : true,
				type : "POST",
				data : {"phoneNo" : phoneNo,"busiType":busiType,"lastTocken":smsWin.tocken,"picVerifyCode":smsWin.picVerifyCode},
				dataType : 'json',
				success: function (data) {
					if(data.success){
						smsWin.tocken = data.tocken;
						if($.isFunction(callback)){
							callback();
						} else {
							alert("发送验证码成功！");
						}
					    
					} else {
						alert(data.error_msg);
					}
			    }
			});
		},
		
		////
		afterSendSuccess : function(){
			//发送后，80秒效果
		    $("#sms-win-getcode").attr("disabled",true);
	        var se = 80;
	        $("#sms-win-getcode").html("重新获取验证码("+se+"秒)");
	        var timer = window.setInterval(function(){
	          se --;
	          $("#sms-win-getcode").html("重新获取验证码("+se+"秒)");
	          if(se==0){
	            $("#sms-win-getcode").html("重新获取验证码");
	            $("#sms-win-getcode").attr("disabled",false);
	            clearInterval(timer);
	          }
	        },1000);
	        
	        alert("发送验证码成功！");
		},
		
		///在校验成功之后；
		afterCheckSuccess:function(tocken,seccode,phoneno){
			smsWin.tocken = tocken;
			smsWin.phoneNo = phoneno;
			smsWin.securityCode = seccode;
			smsWin.checkPass = true;
			smsWin.dialogHtml.modal("hide");
		},
		
		dialogHtml : $('<div class="modal fade" id="sms-dialog-modelid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">'
	               + '<div class="modal-dialog modal-sm" style="margin-top:85px;width:35%;">'
	               +'   <div class="modal-content ">'
	               + '    <div class="modal-header">'
	               + '      <button type="button" class="close" style="display:none" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
	               + '      <h4 class="modal-title">获取验证码</h4>'
	               + '    </div>'
	               + '    <div class="modal-body">'
	               + '      <table class="detailTable" cellpadding="0" cellspacing="0" style="border:0px;">'
	               + '      <tbody>'
	               + '      <tr><td class="Hint" width="25%" style="border:0px;">原手机号码：</td>'
	               + '          <td colspan="3" align="left" style="border:0px;">'
	               + '            <span id="FPRMER_NUM" style="width:165px; color:red"></span>'
	               //+ '            <input type="text" id="FPRMER_NUM" style="width:165px;" name="FPRMER_NUM" maxsize="20" maxlength="20" desc="原手机号码" class="input01 valid sfInput02" nullable="no" size="11" value="" disabled>'
	               + '          </td>'
	               + '      </tr>'
	               + '      <tr><td class="Hint" style="border:0px;">新手机号码：</td>'
	               + '          <td colspan="3" align="left" style="border:0px;">'
	               + '            <input class="input01 valid sfInput02" style="width:165px;" type="text" id="sms-win-phoneNo" name="smsWinPhoneNo" desc="新手机号码" size="11" nullable="no" datatype="text">'
	               + '          </td>'
	               + '      </tr>'
	 				+ '      	<tr><td class="Hint" style="border:0px;"><span class="red">*</span>验证码：</td>'
	  	            + '          <td  style="*width:40%;float:left">'
	                + '           	<input class="form-control" style="width:80px;" type="text" id="PIC_VERIFY_CODE_UC" name="PIC_VERIFY_CODE_UC"  desc="验证码" nullable="no" equsize="4" datatype="text" size="20" onfocus="select();" >'
	  	            + '          </td>'
	  	            + '          <td style="width:50%;*width:40%;float:left"><img id="check_img_uc" src="" align="absmiddle" style="width:70px;height:32px; margin-left:5px;" onclick="checkImgChngUC();"/><a href="javascript:void(0);"  onClick="checkImgChngUC();" id="chgCheckCode" >换一张</a></td>'
	  	            + '         </tr>'
	               + '      <tr><td class="Hint" style="border:0px;"><span class="red">*</span>短信验证码：</td>'
	               + '          <td  style="border:0px;" align="left">'
	               + '            <input type="hidden" id="notUse" value="60"/>'
	               + '            <input type="hidden" id="sms-win-tocken" value=""/>'
	               + '      	  <input class="form-control" type="text" id="sms-win-seccode" name="securityCode" desc="验证码" nullable="no" equsize="4" datatype="text" size="16" onfocus="select();" style="width: 80px;float:left" />'
	               +'             <button class="btn btn-default" type="button" id="sms-win-getcode">获取验证码</button>'
	               + '          </td>'
	               + '        </tr>'
	               + '        </tbody>'
	               + '       </table>'
	               +'      </div>'
	               + '    <div class="modal-footer">'
	               +'        <button type="button" class="btn btn-primary modal-ok">确定</button>'
	               +'        <button type="button" class="btn btn-default modal-cancel" data-dismiss="modal">取消</button>'
	               + '    </div>'
	               + '  </div><!-- /.modal-content -->'
	               + '</div><!-- /.modal-dialog -->'
	             + '</div><!-- /.modal -->'),
	};
	
	$.smsDialogPlugin = {
		
		show : function(options){
			 ///默认参数为 ，修改或绑定手机号码；
			var opts = $.extend({},{
				oldPhoneNo : "",
				title:"绑定或修改手机号码",
				busiType:"21",   
				closedable:false,
				checkSuccessFun:false ///校验成功的回调函数；
			}, options);
			
			
			var $html = smsWin.dialogHtml;
			
			
		             
		    	if($("body > #sms-dialog-modelid").length>0){
		    		$("body > #sms-dialog-modelid").remove();
		    	}
		    	//添加标题；
		    	$html.find(".modal-title").empty().append(opts.title);
		    	
		    	if(opts.closedable){
		    		$html.find(":button.close").show();
		    	}
		    	
		    	$html.appendTo($("body"));
		    	
		    	///校验短信返回的验证码；
		    	$html.delegate(":button.modal-ok","click",function(){
		    		//调用校验方法；入参：tocken , seccode ,phoneno, 回调函数；
		    		smsWin.checksms($("#sms-win-seccode",$html).val(),$("#sms-win-phoneNo",$html).val(),smsWin.afterCheckSuccess);
		    		
		    	}).delegate("#sms-win-getcode","click",function(){
		    		//发送短信验证码
		    		var picVerifyCode = $("#PIC_VERIFY_CODE_UC").val();
		    		if (picVerifyCode == null || picVerifyCode == "") {
		    			alert("验证码不能为空！");
		    			return;
		    		}
		    		smsWin.picVerifyCode = picVerifyCode;
		    		smsWin.sendsms($("#sms-win-phoneNo",$html).val(),opts.busiType,smsWin.afterSendSuccess);
		    		checkImgChngUC();
		    	});
		    	
		    	$html.on('shown.bs.modal',function(){
		    		//初始化变量；
		    		smsWin.init();
		    		//页面显示的时候，调用；初始化展示旧的手机号码；
		    		$("#FPRMER_NUM",$html).empty().append(opts.oldPhoneNo);
		    		$("#sms-win-phoneNo",$html).val("");
		    		$("#sms-win-seccode",$html).val("");
		    	}).on('hidden.bs.modal',function(){
		    		///弹出框关闭的时候，判断校验是否通过，并且是否有回调函数，如果有的话，那么执行回调函数；
		    		if(smsWin.checkPass && $.isFunction(opts.checkSuccessFun)){
		    			opts.checkSuccessFun(smsWin.tocken,smsWin.securityCode,smsWin.phoneNo);
		    		}
		    	}).modal({
			    	  "backdrop":'static',
			    	  "keyboard":false
			    	});
			    $html = null;
			    
			    checkImgChngUC();
		},
	
	    ///独立的发送手机验证码的申请请求；
	    /**
	    * @param phoneNo 接收验证码的手机号码；
	    * @param busiType 业务编码；
	    * @param callback function；手机验证码发送成功之后的回调处理；无入参;
	    */
		"sendSmsSecurity" : function(phoneNo,busiType,picVerifyCode,callback){
			smsWin.picVerifyCode = picVerifyCode;
		    smsWin.sendsms(phoneNo, busiType, callback);
	    },
	
        ///独立的校验手机验证码的方法；
	    /**
	     * @param seccode 校验码；
	     * @param phoneNo 接收验证码的手机号码；
	     * @param busiType 业务编码；
	     * @param callback function；手机验证码发送成功之后的回调处理；入参：tocken\seccode\phoneNo;
	     */
        "checkSmsSecurity" : function(seccode,phoneNo,callback){        	
    	    smsWin.checksms(seccode, phoneNo, callback);
	    },
	    
	    "getTocken" : function(){
	    	return smsWin.tocken;
	    }
	};
	
})(jQuery);

function checkImgChngUC(){
	$("#PIC_VERIFY_CODE_UC").val("");
	document.getElementById('check_img_uc').src = WEB_ROOT + "/captcha/CapthcaImage?a="+ new Date().getTime();
}
