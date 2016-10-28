<?php
						$bd=mysqli_connect('localhost','root','','pfe');
						$requette='SELECT * from usersd';
						$resultat=mysqli_query($bd,$requette);
						$nbcol=mysqli_num_fields($resultat);
					 
	while($ligne=mysqli_fetch_array($resultat)){
	
				
	for($i=0;$i<$nbcol;$i++){
		 echo $ligne[$i]."\n"; 

		 
		
	 
		

	}}
		
	
?>