<!DOCTYPE html>
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
<?php   $salle=$_GET["salle"];
$location=$_GET["location"];  ?>
  <!-- /navbar-inner --> 

<!-- /navbar -->


  <div class="main-inner">
    <div class="container">
      <div class="row">
        <div class="span6">
          
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
          <!-- /widget -->
          
          <!-- /widget --> 
        </div>
        <!-- /span6 -->
       
          <!-- /widget -->
        </div>
        <!-- /span6 --> 
      </div>
      <!-- /row --> 
    </div>
   
  <!-- /main-inner --> 

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
