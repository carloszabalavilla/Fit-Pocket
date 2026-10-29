package edu.fpdual.webapplication.servlet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Session {
    private int userId;
    private String userName;
    private boolean admin;
}
