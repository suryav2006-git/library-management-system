package com.surya.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

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

    @Builder.Default
    @Min(value = 0, message = "Display Order Cannot Be Negative")
    private Integer displayOrder=0;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active=true;

    @ManyToOne
    private Genre parentGenere;

    @Builder.Default
    @OneToMany(mappedBy = "parentGenere")
    private List<Genre> subGenres = new ArrayList<Genre>(); 

    // @OneToMany(mappedBy = "Genre", cascade = CascadeType.PERSIST)
    // private List<Book> books = new ArrayList<Book>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;






}
