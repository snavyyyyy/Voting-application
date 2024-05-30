package org.project.database.repository;

import org.project.database.connection.DatabaseConnection;
import org.project.database.connection.PostgresDatabaseConnection;
import org.project.database.enums.Gender;
import org.project.database.model.VoterEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresVoterRepository implements VoterRepository {
    private static final String GET_ALL_VOTERS = "SELECT * FROM VOTERS";
    private static final String INSERT_VOTER = "INSERT INTO VOTERS VALUES (?,?,?,?,?,?,?)";
    private static final String FIND_BY_USERNAME = "SELECT * FROM VOTERS WHERE username=?";
    private static final String GET_VOTER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM VOTERS WHERE username=? AND password=?";
    private final DatabaseConnection connection;

    public PostgresVoterRepository() {
        this.connection = new PostgresDatabaseConnection();
    }

    @Override
    public List<VoterEntity> getAllVoters() {
        List<VoterEntity> voters = new ArrayList<>();
        Connection conn = null;
        try {
            conn = connection.getConnection();
            PreparedStatement statement = conn.prepareStatement(GET_ALL_VOTERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                VoterEntity voterEntity = new VoterEntity();
                setVoterEntityData(voterEntity, resultSet);
                voters.add(voterEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.closeConnection(conn);
        }
        return voters;
    }

    @Override
    public VoterEntity saveVoter(VoterEntity voterEntity) {
        Connection conn = null;
        try {
            conn = connection.getConnection();
            PreparedStatement statement = conn.prepareStatement(INSERT_VOTER);
            statement.setLong(1, voterEntity.getId());
            statement.setString(2, voterEntity.getName());
            statement.setString(3, voterEntity.getSurname());
            statement.setString(4, voterEntity.getEmail());
            statement.setString(5, voterEntity.getGender().toString());
            statement.setString(6, voterEntity.getUsername());
            statement.setString(7, voterEntity.getPassword());
            statement.executeUpdate();
            return voterEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection(conn);
        }
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        Connection conn = null;
        try {
            conn = connection.getConnection();
            PreparedStatement statement = conn.prepareStatement(FIND_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection(conn);
        }
        return false;
    }

    @Override
    public VoterEntity getVoterByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        conn = connection.getConnection();
        VoterEntity voterEntity = null;
        try {
            PreparedStatement statement = conn.prepareStatement(GET_VOTER_BY_USERNAME_AND_PASSWORD);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                voterEntity = new VoterEntity();
                setVoterEntityData(voterEntity, resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return voterEntity;
    }

    @Override
    public VoterEntity updateVoter(VoterEntity voterEntity) {
        return null;
    }

    @Override
    public boolean deleteVoter(Long id) {
        return false;
    }

    private void setVoterEntityData(VoterEntity voterEntity, ResultSet resultSet) throws SQLException {
        voterEntity.setId(resultSet.getLong("id"));
        voterEntity.setName(resultSet.getString("name"));
        voterEntity.setSurname(resultSet.getString("surname"));
        voterEntity.setGender(Gender.valueOf(resultSet.getString("gender")));
        voterEntity.setEmail(resultSet.getString("email"));
        voterEntity.setUsername(resultSet.getString("username"));
        voterEntity.setPassword(resultSet.getString("password"));
    }
}
