package net.sourceforge.beanoa.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public final class Commons {
	private static final Logger LOGGER = Logger.getLogger(Commons.class.getName());
	
	private Commons(){
	}
	
	public static void close(final Connection connection){
		
		if(null!= connection){
			try{
				connection.close();
			}catch(SQLException _e){
				LOGGER.throwing(Commons.class.getName(), "close", _e);
			}
		}
	}
	
	public static void close(final Statement statement){
		if(null!= statement){
			try{
				statement.close();
			}catch(SQLException _e){
				LOGGER.throwing(Commons.class.getName(), "close", _e);
			}
		}
	}
	
	public static void close(final ResultSet resultSet){
		if(null!= resultSet){
			try{
				resultSet.close();
			}catch(SQLException _e){
				LOGGER.throwing(Commons.class.getName(), "close", _e);
			}
		}
	}
}
