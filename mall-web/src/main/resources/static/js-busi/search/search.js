$(function(){
	//初始化查询商品列表
	var param = Search.generSearchParam();
	Search.gridGdsInfo(param);
	//初始化事件绑定信息
	Search.init();
});
var Search = {
		init : function(){
			//关闭选中的条件事件绑定
			$(".close").off().on('click',function(e){
				var $this = $(this);
				$this.parents("a").remove();
				var catId = $this.attr('id');
				$(".gl_group").find("a[catId="+catId+"]").parents(".gl_items").show();
				if($("#selectedCondition").children("a").length==0){
					$("#condictionFlag").hide();
				}
				//搜索商品列表
				var param = Search.generSearchParam();
				Search.gridGdsInfo(param);
				e.preventDefault();
			});
			
			//分类选择事件绑定
			$(".catSelecte").off().on('click',function(e){
				var $this = $(this);
				var html = "<a href='javascript:void(0)'><em>"+$this.attr('catPName')+":</em><span>"+$this.attr('catName')+"</span><i class='close' catPid="+$this.attr('catPid')+" id="+$this.attr('catId')+">×</i></a>";
				$("#selectedCondition").append(html);
				$this.parents(".gl_items").hide();
				$("#condictionFlag").show();
				//搜索商品列表
				var param = Search.generSearchParam();
				Search.gridGdsInfo(param);
				e.preventDefault();
			});
			
			//排序事件绑定
			$(".sortCondition").off().on('click',function(e){
				var $this = $(this);
				var $glyphicon = $(".glyphicon",$this);
				if($glyphicon.hasClass("glyphicon-arrow-down")){
					$glyphicon.removeClass("glyphicon-arrow-down");
					$glyphicon.addClass("glyphicon-arrow-up");
				}else if($glyphicon.hasClass("glyphicon-arrow-up")){
					$glyphicon.removeClass("glyphicon-arrow-up");
					$glyphicon.addClass("glyphicon-arrow-down");
				}else{
					$glyphicon.removeClass("glyphicon-arrow-up");
					$glyphicon.addClass("glyphicon-arrow-down");
				}
				$this.addClass('active');
				$this.siblings().removeClass('active');
				$this.siblings().each(function(){
					var _glyphicon = $(".glyphicon",$(this));
					_glyphicon.removeClass("glyphicon-arrow-down");
					_glyphicon.removeClass("glyphicon-arrow-up");
				});
				//搜索商品列表
				var param = Search.generSearchParam();
				Search.gridGdsInfo(param);
				e.preventDefault();
			});
			
			//分类更多事件绑定
			$(".btn_more").off().on('click',function(e){
				var $this = $(this);
				if($this.children('i').hasClass('glyphicon-menu-up')){
					$this.removeClass('selected');
					$this.parents(".gl_items").children(".bd").removeClass('selected');
					$this.children('font').text("更多");
					$this.children('i').removeClass('glyphicon-menu-up').addClass("glyphicon-menu-down");
				}else{
					$this.addClass('selected');
					$this.parents(".gl_items").children(".bd").addClass('selected');
					$this.children('font').text("收起");
					$this.children('i').removeClass('glyphicon-menu-down').addClass("glyphicon-menu-up");
				}
				e.preventDefault();
			});
		},
		/**
		 * 查询商品列表
		 */
		gridGdsInfo : function(param){
			$.ajax({
				url:WEB_ROOT+'/search/gridgdsinfo',
				cache:false,
				async:true,
				dataType:'html',
				data : param,
				success:function(data){
					$('#search_list').html(data);
					/**
					 * 分页组件
					 */
					$('#pagerId').pager({callback: function(index){
						var param = Search.generSearchParam();
						param.pageNo = index;
						Search.gridGdsInfo(param);
					}});
					var gdsCollect = $(".gdsCollection");
					if(gdsCollect.length >= 1){
						//判断商品是否已经收藏
						Search.whethercollect();
					}
				}
			});
		},
		/**
		 * 获取查询商品列表信息
		 */
		generSearchParam : function (){
			var param = {};
			if($("#catFirst").val()!=""){
				param.catFirst = $("#catFirst").val();
			}
			//分类
			var selectedCondition =  new Array();
			$(".close",$("#selectedCondition")).each(function(){
				var $this = $(this);
				selectedCondition.push($this.attr('id'));
				
			});
			param.selectedCondition = selectedCondition.join(",");
			//搜索关键词
			param.keyWord = $.trim($("#keyWord").val());
			//排序字段
			param.sortField = $(".gl_list .active").attr('sortField');
			//排序值。ASC 升序，DESC 降序
			var $glyphicon = $(".glyphicon",$(".sortCondition.active"));
			var sortValue = "DESC";
			if($glyphicon.hasClass("glyphicon-arrow-down")){
				sortValue = "DESC";
			}else if($glyphicon.hasClass("glyphicon-arrow-up")){
				sortValue = "ASC";
			}
			param.sortValue = sortValue;
			return param;
		},
		gdsCollection : function(obj,gdsId,skuId,catFirstId){
			var param = {
					gdsId : gdsId,
					catFirstId : catFirstId
			};
			if(skuId !="null" && skuId !=""){
				param.skuId = skuId;
			}
			
			$.ajax({
				url:WEB_ROOT+'/search/gdscollection',
				cache:false,
				async:true,
				dataType:'json',
				data : param,
				success:function(data){
					if(data.success ){
						var _obj = $(obj).children("i");
						if(data.obj=="add"){
							_obj.removeClass("icon-shoucang");
							_obj.addClass("icon-shoucang1")
						}else if(data.obj=="cancel"){
							_obj.removeClass("icon-shoucang1");
							_obj.addClass("icon-shoucang")
						}
					}
				}
			});
		},
		whethercollect : function(){
			var arr = new Array();
			$(".gdsCollection").each(function(){
				arr.push($(this).attr('gdsId'));
			});
			$.ajax({
				url:WEB_ROOT+'/search/whethercollect',
				cache:false,
				async:true,
				dataType:'json',
				data : {gdsIds:arr.toString()},
				success:function(data){
					if(data.success){
						var list = data.obj;
						if(list != null && list.length>=1){
							 $.each(list,function (i,collect) {
								 $(".gdsCollection").each(function(){
									if($(this).attr('gdsId')==collect.gdsId){
										var _obj = $(this);
										_obj.removeClass("icon-shoucang");
										_obj.addClass("icon-shoucang1")
									}
								});
							 });
						}
					}
				}
			});
		},
		addToCart : function(gdsId,skuId){
			var staff_id = "";
			if(staffInfoDTO != null)
			{
				staff_id = staffInfoDTO.staffId;
			}
			if(staff_id == null || staff_id =="")
			{
				WEB.msg.info("提示",'亲，请先登录哦，若无账户，请先注册。');
				return;
			}
			var url = WEB_ROOT+"/order/gdshopcart?gdsId="+gdsId+"&skuId="+skuId;
			window.open(url);
		}
};
function Map() {
    /** 存放键的数组(遍历用到) */
    this.keys = new Array();
    /** 存放数据 */
    this.data = new Object();
    
    /**
     * 放入一个键值对
     * @param {String} key
     * @param {Object} value
     */
    this.put = function(key, value) {
        if(this.data[key] == null){
            this.keys.push(key);
        }
        this.data[key] = value;
    };
    
    /**
     * 获取某键对应的值
     * @param {String} key
     * @return {Object} value
     */
    this.get = function(key) {
        return this.data[key];
    };
    
    /**
     * 删除一个键值对
     * @param {String} key
     */
    this.remove = function(key) {
        this.keys.remove(key);
        this.data[key] = null;
    };
    
    /**
     * 遍历Map,执行处理函数
     * 
     * @param {Function} 回调函数 function(key,value,index){..}
     */
    this.each = function(fn){
        if(typeof fn != 'function'){
            return;
        }
        var len = this.keys.length;
        for(var i=0;i<len;i++){
            var k = this.keys[i];
            fn(k,this.data[k],i);
        }
    };
    
    /**
     * 获取键值数组(类似Java的entrySet())
     * @return 键值对象{key,value}的数组
     */
    this.entrys = function() {
        var len = this.keys.length;
        var entrys = new Array(len);
        for (var i = 0; i < len; i++) {
            entrys[i] = {
                key : this.keys[i],
                value : this.data[i]
            };
        }
        return entrys;
    };
    
    /**
     * 判断Map是否为空
     */
    this.isEmpty = function() {
        return this.keys.length == 0;
    };
    
    /**
     * 获取键值对数量
     */
    this.size = function(){
        return this.keys.length;
    };
    
    /**
     * 重写toString 
     */
    this.toString = function(){
        var s = "{";
        for(var i=0;i<this.keys.length;i++,s+=','){
            var k = this.keys[i];
            s += k+"="+this.data[k];
        }
        s+="}";
        return s;
    };
}


function testMap(){
    var m = new Map();
    m.put('key1','Comtop');
    m.put('key2','南方电网');
    m.put('key3','景新花园');
    alert("init:"+m);
    
    m.put('key1','康拓普');
    alert("set key1:"+m);
    
    m.remove("key2");
    alert("remove key2: "+m);
    
    var s ="";
    m.each(function(key,value,index){
        s += index+":"+ key+"="+value+"\n";
    });
    alert(s);
}