package org.project.database.repository;

import org.project.database.model.VoterEntity;

import java.util.List;

public interface VoterRepository {
    List<VoterEntity> getAllVoters();

    VoterEntity saveVoter(VoterEntity voterEntity);

    VoterEntity updateVoter(VoterEntity voterEntity);

    boolean deleteVoter(Long id);

    boolean existsByUsername(String username);

    VoterEntity getVoterByUsernameAndPassword(String username, String password);
}
