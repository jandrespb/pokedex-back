package jandtocode.pokedex.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "poder")
public class Poder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    // Constructor vacío
    public Poder() {}

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

    // toString
    @Override
    public String toString() {
        return "Poder{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}