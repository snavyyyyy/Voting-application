package org.project;

import org.project.database.enums.Gender;
import org.project.database.model.VoterEntity;
import org.project.exception.InvalidCredentialsException;
import org.project.exception.InvalidEmailException;
import org.project.exception.UsernameAlreadyExistsException;
import org.project.security.Encryption;
import org.project.service.VoterService;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VoterService voterService=new VoterService();

        VoterEntity voterEntity=new VoterEntity();
        voterEntity.setId(10L);
        voterEntity.setName("Angelina");
        voterEntity.setSurname("Avramobic");
        voterEntity.setEmail("angelinavram@gmail.com");
        voterEntity.setGender(Gender.FEMALE);
        voterEntity.setUsername("endzi12345");
      //  SecretKey secret = Encryption.generateKey(128);
      //  IvParameterSpec iv = Encryption.generateIv();
      //  voterEntity.setPassword(Encryption.encrypt("endzi", secret, iv));
        voterEntity.setPassword("endzi");
            try {
                voterService.saveVoterService(voterEntity);
            } catch (InvalidEmailException | UsernameAlreadyExistsException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }

            List<VoterEntity> voters=voterService.getAllVotersService();
        System.out.println(voters);

        try {
            voterService.login("endzi12345","endzi");
        } catch (InvalidCredentialsException e) {
            throw new RuntimeException(e);
        }
    }


    
}