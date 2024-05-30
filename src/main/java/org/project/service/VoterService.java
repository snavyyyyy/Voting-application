package org.project.service;

import org.project.database.model.VoterEntity;
import org.project.database.repository.PostgresVoterRepository;
import org.project.database.repository.VoterRepository;
import org.project.database.validators.VoterValidator;
import org.project.exception.InvalidCredentialsException;
import org.project.exception.InvalidEmailException;
import org.project.exception.UsernameAlreadyExistsException;

import java.util.List;

public class VoterService {
    private final VoterRepository voterRepository;

    public VoterService() {
        this.voterRepository = new PostgresVoterRepository();
    }
    public List<VoterEntity> getAllVotersService(){
       return voterRepository.getAllVoters();
    }
    public VoterEntity saveVoterService(VoterEntity voterEntity) throws InvalidEmailException, UsernameAlreadyExistsException {
        if(!VoterValidator.ValidateVoterEmail(voterEntity.getEmail())){
            throw new InvalidEmailException(voterEntity.getEmail());
        }
        if (voterRepository.existsByUsername(voterEntity.getUsername())){
            throw new UsernameAlreadyExistsException(voterEntity.getUsername());
        }
       return voterRepository.saveVoter(voterEntity);
    }

    public VoterEntity login(String username, String password) throws InvalidCredentialsException {
       VoterEntity voter= voterRepository.getVoterByUsernameAndPassword(username, password);
       if(voter==null){
           throw new InvalidCredentialsException();
       }
        System.out.println("Korisnik je ulogovan.");
       return voter;
    }
}
