package com.eblock.interview.warmup.repository;

import com.eblock.interview.warmup.domain.Boat;
import com.eblock.interview.warmup.domain.FuelType;
import com.eblock.interview.warmup.domain.InventoryItem;
import com.eblock.interview.warmup.domain.MotorMount;
import com.eblock.interview.warmup.domain.Odometer;
import com.eblock.interview.warmup.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.money.Monetary;
import java.math.BigDecimal;
import java.util.Collections;

@Repository
public class InventoryItemRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public InventoryItemRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public InventoryItem get(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }

        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "SELECT type, " +
                            "id," +
                            "tag_line, " +
                            "description, " +
                            "year, " +
                            "price, " +
                            "currency_code, " +
                            "vehicle_make, " +
                            "vehicle_model, " +
                            "vehicle_cylinders, " +
                            "vehicle_displacement, " +
                            "vehicle_fuelType, " +
                            "vehicle_odometer_mileage, " +
                            "vehicle_odometer_unit, " +
                            "boat_motorMount " +
                            "FROM inventory_item " +
                            "WHERE id = :id",
                    Collections.singletonMap("id", id), (rs, rowNum) -> {
                        if (rs.getString(1).equals("VEHICLE")) {
                            final Vehicle vehicle = new Vehicle();
                            vehicle.setId(rs.getString(2));
                            vehicle.setTagLine(rs.getString(3));
                            vehicle.setDescription(rs.getString(4));
                            vehicle.setYear(rs.getInt(5));
                            final BigDecimal amount = rs.getBigDecimal(6);
                            final String currencyCode = rs.getString(7);
                            if (amount != null && !StringUtils.isEmpty(currencyCode)) {
                                vehicle.setPrice(Monetary.getDefaultAmountFactory().setCurrency(currencyCode).setNumber(amount).create());
                            }
                            vehicle.setMake(rs.getString(8));
                            vehicle.setModel(rs.getString(9));
                            vehicle.setCylinders(rs.getInt(10));
                            vehicle.setDisplacement(rs.getDouble(11));
                            vehicle.setFuelType(FuelType.getByName(rs.getString(12)));
                            vehicle.setOdometer(new Odometer(rs.getInt(13), rs.getString(14)));
                            return vehicle;
                        } else if(rs.getString(1).equals("BOAT")) {
                        	final Boat boat = new Boat();
                        	boat.setId(rs.getString(2));
                        	boat.setTagLine(rs.getString(3));
                        	boat.setDescription(rs.getString(4));
                        	boat.setYear(rs.getInt(5));
                            final BigDecimal amount = rs.getBigDecimal(6);
                            final String currencyCode = rs.getString(7);
                            if (amount != null && !StringUtils.isEmpty(currencyCode)) {
                            	boat.setPrice(Monetary.getDefaultAmountFactory().setCurrency(currencyCode).setNumber(amount).create());
                            }
                            boat.setMotorMount(MotorMount.getByName(rs.getString(15)));
                        	return boat;
                        }
                        throw new RuntimeException("Not Implemented for type " + rs.getString(1));
                    });
        } catch (final EmptyResultDataAccessException e) {
            return null;
        }
    }
}
