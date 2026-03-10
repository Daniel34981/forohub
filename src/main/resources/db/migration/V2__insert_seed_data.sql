INSERT INTO perfiles (nombre) VALUES
                                  ('ROLE_ADMIN'),
                                  ('ROLE_USER');

INSERT INTO usuarios (nombre, correo_electronico, contrasena) VALUES
    ('Administrador', 'admin@forohub.com', '$2a$10$Lr2/V3gSCGftnA/w9vM9qekf/gulLP/4q1JF25KXZvyqBuMUtX7Qu');

INSERT INTO usuarios_perfiles (usuario_id, perfil_id) VALUES
    (1, 1);

INSERT INTO cursos (nombre, categoria) VALUES
                                           ('Spring Boot', 'Backend'),
                                           ('Java OO', 'Programación'),
                                           ('SQL y MySQL', 'Base de Datos');

INSERT INTO topicos (titulo, mensaje, status, autor_id, curso_id) VALUES
                                                                      ('Duda con Spring Security', 'No entiendo cómo configurar filtros JWT', 'ABIERTO', 1, 1),
                                                                      ('Error con JPA', 'Mi entidad no está mapeando bien la relación', 'ABIERTO', 1, 1);

INSERT INTO respuestas (mensaje, topico_id, autor_id, solucion) VALUES
                                                                    ('Revisa tu configuración de SecurityFilterChain', 1, 1, TRUE),
                                                                    ('Asegúrate de usar @ManyToOne correctamente', 2, 1, FALSE);