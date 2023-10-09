package interviews.tech.atlassian.connectionpool;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection() throws InterruptedException;
    int currentPoolSize();
}
