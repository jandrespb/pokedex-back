package jandtocode.pokedex.exception;

public class ParametroInvalidoException extends RuntimeException {
    public ParametroInvalidoException(String parametro) {
        super("El parámetro '" + parametro + "' no es válido, solo se permiten valores numéricos enteros positivos");
    }
}
