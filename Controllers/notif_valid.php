<?php
						$bd=mysqli_connect('localhost','root','','pfe');
						$id=$_POST['id'];
						$email=$_POST['email'];
						$mdp="v";

						
						$requette='UPDATE notification SET vue = "true" where id="'.$id.'" and user="'.$email.'"';
						$resultat=mysqli_query($bd,$requette);
						$nbcol=mysqli_num_fields($resultat);
                        $i="no";
					 if($ligne=mysqli_fetch_array($resultat)){
					 	$i="yes";
					 }
                        echo $i;

	
?>