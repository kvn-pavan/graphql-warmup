package com.eblock.interview.warmup.domain;

import java.util.Objects;

public class Odometer {

	private Integer mileage;
	private String unit;
	
	public Odometer() {}
	
	public Odometer(Integer mileage, String unit) {
		this.mileage = mileage;
		this.unit = unit;
	}
	
	public Integer getMileage() {
		return mileage;
	}
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Odometer that = (Odometer) o;
        return Objects.equals(mileage, that.mileage) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mileage, unit);
    }

    @Override
    public String toString() {
        return "Odometer{" +
                "mileage='" + mileage + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
	
}
