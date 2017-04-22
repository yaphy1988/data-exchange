/**
 * 消息弹出框
 * 基于 jquery 和 bootstrap3； 
 */

(function($){
	$.messagerPlugin = {
		
		closeType : 'cancel',
		
	    show : function(options){
	    	var opts = $.extend({},{
				buttons:['ok','cancel'],  //需要展示的按钮；目前仅根据数量进行控制展示；
				message:'',  //显示的消息内容
				title : '消息提醒',//标题
				closedable:false,  //默认不展示关闭按钮；
				okCallBack:false,
				cancelCallBack : false,
				msgType : false
			}, options);
	    	
	    	var $html = $('<div class="modal fade" id="msgAlertModelId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">'
	               + '<div class="modal-dialog modal-sm">'
	               +'   <div class="modal-content ">'
	               + '    <div class="modal-header">'
	               + '      <button type="button" class="close" style="display:none" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
	               + '      <h4 class="modal-title"></h4>'
	               + '    </div>'
	               + '    <div class="modal-body">'
	               +'      </div>'
	               + '    <div class="modal-footer">'
	               + '    </div>'
	               + '  </div><!-- /.modal-content -->'
	               + '</div><!-- /.modal-dialog -->'
	             + '</div><!-- /.modal -->');
	             
	    	if($("body > #msgAlertModelId").length>0){
	    		$("body > #msgAlertModelId").remove();
	    	}
	    	
	    	//添加按钮；
	    	if(opts.buttons.length==2){
	    		$html.find(".modal-footer").append( '<button type="button" class="btn btn-primary modal-ok" data-dismiss="modal">确认</button>'
	    				+'<button type="button" class="btn btn-default modal-cancel" data-dismiss="modal">取消</button>');
	    	} else {
	    		$html.find(".modal-footer").append( '      <button type="button" class="btn btn-primary modal-ok" data-dismiss="modal">确认</button>');
	    	}
	    	/*if($.inArrays('ok',opts.buttons)){
	    		$html.find(".modal-footer").append( '      <button type="button" class="btn btn-primary modal-ok" data-dismiss="modal">确认</button>');
	    	}
	    	if($.inArrays('cancel',opts.buttons)){
	    		$html.find(".modal-footer").append( '      <button type="button" class="btn btn-default modal-cancel" data-dismiss="modal">取消</button>');
	    	}*/
	    	
	    	//添加消息；
	    	$html.find(".modal-body").append('<p>'+opts.message+'</p>');
	    	//添加标题；
	    	$html.find(".modal-title").append(opts.title);
	    	
	    	if(opts.closedable){
	    		$html.find(":button.close").show();
	    	}
	    	
	    	$html.appendTo($("body"));
	    	
	    	/**
	    	 * 如果按钮的数量为2 ，那么需要处理 确定按钮的额外处理方式；
	    	 */
	    	if(opts.buttons.length == 2){
	    		$.messagerPlugin.closeType = 'cancel';
	    	} else {
	    		$.messagerPlugin.closeType = 'ok';
	    	}
	    	/**
	    	 * 确定按钮的时候，closeType 统一设定为 ok，保证关闭的时候，所触发的动作；
	    	 */
	    	$html.delegate(":button.modal-ok","click",function(){
	    		$.messagerPlugin.closeType = 'ok';
	    	});
	    	$html.delegate(":button.modal-cancel","click",function(){
	    		$.messagerPlugin.closeType = 'cancel';
	    	});
	    	
	    	$html.on("hidden.bs.modal",function(){
	    	  if($.messagerPlugin.closeType == 'cancel'){
	    		  if (opts.cancelCallBack && $.isFunction(opts.cancelCallBack)) {
	    			  opts.cancelCallBack.call();
		          }
	    	  } else {
	    		  if (opts.okCallBack && $.isFunction(opts.okCallBack)) {
	    			  opts.okCallBack.call();
		          }
	    	  }
	    	}).modal({
	    	  "backdrop":'static',
	    	  "keyboard":false
	    	});
	        $html = null;
	        
	    }
	}
}
)(jQuery)