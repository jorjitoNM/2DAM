package hospitaljpa.domain.service;

import com.hospitalcrud.dao.respositories.CredentialRepository;
import com.hospitalcrud.domain.model.CredentialUI;
import org.springframework.stereotype.Service;


@Service
public class CredentialService {
    private final CredentialRepository credentialRepository;
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public boolean login(CredentialUI userCredentialsUI) {
        return credentialRepository.get(userCredentialsUI.getUsername())
                .getPassword().equals(userCredentialsUI.getPassword());
    }
}
