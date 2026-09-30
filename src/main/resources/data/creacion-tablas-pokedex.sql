/*Creación base de datos 'pokedex-db'*/
CREATE SCHEMA IF NOT EXISTS `pokedex-db` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `pokedex-db`;

/*Eliminar tabla tipo*/
DROP TABLE IF EXISTS tipo;
/*Creación tabla Tipo Pokemon 'agua, tierra, ...' */
CREATE TABLE tipo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


/*Eliminar tabla poder*/
DROP TABLE IF EXISTS poder;
/*Creación tabla Poderes Pokemon 'terremoto, cabezazo, ...' */
CREATE TABLE poder (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


/*Eliminar tabla juego*/
DROP TABLE IF EXISTS juego;
/*Creación tabla juego Pokemon apariciones 'rojo, amarillo, ...' */
CREATE TABLE juego (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- ============================================
-- TABLA: POKEMON (tabla principal)
-- Descripción: Información central de cada Pokémon
-- Dependencias: tipo (FK)
-- ============================================
DROP TABLE IF EXISTS pokemon;

CREATE TABLE pokemon (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    tipo_id INT NOT NULL,
    familia VARCHAR(255),
    evolucion VARCHAR(100),
    altura DECIMAL(5,2),
    peso DECIMAL(5,2),
    imagen_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (tipo_id) REFERENCES tipo(id)
);

-- ============================================
-- TABLA PUENTE: POKEMON_PODER
-- Descripción: Relación muchos a muchos entre Pokémon y Poderes
-- Dependencias: pokemon (FK), poder (FK)
-- ============================================
DROP TABLE IF EXISTS pokemon_poder;

CREATE TABLE pokemon_poder (
    pokemon_id INT NOT NULL,
    poder_id INT NOT NULL,
    PRIMARY KEY (pokemon_id, poder_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    FOREIGN KEY (poder_id) REFERENCES poder(id)
);

-- ============================================
-- TABLA PUENTE: POKEMON_DEBILIDAD
-- Descripción: Relación muchos a muchos entre Pokémon y Tipos (debilidades)
-- Dependencias: pokemon (FK), tipo (FK)
-- ============================================
DROP TABLE IF EXISTS pokemon_debilidad;

CREATE TABLE pokemon_debilidad (
    pokemon_id INT NOT NULL,
    tipo_id INT NOT NULL,
    PRIMARY KEY (pokemon_id, tipo_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    FOREIGN KEY (tipo_id) REFERENCES tipo(id)
);

-- ============================================
-- TABLA PUENTE: POKEMON_JUEGO
-- Descripción: Relación muchos a muchos entre Pokémon y Juegos
-- Dependencias: pokemon (FK), juego (FK)
-- ============================================
DROP TABLE IF EXISTS pokemon_juego;

CREATE TABLE pokemon_juego (
    pokemon_id INT NOT NULL,
    juego_id INT NOT NULL,
    PRIMARY KEY (pokemon_id, juego_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    FOREIGN KEY (juego_id) REFERENCES juego(id)
);



