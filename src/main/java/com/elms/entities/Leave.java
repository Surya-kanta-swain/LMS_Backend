package com.elms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "leaves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate appliedDate = LocalDate.now();
    private Date startDate;
    private Date endDate;
    private String leaveCause;
    private boolean isApproved = false;
    private Long uId;
    private String leaveType;
    private String departmentName;
    private String status;
    private String adminComment;


	@ManyToOne
    @JoinColumn(name = "user_id")//Specifies the foreign key column (user_id) in the leaves table,
    //// which references the id field of the User entity.
    private User user;


}

