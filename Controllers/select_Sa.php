<?php
						$bd=mysqli_connect('localhost','root','','pfe');
						$requette='SELECT * from salle';
						$resultat=mysqli_query($bd,$requette);
						$nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
					 while($ligne=mysqli_fetch_array($resultat)){
					 	$tab["nom"]=$ligne[0];
					 	$tab["location"]=$ligne[1];
					 	$tabb[$i]=$tab;
					 	$i++;
					 }
                        echo json_encode($tabb);

	
?>