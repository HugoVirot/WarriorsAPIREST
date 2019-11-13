package hello;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import io.vavr.control.Option;
import org.springframework.web.bind.annotation.*;
import warriors.contracts.*;
import warriors.engine.Game;
import warriors.engine.Warriors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import warriors.model.BaseCase;
import warriors.model.EmptyCase;
import warriors.model.MapModel;

@RestController
public class RequestController<Games> {

    private static final WarriorsAPI api = new Warriors();
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    RequestController() {
    }

    @GetMapping("/heroes")
    List<Hero> getHeroesListFromIterator() {
        Iterable<Hero> heroList = api.availableHeroes();
        List<Hero> heroesList = new ArrayList<Hero>();
        heroList.forEach(heroesList::add);
        return heroesList;
    }

    @GetMapping("/maps")
    List<Map> getMapsListFromIterator() {
        Iterable<Map> mapList = api.availableMaps();
        List<Map> mapsList = new ArrayList<Map>();
        mapList.forEach(mapsList::add);
        return mapsList;
    }

    //    https://stackoverflow.com/questions/29313687/trying-to-use-spring-boot-rest-to-read-json-string-from-post
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/games")
    public GameState createGame(@RequestBody java.util.Map<String, Object> payload) {
        System.out.println(payload);

        Iterable<Hero> heroList = api.availableHeroes();
        List<Hero> heroesList = new ArrayList<Hero>();
        heroList.forEach(heroesList::add);
        Integer heroIndex = (Integer) payload.get("hero");
        Hero hero = heroesList.get(heroIndex);

        Iterable<Map> mapList = api.availableMaps();
        List<Map> mapsList = new ArrayList<Map>();
        mapList.forEach(mapsList::add);
        Integer mapIndex = (Integer) payload.get("map");
        Map map = mapsList.get(mapIndex);

        return api.createGame((String) payload.get("playerName"), hero, map);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/games/{id}/turn")
    Option<GameState> gameStateId (@PathVariable String id){
        logger.info(id);
        GameId gameId = GameId.parse(id);
        return api.nextTurn(gameId);
    }

//    Option<Game> show(GameId gameId);
//    Iterable<Game> listGames();

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/games/{id}")        // affiche l'état d'une partie en cours (actualisation)
    Option<Game> showGame (@PathVariable String id){
        GameId gameId = GameId.parse(id);
        return api.show(gameId);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/games")            // affiche liste parties en cours (bouton Observe games)
    List<Game> getGames() {
        Iterable<Game> gameList = api.listGames();
        List<Game> gamesList = new ArrayList<Game>();
        gameList.forEach(gamesList::add);
        return gamesList;
    }
}