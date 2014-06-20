signup(contextPath, validateForm, email, firstName, lastName)::="
<div class='container'>
	<div class='jumbotron'>
		
			$if(validateForm)$
				<div class='alert alert-danger'>$validateForm$</div>
			$endif$
			
			<form class='form-horizontal' role='form' method='post' action='$contextPath$signup'>
				<h1><center>Signup</center></h1>
				
				<div class='form-group'>
			    	<label for='inputEmail3' class='col-sm-2 control-label'>Email</label>
			    	<div class='col-sm-10'>
			    		<input type='email' class='form-control' id='inputEmail3' placeholder='Email' name='email' value='$email$'>
			   		</div>
			  	</div>
			  	
			  	<div class='form-group'>
			    	<label for='inputPassword' class='col-sm-2 control-label'>Password</label>
			    	<div class='col-sm-10'>
			    		<input type='password' class='form-control' id='inputPassword' placeholder='Password' name='password'>
			   		</div>
			  	</div>
			  	
			  	<div class='form-group'>
			    	<label for='inputFirtName' class='col-sm-2 control-label'>First Name</label>
			    	<div class='col-sm-10'>
			    		<input type='text' class='form-control' id='inputFirtName' placeholder='First Name' name='firstName' value='$firstName$'>
			   		</div>
			  	</div>
			  	
			  	<div class='form-group'>
			    	<label for='inputLastName' class='col-sm-2 control-label'>Last Name</label>
			    	<div class='col-sm-10'>
			    		<input type='text' class='form-control' id='inputLastName' placeholder='Last Name' name='lastName' value='$lastName$'>
			   		</div>
			  	</div>
			  	
			  	<div class='form-group'>
	    			<div class='col-sm-offset-2 col-sm-10'>
	      				<button type='submit' class='btn btn-default'>Sign Up</button>
	      				<button type='reset' class='btn btn-default'>Reset</button>
	    			</div>
	  			</div>
	  			
	  			<div class='form-group'>
	  				<div class='col-sm-offset-2 col-sm-10'>
	  					Already have account. <a href='$contextPath$login'>Log in here!</a>	
	  				</div>
  				</div>
			  	
			</form>
		
	</div>
</div>
";