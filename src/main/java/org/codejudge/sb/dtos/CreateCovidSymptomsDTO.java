package org.codejudge.sb.dtos;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class CreateCovidSymptomsDTO {
    @NonNull
    private Long userId;
    @NonNull
    private List<String> symptoms;
    @NonNull
    private boolean travelHistory;
    @NonNull
    private boolean contactWithCovidPatient;
}
