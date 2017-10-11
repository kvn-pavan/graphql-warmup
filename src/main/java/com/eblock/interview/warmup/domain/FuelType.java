package com.eblock.interview.warmup.domain;

public enum FuelType {
    DIESEL("Diesel"), ELECTRIC("Electric"), FLEX_FUEL("Flex Fuel / Electric Hybrid"), GASOLINE("Gasoline"), GASOLINE_ELECTRIC("Gasoline / Electric Hybrid"), GASOLINE_NATURAL("Gasoline / Natural Gas"), GASOLINE_PROPANE("Gasoline / Propane"), HYDROGEN("Hydrogen"), NATURAL_GAS("Natural Gas"), PROPANE("Propane"), UNKNOWN("unknown");
	
	private String name;
	
    FuelType(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public static FuelType getByName(String name) {
    	switch(name.toLowerCase()) {
    	case "diesel":
    		return DIESEL;
    	case "electric":
    		return ELECTRIC;
    	case "flex fuel / electric hybrid":
    		return FLEX_FUEL;
    	case "gasoline":
    		return GASOLINE;
    	case "gasoline / electric hybrid":
    		return GASOLINE_ELECTRIC;
    	case "gasoline / natural gas":
    		return GASOLINE_NATURAL;
    	case "gasoline / propane":
    		return GASOLINE_PROPANE;
    	case "hydrogen":
    		return HYDROGEN;
    	case "natural gas":
    		return NATURAL_GAS;
    	case "propane":
    		return PROPANE;
    	default:
    		return UNKNOWN;
    	}
    }
}
