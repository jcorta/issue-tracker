<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Issue Tracker</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<link rel="shortcut icon" href="./img/favicon.png">
<style type="text/css">
body {
	padding-top: 60px;
}
</style>

</head>
<body>
	<!-- inicio del menu -->
	<jsp:include page="Menu.jsp" />
	<!-- fin de menu -->
	<!-- container -->
	<div id="container" class="container">
		<h1>Equipos<a href="#myModal" role="button" class="btn"
				data-toggle="modal"><i class="icon-plus-sign"></i></a>
		</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Usuarios</th>
					<th></th>
				</tr>
			</thead>
			<tbody data-bind="foreach: teams">
				<tr>
					<td data-bind="text: oid"></td>
					<td data-bind="text: name"></td>
					<td data-bind="foreach: users">
						<ul>
							<li data-bind="text: $data"></li>
						</ul>
					</td>
					<td><p class="text-right">
						<a class="btn" data-bind="click: $parent.deleteTeam"><i class="icon-trash"></i></a>
						<a class="btn" data-bind="click: $parent.addUsers"><i class="icon-user"></i></a></p>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- div del popup -->
		<div id="myModal" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Agregar un Equipo</h3>
			</div>
			<div class="modal-body">
				<form onsubmit="javascript:return false;">
					<label>Nombre</label> <input id="name" class="input-block-level"
						data-bind="value: name" /> 
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
				<a href="#" class="btn btn-primary" data-bind="click: createTeam" data-dismiss="modal">Guardar</a>
			</div>
		</div>
		<!-- fin div popup -->
		<!-- inicio div popuUsuarios -->
		<div id="users" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Agregar Usuarios al Equipo</h3>
			</div>
			<div class="modal-body" data-bind="foreach: candidates">
				<div>
					<label class="checkbox"><span data-bind="text: $data"></span><input type="checkbox" data-bind="value:$data, checked:$root.toAdd"/></label>
				</div>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
				<a href="#" class="btn btn-primary" data-bind="click: addToTeam" data-dismiss="modal">Guardar</a>
			</div>
		</div>
		<!-- fin div popupUsuarios -->
	</div>
	<!-- fin container -->

	<!-- javascript -->
	<script type="text/javascript">
		function TeamsViewModel() {
			var self = this;
			this.name = ko.observable();
			this.teams = ko.observableArray();
			this.toAdd = ko.observableArray();
			this.candidates = ko.observableArray();
			this.refresh = function() {
				$.getJSON(root + "/json/party/teams", function(data) {
					self.teams(data);
				});
			};
			this.createTeam = function() {
				$.post(
						root + "/json/party/team/"
								+ encodeURIComponent(self.name())).done(
						function() {
							messageViewModel.ok("Equipo Creado");
							self.refresh();
						}).fail(function() {
					messageViewModel.error("Falló la Creación del Equipo");
				});
			};
			
			this.addUsers =function(){
				self.name(this.name);
				self.toAdd.removeAll();
				self.candidates.removeAll();
				var team = this; 
				$.ajax({
					  url: root + "/json/party/users",
					  dataType: 'json',
					  async: false,
					  success: function(data) {
					     $.each(data,function(index, user){
					    	 if(team.users.indexOf(user.username)==-1){
					    		 self.candidates.push(user.username);
					    	 }
					     });
					  }
					});
				$("#users").modal("show");
				
			};
			
			this.addToTeam =function(){
				$.each(self.toAdd(),function(index,data){
					$.ajax({
						  type: 'POST',
						  url: root+"/json/party/addUserToTeam/"+encodeURIComponent(data)+"/"+encodeURIComponent(self.name()),
						  async:false
						});
				});
				self.refresh();
			};
			
			
			this.deleteTeam = function() {
				var name = this.name;
				bootbox
						.confirm(
								'Desea Eliminar el Equipo ' + name,
								"Cancelar",
								"Eliminar",
								function(result) {
									if(result)
									$.ajax(root+ "/json/party/team/"+encodeURIComponent(name),
													{
														type : "DELETE",
														async : false
													})
											.done(
													function() {
														messageViewModel
																.ok("Equipo Eliminado");
													})
											.fail(
													function() {
														messageViewModel
																.error("No se puedo borrar el Equipo");
													});
									self.refresh();
								});

			};

		}
	</script>

	<script type="text/javascript">
		$("#menu_teams").addClass("active");
		var viewModel = new TeamsViewModel();
		ko.applyBindings(viewModel, document.getElementById("container"));
		viewModel.refresh();
	</script>
</body>
</html>