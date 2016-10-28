<?php

//function insert(){
	//if(!isset($_POST['nom'])&&!isset($_POST['mdp'])){
		
		//include("fourmilaire.html.php");
	//}
	//else{
		
		$id=$_POST['id'];
		$user=$_POST['user'];
		$day=$_POST['day'];
		$heuredebut=$_POST['heure'];
		$desc=$_POST['desc'];
		$as=$_POST['as'];
		$v=$_POST['v'];
		$timer=$_POST['timer'];
        $locat=$_POST['locat'];
		
		
		
		$conn = mysqli_connect("localhost", "root", "");
    if(mysqli_connect_errno()){
        exit("Erreur de connexion db ! ");
    }
    mysqli_select_db($conn,"pfe");
		 $reqpr = mysqli_prepare($conn,"INSERT INTO reservation value('$id','$user','$day','$heuredebut','$as','$v','$locat','$timer','$desc')");
		$ok=mysqli_stmt_execute($reqpr);
		if($ok){echo"un enregistrement est effectué";}
		else{echo"la requéte a echoué";}
		
		
	//}
	
	
	
	
//}
//insert();




?>