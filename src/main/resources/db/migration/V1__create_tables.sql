CREATE TABLE perfiles (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nombre VARCHAR(100) NOT NULL,
                          PRIMARY KEY (id),
                          UNIQUE KEY uk_perfiles_nombre (nombre)
);

CREATE TABLE usuarios (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nombre VARCHAR(150) NOT NULL,
                          correo_electronico VARCHAR(150) NOT NULL,
                          contrasena VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id),
                          UNIQUE KEY uk_usuarios_correo (correo_electronico)
);

CREATE TABLE usuarios_perfiles (
                        usuario_id BIGINT NOT NULL,
                        perfil_id BIGINT NOT NULL,
                        PRIMARY KEY (usuario_id, perfil_id),
                                   CONSTRAINT fk_usuarios_perfiles_usuario
                                       FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
                                           ON DELETE CASCADE
                                           ON UPDATE CASCADE,
                                   CONSTRAINT fk_usuarios_perfiles_perfil
                                       FOREIGN KEY (perfil_id) REFERENCES perfiles (id)
                                           ON DELETE CASCADE
                                           ON UPDATE CASCADE
);

CREATE TABLE cursos (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        nombre VARCHAR(150) NOT NULL,
                        categoria VARCHAR(100) NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE topicos (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         titulo VARCHAR(255) NOT NULL,
                         mensaje TEXT NOT NULL,
                         fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         status VARCHAR(50) NOT NULL,
                         autor_id BIGINT NOT NULL,
                         curso_id BIGINT NOT NULL,
                         PRIMARY KEY (id),
                         CONSTRAINT fk_topicos_autor
                             FOREIGN KEY (autor_id) REFERENCES usuarios (id)
                                 ON DELETE RESTRICT
                                 ON UPDATE CASCADE,
                         CONSTRAINT fk_topicos_curso
                             FOREIGN KEY (curso_id) REFERENCES cursos (id)
                                 ON DELETE RESTRICT
                                 ON UPDATE CASCADE
);

CREATE TABLE respuestas (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            mensaje TEXT NOT NULL,
                            topico_id BIGINT NOT NULL,
                            fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            autor_id BIGINT NOT NULL,
                            solucion BOOLEAN NOT NULL DEFAULT FALSE,
                            PRIMARY KEY (id),
                            CONSTRAINT fk_respuestas_topico
                                FOREIGN KEY (topico_id) REFERENCES topicos (id)
                                    ON DELETE CASCADE
                                    ON UPDATE CASCADE,
                            CONSTRAINT fk_respuestas_autor
                                FOREIGN KEY (autor_id) REFERENCES usuarios (id)
                                    ON DELETE RESTRICT
                                    ON UPDATE CASCADE
);