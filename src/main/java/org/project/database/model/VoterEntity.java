package org.project.database.model;

import lombok.Data;
import org.project.database.enums.Gender;

@Data
public class VoterEntity extends ApplicationUser{
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Gender gender;
}
