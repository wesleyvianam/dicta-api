package br.com.dictaapi.domain.project;

import jakarta.validation.constraints.NotBlank;

public record UpdateProjectData(
    @NotBlank
    String title, 
    String description
) {  
}
