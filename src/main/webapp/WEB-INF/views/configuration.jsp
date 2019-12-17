<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="sc"
	uri="http://www.springframework.org/security/tags"%>
<t:template>
	<div class="col-md-6">
		<div class="box box-success collapsed-box box-solid">
			<div class="box-header with-border">
				<h3 class="box-title">
					<span class="fa fa-tasks fa-fw"></span>Gestion Escalation
				</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-plus"></i>
					</button>
				</div>
				<!-- /.box-tools -->
			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<div id="affichage_escalation">
					<div class="navbar navbar-warning">
						<button data-toggle="tooltip" onclick="show_edit_escalation()"
							data-placement="top"
							title="Modifier la configuration d'escalation"
							class="btn btn-success pull-left">
							<span class="fa fa-pencil"></span>
						</button>
					</div>
					<hr class="divider" />
					<fieldset>
						<legend>Temps Envoie sms</legend>
						<div class="form-group">
							<label>Chef chaine :</label> <span class="pull-right text-muted"><em
								id="current_escale1"></em> </span>
						</div>
						<hr class="divider" />
						<div class="form-group">
							<label>Contre maître:</label> <span
								class="pull-right text-muted"><em id="current_escale2"></em>
							</span>
						</div>
						<hr class="divider" />
						<div class="form-group">
							<label>Chef segment:</label> <span class="pull-right text-muted"><em
								id="current_escale3"></em> </span>
						</div>
						<hr class="divider" />
						<div class="form-group">
							<label>Chef Unité de Production:</label> <span
								class="pull-right text-muted"><em id="current_escale4"></em>
							</span>
						</div>
					</fieldset>
				</div>
			</div>

			<div id="modification_escalation">
				<f:form action="/kosu_connect/admin/createEscalation" method="post"
					modelAttribute="escalation" id="edit_ecalation">
					<fieldset class="form-group">
						<legend>Temps Envoie sms</legend>
						<div class="form-group">
							<label for="escale1">Chef chaine</label>
							<f:input path="escale1" id="escale1" class="form-control"
								type="text" />
						</div>
						<div class="form-group">
							<label for="escale2">Contre maître</label>
							<f:input path="escale2" id="escale2" class="form-control"
								type="text" placeholder="2ème Escalation en minute" />
						</div>
						<div class="form-group">
							<label for="escale3">Chef Segment</label>
							<f:input path="escale3" id="escale3" class="form-control"
								type="text" placeholder="3ème Escalation en minute" />
						</div>
						<div class="form-group">
							<label for="escale4">Chef Unité de Production</label>
							<f:input path="escale4" id="escale4" class="form-control"
								type="text" placeholder="Te en minute" />
						</div>
					</fieldset>
					<div class="form-group">
						<button type="submit" class="btn btn-success">Save</button>
						<button type="reset" onclick="hid_edit_escalation()" class="btn btn-danger pull-right">
							Cancel</button>
					</div>

				</f:form>
			</div>
		</div>
		<!-- /.box-body -->
	</div>
	<!-- /.box -->

	<!-- /.col -->

	<div class="col-md-6">
		<div class="box box-warning collapsed-box box-solid">
			<div class="box-header with-border">
				<h3 class="box-title">
					<span class="fa fa-gear fa-fw"></span>Gestion Unité de Production
				</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-plus"></i>
					</button>
				</div>
				<!-- /.box-tools -->
			</div>
			<!-- Pent Section -->
			<div class="box-body">
				<div class="form-group">
					<button data-toggle="tooltip" data-placement="left"
						title="Ajouter une unité de production"
						onclick="showAjoutPlentSectionForm()"
						class="btn btn-success pull-left">
						<span class="fa fa-plus"></span>
					</button>
					<button data-toggle="tooltip" data-placement="top"
						title="Modifier une unité de production" style="margin-left: 10px"
						class="btn btn-warning pull-left">
						<span class="fa fa-pencil"></span>
					</button>
					<button data-toggle="tooltip" data-placement="right"
						title="Suprimer une unité de production" style="margin-left: 10px"
						class="btn btn-danger pull-left">
						<span class="fa fa-trash-o"></span>
					</button>


				</div>
				<hr class="divider" />
				<div class="alert alert-success alert-dismissable"
					id="createPlentSection_success">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
				</div>
				<div id="addPentSection">

					<f:form id="addPentSectionForm" modelAttribute="plantSection"
						action="/kosu_connect/admin/configuration" method="post">
						<fieldset class="form-group">
							<label for="nom_plentSection">Nom: </label>
							<f:input path="nom" type="text" id="nom_plentSection"
								placeholder="nom" class="form-control" />
						</fieldset>
						<fieldset class="form-group">
							<label for="entreprise_plentSection">Entreprise: </label>
							<f:input path="entreprise" type="text"
								id="entreprise_plentSection" placeholder="Entreprise"
								class="form-control" />
						</fieldset>
						<fieldset class="form-group">
							<label for="chef_plentSection">Chef Unité de Production: </label> <select
								title="selectionner le chef unité"
								name="chefPlentSection" id="chefPlentSectionId"
								class="selectpicker form-control">
								<option value="">Selectioner ....</option>
								<c:forEach items="${users }" var="chef">
									<option value="${chef.idUser }">${chef.firstName }&nbsp;
										${chef.lastName }</option>
								</c:forEach>
							</select>
						</fieldset>
						<div class="form-group">
							<button class="btn btn-success" id="savePlentSection"
								type="submit">Save</button>
							<button class="btn btn-danger pull-right" type="reset"
								onclick="hideAddPlentSectionForm()">Cancel</button>
						</div>
					</f:form>
				</div>
				<hr class="divider">

				<div class="form-group">
					<table
						class="table table-striped table-bordered table-responsive table-hover nowrap"
						id="plentSection">
						<thead>
							<tr>
								<th>Désignation</th>
								<th>Entreprise</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>




	<!--  Segment -->
	<div class="col-md-6">
		<div class="box box-primary collapsed-box box-solid">
			<div class="box-header with-border">
				<h3 class="box-title">
					<span class="fa fa-gear fa-fw"></span>Gestion Segment
				</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-plus"></i>
					</button>
				</div>
				<!-- /.box-tools -->
			</div>
			<div class="box-body">
				<div class="form-group">
					<button data-toggle="tooltip" data-placement="left"
						title="Ajouter un segment" onclick="showAddSegmentForm()"
						class="btn btn-success pull-left">
						<span class="fa fa-plus"></span>
					</button>
					<button data-toggle="tooltip" data-placement="top"
						title="Modifier un segment" style="margin-left: 10px"
						class="btn btn-warning pull-left">
						<span class="fa fa-pencil"></span>
					</button>
					<button data-toggle="tooltip" data-placement="right"
						title="Suprimer un segment" style="margin-left: 10px"
						class="btn btn-danger pull-left">
						<span class="fa fa-trash-o"></span>
					</button>
				</div>

				<hr class="divider" />
				<div id="addSegment">
					<div class="alert alert-success alert-dismissable"
						id="createSegment_success">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					</div>
					<f:form id="addSegmentForm" modelAttribute="segment"
						action="/kosu_connect/admin/configuration" method="post">
						<fieldset>
							<legend> Info Segment</legend>
							<div class="form-group">
								<label for="designation_segment">Designation : </label>
								<f:input path="nom" type="text" id="designation_segment"
									placeholder="Designation" class="form-control" />
							</div>
							<div class="form-group">
								<label for="plentSectionSegmentId">Unité de Production: </label> <select
									name="plentSectionSegment" id="plentSectionSegmentId"
									class="selectpicker form-control">
									<option value="">Selectioner ....</option>
									<c:forEach items="${plantSections }" var="p">
										<option value="${p.id }">${p.nom}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="chef_segment">Chef Segment</label> <select
									name="chef_segment" id="chef_segment"
									class="selectpicker form-control">
									<option value="">Selectioner ....</option>
									<c:forEach items="${users }" var="chef">
										<option value="${chef.idUser }">${chef.firstName }&nbsp;
											${chef.lastName }</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<hr class="divider">

						<fieldset>
							<legend> Info Post matin</legend>
							<div class="form-group">
								<label for="description_PM">Description :</label> <input
									type="text" class="form-control" name="description_PM"
									id="description_PM" />
							</div>
							<div class="form-group">
								<label for="chef_plentSection">Contre Maître PM :</label> <select
									name="chefPM" id="chefPMId" class="selectpicker form-control">
									<option value="">Selectioner ....</option>
									<c:forEach items="${users }" var="chef">
										<option value="${chef.idUser }">${chef.firstName }&nbsp;
											${chef.lastName }</option>
									</c:forEach>
								</select>
							</div>

						</fieldset>
						<hr class="divider">

						<fieldset>

							<legend> Info Post Après-midi</legend>
							<div class="form-group">
								<label for="description_PA">Description :</label> <input
									type="text" class="form-control" name="description_PA"
									id="description_PA" />
							</div>
							<div class="form-group">
								<label for="chefPA">Contre Maître PA :</label> <select
									name="chefPA" id="chefPAId" class="selectpicker form-control">
									<option value="">Selectioner ....</option>
									<c:forEach items="${users }" var="chef">
										<option value="${chef.idUser }">${chef.firstName }&nbsp;
											${chef.lastName }</option>
									</c:forEach>
								</select>
							</div>

						</fieldset>
						<hr class="divider" />
						<div class="form-group">
							<button class="btn btn-success" id="saveSegment" type="submit">Save</button>
							<button class="btn btn-danger pull-right"
								onclick="hideAddSegmentForm()" id="AnnulerSegment" type="reset">Annuler</button>

						</div>
					</f:form>
				</div>
				<hr class="divider">

				<div class="form-group">
					<table
						class="table table-striped table-bordered table-responsive table-hover nowrap"
						id="table_segment">
						<thead>
							<tr>
								<th>Désignation</th>
								<th>Nombre de Post</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>

	<!--  Chaine -->
	<div class="col-md-6">
		<div class="box box-danger collapsed-box box-solid">
			<div class="box-header with-border">
				<h3 class="box-title">
					<span class="fa fa-gear fa-fw"></span>Gestion Chaine
				</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-plus"></i>
					</button>
				</div>
				<!-- /.box-tools -->
			</div>
			<div class="box-body">
				<div class="form-group">
					<button onclick="showAddChaineForm()" data-toggle="tooltip"
						data-placement="left" title="Ajouter une chaine"
						class="btn btn-success pull-left">
						<span class="fa fa-plus"></span>
					</button>
					<button data-toggle="tooltip" data-placement="top"
						title="Modifier une chaine" style="margin-left: 10px"
						class="btn btn-warning pull-left">
						<span class="fa fa-pencil"></span>
					</button>
					<button data-toggle="tooltip" data-placement="right"
						title="Suprimer une chaine" style="margin-left: 10px;"
						class="btn btn-danger pull-left">
						<span class="fa fa-trash-o"></span>
					</button>

				</div>
				<hr class="divider" />
				<div id="addChaine">
					<div class="alert alert-success alert-dismissable"
						id="createChaine_success">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
					</div>
					<f:form id="addChaineForm" modelAttribute="chaine"
						action="/kosu_connect/admin/configuration" method="post">
						<fieldset>
							<legend>Info chaine</legend>

							<div class="form-group">
								<label for="nom_chaine">Nom: </label>
								<f:input path="nom" type="text" id="nom_chaine"
									placeholder="nom" class="form-control" />
							</div>
							<div class="form-group">
								<label for="description_chaine">Description: </label>
								<f:input path="description" type="text" id="description_chaine"
									placeholder="Description" class="form-control" />
							</div>
							<div class="form-group">
								<label for="nombrePostTravail_chaine">Nombre de Post de
									Travail: </label>
								<f:input path="nombrePostTravail" id="nombrePostTravail_chaine"
									placeholder="Nombre de Post de Travail" class="form-control" />
							</div>
						</fieldset>
						<hr class="divider">
						<fieldset>
							<legend>Info Segment</legend>
							<div class="form-group">
								<label for="segment_chaine">Segment : </label> <select
									name="segmentChaine" id="segmentChaineId"
									class="selectpicker form-control">
									<option value="">Selectioner ....</option>
									<c:forEach items="${segments }" var="segment">
										<option value="${segment.idSegment }">${segment.nom}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<hr class="divider">
						<fieldset>
							<legend>Info chef chaine</legend>
							<div class="form-group">
								<label for="chef_chaine">Chef Chaine : </label> <select
									name="chefChaine" id="chefChaineId"
									class="selectpicker form-control">
									<option value="">Selectioner ....</option>
									<c:forEach items="${users }" var="chef">
										<option value="${chef.idUser }">${chef.firstName }&nbsp;
											${chef.lastName }</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>

						<div class="form-group">
							<button class="btn btn-success" id="savePlentSection"
								type="submit">Save</button>
							<button class="btn btn-danger pull-right"
								id="CancelSavePlentSection" type="reset"
								onclick="hideAddChaineForm()">Cancel</button>
						</div>
					</f:form>
				</div>

				<hr class="divider" />
				<div class="form-group">
					<table
						class="table table-striped table-bordered table-responsive table-hover nowrap"
						id="table_chaine">
						<thead>
							<tr>
								<th>Désignation</th>
								<th>Nombre de Post</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>

</t:template>
