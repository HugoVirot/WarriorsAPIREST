package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;
import warriors.contracts.GameState;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.contracts.WarriorsAPI;
import warriors.engine.Game;
import warriors.engine.Warriors;
import warriors.model.Warrior;

@RestController
public class RequestController {

    private static final WarriorsAPI api = new Warriors();

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

//créer objets hero et map à partir des names
//récup listes héros et maps
//créer liste games
//ajouter game dans liste games
}