package com.eblock.interview.warmup.domain;

public enum MotorMount {
    INBOARD("inboard"), OUTBOARD("outboard"), NONE("none");
	
	private String name;
	
    MotorMount(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public static MotorMount getByName(String name) {
    	switch(name.toLowerCase()) {
    	case "inboard":
    		return INBOARD;
    	case "outboard":
    		return MotorMount.OUTBOARD;
    	default:
    		return NONE;
    	}
    }
}
