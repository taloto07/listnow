login(errorMessage, contextPath)::="
<div class='container'>
	<div class='jumbotron'>
		$if(errorMessage)$
			<div class='alert alert-danger'>$errorMessage$</div>
		$endif$
		
		<form class='form-horizontal' role='form' method='post' action='j_security_check'>
			<h1><center>Log In</center></h1>
			
			<div class='form-group'>
		    	<label for='inputEmail3' class='col-sm-2 control-label'>Email</label>
		    	<div class='col-sm-10'>
		    		<input type='email' class='form-control' id='inputEmail3' placeholder='Email' name='j_username'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputPassword' class='col-sm-2 control-label'>Password</label>
		    	<div class='col-sm-10'>
		    		<input type='password' class='form-control' id='inputPassword' placeholder='Password' name='j_password'>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
    			<div class='col-sm-offset-2 col-sm-10'>
      				<button type='submit' class='btn btn-default'>Log In</button>
      				<button type='reset' class='btn btn-default'>Reset</button>
    			</div>
  			</div>
  			
  			<div class='form-group'>
  				<div class='col-sm-offset-2 col-sm-10'>
  					Don't have account yet. <a href='$contextPath$signup'>sing up here!</a>	
  				</div>
  			</div>
		</form>
	</div>
</div>
";