search(contextPath, items)::="
<div class='results'>
	<div class='container'>
   		 $if(!items)$
   		 	<div class='alert alert-warning'>Sorry, there is no result.</div>	
   		 $endif$
   		 
   		 $items:{item|
   		 	<div class='result'>
				<div class='col-sm-4'>
		    		<div class='thumbnail'>
		    			<img src='$contextPath$image_post/$first(item.itemImages).image.id$$first(item.itemImages).image.extension$' width='300px' />
				    	<span class='badge'>$length(item.itemImages)$ Photos</span>
		    		</div>
	   		 	</div>
	   		 	<div class='col-sm-8'>
		    		<div class='result-title'>
		    			$item.title$
		    		</div>
    				<h2> description: </h2>
		    		<div class='description'>
		    			$item.description$
		    		</div>
					<div class='btn-group tags'>
		    			<button type='button' class='btn btn-success btn-lg'> \$$item.price$ </button>
		    			<button type='button' class='btn btn-default btn-lg'> $item.city.name$, WA </button>
		    			<button type='button' class='btn btn-primary btn-lg'> Message Seller </button>
					</div>
	   		 	</div>
   		 	</div>
   		 }$
   		 
	</div>
</div>	
";