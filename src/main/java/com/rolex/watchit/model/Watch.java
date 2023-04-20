package com.rolex.watchit.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pathToImage;
    private String model;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

}
