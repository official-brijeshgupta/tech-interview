package interviews.tech.companies.atlassian.connectionpool;

import lombok.Value;
import org.springframework.util.Assert;

@Value
public class SimpleConnectionPoolConfig {
    private static final int DEFAULT_MAX_POOL_SIZE = 10;
    private static final int DEFAULT_INITIAL_POOL_SIZE = 1;
    private static final int DEFAULT_CONNECTION_TIMEOUT = 500;

    private final int maxPoolSize;
    private final int initialPoolSize;
    private final int connectionTimeOutInMillis;
    private final String userName;
    private final String password;
    private final String host;

    private final int connectionPoolSize;

    public SimpleConnectionPoolConfig(
            String userName,
            String password,
            String host){
        this(
                DEFAULT_CONNECTION_TIMEOUT,
                DEFAULT_MAX_POOL_SIZE,
                DEFAULT_INITIAL_POOL_SIZE,
                userName, password, host);
    }

    public SimpleConnectionPoolConfig(
            int connectionTimeOutInMillis,
            int maxConnectionPoolSize,
            int initialConnectionPoolSize,
            String userName,
            String password,
            String host
            ){
        this.maxPoolSize = maxConnectionPoolSize;
        this.initialPoolSize = initialConnectionPoolSize;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.connectionTimeOutInMillis = connectionTimeOutInMillis;

        connectionPoolSize = 0;

        validate();
    }

    private void validate(){
        Assert.isTrue(
                initialPoolSize <= maxPoolSize,
                "Initial pool size cannot be greater than max pool size");
    }

}
