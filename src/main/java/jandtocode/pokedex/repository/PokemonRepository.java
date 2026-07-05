package jandtocode.pokedex.repository;

import jandtocode.pokedex.dto.PokemonListDTO;
import jandtocode.pokedex.entity.Pokemon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    @Query("SELECT new jandtocode.pokedex.dto.PokemonListDTO(p.id, p.nombre) FROM Pokemon p ORDER BY p.id ASC")
    List<PokemonListDTO> findAllBasic(Pageable pageable);

}