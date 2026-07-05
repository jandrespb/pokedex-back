package jandtocode.pokedex.dto;

public class JuegoDTO {

    private Integer id;
    private String nombre;

    // Constructor vacío
    public JuegoDTO() {}

    // Constructor con parámetros
    public JuegoDTO(Integer id, String nombre) {
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