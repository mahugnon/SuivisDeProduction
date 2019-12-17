$(document).ready(function() {
	 $('#create-form')
     .find('[name="telephone"]')
	.intlTelInput({
		utilsScript: $('#path').val()+"/resources/js/utils.js",
		 autoPlaceholder: true,
	});
	 
$("#create-form").bootstrapValidator({
									// To use feedback icons, ensure that
										// you use Bootstrap v3.1.0 or later
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											firstName : {
												validators : {
													stringLength : {
														min : 2,
													},
													notEmpty : {
														message : 'Remplissez le champ nom s\'il vous plait'
													}
												}
											},
											lastName : {
												validators : {
													stringLength : {
														min : 2,
													},
													notEmpty : {
														message : 'Remplissez le champ prénom s\'il vous plait'
													}
												}
											},
											adresseMail : {
												validators : {

													notEmpty : {
														message : 'Remplissez le champ email  s\'il vous plait'
													},
													 emailAddress: {
									                        message: 'Veillez saisir une adresse email valid s\'il vous plait'
									                    }
												}
											},
											sexe:{
												validators :{
													notEmpty : {
														message : 'Veillez selectionné un sexe s\'il vous plait'
													}
												}
												
											},
											telephone : {
												validators : {
													notEmpty : {
														message : 'Remplissez le champ téléphone s\'il vous plait'
													},
													  callback:{
														  message: 'Le numero de téléphone n\'est pas valide',
														  callback:function(value, validator, $field){
															  return value === '' || $field.intlTelInput('isValidNumber'); 
														  }
													  }
												}
											},
											adresse: {
									                validators: {
									                     stringLength: {
									                        min: 8,
									                    },
									                    notEmpty: {
									                        message: 'Saisissez votre adresse s\il vous plait'
									                    }
									                }
									            },
									            role:{
									            	validators :{
														notEmpty : {
															message : 'Veillez renseigner le role de l\'utilisateur s\'il vous plait'
														}
													}
									            },
									            roles:{
									            	validators :{
														notEmpty : {
															message : 'Veillez renseigner le role de l\'utilisateur s\'il vous plait'
														}
													}
									            }
									         
										}
									})
									// Revalidate the number when changing the country
							        .on('click', '.country-list', function() {
							            $('#create-form').formValidation('revalidateField', 'telephone');
							        })
							        .on('success.form.bv', function(e) {
										$("#create-success").html("Ajout effectuer avec succes");
							        	$('#create-success').slideDown({ opacity: "show" }, "slow") // Do something ...
						            setTimeout(() => {
										$("#create-success").hide("drop",{direction: "right"},500);	
									}, 3000);
							        	$('#create-form').data('bootstrapValidator').resetForm();
							            
						            // Prevent form submission
						            e.preventDefault();

							            postCreate();
						        });
	 
	 
	 $("#addPentSectionForm").bootstrapValidator({
		 feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
		fields :{
			nom:{
				validators : {
					stringLength : {
						min : 2,
						
					},
					notEmpty : {
						message : 'Veillez renseigner le nom du plent section s\'il vous plait'
					}
				}	
			},
			entreprise :{
				validators : {
					stringLength : {
						min : 2,
					},
					notEmpty : {
						message : 'Veillez renseigner l\entreprise auquel appartient ce plent section s\'il vous plait'
					}
				}	
			},
			chefPlentSection :{
				validators : {
				
					notEmpty : {
						message : 'Veillez renseigner le chef de ce plent section s\'il vous plait'
					}
				}	
			}
			
		}
		
	 })
	 .on('success.form.bv', function(e) {
			$("#createPlentSection_success").html("Ajout de plent section effectuer avec succes");
     	$('#createPlentSection_success').slideDown({ opacity: "show" }, "slow") // Do something ...
         setTimeout(() => {
				$("#createPlentSection_success").hide("drop",{direction: "right"},500);	
									}, 3000);
     	$('#addPentSectionForm').data('bootstrapValidator').resetForm();
         
     // Prevent form submission
     e.preventDefault();

     savePlentSection()
     });
	 $('#addSegmentForm').bootstrapValidator({
		  feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
		fields :{
			nom:{
				validators : {
					stringLength : {
						min : 2,
						
					},
					notEmpty : {
						message : 'Veillez renseigner le nom du segment s\'il vous plait'
					}
				}	
			},
			plentSectionSegment:{
				validators : {
					notEmpty : {
						message : 'Veillez renseigner le plent section auquel appartient le segment s\'il vous plait'
					}
				}
			},
			chef_segment:{
				validators :{
					notEmpty : {
						message : 'Veillez renseigner le chef du  segment s\'il vous plait'
					}
				}
				
			},
			description_PM:{
				validators :{
					stringLength : {
						min : 2,
						
					},
					notEmpty : {
						message : 'Veillez donner une bref description du post matin de ce segment s\'il vous plait'
					}
				}
			},
			chefPM:{
				validators :{
					notEmpty : {
						message : 'Veillez renseigner le chef du post matin de ce segment s\'il vous plait'
					}
				}
			},
			description_PA:{
				validators :{
					stringLength : {
						min : 2,
						
					},
					notEmpty : {
						message : 'Veillez donner une bref description du post d\'après-midi de ce segment s\'il vous plait'
					}
				}
			},
			chefPA:{
				validators :{
					notEmpty : {
						message : 'Veillez renseigner le chef du post d\'après-midi de ce segment s\'il vous plait'
					}
				}
			}
		}
	 })
	 .on('success.form.bv', function(e) {
			$("#createSegment_success").html("Ajout de segment effectuer avec succes");
  	$('#createSegment_success').slideDown({ opacity: "show" }, "slow") // Do something ...
   setTimeout(() => {
			$("#createSegment_success").hide("drop",{direction: "right"},500);	
					}, 3000);
  	$('#addSegmentForm').data('bootstrapValidator').resetForm();
      
  // Prevent form submission
  e.preventDefault();

	saveSegment();

  });
	 $("#addChaineForm").bootstrapValidator({
		  feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
		fields :{
			nom:{
				validators : {
					stringLength : {
						min : 2,
						
					},
					notEmpty : {
						message : 'Veillez renseigner le nom de la chaine s\'il vous plait'
					}
				}	
			},
			description:{
				validators : {
					stringLength : {
						min : 2,
						
					},
					notEmpty : {
						message : 'Veillez donner une bref description de cette chaine s\'il vous plait'
					}
				}	
			},
			nombrePostTravail:{
				validators : {
					notEmpty : {
						message : 'Veillez renseigner le nombre de post sur la chaine s\'il vous plait'
					}
				}	
			},
			segmentChaine:{
				validators : {
					notEmpty : {
						message : 'Veillez renseigner le segment auquel appartient la chaine s\'il vous plait'
					}
				}
			},
			chefChaine:{
				validators : {
					notEmpty : {
						message : 'Veillez renseigner le chef de la chaine s\'il vous plait'
					}
				}
			}
		}
	 })
	 .on('success.form.bv', function(e) {
			$("#createChaine_success").html("Ajout de la nouvelle chaine  effectuer avec succes");
		  	$('#createChaine_success').slideDown({ opacity: "show" }, "slow") // Do something ...
		      setTimeout(() => {
			$("#createChaine_success").hide("drop",{direction: "right"},500);	
					}, 3000);
		  	$('#addChaineForm').data('bootstrapValidator').resetForm();
		      
		  // Prevent form submission
		  e.preventDefault();

		  saveChaine();
		  });
	 
	 
	 $("#sendMessageForm").bootstrapValidator({
		  feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
		fields :{
			role:{
				validators:{
					notEmpty:{
						message:'Veillez séléctionner un destinataire'
					}
				}
			},
			sujet:{
				validators:{
					stringLength:{
						min:2
					},
					notEmpty:{
						message:'Veillez renseigner le sujet de votre message'
					}
				}
			},
			contenu:{
				validators:{
					stringLength:{
						min:10,
						max:500
					},
					notEmpty:{
						message:'Vous ne pouvez pas envoyer un message avec un contenu vide'
					}
				}
			
			}
		}
	 })
	 .on('success.form.bv', function(e) {
			$("#sendMessage_success").html("Message envoyer  avec succes");
		  	$('#sendMessage_success').slideDown({ opacity: "show" }, "slow") // Do something ...
		  	$('#sendMessageForm').data('bootstrapValidator').resetForm();
		  	setTimeout(() => {
			$("#sendMessage_success").hide("drop",{direction: "right"},500);	
					}, 3000);
		      
		  // Prevent form submission
		  e.preventDefault();

			sendMessage();
		  });
	 
	 //selection de periode

});

