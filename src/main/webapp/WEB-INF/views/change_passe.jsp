
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>Login</title>
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

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>
<body>




	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">

				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Changer Mot de Passe</h3>
						<input type="hidden" id="pass-path"
			value="${pageContext.request.contextPath}">
						
					</div>
					<div class="panel-body">
						<f:form action="${pageContext.request.contextPath}/kosu_connect/changer_mot_de_passe"  modelAttribute="pass" id="pass-form" method="post">

							<div class="form-group">
								<f:input type="password" path="mdp" id="myPassword" class="form-control"
									placeholder="Saisir un mot de passe" /> <label
									class="label label-danger" id="pass-error"></label>
							</div>


							<div class="form-group">
								<f:input type="password" path="confirm" id="confirm" class="form-control"
									placeholder="confirmer mot de passe"/> <label
									class="label label-danger" id="confirm-error"></label>

							</div>

							<input type="submit" value="Entrer" class="btn  btn-success">
						</f:form>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/sb-admin-2.js"></script>
	<!--	<script
		src="<%=request.getContextPath()%>/resources/js/model/ajax.js"></script>
	 <script type="text/javascript">
		$(document).ready(function($) {
			$("#pass-form").submit(function(e) {
				e.preventDefault();
				changePass();
			})
		})
	</script> -->
</body>
</html>
