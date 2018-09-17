$(document).ready(function(){
	
	$('.nav-tabs li a').click(function(){
		var tab_id = $(this).attr('data-tab');
		alert(tab_id);
		$('ul.nav-tabs li').removeClass('active');
		$('.tab-content .tab-pane').removeClass('active');
		$('.tab-content .tab-pane').removeClass('show');

		$(this).addClass('active');
		$("#"+tab_id).addClass('active');
	})

})