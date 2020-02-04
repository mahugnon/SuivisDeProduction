/**
 * @author Honoré Houekpetodji Created on 1/11/2016.
 */

// Begin Something modal population and submit functions
var url = $('#path').val() + "/kosu_connect/admin/";
var editModalTarget = url + "loadUser/";
var tableTarget = url + "loadUserTable/";
var role = '';
var imagResize = null;
var indicateur = '';

// Build the url for the Ajax request for Something.
function showEditModal(index) {
	console.log(index);
	var editUrl = $('#path').val() + "/kosu_connect/admin/loadUser/" + index;
	loadEntity(editUrl);
}

function showDeleteModal(index) {
	console.log(index);
	$('#delete-id').val(index);
}

// Ajax request for Something to populate the modal form.
function loadEntity(url) {
	$.getJSON(url, {}, function(data) {
		
		populateModal(data);
	});
}

// Assign the data values to the modal form.
function populateModal(data) {
	$('#update-id').val(data.idUser);
	$('#update-userName').val(data.userName);
	$('#update-firstName').val(data.firstName);
	$('#update-lastName').val(data.lastName);
	$('#update-telephone').val(data.telephone);
	var d = new Date(data.dateDeNaissance);
	var today_date = ("0" + d.getDate()).slice(-2) + "/"
			+ ("0" + (d.getMonth() + 1)).slice(-2) + "/" + d.getFullYear();
	$('#update-dateNaissance').val(today_date);
	$('#update-email').val(data.adresseMail);
	if (data.sexe == 'Masculin') {
		$('#edit-form').find(':radio[name=sexe][value="Masculin"]').prop(
				'checked', true);
	} else {
		$('#edit-form').find(':radio[name=sexe][value="Féminin"]').prop(
				'checked', true);
	}
	$('#update-adresse').val(data.adresse);
	$.each(data.roles, function(k, r) {
		if (r.roleName == "SUPER_ADMIN") {

			// $("#update-superAdmin").prop("checked", 1);
			$('#edit-form').find(':radio[name=roles][value="1"]').prop(
					'checked', true);

		}
		if (r.roleName == "ADMIN") {

			$('#edit-form').find(':radio[name=roles][value="2"]').prop(
					'checked', true);
		}
		if (r.roleName == "CONSULTANT") {

			$('#edit-form').find(':radio[name=roles][value="3"]').prop(
					'checked', true);
		}

	});

}

function clearModal() {
	$('#update-id').val('');
	$('#update-firstName').val('');
	$('#update-lastName').val('');
	$('#update-userName').val('');
	$('#update-dateNaissance').val('');
	$('#update-adresseMail').val('');
	$('#update-telephone').val('');
	$('#edit-form').find(':radio[name=sexe][value="Masculin"]').prop('checked',
			false);
	$('#edit-form').find(':radio[name=sexe][value="Féminin"]').prop('checked',
			false);
	$('#update-adresse').val('');
	$("#update-consultant").prop("checked", 0);
	$("#update-admin").prop("checked", 0);
	$("#update-superAdmin").prop("checked", 0);

}

function closeModal(name) {
	$(name).modal('toggle');
}

function clearAndCloseModal(name) {
	clearModal();
	closeModal(name);
}

// POST the edits to Something to the server.
function postEdit() {
	// Get form
	var form = $('#edit-form')[0];
	// Create an FormData object
	var data = new FormData(form);
	
	var editUrl = $('#path').val() + "/kosu_connect/admin/update";
	$.ajax({
		type : "POST",
		enctype : 'multipart/form-data',
		url : editUrl,
		data : data,
		processData : false,
		contentType : false,
		cache : false,
		timeout : 600000,
		success : function(data) {

			$("#create-success").html("modifications effectuer avec succes");
			$("#create-success").show();
			setTimeout(() => {
				$("#create-success").hide("fade",{},5000);	
			}, 300);
			var table = $('#user-table').DataTable();

			table.ajax.url('/production/kosu_connect/admin/loadUserTable')
					.load();

		},
		error : function(e) {

			console.log("ERROR : ", e);

		}
	});
	clearAndCloseModal('#umodal');
}

// POST the new to user to the server.
function postCreate() {

	
		// Get form
		var form = $('#create-form')[0];
		// Create an FormData object
		var data = new FormData(form);
		
		var createUrl = $('#path').val() + "/kosu_connect/admin/create";
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : createUrl,
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success : function(data) {

				console.log("SUCCESS : ", data);
				$("#create-success").html("Ajout effectuer avec succes");
				$("#create-success").show();
				setTimeout(() => {
					$("#create-success").hide("fade",{},5000);
				}, 300);
				var table = $('#user-table').DataTable();

				table.ajax.url('/production/kosu_connect/admin/loadUserTable')
						.load();

			},
			error : function(e) {

				console.log("ERROR : ", e);

			}
		});

		clearAndCloseModal('#cmodal');

	

}

ShowMethodSucceeded = function(data) {
	if (data == null) {
		return "";
	} else {
		return data;
	}
}

function deleteEntity() {
	var input = $('#delete-id');
	var url = $('#path').val() + '/kosu_connect/admin/delete/' + input.val();
	console.log(input.val());
	$.post(url, {}, function(data) {
		var table = $('#user-table').DataTable();

		table.ajax.url('/production/kosu_connect/admin/loadUserTable').load();
	});
	closeModal('#dmodal');
	input.val('');
	console.log('succes');

}

function updateTable() {
	$('#user-table')
			.DataTable(
					{
						'ajax' : {
							"dataType" : 'json',
							"contentType" : "application/json; charset=utf-8",
							"type" : "POST",
							"url" : '/production/kosu_connect/admin/loadUserTable',
							"dataSrc" : function(data) {
								return data;
							}
						},
						"info" : false,
						"lengthChange" : false,
						"language" : {
							"url" : "//cdn.datatables.net/plug-ins/1.10.15/i18n/French.json"
						},
						"pageLength" : 5,
						"pagingType" : "full_numbers",
						"columns" : [
								{
									data : null,
									render : function(data, type, row) {
										// Image
										var image = ""
										if (data.photo == null) {
											if (data.sexe == "Masculin") {
												image = '<img class="image_user" src="'
														+ $('#path').val()
														+ '/resources/images/male.png">';

											} else {
												image = '<img class="image_user" src="'
														+ $('#path').val()
														+ '/resources/images/female.png">';

											}

										} else {
											image = '<img class="img-circle image_user"  id="image_user" src="data:image/png;base64,'
													+ data.photo + '"/>';
										}
										return image;
									}
								},
								{
									"data" : "firstName",
									"orderable" : true
								},
								{
									"data" : "lastName",
									"orderable" : true
								},
								
								{
									"data" : "adresseMail",
									"orderable" : true
								},
								{
									"data" : "roles.0.roleName",
									"orderable" : true
								},
								{"data":"telephone"},
								{
									data : null,
									orderable : false,
									render : function(data, type, row) {
										var end = data.idUser + ");'";
										var edit = "'showEditModal(" + end;
										var roleAuth= $("#roleAuth").val();
										var dataRole=data.roles[0].roleName;
										if (roleAuth===("ADMIN")&& dataRole===("SUPER_ADMIN")) {
											return "<button type='button' data-toggle='modal'class='btn btn-success' disabled data-target='#umodal' onclick="
											+ edit
											+ "><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>Edit</button>"
	
										}
										return "<a data-toggle='modal'class='btn btn-success' data-target='#umodal' onclick="
												+ edit
												+ "><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>Edit</a>"
									}
								},
								{
									data : null,
									orderable : false,
									render : function(data, type, row) {
										var idAuth = $("#idUser").val();
										var roleAuth= $("#roleAuth").val();
										var dataRole=data.roles[0].roleName;
										var end = data.idUser + ");'";
										var del = "'showDeleteModal(" + end;
										if (data.idUser == idAuth) {
											return "<button type='button' data-toggle='modal' class='btn btn-danger' disabled data-target='#dmodal' onclick="
											+ del
											+ "><span class='glyphicon glyphicon-trash' aria-hidden='true'></span>Delete</button>"

										}else if (roleAuth===("ADMIN")&& dataRole===("SUPER_ADMIN")) {
											return "<button type='button' data-toggle='modal' class='btn btn-danger' disabled data-target='#dmodal' onclick="
											+ del
											+ "><span class='glyphicon glyphicon-trash' aria-hidden='true'></span>Delete</button>"

										}else{
											return "<a data-toggle='modal' class='btn btn-danger' data-target='#dmodal' onclick="
											+ del
											+ "><span class='glyphicon glyphicon-trash' aria-hidden='true'></span>Delete</a>"
										}
										
									}
								} ]
					});
}
function showRole() {
	$("#role").hide("fade")
	$('#create-roles').slideDown();
}
function showUpdateRole() {
	$("#update_role").hide("fade");
	$('#update-roles').slideDown();
}


function changePass() {
	var successUrl = "login.jsp"; // might be a good idea to return this URL
	// in the successful AJAX call
	var pass = $("#myPassword").val();
	var confirme = $("#confirm").val();
	var data = $("#pass-form").serialize();
	if (pass.length < 9) {
		$("#pass-error").html(
				"Le mot de passe doit contenir au moins 9 caractère");
	} else if (pass != null && pass != confirme) {
		$("#confirm-error").html("La confirmation ne correspond pas");

	} else {
		var url = $('#pass-path').val() + "/kosu_connect/changer_mot_de_passe";
		$.post(url, data, function(response) {
			window.location.href = successUrl;
		})
	}

}
// Diminuer la taille de l'image
$("#create-file").on('change', function(event) {
	var files = event.target.files;
	var file = files[0];
	if (!file.type.match('image.*')) {
		$("#file-create-error").html("le ficher doit être une image")
	} else if (typeof file !== 'undefined' && file.type.match('image.*')) {
		$("#file-create-error").hide();
		resizeImage(file, 256, function(data) {
			placeImage(data)
		});
	}
});

$("#update-file").on('change', function(event) {
	var files = event.target.files;
	var file = files[0];
	if (!file.type.match('image.*')) {
		$("#file-update-error").html("le ficher doit être une image")
	} else if (typeof file !== 'undefined' && file.type.match('image.*')) {
		$("#file-update-error").hide();
		resizeImage(file, 256, function(data) {
			placeImage(data)
		});
	}
});
// reduire l'image
function resizeImage(file, size, callback) {

	var fileTracker = new FileReader;
	fileTracker.onload = function() {
		Resample(this.result, size, size, callback);
	}
	fileTracker.readAsDataURL(file);

	fileTracker.onabort = function() {
		alert("The upload was aborted.");
	}
	fileTracker.onerror = function() {
		alert("An error occured while reading the file.");
	}

}

// Methode exécuter après chargement de toutes image par input type file après
// reduction de l'image puis save.
function placeImage(data) {
	var binary = atob(data.split(',')[1]);
	var array = [];
	for (var i = 0; i < binary.length; i++) {
		array.push(binary.charCodeAt(i));
	}
	imagResize = new Blob([ new Uint8Array(array) ], {
		type : 'image/jpeg'
	});
	var xhr = new XMLHttpRequest();
	var fd = new FormData();

	fd.append('file', imagResize);

	xhr.open('POST', $('#path').val() + "/kosu_connect/admin/postPhoto", true);
	xhr.send(fd);

}
// Scripte après reduiction la taille d'image après drag and drop puis
// enregistrer dans la base de donnée.
function changeImage(data) {
	var binary = atob(data.split(',')[1]);
	var array = [];
	for (var i = 0; i < binary.length; i++) {
		array.push(binary.charCodeAt(i));
	}
	imagResize = new Blob([ new Uint8Array(array) ], {
		type : 'image/jpeg'
	});
	var xhr = new XMLHttpRequest();
	var fd = new FormData();

	fd.append('file', imagResize);

	xhr.open('POST', $('#root').val() + "/kosu_connect/admin/modifierPhoto",
			true);
	xhr.send(fd);
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			$("#profile-avatar").attr("src", data);

		}
	}

}

// Script pour sortir les calendriers
$('#update-dateNaissance').datepicker({
	format : "dd/mm/yyyy",
	clearBtn : true,
	language : "fr",
	daysOfWeekHighlighted : "0,6",
	calendarWeeks : true,
	endDate : 'today',
	autoclose : true,
	todayHighlight : true,
	beforeShowDay : function(date) {
		if (date.getMonth() == (new Date()).getMonth())
			switch (date.getDate()) {
			case 4:
				return {
					tooltip : 'Example tooltip',
					classes : 'active'
				};
			case 8:
				return false;
			case 12:
				return "green";
			}
	},
	beforeShowMonth : function(date) {
		if (date.getMonth() == 8) {
			return false;
		}
	},
	beforeShowYear : function(date) {
		if (date.getFullYear() == 2007) {
			return false;
		}
	}
});
$('#create-dateNaissance').datepicker({
	format : "dd/mm/yyyy",
	clearBtn : true,
	language : "fr",
	daysOfWeekHighlighted : "0,6",
	calendarWeeks : true,
	endDate : 'today',
	autoclose : true,
	todayHighlight : true,
	beforeShowDay : function(date) {
		if (date.getMonth() == (new Date()).getMonth())
			switch (date.getDate()) {
			case 4:
				return {
					tooltip : 'Example tooltip',
					classes : 'active'
				};
			case 8:
				return false;
			case 12:
				return "green";
			}
	},
	beforeShowMonth : function(date) {
		if (date.getMonth() == 8) {
			return false;
		}
	},
	beforeShowYear : function(date) {
		if (date.getFullYear() == 2007) {
			return false;
		}
	}
});
// Methode Ajax pour remplir la table des messages reçu
function updateMessageTable() {
	var table = $('#boite-message')
			.DataTable(
					{
						'ajax' : {
							"dataType" : 'json',
							"contentType" : "application/json; charset=utf-8",
							"type" : "POST",
							"url" : '/production/kosu_connect/admin/loadMessagesTable',
							"dataSrc" : function(data) {
								return data;
							}
						},
						"info" : false,
						"lengthChange" : false,
						"language" : {
							"url" : "//cdn.datatables.net/plug-ins/1.10.15/i18n/French.json"
						},
						"pageLength" : 5,
						"rowCallback" : function(row, data, index) {
							if (data.vu == false) {
								$(row).toggleClass('success');
							}
						},

						"columns" : [

								{
									data : null,
									orderable : false,
									render : function(data, type, row) {
										var emetteur = data.message.emetteur.firstName
												+ " "
												+ data.message.emetteur.lastName;

										return emetteur
									}
								},
								{
									"data" : "message.sujet",
									"orderable" : false
								},
								{
									data : null,
									render : function(data, type, row) {
										// get a moment based on the user input
										var offset = new Date()
												.getTimezoneOffset();
										// console.log(offset);
										var m = moment(data.message.date).utcOffset(
												offset);
										// calculate a duration of time passed
										// between now and that moment
										var now = moment();
									
										var d = moment.duration({
											from : m,
											to : now
										});
										if (d.years() != 0) {
											if (d.years() == 1) {
												return "<h6><small class='text-muted'>il y a "
														+ d.years()
														+ " an</small><h6>"
											} else {
												return "<h6><small class='text-muted'>il y a "
														+ d.years()
														+ " ans</small><h6>"
											}

										} else if (d.months() != 0) {
											return "<h6><small class='text-muted'>il y a "
													+ d.months()
													+ " mois</small><h6>"

										} else if (d.days() != 0) {
											if (d.days() == 1) {
												return "<h6><small class='text-muted'>il y a "
														+ d.days()
														+ " jour</small><h6>"

											} else {
												return "<h6><small class='text-muted'>il y a "
														+ d.days()
														+ " jours</small><h6>"

											}

										} else if (d.minutes() != 0) {
											if (d.minutes() == 1) {
												return "<h6><small class='text-muted'>il y a "
														+ d.minutes()
														+ " minute</small><h6>"

											} else {
												return "<h6><small class='text-muted'>il y a "
														+ d.minutes()
														+ " minutes</small><h6>"

											}
										} else {
											if (d.seconds() == 1) {
												return "<h6><small class='text-muted'>il y a "
														+ d.seconds()
														+ " second</small><h6>"

											} else {
												return "<h6><small class='text-muted'>il y a "
														+ d.seconds()
														+ " seconds</small><h6>"

											}
										}

									}
								},

								{
									data : null,
									orderable : false,
									render : function(data, type, row) {

										if (data.vu == false) {
											return indicateur = "<h6><span id='nouveau' class='label label-danger pull-right'>Nouveau</span></h6>";

										} else {
											return '';
										}
									}
								} ]
					});

	$('#boite-message tbody').on('click', 'tr', function() {
		data = table.row(this).data();
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).removeClass('success');
			$(this).addClass('selected');
		}
		
		editMassage(data);
	});
}

function editMassage(data) {
	var m = data;
	if (data.vu == false) {
		$.ajax({
			dataType : "json",
			url : '/production/kosu_connect/admin/edit/' + data.message.id,
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			type : 'POST',
			success : function(response) {
				
				$("#message_conteneur").html(response);
			}
		});
	}
	$("#recu").addClass('active');
	$('#envoye').removeClass("active");
	$("#message_conteneur").html(
			"<div class='well'><span style='font-weight:bold' class='pull-left'>"
					+ m.message.emetteur.userName + ":&nbsp;</span>"
					+ m.message.contenu + "</div>");
}

/*setInterval(() => {
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/countNonVu',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'POST',
		success : function(response) {
			
			if (response != 0) {
				$("#nouvMessages").html(response);
			}
		}
	});
}, 5000);*/
function nouvMessageCount() {
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/countNonVu',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'POST',
		success : function(response) {
			
			if (response != 0) {
				$("#nouvMessages").html(response);
			}
		}
	});
}


function sendMessage() {
	var data = $("#sendMessageForm").serialize();
	
	var url = '/production/kosu_connect/admin/sendMessage';
	$.post(url, data, function(data) {
		console.log("success")
	});
}

//show edit escalation
function show_edit_escalation() {
	$("#affichage_escalation").hide("drop",{direction: "left"},500);
	$("#modification_escalation").show("drop",{direction: "right"},500);
}function hid_edit_escalation() {
	$("#modification_escalation").hide("drop",{direction: "left"},500);
	$("#affichage_escalation").show("drop",{direction: "right"},500);
}

function loadEscalation() {
	var url="/production/kosu_connect/admin/loadEscalation"
	$.getJSON(url, {}, function(data) {
		$("#current_escale1").text(data.escale1+" minutes");
		$("#current_escale2").text(data.escale2+" minutes");
		$("#current_escale3").text(data.escale3+" minutes");
		$("#current_escale4").text(data.escale4+" minutes");

		$("#escale1").val(data.escale1);
		$("#escale2").val(data.escale2);
		$("#escale3").val(data.escale3);
		$("#escale4").val(data.escale4);


	});
	
}
function createEscalation(){
	var data=$("#edit_ecalation").serialize();
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/createEscalation',
		
		type : 'POST',
		data : data,
		success : function(response) {
			
			$("#modification_escalation").hide("drop",{direction: "left"});
			$("#affichage_escalation").show("drop",{direction: "right"});
			loadEscalation();
		}
	});
	
}
//plent section
function updatePlentSection(){
	var table=$("#plentSection")
	.DataTable({
		'ajax' : {
			"dataType" : 'json',
			"contentType" : "application/json; charset=utf-8",
			"type" : "POST",
			"url" : '/production/kosu_connect/admin/loadPlentSection',
			"dataSrc" : function(data) {
				return data;
			}
		},
		"info" : false,
		"lengthChange" : false,
		"language" : {
			"url" : "//cdn.datatables.net/plug-ins/1.10.15/i18n/French.json"
		},
		"pageLength" : 4,
		"columns" :[
			{"data":"nom"},
			{"data":"entreprise"}
		]
	});
}

function showAjoutPlentSectionForm(){
	$("#addPentSection").slideDown();

}
function savePlentSection(){
	var data=$("#addPentSectionForm").serialize();
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/createPentSection',
		
		type : 'POST',
		data : data,
		success : function(response) {
			var table = $('#plentSection').DataTable();

			table.ajax.url('/production/kosu_connect/admin/loadPlentSection')
					.load();
		}
		
	});
	clearSavePlentSectionForm();
}



function clearSavePlentSectionForm(){
	$("#nom_plentSection").val('');
	$("#entreprise_plentSection").val('');
	$('#chefPlentSectionId').prop('selectedIndex', 0);
	$("#addPentSection").slideUp();
}

//Segment
function loadSegment1(){
	
	var table=$("#table_segment")
	.DataTable({
		'ajax' : {
			"dataType" : 'json',
			"contentType" : "application/json; charset=utf-8",
			"type" : "POST",
			"url" : '/production/kosu_connect/admin/loadSegment',
			"dataSrc" : function(data) {
				return data;
			}
		},
		"info" : false,
		"lengthChange" : false,
		"language" : {
			"url" : "//cdn.datatables.net/plug-ins/1.10.15/i18n/French.json"
		},
		"pageLength" : 4,
		"columns" :[
			
			{"data":"idSegment"},
			{"data":"nom"}
		]
	});
}

function saveSegment(){
	var data=$("#addSegmentForm").serialize();
	
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/createSegment',
		
		type : 'POST',
		data : data,
		success : function(response) {
			hideAddSegmentForm();
			var table = $('#table_segment').DataTable();

			table.ajax.url('/production/kosu_connect/admin/loadSegment')
					.load();
		}
	});
}


function showAddSegmentForm(){
	$("#addSegment").slideDown();
}
function hideAddSegmentForm(){
	$("#addSegment").slideUp();
}
function loadChaine(){
	var table=$("#table_chaine")
	.DataTable({
		'ajax' : {
			"dataType" : 'json',
			"contentType" : "application/json; charset=utf-8",
			"type" : "POST",
			"url" : '/production/kosu_connect/admin/loadChaine',
			"dataSrc" : function(data) {
				return data;
			}
		},
		"info" : false,
		"lengthChange" : false,
		"language" : {
			"url" : "//cdn.datatables.net/plug-ins/1.10.15/i18n/French.json"
		},
		"pageLength" : 4,
		"columns" :[
			{"data":"nom"},
			{"data":"nombrePostTravail"}
			
		]
	});
	
}
function hideAddChaineForm(){
	$("#addChaineForm").slideUp();
}
function saveChaine(){
	var data=$("#addChaineForm").serialize();
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/createChaine',
		
		type : 'POST',
		data : data,
		success : function(response) {
			hideAddChaineForm();
			var table = $('#table_chaine').DataTable();

			table.ajax.url('/production/kosu_connect/admin/loadChaine')
					.load();
		}
	});
	
}
function showAddChaineForm(){
	$("#addChaineForm").slideDown();

}
function hideAddPlentSectionForm(){
	$("#addPentSection").hide();
}
function hideSendMessageSuccess(){
	$('#sendMessage_success').hide();
}

//selectionner le jour voulu
$('#filtre_jour_id').datepicker({
	format : "dd/mm/yyyy",
	clearBtn : true,
	language : "fr",
	daysOfWeekHighlighted : "0,6",
	calendarWeeks : true,
	endDate : 'today',
	autoclose : true,
	todayHighlight : true,
	beforeShowDay : function(date) {
		if (date.getMonth() == (new Date()).getMonth())
			switch (date.getDate()) {
			case 4:
				return {
					tooltip : 'Example tooltip',
					classes : 'active'
				};
			case 8:
				return false;
			case 12:
				return "green";
			}
	},
	beforeShowMonth : function(date) {
		if (date.getMonth() == 8) {
			return false;
		}
	},
	beforeShowYear : function(date) {
		if (date.getFullYear() == 2007) {
			return false;
		}
	}
});

//Selectionné le mois voulu

$("#select_mois").datepicker( {
    format: "mm/yyyy",
    viewMode: "months", 
    minViewMode: "months",
    language: "fr",
    autoclose: true

});

//Selectionné la période voulu
$('#choix_fin').datepicker({
	format : "dd/mm/yyyy",
	clearBtn : true,
	language : "fr",
	daysOfWeekHighlighted : "0,6",
	calendarWeeks : true,
	todayHighlight : true,
	beforeShowDay : function(date) {
		if (date.getMonth() == (new Date()).getMonth())
			switch (date.getDate()) {
			case 4:
				return {
					tooltip : 'Example tooltip',
					classes : 'active'
				};
			case 8:
				return false;
			case 12:
				return "green";
			}
	},
	beforeShowMonth : function(date) {
		if (date.getMonth() == 8) {
			return false;
		}
	},
	beforeShowYear : function(date) {
		if (date.getFullYear() == 2007) {
			return false;
		}
	}
});

$('#choix_debut').datepicker({
	format : "dd/mm/yyyy",
	clearBtn : true,
	language : "fr",
	daysOfWeekHighlighted : "0,6",
	calendarWeeks : true,
	todayHighlight : true,
	beforeShowDay : function(date) {
		if (date.getMonth() == (new Date()).getMonth())
			switch (date.getDate()) {
			case 4:
				return {
					tooltip : 'Example tooltip',
					classes : 'active'
				};
			case 8:
				return false;
			case 12:
				return "green";
			}
	},
	beforeShowMonth : function(date) {
		if (date.getMonth() == 8) {
			return false;
		}
	},
	beforeShowYear : function(date) {
		if (date.getFullYear() == 2007) {
			return false;
		}
	}
});
//Selectionné l'année voulu
$("#select_annee").datepicker( {
    format: "yyyy",
    viewMode: "years", 
    minViewMode: "years",
    language: "fr",
    autoclose: true

});
function initFiltre(){
	$('#filtre_form').find(':radio[name=tempsFiltre][value="2"]').prop('checked',
			true);
	
	$("#filtre_jours").hide();
	$("#filtre_periode").hide();
	$("#filtre_annee").hide();
	var month=getFormattedMonth(new Date());
	$("#select_mois").val(month);
$("#select_annee").val(getFormattedYear(new Date()))
var data=$("#filtre_mois_form").serialize();
drowArretGraphMonth(data);

}
//radio button events
var rad = document.filtre_form.tempsFiltre;
var prev = null;
for(var i = 0; i < rad.length; i++) {
    rad[i].onclick = function() {
        if(this.value==1){
        	$("#filtre_mois").hide();
        	$("#filtre_jours").show();
        	$("#filtre_periode").hide();
        	$("#filtre_annee").hide();
        	var idSegment=$('#filtre_jours_segment option:selected').val();
        	var day=getFormattedDate(new Date());
        	if($("#filtre_jour_id").val()==''){
        		$("#filtre_jour_id").val(day);
        	}
        	var info=$("#filtre_jour_form").serialize();
        	var description='Production du '+ $("#filtre_jour_id").val();
        	$("#titre_prodcution").empty();
        	$("#titre_prodcution").html("Production de la date du "+day);
        	
        	$("#titre_pie").empty();
        	$("#titre_pie").html("Production sur tout segment de la date "+day);
        	if(idSegment!=''){        		
        	loadProductionJourSegment(info)
        	drowArretGraphDay(info)
        	}else{
        		
        		loadProductionJour(info,description);
        		loadProductionJourBySegment(info);
        		drowArretGraphDay(info)
        	}
        	
        }else if(this.value==3){
        	$("#filtre_mois").hide();
        	$("#filtre_jours").hide();
        	$("#filtre_periode").show();
        	$("#filtre_annee").hide();
            console.log(moment().subtract(3, 'month').calendar());
            $("#choix_debut").val(moment().subtract(3, 'month').calendar());
            $("#choix_fin").val(getFormattedDate(new Date()));
            var data=$("#choix_periode_form").serialize();
            drowLineProductionPeriode(data);
            drowPieProductionPeriode(data);
            drowArretGraphPriode(data);
        }else if(this.value==4){
        	$("#filtre_mois").hide();
        	$("#filtre_jours").hide();
        	$("#filtre_periode").hide();
        	$("#filtre_annee").show();
        	$("#select_annee").val(getFormattedYear(new Date()));
        var data=$("#filtre_annee_form").serialize();
        	drowLineProductionYear(data);
        	drowPieProductionYear(data);
        	drowArretGraphYear(data);

        }else{
        	$("#filtre_mois").show();
        	$("#filtre_jours").hide();
        	$("#filtre_periode").hide();
        	$("#filtre_annee").hide();	
        	var month=getFormattedMonth(new Date());
        	$("#select_mois").val(month);
        	loadProduction();
        	loadProductionOnSegment();
        	var monthForm=$("#filtre_mois_form").serialize();
        	drowArretGraphMonth(monthForm);
        }
    };
}

function getFormattedDate(date) {
	  var year = date.getFullYear();

	  var month = (1 + date.getMonth()).toString();
	  month = month.length > 1 ? month : '0' + month;

	  var day = date.getDate().toString();
	  day = day.length > 1 ? day : '0' + day;
	  
	  return  day+ '/' +month  + '/' + year;
	}
function getFormattedMonth(date){
	 var year = date.getFullYear();

	  var month = (1 + date.getMonth()).toString();
	  month = month.length > 1 ? month : '0' + month;
	  
	  return  month  + '/' + year;
}

function getFormattedYear(date){
	 var year = date.getFullYear();
	  return  year;
}
