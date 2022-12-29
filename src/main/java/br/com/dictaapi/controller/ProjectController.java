package br.com.dictaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dictaapi.domain.project.CreateProjectData;
import br.com.dictaapi.domain.project.DetailedProjectData;
import br.com.dictaapi.domain.project.Project;
import br.com.dictaapi.domain.project.ProjectRepository;
import br.com.dictaapi.domain.project.UpdateProjectData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<DetailedProjectData> detailed(@PathVariable Long id) {
        var project = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailedProjectData(project));
    }

    @GetMapping
    public ResponseEntity<List<Project>> listProjects() {
        var page = repository.findAll();
        return ResponseEntity.ok(page);
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<DetailedProjectData> create(@RequestBody @Valid CreateProjectData data, UriComponentsBuilder uriBuilder) {
        var project = new Project(data);
        repository.save(project);
        var uri = uriBuilder.path("/project/{id}").buildAndExpand(project.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailedProjectData(project));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetailedProjectData> updateProject(@RequestBody @Valid UpdateProjectData data, @PathVariable Long id) {
        var project = repository.getReferenceById(id);
        project.updateData(data);

        return ResponseEntity.ok(new DetailedProjectData(project));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Project> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
