

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;


public class IncreaseMinionAge {

    static final String GET_ALL_MINIONS = "SELECT * FROM `minions`";
    static final String UPDATE_MINIONS_BY_ID = "UPDATE minions SET age = age + 1, `name` = lower(`name`) WHERE id IN (%s);";
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        Connection connection = Utils.getSQLConnection();

        int[] minionIds = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        StringBuilder paramsNumber = new StringBuilder();
        for (int minionId : minionIds) {
            paramsNumber.append("?,");
        }
        paramsNumber.deleteCharAt(paramsNumber.length() - 1);
        String updateQuery = String.format(UPDATE_MINIONS_BY_ID, paramsNumber);

        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        int paramNumber = 1;
        for (int minionId : minionIds) {
            preparedStatement.setInt(paramNumber, minionId);
            paramNumber++;
        }
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement(GET_ALL_MINIONS);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getInt("age"));
        }
    }

}
