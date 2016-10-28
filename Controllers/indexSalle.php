<!DOCTYPE html>
<?php session_start();
if(!isset($_SESSION["cnx"])) 
  header('Location: indexlogin.php'); 

if(!isset($_GET["salle"])){
  $salle="Salle 1";
  $location="Annexe 1";}
  else{
     $salle=$_GET["salle"];
  $location=$_GET["location"];}

  if(isset($_POST["nom"]) && isset($_POST["type"]) && isset($_POST["location"])){

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
  }
  
;?>
<html lang="en">
<head>
<meta charset="utf-8">
<title>SmartSchedue Administration</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
        rel="stylesheet">
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/pages/dashboard.css" rel="stylesheet">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"><span
                    class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span> </a><a class="brand" href="index.php">SmartAdministration </a>
      <div class="nav-collapse">
        <ul class="nav pull-right">
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                           ></i> </a>
            
          </li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="icon-user"></i> administrateur <b class="caret"></b></a>
            <ul class="dropdown-menu">
          
              <li><a href="deconnexion.php">deconnexion</a></li>
            </ul>
          </li>
        </ul>
        <form class="navbar-search pull-right">
          
        </form>
      </div>
      <!--/.nav-collapse --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /navbar-inner --> 
</div>
<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
      <ul class="mainnav">
        <li ><a href="index.php"><i class="icon-dashboard"></i><span>ScheduleBoard</span> </a> </li>
        <li><a href="indexSalle.php"><i class="icon-list-alt"></i><span>Salles</span> </a> </li>
         
        </li>
      </ul>
    </div>
    <!-- /container --> 
  </div>
  <!-- /subnavbar-inner --> 
</div>
<!-- /navbar -->

<div class="main">
  <div class="main-inner">
    <div class="container">
      <div class="row">
        <div class="span6">
          <div class="widget widget-nopad">
            
            <!-- /widget-header -->
                   </div>
          <!-- /widget -->
          <div class="widget widget-nopad">
            <div class="widget-header"> <i class="icon-list-alt"></i>
              <h3> <?php echo $salle ."/". $location; ?></h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <div id='calendar'>
              </div>
            </div>
            <!-- /widget-content --> 
          </div>
          <div class="account-container register">
  
  <div class="content clearfix">
    
    <form action="#" method="post">
    
      <h1>Ajouter une salle/Amphie</h1>      
      
      <div class="login-fields">
        
       
        
        <div class="field">
          <label for="firstname">nom de la salle:</label>
          <input type="text" id="nom" name="nom"  placeholder="nom de la salle" class="login" />
        </div> <!-- /field -->
        
        <div class="field">
          <label for="lastname">location:</label>  
          <input type="text" id="location" name="location"  placeholder="location" class="login" />
        </div> <!-- /field -->
        
        
        <div class="field">
          <label for="email">type:</label>
          <input type="text" id="type" name="type" value="" placeholder="type" class="login"/>
        </div> <!-- /field -->
        
         
        
      </div> <!-- /login-fields -->
      
      <div class="login-actions">
        
        
                  
        <button class="button btn btn-primary btn-large">Ajouter</button>
        
      </div> <!-- .actions -->
      
    </form>
    
  </div> <!-- /content -->
  
</div> <!-- /account-container -->

          
          <!-- /widget -->
          
          <!-- /widget --> 
        </div>
        <!-- /span6 -->
        <div class="span6">
          <div class="widget">
            
            <!-- /widget-content --> 
        
          <!-- /widget -->
           <div class="widget-header"> <i class="icon-list-alt"></i>
              <h3> Annexe 1</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <table class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th> nom </th>
                 
                    <th> type</th>
                    

                    <th class="td-actions"> </th>
                  </tr>
                </thead>
                <tbody>
                  <?php 
                    $i=0;

            $bd=mysqli_connect('localhost','root','','pfe');
            $now = new DateTime();
                        $actual=$now->format('Y-m-d');    // MySQL datetime format
                        
            $requette='SELECT * from Salle Where location="Annexe 1" ' ;
            $resultat=mysqli_query($bd,$requette);
            $nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
           while($ligne=mysqli_fetch_array($resultat)){
            $tab["nom"]=$ligne[0];
            $tab["location"]=$ligne[1];
            $tab["type"]=$ligne[2];
            
           
                    
                  echo '<tr>
                    <td> <a href="indexSalle.php?salle='.$tab["nom"].'&location='.$tab["location"].'" class="news-item-title" >'.$tab["nom"].'</a> </td>
                    
                    <td> '.$tab["type"].' </td>
                    <td class="td-actions">';
                    
                     echo '<a href="res_supp_site.php?email='.$tab["nom"].'" class="btn btn-danger btn-small"><i class="btn-icon-only icon-remove"> </i></a></td>';

                 ' </tr>';
                  }
                  ?>
                </tbody>
              </table>
            </div>
            <!-- /widget-content --> 
          </div>
          <!-- /widget --> 
          <div class="widget widget-nopad">
            
            <div class="widget">
            
            <!-- /widget-content --> 
        
          <!-- /widget -->
           <div class="widget-header"> <i class="icon-list-alt"></i>
              <h3> Annexe 2</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <table class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th> nom </th>
                 
                    <th> type</th>
                    

                    <th class="td-actions"> </th>
                  </tr>
                </thead>
                <tbody>
                  <?php 
                    $i=0;

            $bd=mysqli_connect('localhost','root','','pfe');
            $now = new DateTime();
                        $actual=$now->format('Y-m-d');    // MySQL datetime format
                        
            $requette='SELECT * from Salle Where location="Annexe 2" ' ;
            $resultat=mysqli_query($bd,$requette);
            $nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
           while($ligne=mysqli_fetch_array($resultat)){
            $tab["nom"]=$ligne[0];
            $tab["location"]=$ligne[1];
            $tab["type"]=$ligne[2];
            
           
                    
                  echo '<tr>
                     <td> <a href="indexSalle.php?salle='.$tab["nom"].'&location='.$tab["location"].'" class="news-item-title" >'.$tab["nom"].'</a> </td> 
                    
                    <td> '.$tab["type"].' </td>
                    <td class="td-actions">';
                    
                     echo '<a href="res_supp_site.php?email='.$tab["nom"].'" class="btn btn-danger btn-small"><i class="btn-icon-only icon-remove"> </i></a></td>';

                 ' </tr>';
                  }
                  ?>
                </tbody>
              </table>
            </div>
            <!-- /widget-content --> 
          </div>
          <div class="widget">
            
            <!-- /widget-content --> 
        
          <!-- /widget -->
           <div class="widget-header"> <i class="icon-list-alt"></i>
              <h3> Centrale</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <table class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th> nom </th>
                 
                    <th> type</th>
                    

                    <th class="td-actions"> </th>
                  </tr>
                </thead>
                <tbody>
                  <?php 
                    $i=0;

            $bd=mysqli_connect('localhost','root','','pfe');
            $now = new DateTime();
                        $actual=$now->format('Y-m-d');    // MySQL datetime format
                        
            $requette='SELECT * from Salle Where location="Centrale" ' ;
            $resultat=mysqli_query($bd,$requette);
            $nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
           while($ligne=mysqli_fetch_array($resultat)){
            $tab["nom"]=$ligne[0];
            $tab["location"]=$ligne[1];
            $tab["type"]=$ligne[2];
            
           
                    
                  echo '<tr>
                     <td> <a href="indexSalle.php?salle='.$tab["nom"].'&location='.$tab["location"].'" class="news-item-title" >'.$tab["nom"].'</a> </td> 
                    
                    <td> '.$tab["type"].' </td>
                    <td class="td-actions">';
                    
                     echo '<a href="res_supp_site.php?email='.$tab["nom"].'" class="btn btn-danger btn-small"><i class="btn-icon-only icon-remove"> </i></a></td>';

                 ' </tr>';
                  }
                  ?>
                </tbody>
              </table>
            </div>
            <!-- /widget-content --> 
          </div>
          <!-- /widget --> 
          
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /main-inner --> 
</div>
<!-- /main -->

<!-- Placed at the end of the document so the pages load faster --> 
<script src="js/jquery-1.7.2.min.js"></script> 
<script src="js/excanvas.min.js"></script> 
<script src="js/chart.min.js" type="text/javascript"></script> 
<script src="js/bootstrap.js"></script>
<script language="javascript" type="text/javascript" src="js/full-calendar/fullcalendar.min.js"></script>
 
<script src="js/base.js"></script> 
<script>     

       

      


       

        $(document).ready(function() {
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();
        var calendar = $('#calendar').fullCalendar({
          header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
          },
          
          select: function(start, end, allDay) {
            var title = prompt('Event Title:');
            if (title) {
              calendar.fullCalendar('renderEvent',
                {
                  title: title,
                  start: start,
                  end: end,
                  allDay: allDay
                },
                true // make the event "stick"
              );
            }
            calendar.fullCalendar('unselect');
          },
          editable: true,
         events: [
       
                

          <?php 
          $bd=mysqli_connect('localhost','root','','pfe');
            $now = new DateTime();
                        $actual=$now->format('Y-m-d');    // MySQL datetime format
                        $cal="";
             $requette="SELECT * from reservation where sa='".$salle."' AND location='".$location."' " ;
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
            $c=$cal;
$cal=$c."
            {
              title: '".$tab["heure"].'/'.$tab["desc"]."',
              start: new Date('".$tab["day"]."')
            },";
          }
         
            
            echo $cal; ?>
          ]
        });
      });
    </script><!-- /Calendar -->
</body>
</html>
