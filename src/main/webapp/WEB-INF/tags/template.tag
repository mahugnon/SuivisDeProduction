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

<title>Kosu-connecte</title>
<!-- Latest compiled and minified JavaScript -->
<!-- Latest compiled and minified CSS -->






<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/base.css" />


<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
	<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap-datepicker.min.css"
	rel="stylesheet">
<!-- DataTables CSS -->
<link
	href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link
	href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/1.10.15/css/dataTables.jqueryui.min.css"
	rel="stylesheet">
<!-- Boostrap validation css -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" />
<!-- MetisMenu CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/resources/dist/css/sb-admin-2.css"
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
				<a class="navbar-brand"
					href="<c:url value="/kosu_connect/bienvenue"></c:url>">KOSU
					CONNECT</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">

				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">
					 <i class="glyphicon glyphicon-envelope msg" title="2"></i>					 
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-tasks" id="nouveau_message">
					
						<li class="divider"></li>
						<li><a class="text-center" href="#"> <strong>Voir
									tous les messages</strong> <i class="fa fa-angle-right"></i>
						</a></li>
					</ul> <!-- /.dropdown-tasks --></li>
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="glyphicon glyphicon-globe notif" title="4"></i>
						
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-alerts" id="notifications_arret">
						
						<li><a class="text-center" href="#"> <strong>Voir
						
									Toutes les notifications</strong> <i class="fa fa-angle-right"></i>
						</a></li>
					</ul> <!-- /.dropdown-alerts --></li>

				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <c:if
							test="${actualUser.photo !=null}">
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
						<li><a
							href="<c:url value="/kosu_connect/admin/statistiques"></c:url>"><i
								class="fa fa-bar-chart-o fa-fw"></i> Statistiques</a></li>
						<li><a
							href="<c:url value="/kosu_connect/admin/messages_view"></c:url>"><i
								class="fa fa-envelope-o"></i> Messagerie<span
								class="label label-danger pull-right" id="nouvMessages"></span></a></li>
						<sc:authorize access="hasRole('IT_MANAGER')">
							<li><a
								href='<c:url value="/kosu_connect/admin/list"></c:url>'><i
									class="fa fa-users"></i> Utilisateurs</a></li>

						</sc:authorize>
						<sc:authorize access="hasRole('IT_MANAGER')">
							<li><a
								href='<c:url value="/kosu_connect/admin/configuration"></c:url>'><i
									class="fa fa-gear"></i> Configuration</a></li>

						</sc:authorize>

					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<img class="img-fluid" src="<%=request.getContextPath()%>/resources/images/images.jpg">
		
			<div class="row">

				<jsp:doBody />
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->

	<script src="//code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Date picker bootStrap -->
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.fr.min.js
		"></script>

	<!-- script for chart -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.js"></script>

	
	<!-- Manipulation de date -->
	<script
		src="<%=request.getContextPath()%>/resources/js/model/moment-with-locales.min.js"></script>

	<!-- BootStrap validation -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/language/be_FR.min.js"></script>

	<!-- DataTables JavaScript -->
	<script
		src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js
		"></script>
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/12.0.2/js/intlTelInput.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/sb-admin-2.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/adminlte.min.js"></script>


	<script src="<%=request.getContextPath()%>/resources/js/model/ajax.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/model/resample.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/model/statistique.js"></script>
	
	<script
		src="<%=request.getContextPath()%>/resources/js/model/formValidation.js"></script>
		
<script
		src="<%=request.getContextPath()%>/resources/js/model/pdg/pdg_stat.js"></script>
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

			//escalation
			$("#modification_escalation").hide();
			$("#edit_ecalation").submit(function(event) {
				event.preventDefault();
				createEscalation();
			});
			loadEscalation();

			//Plent section
			updatePlentSection();
			$("#addPentSection").hide();

			$("#createPlentSection_success").hide();

			//Segment

			$("#addSegment").hide();
			loadSegment1();
			$("#createSegment_success").hide();
			//chaine
			hideAddChaineForm();
			$("#createChaine_success").hide();

			loadChaine();
			nouvMessageCount();
//Statistiques
			initFiltre();
			
		});
	</script>

</body>

</html>

