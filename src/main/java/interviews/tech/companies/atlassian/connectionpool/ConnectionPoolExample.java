package interviews.tech.companies.atlassian.connectionpool;


import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolExample {
    public static void execute() {
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        SimpleConnectionPoolConfig config = new SimpleConnectionPoolConfig(
                "sa", "password", "jdbc:h2:mem:interview"
        );

        SimpleConnectionPool pool = SimpleConnectionPool.getInstance(config);

        for(int i=1; i<=20; i++){
           executorService.execute(()->test(pool));
        }
    }

    private static void  test(SimpleConnectionPool pool){
        try (Connection conn = pool.getConnection()){
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT name FROM country");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            pool.releaseConnection(conn);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
