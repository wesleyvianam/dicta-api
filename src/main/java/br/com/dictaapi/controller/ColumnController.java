package br.com.dictaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dictaapi.domain.column.Column;
import br.com.dictaapi.domain.column.ColumnRepository;
import br.com.dictaapi.domain.column.CreateColumnData;
import br.com.dictaapi.domain.column.DetailedColumnData;
import br.com.dictaapi.domain.project.ProjectRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/project/{id}/column")
public class ColumnController {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ColumnRepository columnRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailedColumnData> create(@RequestBody CreateColumnData data, @PathVariable Long id, UriComponentsBuilder uriBuilder) {
        var column = new Column();
        column.setName(data.name());
        column.setProject(projectRepository.getReferenceById(id));
        columnRepository.save(column);
        var uri = uriBuilder.path("/project/id/column/{id}").buildAndExpand(column.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailedColumnData(column));
    }
}
