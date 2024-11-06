package com.myProject.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "TP_ACCOUNT_TYPES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAccountType")
    private Integer idAccountType;

    @Column(name = "Description")
    @NotNull
    private String description;

}