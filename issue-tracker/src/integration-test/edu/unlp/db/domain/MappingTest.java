package edu.unlp.db.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.unlp.db.domain.ItemState;
import edu.unlp.db.domain.ItemType;
import edu.unlp.db.domain.Team;
import edu.unlp.db.domain.Workflow;
import edu.unlp.db.domain.WorkflowItem;

public class MappingTest {
	ConfigurableApplicationContext applicationContext;
	private Session session;
	private Transaction transaction;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
                "/context.xml");
        applicationContext.registerShutdownHook();
        
        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		//Rollbackea
		transaction.rollback();
	}

//	@Test
//	public void testAddItemPriority() {
//		ItemPriority itemPriority = new ItemPriority();
//		itemPriority.setName("URGENTE");
//		itemPriority.setDescription("Alta prioridad");
//		
//		session.save(itemPriority);
//		
//		session.flush();
//		Assert.assertTrue("Bajo bien a la db", true);
//	}
//	
//	@Test
//	public void testAddItemState() {
//		ItemState itemState = new ItemState();
//		itemState.setName("CREADO");
//		itemState.setDescription("Recién creado");
//		
//		session.save(itemState);
//		
//		session.flush();
//		Assert.assertTrue("Bajo bien a la db", true);
//	}
	
//	@Test
//	public void testAddWorkflow() {
//		ItemState creado = new ItemState();
//		creado.setName("CREADO");
//		
//		ItemState desarrollo = new ItemState();
//		desarrollo.setName("DESARROLLO");
//		
//		ItemState validacion = new ItemState();
//		validacion.setName("VALIDACION");
//		
//		ItemState terminado = new ItemState();
//		terminado.setName("TERMINADO");
//		
//		session.save(creado);
//		session.save(desarrollo);
//		session.save(validacion);
//		session.save(terminado);
//		
//		Set<ItemState> toStates;
//
//		WorkflowItem fromCreado = new WorkflowItem();
//		fromCreado.setFromState(creado);
//		toStates = new HashSet<ItemState>();
//		toStates.add(desarrollo);
//		fromCreado.setToStates(toStates);
//		
//		WorkflowItem fromDesarrollo = new WorkflowItem();
//		fromDesarrollo.setFromState(desarrollo);
//		toStates = new HashSet<ItemState>();
//		toStates.add(validacion);
//		fromDesarrollo.setToStates(toStates);
//		
//		WorkflowItem fromValidacion = new WorkflowItem();
//		fromValidacion.setFromState(validacion);
//		toStates = new HashSet<ItemState>();
//		toStates.add(desarrollo);
//		toStates.add(terminado);
//		fromValidacion.setToStates(toStates);
//		
//		Map<ItemState,WorkflowItem> items = new HashMap<ItemState,WorkflowItem>();
//		items.put(creado, fromCreado);
//		items.put(desarrollo, fromDesarrollo);
//		items.put(validacion, fromValidacion);
//		
//		Workflow workflow = new Workflow();
//		workflow.setName("Circuito de bug fix");
//		workflow.setValidSecuenceStates(items);
//		
//		session.save(workflow);
//		
//		session.flush();
//
//		Assert.assertTrue("Bajo bien a la db", true);
//	}
	
	@Test
	public void testAddItem() {
		ItemState creado = new ItemState();
		creado.setName("CREADO");

		ItemState desarrollo = new ItemState();
		desarrollo.setName("DESARROLLO");

		ItemState validacion = new ItemState();
		validacion.setName("VALIDACION");

		ItemState terminado = new ItemState();
		terminado.setName("TERMINADO");

		session.save(creado);
		session.save(desarrollo);
		session.save(validacion);
		session.save(terminado);

		Set<ItemState> toStates;

		WorkflowItem fromCreado = new WorkflowItem();
		fromCreado.setFromState(creado);
		toStates = new HashSet<ItemState>();
		toStates.add(desarrollo);
		fromCreado.setToStates(toStates);

		WorkflowItem fromDesarrollo = new WorkflowItem();
		fromDesarrollo.setFromState(desarrollo);
		toStates = new HashSet<ItemState>();
		toStates.add(validacion);
		fromDesarrollo.setToStates(toStates);

		WorkflowItem fromValidacion = new WorkflowItem();
		fromValidacion.setFromState(validacion);
		toStates = new HashSet<ItemState>();
		toStates.add(desarrollo);
		toStates.add(terminado);
		fromValidacion.setToStates(toStates);

		Map<ItemState, WorkflowItem> items = new HashMap<ItemState, WorkflowItem>();
		items.put(creado, fromCreado);
		items.put(desarrollo, fromDesarrollo);
		items.put(validacion, fromValidacion);

		Workflow workflow = new Workflow();
		workflow.setName("Circuito de bug fix");
		workflow.setValidSecuenceStates(items);

		session.save(workflow);
		
		Team team1 = new Team();
		team1.setName("Equipo desa1");
		Team team2 = new Team();
		team2.setName("Equipo desa2");
		
		session.save(team1);
		session.save(team2);
		
		Set<Team> teams = new HashSet<Team>();
		teams.add(team1);
		teams.add(team2);
		
		ItemType itemType = new ItemType();
		itemType.setName("Bug");
		itemType.setDescription("Cualquier tipo de incidente");
		itemType.setWorkflow(workflow);
		itemType.setPosibleTeams(teams);
		session.save(itemType);
		
		session.flush();
		
		Assert.assertTrue("Bajo bien a la db", true);
	}

}
