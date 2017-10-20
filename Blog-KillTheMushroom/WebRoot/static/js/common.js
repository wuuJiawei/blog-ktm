$(function(){	

	$(window).scroll(function() {	
		console.log("scroll")
		if($(window).scrollTop() >= 100){
			$('.actGotop').fadeIn(300); 
		}else{    
			$('.actGotop').fadeOut(300);    
		}  
	});

	$('.actGotop').click(function(){
		$('html,body').animate({scrollTop: '0px'}, 800);
	});	

});