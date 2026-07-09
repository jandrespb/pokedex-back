package jandtocode.pokedex.exception;

public class PokemonNotFoundException extends RuntimeException{
    public PokemonNotFoundException(Integer id) {
        super("Pokemon con id " + id + " no encontrado");
    }
}
