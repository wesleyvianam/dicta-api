package br.com.dictaapi.domain.column;

import br.com.dictaapi.domain.project.Project;

public record DetailedColumnData(
    Long id,
    String name,
    Long project
) {
    public DetailedColumnData(Column column) {
        this(column.getId(), column.getName(), column.getProject().getId());
    }
}
