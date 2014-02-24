package net.sourceforge.beanoa.db;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import net.sourceforge.beanoa.AbstractBeanoaEncoder;
import net.sourceforge.beanoa.BeanoaEncoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.example.db.derby.Test;
import net.sourceforge.beanoa.exception.BeanoaException;
import net.sourceforge.beanoa.util.Commons;

public final class DataBaseBeanoaEncoder extends AbstractBeanoaEncoder<InstanceID> implements BeanoaEncoder<Object, InstanceID> {
	private static final Logger LOGGER = Logger.getLogger(DataBaseBeanoaEncoder.class.getName());
	
	static{
		try{
			Class.forName(Test.DRIVER);
			
		}catch(ClassNotFoundException _){
			LOGGER.throwing(DataBaseBeanoaEncoder.class.getName(), "<init>", _);
		}
	};
	
	private BeanoaEncoder<Object, ?> encoder;
	public DataBaseBeanoaEncoder(final Wrapper<InstanceID> wrapper, final BeanoaEncoder<Object, ?> encoder){
		super(wrapper);
		this.encoder = encoder;
	}
	
	public void overall(final Object input) {

		Connection _connection = null;
		PreparedStatement _stm = null;
		Statement _stmid = null;
		ResultSet _resid = null;
		try{
			_connection = DriverManager.getConnection(Test.URL);
			_connection.setAutoCommit(false);
			
			_stmid = _connection.createStatement();
			_resid = _stmid.executeQuery(Test.NEXT_ID);
			int _id = 0;
			if(_resid.next()){
				_id = _resid.getInt(1);
			}
			_stm = _connection.prepareStatement(Test.INSERT);
			
			_stm.setInt(   1, _id);
			_stm.setString(2, encoder.getClass().getName());
			
			//decorated encoder
			encoder.overall(input);
			
			final ByteArrayOutputStream _streamo = new ByteArrayOutputStream();
			final ObjectOutputStream _stream = new ObjectOutputStream(_streamo);
			_stream.writeObject(encoder.getWrapper().getValue());
			
			final Blob _blob = _connection.createBlob();
			_blob.setBytes(1L, _streamo.toByteArray());
			
			_stm.setBlob(3, _blob);
			
			_stm.executeUpdate();
			_connection.commit();
			
			getWrapper().setValue(new InstanceID(_id));
			
		}catch(Exception _e){
			try{
				if(null!= _connection){
					_connection.rollback();
				}
			}catch(SQLException __e){}
			
			throw new BeanoaException(_e);
		}finally{
			Commons.close(_resid);
			Commons.close(_stmid);
			Commons.close(_stm);
			Commons.close(_connection);
		}
	}

}
