<?php
						$bd=mysqli_connect('localhost','root','','pfe');
						$requette='SELECT * from location';
						$resultat=mysqli_query($bd,$requette);
						$nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
					 while($ligne=mysqli_fetch_array($resultat)){
					 	$tab["nom"]=$ligne[$i];
					 	
					 	$tab[$i]=$tab;
					 	$i++;
					 }
                        echo json_encode($tab);

	
?>