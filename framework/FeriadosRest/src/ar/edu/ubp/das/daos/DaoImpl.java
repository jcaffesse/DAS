package ar.edu.ubp.das.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.beans.Bean;

public abstract class DaoImpl implements Dao {

    private String driver;
    private String url;
    private String user;
    private String passwd;

    private Connection        connection;
    private CallableStatement statement;

    @Override
    protected void finalize() throws Throwable {
        try {
            if(this.statement != null && !this.statement.isClosed()) {
                this.statement.close();
            }
        }
        catch(SQLException ex) {
            throw new Throwable(ex.getMessage());
        }
        finally {
            try {
                if(this.connection != null && !this.connection.isClosed()) {
                    this.connection.close();
                }
            }
            catch(SQLException ex) { 
                throw new Throwable(ex.getMessage());
            }
            finally {
                super.finalize();
            }
        }
    }

    public void connect() throws SQLException {
        try {
            Class.forName(this.driver).newInstance();
            this.connection = DriverManager.getConnection(this.url, this.user, this.passwd);
            this.connection.setAutoCommit(true);
        }
        catch(InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    public void disconnect() throws SQLException {
        this.statement.close();
        this.connection.close();
    }

    public int executeUpdate() throws SQLException {
    	int rows = 0;
        try {
            this.connection.setAutoCommit(false);
            rows = this.statement.executeUpdate();
            this.connection.commit();
        }
        catch(SQLException ex) {
            this.connection.rollback();
            throw new SQLException(ex.getMessage());
        }
        finally {
            this.connection.setAutoCommit(true);
        }
        return rows;
    }

    public List<Bean> executeQuery() throws SQLException {
        List<Bean> list   = new LinkedList<Bean>();
        System.out.println(statement.toString());
        ResultSet  result = this.statement.executeQuery();
        while(result.next()) {
            list.add(this.make(result));
        }
        return list;
    }
    
    public Boolean executeValidation() throws SQLException {
        ResultSet result = this.statement.executeQuery();
        result.next();
        return result.getBoolean("isValid");
    }
	
    protected void setDriver(String driver) {
        this.driver = driver;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    protected void setUser(String user) {
        this.user = user;
    }

    protected void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setProcedure(String procedure) throws SQLException {
        this.statement = this.connection.prepareCall("{ CALL " + procedure + " }");
    }
    
    public void setParameter(int paramIndex, long paramValue) throws SQLException {
    	this.statement.setLong(paramIndex, paramValue);
    }

    public void setParameter(int paramIndex, int paramValue) throws SQLException {
    	this.statement.setInt(paramIndex, paramValue);
    }
    
    public void setParameter(int paramIndex, short paramValue) throws SQLException {
    	this.statement.setShort(paramIndex, paramValue);
    }

    public void setParameter(int paramIndex, double paramValue) throws SQLException {
    	this.statement.setDouble(paramIndex, paramValue);
    }
    
    public void setParameter(int paramIndex, float paramValue) throws SQLException {
    	this.statement.setFloat(paramIndex, paramValue);
    }

    public void setParameter(int paramIndex, String paramValue) throws SQLException {
    	this.statement.setString(paramIndex, paramValue);
    }

    public void setParameter(int paramIndex, Date paramValue) throws SQLException {
    	this.statement.setDate(paramIndex, paramValue);
    }

    public void setOutParameter(int paramIndex, int sqlType) throws SQLException {
    	this.statement.registerOutParameter(paramIndex, sqlType);
    }
    
    public long getLongParam(String paramName) throws SQLException {
    	return this.statement.getLong(paramName);
    }
    
    public int getIntParam(String paramName) throws SQLException {
    	return this.statement.getInt(paramName);
    }
    
    public short getShortParam(String paramName) throws SQLException {
    	return this.statement.getShort(paramName);
    }
    
    public double getDoubleParam(String paramName) throws SQLException {
    	return this.statement.getDouble(paramName);
    }
    
    public double getFloatParam(String paramName) throws SQLException {
    	return this.statement.getFloat(paramName);
    }

    public String getStringParam(String paramName) throws SQLException {
    	return this.statement.getString(paramName);
    }
    
    public Date getDateParam(String paramName) throws SQLException {
    	return this.statement.getDate(paramName);
    }
    
    public long getLongParam(int paramIndex) throws SQLException {
    	return this.statement.getLong(paramIndex);
    }
    
    public int getIntParam(int paramIndex) throws SQLException {
    	return this.statement.getInt(paramIndex);
    }
    
    public short getShortParam(int paramIndex) throws SQLException {
    	return this.statement.getShort(paramIndex);
    }
    
    public double getDoubleParam(int paramIndex) throws SQLException {
    	return this.statement.getDouble(paramIndex);
    }
    
    public double getFloatParam(int paramIndex) throws SQLException {
    	return this.statement.getFloat(paramIndex);
    }

    public String getStringParam(int paramIndex) throws SQLException {
    	return this.statement.getString(paramIndex);
    }
    
    public Date getDateParam(int paramIndex) throws SQLException {
    	return this.statement.getDate(paramIndex);
    }
    
}
