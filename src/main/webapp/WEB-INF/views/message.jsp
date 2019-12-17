<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="sc"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<title>Kosu connecte</title>
<!-- Bootstrap Core CSS -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap-datepicker.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.css"
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css"/>
<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/resources/dist/css/sb-admin-2.css"
	rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/12.0.2/css/intlTelInput.css"
	rel="stylesheet">
	
<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

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
								<input type="text" class="form-control"
									placeholder="Rechercher..."> <span
									class="input-group-btn">
									<button class="btn btn-default" type="button">
										<i class="fa fa-search"></i>
									</button>
								</span>
							</div> <!-- /input-group -->
						</li>
						<li><a href="<c:url value="/kosu_connect/bienvenue"></c:url>"><i
								class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
						<li><a href="<c:url value="/kosu_connect/admin/statistiques"></c:url>"><i class="fa fa-bar-chart-o fa-fw"></i>
								Statistiques</a>
							</li>
						<li><a
							href="<c:url value="/kosu_connect/admin/messages_view"></c:url>"><i
								class="fa fa-envelope-o"></i> Messagerie<span class="label label-danger pull-right" id="nouvMessages"></span></a></li>
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






				<div class="col-md-3">
					<a href="<c:url value="/kosu_connect/admin/nouveau_message"></c:url>" class="btn btn-primary btn-block margin-bottom">Composer
						un message</a>

					<div class="box box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">Dossiers</h3>

							<div class="box-tools">
								<button type="button" class="btn btn-box-tool"
									data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="box-body no-padding">
							<ul class="nav nav-pills nav-stacked">
								<li class="active" id="recu" onclick="recu()"><a href="<c:url value="/kosu_connect/admin/messages_view"></c:url>" ><i class="fa fa-inbox"></i>Message reçu<span class="label label-danger pull-right" id="nouvMessages"></span></a></li>
								<li id="envoye" onclick="envoie()"><a href=""><i class="fa fa-envelope-o"  ></i> Envoyé</a></li>

							</ul>
						</div>
						<!-- /.box-body -->
					</div>

				</div>
				<!-- /.col -->
				<div class="col-md-9">
					<div class="panel panel-primary">
						<div class="panel-heading">Messagerie</div>
						<!-- /.box-tools -->
					</div>
					<!-- /.box-header -->
					<div class="box-body no-padding">
						<div class="mailbox-controls">
							<!-- Check all button -->

							<div class="btn-group">
								<button type="button" class="btn btn-primary btn-sm">
									<i class="glyphicon glyphicon-envelope"></i>
								</button>
								<button type="button" class="btn btn-primary btn-sm">
									<i class="fa fa-reply"></i>
								</button>

							</div>
							<!-- /.btn-group -->
							<button type="button" class="btn btn-primary btn-sm">
								<i class="fa fa-refresh"></i>
							</button>

						</div>
						<!-- /.pull-right -->
					</div>

					<div class="panel-body" id="message_conteneur">
						<table class="table table-hover table-striped" id="boite-message">
							<thead>
								<tr>
									<th></th>
									<th></th>
									<th></th>
									<th></th>

								</tr>
							</thead>
							<tbody></tbody>

							<tfoot>
								<tr>

									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</tfoot>
						</table>
					</div>



					<!-- /.box-body -->
					<div class="panel-footer no-padding">
						<div class="mailbox-controls">
							<!-- Check all button -->

							<div class="btn-group">
								<button type="button" class="btn btn-primary btn-sm">
									<i class="glyphicon glyphicon-envelope"></i>
								</button>
								<button type="button" class="btn btn-primary btn-sm">
									<i class="fa fa-reply"></i>
								</button>

							</div>
							<!-- /.btn-group -->
							<button type="button" class="btn btn-primary btn-sm">
								<i class="fa fa-refresh"></i>
							</button>

						</div>
						<!-- /.btn-group -->
					</div>
					<!-- /.pull-right -->
				</div>
			</div>
		</div>
		<!-- /. box -->
	</div>
	<!-- /.col -->
	</div>
	<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<!-- jQuery -->
	<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type = "text/javascript" 
         src = "https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script
		src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"></script>
	<script
		src="https://cdn.datatables.net/responsive/2.1.1/js/responsive.bootstrap.min.js"></script>

	<!-- Date picker bootStrap -->
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.fr.min.js
		"></script>
		<script src="<%=request.getContextPath()%>/resources/js/model/moment-with-locales.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/sb-admin-2.js"></script>

	<script src="<%=request.getContextPath()%>/resources/js/model/ajax.js"></script>


	<script
		src="<%=request.getContextPath()%>/resources/js/model/resample.js"></script>

	<script>
		$(document).ready(function($) {
			nouvMessageCount();
			updateMessageTable();
			envoie=function (){
				$("#envoye").addClass('active');
				$('#recu').removeClass("active");
				
			}
			recu=function (){
				$("#recu").addClass('active');
				$('#envoye').removeClass("active");
				
			}

		});
	</script>

</body>

</html>

