<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Issue Tracker</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<link rel="shortcut icon" href="./img/favicon.gif">
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
		<h1>
			Tipos de Incidencias <a href="#myModal" role="button" class="btn"
				data-toggle="modal"><i class="icon-plus-sign"></i></a>
		</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Descripci&oacute;n</th>
					<th>Workflow</th>
					<th>Equipos</th>
				</tr>
			</thead>
			<tbody data-bind="foreach: itemTypes">
				<tr>
					<td data-bind="text: oid"></td>
					<td data-bind="text: name"></td>
					<td data-bind="text: description"></td>
					<td data-bind="text: workflow"></td>
					<td data-bind="foreach: posibleTeams">
						<div>
							<i class="icon-user"></i> <span data-bind="text: $data"></span>
						</div>
					</td>
					<td>
						<p class="text-right"><a class="btn" data-bind="click: $parent.deleteItemType"><i class="icon-trash"></i></a></p>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- div del popup -->
		<div id="myModal" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Agregar una Tipo de Incidencia</h3>
			</div>
			<div class="modal-body">
				<form onsubmit="javascript:return false;">
					<label>Nombre</label> <input id="name" class="input-block-level" data-bind="value: name" /> 
					<label>Descripci&oacute;n</label> <textarea id="description" class="input-block-level" rows="4" cols="4" data-bind="value: description"></textarea>
					<label>Workflow</label><select id="workflow" class="input-block-level" data-bind="options:workflows, optionsText:'name', value:workflow"></select>
					<label>Equipos</label>
					<div data-bind="foreach: teams">
						<div>
							<label class="checkbox">
								<span data-bind="text: $data.name"></span>
								<input type="checkbox" data-bind="value:$data.name, checked:$root.selectedTeams"/>
							</label>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
				<a href="#" class="btn btn-primary"
					data-bind="click: createItemType" data-dismiss="modal">Guardar</a>
			</div>
		</div>
		<!-- fin div popup -->
	</div>
	<!-- fin container -->

	<!-- javascript -->
	<script type="text/javascript">
		
		function ItemsTypeViewModel() {
			var self = this;
			this.name = ko.observable();
			this.description = ko.observable();
			this.workflow = ko.observable();
			
			this.itemTypes = ko.observableArray();
			this.workflows = ko.observableArray();
			this.teams = ko.observableArray();
			this.selectedTeams = ko.observableArray();
			
			this.refresh = function() {
				$.getJSON(root + "/json/itemTypes", function(data) {
					self.itemTypes(data);
				});
				$.getJSON(root + "/json/workflows", function(data) {
					self.workflows(data);
				});
				$.getJSON(root + "/json/party/teams", function(data) {
					self.teams(data);
				});
			};
			this.createItemType = function() {
				var type = new Object();
				type.name = self.name();
				type.description = self.description();
				type.workflow = self.workflow().name;
				type.posibleTeams = self.selectedTeams();
				
				$.ajax({
					 type: "POST",
					 url: root + "/json/itemType",
					 data: $.toJSON(type),
					 async: false,
					 dataType: "json",
					 contentType: "application/json"
					 }).done(
						function() {
							messageViewModel.ok("Tipo de Incidencia Agregado");
							self.refresh();
						}).fail(function() {
					messageViewModel.error("Fallo la inclusión del Tipo de Incidencia");});
			};
			this.deleteItemType = function() {
				var name = this.name;
				bootbox
						.confirm(
								'Desea Eliminar el Tipo de Incidencia ' + name,
								"Cancelar",
								"Eliminar",
								function(result) {
									if(result)
									$.ajax(root+ "/json/itemType/"+encodeURIComponent(name),
													{
														type : "DELETE",
														async : false
													})
											.done(
													function() {
														messageViewModel
																.ok("Tipo de Incidencia Eliminado");
													})
											.fail(
													function() {
														messageViewModel
																.error("No se puedo borrar Tipo de Incidencia");
													});
									self.refresh();
								});

			};

		}
	</script>

	<script type="text/javascript">
		$("#menu_itemTypes").addClass("active");
		var viewModel = new ItemsTypeViewModel();
		ko.applyBindings(viewModel, document.getElementById("container"));
		viewModel.refresh();
	</script>
</body>
</html>