$("#myform").submit(
		function(event) {
			event.preventDefault();
			var name = document.getElementById("exampleInputEmail1").value;
			var name2 = document.getElementById("exampleInputEmail2").value;
			var targetUrl = 'http://localhost:8080/load?fauth=' + name
					+ '&sauth=' + name2;
			
			var form_data = $(this).serialize();
			var cachedThis = this;
			
			$.ajax({
				url : targetUrl,
				type : "GET",
				data : form_data,
				dataType : "XML"
			}).done(function(response) {
				var url=$(response).find("binding uri").text();
				$("#server-results").html(url);

			});

		});
