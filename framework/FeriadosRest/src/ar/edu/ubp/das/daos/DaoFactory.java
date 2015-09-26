package ar.edu.ubp.das.daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {

    private static Properties propFile = new Properties();
    private static boolean    loadProp = false;

    private DaoFactory() {}

    public static Dao getDao(String daoName) throws SQLException {
        try {
            return Dao.class.cast(Class.forName(DaoFactory.getDaoClassName(daoName)).newInstance());
        }
        catch(InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private static String getDaoClassName(String daoName) throws SQLException {
        try {
        	if(!DaoFactory.loadProp) {
                InputStream file = DaoFactory.class.getResourceAsStream("DaoFactory.properties");
                DaoFactory.propFile.load(file);
                file.close();

                DaoFactory.loadProp = true;
            }
            return DaoFactory.class.getPackage().getName() + "." + DaoFactory.propFile.getProperty("CurrentDaoFactory") + daoName + "Dao";
        }
        catch(IOException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

}
