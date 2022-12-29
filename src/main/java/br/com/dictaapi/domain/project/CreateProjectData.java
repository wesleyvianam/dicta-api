package br.com.dictaapi.domain.project;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectData(
    @NotBlank
    String title,
    String description
) {
}
