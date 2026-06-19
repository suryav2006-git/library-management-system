package com.surya.modal;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Genre Code is Mandatory")
    private String code;

    @NotBlank(message = "Genra Name Is Required")
    private String name;

    @Size(max = 500, message = "Description Must Not Exceed 500 Characters!")
    private String description;

    @Min(value = 0, message = "Display Order Cannot Be Negative")
    private Integer displayOrder=0;

    @Column(nullable = false)
    private Boolean active=true;

    @ManyToOne
    private Genre parentGenere;

    @OneToMany
    private List<Genre> subGenres = new ArrayList<Genre>();



}
