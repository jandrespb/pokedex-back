package jandtocode.pokedex.controller;

import jandtocode.pokedex.dto.PokemonDetailDTO;
import jandtocode.pokedex.dto.PokemonListDTO;
import jandtocode.pokedex.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<PokemonListDTO>> obtenerPokemon(
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(pokemonService.obtenerPokemon(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDetailDTO> obtenerDetalle(@PathVariable Integer id) {
        return ResponseEntity.ok(pokemonService.obtenerDetalle(id));
    }
}