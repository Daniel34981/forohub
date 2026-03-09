package com.daniel.forohub.controller;

import com.daniel.forohub.domain.curso.Curso;
import com.daniel.forohub.domain.curso.CursoRepository;
import com.daniel.forohub.domain.topico.*;
import com.daniel.forohub.domain.usuario.Usuario;
import com.daniel.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrar(@RequestBody @Valid DatosRegistroTopico datos,
                                       UriComponentsBuilder uriComponentsBuilder) {

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado."));

        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado."));

        Topico topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {

        var page = topicoRepository.findAllByOrderByFechaCreacionAsc(paginacion)
                .map(DatosListadoTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detallar(@PathVariable Long id) {
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DatosDetalleTopico(optionalTopico.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody @Valid DatosActualizarTopico datos) {

        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = optionalTopico.get();
        topico.actualizarDatos(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}