$(function(){
	$("#codigo").keyup(function(){
		var texto = $(this).val();
		
		$("#tablebody tr").css("display", "block");
		$("#tablebody tr").each(function(){
			if($(this).text().toUpperCase().indexOf(texto.toUpperCase()) < 0)
			   $(this).css("display", "none");
		});
	});
});
