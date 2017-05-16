var basePath =  WEB_ROOT;
 $(function(){
	$("#needcontent").focus(function(){
		hiddenwarm('needcontentDiv');
	});
	$("#needTiel").focus(function(){
		hiddenwarm('needTielDiv');
	});
	$("#lnkposen").focus(function(){
		hiddenwarm('lnkposenDiv');
	});
	$("#lnkphone").focus(function(){
		hiddenwarm('lnkphoneDiv');
	});
	$("#lnkemail").focus(function(){
		hiddenwarm('lnkemailDiv');
	});
	queryPageModue();
})
//显示提示
function showwarm(id,msg){
	$("#"+id).find("p").css('visibility','visible');
	$("#"+id).find("span").html(msg);
}
//隐藏提示
function hiddenwarm(id){
	$("#"+id).find("p").css('visibility','hidden');
}
function encodeURI2(strinfo) {
	//中文编码一次，后台解析即可
	var strinfo1 = encodeURI(strinfo);
	return strinfo1;
}

/**查询楼层信息，异步加载楼层内容*/
function queryPageModue(){
	var url = basePath+'/homePage/queryPageModue';
	var params={};
	var callBack = function(data) {
		 if(data.success){
			 if(data.pageModuleList != null && data.pageModuleList != undefined){
				 $(data.pageModuleList).each(function(i,d){
					 switch (d.moduleId) {
					case 101://01-首页轮播广告；
						queryModue101(d.moduleId);
						break;
					case 102://01-首页数据推荐；
						querydata_recommend102(d.moduleId);
						break;
					case 103://03-数据定制；轮播图片获取 
						queryModue103(d.moduleId);
						break;
					case 104://04-平台动态
						queryModue104(d.subPageModuleList);
						break;
					case 109://109-合作伙伴
						queryPartner109(d.moduleId);
						break;							
					default:
						break;
					}
				 });
			 }
		 }else{
			 alert('查询楼层信息异常！');
		 }
	};
	doAjax(url,params,callBack);
}
//查询推荐楼层信息
function querydata_recommend102(moduleId){
	var url = basePath+'/homePage/queryPageModuleGoods';
	var params={moduleId:moduleId};
	var callBack =function(data){
		var html = "";
		if(data.success){
	        if(data.success){
    			var obj = data.moduleGoodsList ;
    			$(obj).each(function(i,d){
    				var name =  d.gdsName;
    				if(name ==null || name == undefined)
    					name="";
    				var gdsid =  d.gdsId; 
    				var gdsdetailurl = basePath+"/goods/details/"+gdsid+"-";
    				var vfsid= d.vfsid;
				    html +='<li><a href='+gdsdetailurl+' target="_blank"><img src="'+vfsid+'"><span>'+name+'</span></a></li>';
    		   
    			});
			}
		 }
		$('#data_recommend').html(html);
	};
	doAjax(url,params,callBack);
}
//查询轮播广告信息
function queryModue101(moduleId){
	var url = basePath+'/homePage/queryPageModuleAd';
	var params={moduleId:moduleId};
	var callBack =function(data){
		var htmlOl = '';
		var htmDiv = '';
		if(data.success){
			$(data.moduleAdList).each(function(i,d){
				if(i==0){
					htmlOl +='<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class="active"></li>';
					htmDiv +='<div class="item active">'+
	                '<a href="'+setLinkUrk(d.linkPage)+'" target="_blank"><img src="'+d.vfsId+'" alt="'+d.adTitle+'"></a>'+
	            '</div>';
				}else{
					htmlOl +='<li data-target="#carousel-example-generic" data-slide-to="'+i+'"></li>';
					htmDiv +='<div class="item">'+
		                '<a href="'+setLinkUrk(d.linkPage)+'" target="_blank"><img  src="'+d.vfsId+'" alt="'+d.adTitle+'"></a>'+
		            '</div>';
				}
			});
		}
		$('#carousel-example-generic>ol').html(htmlOl);
		$('#carousel-example-generic>div').html(htmDiv);
		$('#carousel-example-generic').carousel({interval: 5000});
	};
	doAjax(url,params,callBack);
}
//获取数据定制的图片
function queryModue103(moduleId){
	var url = basePath+'/homePage/queryPageModuleAd';
	var params={moduleId:moduleId};
	var callBack =function(data){
		var htmlOl = '';
		var htmDiv = '';
		if(data.success){
			if(data.moduleAdList != null){
				var moduleAd = data.moduleAdList[0];
				$("#datasetImg").attr("src",moduleAd.vfsId); 
			}
		} 
	};
	doAjax(url,params,callBack);
}
//查询平台动态公告信息
function queryModue104(subPageModuleList){
	if(subPageModuleList != undefined && subPageModuleList != null){
		$(subPageModuleList).each(function(i,d){
			querysubPageModuleList(d.moduleId,1);
		});
	}
}
//查询平台动态公告子楼层信息
function querysubPageModuleList(moduleId,pageNo){
	var url = basePath+'/homePage/queryPageInfoList';
	var params={
		moduleId:moduleId,
		pageNo:pageNo
	};
	var callBack =function(data){
		var html='';
		if(data.success){
			var pageModule = data.moduleRespDTO;
			html +='<dt>'+pageModule.moduleName+'</dt>';
			$(data.pageInfoList).each(function(i,d){
                html+='<dd><a href="'+basePath+'/homePage/newsDetail?infoId='+d.infoId+'" target="_blank">'+d.infoTitle+'</a> </dd>';
			});
		}
		switch (moduleId) {
		case 105://平台资讯
			$('#news_info').html(html);
			break;
		case 106://平台公告
			$('#news_adverse').html(html);
			break;
		case 107://平台活动
			$('#news_activity').html(html);
			break;
		case 108://常见问题
			$('#news_question').html(html);
			break;			
		default:
			break;
		}
	};
	doAjax(url,params,callBack);
}

//查询合作伙伴
function queryPartner109(moduleId){
	var url = basePath+'/homePage/queryPageModuleAd';
	var params={moduleId:moduleId};
	var callBack =function(data){
		var	html ='<div class="active item parter-sub">';
		if(data.success){
			$(data.moduleAdList).each(function(i,d){
 				html +='<a href="'+setLinkUrk(d.linkPage)+'"  target="_blank"><img src="'+d.vfsId+'" "> </a>';
 				if(parseInt(i+1)%5 == 0){
					if(parseInt(i+1)== data.moduleAdList.length){
						html +='</div>'; 
					}else{
						html +='</div><div class="item parter-sub">';
					}
				} 
			});
		}
		$('#partnertCarousel>div').html(html); 
		$('#partnertCarousel').carousel({interval: 5000});
	};
	doAjax(url,params,callBack);
}

