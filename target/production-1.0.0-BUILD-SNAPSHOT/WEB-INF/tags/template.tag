<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="sc"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<title>KOSU CONNECTE</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<!-- Latest compiled and minified CSS -->






<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/base.css" />


<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/resources/dist/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/morrisjs/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<link
	href="<%=request.getContextPath()%>/resources/css/AdminLTE.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/css/_all-skins.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/myStyle.css"
	rel="stylesheet">


</head>

<body>

	<div id="wrapper">

	<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/kosu_connect/bienvenue"></c:url>">KOSU CONNECT</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i
						class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-messages">
						<li><a href="#">
								<div>
									<strong>John Smith</strong> <span class="pull-right text-muted">
										<em>Yesterday</em>
									</span>
								</div>
								<div>Lorem ipsum dolor sit amet, consectetur adipiscing
									elit. Pellentesque eleifend...</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<strong>John Smith</strong> <span class="pull-right text-muted">
										<em>Yesterday</em>
									</span>
								</div>
								<div>Lorem ipsum dolor sit amet, consectetur adipiscing
									elit. Pellentesque eleifend...</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<strong>John Smith</strong> <span class="pull-right text-muted">
										<em>Yesterday</em>
									</span>
								</div>
								<div>Lorem ipsum dolor sit amet, consectetur adipiscing
									elit. Pellentesque eleifend...</div>
						</a></li>
						<li class="divider"></li>
						<li><a class="text-center" href="#"> <strong>Read
									All Messages</strong> <i class="fa fa-angle-right"></i>
						</a></li>
					</ul> <!-- /.dropdown-messages --></li>
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-tasks">
						<li><a href="#">
								<div>
									<p>
										<strong>Task 1</strong> <span class="pull-right text-muted">40%
											Complete</span>
									</p>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 40%">
											<span class="sr-only">40% Complete (success)</span>
										</div>
									</div>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<p>
										<strong>Task 2</strong> <span class="pull-right text-muted">20%
											Complete</span>
									</p>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-info" role="progressbar"
											aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
											style="width: 20%">
											<span class="sr-only">20% Complete</span>
										</div>
									</div>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<p>
										<strong>Task 3</strong> <span class="pull-right text-muted">60%
											Complete</span>
									</p>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-warning"
											role="progressbar" aria-valuenow="60" aria-valuemin="0"
											aria-valuemax="100" style="width: 60%">
											<span class="sr-only">60% Complete (warning)</span>
										</div>
									</div>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<p>
										<strong>Task 4</strong> <span class="pull-right text-muted">80%
											Complete</span>
									</p>
									<div class="progress progress-striped active">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="80" aria-valuemin="0"
											aria-valuemax="100" style="width: 80%">
											<span class="sr-only">80% Complete (danger)</span>
										</div>
									</div>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a class="text-center" href="#"> <strong>See
									All Tasks</strong> <i class="fa fa-angle-right"></i>
						</a></li>
					</ul> <!-- /.dropdown-tasks --></li>
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-alerts">
						<li><a href="#">
								<div>
									<i class="fa fa-comment fa-fw"></i> New Comment <span
										class="pull-right text-muted small">4 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
										class="pull-right text-muted small">12 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<i class="fa fa-envelope fa-fw"></i> Message Sent <span
										class="pull-right text-muted small">4 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<i class="fa fa-tasks fa-fw"></i> New Task <span
										class="pull-right text-muted small">4 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
										class="pull-right text-muted small">4 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a class="text-center" href="#"> <strong>See
									All Alerts</strong> <i class="fa fa-angle-right"></i>
						</a></li>
					</ul> <!-- /.dropdown-alerts --></li>
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <c:if test="${actualUser.photo !=null}">
							<img class="img-circle img-responsive profile"
								src="${pageContext.request.contextPath}/kosu_connect/admin/loadUserProfile/${actualUser.idUser}" />
								</c:if> <c:if test="${actualUser.photo==null}">
							<c:if test="${actualUser.sexe=='Masculin' }">
								<img class="img-circle img-responsive profile"
									src="${pageContext.request.contextPath}/resources/images/male.png" />
							</c:if>
						</c:if> <c:if test="${actualUser.photo==null}">
							<c:if test="${actualUser.sexe=='Féminin' }">
								<img class="img-circle img-responsive profile"
									src="${pageContext.request.contextPath}/resources/images/female.png" />
							</c:if>
						</c:if> <span class="hidden-xs">${actualUser.userName}</span> <i
						class="fa fa-caret-down"></i> <!-- Status -->

				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a
							href="<c:url value="/kosu_connect/admin/loadUserProfile" />"><i
								class="fa fa-user fa-fw"></i> Mon Profile</a></li>

						<li class="divider"></li>
						<li><a href="<c:url value="/j_spring_security_logout" />"><i
								class="fa fa-sign-out fa-fw"></i> Se déconnecter</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li class="sidebar-search">
							<div class="input-group custom-search-form">
								<input type="text" class="form-control" placeholder="Search...">
								<span class="input-group-btn">
									<button class="btn btn-default" type="button">
										<i class="fa fa-search"></i>
									</button>
								</span>
							</div> <!-- /input-group -->
						</li>
						<li><a href="<c:url value="/kosu_connect/bienvenue"></c:url>"><i
								class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
						<li><a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>
								Charts<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="flot.html">Flot Charts</a></li>
								<li><a href="morris.html">Morris.js Charts</a></li>
							</ul> <!-- /.nav-second-level --></li>
						<li><a
							href="<c:url value="/kosu_connect/admin/messages_view"></c:url>"><i
								class="fa fa-envelope-o"></i> Messagerie</a></li>
						<sc:authorize access="hasAnyRole('ADMIN','SUPER_ADMIN')">
							<li><a
								href='<c:url value="/kosu_connect/admin/list"></c:url>'><i
									class="fa fa-users"></i> Utilisateurs</a></li>
							
						</sc:authorize>
						
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<div class="row">

				<jsp:doBody />
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->

	<script
		src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
		
		<!-- Date picker bootStrap -->
<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.min.js"></script>
<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.fr.min.js
		"></script>
		
	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/raphael/raphael.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/vendor/morrisjs/morris.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/data/morris-data.js"></script>
	


	<!-- Custom Theme JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/sb-admin-2.js"></script>

	
	<script src="<%=request.getContextPath()%>/resources/js/model/ajax.js"></script>
		<script
		src="<%=request.getContextPath()%>/resources/js/model/resample.js"></script>

	<script>
		$(document).ready(function($) {
			$("#edit-form").submit(function(event) {
				event.preventDefault();
				postEdit();
			});
			$("#create-form").submit(function(event) {
				event.preventDefault();
				postCreate();
			});
			$("#create-roles").hide();
			$("#create-error").hide();
			$("#edit-success").hide();
			$("#create-success").hide();
			$("#create-error").hide();
			$("#update-roles").hide();
			$("#uploader").hide();

			// 	partie drag and drop
			$("#drop-area").on('dragenter', function(e) {
				e.preventDefault();
				$(this).css('background', '#BBD5B8');
			});

			$("#drop-area").on('dragover', function(e) {
				e.preventDefault();
			});

			$("#drop-area").on('drop', function(e) {
				e.preventDefault();
				var file = e.originalEvent.dataTransfer.files[0];
				if (typeof file !== 'undefined' && file.type.match('image.*')) {
					resizeImage(file, 256, function(data) {
						$("#profile-avatar").attr("src", data);

						changeImage(data)

					});
				}
			});
			//tooltip
			$('[data-toggle="tooltip"]').tooltip();
			
		});
	</script>

</body>

</html>

