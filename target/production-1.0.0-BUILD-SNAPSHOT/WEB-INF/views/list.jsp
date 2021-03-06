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

<title>KOSU CONNECTE</title>
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

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/resources/dist/css/sb-admin-2.css"
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
							<c:if test="${actualUser.sexe=='F�minin' }">
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
								class="fa fa-sign-out fa-fw"></i> Se d�connecter</a></li>
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













				<sc:authorize access="hasAnyRole('ADMIN','SUPER_ADMIN')">
					<div class="alert alert-success alert-dismissable"
						id="create-success">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					</div>
					<div class="alert alert-success alert-dismissable"
						id="edit-success">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					</div>


					<input type="hidden" id="path"
						value="${pageContext.request.contextPath}">

					<input type="hidden" id="imageRedimensionner" value="" />
					<!-- Form modal for creating new user -->
					<div id="cmodal" class="modal fade" tabindex="-1" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Create User</h4>
								</div>
								<div class="modal-body">
									<div class="alert alert-danger" id="create-error"></div>
									<f:form id="create-form" role="form" method="post"
										action="${pageContext.request.contextPath}/kosu_connect/admin/create"
										modelAttribute="user" enctype="multipart/form-data"
										accept-charset="utf-8">
										<f:input id="create-id" type="hidden" path="idUser"
											value="${user.idUser}" />
										<fieldset class="form-group">
											<label for="create-firstName">First Name</label>
											<f:input path="firstName" class="form-control"
												id="create-firstName" type="text" placeholder="First Name" />
										</fieldset>
										<fieldset class="form-group">
											<label for="create-lastName">Last Name</label>
											<f:input path="lastName" class="form-control"
												id="create-lastName" type="text" placeholder="last Name" />
										</fieldset>
										<fieldset class="form-group">
											<label for="create-lastName">Sexe</label>
											<f:radiobutton path="sexe" value="Masculin"
												id="create-masculin" />
											Masculin&nbsp;&nbsp;
											<f:radiobutton path="sexe" value="F�minin"
												id="create-feminin" />
											F�minin
										</fieldset>


										<fieldset class="form-group">
											<label for="create-dateNaissance">Date de Naissance</label>
											<f:input path="dateDeNaissance" class="form-control"
												id="create-dateNaissance" type="text"
												placeholder="jj/mm/yyyy" />
										</fieldset>
										<fieldset class="form-group">
											<label for="create-email">Email</label>
											<f:input path="adresseMail" class="form-control"
												id="create-email" type="email" placeholder="Adresse email" />
										</fieldset>

										<fieldset class="form-group">
											<label for="create-adresse">Adresse</label>
											<f:input path="adresse" class="form-control"
												id="create-adresse" type="text" placeholder="Adresse" />
										</fieldset>
										<hr role="separator" class="divider" />
										<fieldset class="form-group">

											<input type="radio" id="role" onchange="showRole()">
											<label for="create-role">Roles</label>
										</fieldset>
										<div id="create-roles">
											<fieldset class="form-group">
												<sc:authorize access="hasRole('SUPER_ADMIN')">
													<label class="radio-inline">
												<f:radiobutton path="roles"
																id="create-superAdmin" value="1" />SUPER ADMIN
													</label>
														</sc:authorize>
														<label class="radio-inline">
												<f:radiobutton path="roles"
																id="create-admin" value="2" /> ADMIN
													</label>
													<label class="radio-inline">
												<f:radiobutton path="roles"
																id="create-consultant" value="3" /> CONSULTANT
													</label>
													<%-- <sc:authorize access="hasRole('SUPER_ADMIN')">
													<div class="checkbox">
														<label><f:checkbox path="roles"
																id="create-superAdmin" value="1" />SUPER ADMIN</label>
													</div>
												</sc:authorize>
												
												<div class="checkbox">
													<label> <f:checkbox path="roles" id="create-admin"
															value="2" />ADMIN
													</label>
												</div>
												
												<div class="checkbox">
													<label><f:checkbox path="roles"
															id="create-consultant" value="3" />CONSULTANT</label>
												</div> --%>
											</fieldset>
										</div>
										<fieldset class="form-group">
											<label for="create-file">Photo</label> <input
												id="create-file" type="file" name="file"
												placeholder="charger votre photo" /> <label
												id="file-create-error" class="label label-danger"></label>
										</fieldset>
										<div class="form-group">
											<button class="btn btn-success" type="submit">Save</button>
										</div>
									</f:form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->


					<!-- Form modal for updating user -->
					<div id="umodal" class="modal fade" tabindex="-1" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Update User</h4>
								</div>
								<div class="modal-body">
									<f:form id="edit-form" role="form" method="post"
										action="${pageContext.request.contextPath}/kosu_connect/admin/update"
										modelAttribute="user" enctype="multipart/form-data"
										accept-charset="utf-8">
										<f:input id="update-id" type="hidden" path="idUser"
											value="${user.idUser}" />
										<f:input id="create-userName" type="hidden" path="idUser"
											value="${user.idUser}" />
										<fieldset class="form-group">
											<label for="update-firstName">First Name</label>
											<f:input path="firstName" class="form-control"
												id="update-firstName" type="text" placeholder="First Name" />
										</fieldset>
										<fieldset class="form-group">
											<label for="update-lastName">Last Name</label>
											<f:input path="lastName" class="form-control"
												id="update-lastName" type="text" placeholder="last Name" />
										</fieldset>
										<fieldset class="form-group">
											<label for="update-sexe">Sexe</label>
											<f:radiobutton path="sexe" value="Masculin"
												id="update-masculin" />
											Masculin&nbsp;&nbsp;
											<f:radiobutton path="sexe" value="F�minin"
												id="update-feminin" />
											F�minin
										</fieldset>


										<fieldset class="form-group">
											<label for="update-dateNaissance">Date de Naissance</label>
											<f:input path="dateDeNaissance" class="form-control"
												id="update-dateNaissance" type="text"
												placeholder="jj/mm/yyyy" />
										</fieldset>
										<fieldset class="form-group">
											<label for="update-email">Email</label>
											<f:input path="adresseMail" class="form-control"
												id="update-email" type="email" placeholder="Adresse email" />
										</fieldset>

										<fieldset class="form-group">
											<label for="update-adresse">Adresse</label>
											<f:input path="adresse" class="form-control"
												id="update-adresse" type="text" placeholder="Adresse" />
										</fieldset>
										<hr role="separator" class="divider" />
										<fieldset class="form-group">

											<input type="radio" id="role" onchange="showUpdateRole()">
											<label for="update-role">Roles</label>
										</fieldset>
										<div id="update-roles">
											<fieldset class="form-group">
												<sc:authorize access="hasRole('SUPER_ADMIN')">
													<label class="radio-inline">
												<f:radiobutton path="roles"
																id="update-superAdmin" value="1" />SUPER ADMIN
													</label>
														</sc:authorize>
														<label class="radio-inline">
												<f:radiobutton path="roles"
																id="update-admin" value="2" /> ADMIN
													</label>
													<label class="radio-inline">
												<f:radiobutton path="roles"
																id="update-consultant" value="3" /> CONSULTANT
													</label>
											</fieldset>
										</div>
										<fieldset class="form-group">
											<label for="update-file">Photo</label> <input
												id="update-file" type="file" name="file"
												placeholder="charger votre photo" />
												<label
												id="file-update-error" class="label label-danger"></label>
										</fieldset>
										<div class="form-group">
											<button class="btn btn-success" type="submit">Save</button>
										</div>
									</f:form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
					
					<!-- Modal for delete -->
					<div id="dmodal" class="modal fade" tabindex="-1" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">Delete User</h4>
								</div>
								<div class="modal-body">
									<input id="delete-id" type="hidden" />
									<div align="center">
										<p>Are you sure you want to delete this record?</p>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>
									<a type="button" class="btn btn-danger"
										onclick="deleteEntity()">Delete</a>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->

					<div class="panal panel-primary">
						<div class="panel-heading">

							<button type="button" class="btn btn-success" data-toggle='modal'
								data-target='#cmodal'>
								<span class="fa  fa-user-plus"></span> Ajout utilisateur
							</button>
						</div>

<div class="panel-body">
						<table class="table table-striped table-bordered table-responsive table-hover nowrap"
							id="user-table">
							<thead>
								<tr>
									<th>Profile</th>

									<th>Nom</th>
									<th>Pr�nom</th>
									<th>Acc�s</th>
									<th>Email</th>
									<th>Role</th>
									<th>T�l�phone</th>
									<th></th>
									<th></th>
								</tr>
							</thead>


							<tfoot>
								<tr>
									<th>Profile</th>

									<th>Nom</th>
									<th>Pr�nom</th>
									<th>Acc�s</th>
									<th>Email</th>
									<th>Role</th>
									<th>T�l�phone</th>
									<th></th>
									<th></th>
								</tr>
							</tfoot>
						</table>
						</div>
					</div>
				</sc:authorize>
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<!-- jQuery -->
	<script src="//code.jquery.com/jquery-1.12.4.js"></script>

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
			updateTable();

		});
	</script>

</body>

</html>

