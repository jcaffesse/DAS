package ar.edu.ubp.das.daos;

public abstract class MSSQLDao extends DaoImpl {

    protected MSSQLDao() {
        this.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.setUrl("jdbc:sqlserver://25.136.78.82;databaseName=finaldas;characterEncoding=ISO-8859-1");
        this.setUser("sa");
        this.setPasswd("qwerty");
    }

}
