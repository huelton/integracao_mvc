package integracao.mvc;

import org.junit.After;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

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
	public void status() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
}
