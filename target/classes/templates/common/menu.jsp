<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- <title>Score Well - An Institute for Civil Services Examination</title> -->
<title>Score Well - ${title}</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="css/layout.css" rel="stylesheet" type="text/css" media="all">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
<!-- <link href="css/about.css" rel="stylesheet" type="text/css" media="all"> -->
<!-- <link rel="stylesheet" type="text/css" href="css/bootstrap4/bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link rel="stylesheet" type="text/css" href="css/responsive.css">

</head>
<body id="top">
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<div class="wrapper row0">
		<header id="header" class="hoc">
			<div id="logo" class="fl_left">
				<h1>
					<a href="/home">Score Well</a>
				</h1>
			</div>
			<!-- <div id="logo" class="fl_left">
						<img src="img/logo.png" alt="">
					</div> -->
			<div class="fl_right">
				<ul class="nospace heading">
					<li><a href="#"><font color="blue"><B>Login</B></font></a></li>
				</ul>
			</div>
		</header>
	</div>
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<!-- ################################################################################################ -->
	<div class="wrapper row1">
		<div id="topbar" class="clear">
			<nav id="mainav" class="fl_left">
				<ul class="clear">
					<li><a href="/home">Home</a></li>
					<li><a href="/about">About us</a></li>
					<li><a href="/course-info">Courses</a></li>
					<li><a href="/career">Careers</a></li>
					<li><a href="/contact">Contact</a></li>
					<!-- <li><a href="/resource">Resource</a></li> -->
				</ul>
			</nav>
			<div id="search" class="fl_right">
				<form class="clear" method="post" action="#">
					<fieldset>
						<legend>Search:</legend>
						<input type="text" value="" placeholder="Search Here">
						<button class="fa fa-search" type="submit" title="Search">
							<em>Search</em>
						</button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	