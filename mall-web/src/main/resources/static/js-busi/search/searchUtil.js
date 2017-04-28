$(function(){
	if($('#siteSearch').size()>0){
        $(document).on('click.hideAutocomplete',function () {
            var event = event || window.event;
            var target = event.target || event.srcElement;
            if($(target).parents('.autocomplete-container').size()<=0 && !$(target).hasClass('autocomplete-container')){
                $('.autocomplete-container .proposal-list').empty();
            }
        });
        $('#siteSearch').autocomplete({
                //        hints: words,
                placeholder: 'Search',
                width: 500,
                height: 44,
                showButton: true,
                buttonText: '搜索',
                onSubmit:function(input){
                	location.href = WEB_ROOT+"/search?keyWord="+encodeURI(encodeURI(input.val()))+"&catFirst="+$("#catFirst").val();
                	if($("#catFirst").val()=="2"){
                		$.cookie('gjs_navigation', "/search/custom", {path : '/'});
                	}else if($("#catFirst").val()=="3"){
                		$.cookie('gjs_navigation', "/search/solution", {path : '/'});
                	}else{
                		$.cookie('gjs_navigation', "/search/api", {path : '/'});
                	}
                	
                },
                inputSet:function(input,currentProposals,currentSelection){
                    if(currentSelection>-1){
                        var curPro=currentProposals[currentSelection];
                        input.val(curPro.val);
                        input.attr('sub-id',curPro.id);
                    }

                },
                onChange:function(params,input,proposalList,currentProposals){
                	var keyWord = $.trim($(input).val());
            		if(keyWord==""){
            			$(".proposal-list").empty();
            			return;
            		}
            		var param = {keyWord:keyWord};
        			$.ajax({
        				url:WEB_ROOT+'/search/suggest',
        				async:true,
        				dataType: "json",
        				data : param,
        				success:function(data){
	            			if (data.success) {
	            				var list = data.obj;
	            				if(list == null || list.length==0){
	        						proposalList.empty();
	        					}else{
	                				for(var index in list){
	                					var v = list[index];
	    		                        currentProposals.push({id: v.name,val:v.name});
	    		                        var element = $('<li></li>')
	    		                            .addClass('proposal')
	    		                            .click(function(){
	                                        	input.val($(".search-val",$(this)).text());
	    		                                params.onSubmit(input);
	    		                            })
	    		                            .mouseenter(function() {
	    		                                $(this).addClass('selected');
	    		                            })
	    		                            .mouseleave(function() {
	    		                                $(this).removeClass('selected');
	    		                            });
	    		                        var html= "<span class='search-val'>"+v.name+"</span>" +
	    		                            "<span class='search-num'>搜索约"+ v.count +"个结果</span>";
	    		                        if(index>proposalList.find('li').size()-1){
	    		                            element.html(html);
	    		                            proposalList.append(element);
	    		                        }else{
	    		                            proposalList.find('li').eq(index).html(html);
	    		                        }
	    		                    }
	    		                    if(list.length<proposalList.size()){
	    		                        proposalList.find('li:gt('+(list.length-1)+')').remove();
	    		                    }
	        					}
	            			}
        				}
            		});
                }
            }
        );
        $('#searchTxt').val($("#keyWord").val());
    }
});