package com.hospitalcrud.dao.model;

import com.hospitalcrud.dao.mappers.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@XmlRootElement(name = "medicalRecord")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicalRecord {
    private int id;
    private int idPatient;
    @XmlElement(name = "doctor")
    private int idDoctor;
    private String diagnosis;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate date;
    @XmlElementWrapper(name="medications")
    @XmlValue
    private List<Medication> medications;
}
