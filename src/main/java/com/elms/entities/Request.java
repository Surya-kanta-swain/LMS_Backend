package com.elms.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Request {
    private String email;
    private String password;

    
}
