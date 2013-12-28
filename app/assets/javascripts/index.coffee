$ ->
	$.get "/json", (data) ->
		$.each data, (index, task) ->
			$("#tasks").append $("<li>").text task.label
			$("#tasks").append $("<input type='submit' value='Delete'>").click (e) ->
			    $.post "/tasks/" + task.id + "/delete",
			        (data) -> $(location).attr('href', "/")
		$("#header").text data.length + " Task(s)"