package jandtocode.pokedex.dto;

public class PokemonListDTO {

    private Integer id;
    private String nombre;

    // Constructor vacío
    public PokemonListDTO() {}

    // Constructor con parámetros
    public PokemonListDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}