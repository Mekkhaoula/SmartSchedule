<?php
						$bd=mysqli_connect('localhost','root','','pfe');
						
						$email=$_POST['email'];
						$mdp="v";
						$requette='SELECT * from reservation where id="'.$email.'"'  ;
						$resultat=mysqli_query($bd,$requette);
						$nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
					 $ligne=mysqli_fetch_array($resultat);
					 	$tab["id"]=$ligne[0];
					 	$tab["user"]=$ligne[1];
					 	$tab["day"]=$ligne[2];
					 	$tab["heure"]=$ligne[3];
					 	$tab["as"]=$ligne[4];
					 	$tab["v"]=$ligne[5];
					 	$tab["location"]=$ligne[6];
					 	$tab["timer"]=$ligne[7];
					 	$tab["desc"]=$ligne[8];

						
						$requette='UPDATE reservation SET v = "v" where id="'.$email.'"';
						$resultat=mysqli_query($bd,$requette);
                       $now = new DateTime();
                       $actual=$now->format('Y-m-d');

    $conn = mysqli_connect("localhost", "root", "");
    mysqli_select_db($conn,"pfe");
             $message="la demande de reservation de ".$tab["as"]."/".$tab["location"]."le".$tab["day"]. " a etait valider";
		     $reqpr = mysqli_prepare($conn,"INSERT INTO notification value('".$tab['user']."','$message','$actual','false','reservation valider','$email')");
		     $ok=mysqli_stmt_execute($reqpr);
						$nbcol=mysqli_num_fields($resultat);
                        $i="no";
					 if($ligne=mysqli_fetch_array($resultat)){
					 	$i="yes";
					 }
                        echo $i;

	
?>