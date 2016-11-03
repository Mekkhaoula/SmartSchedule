
<?php       
						$bd=mysqli_connect('localhost','root','','pfe');
						
						$id=$_POST['id'];
						$requette="SELECT * from reservation WHERE id='$id'";
						$resultat=mysqli_query($bd,$requette);
						$nbcol=mysqli_num_fields($resultat);
                        $i="non reserve";
					 if($ligne=mysqli_fetch_array($resultat)){
					 	$i="reserve ";
					 }
                        echo $i;
	
?>