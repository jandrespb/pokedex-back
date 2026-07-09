package jandtocode.pokedex.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    @Column(name = "familia")
    private String familia;

    @Column(name = "evolucion")
    private String evolucion;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pokemon_poder",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "poder_id")
    )
    private Set<Poder> poderes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pokemon_debilidad",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_id")
    )
    private Set<Tipo> debilidades;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pokemon_juego",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "juego_id")
    )
    private Set<Juego> juegos;

    // Constructor vacío
    public Pokemon() {}

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
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

    public Set<Poder> getPoderes() {
        return poderes;
    }

    public Set<Tipo> getDebilidades() {
        return debilidades;
    }

    public Set<Juego> getJuegos() {
        return juegos;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(Tipo tipo) {
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

    public void setPoderes(Set<Poder> poderes) {
        this.poderes = poderes;
    }

    public void setDebilidades(Set<Tipo> debilidades) {
        this.debilidades = debilidades;
    }

    public void setJuegos(Set<Juego> juegos) {
        this.juegos = juegos;
    }

    // toString
    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", familia='" + familia + '\'' +
                ", evolucion='" + evolucion + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", imagenUrl='" + imagenUrl + '\'' +
                ", poderes=" + poderes +
                ", debilidades=" + debilidades +
                ", juegos=" + juegos +
                '}';
    }
}