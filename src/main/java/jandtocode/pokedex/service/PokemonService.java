package jandtocode.pokedex.service;

import jandtocode.pokedex.dto.JuegoDTO;
import jandtocode.pokedex.dto.PokemonDetailDTO;
import jandtocode.pokedex.dto.PokemonListDTO;
import jandtocode.pokedex.dto.PoderDTO;
import jandtocode.pokedex.dto.TipoDTO;
import jandtocode.pokedex.entity.Pokemon;
import jandtocode.pokedex.exception.PaginaInvalidaException;
import jandtocode.pokedex.exception.ParametroInvalidoException;
import jandtocode.pokedex.exception.PokemonNotFoundException;
import jandtocode.pokedex.repository.PokemonRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Transactional(readOnly = true)
    public List<PokemonListDTO> obtenerPokemon(int page) {

        if (page < 0) {
            throw new PaginaInvalidaException(page);
        }

        long total = pokemonRepository.count();
        int maxPage = (int) Math.ceil((double) total / 4) - 1;

        if (page > maxPage) {
            throw new PaginaInvalidaException(page);
        }

        Pageable pageable = PageRequest.of(page, 4);
        return pokemonRepository.findAllBasic(pageable);
    }

    @Transactional(readOnly = true)
    public PokemonDetailDTO obtenerDetalle(Integer id) {

        if (id < 0) {
            throw new PaginaInvalidaException(id);
        }

        Pokemon pokemon = pokemonRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new PokemonNotFoundException(id));

        return mapearDetalle(pokemon);
    }

    @Transactional(readOnly = true)
    public PokemonDetailDTO buscarPokemon(String query) {

        String mensajeContexto = "Solo se permiten cadenas de texto sin caracteres especiales y números enteros positivos";

        // Validar longitud máxima
        if (query.length() > 12) {
            throw new ParametroInvalidoException(query, mensajeContexto);
        }

        // Es número → busca por id
        if (query.matches("[0-9]+")) {
            Integer id = Integer.parseInt(query);
            return obtenerDetalle(id);
        }

        // Validar solo letras
        if (!query.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            throw new ParametroInvalidoException(query, mensajeContexto);
        }

        // Capitalizar
        String nombreCapitalizado = query.substring(0, 1).toUpperCase() + query.substring(1).toLowerCase();

        // Buscar por nombre
        Pokemon pokemon = pokemonRepository.findByNameWithRelations(nombreCapitalizado)
                .orElseThrow(() -> new PokemonNotFoundException(nombreCapitalizado));

        return mapearDetalle(pokemon);
    }

    // Método privado reutilizable
    private PokemonDetailDTO mapearDetalle(Pokemon pokemon) {

        TipoDTO tipoDTO = new TipoDTO(
                pokemon.getTipo().getId(),
                pokemon.getTipo().getNombre()
        );

        Set<PoderDTO> poderesDTO = pokemon.getPoderes().stream()
                .map(poder -> new PoderDTO(poder.getId(), poder.getNombre()))
                .collect(Collectors.toSet());

        Set<TipoDTO> debilidadesDTO = pokemon.getDebilidades().stream()
                .map(debilidad -> new TipoDTO(debilidad.getId(), debilidad.getNombre()))
                .collect(Collectors.toSet());

        Set<JuegoDTO> juegosDTO = pokemon.getJuegos().stream()
                .map(juego -> new JuegoDTO(juego.getId(), juego.getNombre()))
                .collect(Collectors.toSet());

        return new PokemonDetailDTO(
                pokemon.getId(),
                pokemon.getNombre(),
                tipoDTO,
                pokemon.getFamilia(),
                pokemon.getEvolucion(),
                pokemon.getAltura(),
                pokemon.getPeso(),
                pokemon.getImagenUrl(),
                poderesDTO,
                debilidadesDTO,
                juegosDTO
        );
    }
}