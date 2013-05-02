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
		<h1>Usuario<a href="#myModal" role="button" class="btn"
				data-toggle="modal"><i class="icon-plus-sign"></i></a>
		</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Grupos</th>
					<th></th>
				</tr>
			</thead>
			<tbody data-bind="foreach: users">
				<tr>
					<td data-bind="text: oid"></td>
					<td data-bind="text: username"></td>
					<td data-bind="foreach:teams">
						<div class="row">
						<a class="btn" data-bind="click: $root.removeTeam.bind($parent)"><i class="icon-remove-sign"></i></a>
						<span data-bind="text: $data"></span>
						</div>
					</td>
					<td><p class="text-right">
						<a class="btn" data-bind="click: $parent.deleteUser"><i class="icon-trash"></i></a>
						<a class="btn" data-bind="click: $parent.addTeams"><i class="icon-wrench"></i></a></p>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- div del popup -->
		<div id="myModal" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Agregar un Usuario</h3>
			</div>
			<div class="modal-body">
				<form onsubmit="javascript:return false;">
					<label>Nombre</label> <input id="name" class="input-block-level"
						data-bind="value: name" /> <label>password</label>
					<input type="password" id="description" class="input-block-level" rows="4"
						cols="4" data-bind="value: password" />
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
				<a href="#" class="btn btn-primary" data-bind="click: createUser" data-dismiss="modal">Guardar</a>
			</div>
		</div>
		<!-- fin div popup -->
		<!-- inicio div popupTeams -->
		<div id="teams" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Agregar Equipos al Usuario</h3>
			</div>
			<div class="modal-body" data-bind="foreach: candidates">
				<div>
					<label class="checkbox"><span data-bind="text: $data"></span><input type="checkbox" data-bind="value:$data, checked:$root.toAdd"/></label>
				</div>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
				<a href="#" class="btn btn-primary" data-bind="click: addToUser" data-dismiss="modal">Guardar</a>
			</div>
		</div>
		<!-- fin div popupTeams -->
	</div>
	<!-- fin container -->

	<!-- javascript -->
	<script type="text/javascript">
		function UsersViewModel() {
			var self = this;
			this.name = ko.observable();
			this.password = ko.observable();
			this.users = ko.observableArray();
			this.toAdd = ko.observableArray();
			this.candidates = ko.observableArray();
			this.refresh = function() {
				$.getJSON(root + "/json/party/users", function(data) {
					self.users(data);
				});
			};
			this.createUser = function() {
				$.post(
						root + "/json/party/user/"
								+ encodeURIComponent(self.name()) + "/"
								+ encodeURIComponent(self.password())).done(
						function() {
							messageViewModel.ok("Usuario Creado");
							self.refresh();
						}).fail(function() {
					messageViewModel.error("Falló la Creación del Usuario");
				});
			};
			
			this.addTeams =function(){
				self.name(this.username);
				self.toAdd.removeAll();
				self.candidates.removeAll();
				var user = this; 
				$.ajax({
					  url: root + "/json/party/teams",
					  dataType: 'json',
					  async: false,
					  success: function(data) {
					     $.each(data,function(index, team){
					    	 if(user.teams.indexOf(team.name)==-1){
					    		 self.candidates.push(team.name);
					    	 }
					     });
					  }
					});
				$("#teams").modal("show");
			};
			
			
			this.removeTeam = function(team){
		    	var user = this.username;
		    	bootbox
				.confirm(
						'Desea Eliminar el equipo ' + team,
						"Cancelar",
						"Eliminar",
						function(result) {
							if(result)
							$.ajax(root+ "/json/party/removeUserFromTeam/"+encodeURIComponent(user)+"/"+encodeURIComponent(team),
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
														.error("No se puedo eliminar el Equipo");
											});
							self.refresh();
						});
			};
			
			this.addToUser= function(){
				$.each(self.toAdd(),function(index,data){
					$.ajax({
						  type: 'POST',
						  url: root+"/json/party/addUserToTeam/"+encodeURIComponent(self.name())+"/"+encodeURIComponent(data),
						  async:false
						});
				});
				self.refresh();
			}
			
			this.deleteUser = function() {
				var name = this.username;
				bootbox
						.confirm(
								'Desea Eliminar el Usuario ' + name,
								"Cancelar",
								"Eliminar",
								function(result) {
									if(result)
									$.ajax(root+ "/json/party/user/"+encodeURIComponent(name),
													{
														type : "DELETE",
														async : false
													})
											.done(
													function() {
														messageViewModel
																.ok("Usuario Eliminado");
													})
											.fail(
													function() {
														messageViewModel
																.error("No se puedo borrar el Usuario");
													});
									self.refresh();
								});

			};

		}
	</script>

	<script type="text/javascript">
		$("#menu_users").addClass("active");
		var viewModel = new UsersViewModel();
		ko.applyBindings(viewModel, document.getElementById("container"));
		viewModel.refresh();
	</script>
</body>
</html>