package com.hospitalcrud.dao.respositories.statiC;

import com.hospitalcrud.dao.model.Medication;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StaticMedicationsRepository {
    public List<Medication> getAll () {
        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication(1,"Ibuprofen",0));
        medications.add(new Medication(2,"Tylenol",0));
        medications.add(new Medication(3,"Penicilina",0));
        medications.add(new Medication(4,"Insulin",0));
        medications.add(new Medication(5,"Folic acid",0));
        return medications;
    }
}
