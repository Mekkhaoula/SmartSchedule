<?php

//function insert(){
	//if(!isset($_POST['nom'])&&!isset($_POST['mdp'])){
		
		//include("fourmilaire.html.php");
	//}
	//else{
		
		
		$NomSA=$_POST['nom'];
		$Location=$_POST['location'];
		$type=$_POST['type'];
 
		
		
		
		$conn = mysqli_connect("localhost", "root", "");
    if(mysqli_connect_errno()){
        exit("Erreur de connexion db ! ");
    }
    mysqli_select_db($conn,"pfe");
		 $reqpr = mysqli_prepare($conn,"INSERT INTO salle value('$NomSA','$Location','$type')");
		$ok=mysqli_stmt_execute($reqpr);
		if($ok){echo"un enregistrement est effectué";}
		else{echo"la requéte a echoué";}
		
		
	//}
	
	
	
	
//}
//insert();




?>