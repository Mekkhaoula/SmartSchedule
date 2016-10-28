<!DOCTYPE html>
<?php session_start();
if(!isset($_SESSION["cnx"])) 
  header('Location: indexlogin.php'); 
?>
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
              <h3> Schedule</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <div id='calendar'>
              </div>
            </div>
            <!-- /widget-content --> 
          </div>
          <div class="widget widget-nopad">
            <div class="widget-header"> <i class="icon-list-alt"></i>
              <h3> Recent News</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <ul class="news-items">
                <?php $bd=mysqli_connect('localhost','root','','pfe');
            
                   $date=  date('Y-m-d', strtotime( '-3 days' ) );
                        
            $requette="SELECT * from notification where date >'$date' "  ;
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
            
            
           echo'
                <li>
                  
                  
                  <div class="news-item-detail"> '.$tab["date"]=$ligne[2].'</a>
                    <p class="news-item-preview"> '. $tab["message"].' </p>
                  </div>
                  
                </li>';}
               ?>
              </ul>
            </div>
            <!-- /widget-content --> 
          </div>
          <!-- /widget -->
          
          <!-- /widget --> 
        </div>
        <!-- /span6 -->
        <div class="span6">
          <div class="widget">
            
            <!-- /widget-content --> 
        
          <!-- /widget -->
           <div class="widget-header"> <i class="icon-list-alt"></i>
              <h3> Reservation</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <table class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th> Date </th>
                    <th> créneau </th>
                    <th> location</th>
                    <th> utilisateur</th>
                    

                    <th class="td-actions"> </th>
                  </tr>
                </thead>
                <tbody>
                  <?php 
                    $i=0;

            $bd=mysqli_connect('localhost','root','','pfe');
            $now = new DateTime();
                        $actual=$now->format('Y-m-d');    // MySQL datetime format
                        
            $requette="SELECT * from reservation where day >'$date'" ;
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
                    
                  echo '<tr>
                    <td> '.$tab["day"].' </td>
                    <td> '.$tab["heure"].' </td>
                    <td> '.$tab["location"].'/'.$tab["as"].' </td>
                    <td> '.$tab["user"].' </td>
                    <td class="td-actions">';
                    if($tab["v"]=="nv"){echo '<a href="res_valid_site.php?e='.$tab["id"].'" class="btn btn-small btn-success"><i class="btn-icon-only icon-ok"> </i></a>';}
                     echo '<a href="res_supp_site.php?email='.$tab["id"].'" class="btn btn-danger btn-small"><i class="btn-icon-only icon-remove"> </i></a></td>';

                 ' </tr>';
                  }
                  ?>
                </tbody>
              </table>
            </div>
            <!-- /widget-content --> 
          </div>
          <!-- /widget --> 
         <div class="widget">
            
            <!-- /widget-content --> 
        
          <!-- /widget -->
           <div class="widget-header"> <i class="icon-list-alt"></i>
              <h3> Utilisateurs</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <table class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th> email </th>
                    <th> nom</th>
                    <th> departement</th>
                    <th> mot de passe</th>
                    

                    <th class="td-actions"> </th>
                  </tr>
                </thead>
                <tbody>
                  <?php 
                    $i=0;

            $bd=mysqli_connect('localhost','root','','pfe');
            $now = new DateTime();
                        $actual=$now->format('Y-m-d');    // MySQL datetime format
                        
            $requette="SELECT * from usersd" ;
            $resultat=mysqli_query($bd,$requette);
            $nbcol=mysqli_num_fields($resultat);
                        $tab=array(); $tabb=array();$i=0;
           while($ligne=mysqli_fetch_array($resultat)){
            $tab["email"]=$ligne[0];
            $tab["mdp"]=$ligne[1];
            $tab["nom"]=$ligne[2];
            $tab["dept"]=$ligne[3];
            $tab["v"]=$ligne[4];
           
                    
                  echo '<tr>
                    <td> '.$tab["email"].' </td>
                    <td> '.$tab["nom"].' </td>
                    <td> '.$tab["dept"].' </td>
                    <td> '.$tab["mdp"].' </td>
                    <td class="td-actions">';
                   if($tab["v"]=="nv"){echo '<a href="users_valid_site.php?email='.$tab["email"].'" class="btn btn-small btn-success"><i class="btn-icon-only icon-ok"> </i></a>';}
                     echo '<a href="users_supp_site.php?email='.$tab["email"].'" class="btn btn-danger btn-small"><i class="btn-icon-only icon-remove"> </i></a></td>';

                 ' </tr>';
                  }
                  ?>
                </tbody>
              </table>
            </div>
            <!-- /widget-content --> 
          </div>  
          <!-- /widget -->
        </div>
        <!-- /span6 --> 
      </div>
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
            $requette='SELECT * from reservation ' ;
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
              title: '".$tab["location"].'/'.$tab["as"]."',
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
