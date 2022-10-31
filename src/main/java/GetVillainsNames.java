

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainsNames {
    private static final String GET_VILLAIN_NAMES = """
                SELECT v.`name` AS name, count(distinct(mv.minion_id)) AS minions_count
                FROM minions_villains AS mv
                JOIN villains AS v
                ON mv.villain_id = v.id
                GROUP BY mv.villain_id
                HAVING minions_count > ?
                ORDER BY minions_count DESC;""";


    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();
        final PreparedStatement statement = connection.prepareStatement(GET_VILLAIN_NAMES);
        statement.setInt(1,15);
        final ResultSet resultSet = statement.executeQuery();


        while (resultSet.next()){
            final String villainName = resultSet.getString(Utils.COLUMN_LABEL_NAME);
            final int minionsCount = resultSet.getInt(Utils.COLUMN_MINIONS_COUNT);
            System.out.printf("%s %d",villainName,minionsCount);
        }

        connection.close();
    }
}
