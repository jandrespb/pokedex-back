package jandtocode.pokedex.repository;

import jandtocode.pokedex.dto.PokemonListDTO;
import jandtocode.pokedex.entity.Pokemon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    @Query("SELECT new jandtocode.pokedex.dto.PokemonListDTO(p.id, p.nombre) FROM Pokemon p ORDER BY p.id ASC")
    List<PokemonListDTO> findAllBasic(Pageable pageable);

    @Query("SELECT p FROM Pokemon p " +
            "JOIN FETCH p.tipo " +
            "JOIN FETCH p.poderes " +
            "JOIN FETCH p.debilidades " +
            "JOIN FETCH p.juegos " +
            "WHERE p.id = :id")
    Optional<Pokemon> findByIdWithRelations(@Param("id") Integer id);

}