package com.eblock.interview.warmup.graphql;

import java.util.Locale;

public class GraphQLContext {
	
	private Locale locale;
	
	public GraphQLContext(Locale locale) {
		this.locale = locale;
	}
	
	public Locale getLocale() {
		return this.locale;
	}
	
}
