package edu.unlp.db.service;

import java.util.Collection;

import edu.unlp.db.dto.ItemStateDto;
import edu.unlp.db.dto.WorkflowDto;

public interface WorkflowService {
	// El map indica estado de origen y estado de destino posible. Si ya existen
	// estados con ese nombre los usa y si no los crea
	public WorkflowDto createWorkflow(WorkflowDto workflowDto);
	public WorkflowDto editWorkflow(WorkflowDto workflowDto);
	public void deleteWorkflow(long oid);
	public Collection<WorkflowDto> getWorkflows();

	public ItemStateDto createItemState(String name, String description);
	public void deleteItemState(String name);
	public Collection<ItemStateDto> getItemStates();

}