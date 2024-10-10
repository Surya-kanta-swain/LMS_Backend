package com.elms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String department;
    private String role;
    private int days = 50;


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Leave> leaveList = new ArrayList<>();
}
