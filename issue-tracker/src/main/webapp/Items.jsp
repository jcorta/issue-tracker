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
			Incidencias <a href="#" role="button" class="btn"
				data-bind="click: openCreateItem"><i class="icon-plus-sign"></i></a>
		</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Tipo</th>
					<th>Tema</th>
					<th>Descripci&oacute;n</th>
					<th>Prioridad</th>
					<th>Estado</th>
					<th>Fecha</th>
					<th>Usuario</th>
				</tr>
			</thead>
			<tbody data-bind="foreach: items">
				<tr>
					<td data-bind="text: oid"></td>
					<td data-bind="text: itemTypeName"></td>
					<td data-bind="text: subject"></td>
					<td data-bind="text: description"></td>
					<td data-bind="text: priority"></td>
					<td data-bind="text: state"></td>
					<td data-bind="text: created"></td>
					<td data-bind="text: user"></td>
					<td>
						<p class="text-right">
							<a class="btn" data-bind="click: $root.openEditItem"><i class="icon-pencil"></i></a>
							<a class="btn" data-bind="click: $root.openChangeState"><i class="icon-forward"></i></a>
							<a class="btn" data-bind="click: $root.openTimelime"><i class="icon-time"></i></a>
							<a class="btn" data-bind="click: $root.openComments"><i class="icon-comment"></i></a>
						</p>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- div del popup -->
		<div id="myModal" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3 data-bind="text: popupTitle">Agregar una Incidencia</h3>
			</div>
			<div class="modal-body">
				<form onsubmit="javascript:return false;">
					<label>Tema</label> <input id="subject" class="input-block-level" data-bind="value: subject" /> 
					<label>Tipo</label> <select id="itemType" class="input-block-level" data-bind="options:itemTypes, optionsText:'optionText', value:itemType"></select> 
					<label>Prioridad</label> <select id="itemPriority" class="input-block-level" data-bind="options:itemPriorities, optionsText:'name', value:itemPriority"></select>
					<label>Estado</label> <select id="state" class="input-block-level" data-bind="options:states, optionsText:'name', value:state"></select>
					<label>Descripci&oacute;n</label> <textarea id="description" class="input-block-level" rows="4" cols="4" data-bind="value: description"></textarea>
					<label>Usuario</label> <select id="user" class="input-block-level" data-bind="options:users, optionsText:'username', value:user"></select>
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
				<a href="#" class="btn btn-primary"
					data-bind="click: sendItem" data-dismiss="modal">Guardar</a>
			</div>
		</div>
		<div id="changeStateModal" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Cambio de Estado</h3>
			</div>
			<div class="modal-body">
				<form onsubmit="javascript:return false;">
					<label>Pr&oacute;ximo Estado</label><select id="state" class="input-block-level" data-bind="options:states, optionsText:'name', value:state"></select>
					<label>Usuario</label> <select id="user" class="input-block-level" data-bind="options:users, optionsText:'username', value:user"></select>
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
				<a href="#" class="btn btn-primary"
					data-bind="click: sendItem" data-dismiss="modal">Guardar</a>
			</div>
		</div>
		<div id="timeLineModal" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Historial de Cambios</h3>
			</div>
			<div class="modal-body">
				<table class="table">
					<thead>
						<tr>
							<th>Origen</th>
							<th>Destino</th>
							<th>Fecha</th>
							<th>Usuario</th>
						</tr>
					</thead>
					<tbody data-bind="foreach: historyEntries">
						<tr>
							<td data-bind="text: fromState"></td>
							<td data-bind="text: toState"></td>
							<td data-bind="text: changedDate"></td>
							<td data-bind="text: changedBy"></td>
						</tr>
					</tbody> 
				</table>
				
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cerrar</a>
			</div>
		</div>
		<div id="commentsModal" class="modal hide fade">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Comentarios</h3>
			</div>
			<div class="modal-body">
				<table class="table" data-bind="with:item">
					<thead>
						<tr>
							<th>Comentario</th>
							<th>Usuario</th>
							<th>Fecha</th>
						</tr>
					</thead>
					<tbody data-bind="foreach: comments">
						<tr>
							<td data-bind="text: comment"></td>
							<td data-bind="text: user"></td>
							<td data-bind="text: date"></td>
						</tr>
					</tbody> 
				</table>
				<form onsubmit="javascript:return false;">
					<label>Comentario</label><textarea id="comment" class="input-block-level" rows="4" cols="4" data-bind="value: comment"></textarea>
					<label>Usuario</label> <select id="user" class="input-block-level" data-bind="options:users, optionsText:'username', value:user"></select>
				</form>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn btn-primary" data-bind="click: addComment" data-dismiss="modal">Guardar</a>
				<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cerrar</a>
			</div>
		</div>
		<!-- fin div popup -->
	</div>
	<!-- fin container -->

	<!-- javascript -->
	<script type="text/javascript">
		function ItemViewModel() {
			var self = this;
			this.method = ko.observable("POST");
			this.popupTitle=ko.observable();	
			this.okMessage = "Item Creado";
			this.subject = ko.observable();
			this.description = ko.observable();
			this.itemPriority = ko.observable();
			this.itemType = ko.observable();
			this.state = ko.observable();
			this.user = ko.observable();
			this.comment = ko.observable();
			this.item = ko.observable();
			
			this.items = ko.observableArray();
			this.itemPriorities = ko.observableArray();
			this.itemTypes = ko.observableArray();
			this.states = ko.observableArray();
			this.users = ko.observableArray();
			this.historyEntries = ko.observableArray();

			
			this.refresh = function() {
				$.getJSON(root + "/json/itemPriorities", function(data) {
					self.itemPriorities(data);
				});
				$.getJSON(root + "/json/itemTypes", function(data) {
					$.each(data, function(){
						this.optionText = this.workflow + ' - ' + this.name;
					});
					self.itemTypes(data);
				});
				$.getJSON(root + "/json/itemStates", function(data) {
					self.states(data);
				});
				$.getJSON(root + "/json/party/users", function(data) {
					self.users(data);
				});
				$.getJSON(root + "/json/items", function(data) {
					self.items(data);
				});
				
			};
			
			this.openCreateItem = function(){
				self.method("POST");
				self.popupTitle("Agregar Incidencia");
				self.okMessage="Incidencia Creada";
				self.item(new Object());
				$("#myModal").modal();
			};
			
			this.openTimelime = function(){
				self.historyEntries(this.stateHistory);
				$("#timeLineModal").modal();
			};
			
			this.openComments = function(){
				self.item(this);
				$("#commentsModal").modal();
			};
			
			
			this.openChangeState = function(){
				self.okMessage="Se ha Cambiado el estado del Item";
				self.setEditionMode(this);
				item = this;
				var workflow=new Object();
				var workflowName = $.grep(self.itemTypes(),function(i){
					return i.name==item.itemTypeName;
				})[0].workflow;
				$.ajax({
					  dataType: "json",
					  url: root + "/json/workflows",
					  async: false,
					  success: function(data) {
							workflow= $.grep(data,
									function(e){ return e.name == workflowName ;
							})[0];
						}});
				
				var destinations = workflow.stateTransitions[this.state];
				self.states(
						$.grep(self.states(), function(e){
							return destinations.indexOf(e.name)> -1;
						}));
				$("#changeStateModal").modal();
			};
			
			this.openEditItem = function(){
				self.popupTitle("Editar Incidencia");
				self.okMessage="Incidencia Actualizada";
				self.setEditionMode(this);
				$("#myModal").modal();
			};
			
			
			
			this.setEditionMode=function(itemToEdit){
				self.method("PUT");
				self.item(itemToEdit);
				self.subject(itemToEdit.subject);
				self.description(itemToEdit.description);
				self.state($.grep(self.states(),function(e){ return e.name == itemToEdit.state; })[0]);
				self.itemPriority($.grep(self.itemPriorities(),function(e){ return e.name == itemToEdit.priotity; })[0]);
				self.itemType($.grep(self.itemTypes(),function(e){ return e.name == itemToEdit.itemTypeName; })[0]);
			};
			
			
			this.addComment = function(){
					$.post(
							root + "/json/item/addComment/"
									+ encodeURIComponent(self.item().oid) + "/"
									+ encodeURIComponent(self.user().username) + "/"
									+ encodeURIComponent(self.comment())).done(
							function() {
								messageViewModel.ok("Comentario Agregado");
								self.refresh();
							}).fail(function() {
						messageViewModel.error("Fallo la Creación del Comentario");
					});
			};
			
			
			this.sendItem = function() {
				var item = new Object();
			    self.item().subject = self.subject();
				self.item().description = self.description();
				self.item().state = self.state().name;
				self.item().priority = self.itemPriority().name;
				self.item().itemTypeName = self.itemType().name;
				delete self.item().created;
				delete self.item().comments;
				delete self.item().stateHistory;
				var url = root + "/json/item";
				if(self.method()=="PUT"){
					delete self.item().user;
					url= url +"/"+self.user().username;
				}else{
					self.item().user = self.user().username;
				}
				
				$.ajax({
					 type: self.method(),
					 url: url,
					 data: $.toJSON(self.item()),
					 async: false,
					 dataType: "json",
					 contentType: "application/json"
					 }).done(
						function() {
							messageViewModel.ok(self.okMessage);
							self.refresh();
						}).fail(function() {
							messageViewModel.error("Fallo el envio de la Incidencia");
							self.refresh();
				});
			};

		}
	</script>

	<script type="text/javascript">
		$("#menu_items").addClass("active");
		var viewModel = new ItemViewModel();
		ko.applyBindings(viewModel, document.getElementById("container"));
		viewModel.refresh();
	</script>
</body>
</html>