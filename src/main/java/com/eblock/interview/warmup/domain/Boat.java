package com.eblock.interview.warmup.domain;

import java.util.Objects;

public class Boat extends InventoryItem {

	private MotorMount motorMount;

	public MotorMount getMotorMount() {
		return motorMount;
	}

	public void setMotorMount(MotorMount motorMount) {
		this.motorMount = motorMount;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Boat boat = (Boat) o;
        return Objects.equals(motorMount, boat.motorMount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), motorMount);
    }

    @Override
    public String toString() {
        return "Boat{" +
                "motorMount='" + motorMount + '\'' +
                "} " + super.toString();
    }
	
}
