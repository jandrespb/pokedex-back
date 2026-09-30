# Pokedex Backend

Backend de la Pokedex, desarrollado con **Spring Boot**, **Spring Data JPA** y
**MySQL**. Esta API es el backend del frontend de la Pokedex y expone la
informacion necesaria para consultar Pokemon, sus caracteristicas, poderes,
debilidades y juegos relacionados.

El proyecto frontend que consume esta API se encuentra en:

[Repositorio del Frontend Pokedex](https://github.com/jandrespb/pokedex-front)

Toda la documentacion de instalacion, ejecucion y uso del frontend esta
disponible en el README de ese repositorio.

El proyecto esta pensado especialmente para **practicar QA**: permite probar
casos felices, validaciones de parametros, paginacion, respuestas de error y
el comportamiento de una API consumida por una aplicacion web. No es necesario
usar Swagger; los endpoints y ejemplos de respuesta estan documentados en este
README.

## Descargar el proyecto

Este backend utiliza la rama principal `main`. Puedes clonar directamente el
repositorio:

```bash
git clone https://github.com/jandrespb/pokedex-back.git
cd pokedex-back
```

Tambien puedes descargar el proyecto desde GitHub seleccionando la rama `main`
y usando **Code > Download ZIP**:

[Descargar el proyecto en ZIP](https://github.com/jandrespb/pokedex-back/archive/refs/heads/main.zip)

## Requisitos previos

- Java 21
- Maven (o el Maven Wrapper incluido)
- MySQL
- Una base de datos llamada `pokedex-db`

Los scripts SQL incluidos en `src/main/resources/data/` crean y cargan la base
de datos:

- `creacion-tablas-pokedex.sql`: crea el esquema `pokedex-db`, las tablas y
  sus relaciones.
- `poblar_pokedex.sql`: inserta los tipos, poderes, juegos, Pokemon y
  relaciones de la Pokedex.

El script de creacion elimina las tablas existentes antes de crearlas de nuevo.
Ejecutalo solo cuando quieras reconstruir la base de datos.

## Configurar la base de datos

Inicia el servidor MySQL y, desde la raiz del proyecto, ejecuta los scripts en
este orden:

```bash
mysql -u root -p < src/main/resources/data/creacion-tablas-pokedex.sql
mysql -u root -p < src/main/resources/data/poblar_pokedex.sql
```

Tambien puedes abrir ambos archivos desde MySQL Workbench y ejecutarlos
respetando el mismo orden. El script de poblacion carga actualmente 15 tipos,
155 poderes, 4 juegos y 152 Pokemon, junto con sus poderes, debilidades y
apariciones en juegos.

La aplicacion usa por defecto la siguiente configuracion en
`src/main/resources/application.properties`:

```text
Host: localhost
Puerto de MySQL: 3306
Base de datos: pokedex-db
Usuario: #Cualquiera#
Contraseña: #Cualquiera#
```

Configura la contrasena y cualquier otro dato de conexion en
`application.properties` antes de iniciar la API. El usuario de MySQL utilizado
para ejecutar los scripts puede ser distinto del usuario configurado para
Spring Boot, siempre que ambos tengan permisos sobre `pokedex-db`.

Puedes comprobar que la carga se realizo correctamente con:

```sql
USE `pokedex-db`;
SELECT COUNT(*) AS total_pokemon FROM pokemon;
SELECT COUNT(*) AS total_tipos FROM tipo;
SELECT COUNT(*) AS total_poderes FROM poder;
SELECT COUNT(*) AS total_juegos FROM juego;
```

## Instalacion y ejecucion

Desde la raiz del proyecto, instala las dependencias y ejecuta la aplicacion:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

La API quedara disponible en:

```text
http://localhost:8080
```

Todos los endpoints son de consulta y aceptan solicitudes `GET`. CORS esta
habilitado para que el frontend pueda consumir la API durante el desarrollo.

## Endpoints

### 1. Listar Pokemon

```http
GET /pokemon?page=0
```

Devuelve una pagina de Pokemon con los campos basicos `id` y `nombre`.
La paginacion empieza en `0` y cada pagina contiene hasta 4 registros.

Ejemplo:

```bash
curl "http://localhost:8080/pokemon?page=0"
```

Respuesta `200 OK`:

```json
[
  {
    "id": 1,
    "nombre": "Bulbasaur"
  },
  {
    "id": 2,
    "nombre": "Ivysaur"
  }
]
```

El parametro `page` es opcional; si se omite, se utiliza `page=0`.

### 2. Consultar el detalle por ID

```http
GET /pokemon/{id}
```

Busca un Pokemon por su identificador y devuelve toda su informacion
relacionada.

Ejemplo:

```bash
curl "http://localhost:8080/pokemon/1"
```

Respuesta `200 OK`:

```json
{
  "id": 1,
  "nombre": "Bulbasaur",
  "tipo": {
    "id": 4,
    "nombre": "Planta"
  },
  "familia": "Semilla",
  "evolucion": "Ivysaur",
  "altura": 0.7,
  "peso": 6.9,
  "imagenUrl": "https://example.com/bulbasaur.png",
  "poderes": [
    {
      "id": 1,
      "nombre": "Latigo Cepa"
    }
  ],
  "debilidades": [
    {
      "id": 2,
      "nombre": "Fuego"
    }
  ],
  "juegos": [
    {
      "id": 1,
      "nombre": "Pokemon Rojo"
    }
  ]
}
```

Los valores concretos de las relaciones dependen de los datos cargados en
MySQL.

### 3. Buscar Pokemon por ID o nombre

```http
GET /pokemon/buscar?query={valor}
```

El parametro `query` permite buscar:

- Un ID formado solo por numeros enteros no negativos.
- Un nombre formado solo por letras, incluyendo tildes y `ñ`.

La busqueda por nombre no distingue mayusculas y minusculas. El texto se
normaliza antes de consultar, por ejemplo, `pikachu` se busca como `Pikachu`.

Ejemplos:

```bash
curl "http://localhost:8080/pokemon/buscar?query=25"
curl "http://localhost:8080/pokemon/buscar?query=pikachu"
```

El valor no puede superar los 12 caracteres ni contener espacios, simbolos o
caracteres especiales.

## Respuestas de error

Los errores se devuelven con esta estructura:

```json
{
  "status": 404,
  "mensaje": "No se encontro el Pokemon solicitado",
  "timestamp": "2026-09-30T16:28:35.311"
}
```

| Situacion | HTTP | Ejemplo |
| --- | ---: | --- |
| Pokemon inexistente | `404` | `GET /pokemon/9999` |
| Pagina negativa o fuera de rango | `400` | `GET /pokemon?page=-1` |
| ID con formato no numerico | `400` | `GET /pokemon/abc` |
| Query vacio, demasiado largo o con caracteres invalidos | `400` | `GET /pokemon/buscar?query=pika-25` |
| Error inesperado del servidor | `500` | Revisar la consola de la aplicacion |

## Ideas de pruebas para QA

- Consultar la primera pagina, una pagina intermedia y la ultima pagina valida.
- Verificar que cada pagina devuelva como maximo 4 Pokemon.
- Probar `page=-1`, una pagina decimal, texto y una pagina fuera de rango.
- Consultar un ID existente, un ID inexistente, `0` y un valor negativo.
- Buscar por nombre usando mayusculas, minusculas, tildes y `ñ`.
- Enviar espacios, simbolos, numeros mezclados y mas de 12 caracteres en
  `query`.
- Validar el codigo HTTP, los nombres de las propiedades y el formato de
  `timestamp` en cada respuesta de error.
- Confirmar que el frontend pueda consumir la API desde otro origen mediante
  CORS.

## Estructura principal

```text
src/main/java/jandtocode/pokedex/
├── controller/   Endpoints HTTP de Pokemon
├── service/      Validaciones, paginacion y logica de negocio
├── repository/   Consultas a MySQL mediante JPA
├── entity/       Entidades Pokemon y sus relaciones
├── dto/          Formatos de respuesta de la API
├── exception/    Errores y respuestas HTTP
└── config/       Configuracion de CORS
```

## Recursos

- [Repositorio del Frontend Pokedex](https://github.com/jandrespb/pokedex-front)
- [Repositorio del Backend Pokedex](https://github.com/jandrespb/pokedex-back)
- [Documentacion de Spring Boot](https://spring.io/projects/spring-boot)
