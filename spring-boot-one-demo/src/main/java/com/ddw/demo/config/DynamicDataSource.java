package com.ddw.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

	@Override
	protected Object determineCurrentLookupKey() {
		return contextHolder.get();
	}

	public enum DatabaseType {
		MASTER, SLAVE
	}

	public static void master(){
		contextHolder.set(DatabaseType.MASTER);
	}


	public static void slave(){
		contextHolder.set(DatabaseType.SLAVE);
	}

	public static void setDatabaseType(DatabaseType type) {
		contextHolder.set(type);
	}

	public static DatabaseType getType(){
		return contextHolder.get();
	}

}
