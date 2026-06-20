package com.surya.payload.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {

    private Long id;

    @NotBlank(message = "Genre Code is Mandatory")
    private String code;

    @NotBlank(message = "Genra Name Is Required")
    private String name;

    @Size(max = 500, message = "Description Must Not Exceed 500 Characters!")
    private String description;

    @Builder.Default
    @Min(value = 0, message = "Display Order Cannot Be Negative")
    private Integer displayOrder=0;

    private boolean active;

    private Long parentGenreId;

    private String parentGenreName;

    private List<GenreDTO> subGenre;

    private Long bookCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    


}
