<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a href="#" class="brand">Issue Tracker</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li><a href="#">Incidencias</a></li>
					<li id="menu_users"><a href="./Users.jsp">Usuarios</a></li>
					<li id="menu_teams"><a href="./Teams.jsp">Equipos</a></li>
					<li id="menu_itemStates"><a href="./ItemStates.jsp">Estados</a></li>
					<li id="menu_itemPriorities"><a href="./ItemPriorities.jsp">Prioridades</a></li>
					<li><a href="#">Workflow</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="container">
	<div id="alert" class="alert" style="display:none">
		<a class="close"href="#" onclick="javascript:$('#alert').fadeOut();" >×</a>
		<div data-bind="text: message">No hay mensage</div>
	</div>
</div>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/bootbox.js"></script>
<script src="js/knockout-2.2.1.js"></script>
<script type="text/javascript">
<!--
	var root = "${pageContext.request.contextPath}";

	function MessageViewModel() {
		var self = this;
		this.message = ko.observable("");
		this.ok = function(text) {
			self.message(text);
			$("#alert").addClass("alert-success");
			$("#alert").fadeIn();
		};
		
		this.error = function(text){
			self.message(text);
			$("#alert").addClass("alert-error");
			$("#alert").fadeIn();
		};
	};

	var messageViewModel = new MessageViewModel();

	ko.applyBindings(messageViewModel, document.getElementById("alertDiv"));
//-->
</script>