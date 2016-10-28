<?php

//function insert(){
	//if(!isset($_POST['nom'])&&!isset($_POST['mdp'])){
		
		//include("fourmilaire.html.php");
	//}
	//else{
		
		$email=$_POST['email'];
		$mdp=$_POST['mdp'];
		$nom=$_POST['nom'];
		$prenom=$_POST['prenom'];
		$dept=$_POST['dept'];
		$v=$_POST['v'];
		$conn = mysqli_connect("localhost", "root", "");
    if(mysqli_connect_errno()){
        exit("Erreur de connexion db ! ");
    }
    mysqli_select_db($conn,"pfe");
		 $reqpr = mysqli_prepare($conn,"INSERT INTO usersd value('$email','$mdp','$nom','$dept','$v')");
		$ok=mysqli_stmt_execute($reqpr);
		if($ok){echo"un enregistrement est effectué";}
		else{echo"la requéte a echoué";}
		
		
	//}
	
	
	
	
//}
//insert();




?>