package nl.fontys.sebivenlo.dao.pg;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

/**
 * This data source can be used in simple java se applications and doesn't
 * depend on container like GlassFish
 *
 * @author hvd
 */
public enum PGDataSource implements DataSource {

    DATA_SOURCE;
    private final static String SERVER = "localhost";
    private final static String DB = "simpledao";
    private final static String DBUSER = "exam";
    private final static String DBPASSWORD = "exam";
    private final static String CONNECTION_PROPS_FILE = "connection.properties";

    private final PGSimpleDataSource dataSource;

    private PGDataSource() {

        System.out.println( "PGDataSource" );
        Properties props = new Properties();
        try {
            props.load( new InputStreamReader( Files.newInputStream( Paths.get(
                   CONNECTION_PROPS_FILE ) ) ) );
        } catch ( IOException ex ) {
            Logger.getLogger( PGDataSource.class.getName() ).
                    log( Level.SEVERE, null, ex );
        }
        dataSource = new PGSimpleDataSource();
        dataSource.setServerName( props.getProperty( "server", SERVER ) );
        dataSource.setDatabaseName( props.getProperty( "db", DB ) );
        dataSource.setUser( props.getProperty( "dbuser", DBUSER ) );
        dataSource.setPassword( props.getProperty( "dbpassword", DBPASSWORD ) );

    }

    /**
     * Get a connection.
     *
     * @return a java.sql.Connection to the data source.
     * @throws java.sql.SQLException when connection cannot be made.
     */
    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }

    @Override
    public Connection getConnection( String username, String password ) throws
            SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public void setLogWriter( PrintWriter out ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public void setLoginTimeout( int seconds ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public <T> T unwrap( Class<T> iface ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public boolean isWrapperFor(
            Class<?> iface ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." );
    }
}