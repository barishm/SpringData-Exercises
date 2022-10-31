

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMinion {
    private static final String GET_TOWN_BY_NAME = "select id from towns where name = ?";
    private static final String GET_VILLAIN_BY_NAME = "select id from villains where name = ?";
    private static final String GET_LAST_MINION_ID = "select id from minions order by id desc";
    private static final String INSERT_TOWNS = "insert into towns (name) values(?)";
    private static final String INSERT_VILLAIN = "insert into villains (name,evilness_factor) values(?,'evil')";
    private static final String INSERT_MINION = "insert into minions (name,age,town_id) values(?,?,?)";
    private static final String INSERT_INTO_MAPPING = "insert into minions_villains (minion_id,villain_id) values (?,?)";


    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();
        final Scanner scan = new Scanner(System.in);

        final String[] minionInfo = scan.nextLine().split(" ");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];
        final String villainName = scan.nextLine().split(" ")[1];

        final int townID = insertTown(connection, minionTown);
        final int villainID = insertVillain(connection,villainName);
        final int minionID = insertMinion(connection,minionName,minionAge,townID);
        insertIntoMapping(connection,villainID,minionID, minionName,villainName);

    }

    private static void insertIntoMapping(Connection connection, int villainID, int minionID, String minionName, String villainName) throws SQLException {
        final PreparedStatement insertStatement = connection.prepareStatement(AddMinion.INSERT_INTO_MAPPING);
        insertStatement.setInt(1,minionID);
        insertStatement.setInt(2,villainID);
        insertStatement.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.%n",minionName,villainName);
    }

    private static int insertMinion(Connection connection, String minionName, int minionAge, int townID) throws SQLException {
        final PreparedStatement insertStatement = connection.prepareStatement(AddMinion.INSERT_MINION);
        insertStatement.setString(1,minionName);
        insertStatement.setInt(2,minionAge);
        insertStatement.setInt(3,townID);
        insertStatement.executeUpdate();
        final PreparedStatement selectStatement = connection.prepareStatement(AddMinion.GET_LAST_MINION_ID);
        final ResultSet rs = selectStatement.executeQuery();
        rs.next();
        return rs.getInt(Utils.COLUMN_LABEL_ID);
    }

    private static int insertVillain(Connection connection, String villainName) throws SQLException {
        final PreparedStatement selectStatement = connection.prepareStatement(AddMinion.GET_VILLAIN_BY_NAME);
        selectStatement.setString(1,villainName);
        final ResultSet resultSet = selectStatement.executeQuery();
        if(!resultSet.next()){
            final PreparedStatement insertStatement = connection.prepareStatement(AddMinion.INSERT_VILLAIN);
            insertStatement.setString(1,villainName);
            insertStatement.executeUpdate();
            final ResultSet newResultSet = selectStatement.executeQuery();
            newResultSet.next();

            System.out.printf("Villain %s was added to the database.%n",villainName);

            return newResultSet.getInt(Utils.COLUMN_LABEL_ID);
        }
        return resultSet.getInt(Utils.COLUMN_LABEL_ID);
    }
    private static int insertTown(Connection connection, String minionTown) throws SQLException {
        final PreparedStatement selectStatement = connection.prepareStatement(AddMinion.GET_TOWN_BY_NAME);
        selectStatement.setString(1,minionTown);
        final ResultSet resultSet = selectStatement.executeQuery();
        if(!resultSet.next()){
            final PreparedStatement insertStatement = connection.prepareStatement(AddMinion.INSERT_TOWNS);
            insertStatement.setString(1,minionTown);
            insertStatement.executeUpdate();
            final ResultSet newResultSet = selectStatement.executeQuery();
            newResultSet.next();

            System.out.printf("Town %s was added to the database.%n",minionTown);

            return newResultSet.getInt(Utils.COLUMN_LABEL_ID);

        }
        return resultSet.getInt(Utils.COLUMN_LABEL_ID);
    }
}
