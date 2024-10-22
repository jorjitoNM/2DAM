package com.hospitalcrud.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "medication")
@XmlAccessorType(XmlAccessType.NONE)
public class Medication {
    @XmlTransient
    private int id;
    @XmlValue
    private String medicationName;
    @XmlTransient
    private int medRecordId;
}
