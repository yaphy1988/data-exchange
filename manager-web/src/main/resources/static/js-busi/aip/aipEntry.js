/**
 * Created by yx on 2017/5/9.
 */
$(function () {

})

function inParaAddRow(obj) {
    var html =  $(obj).parent().parent().prev().html();
    $(obj).parent().parent().before('<tr class="inParaTr">'+html+'</tr>');
}

function outParaAddRow(obj){
    var html =  $(obj).parent().parent().prev().html();
    $(obj).parent().parent().before('<tr class="outParaTr">'+html+'</tr>');
}

function delRow(obj){
    $(obj).parent().parent().parent().remove();
}