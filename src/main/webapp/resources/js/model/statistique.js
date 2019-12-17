$(document).ready(function() {
	moment.locale('fr');
	loadProduction();
	loadProductionOnSegment();
	var option_j=$("#filtre_jours_segment");
	var option_m=$('#filtre_mois_segment');
	var option_p=$('#filtre_periode_segment');
	var option_a=$('#filtre_annee_segment');

	loadSegment(option_j);
	loadSegment(option_m);
	loadSegment(option_p);
	loadSegment(option_a);

	setInterval(() => {
		
	}, 60*1000);
});

function loadSegment(option){
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/loadSegment',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		success : function(response) {
			populateSelect(response,option);
			
		}
	});
}

function populateSelect(data,selectOption){
	selectOption.find('option').remove();  
	selectOption.append('<option value="">-------choix segment------</option>');
	$.each(data,function(key, value) 
			{
		selectOption.append('<option value=' + value.idSegment + '>' + value.nom + '</option>');
			});
}
function loadProduction() {
	$("#titre_prodcution").empty();

	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/month_production',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		success : function(response) {
			var label = [];
			var donnee = [];
			for (i = 0; i < response.length; i++) {
				label.push(moment(response[i][0]).format('Do MMM YY'))
				donnee.push(response[i][1])
			}
        	$("#titre_prodcution").html("PRODUCTION DE "+moment(new Date()).format('MMMM YYYY').toUpperCase());
        		 $('#results-graph').remove(); // this is my <canvas> element
			  $('#production_container').append('<canvas id="results-graph"><canvas>');
			var ctx = document.getElementById('results-graph').getContext(
					'2d');
			 drowLineChart(ctx,label,donnee,'Production ','Jour','bar')
			
		}
	});
}


// production of month by segment
function loadProductionOnSegment() {
	$("#titre_pie").empty();
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/month_production_by_segment',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		success : function(response) {
			var label = [];
			var donnee = [];
			if(response.length!=0){
				$("#titre_pie").html('PRODUCTION PAR SEGMENT')
			}
			for (i = 0; i < response.length; i++) {
				label.push(response[i][0])
				donnee.push(response[i][1])
			}
			$('#pie_results-graph').remove(); // this is my <canvas> element
			  $('#pie_production_container').append('<canvas id="pie_results-graph"><canvas>');
			var ctx = document.getElementById('pie_results-graph').getContext(
					'2d');
			drawPieChart(ctx,label,donnee);


		}
	});
}
function drowLineChart(ctx,label,donnee,description,XlabelString,type){
	var graph = new Chart(ctx, {
		type : type,
		label : 'Production',
		data : {
			labels : label,
			datasets : [ {
				label : 'Production',
				data : donnee,
				fill: false,
				 backgroundColor: '#2E4A3C',
                    borderColor: '#2E4A3C',
				 hoverBackgroundColor: "rgba(255,99,132,0.4)",
				    hoverBorderColor: "rgba(255,99,132,1)",
				    pointBackgroundColor:'#96E82A',
				    pointRadius:8,
				    pointHoverRadius:10
									} ],

		},
		options : {
			responsive: true,
			tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
			scales : {
				xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: XlabelString
                    }
                }],
				yAxes : [ {
					 display: true,
                        scaleLabel: {
                            display: true,
                            labelString: 'Quantité Produite'
                        },
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	})
	return graph;
}

function drowArretChart(ctx,label,donnee,description,XlabelString){
	var graph = new Chart(ctx, {
		type : 'line',
		label : 'Arrêt',
		data : {
			labels : label,
			datasets : [ {
				label : 'Arrêt',
				data : donnee,
				fill: false,
				lineTension:0,
				 backgroundColor: '#3D0C32',
                    borderColor: '#3D0C32',
				 hoverBackgroundColor: "rgba(255,99,132,0.4)",
				    hoverBorderColor: "rgba(255,99,132,1)",
				    pointBackgroundColor:'#B21A07',
				    pointRadius:8,
				    pointHoverRadius:10
									} ],

		},
		options : {
			responsive: true,
			tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
			scales : {
				xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: XlabelString
                    }
                }],
				yAxes : [ {
					 display: true,
                        scaleLabel: {
                            display: true,
                            labelString: 'Arrêt'
                        },
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	})
}
function drawPieChart(ctx,label,data){
	var graph = new Chart(ctx, {
		type : 'pie',
		label : 'Production du mois par segment',
		data : {
			labels : label,
			datasets : [ {
				 backgroundColor: [
	                    'red',
	                    'orange',
	                    'yellow',
	                    'green',
	                    'blue',
	                ],

				data : data} ],
				
		},
		options : {
			responsive: true,
			tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            }
			
		}
	})
}


$("#filtre_jour_id").change(function(){
	var description='Production du '+ $("#filtre_jour_id").val();
	var idSegment=$('#filtre_jours_segment option:selected').val();
	var info=$("#filtre_jour_form").serialize();
	if(idSegment!=''){
	loadProductionJourSegment(info)
			loadProductionJourBySegment(info);
	drowArretGraphDay(info)
	}else{
		
		loadProductionJour(info,description);
		loadProductionJourBySegment(info);
		drowArretGraphDay(info)
	}
});
function loadProductionJour(data,description){
	$("#titre_prodcution").empty();
	 $('#results-graph').remove(); // this is my <canvas> element
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/jour_production_on_segment',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			if(response.length!=0){
				var label = [];
				var donnee = [];
				for (i = 0; i < response.length; i++) {
					label.push(response[i][0])
					donnee.push(response[i][1])
				}
				var day=$("#filtre_jour_id").val();
	        	$("#titre_prodcution").html("PRODUCTION DU "+day);
	        	
				  $('#production_container').append('<canvas id="results-graph"><canvas>');
				var ctx = document.getElementById('results-graph').getContext(
						'2d');
				
				drowLineChart(ctx,label,donnee,description,'Post','bar');	
			}
			
		}
	});
}

function loadProductionJourBySegment(data){
	$("#titre_pie").empty();
	$('#pie_results-graph').remove(); // this is my <canvas> element

	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/jour_production_By_segment',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			if(response.length!=0){
				var label = [];
				var donnee = [];
				for (i = 0; i < response.length; i++) {
					label.push(response[i][0])
					donnee.push(response[i][1])
				}
				var day=$("#filtre_jour_id").val();

				var valSeg=$('#filtre_jours_segment').val();
				console.log(valSeg)
				if(valSeg!=''){
		        	$("#titre_pie").html("PRODUCTION PAR CHAINE DU "+day);

				}else{
		        	$("#titre_pie").html("PRODUCTION PAR SEGMENT DU "+day);
				}
				  $('#pie_production_container').append('<canvas id="pie_results-graph"><canvas>');
				var ctx = document.getElementById('pie_results-graph').getContext(
						'2d');
				drawPieChart(ctx,label,donnee);	
			}
			
			
		}
	});
}

$("#filtre_jours_segment").change(function(){
	var description='PRODUCTION DU '+ $("#filtre_jour_id").val();
	var idSegment=$('#filtre_jours_segment').val();
	var nom=$('#filtre_jours_segment option:selected').text();
	var option=	$('#filtre_jours_chaine');

	var data=$("#filtre_jour_form").serialize();
	if(idSegment!=''){
		loadProductionJourSegment(data,nom);
		drowGraphByChaine(data);
		drowArretGraphDay(data);
	}else{
		loadProductionJour(data,description);
		loadProductionJourBySegment(data);
		drowArretGraphDay(data)
	}
})
function loadProductionJourSegment(donnee,nom){
	$("#titre_prodcution").empty();
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/jour_By_production_By_segment',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:donnee,
		success : function(response) {
			 $('#results-graph').remove(); // this is my <canvas> element
			var label = [];
			var donnee = [];
			if(response.length!=0){
				for (i = 0; i < response.length; i++) {
					label.push(response[i][0])
					donnee.push(response[i][1])
				}
				var day=$("#filtre_jour_id").val();
				$("#titre_prodcution").html("PRODUCTION DU "+day )
				  $('#production_container').append('<canvas id="results-graph"><canvas>');
				var ctx = document.getElementById('results-graph').getContext(
						'2d');
				
	        	
				drowLineChart(ctx,label,donnee,'Segment: '+nom,'Post','bar')
			}
		
		}
	});
}

/*
 * function loadChaineBySegment(idSegment,option){ $.ajax({ dataType : "json",
 * url : '/production/kosu_connect/admin/statistiques/loadChaine/'+idSegment,
 * headers : { 'Accept' : 'application/json', 'Content-Type' :
 * 'application/json' }, type : 'GET', success : function(response) {
 * populateChaineSelect(response,option); } }); }
 */

/*
 * function populateChaineSelect(data,selectOption){
 * selectOption.find('option').remove(); selectOption.append('<option
 * value="">-------choix Chaine------</option>'); $.each(data,function(key,
 * value) { selectOption.append('<option value=' + value.idChaine + '>' +
 * value.nom + '</option>'); }); }
 */

function drowGraphByChaine(donnee){
	$("#titre_pie").empty();
	$('#pie_results-graph').remove(); // this is my <canvas> element
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/loadproductionChaineByDayBySegment',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:donnee,
		success : function(response) {
			if(response.length!=0){
				var label = [];
				var donnee = [];
				for (i = 0; i < response.length; i++) {
					label.push(response[i][0])
					donnee.push(response[i][1])
				}
				var day=$("#filtre_jour_id").val();

				var valSeg=$('#filtre_jours_segment').val();

				if(valSeg!=''){
		        	$("#titre_pie").html("PRODUCTION PAR CHAINE DU "+day);

				}else{
		        	$("#titre_pie").html("PRODUCTION PAR SEGMENT DU "+day);
				}
				  $('#pie_production_container').append('<canvas id="pie_results-graph"><canvas>');
				var ctx = document.getElementById('pie_results-graph').getContext(
						'2d');
				drawPieChart(ctx,label,donnee);	
			}		}
	});
}
$("#select_mois").change(function(){
	var month=$("#select_mois").val();
	var idSegment=$("#filtre_mois_segment").val();
	$("#titre_prodcution").empty();
	$("#titre_prodcution").html("Production du mois "+month);
	var data=$("#filtre_mois_form").serialize();
	if(idSegment!=''){
		drowloadLinProductionByMonthBySegment(data)
		drowloadPieProductionByMonthBySegment(data)
	drowArretGraphMonth(data);

	}else{
		drowloadLineProductionMonth(data)
		drowloadPieProductionMonth(data)
	drowArretGraphMonth(data);

	}
	
});

$("#filtre_mois_segment").change(function(){
	var month=$("#select_mois").val();
	var idSegment=$("#filtre_mois_segment").val();
	var data=$("#filtre_mois_form").serialize();
	var selectOption=$("#filtre_mois_chaine")
	if(idSegment!=''){
		drowloadLinProductionByMonthBySegment(data)
		drowloadPieProductionByMonthBySegment(data)
		drowArretGraphMonth(data)
	}else{
		drowloadLineProductionMonth(data)
		drowloadPieProductionMonth(data)
		drowArretGraphMonth(data)
	}
});

function drowloadLineProductionMonth(data){
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/loadProductionByMonth',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			var label = [];
			var donnee = [];
			$("#titre_prodcution").empty();
	    	$('#results-graph').remove(); // this is my <canvas> element

	if(response.length!=0){
		for (i = 0; i < response.length; i++) {
		label.push((moment(response[i][0]).format('Do MMM YY')))
		donnee.push(response[i][1])
	}
	  $('#production_container').append('<canvas id="results-graph"><canvas>');
	var ctx = document.getElementById('results-graph').getContext(
			'2d');
	$("#titre_prodcution").html("Production du Mois de "+moment(response[0][0]).format('MMMM YYYY'));
	
	 drowLineChart(ctx,label,donnee,'Production ','Jour','bar')
	}
	

			
				}
		
	});
}
function drowloadPieProductionMonth(data){

	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/loadProductionByMonthBySegment',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			var label = [];
			var donnee = [];
			$("#titre_pie").empty();
			$('#pie_results-graph').remove(); // this is my <canvas>

			if(response.length!=0){
				for (i = 0; i < response.length; i++) {
					label.push(response[i][0])
					donnee.push(response[i][1])
				}
													// element
				  $('#pie_production_container').append('<canvas id="pie_results-graph"><canvas>');
				var ctx = document.getElementById('pie_results-graph').getContext(
						'2d');
				if($("#filtre_mois_segment").val()!=''){
					$("#titre_pie").html("PRODUCTION PAR CHAINE ")
		
				}else{
					$("#titre_pie").html("PRODUCTION PAR SEGMENT ")

				}

				drawPieChart(ctx,label,donnee);	
			}
				}
	});
}
function drowloadLinProductionByMonthBySegment(data){
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/month/segment/line',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			$('#results-graph').remove(); // this is my <canvas> element
			$("#titre_prodcution").empty();
	    	$("#titre_pie").empty();
	    	var nom=$('#filtre_mois_segment option:selected').text();
			var label = [];
			var donnee = [];
			if(response.length!=0){
				if(response!=[]){
				for (i = 0; i < response.length; i++) {
					label.push((moment(response[i][0]).format('Do MMM YY')))
					donnee.push(response[i][1])
				}
				
				  $('#production_container').append('<canvas id="results-graph"><canvas>');
				var ctx = document.getElementById('results-graph').getContext(
						'2d');
		    	$("#titre_prodcution").html("PRODUCTION DU "+moment(response[0][0]).format('MMMM YYYY').toUpperCase()+"" +
		    			" SEGMENT :"+nom);
				
				 drowLineChart(ctx,label,donnee,'Production ','Jour',"bar")	
			}
				
			}
			
			
				}
		});
}

/* les arrêt du mois */
function drowArretGraphMonth(data){
	
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/month',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			var nom=$('#filtre_mois_segment option:selected').text();
			var label = [];
			var donnee = [];
			$('#arretResults-graph').remove(); // this is my <canvas> element
			$("#titre_arret").empty();

			if(response.length!=0){

				for (i = 0; i < response.length; i++) {
					label.push((moment(response[i][0]).format('Do MMM YY')))
					donnee.push(response[i][1])
				}
				  $('#arret_container').append('<canvas id="arretResults-graph"><canvas>');
				var ctx = document.getElementById('arretResults-graph').getContext(
						'2d');
				$("#titre_arret").html("ARRÊT DU MOIS "+moment(response[0][0]).format(' MMMM YYYY').toUpperCase());

		    	drowArretChart(ctx,label,donnee,'Arrêt ','Jour')	
			}
					}
		});
}

/* Arrêt du jour */
function drowArretGraphDay(data){
	$('#arretResults-graph').remove(); // this is my <canvas> element
	$("#titre_arret").empty();
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/day',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {

			var nom=$('#filtre_mois_segment option:selected').text();
			var label = [];
			var donnee = [];
			

			if(response.length!=0){
				for (i = 0; i < response.length; i++) {
					label.push(moment(response[i][0]).format('LT')+"H ")
					donnee.push(response[i][1])
				}
				  $('#arret_container').append('<canvas id="arretResults-graph"><canvas>');
				var ctx = document.getElementById('arretResults-graph').getContext(
						'2d');
		    	$("#titre_arret").html("ARRÊT DU "+moment(response[0][0]).format('Do MMMM YYYY').toUpperCase());
		    	
		    	drowArretChart(ctx,label,donnee,'Arrêt ','Heure')	
			}
					}
		});
}


/* Arrêt du Année */
function drowArretGraphYear(data){
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/year',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			
			var nom=$('#filtre_annee_segment option:selected').text();
			var idSegment=$('#filtre_annee_segment option:selected').val();
			
			var label = [];
			var donnee = [];
			$('#arretResults-graph').remove(); // this is my <canvas> element
			$("#titre_arret").empty();

			if(response.length!=0){
				for (i = 0; i < response.length; i++) {
					label.push(moment(response[i][0]).format('MMMM YYYY'))
					donnee.push(response[i][1])
				}
				  $('#arret_container').append('<canvas id="arretResults-graph"><canvas>');
				var ctx = document.getElementById('arretResults-graph').getContext(
						'2d');
				if(idSegment==''){
			    	$("#titre_arret").html("ARRÊT DE L'ANNEE "+moment(response[0][0]).format('YYYY'));
	
				}else{
			    	$("#titre_arret").html("ARRÊT DE L'ANNEE "+moment(response[0][0]).format('YYYY')+" SUR LE SEGMENT "+nom.toUpperCase());

				}
		    	
		    	drowArretChart(ctx,label,donnee,'Arrêt ','Mois')	
			}
					}
		});
}

/* Arrêt du Periode */
function drowArretGraphPriode(data){
	$('#arretResults-graph').remove(); // this is my <canvas> element
	$("#titre_arret").empty();
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/periode',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			var nom=$('#filtre_periode_segment option:selected').text();
			var idSegment=$('#filtre_periode_segment option:selected').val();

			var label = [];
			var donnee = [];
			var debut=$("#choix_debut").val();
			var fin=$("#choix_fin").val();

			if(response.length!=0){
				for (i = 0; i < response.length; i++) {
					label.push(moment(response[i][0]).format('Do MMM YY'))
					donnee.push(response[i][1])
				}
				  $('#arret_container').append('<canvas id="arretResults-graph"><canvas>');
				var ctx = document.getElementById('arretResults-graph').getContext(
						'2d');
		    	if(idSegment==''){
		    		$("#titre_arret").html("ARRET DU "+debut+" AU "+fin)
		    	}else{
		    		$("#titre_arret").html("ARRET DU "+debut+" AU "+fin+" SUR LE SEGMENT "+nom.toUpperCase());

		    	}
		    	drowArretChart(ctx,label,donnee,'Arrêt ','Jours')	
			}
					}
		});
}



function drowloadPieProductionByMonthBySegment(data){
	$('#pie_results-graph').remove(); // this is my <canvas> element
	  $('#pie_production_container').append('<canvas id="pie_results-graph"><canvas>');

	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/month/segment/chart',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			$("#titre_pie").empty();

			var label = [];
			var donnee = [];
			if(response.length!=0){
			for (i = 0; i < response.length; i++) {
				label.push(response[i][0])
				donnee.push(response[i][1])
			}
			
			  $('#pie_production_container').append('<canvas id="pie_results-graph"><canvas>');
			var ctx = document.getElementById('pie_results-graph').getContext(
					'2d');
			var segment=$('#filtre_mois_segment').find(":selected").val();
			drawPieChart(ctx,label,donnee);

			if(segment==''){
				$('#titre_pie').html("QUANTITE PRODUITE PAR SEGMENT")
			}else{
				$('#titre_pie').html("QUANTITE PRODUITE PAR CHAINE")
			}}
		}
	});
}
function drowLineProductionYear(data){
	$("#titre_prodcution").empty();
	$('#results-graph').remove(); // this is my <canvas> element

	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/year/line',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			if(response.length!=0){
				var label = [];
				var donnee = [];
				for (i = 0; i < response.length; i++) {
					label.push((moment(response[i][0]).format('MMMM YYYY')))
					donnee.push(response[i][1])
				}
				  $('#production_container').append('<canvas id="results-graph"><canvas>');
				var ctx = document.getElementById('results-graph').getContext(
						'2d');
		    	$("#titre_prodcution").html("Production de l'année de "+moment(response[0][0]).format('YYYY'));
		    	
				 drowLineChart(ctx,label,donnee,'Production ','MOIS','bar')	
			}
					}
		});
}
function drowPieProductionYear(data){
	$("#titre_pie").empty();
	$('#pie_results-graph').remove(); // this is my <canvas> element

	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/year/pie',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			var idSegment=$('#filtre_annee_segment option:selected').val();

			var label = [];
			var donnee = [];
			if(response.length!=0){
				if(idSegment==''){
					$("#titre_pie").html("PRODUCTION PAR SEGMENT");
	
				}else{
					$("#titre_pie").html("PRODUCTION PAR CHAINE");

				}
				for (i = 0; i < response.length; i++) {
					label.push(response[i][0])
					donnee.push(response[i][1])
				}
				  $('#pie_production_container').append('<canvas id="pie_results-graph"><canvas>');
				var ctx = document.getElementById('pie_results-graph').getContext(
						'2d');
				drawPieChart(ctx,label,donnee);
			}
			}
	});
}
$("#select_annee").change(function(){
	  var data=$("#filtre_annee_form").serialize();
        	drowLineProductionYear(data);
        	drowPieProductionYear(data);
        	drowArretGraphYear(data);
});
$("#filtre_annee_segment").change(function(){
	 var data=$("#filtre_annee_form").serialize();
        	drowLineProductionYear(data);
        	drowPieProductionYear(data);
        	drowArretGraphYear(data);
});


function drowLineProductionPeriode(data){
	$("#titre_prodcution").empty();
	$('#results-graph').remove(); // this is my <canvas> element
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/periode/line',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			if(response.length!=0){
				var debut=$("#choix_debut").val();
				var fin=$("#choix_fin").val();
				var label = [];
				var donnee = [];
				for (i = 0; i < response.length; i++) {
					label.push((moment(response[i][0]).format('Do MMM YY')))
					donnee.push(response[i][1])
				}
				  $('#production_container').append('<canvas id="results-graph"><canvas>');
				var ctx = document.getElementById('results-graph').getContext(
						'2d');
		    	$("#titre_prodcution").html("PRODUCTION DU "+debut+" AU "+fin);
		    	
				 drowLineChart(ctx,label,donnee,'Production ','JOURS','bar')	
			}
					}
		});	
}

function drowPieProductionPeriode(data){
	$("#titre_pie").empty();
	$('#pie_results-graph').remove(); // this is my <canvas> element
	$.ajax({
		dataType : "json",
		url : '/production/kosu_connect/admin/statistiques/periode/pie',
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : 'GET',
		data:data,
		success : function(response) {
			if(response.length!=0){
				var idSegment=$('#filtre_periode_segment option:selected').val();
				var nom=$('#filtre_periode_segment option:selected').text();
				var debut=$("#choix_debut").val();
				var fin=$("#choix_fin").val();
				var label = [];
				var donnee = [];
				for (i = 0; i < response.length; i++) {
					label.push(response[i][0])
					donnee.push(response[i][1])
				}
				if(idSegment==''){
					$("#titre_pie").html("PRODUCTION PAR SEGMENT DU "+debut+" AU "+fin);
	
				}else{
					$("#titre_pie").html("PRODUCTION PAR CHAINE DU "+debut+" AU "+fin+" SUR LE SEGMENT "+nom.toUpperCase());

				}
				  $('#pie_production_container').append('<canvas id="pie_results-graph"><canvas>');
				var ctx = document.getElementById('pie_results-graph').getContext(
						'2d');
				drawPieChart(ctx,label,donnee);
			}
				}
	});
}
$("#filtre_periode_segment").change(function(){
	var data=$("#choix_periode_form").serialize();
    drowLineProductionPeriode(data);
    drowPieProductionPeriode(data);
    drowArretGraphPriode(data);

})

$("#choix_debut").change(function(){
	var data=$("#choix_periode_form").serialize();
    drowLineProductionPeriode(data);
    drowPieProductionPeriode(data);
    drowArretGraphPriode(data);

})

$("#choix_fin").change(function(){
	var data=$("#choix_periode_form").serialize();
    drowLineProductionPeriode(data);
    drowPieProductionPeriode(data);
    drowArretGraphPriode(data);

})