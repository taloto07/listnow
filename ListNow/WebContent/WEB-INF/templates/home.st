home(contextPath, cities)::="
<!DOCTYPE html>
<html>
<head>
	<link href='$contextPath$files/bootstrap/css/bootstrap.min.css' rel='stylesheet' media='screen'>
	<link href='$contextPath$files/bootstrap/css/style.css' rel='stylesheet' media='screen'>
	<script src='http://code.jquery.com/jquery-1.10.2.min.js' type='text/javascript'></script>
	<script src='$contextPath$files/bootstrap/js/bootstramp.js' type='text/javascript'></script>

	<link href='$contextPath$files/non-responsive.css' rel='stylesheet'>
	<link href='$contextPath$files/index.css' rel='stylesheet'>
	<title>Home</title>

</head>
	<body>
		<div class='float-header'>
			This project is still in its early beta phase. Let us know what you think.
		</div>

		<div class='index-search'>
			<div class='container'>
				<div class='col-sm-10 col-sm-offset-1 index-search-wrapper'>
					<center>
						<h1>Deal locally with Listnow</h1>
					</center>
					<form role='form' action='$contextPath$search' method='post' class='form-horizontal'>
						<div class='form-group'>
							<div class='col-sm-8'>
					  			<input type='text' class='form-control' placeholder='Search' name='searchKey'>
					  		</div>
					  		<div class='col-sm-4 '>
							  	<select class='form-control' name='city'>
							  		<option value=''>City</option>
							  		$cities:{n|
							  			<option value='$n.zipcode$'>$n.name$</option>
							  		}$
						  		</select>
						  	</div>
					  	</div>
					  <div class='btn-group col-sm-4 col-sm-offset-4'>
						  <button type='submit' class='btn btn-primary'>Searching</button>
						  <button type='submit' class='btn btn-danger'>Surprise Me</button>
					  </div>
					</form>
				</div>
			</div>
		</div>
		
		<div class='goal'>
			<div class='container'>
				<div class='page-header'><center><h2>How it works.</h2></div></center>
				<p class='col-sm-10 col-sm-offset-1'>
					We are building a community which help our fellows students exchanging, buying, 
					and selling essential products and services in order to help save some money.
				</p>
				<div class='col-sm-4'>
			    	<div class='thumbnail'>
			    		<img src='$contextPath$files/img/tablet.jpg' width='300px'>
			    		<center><h3>Tablets</h3></center>
			    	</div>
		   		 </div>
		   		 <div class='col-sm-4'>
			    	<div class='thumbnail'>
			    		<img src='$contextPath$files/img/book.jpg' width='300px'>
			    		<center><h3>Textbooks</h3></center>
			    	</div>
		   		 </div>
		   		 <div class='col-sm-4'>
			    	<div class='thumbnail'>
			    		<img src='$contextPath$files/img/laptop.jpg' width='300px'>
			    		<center><h3>Laptops</h3></center>
			    	</div>
		   		 </div>

		   		 <div class='col-sm-4'>
			    	<div class='thumbnail'>
			    		<img src='$contextPath$files/img/apartment.jpg' width='300px'>
			    		<center><h3>Apartments</h3></center>
			    	</div>
		   		 </div>

		   		 <div class='col-sm-4'>
			    	<div class='thumbnail'>
			    		<img src='$contextPath$files/img/phone.jpg' width='300px'>
			    		<center><h3>Phones</h3></center>
			    	</div>
		   		 </div>
		   		 <div class='col-sm-4'>
			    	<div class='thumbnail'>
			    		<img src='$contextPath$files/img/tutor.jpg' width='300px'>
			    		<center><h3>Tutors</h3></center>
			    	</div>
		   		 </div>
			</div>
		</div>

		<div class='momo'>
			<div class='container'>
				<center><h2>Do not take our words for it.</h2></center>
				<center>
					<p>
						Browse around and experience our community today.
					</p>
				</center>
			</div>
		</div>

		<div class='subscribe'>
			<div class='container'>
				<center><h2>Subscribe to Listnow.</h2></center>
				<center class='col-sm-12'>
					<p>
						Get amazing deals on textbooks, phones, laptops, and much more. We are planning to expand beyond University of Washington. Subscribe to get the most updated news and deals.
					</p>
				</center>
				<form method='post' action='subcribe.html'>
					<div class='col-sm-6 col-sm-offset-2'>
						<input type='email' class='form-control input' placeholder='Enter your email here'>
					</div>
					<div class='col-sm-4'>
						<button type='submit' class='btn btn-primary'>Subscribe Now</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
";