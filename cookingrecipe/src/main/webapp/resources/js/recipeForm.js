$(document).ready(function(){
var i=1;
  $(".add-step").click(function(){
	  $(".steps").before(`<div class="step-card">
					  		<label for="step">
				  			<span>Step</span>
				  			<span>${i}</span>
				  		</label>
				    	<textarea type="text" class="form-control" placeholder="Input how to make" name="addstep"></textarea>
				  	</div>`);
		i++;
  });  
});
		