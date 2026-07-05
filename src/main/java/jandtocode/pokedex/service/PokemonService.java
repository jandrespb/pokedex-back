package jandtocode.pokedex.service;

import jandtocode.pokedex.dto.PokemonListDTO;
import jandtocode.pokedex.repository.PokemonRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    // Inyección por constructor
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<PokemonListDTO> obtenerPokemon(int page) {
        Pageable pageable = PageRequest.of(page, 4);
        return pokemonRepository.findAllBasic(pageable);
    }

}