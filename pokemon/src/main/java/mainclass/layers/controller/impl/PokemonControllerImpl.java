package mainclass.layers.controller.impl;

import mainclass.layers.controller.PokemonController;
import mainclass.layers.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonControllerImpl implements PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Override
    public String getPokemonByType(@PathVariable("type") Integer type) {
        return pokemonService.getPokemonByType(type);
    }


    @Override
    public String getPokemonByGeneration(@PathVariable("generation") Integer generation) {
        return pokemonService.getPokemonByGeneration(generation);
    }
}