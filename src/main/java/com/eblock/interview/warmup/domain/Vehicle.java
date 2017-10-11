package com.eblock.interview.warmup.domain;

import java.util.Objects;

public class Vehicle extends InventoryItem {

    private String make;
    private String model;
    private Integer cylinders;
    private Double displacement;
    private FuelType fuelType;
    private Odometer odometer;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCylinders() {
		return cylinders;
	}

	public void setCylinders(Integer cylinders) {
		this.cylinders = cylinders;
	}
	
	public Double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Double displacement) {
		this.displacement = displacement;
	}
	
	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public Odometer getOdometer() {
		return odometer;
	}

	public void setOdometer(Odometer odometer) {
		this.odometer = odometer;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(make, vehicle.make) &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(cylinders, vehicle.cylinders) && 
                Objects.equals(displacement, vehicle.displacement) &&
                Objects.equals(fuelType, vehicle.fuelType) &&
                Objects.equals(odometer, vehicle.odometer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), make, model, cylinders, displacement, fuelType, odometer);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", cylinders='" + cylinders + '\'' +
                ", displacement='" + displacement + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", odometer='" + odometer + '\'' +
                "} " + super.toString();
    }
}
