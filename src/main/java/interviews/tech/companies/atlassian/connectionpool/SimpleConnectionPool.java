package interviews.tech.companies.atlassian.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SimpleConnectionPool implements ConnectionPool {

    private static SimpleConnectionPool instance;

    public synchronized static SimpleConnectionPool getInstance(final SimpleConnectionPoolConfig config) {
        if(instance == null){
            instance = new SimpleConnectionPool(config);
        }

        return instance;
    }

    private final SimpleConnectionPoolConfig config;
    private int currentPoolSize;
    private final BlockingQueue<Connection> pool;

    private SimpleConnectionPool(final SimpleConnectionPoolConfig config){
        this.config = config;
        this.pool = new LinkedBlockingQueue<>(config.getMaxPoolSize());
        this.currentPoolSize = 0;
        warmupPool();
    }

    private void warmupPool(){
        for (int i=1; i <= config.getInitialPoolSize(); i++){
            createConnection();
        }
    }


    private synchronized void createConnection() {
        try {
            Connection connection =
                    DriverManager.getConnection(config.getHost(), config.getUserName(), config.getPassword());
            boolean success = pool.offer(connection);
            if (success) {
                currentPoolSize++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public synchronized Connection getConnection() throws InterruptedException {
        if(pool.size() > 0)
            pool.poll();
        else if(currentPoolSize < config.getMaxPoolSize()){
            createConnection();
        }

        return pool.poll(config.getConnectionTimeOutInMillis(), TimeUnit.MILLISECONDS);
    }

    public void releaseConnection(Connection connection) {
        pool.offer(connection);
    }


    @Override
    public int currentPoolSize() {
       return pool.size();
    }
}
