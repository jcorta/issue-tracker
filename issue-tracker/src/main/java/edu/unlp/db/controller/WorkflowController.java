package edu.unlp.db.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.unlp.db.dto.ItemStateDto;
import edu.unlp.db.dto.WorkflowDto;
import edu.unlp.db.service.WorkflowService;

@Controller
public class WorkflowController {

	@Autowired
	WorkflowService workflowService;

	@RequestMapping(value="/workflow", method = RequestMethod.POST)
	public @ResponseBody WorkflowDto createWorkflow(@RequestBody WorkflowDto workflowDto) {
		return workflowService.createWorkflow(workflowDto);
	}

	@RequestMapping(value="/workflow", method = RequestMethod.PUT)
	public @ResponseBody WorkflowDto editWorkflow(@RequestBody WorkflowDto workflowDto) {
		return workflowService.editWorkflow(workflowDto);
	}
	
	@RequestMapping(value="/workflow/delete", method = RequestMethod.DELETE)
	public @ResponseBody void deleteWorkflow(@RequestBody WorkflowDto workflowDto) {
		workflowService.deleteWorkflow(workflowDto.getOid());
	}
	
	@RequestMapping(value="/workflows", method = RequestMethod.GET)
	public @ResponseBody Collection<WorkflowDto> getWorkflows() {
		return workflowService.getWorkflows();
	}
	
	@RequestMapping(value="/itemState/{name}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteItemState(@PathVariable String name) {
		workflowService.deleteItemState(name);
	}
	
	@RequestMapping(value="/itemState/{name}/{description}", method = RequestMethod.POST)
	public @ResponseBody ItemStateDto createItemState(@PathVariable String name, @PathVariable String description) {
		return workflowService.createItemState(name, description);
	}
	
	@RequestMapping(value="/itemStates", method = RequestMethod.GET)
	public @ResponseBody Collection<ItemStateDto> getItemState() {
		return workflowService.getItemStates();
	}

}