package com.myProject.demo.model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name = "user_model")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "anno_nascita")
	private Date anno_nascita;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "cf", unique = true)
	private String cf;

//	@NotNull
//	@ManyToOne
//	@JoinColumn(name = "IdAccountType", referencedColumnName = "IdAccountType")
//	private UserType fkAccountType;

}
