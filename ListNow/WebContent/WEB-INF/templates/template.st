template(contextPath, body, title, user)::="
<!DOCTYPE html>
<html>
<head>
	<link href='$contextPath$files/bootstrap/css/bootstrap.min.css' rel='stylesheet' media='screen'>
	<link href='$contextPath$files/bootstrap/css/style.css' rel='stylesheet' media='screen'>
	<script src='http://code.jquery.com/jquery-1.10.2.min.js' type='text/javascript'></script>
	<script src='$contextPath$files/bootstrap/js/bootstramp.js' type='text/javascript'></script>

	<link href='$contextPath$files/non-responsive.css' rel='stylesheet'>
	<link href='$contextPath$files/style.css' rel='stylesheet'>
	<title>$title$</title>
</head>
<body>
	<div class='navbar navbar-default navbar-fixed-top' role='navigation'>
  		<div class='container'>
	    	<div class='navbar-header'>
	    		<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'>
	            	<span class='sr-only'>Toggle navigation</span>
	            	<span class='icon-bar'></span>
	            	<span class='icon-bar'></span>
	            	<span class='icon-bar'></span>
	          	</button>
	          	<a class='navbar-brand' href='$contextPath$'><span class='glyphicon glyphicon-home'></span> ListNow</a>
        	</div>
	        <div class='navbar-collapse collapse'>
	        	<ul class='nav navbar-nav'>
	            	<li><a href='#about'><span class='glyphicon glyphicon glyphicon-th-list'></span> Post</a></li>
	            	<li>
	            		<form method='post' action='$contextPath$search'><input type='text' class='form-control' id='search-main' placeholder='Search'></form>
	            	</li>
	          	</ul>
				
	          	<ul class='nav navbar-nav navbar-right'>
	            	<li>
	            		$if(user)$
	            			<a href='$contextPath$logout'>
	            				Welcome $user$
	            				<span class='glyphicon glyphicon glyphicon-user'></span> 
		            			Log out
		            		</a>
		            	$else$
		            		<a href='$contextPath$login'><span class='glyphicon glyphicon glyphicon-user'></span> 
		            			Log In
		            		</a>
		            	$endif$
	            	</li>
	          	</ul>
	        </div><!--/.nav-collapse -->
    	</div>
	</div>

	$body$

	<div id='footer'>
		Code Day 24-HRs coding challenge.
	</div>
</body>
</html>
";