package jandtocode.pokedex.dto;

import java.util.Set;

public class PokemonDetailDTO {

    private Integer id;
    private String nombre;
    private TipoDTO tipo;
    private String familia;
    private String evolucion;
    private Double altura;
    private Double peso;
    private String imagenUrl;
    private Set<PoderDTO> poderes;
    private Set<TipoDTO> debilidades;
    private Set<JuegoDTO> juegos;

    // Constructor vacío
    public PokemonDetailDTO() {}

    // Constructor con parámetros
    public PokemonDetailDTO(Integer id, String nombre, TipoDTO tipo, String familia,
                            String evolucion, Double altura, Double peso, String imagenUrl,
                            Set<PoderDTO> poderes, Set<TipoDTO> debilidades, Set<JuegoDTO> juegos) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.familia = familia;
        this.evolucion = evolucion;
        this.altura = altura;
        this.peso = peso;
        this.imagenUrl = imagenUrl;
        this.poderes = poderes;
        this.debilidades = debilidades;
        this.juegos = juegos;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoDTO getTipo() {
        return tipo;
    }

    public String getFamilia() {
        return familia;
    }

    public String getEvolucion() {
        return evolucion;
    }

    public Double getAltura() {
        return altura;
    }

    public Double getPeso() {
        return peso;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public Set<PoderDTO> getPoderes() {
        return poderes;
    }

    public Set<TipoDTO> getDebilidades() {
        return debilidades;
    }

    public Set<JuegoDTO> getJuegos() {
        return juegos;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(TipoDTO tipo) {
        this.tipo = tipo;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public void setEvolucion(String evolucion) {
        this.evolucion = evolucion;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setPoderes(Set<PoderDTO> poderes) {
        this.poderes = poderes;
    }

    public void setDebilidades(Set<TipoDTO> debilidades) {
        this.debilidades = debilidades;
    }

    public void setJuegos(Set<JuegoDTO> juegos) {
        this.juegos = juegos;
    }
}