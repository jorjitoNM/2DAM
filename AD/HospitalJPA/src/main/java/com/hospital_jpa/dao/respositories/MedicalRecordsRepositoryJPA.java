package com.hospital_jpa.dao.respositories;

import com.hospital_jpa.dao.model.MedicalRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Log4j2
public class MedicalRecordsRepositoryJPA implements com.hospital_jpa.dao.interfaces.MedicalRecordsRepository {

    private final JPAUtil jpaUtil;

    public MedicalRecordsRepositoryJPA(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }


    @Override
    public List<MedicalRecord> getAll(int idPatient) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        try (EntityManager em = jpaUtil.getEntityManager()) {
            medicalRecords = em.createNamedQuery("getPatientMedicalRecords",MedicalRecord.class)
                    .setParameter("id", idPatient)
                    .getResultList();
        } catch (PersistenceException e) {
            log.error(e.getMessage(), e);
        }
        return medicalRecords;
    }

    @Override
    public void delete(MedicalRecord medicalRecord) {

    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        return 0;
    }

    @Override
    public void update(MedicalRecord medicalRecord) {

    }
}
