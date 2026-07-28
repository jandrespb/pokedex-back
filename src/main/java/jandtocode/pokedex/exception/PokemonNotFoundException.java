package jandtocode.pokedex.exception;

public class PokemonNotFoundException extends RuntimeException{
    public PokemonNotFoundException(Integer id) {
        super("Pokemon con id " + id + " no encontrado");
    }

    public PokemonNotFoundException(String nombre) {
        super("Pokemon con nombre '" + nombre + "' no encontrado");
    }
}
