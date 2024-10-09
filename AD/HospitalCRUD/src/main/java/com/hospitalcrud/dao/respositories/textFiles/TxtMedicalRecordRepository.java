package com.hospitalcrud.dao.respositories.textFiles;

import com.hospitalcrud.dao.configuration.FilesConfiguration;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;

import java.util.List;

public class TxtMedicalRecordRepository implements MedicalRecordsRepository {
    private final FilesConfiguration configuration;

    public TxtMedicalRecordRepository() {
        this.configuration = FilesConfiguration.getInstance();
    }

    @Override
    public List<MedicalRecord> getAll(int idPatient) {
        return List.of();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        return 0;
    }

    @Override
    public void update(MedicalRecord medicalRecord) {

    }
}
