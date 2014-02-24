package net.sourceforge.beanoa.db;

import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sourceforge.beanoa.AbstractBeanoaDecoder;
import net.sourceforge.beanoa.BeanoaDecoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.example.db.derby.Test;
import net.sourceforge.beanoa.exception.BeanoaException;
import net.sourceforge.beanoa.util.Commons;

public final class DataBaseBeanoaDecoder extends AbstractBeanoaDecoder<InstanceID> implements BeanoaDecoder<Object, InstanceID> {

	private BeanoaDecoder<Object, ? extends Object> decoder;
	public DataBaseBeanoaDecoder(final Wrapper<InstanceID> wrapper, final BeanoaDecoder<Object, ? extends Object> decoder) {
		super(wrapper);
		this.decoder = decoder;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public Object overall() {
	
		Connection _connection = null;
		PreparedStatement _stm = null;
		ResultSet _res = null;
		Object _result = null;
		try{
			_connection = DriverManager.getConnection(Test.URL);
			_stm = _connection.prepareStatement(Test.SELECT);
			_stm.setInt(1, wrapper.getValue().intValue());
			
			_res = _stm.executeQuery();
			if(_res.next()){
				final Blob _serial = _res.getBlob(3);
				final ObjectInputStream _stream = new ObjectInputStream(_serial.getBinaryStream());
				final Object _dbo = _stream.readObject();
				
				final Wrapper _input = new Wrapper<Object>();
				_input.setValue(_dbo);
				decoder.setWrapper(_input);
				
				//decoding
				_result = decoder.overall();
			} else {
				throw new BeanoaException("Instance not found by ID: " + wrapper.getValue().intValue());
			}
		}catch(Exception _e){
			throw new BeanoaException(_e);
		} finally{
			Commons.close(_res);
			Commons.close(_stm);
			Commons.close(_connection);
		}
		
		return _result;
	}

}
