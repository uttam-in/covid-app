package org.codejudge.sb.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "covidsymptoms")
public class CovidSymptoms extends CovidAuditable {

    private Long userId;
    @ManyToMany
    private Set<Symptom> symptoms = new HashSet<>();
    private boolean travelHistory;
    private boolean contactWithCovidPatient;

    public CovidSymptoms(Long userId, boolean travelHistory, boolean contactWithCovidPatient) {
        this.userId = userId;
        this.symptoms = symptoms;
        this.travelHistory = travelHistory;
        this.contactWithCovidPatient = contactWithCovidPatient;
    }

    public void addSymptom(Symptom role) {
        this.symptoms.add(role);
    }


}
