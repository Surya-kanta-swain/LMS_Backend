package com.elms.entities;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

        private String jwt;
        private String message;
        private boolean status;
        private User user;
}
