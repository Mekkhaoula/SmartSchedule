<?php
						$bd=mysqli_connect('localhost','root','','pfe');
						$now = new DateTime();
                        $actual=$now->format('Y-m-d');
                        $day=$_POST['day'] ;  
                        $time=$_POST['time'] ; 
                        $location=$_POST['location'] ; 
                        
						$requette='SELECT * from salle Where nom not in(SELECT sa from reservation where  day="'.$day.'" AND heure="'.$time.'" AND location="'.$location.'") ' ;
						$resultat=mysqli_query($bd,$requette);
						$nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
					 while($ligne=mysqli_fetch_array($resultat)){
					 	
					 	$tab["as"]=$ligne[0];
					 	
					 	$tab["location"]=$ligne[1];
					 	
					 	$tabb[$i]=$tab;
					 	$i++;
					 }
                        echo json_encode($tabb);

	
?>