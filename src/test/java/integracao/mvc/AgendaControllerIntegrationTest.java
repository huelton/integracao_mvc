package integracao.mvc;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import integracao.mvc.contatos.Contato;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class AgendaControllerIntegrationTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Contato contato;
	
	@Before
	public void start() throws Exception {
		contato = new Contato("Chefe", "0y","0xxxxxxx0");
		testEntityManager.persist(contato);
		
	}
	
	@After
	public void end() throws Exception {
		testEntityManager.getEntityManager().createQuery("DELETE FROM Contato").executeUpdate();
	}
	

	@Test
	public void checarStatus() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));		
		resultActions.andDo(MockMvcResultHandlers.print());
		
		Integer status = resultActions.andReturn().getResponse().getStatus();
		
		Assert.assertTrue(status.equals(200));
		
	}
	
	@Test
	public void checarView() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		resultActions.andDo(MockMvcResultHandlers.print());
		ModelAndView mav = resultActions.andReturn().getModelAndView();
		
		Assert.assertEquals("agenda", mav.getViewName());
		
		

	}
	
	@Test
	public void checarModel() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		resultActions.andDo(MockMvcResultHandlers.print());
		
		ModelAndView mav = resultActions.andReturn().getModelAndView();
		
		List<Contato> contatos = (List<Contato>) mav.getModel().get("contatos");
		Assert.assertEquals(1, contatos.size());
		Assert.assertTrue(contatos.contains(contato));

	}
}
