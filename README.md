# 💬 ForoHub API

API REST desarrollada con **Java + Spring Boot** que simula el backend de un foro de discusión.  
Permite crear tópicos, responderlos y gestionar usuarios con autenticación segura mediante **JWT**.

Este proyecto fue desarrollado como parte del **Challenge ForoHub** del programa **Oracle Next Education (ONE) + Alura Latam**.

---

# 🚀 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL
- Flyway (migraciones de base de datos)
- Maven

---

# 🏗 Arquitectura del proyecto

El proyecto sigue una arquitectura basada en capas:

controller → recibe peticiones HTTP  
service → lógica de negocio  
repository → acceso a base de datos  
domain → entidades del sistema  
infra/security → configuración de seguridad y JWT

---

# 📂 Estructura del proyecto

```
src/main/java/com/daniel/forohub

controller
  AutenticacionController
  TopicoController

domain
  topico
  usuario
  curso
  respuesta
  perfil

repository
  TopicoRepository
  UsuarioRepository
  CursoRepository
  RespuestaRepository

infra
  security
    SecurityConfigurations
    SecurityFilter
    TokenService
    AutenticacionService
```

---

# 🔐 Autenticación

La API utiliza **JWT (JSON Web Token)** para autenticación.

Flujo de autenticación:

1️⃣ El usuario envía sus credenciales a `/login`  
2️⃣ El servidor valida usuario y contraseña  
3️⃣ Se genera un **token JWT**  
4️⃣ El cliente debe enviar el token en las siguientes peticiones

Header requerido:

```
Authorization: Bearer TOKEN_JWT
```

---

# ⚙ Variables de entorno

Por seguridad, las configuraciones sensibles se manejan mediante variables de entorno.

Crear un archivo `.env` (no subir a GitHub).

Ejemplo `.env.example`:

```
DB_URL=jdbc:mysql://127.0.0.1:3306/forohub
DB_USERNAME=root
DB_PASSWORD=tu_password
JWT_SECRET=tu_clave_secreta_segura
```

El archivo `.env` debe agregarse al `.gitignore`.

---

# 🗄 Configuración de base de datos

Crear base de datos en MySQL:

```sql
CREATE DATABASE forohub;
```

Las tablas se crean automáticamente mediante **Flyway migrations**.

Migraciones ubicadas en:

```
src/main/resources/db/migration
```

---

# ▶ Cómo ejecutar el proyecto

### 1️⃣ Clonar repositorio

```bash
git clone https://github.com/Daniel34981/forohub
```

### 2️⃣ Configurar variables de entorno

Crear `.env` o configurar variables en el sistema.

### 3️⃣ Ejecutar el proyecto

```bash
mvn spring-boot:run
```

o ejecutar desde IntelliJ:

```
ForohubApplication
```

Servidor disponible en:

```
http://localhost:8080
```

---

# 🔑 Autenticación (Login)

Endpoint:

```
POST /login
```

Body:

```json
{
  "correoElectronico": "admin@forohub.com",
  "contrasena": "123456"
}
```

Respuesta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

# 📌 Endpoints principales

## 📚 Tópicos

Crear tópico

```
POST /topicos
```

Listar tópicos

```
GET /topicos
```

Detalle de tópico

```
GET /topicos/{id}
```

Actualizar tópico

```
PUT /topicos/{id}
```

Eliminar tópico

```
DELETE /topicos/{id}
```

---

# 📥 Ejemplo de request protegido

```
GET /topicos
```

Header requerido:

```
Authorization: Bearer TOKEN_JWT
```

---

# 🔒 Seguridad implementada

✔ Autenticación con **JWT**  
✔ Contraseñas encriptadas con **BCrypt**  
✔ Endpoints protegidos mediante **Spring Security**  
✔ Secrets manejados mediante **variables de entorno**  
✔ Filtro personalizado para validación de tokens

---

# 📊 Modelo de datos

Entidades principales del sistema:

- Usuario
- Perfil
- Curso
- Tópico
- Respuesta

Relaciones:

Usuario 1 --- N Topicos  
Usuario 1 --- N Respuestas  
Curso 1 --- N Topicos  
Topico 1 --- N Respuestas  
Usuario N --- N Perfil

---

# 🧪 Pruebas de la API

La API puede probarse usando herramientas como:

- Postman
- Insomnia
- Thunder Client
- cURL

---

# 🧑‍💻 Autor

Desarrollado por **Daniel Suárez**

Proyecto realizado para el programa **Oracle Next Education (ONE) + Alura Latam**

---

# 📄 Licencia

Proyecto desarrollado con fines educativos.