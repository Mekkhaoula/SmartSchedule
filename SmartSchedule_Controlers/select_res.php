<?php
						$bd=mysqli_connect('localhost','root','','pfe');
						$now = new DateTime();
                        $actual=$now->format('Y-m-d');    // MySQL datetime format
                        
						$requette='SELECT * from reservation where day>"'.$actual.'"'  ;
						$resultat=mysqli_query($bd,$requette);
						$nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
					 while($ligne=mysqli_fetch_array($resultat)){
					 	$tab["id"]=$ligne[0];
					 	$tab["user"]=$ligne[1];
					 	$tab["day"]=$ligne[2];
					 	$tab["heure"]=$ligne[3];
					 	$tab["as"]=$ligne[4];
					 	$tab["v"]=$ligne[5];
					 	$tab["location"]=$ligne[6];
					 	$tab["timer"]=$ligne[7];
					 	$tab["desc"]=$ligne[8];
					 	$tabb[$i]=$tab;
					 	$i++;
					 }
                        echo json_encode($tabb);

	
?>