$("#myform").submit(
		function(event) {
			event.preventDefault();
			var name = document.getElementById("exampleInputEmail1").value;
			var name2 = document.getElementById("exampleInputEmail2").value;
			var targetUrl = 'http://localhost:8080/load?fauth=' + name + '&sauth=' + name2;
			
			var form_data = $(this).serialize();
			var cachedThis = this;
			
			$.ajax({
				url : targetUrl,
				type : "GET",
				data : form_data,
				dataType : "XML"
			}).done(function(response) {
				var coi_level;
				if((response >= 0) && (response <= 5)){
					coi_level = "Low";
				}else if((response > 5) && (response <= 15)){
					coi_level = "Medium";
				}else if((response > 15)){
					coi_level ="High";
				}
				document.getElementById("server-results").innerHTML = 
					"<b><u>Result</u></b><br>Number of co-authored papers by <b>"+name+"</b> and <b>"+name2+"</b> is: <b>"+response+"</b><br>Calculated COI Level is: <b>"+coi_level+"</b>";
			});

		});
