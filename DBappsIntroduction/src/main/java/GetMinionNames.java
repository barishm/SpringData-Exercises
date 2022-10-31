

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {
    static final String GET_MINIONS_NAMES = """
                SELECT m.`name`, m.age
                FROM minions_villains AS mv
                JOIN minions AS m
                ON m.id = mv.minion_id
                WHERE mv.villain_id = ?
                GROUP BY mv.minion_id;""";

    static final String GET_VILLAIN_NAME_BY_ID = "SELECT `name` FROM `villains` WHERE `id` = ?";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        Scanner scan = new Scanner(System.in);
        int villainID = Integer.parseInt(scan.nextLine());

        PreparedStatement statement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        statement.setInt(1,villainID);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()) {
            final String villainName = resultSet.getString(Utils.COLUMN_LABEL_NAME);
            System.out.println("Villain: " + villainName);
        }
        statement = connection.prepareStatement(GET_MINIONS_NAMES);
        statement.setInt(1,villainID);
        resultSet = statement.executeQuery();
        int minionCount = 1;
        while (resultSet.next()){
            final String minionName = resultSet.getString(Utils.COLUMN_LABEL_NAME);
            final int minionsAge = resultSet.getInt(Utils.COLUMN_LABEL_AGE);
            System.out.println(minionCount++ +". "+minionName+" "+minionsAge);
        }
    }
}
