<?php
						$bd=mysqli_connect('localhost','root','','pfe');
						
						$email=$_GET["email"];
						$mdp="v";

						
						$requette='DELETE from reservation where id="'.$email.'"';
						$resultat=mysqli_query($bd,$requette);
						header('Location: index.php'); 

	
?>