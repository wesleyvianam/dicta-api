package br.com.dictaapi.domain.project;

public record DetailedProjectData(
    Long id,
    String title,
    String description
) {
    public DetailedProjectData(Project project) {
        this(project.getId(), project.getTitle(), project.getDescription());
    }
}
