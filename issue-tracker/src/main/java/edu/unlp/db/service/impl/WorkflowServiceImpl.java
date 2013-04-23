package edu.unlp.db.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.Assert;

import edu.unlp.db.domain.ItemState;
import edu.unlp.db.domain.Tracker;
import edu.unlp.db.domain.Workflow;
import edu.unlp.db.dto.ItemStateDto;
import edu.unlp.db.dto.WorkflowDto;
import edu.unlp.db.service.WorkflowService;

public class WorkflowServiceImpl extends EntityService implements WorkflowService {

	public WorkflowDto createWorkflow(WorkflowDto workflowDto) {
		Assert.notNull(workflowDto.getName(), "name no puede ser null");

		Workflow workflow = mapper.map(workflowDto, Workflow.class);

		getTracker().addWorkflow(workflow);
		
		trackerLoader.refreshOids();

		return mapper.map(workflow, WorkflowDto.class);
	}

	public WorkflowDto editWorkflow(WorkflowDto workflowDto) {
		Assert.isTrue(workflowDto.getOid() > 0,
				"Oid tiene que ser mayor a cero");
		Assert.isTrue(workflowDto.getVersion() >= 0,
				"version tiene que ser mayor a cero");

		Workflow workflow = getWorkflowById(workflowDto.getOid());
		if (workflow.getVersion() > workflowDto.getVersion()) {
			throw new RuntimeException(
					"No puede actualizar el workflow, hay una version mas nueva");
		}
		
		Workflow workflowC = mapper.map(workflowDto, Workflow.class);

		workflow.setName(workflowC.getName());
		workflow.replaceValidSecuenceStatesWith(workflowC.getValidSecuenceStates());

		return mapper.map(workflow, WorkflowDto.class);
	}

	private Workflow getWorkflowById(long oid) {
		Workflow ret = null;
		for (Workflow wf : getTracker().getWorkflows()) {
			if (wf.getOid() == oid) {
				ret = wf;
			}
		}
		if (ret == null) {
			throw new RuntimeException("No se encontro workflow con id " + oid);
		}
		return ret;
	}

	public void deleteWorkflow(long oid) {
		Tracker tracker = getTracker();
		Collection<Workflow> workflows = tracker.getWorkflows();
		Workflow workflow1 = null;
		for (Workflow workflow : workflows) {
			if (oid == workflow.getOid()) {
				workflow1 = workflow;
			}
		}
		if (workflow1 == null) {
			throw new RuntimeException("Workflow no existe");
		} else {
			tracker.removeWorkflow(workflow1);
		}
	}

	public Collection<WorkflowDto> getWorkflows() {
		Collection<Workflow> workflows = getTracker().getWorkflows();
		Collection<WorkflowDto> ret = new ArrayList<WorkflowDto>();
		for (Workflow workflow : workflows) {
			ret.add(mapper.map(workflow, WorkflowDto.class));
		}
		return ret;
	}

	public Collection<ItemStateDto> getItemStates() {
		Collection<ItemState> itemStates = getTracker().getItemStates();
		Collection<ItemStateDto> ret = new ArrayList<ItemStateDto>();
		for (ItemState itemState : itemStates) {
			ret.add(mapper.map(itemState, ItemStateDto.class));
		}
		return ret;
	}

	public ItemStateDto createItemState(String name, String description) {
		ItemState itemState = new ItemState();
		itemState.setName(name);
		itemState.setDescription(description);
		getTracker().addItemState(itemState);
		trackerLoader.refreshOids();
		
		return mapper.map(itemState, ItemStateDto.class);
	}

	public void deleteItemState(String stateName) {
		Tracker tracker = getTracker();
		Collection<ItemState> itemStates = tracker.getItemStates();
		ItemState itemState1 = null;
		for (ItemState itemState : itemStates) {
			if (stateName.equals(itemState.getName())) {
				itemState1 = itemState;
			}
		}
		if (itemState1 == null) {
			throw new RuntimeException("Estado no existe");
		}
		tracker.removeItemState(itemState1);
	}

}
