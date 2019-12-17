<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="sc"
	uri="http://www.springframework.org/security/tags"%>
<t:template>

	<div class="box box-primary collapsed-box box-solid">
		<div class="box-header with-border">
			<h3 class="box-title">
				<span class="glyphicon glyphicon-filter"></span> Options
			</h3>

			<div class="box-tools pull-right">
				Détails<button type="button" class="btn btn-box-tool"
					data-widget="collapse">
					<i class="fa fa-plus"></i>
				</button>
			</div>
			<!-- /.box-tools -->
		</div>
		<!-- /.box-header -->
		<div class="box-body">
			<div class="well">

				<div class="panel-body">
					<form action="" class="sandbox-form " id="filtre_form"
						name="filtre_form">
						<div class="form-group">
							<label>Production :</label> <label class="radio-inline">
								<input type="radio" name="tempsFiltre" id="inlineRadio1"
								value="1"> Du Jour
							</label> <label class="radio-inline"> <input type="radio"
								name="tempsFiltre" id="temps_filtre1" value="2"> Du Mois
							</label> <label class="radio-inline"> <input type="radio"
								name="tempsFiltre" id="temps_filtre2" value="3"> De la
								Periode 
							</label> <label class="radio-inline"> <input type="radio"
								name="tempsFiltre" id="temps_filtre3" value="4"> De
								l'Anneé
							</label>
						</div>
					</form>
					<form action="" id="filtre_jour_form" class="sandbox-form ">

						<div class="form-group" id="filtre_jours">
							<label for="date"> Date <input
								class="span2 col-md-2 form-control" name="filtre_jour"
								type="text" id='filtre_jour_id' placeholder="jj/mm/aaaa">
							</label> <label for="filtre_jours_segment"> Segment <select
								class="span2 col-md-1 form-control" name="filtre_jours_segment"
								id='filtre_jours_segment'>
							</select>
							</label>
						</div>
					</form>
					<form action="" id="filtre_mois_form" class="sandbox-form ">
						<div class="form-group" id='filtre_mois'>
							<label for="date"> Mois <input
								class="span2 col-md-2 form-control" name="select_mois"
								type="text" id='select_mois' placeholder="mm/aaaa">
							</label> <label for="filtre_mois_segment"> Segment <select
								class="span2 col-md-1 form-control" name="filtre_mois_segment"
								id='filtre_mois_segment'>
							</select>
							</label>
						</div>
					</form>
					<form action="" id="choix_periode_form">
						<div class="form-group" id="filtre_periode">
							<label for="choix_periode"> Période </label>
							<div class="input-daterange input-group col-md-8"
								id="choix_periode">
								<label for="choix_debut"> Début : <input
									class="span2 col-md-2 form-control" name="choix_debut"
									type="text" id='choix_debut' placeholder="jj/mm/aaaa"></label>
								<label for="choix_fin" style="margin-left: 20px"> Fin :
									<input class="span2 col-md-2 form-control" name="choix_fins"
									type="text" id='choix_fin' placeholder="jj/mm/aaaa">
								</label>
							</div>
							<div class="col_md-4">
								<label for="filtre_periode_segment"> Segment <select
									class="span2 col-md-1 form-control"
									name="filtre_periode_segment" id='filtre_periode_segment'>
								</select>
								</label>
							</div>
						</div>
					</form>
					<form action="" id="filtre_annee_form" class="sandbox-form ">
						<div class="form-group" id='filtre_annee'>
							<label for=select_annee> Année <input
								class="span2 col-md-2 form-control" name="select_annee"
								type="text" id='select_annee' placeholder="aaaa">
							</label> <label for="filtre_annee_segment"> Segment <select
								class="span2 col-md-1 form-control" name="filtre_annee_segment"
								id='filtre_annee_segment'>
							</select>
							</label>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div></div>
	</div>
	<!-- /.box-body -->
	<!-- /.box -->

	<!-- /.col -->

	<div class="container-fluid">
		<div class="row">
			<div class="card teal col-xs-6 col-md-9">
				<div class="chart-container" id="production_container">
					<canvas id="results-graph"></canvas>
				</div>
				<div class="card-action text-center" >
				<h4> <strong id="titre_prodcution" class="text-center"></strong></h4>

				</div>

			</div>
		</div>
		<hr class="divider" />
		<div class="row">
			<div class="card teal col-xs-6 col-md-9">
				<div class="chart-container" id="pie_production_container">
					<canvas id="pie_results-graph"></canvas>
				</div>
				<div class="card-action  text-center">
					<h4><strong  id="titre_pie"></strong></h4>
				</div>

			</div>
		</div>
	</div>
	<hr class="divider" />
	<div class="container-fluid">
		<div class="row">
			<div class="card teal col-xs-6 col-md-8">
				<div class="chart-container" id="arret_container">
					<canvas id="arretResults-graph"></canvas>
				</div>
				<div class="card-action  text-center">
					<h4><strong  id="titre_arret"></strong></h4>
				</div>

			</div>
		</div>
	</div>
</t:template>