<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="sc"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:template>



	<div class="container">
		<div class="row">
<input type="hidden" value="${pageContext.request.contextPath}" id="root"/>
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


				<div class="panel panel-default">
					<div class="panel-heading" >
						<span class="pull-right " title="Editer info personnelle" data-placement="right" data-toggle="tooltip"> <a data-toggle="modal" data-target="#epmodal"
							
							type="button" class="btn btn-sm btn-warning"><i
								class="glyphicon glyphicon-edit"></i></a>
						</span>
						<h3 class="panel-title">${current.firstName }&nbsp;
							${current.lastName }</h3>

					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 profile-avatar-wrap " id="drop-area" align="center">

								<c:if test="${current.photo !=null}">
									<img alt="User Pic" id="profile-avatar" class="img-circle img-responsive"
										src="${pageContext.request.contextPath}/kosu_connect/admin/loadUserProfile/${current.idUser}" />

								</c:if>
								<c:if test="${current.photo==null}">
									<c:if test="${current.sexe=='Masculin' }">
										<img alt="User Pic" id="profile-avatar" class="img-circle img-responsive"
											src="${pageContext.request.contextPath}/resources/images/male.png" />
									</c:if>
								</c:if>
								<c:if test="${current.photo==null}">
									<c:if test="${current.sexe=='Féminin' }">
										<img alt="User Pic" id="profile-avatar" class="img-circle img-responsive"
											src="${pageContext.request.contextPath}/resources/images/female.png" />
									</c:if>
								</c:if>

								<input type="file" name="file" id="uploader"/>
							</div>
						</div>

				
						<div class=" col-md-9 col-lg-9 ">
							<table class="table table-user-information">
								<tbody>
									<tr>
										<td>Nom d'utilisateur:</td>
										<td>${current.userName }</td>

									</tr>
									<tr>
										<td>Date d'engagement :</td>
										<td>06/23/2013</td>
									</tr>
									<tr>
										<td>Date de Naissance</td>
										<td>
										<fmt:formatDate value="${current.dateDeNaissance}" pattern="dd/MM/yyyy" /></td>
									</tr>

									<tr>
									<tr>
										<td>Sexe</td>
										<td>${current.sexe }</td>
									</tr>
									<tr>
										<td>Adresse domicile</td>
										<td>${current.adresse }</td>
									</tr>
									<tr>
										<td>Email</td>
										<td><a href="${adresseMail }">${current.adresseMail }</a></td>
									</tr>

								</tbody>
							</table>


						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- /.modal -->

	<div id="epmodal" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Changer Image de profile</h4>
				</div>
				<div class="modal-body">

					<div class="profile">

						<div class="profile-avatar-wrap">
							<c:if test="${current.photo !=null}">
								<img alt="User Pic" id="profile-avatar"
									src="${pageContext.request.contextPath}/kosu_connect/admin/loadUserProfile/${current.idUser}" />

							</c:if>
							<c:if test="${current.photo==null}">
								<c:if test="${current.sexe=='Masculin' }">
									<img alt="User Pic" id="profile-avatar"
										src="${pageContext.request.contextPath}/resources/images/male.png" />
								</c:if>
							</c:if>
							<c:if test="${current.photo==null}">
								<c:if test="${current.sexe=='Féminin' }">
									<img id="profile-avatar"
										src="${pageContext.request.contextPath}/resources/images/female.png" />
								</c:if>
							</c:if>
						</div>
					</div>

					<input type="file" id="uploader">
				</div>
				<div class="modal-footer">
					<a type="button" class="btn btn-success" onclick=""><i
						class="fa fa-pencil"></i>Edit</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</t:template>