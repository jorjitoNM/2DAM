package com.hospitalcrud.dao.respositories.textFiles;

import com.hospitalcrud.dao.configuration.FilesConfiguration;
import com.hospitalcrud.dao.mappers.MedicalRecordRowMapper;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Log4j2
@Repository
public class TxtMedicalRecordRepository implements MedicalRecordsRepository {
    private final FilesConfiguration configuration;
    private final MedicalRecordRowMapper medicalRecordMapper;

    public TxtMedicalRecordRepository(MedicalRecordRowMapper medicalRecordMapper) {
        this.medicalRecordMapper = medicalRecordMapper;
        this.configuration = FilesConfiguration.getInstance();
    }

    @Override
    public List<MedicalRecord> getAll(int idPatient) {
        return loadMedicalRecords();
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

    private boolean saveMedicalRecords (List<MedicalRecord> medicalRecords) {
        BufferedWriter bw = null;
        try {
            OpenOption option = TRUNCATE_EXISTING;
            bw = Files.newBufferedWriter(Paths.get(configuration.getPathPatients()),option);
            BufferedWriter finalBw = bw;
            medicalRecords.forEach(p -> finalBw.write(p.toStringFichero()));
        }
        catch (IOException e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        } finally {
            if (bw != null)
                bw.close();
        }
        return false;
    }
    private List<MedicalRecord> loadMedicalRecords () {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get(configuration.getPathPatients()));
            br.lines().forEach(l -> medicalRecords.add(medicalRecordMapper.mapRow(l)));
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return medicalRecords;
    }
}
