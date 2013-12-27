$ ->
	$.get "/json", (data) ->
		$.each data, (index, task) ->
			$("#tasks").append $("<li>").text task.label
			$("#tasks").append $("<input type='submit' value='Delete'>").click () ->
			    $.post "/tasks/delete/",
			        id: task.id
			        (data) -> $('body').append "Successfully posted to the page."