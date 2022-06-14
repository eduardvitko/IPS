package com.dao;

import com.dto.TariffDto;
import com.exceptions.TariffException;
import com.exceptions.UserException;
import com.model.Role;
import com.model.ServiceType;
import com.model.Tariff;
import com.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TariffDao implements Dao<Tariff> {

    private static final String CREATE_QUERY = "insert into tariffs(name,service_type,  price_per_day,isActive) values (?,?,?,?)";
    private static final String FIND_BY_FIELD_QUERY = "select * from tariffs where name = ?";
    private static final String UPDATE_QUERY = "UPDATE tariffs SET item=? WHERE name=?";
    private static final String DELETE_QUERY = "DELETE  FROM tariffs WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM tariffs";
    private static final String SQL_CALC_FOUND_ROWS = "select SQL_CALC_FOUND_ROWS * from tariffs limit ?, ?";
    private static Logger logger = LogManager.getLogger(TariffDao.class);

    private int noOfRecords;

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Tariff create(Tariff tariff) throws TariffException {
        logger.debug("Start tariff creating");
        if (tariff == null) {
            logger.error("tariff not found");

            throw new IllegalArgumentException();
        }


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, tariff.getName());
            pst.setString(2, String.valueOf(tariff.getType()));
            pst.setInt(3, tariff.getPricePerDay());
            pst.setBoolean(4, tariff.isActive());


            int status = pst.executeUpdate();

            if (status != 1) throw new TariffException("Created more than one record!!");
            ResultSet keys = pst.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                tariff.setId(id);
                System.out.println(tariff.getId());
            }

        } catch (Exception ex) {
            logger.debug("Problem with creating tariff: " + ex.getMessage());
            throw new TariffException(ex.getMessage(), ex);
        }

        logger.debug("Tariff created");


        return tariff;
    }

    @Override
    public Tariff findByField(String value) {
        Tariff tariff = new Tariff();

        logger.debug("Start tariff searching....");


        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_BY_FIELD_QUERY);) {

            pst.setString(1, value);

            ResultSet resultSet = pst.executeQuery();
            resultSet.next();


            tariff.setName(resultSet.getString("name"));
            tariff.setType(ServiceType.valueOf(resultSet.getString("service_type")));
            tariff.setPricePerDay(resultSet.getInt("price_per_day"));
            tariff.setActive(resultSet.getBoolean("isActive"));
            tariff.setCreated(LocalDateTime.parse(resultSet.getString("created")));
            tariff.setUpdated(LocalDateTime.parse(resultSet.getString("updated")));


        } catch (Exception ex) {
            logger.debug("Problem with searching tariff: " + ex.getMessage());
        }

        logger.debug("Tariff searched");

        System.out.println(tariff.toString());
        return tariff;
    }


    @Override
    public Tariff update(Tariff item) {
        Tariff tariff = new Tariff();

        logger.debug("Start tariff updating....");
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_QUERY);) {
            pst.setInt(1, tariff.getId());

            tariff.setName(item.getName());
            tariff.setType(item.getType());
            tariff.setPricePerDay(item.getPricePerDay());
            tariff.setActive(item.isActive());
            tariff.setCreated(item.getCreated());
            tariff.setUpdated(item.getUpdated());

            int status = pst.executeUpdate();
            if (status != 1) throw new UserException("Updated more than one record!!");


        } catch (Exception ex) {

            logger.debug("Problem with updating tariff: " + ex.getMessage());
        }

        logger.debug("Tariff updated");
        return tariff;
    }

    @Override
    public boolean delete(int id) {
        boolean status_boolean = false;
        logger.debug("Start tariff deleting....");

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_QUERY);) {
            pst.setInt(1, id);

            int status = pst.executeUpdate();
            if (status == 1) {
                status_boolean = true;
            }
            if (status != 1) throw new UserException("Deleted more than one record!!");
            con.close();
        } catch (Exception ex) {
            logger.debug("Problem with deleting tariff: " + ex.getMessage());
        }

        logger.debug("Tariff deleted");
        return status_boolean;
    }

    @Override
    public List<Tariff> findAll() {
        logger.debug("Start  searching all tariffs....");
        List<Tariff> tariffsList = null;

        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_ALL_QUERY);) {
            ResultSet result = pst.executeQuery();
            tariffsList = new ArrayList<>();
            Tariff tariff = null;
            while (result.next()) {
                tariff = new Tariff();
                tariff.setId(result.getInt("id"));
                tariff.setName(result.getString("name"));
                tariff.setType(ServiceType.valueOf(result.getString("service_type")));
                tariff.setPricePerDay(result.getInt("price_per_day"));
                tariff.setActive(result.getBoolean("isActive"));
                tariff.setCreated(result.getTimestamp("created").toLocalDateTime());
                tariff.setUpdated(result.getTimestamp("updated").toLocalDateTime());

                tariffsList.add(tariff);

            }


        } catch (Exception ex) {
            logger.debug("Problem with searching all tariffs: " + ex.getMessage());
        }

        logger.debug("All Tariffs searched");
        System.out.println(tariffsList + "dao");

        return tariffsList;
    }

    public List<Tariff> getAll(int offset, int noOfRecords) {

        Connection con = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            con = DataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Tariff> tariffsList = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(SQL_CALC_FOUND_ROWS);
            statement = con.createStatement();
            pstmt.setInt(1, offset);
            pstmt.setInt(2, noOfRecords);

            resultSet = pstmt.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                ServiceType type = ServiceType.valueOf(resultSet.getString("service_type"));
                int pricePerDay = resultSet.getInt("price_per_day");
                boolean isActive = resultSet.getBoolean("isActive");
                LocalDateTime created = resultSet.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = resultSet.getTimestamp("updated").toLocalDateTime();

                Tariff tariff = new Tariff(id, name, type, pricePerDay, isActive, created, updated);
                tariffsList.add(tariff);
            }
            resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
                if (resultSet != null)
                    resultSet.close();
                if (pstmt != null)
                    pstmt.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tariffsList;

    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
