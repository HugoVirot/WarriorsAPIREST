package testpackage;

import warriorsapirest.WarriorsApirestApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WarriorsApirestApplication.class})

public class RequestControllerTest {

    public static final Logger logger = LoggerFactory.getLogger(RequestControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void heroesTest() throws Exception {
        mockMvc.perform(get("/heroes"))   //Lancer une requête Rest de type GET pour l'url '/data'
                .andExpect(status().isOk())          //Assert le statut de la réponse http est égal a OK.
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))  //Assert l’existence d'une réponse json.
                .andDo(print())                  //Afficher la réponse.
                .andExpect(content().json("[{\"name\":\"Guerrier\", \"life\":5,\"attackLevel\":10,\"dead\":false},{\"name\":\"Magicien\",\"life\":3,\"attackLevel\":6,\"dead\":false}]"));
    }

    @Test
    public void mapsTest() throws Exception {
        mockMvc.perform(get("/maps"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json("[{\"name\":\"Default Map\",\"cases\":[{\"type\":\"EMPTY\"},{\"equipmentValue\":2,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"equipmentValue\":2,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":3,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":2,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"type\":\"EMPTY\"},{\"equipmentValue\":2,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"equipmentValue\":3,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":2,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Gobelin\",\"life\":6,\"attack\":1},\"type\":\"ENEMY\"},{\"equipmentValue\":2,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"equipmentValue\":1,\"type\":\"EQUIPMENT\"},{\"type\":\"EMPTY\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"equipmentValue\":3,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":2,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"equipmentValue\":5,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":5,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":2,\"type\":\"EQUIPMENT\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"ennemy\":{\"name\":\"Dragon\",\"life\":15,\"attack\":4},\"type\":\"ENEMY\"},{\"type\":\"EMPTY\"},{\"ennemy\":{\"name\":\"Sorcier\",\"life\":9,\"attack\":2},\"type\":\"ENEMY\"},{\"equipmentValue\":7,\"type\":\"EQUIPMENT\"},{\"equipmentValue\":7,\"type\":\"EQUIPMENT\"},{\"type\":\"EMPTY\"},{\"type\":\"EMPTY\"},{\"ennemy\":{\"name\":\"Dragon\",\"life\":15,\"attack\":4},\"type\":\"ENEMY\"},{\"equipmentValue\":5,\"type\":\"EQUIPMENT\"},{\"type\":\"EMPTY\"},{\"type\":\"EMPTY\"},{\"ennemy\":{\"name\":\"Dragon\",\"life\":15,\"attack\":4},\"type\":\"ENEMY\"},{\"type\":\"EMPTY\"},{\"type\":\"EMPTY\"},{\"type\":\"EMPTY\"},{\"type\":\"EMPTY\"},{\"type\":\"EMPTY\"},{\"ennemy\":{\"name\":\"Dragon\",\"life\":15,\"attack\":4},\"type\":\"ENEMY\"},{\"type\":\"EMPTY\"}],\"numberOfCase\":64}]"));
    }

    @Test
    public void createGameTest() throws Exception {
        mockMvc.perform(post("/games")
                .content("{\"playerName\": \"toto\", \"hero\": 0, \"map\": 0}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())                //il faut renvoyer code 201 au lieu de 200
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void checkGameState() throws Exception {
        MvcResult result = mockMvc.perform(post("/games")              //créer partie -> il faut récupérer l'id
                .content("{\"playerName\": \"toto\", \"hero\": 0, \"map\": 0}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())          //il faut renvoyer code 201 au lieu de 200
                .andReturn();
        String content = result.getResponse().getContentAsString();
        String id = content.substring(12,48);      //ne marche pas (id de longueur variable)
        logger.info(id);
        mockMvc.perform(get("/games/{id}")         //la récupérer à l'aide de l'id
                .param("id", id))
                .andExpect(status().isCreated());
    }

}
