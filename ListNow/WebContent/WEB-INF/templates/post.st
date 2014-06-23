post(contextPath, cities, categories, city, category, errorMessage, title, description, price)::="
<div class='container'>
	<div class='jumbotron'>
		$if(errorMessage)$
			<div class='alert alert-danger'>$errorMessage$</div>
		$endif$
		
		<form class='form-horizontal' role='form' method='post' action='$contextPath$post' enctype='multipart/form-data'>
			<h1><center>Post</center></h1>
			
			<div class='form-group'>
		    	<label for='inputTitle' class='col-sm-2 control-label'>Title</label>
		    	<div class='col-sm-10'>
		    		<input type='text' class='form-control' id='inputTitle' placeholder='Title' name='title' value='$title$'/>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputDescription' class='col-sm-2 control-label'>Description</label>
		    	<div class='col-sm-10'>
		    		<textarea class='form-control' id='inputDescription' placeholder='Description' name='description' rows='5' style='resize:none;'>$description$</textarea>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputPrice' class='col-sm-2 control-label'>Price</label>
		    	<div class='col-sm-10'>
		    		<input type='number' class='form-control' id='inputPrice' placeholder='Price' name='price' value='$price$'/>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputCity' class='col-sm-2 control-label'>City</label>
		    	<div class='col-sm-10'>
		    		<select type='text' class='form-control' id='inputCity' placeholder='City' name='city'>
		    			$if(city)$
		    				<option value='$city.zipcode$' selected='selected'>$city.name$</option>
		    			$endif$
		    			$cities:{n|
		    				<option value='$n.zipcode$'>$n.name$</option>
		    			}$
		    		</select>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputCategory' class='col-sm-2 control-label'>Category</label>
		    	<div class='col-sm-10'>
		    		<select class='form-control' id='inputCategory' placeholder='Category' name='category'>
		    			$if(category)$
		    				<option value='$category.id$' selected='selected'>$category.name$</option>
		    			$endif$
		    			$categories:{n|
		    				<option value='$n.id$'>
		    					$n.name$
		    				</option>
		    			}$
		    		<select>
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputImage1' class='col-sm-2 control-label'>Image 1</label>
		    	<div class='col-sm-10'>
		    		<input type='file' id='inputImage1' name='image1' accept='image/*' />
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputImage2' class='col-sm-2 control-label'>Image 2</label>
		    	<div class='col-sm-10'>
		    		<input type='file' id='inputImage2' name='image2' accept='image/*' />
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputImage3' class='col-sm-2 control-label'>Image 3</label>
		    	<div class='col-sm-10'>
		    		<input type='file' id='inputImage3' name='image3' accept='image/*' />
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputImage4' class='col-sm-2 control-label'>Image 4</label>
		    	<div class='col-sm-10'>
		    		<input type='file' id='inputImage4' name='image4' accept='image/*' />
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
		    	<label for='inputImage5' class='col-sm-2 control-label'>Image 5</label>
		    	<div class='col-sm-10'>
		    		<input type='file' id='inputImage5' name='image5' accept='image/*' />
		   		</div>
		  	</div>
		  	
		  	<div class='form-group'>
				<div class='col-sm-offset-2 col-sm-10'>
					<button type='submit' class='btn btn-default'>Post it</button>
					<button type='reset' class='btn btn-default'>Reset</button>
				</div>
			</div>
			
		</form>
	</div>
</div>		
";