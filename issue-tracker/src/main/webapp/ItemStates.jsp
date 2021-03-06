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
			Estados <a href="#myModal" role="button" class="btn"
				data-toggle="modal"><i class="icon-plus-sign"></i></a>
		</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Descripci&oacute;n</th>
					<th></th>
				</tr>
			</thead>
			<tbody data-bind="foreach: itemStates">
				<tr>
					<td data-bind="text: oid"></td>
					<td data-bind="text: name"></td>
					<td data-bind="text: description"></td>
					<td>
						<p class="text-right"><a class="btn" data-bind="click: $parent.deleteItemState"><i class="icon-trash"></i></a></p>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- div del popup -->
		<div id="myModal" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Agregar un Estado</h3>
			</div>
			<div class="modal-body">
				<form onsubmit="javascript:return false;">
					<label>Nombre</label> <input id="name" class="input-block-level"
						data-bind="value: name" /> <label>Descripci&oacute;n</label>
					<textarea id="description" class="input-block-level" rows="4"
						cols="4" data-bind="value: description"></textarea>
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
				<a href="#" class="btn btn-primary"
					data-bind="click: createItemState" data-dismiss="modal">Guardar</a>
			</div>
		</div>
		<!-- fin div popup -->
	</div>
	<!-- fin container -->

	<!-- javascript -->
	<script type="text/javascript">
		function ItemsStatesViewModel() {
			var self = this;
			this.name = ko.observable();
			this.description = ko.observable();
			this.itemStates = ko.observableArray();
			this.refresh = function() {
				$.getJSON(root + "/json/itemStates", function(data) {
					self.itemStates(data);
				});
			};
			this.createItemState = function() {
				$.post(
						root + "/json/itemState/"
								+ encodeURIComponent(self.name()) + "/"
								+ encodeURIComponent(self.description())).done(
						function() {
							messageViewModel.ok("Estado Creado");
							self.refresh();
						}).fail(function() {
					messageViewModel.error("Fallo la Creación del Estado");
				});
			};
			this.deleteItemState = function() {
				var name = this.name;
				bootbox
						.confirm(
								'Desea Eliminar el Estado ' + name,
								"Cancelar",
								"Eliminar",
								function(result) {
									if(result)
									$.ajax(root+ "/json/itemState/"+encodeURIComponent(name),
													{
														type : "DELETE",
														async : false
													})
											.done(
													function() {
														messageViewModel
																.ok("Estado Eliminado");
													})
											.fail(
													function() {
														messageViewModel
																.error("No se puedo borrar el Estado");
													});
									self.refresh();
								});

			};

		}
	</script>

	<script type="text/javascript">
		$("#menu_itemStates").addClass("active");
		var viewModel = new ItemsStatesViewModel();
		ko.applyBindings(viewModel, document.getElementById("container"));
		viewModel.refresh();
	</script>
</body>
</html>