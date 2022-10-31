

import java.sql.*;
import java.util.Scanner;


public class IncreaseAgeStoredProcedure {
    static final String GET_MINION_BY_ID = "SELECT * FROM minions WHERE id = ?";
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        int minionId = Integer.parseInt(scan.nextLine());

        Connection connection = Utils.getSQLConnection();

        CallableStatement callableStatement = null;

        String query = "{call usp_get_older(?)}";
        callableStatement = connection.prepareCall(query);

        callableStatement.setInt(1, minionId);
        callableStatement.execute();

        PreparedStatement stmt = connection.prepareStatement(GET_MINION_BY_ID);
        stmt.setInt(1, minionId);
        ResultSet rs = stmt.executeQuery();
        rs.next();

        System.out.println(rs.getString("name") + " " + rs.getInt("age"));
    }
            /*
            DELIMITER $$
            DROP PROCEDURE IF EXISTS usp_get_older $$
            CREATE PROCEDURE usp_get_older(minion_id INT)
            BEGIN
                UPDATE minions SET age = age + 1 WHERE id = minion_id;
            END $$
            DELIMITER ;
         */
}
