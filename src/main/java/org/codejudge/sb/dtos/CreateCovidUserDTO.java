package org.codejudge.sb.dtos;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class CreateCovidUserDTO {
    @NonNull
    private String username;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String pinCode;

}
