package com.hospital_jpa.domain.mappers;

import com.hospital_jpa.dao.model.Patient;
import com.hospital_jpa.domain.model.PatientUI;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientToPatientUI {

    public List<PatientUI> mapToPatientUI (List<Patient> patients) {
        List<PatientUI> patientUIList = new ArrayList();
        patients.forEach(patient -> patientUIList.add(new PatientUI(patient)));
        return patientUIList;
    }
}
