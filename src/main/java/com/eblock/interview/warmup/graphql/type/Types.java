package com.eblock.interview.warmup.graphql.type;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.GraphQLType;

@Component
public class Types {
	
	private final VehicleType vehicleType;
	private final BoatType boatType;

	@Autowired
	public Types(VehicleType vehicleType, BoatType boatType) {
		this.vehicleType = vehicleType;
		this.boatType = boatType;
	}
	
	public Set<GraphQLType> getTypes() {
		Set<GraphQLType> types = new LinkedHashSet<>();
		types.add(this.vehicleType.get());
		types.add(this.boatType.get());
		
		return types;
	}
	
}
