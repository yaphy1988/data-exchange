/**
A jQuery plugin for search hints

Author: Lorenzo Cioni - https://github.com/lorecioni
*/

(function($) {
	$.fn.autocomplete = function(params) {
		//Selections
		var currentSelection = -1;
		var currentProposals = [];
		
		//Default parameters
		params = $.extend({
			hints: [],
			placeholder: 'Search',
			width: 600,
			height: 44,
            maxlength:255,
			showButton: true,
			buttonText: 'Search',
			inputSet:null,
			onSubmit: function(text){},
			onBlur: function(){},
			onChange:null
		}, params);

		//Build messagess
		this.each(function() {
			//Container
			var searchContainer = $('<div></div>')
				.addClass('autocomplete-container')
				.css('height', params.height);
				
			//Text input		
			var input = $('<input type="text" id="searchTxt" autocomplete="off" name="query">')
				.attr('placeholder', params.placeholder)
				.attr('maxlength', params.maxlength)
				.addClass('autocomplete-input')
				.css({
					'width' : params.width,
					'height' : params.height
				});
			
			if(params.showButton){
				input.css('border-radius', '0');
			}

			//Proposals
			var proposals = $('<div></div>')
				.addClass('proposal-box')
				.css('width', params.width + 0)
				.css('top', input.height() + 0);
			var proposalList = $('<ul></ul>')
				.addClass('proposal-list');

			proposals.append(proposalList);
			input.keydown(function(e) {
				switch(e.which) {
					case 38: // Up arrow
					e.preventDefault();
					$('ul.proposal-list li').removeClass('selected');
					if((currentSelection - 1) >= 0){
						currentSelection--;
						$( "ul.proposal-list li:eq(" + currentSelection + ")" )
							.addClass('selected');
						/* zqr ������������ */
						if(params.inputSet){
							params.inputSet(input,currentProposals,currentSelection);
						}
					} else {
						currentSelection = -1;
					}
					break;
					case 40: // Down arrow
					e.preventDefault();
					if((currentSelection + 1) < currentProposals.length){
						$('ul.proposal-list li').removeClass('selected');
						currentSelection++;
						$( "ul.proposal-list li:eq(" + currentSelection + ")" )
							.addClass('selected');
						/* zqr ������������ */
						if(params.inputSet){
							params.inputSet(input,currentProposals,currentSelection);
						}
					}
					break;
					case 13: // Enter
						if(params.inputSet){
							params.inputSet(input,currentProposals,currentSelection);
						}else{
							if(currentSelection > -1){
								var text = $( "ul.proposal-list li:eq(" + currentSelection + ")" ).html();
								input.val(text);
							}
						}
						currentSelection = -1;
						proposalList.empty();
						params.onSubmit(input);
						break;
					case 27: // Esc button
						currentSelection = -1;
						proposalList.empty();
						input.val('');
						break;
				}
			});
			var prevVal='';
			input.bind("change paste keyup", function(e){
				if(e.which != 13 && e.which != 27 
						&& e.which != 38 && e.which != 40){				
					currentProposals = [];
					currentSelection = -1;
					if(prevVal==$(this).val()){
				    	return true;
					}
					prevVal=$(this).val();
					/*** zqr �޸� **/
					if(params.onChange){
						params.onChange(params,$(this),proposalList,currentProposals)
					}
					else if(input.val() != ''){
						proposalList.empty();
						var word = "^" + input.val() + ".*";
						for(var test in params.hints){
							if(params.hints[test].match(word)){
								currentProposals.push(params.hints[test]);
								var element = $('<li></li>')
									.html(params.hints[test])
									.addClass('proposal')
									.click(function(){
										input.val($(this).html());
										proposalList.empty();
										params.onSubmit(input.val());
									})
									.mouseenter(function() {
										$(this).addClass('selected');
									})
									.mouseleave(function() {
										$(this).removeClass('selected');
									});
							    	proposalList.append(element);
							}
						}

					}
				}
			});
			
			input.blur(function(e){
				currentSelection = -1;
				//proposalList.empty();
				params.onBlur();
			});
			
			searchContainer.append(input);
			searchContainer.append(proposals);		
			
			if(params.showButton){
				//Search button
				var button = $('<div></div>')
					.addClass('autocomplete-button')
					.html(params.buttonText)
					.css({
						'height': params.height + 0,
						'line-height': params.height + 'px'
					})
					.click(function(){
						proposalList.empty();
						/*
						* zhuqr�޸�
						* params.onSubmit(input.val());
						* */
						params.onSubmit(input);
					});
				searchContainer.append(button);	
			}
	
			$(this).append(searchContainer);	
			
			if(params.showButton){
				//Width fix
				searchContainer.css('width', params.width + button.width() + 60);
			}
		});

		return this;
	};

})(jQuery);