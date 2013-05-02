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
			Workflows<a href="#myModal" role="button" class="btn"
				data-toggle="modal"><i class="icon-plus-sign"></i></a>
		</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th></th>
				</tr>
			</thead>
			<tbody data-bind="foreach: workflows">
				<tr>
					<td data-bind="text: oid"></td>
					<td data-bind="text: name"></td>
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
						data-bind="value: name" />
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
				<a href="#" class="btn btn-primary"
					data-bind="click: createWorkflow" data-dismiss="modal">Guardar</a>
			</div>
		</div>
		<!-- fin div popup -->
	</div>
	<!-- fin container -->

	<!-- javascript -->
	<script type="text/javascript">
		function WorkflowViewModel() {
			var self = this;
			this.name = ko.observable();
			this.description = ko.observable();
			this.workflows = ko.observableArray();
			this.refresh = function() {
				$.getJSON(root + "/json/workflows", function(data) {
					self.workflows(data);
				});
			};
			this.createWorkflow = function() {
				var workflow= new Workflow();
				 workflow.name=this.name();
				 $.ajax({
					 type: "POST",
					 url: root + "/json/workflow",
					 data: $.toJSON(workflow),
					 async: false,
					 dataType: "json",
					 contentType: "application/json"
					 }).done(
						function() {
							messageViewModel.ok("Workflow Creado");
							self.refresh();
						}).fail(function() {
					messageViewModel.error("Fallo la Creación del Workflow");
				});
			};
			this.deleteItemState = function() {
				var workflow = new Workflow();
				workflow.oid= this.oid
				bootbox
						.confirm(
								'Desea Eliminar el Workflow ' + this.name,
								"Cancelar",
								"Eliminar",
								function(result) {
									if(result)
									$.ajax(root+ "/json/workflow/delete",
													{
														type : "DELETE",
														async : false,
														data: $.toJSON(workflow),
														dataType: "json",
														contentType: "application/json",
														error: function(){ messageViewModel.ok("Workflow Eliminado"); },
														statusCode : { 200: function() {
																				messageViewModel.ok("Workflow Eliminado");
																			}
																	 }
													});
									self.refresh();	
								});

			};

		}
	</script>

	<script type="text/javascript">
		$("#menu_workflows").addClass("active");
		var viewModel = new WorkflowViewModel();
		ko.applyBindings(viewModel, document.getElementById("container"));
		viewModel.refresh();
	</script>
</body>
</html>