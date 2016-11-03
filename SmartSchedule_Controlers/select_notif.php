<?php
						$bd=mysqli_connect('localhost','root','','pfe');
						
                        $user=$_POST["user"];   
                        
						$requette="SELECT * from notification where user='$user'"  ;
						$resultat=mysqli_query($bd,$requette);
						$nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
					 while($ligne=mysqli_fetch_array($resultat)){
					 	$tab["user"]=$ligne[0];
					 	$tab["message"]=$ligne[1];
					 	$tab["date"]=$ligne[2];
					 	$tab["vue"]=$ligne[3];
					 	$tab["txt"]=$ligne[4];
					 	$tab["id"]=$ligne[5];
					 	
					 	$tabb[$i]=$tab;
					 	$i++;
					 }
                        echo json_encode($tabb);


	
?>