package jandtocode.pokedex.controller;

import jandtocode.pokedex.dto.PokemonListDTO;
import jandtocode.pokedex.service.PokemonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    // Inyección por constructor
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<PokemonListDTO> obtenerPokemon(@RequestParam(defaultValue = "0") int page) {
        return pokemonService.obtenerPokemon(page);
    }

}