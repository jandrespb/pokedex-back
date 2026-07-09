package jandtocode.pokedex.exception;

public class PaginaInvalidaException extends RuntimeException {
    public PaginaInvalidaException(int page) {
        super("La página " + page + " no es válida, no puede ser negativa ni exceder el límite de pokémon disponibles");
    }
}
