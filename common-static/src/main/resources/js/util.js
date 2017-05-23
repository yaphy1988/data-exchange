/*
 * util.js depend jquery-1.7.2.js+
 * 
 * 2014.2.24 modify by yugn
 * 
 */
/* 常用工具类 */

var WEB = {
   /*  "WEBROOT" : GLOBAL.WEBROOT,
    "COMSHOP" : GLOBAL.COMSHOP,*/
    string : {}, // 与字符串相关的方法；
    math : {}, // 与数学相关的方法；
    array : {}, // 与数学相关的方法；
    check : {},
    cookie : {},
    date : {},
    file : {},
    // 与弹出框相关的一下方法；
    win : {},
    // 与首页的页签相关的方法；包括在框架层面增加 一个页签之类的；
    tab : {},
    // 与信息展示相关的，包括信息框弹出之类，确认框；
    msg : {},
    //javascript控制台调试方法
    debug:{},
    version : "1.0"
    
};
WEB.$d = function(id) {
    var obj = document.getElementById(id);
    if (obj != null) {
        return obj;
    } else {
        try {
            obj = top.window.frames['frm_top'].document.getElementById(id);
        } catch (e) {
            obj = null;
        }
        if (obj != null) {
            return obj;
        }
    }
    return null;
};
//只能输入20位以内的数字
//使用 ：onkeyup='checkAmout(this)'
var record={ num:"" } ;
function checkAmout(obj){
	var priceReg=/^\d{1,8}?$/; 
	if(obj.value != "" && priceReg.test(obj.value)){ 
		record.num = obj.value; 
	}else{ 
		if(obj.value != ""){ 
			obj.value = record.num; 
		} 
	} 
}
WEB.checkNum = function(obj) {
    if (event.keyCode == 13 || event.keyCode == 8) {
        return;
    }
    if ((event.keyCode < 44 || event.keyCode > 58)) {
        alert("\u8bf7\u8f93\u5165\u6570\u5b57");
        obj.focus();
        event.returnValue = false;
    }
};
//校验是否是价格
WEB.checkNum.isPirce = function (obj){
	if(obj == null || obj == ""){
		return false;
	}
	obj = obj.replace(/(^\s*)|(\s*$)/g, "");;
    var p =/^[1-9](\d+(\.\d{1,2})?)?$/; 
    var p1=/^[0-9](\.\d{1,2})?$/;
    return p.test(obj) || p1.test(obj);
};

//判断输入的值的长度
WEB.fucCheckLength =function (strTemp) { 
	var i,sum;
	sum=0;
	for(i=0;i<strTemp.length;i++) { 
		if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=255)) {
			sum=sum+1;
		}else {
			sum=sum+2;
		}
	}
	return sum; 
};
WEB.string.pad = function(str, length, flag, pos) {// 格式化字符串为length长度,不足用flag补,pos{"r":右补,其它:左补}
    str = str + "";
    var len = length - (str.length);
    var p = "";
    for (var i = 1; i <= len; i++) {
        p += flag;
    }
    if (pos == "r") {
        str = str + "" + p;
    } else {
        str = p + "" + str;
    }
    return str;
};

WEB.string.lpad = function(str, len) {// 左补零
    var t = "0000000000000000" + str;
    return t.substring(t.length - len, t.length);
};

WEB.string.rpad = function(str, len) {// 右补零
    var t = str + "0000000000000000";
    return t.substring(0, len);
};

WEB.string.replaceAll = function(str, f, t) {
    return str.replace(new RegExp(f, "gm"), t);
};

WEB.string.placeholder = function() {
    var ary = [];
    for (i = 1; i < arguments.length; i++) {
        ary.push(arguments[i]);
    }
    return arguments[0].replace(/\{(\d+)\}/g, function(m, i) {
                return ary[i];
            });
};

WEB.string.htmlEscape = function(str) {
    return str.replace(/<[^>].*?>/g, "");
};

WEB.string.nvl = function(str) {
    return (str == null) ? "" : str;
};
/**
 * 如果 str 为空，那么使用 de 返回；
 * 
 * @param {}
 *            str
 * @param {}
 *            de
 * @return {}
 */
WEB.string.getValue = function(str, de) {
    str = WEB.string.nvl(str);
    return (str == "") ? de : str;
};

/**
 * 根据参数不同而不同的数字判断
 * @param {} object input输入域
 * @param {} digits 表示小数点后的位数
 */
WEB.string.onlyNumber = function(object, digits) {
	if(digits){
		if(isNaN(digits)){
			digits = 0;
		}else{
			digits = parseInt(digits);
		}
	}
	if(digits == undefined){
		//如果没有输入length，表示没有对小数位数的要求
		if((!(/^\d+(\.\d*)?$/.test(object.value)) || (/^0[^\.]/.test(object.value))) && (object.value.length >= 1)){
			object.value=object.value.substr(0, object.value.length-1)
				WEB.string.onlyNumber(object, digits);

		}
	}else if(digits == 0){
		//如果输入length为0，表示要求不能有小数
		if((!(/^\d+$/.test(object.value)) || (/^0[^\.]/.test(object.value))) && (object.value.length >= 1)){
			object.value=object.value.substr(0, object.value.length-1)
				WEB.string.onlyNumber(object,digits);
		}
	}else{
		//如果输入为其他数字，则表示有该数字的小数位
		var reg = new RegExp("^\\d+(\\.\\d{0,"+digits+"})?$");
		if((!(reg.test(object.value)) || (/^0[^\.]/.test(object.value))) && (object.value.length >= 1)){
			object.value=object.value.substr(0, object.value.length-1)
				WEB.string.onlyNumber(object,digits);
		}
	}
}

// math
WEB.math.round = function(number, X) {// 4舍5入
    // rounds number to X decimal places, defaults to 2
    X = (!X ? 2 : X);
    return Math.round(number * Math.pow(10, X)) / Math.pow(10, X);
};

WEB.math.random = function(first, last) {// 产生 first~last范围内的随时数
    var max = last - first + 1;
    return Math.floor(Math.random() * max + first);
};

/**
 * 判断 substr 是否在 str 串内；str 使用 , 分割；
 * 
 * @param {}
 *            str
 * @param {}
 *            substr
 * @return {Boolean}
 */
WEB.array.isSelected = function(str, substr) {
    var strArr = str.split(",");
    /*
     * for (var i = 0; i < strArr.length; i++) { if (strArr[i] == substr) {
     * return true; } } return false;
     */
    // 直接调用 jquery 提供的 inArray 方法；
    var index = $.inArray(substr, strArr);
    if (index < 0) {
        return false;
    } else {
        return true;
    }
};

/**
 * 判断 name = cname 的复选框是否选中；
 * 
 * @param {}
 *            name
 * @return {Boolean}
 */
WEB.check.isChecked = function(cname) {
    var d = document.getElementsByName(cname);
    for (var i = 0; i < d.length; i++) {
        if (d[i].checked)
            return true;
    }
    return false;
};

/**
 * 反选；将 name = cname的 checkbox 全部反选；
 * 
 * @param {}
 *            cname
 */
WEB.check.checkRev = function(cname) {
    var obj = document.getElementsByName(cname);
    var len = obj.length;
    for (var i = 0; i < len; i++) {
        if (obj[i].checked) {
            obj[i].checked = false;
        } else {
            obj[i].checked = true;
        }
    }
};

/**
 * 全不选；将名称为 cname的checkbox 全部不选中；
 * 
 * @param {}
 *            cname
 */
WEB.check.checkNone = function(cname) {
    var obj = document.getElementsByName(cname);
    var len = obj.length;
    for (var i = 0; i < len; i++) {
        obj[i].checked = false;
    }
};

/**
 * 全选；将名称为：cname的checkbox 全部选中；
 * 
 * @param {}
 *            cname
 */
WEB.check.checkAll = function(cname) {
    var obj = document.getElementsByName(cname);
    var len = obj.length;
    for (var i = 0; i < len; i++) {
        obj[i].checked = true;
    }
};

WEB.check.checkToggle = function(cname) {
    var obj = document.getElementsByName(cname);
    var len = obj.length;
    var o = event.srcElement;
    for (var i = 0; i < len; i++) {
        obj[i].checked = o.checked;
    }
};


//验证手机号码
//2015.3.20 add by chenjy
WEB.check.isMobile=function(mobile){ 

	    var mobileReg =/^1[34578]\d{9}$/; 
	    if(!(mobileReg.test(mobile)))
	    { 
	        return false; 
	    } 
	    else
	    {return true;}  
};

//电话号码校验
//2015.3.20 add by chenjy
WEB.check.isPhone=function(phone){ 
	    var fixedReg =/^(0[0-9]{2,3}\-?)?([2-9][0-9]{6,7})$/; 
	    var mobileReg=/^1[34578]\d{9}$/;	    
	    if((fixedReg.test(phone))||(mobileReg.test(phone))){ 
	        return true; 
	    } 
	    else{
	    	return false;
	    }    
};

//邮箱校验
WEB.check.isEmail = function(email){
	var szReg=/^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/;
	var bChk=szReg.test(email);
	return bChk;

/*	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!(emailReg.test(email))){
		return false;
	}else{
		return true;
	}*/
}

WEB.cookie.getCookie = function(l) {
    var i = "", I = l + "=";
    if (document.cookie.length > 0) {
        var offset = document.cookie.indexOf(I);
        if (offset != -1) {
            offset += I.length;
            var end = document.cookie.indexOf(";", offset);
            if (end == -1)
                end = document.cookie.length;
            i = unescape(document.cookie.substring(offset, end))
        }
    };
    return i
};

WEB.cookie.setCookie = function(O, o, l, I) {
    var i = "", c = "";
    if (l != null) {
        i = new Date((new Date).getTime() + l * 3600000);
        i = "; expires=" + i.toGMTString();
    }
    if (I != null) {
        c = ";domain=" + I;
    }
    document.cookie = O + "=" + escape(o) + i + c
};

// date
WEB.date.today = function() {
    var d = new Date();
    var y = d.getFullYear();
    var m = d.getMonth() + 1;
    m = (m >= 10) ? m : "0" + m;
    var dy = d.getDate();
    dy = (dy >= 10) ? dy : "0" + dy;
    var s = y + "-" + m + "-" + dy;
    return s;
};

WEB.date.now = function() {
    var d = new Date();
    var t = WEB.today()
    var h = d.getHours();
    h = (h >= 10) ? h : "0" + h;
    var m = d.getMinutes();
    m = (m >= 10) ? m : "0" + m;
    var s = d.getSeconds();
    s = (s >= 10) ? s : "0" + s;
    return t + " " + h + ":" + m + ":" + s;
};

/**
 * 获取上个月的最后一日的日期
 */
WEB.date.lastMonthDate = function () {
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth();		//不+1代表上个月
	if (month <= 0) {
		month = 12;
		year--;
	} 
	var date = WEB.date.lastDay(year, month);
	if (month < 10) {
	    month= "0" +month;
	}
	if (date < 10) {
	    date= "0" + date;
	}
	return year + "-" + month + "-" + date;	//取上个月最后一天
};
/**
 * 获取当前月的第一天
 */
WEB.date.MonthfirstDate = function () {
	var d = new Date();
	var lastYear = d.getFullYear();
	var vMon = d.getMonth() + 1; 
	// alert((vMon+'').length);
	// 如果月份是一位数，前面补0
	if ((vMon + '').length == 1) {
		vMon = "0" + vMon;
	}
	var firstDate = "01";
	var lastnow = lastYear + "-" + vMon + "-" + firstDate;
    return lastnow;
};


/**
 * 获取上个月的当前日期
 */
WEB.date.lastMonthCurrDate = function() {
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth(); // 不+1代表上个月
	if (month <= 0) {
		month = 12;
		year--;
	}
	if (month < 10) {
		month = "0" + month;
	}
	// 如果日期是05,07,10,12月月末(即05/07/10/12-31),则上月日期取04/06/09/11-30
	// 如果是闰年，日期 >03-29, 则上月日期取02-29, 非闰年上月日期取02-28, 其他情况取当前日期
	var date = d.getDate();
	if ("04" == month || "06" == month || "09" == month || "11" == month) {
		if ("31" == date) {
			date = "30";
		}
	} else if ("02" == month) {
		if(date > 28){
			if (WEB.date.isLeapYear()) {
				date = "29"; // 闰年
			} else {
				date = "28"; // 非闰年
			}
		}
	}
	if (date < 10) {
		date = "0" + date;
	}
	return year + "-" + month + "-" + date; // 取上月当前日期
};

/**
 * 获取上一年的当前日期
 */
WEB.date.lastYearCurrDate = function() {
	var d = new Date();
	var nowYear = d.getFullYear();
	var lastYear = d.getFullYear() - 1;
	
	var lastMon = d.getMonth() + 1 ;
	
	var nowDay = d.getDate();
	var LastDay = d.getDate();
	
	if (lastMon < 10) {
		lastMon = "0" + lastMon;
	}
	
	if (LastDay < 10) {
		LastDay = "0" + LastDay;
	}
	
	//判断当前年份是否是闰年，并且天数等于29天时候，去年的二月份才会取28天。
	if ((nowYear % 4 == 0 && nowYear % 100 != 0) || (nowYear % 400 == 0)) {
		if(nowDay == "29" && lastMon == "02"){
			LastDay = 28;
		}
	}
	
	return lastYear + "-" + lastMon + "-" + LastDay;
};


/**
 * 判断闰年, 年份能被4整除同时不能被100整除，或者能被400整除则为闰年
 */
WEB.date.isLeapYear = function() {
	var d = new Date();
	var year = d.getFullYear();
	if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
		return true;
	}
	return false;
};

/**
 * 获取与某年月相差number月份的第一天的日期，如与2014-03相差-3月的日期为2013-12-01
 */
WEB.date.differMonthDate = function (t, number) {
	var tarr = t.split('-');
	
    var year = tarr[0];             //获取当前日期的年
    var month = tarr[1];            //获取当前日期的月
    
	year = parseInt(year);
	month = parseInt(month) + number; // 当前月加上相差月份
	if (month <= 0) { // 月份少于0
		year--;
		month += 12;
	} else if (month > 12) { // 月份大于12
		year++;
		month -= 12;
	}
	if (month < 10) {
		month = "0" + month;
	}
	return year + '-' + month + "-01"; //取上个月头一天2009-08-01
};
/**
 * 获取与某年月相差number月份的当前日期，如与2014-03-01相差6月的日期为2014-09-01
 */
WEB.date.differMonthDateToday = function(t, number) {
	var tarr = t.split('-');
	
    var year = tarr[0];             //获取当前日期的年
    var month = tarr[1];            //获取当前日期的月
    var day = tarr[2]; 				 //获取当前日期的日
	year = Number(year);
	month = Number(month) + number; // 当前月加上相差月份
	if (month <= 0) { // 月份少于0
		year--;
		month += 12;
	} else if (month > 12) { // 月份大于12
		year++;
		month -= 12;
	}
	if (month < 10) {
		month = "0" + month;
	}
	return year + '-' + month + "-"+day; //取相差number个月的当前日期
};
WEB.date.lastDay = function(year, month){   
	var new_year = year;    	//取当前的年份   
	var new_month = month++;	//取下一个月的第一天，方便计算（最后一天不固定）   
	if(month > 12) {            //如果当前大于12月，则年份转到下一年   
		new_month -= 12;        //月份减   
		new_year++;            	//年份增   
	}   
	var newnew_date = new Date(new_year, new_month, 1); //取当年当月中的第一天   
	return (new Date(newnew_date.getTime() - 1000*60*60*24)).getDate();//获取当月最后一天日期   
};


/**
 * 计算当前日期加上天数后的日期
 * 例：2015-06-17 + 5天 后的值为 2015-06-22
 * @author liangbq 
 */
WEB.date.AddDaysOfDate = function(daysToAdd){
	var addDaysResult = "";
	var now = new Date();
	var newdate = new Date();
	var newtimems = newdate.getTime()+(daysToAdd*24*60*60*1000);
	newdate.setTime(newtimems);
 
    var year = newdate.getFullYear();  
    var month = newdate.getMonth() + 1;  
    var date = newdate.getDate();
   
    if (month < 10) month = "0" + month;  
    if (date < 10) date = "0" + date;  
	
    return year + "-" + month + "-" + date;
}

/**
 * 根据年、月获取该月最后一天的日期
 * @param
 * 参数： year 年 ， month 月
 * @author liangbq
 */
WEB.date.getLastDayByYearMonth = function(year, month) {
	month = parseInt(month,10);
    var temp = new Date(year,month,1);  
    var realTemp = new Date(temp.getTime()-1000*60*60*24);
    return realTemp;
}

/**
 * 根据年、月获取该月第一天的日期
 * @param
 * 参数： year 年 ， month 月
 * 详解：
 * 		JS里 面的new Date("xxxx/xx/xx")这个日期的构造方法有一个妙处，
 * 		当你传入的是"xxxx/xx/0"（0号）的话，得到的日期是"xx"月的前一个 月的最后一天（"xx"月的最大取值是69，题外话），
 *		当你传入的是"xxxx/xx/1"（1号）的话，得到的日期是"xx"月的后一个 月的第一天
 * @author liangbq
 */
WEB.date.getFirstDayByYearMonth = function(year, month) {
	month = parseInt(month,10);
    var temp = new Date(year + "/" + month + "/1");
    return temp;
}

/**
 * 根据年、月获取该月最大天数
 * @param
 * 参数： year 年 ， month 月
 * @author liangbq
 */
WEB.date.getMaxDayByYearMonth = function(year, month) {
	month = parseInt(month,10) + 1;
    var temp = new Date(year + "/" + month + "/0");
    return temp.getDate();
}

/**
 * //对Date的扩展，将 Date 转化为指定格式的String
 * //月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
 * //年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
 * //例子： 
 * //(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
 * //(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
 */
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	 for (var k in o)
		 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}


// file
WEB.file.fileName = function(dir) {
    return dir.substring(dir.lastIndexOf("\\") + 1, dir.length);
};

WEB.file.fileExt = function(dir) {
    return dir.substring(dir.lastIndexOf(".") + 1, dir.length);
};

WEB.file.fileShortName = function(dir) {
    return dir.substring(dir.lastIndexOf("\\") + 1, dir.lastIndexOf("."));
};

//对年进行加或者减操作获取某年的年月日，flag表示加减操作，number表示加减的维度
WEB.date.dateAddOrSub =function(flag,number){
	var date = new Date();
	if(flag == "+"){
          date.setFullYear(date.getFullYear()+number);  
    }else{  
          date.setFullYear(date.getFullYear()-number);  
    }
    return date;
	
};
//格式化年月日
WEB.date.formatDate =function(date){ 
	var year = date.getFullYear();       //年  
	var month = date.getMonth() + 1;     //月  
	if((month+'').length ==1){
		month="0"+month;
	}
	var day = date.getDate();  //日  
	if((day+'').length ==1){
		day="0"+day;
	}
	return year+"-"+month+"-"+day;  
};


/*
 * 提示信息 显示提示信息,n秒后隐藏
 */
WEB.alt = function(id, str, n) {
    n = n || 5000;
    $('#' + id).fadeIn(500, function() {
                $(this).html(str);
                $(this).fadeOut(n, function() {
                            $(this).html("");
                        });
            });
}

/**
 * 把json解析为url参数 o:jsonObject pre:之前参数
 */
WEB.json2Param = function(o, pre) {
    var value, buf = [], key, e = encodeURIComponent;
    for (key in o) {
        value = (o[key] == undefined) ? "" : e(o[key]);
        buf.push("&", e(key), "=", value);
    }
    if (!pre) {
        buf.shift();
        pre = "";
    }
    return pre + buf.join('');
};

/**
 * 把url参数解析为json string: url overwrite:
 */
WEB.param2Json = function(string, overwrite) {
    var obj = {}, pairs = string.split('&'), d = decodeURIComponent, name, value;
    $.each(pairs, function(i, pair) {
                pair = pair.split('=');
                name = d(pair[0]);
                value = d(pair[1]);
                obj[name] = overwrite || !obj[name] ? value : [].concat(obj[name]).concat(value);
            });
    return obj;
};

/*******************************************************************************
 * 消息弹出框相关；
 ******************************************************************************/
WEB.msg.html = '<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">'
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
             + '</div><!-- /.modal -->';
    /**
     * 弹出普通信息窗口，出现信息的图标
     * 
     * @param {} title 标题
     * @param {} message 信息内容
     * @param {} callback 点击按钮之后的回调函数；
     * @param {} closedable 是否展示关闭图标（在右上角）
     */
WEB.msg.info = function(title, message, callback, closedable) {
	
	$.messagerPlugin.show({
		buttons:['ok'],  //操作按钮
		title : title ,  // 标题
		message : message,  // 消息
		okCallBack : callback,  // 确定按钮的回调函数
		closedable : closedable,  //关闭按钮
		msgType : 'info'
	});

	/*var modalId = "msgAlertModelId";
	if($("body > #"+modalId).length>0){
		$("body > #"+modalId).remove();
	}
	var $html = $(WEB.msg.html);
	$html.attr({"id":modalId});
	//添加按钮；
	$html.find(".modal-footer").append( '      <button type="button" class="btn btn-sm modal-ok" data-dismiss="modal">确认</button>');
	//添加消息；
	$html.find(".modal-body").append('<p>'+message+'</p>');
	//添加标题；
	$html.find(".modal-title").append(title);
	
	if(closedable){
		$html.find(":button.close").show();
	}
	  $html.appendTo($("body"));
	  $("#"+modalId).delegate(":button.modal-ok","click",function(){})
      $("#"+modalId).on("hidden.bs.modal",function(){
	    if ($.isFunction(callback)) {
            callback.call();
        }
	  }).modal({
	    "backdrop":'static',
	    "keyboard":false
	  });
      $html = null;*/
    },
    /**
     * 弹出警告信息窗口，出现警告的图标
     * 
     * @param {} title 标题
     * @param {} message 信息内容
     * @param {} callback 点击按钮之后的回调函数；
     * @param {} closedable 是否展示关闭图标（在右上角）
     */
    WEB.msg.warn = function(title, message, callback, closedable) {
    	$.messagerPlugin.show({
    		buttons:['ok'],  //操作按钮
    		title : title ,  // 标题
    		message : message,  // 消息
    		okCallBack : callback,  // 确定按钮的回调函数
    		closedable : closedable,  //关闭按钮
    		msgType : 'warn'
    	});
    	
       /* $.messager.defaults = {
            ok : "确定",
            cancel : "取消"
        };
        $.messager.alert(title, message, "warning", function() {
                    if ($.isFunction(callback)) {
                        callback.call();
                    }
                });*/
    	/*var modalId = "msgWarnModelId";
    	if($("body > #"+modalId).length>0){
    		$("body > #"+modalId).remove();
    	}
    	var $html = $(WEB.msg.html);
    	$html.attr({"id":modalId});
    	//添加按钮；
    	$html.find(".modal-footer").append( '      <button type="button" class="btn btn-sm modal-ok" data-dismiss="modal">确认</button>');
    	//添加消息；
    	$html.find(".modal-body").append('<p>'+message+'</p>');
    	//添加标题；
    	$html.find(".modal-title").append(title);
    	
    	if(closedable){
    		$html.find(":button.close").show();
    	}
    	  $html.appendTo($("body"));
          $("#"+modalId).on("hidden.bs.modal",function(){
    	    if ($.isFunction(callback)) {
                callback.call();
            }
    	  }).modal({
    	    "backdrop":'static',
    	    "keyboard":false
    	  });
          $html = null;*/
    },

    /**
     * 弹出错误窗口，出现错误的图标
     * 
     * @param {} title 标题
     * @param {} message 信息内容
     * @param {} callback 点击按钮之后的回调函数；
     */
    WEB.msg.error = function(title, message, callback, closedable) {
        /*$.messager.defaults = {
            ok : "确定",
            cancel : "取消"
        };
        $.messager.alert(title, message, "error", function() {
                    if ($.isFunction(callback)) {
                        callback.call();
                    }
                });*/
    	$.messagerPlugin.show({
    		buttons:['ok'],  //操作按钮
    		title : title ,  // 标题
    		message : message,  // 消息
    		okCallBack : callback,  // 确定按钮的回调函数
    		closedable : closedable,  //关闭按钮
    		msgType : 'error'
    	});
    },
    /**
     * 确认窗口
     * 
     * @param {} title 标题
     * @param {} message 信息内容
     * @param {} okfun 点击 ok 按钮 所触发的操作
     * @param {} cancelfun 点击 cancel 按钮 所触发的操作
     */
    WEB.msg.confirm = function(title, message, okfun, cancelfun, closedable) {
    	
    	$.messagerPlugin.show({
    		buttons:['ok','cancel'],  //操作按钮
    		title : title ,  // 标题
    		message : message,  // 消息
    		okCallBack : okfun,  // 确定按钮的回调函数
    		cancelCallBack : cancelfun, //取消按钮的回调函数
    		closedable : closedable,  //关闭按钮
    		msgType : 'error'
    	});
    	
        /*$.messager.defaults = {
            ok : "确定",
            cancel : "取消"
        };
        $.messager.confirm(title, message, function(r) {
                    if (r) {// r 返回 true 表示，点击的是确定按钮；返回 false 表示，点击的是 取消按钮；
                        if ($.isFunction(okfun)) {
                            okfun.call();
                        }
                    } else {
                        if ($.isFunction(cancelfun)) {
                            cancelfun.call();
                        }
                    }

                });*/
    },
    
    /**
     * 弹出输入框，类似与 javascript 提供的 prompt 的方法；
     * @param {} title  弹出窗口的标题
     * @param {} message 弹出窗口展示的信息
     * @param {} callback  点击按钮的回调函数，会传入输入的数据作为返回参数；
     */
    /*WEB.msg.prompt = function(title,message,callback){
        $.messager.prompt(title, message, function (data) {
            alert(data);
            if($.isFunction(callback)){
               callback.call(this,data);
            }
        });
    }*/

/*WEB.addLog = function(s) {
    WEB.$d("log").innerHTML += (s + "<br/>");
    var h = WEB.$d("log").offsetHeight;
    WEB.$d("wlog").scrollTop = h;
};

WEB.clearLog = function() {
    WEB.$d("log").innerHTML = "";
};*/

 
/**JS控制台调试,调试语句不必删除*/
WEB.debug = {
    IS_LOG_METHOD_ENABLED : true,
    IS_LOG_ERROR_METHOD_ENABLED : true,
	/**
	 * 输出调试日志.目前：chrome、firefox、IE8以上JavaScript控制台可见
     */ 
    log:function(obj){
    	if(WEB.debug.IS_LOG_METHOD_ENABLED && window.console && window.console.log ){
			if(window.console.dirxml){
				 window.console.dirxml(obj);
			}else if(window.console.dir){
				 window.console.dir(obj);
			}else{
				 window.console.log(obj);
			}
		}
    },
    /**
	 * 输出错误日志.目前：chrome、firefox、IE8以上JavaScript控制台可见
     */ 
    logError:function(obj){
		if(WEB.debug.IS_LOG_ERROR_METHOD_ENABLED && window.console){
			if(window.console.error){
				window.console.error(obj);
			}else {
				WEB.debug.log("Error:");
				WEB.debug.log(obj);
			}
		}
	}
};
    
/** 删除数组对象中某个元素,jquery有现成的 */
Array.prototype.remove = function(n) {
    // n表示第几项，从0开始算起。
    // prototype为对象原型，注意这里为对象增加自定义方法的方法。
	if(this instanceof Array){
		var len = this.length;
	    if (n < 0 || n > len) { // 如果n<0或者n>数组长度时，则不进行任何操作。
	        return this;
	    } else {
	        if (n == 0) {
	            this.shift();
	            return this;
	        } // 删除第一个元素
	        if (n == len - 1) {
	            this.pop();
	            return this;
	        } // 删除最后一个元素
	        return this.slice(0, n).concat(this.slice(n + 1, this.length));
	    }
	}
    
};
/**根据传入的渠道编码判断渠道类型**/
var StaffUtil = {
	//是否是联通人员
	isUnicomPerson : function(staffType){
		if(staffType=='10'){
			return true;
		}
		return false;
	},
	//是否是供货商
	isSupplier : function(staffType){
		if(staffType=='20'){
			return true;
		}
	return false;
	},
	//是否是渠道商
	isChannel : function(staffType){
		if(staffType=='30'||staffType=='40'||staffType=='50'){
			return true;
		}
		return false;
	},
	//是否为全国账号
	isLevelCountry : function(staffLevel){
		if(staffLevel=='01'){
			return true;
		}
		return false;
	},
	//是否为省级账号
	isLevelCountry : function(staffLevel){
		if(staffLevel=='02'){
			return true;
		}
		return false;
	}
};

// .caption自动添加开关功能
$(document).ready(function() {
            // .caption添加开关
            $(".caption").prepend('<span class="icon-switch icon-plus"></span>').end()
            $(".caption .icon-switch").click(function() {
                        $(this).toggleClass("icon-minu");
                        var oid = $(this).attr("id");
                        if (oid == "") {
                            $(this).parents(".caption:first").next().toggle();
                            // $(this).parents(".caption:first").next().slideToggle(300);
                        } else {
                            $("[id^='" + oid + "_']").toggle();
                            // $("[id^='"+oid+"_']").slideToggle(300);
                        }
                    });
        });

// legend.fswitch自动添加开关功能
$(document).ready(function() {
            $("legend.fswitch").css({
                        "cursor" : "pointer"
                    });
            $("legend.fswitch").prepend('<span class="icon-switch icon-plus">&nbsp;&nbsp;&nbsp;&nbsp;</span>').end();
            $("legend.fswitch").click(function() {
                        $(this).next().toggle();
                        $(this).children("span").toggleClass("icon-minu");
                    });
        });
