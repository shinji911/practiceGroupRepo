package com.awesome.motoinventory.dao;
import com.awesome.motoinventory.model.Motorcycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MotorcycleDaoJdbcTemplateImpl implements MotorcycleDao {
    // Prepared SQL Statements (Constants)
    private static final String INSERT_MOTORCYCLE_SQL =
            "INSERT INTO motorcycle (price, vin, make, model, year, color) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_MOTORCYCLE_SQL =
            "SELECT * FROM motorcycle WHERE id = ?";
    private static final String SELECT_ALL_MOTORCYCLE_SQL =
            "SELECT * FROM motorcycle";
    private static final String UPDATE_MOTORCYCLE_SQL =
            "UPDATE motorcycle SET price = ?, vin = ?, make = ?, model = ?, year = ?, color = ? WHERE id = ?";
    private static final String DELETE_MOTORCYCLE_SQL =
            "DELETE FROM motorcycle WHERE id = ?";

    // Properties
    private JdbcTemplate jdbcTemplate;

    // Constructor
    @Autowired
    public MotorcycleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Methods
    @Override
    @Transactional
    public Motorcycle addMotorcycle(Motorcycle motorcycle) {
        jdbcTemplate.update(INSERT_MOTORCYCLE_SQL,
                motorcycle.getPrice(),
                motorcycle.getVin(),
                motorcycle.getMake(),
                motorcycle.getModel(),
                motorcycle.getYear(),
                motorcycle.getColor());
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        motorcycle.setId(id);
        return motorcycle;
    }

    @Override
    public Motorcycle getMotorcycle(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_MOTORCYCLE_SQL, this::mapRowToMotorcycle, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Motorcycle> getAllMotorcycle() {
        return jdbcTemplate.query(SELECT_ALL_MOTORCYCLE_SQL, this::mapRowToMotorcycle);
    }

    @Override
    @Transactional
    public void updateMotorcycle(Motorcycle motorcycle) {
        jdbcTemplate.update(UPDATE_MOTORCYCLE_SQL,
                motorcycle.getPrice(),
                motorcycle.getVin(),
                motorcycle.getMake(),
                motorcycle.getModel(),
                motorcycle.getYear(),
                motorcycle.getColor(),
                motorcycle.getId());
    }

    @Override
    @Transactional
    public void deleteMotorcycle(Integer id) {
        jdbcTemplate.update(DELETE_MOTORCYCLE_SQL, id);
    }

    // Helper Methods
    private Motorcycle mapRowToMotorcycle(ResultSet resultSet, int RowNumber) throws SQLException {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setId(resultSet.getInt("id"));
        motorcycle.setPrice(resultSet.getBigDecimal("price"));
        motorcycle.setVin(resultSet.getString("vin"));
        motorcycle.setMake(resultSet.getString("make"));
        motorcycle.setModel(resultSet.getString("model"));
        motorcycle.setYear(resultSet.getString("year"));
        motorcycle.setColor(resultSet.getString("color"));
        return motorcycle;
    }
}
