/**
 * @author Robert Wilk Created on 1/20/2016.
 */

// Begin Something modal population and submit functions
var url = $('#path').val() + "/kosu_connect/admin/";
var editModalTarget = url + "loadUser/";
var tableTarget = url + "loadUserTable/";
var role = '';
var imagResize = null;
var indicateur='';


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
		console.log(data);
		populateModal(data);
	});
}

// Assign the data values to the modal form.
function populateModal(data) {
	$('#update-id').val(data.idUser);
	$('#update-userName').val(data.userName);
	$('#update-firstName').val(data.firstName);
	$('#update-lastName').val(data.lastName);
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

		//	$("#update-superAdmin").prop("checked", 1);
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
	console.log(data);
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
	// validation
	var valid = createValid();
	if (valid == true) {
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
				var table = $('#user-table').DataTable();

				table.ajax.url('/production/kosu_connect/admin/loadUserTable')
						.load();

			},
			error : function(e) {

				console.log("ERROR : ", e);

			}
		});

		clearAndCloseModal('#cmodal');

		$("#create-error").hide();
	} else {
		$("#create-error").html(
				"Echec d'ajout a cause d'un champ non rempli ou mal rempli");
		$("#create-error").show();

	}

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
									"data" : "actived",
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
								{
									data : null,
									render : function(data, type, row) {

										return " "
									}
								},
								{
									data : null,
									orderable : false,
									render : function(data, type, row) {
										var end = data.idUser + ");'";
										var edit = "'showEditModal(" + end;
										return "<a data-toggle='modal'class='btn btn-success' data-target='#umodal' onclick="
												+ edit
												+ "><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>Edit</a>"
									}
								},
								{
									data : null,
									orderable : false,
									render : function(data, type, row) {
										var end = data.idUser + ");'";
										var del = "'showDeleteModal(" + end;
										return "<a data-toggle='modal' class='btn btn-danger' data-target='#dmodal' onclick="
												+ del
												+ "><span class='glyphicon glyphicon-trash' aria-hidden='true'></span>Delete</a>"
									}
								} ]
					});
}
function showRole() {
	$('#create-roles').slideDown();
}
function showUpdateRole() {
	$('#update-roles').slideDown();
}
// Methode de validation des champs(a revoir)
function createValid() {
	var valid = true;
	var firstName = $("#create-firstName").val();
	var lastName = $("#create-lastName").val();
	var masculin = $("#create-masculin").is(":checked");
	var feminin = $("#create-feminin").is(":checked");
	var dateNaissance = $("#create-dateNaissance").val();
	var email = $("#create-email").val();
	var adresse = $("#create-adresse").val();
	var admin = $("#create-admin").is(":checked");
	var superAdmin = $("#create-superAdmin").is(":checked");
	var consultant = $("#create-consultant").is(":checked");
	if (firstName == '') {
		valid = false;
	}
	if (lastName == '') {
		valid = false;
	}
	if (masculin == false && feminin == false) {
		valid = false;
	}
	if (dateNaissance == '') {
		valid = false;
	}
	if (email == '') {
		valid = false;
	}
	if (adresse == '') {
		valid = false;
	}
	if (admin == false && superAdmin == false && consultant == false) {
		valid = false;
	}
	return valid;
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

};

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
var table=	$('#boite-message')
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
						"rowCallback": function( row, data, index ) {
						    if ( data.vu == false ) {
						      $( row).toggleClass('success');
						      $(row).css('font-weight',bold);
						    }
						  },
						
						"columns" : [

								{
									data : null,
									orderable : false,
									render : function(data, type, row) {
										var emetteur = data.message.emetteur.firstName
												+ " " + data.message.emetteur.lastName;
										
										return  emetteur 
									}
								},
								{
									"data" : "message.sujet",
									"orderable" : false
								},
								{data:null,render: function(data,type,row){
									// get a moment based on the user input
									var offset = new Date().getTimezoneOffset();
									//console.log(offset);
								    var m = moment(data.message.date).zone(offset);
								    // calculate a duration of time passed between now and that moment
								    var now = moment();
								    var d = moment.duration({from: m,to: now});
								    if(d.years()!=0){
							    		if(d.years()==1){
											return "<h6><small class='text-muted'>il y a "+d.years()+" an</small><h6>"
							    		}else {
											return "<h6><small class='text-muted'>il y a "+d.years()+" ans</small><h6>"	
							    		}

							    	}else if(d.months()!=0){
										return "<h6><small class='text-muted'>il y a "+d.months()+" mois</small><h6>"	

							    	}else if(d.days()!=0){
							    		if(d.days()==1){
											return "<h6><small class='text-muted'>il y a "+d.days()+" jour</small><h6>"	

							    		}else{
											return "<h6><small class='text-muted'>il y a "+d.days()+" jours</small><h6>"	

							    		}
	
							    	}else if(d.minutes()!=0){
							    		if(d.minutes()==1){
											return "<h6><small class='text-muted'>il y a "+d.minutes()+" minute</small><h6>"	

							    		}else{
											return "<h6><small class='text-muted'>il y a "+d.minutes()+" minutes</small><h6>"	

							    		}
							    	}else{
							    		if(d.seconds()==1){
											return "<h6><small class='text-muted'>il y a "+d.seconds()+" second</small><h6>"	

							    		}else{
											return "<h6><small class='text-muted'>il y a "+d.seconds()+" seconds</small><h6>"	

							    		}
							    	}
								   
								}},
								
								{
									data : null,
									orderable : false,
									render : function(data, type, row) {
										
										if(data.vu==false){
											return indicateur = "<h6><span id='nouveau' class='label label-danger pull-right'>Nouveau</span></h6>";

										}else{
											return '';}
									}}
								]
					});
	
	$('#boite-message tbody').on( 'click', 'tr', function () {
		data=table.row( this ).data();
		if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }else{
        	 table.$('tr.selected').removeClass('selected');
        	 $(this).removeClass('success');
             $(this).addClass('selected');
        }
	    console.log( data);
		editMassage(data);
	} );
}


function editMassage(data) {
	var m=data;
	if (data.vu== false) {
		$.ajax({
			dataType : "json",
			url : '/production/kosu_connect/admin/edit/' + data.message.id,
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			type : 'POST',
			  success: function(response) {
				  console.log(response);
	                $("#message_conteneur").html( response );
	            }
		});
	}
	$("#recu").addClass('active');
	$('#envoye').removeClass("active");
	 $("#message_conteneur").html( "<div class='well'><span style='font-weight:bold' class='pull-left'>"+
			 m.message.emetteur.userName
			 +":&nbsp;</span>"+m.message.contenu+"</div>" );
}

function nouvMessageCount() {
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/countNonVu',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'POST',
		  success: function(response) {
			  console.log(response);
			  if(response!=0){
	                $("#nouvMessages").html( response );  
			  }
            }
	});
}

