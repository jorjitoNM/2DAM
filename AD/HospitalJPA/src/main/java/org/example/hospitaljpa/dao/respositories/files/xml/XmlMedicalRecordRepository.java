package org.example.hospitaljpa.dao.respositories.files.xml;

import com.hospitalcrud.dao.configuration.XMLConfiguration;
import com.hospitalcrud.dao.model.MedicalRecord;
import com.hospitalcrud.dao.model.MedicalRecords;
import com.hospitalcrud.dao.respositories.MedicalRecordsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Profile("TXT")
@Log4j2
@Repository
public class XmlMedicalRecordRepository implements MedicalRecordsRepository {

    private XMLConfiguration configuration;

    public XmlMedicalRecordRepository() {
        this.configuration = XMLConfiguration.getInstance();
        calculateID();
    }

    private void calculateID() {
        List<MedicalRecord> medicalRecords = loadMedicalRecords();
        int lastID = medicalRecords.get(medicalRecords.size() - 1).getId();
        configuration.setLastID(lastID);
    }


    @Override
    public List<MedicalRecord> getAll(int idPatient) {
        return loadMedicalRecords().stream().filter(m -> m.getIdPatient() == idPatient).toList();
    }

    @Override
    public void delete(MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecords = new ArrayList<>(loadMedicalRecords());
        medicalRecords.removeIf(m -> m.getId() == medicalRecord.getId());
        saveMedicalRecords(medicalRecords);
    }

    @Override
    public int save(MedicalRecord medicalRecord) {
        medicalRecord.setId(configuration.getLastID()+1);
        List<MedicalRecord> medicalRecords = new ArrayList<>(loadMedicalRecords());
        medicalRecords.add(medicalRecord);
        saveMedicalRecords(medicalRecords);
        configuration.setLastID(medicalRecord.getId());
        return medicalRecord.getId();
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
    }

    private List<MedicalRecord> loadMedicalRecords() {
        try {
            JAXBContext context = JAXBContext.newInstance(MedicalRecords.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MedicalRecords medicalRecords = (MedicalRecords)unmarshaller.unmarshal(Files.newInputStream(configuration.getPathMedicalRecords()));
            return medicalRecords.getMedicalRecords();
        } catch (JAXBException | IOException e) {
            //log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    public void saveMedicalRecords (List<MedicalRecord> medicalRecords) {
        try {
            JAXBContext context = JAXBContext.newInstance(MedicalRecords.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new MedicalRecords(medicalRecords),Files.newOutputStream(configuration.getPathMedicalRecords()));
        } catch (JAXBException | IOException e) {
            //log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    public void deletePatientMedicalRecords(int patientId) {
        List<MedicalRecord> medicalRecords = new ArrayList<>(loadMedicalRecords());
        medicalRecords.removeIf(m -> m.getIdPatient() == patientId);
        saveMedicalRecords(medicalRecords);
    }
}
