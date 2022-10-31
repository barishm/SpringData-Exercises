

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {
    private static final String DELETE_VILLAIN_BY_ID = "delete from villains where id = ?";
    private static final String GET_VILLAIN_BY_ID = "select `name` from villains where id = ?";
    private static final String RELEASE_MINION_OF_VILLAIN = "delete from minions_villains where villain_id = ?";
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();
        Scanner scan = new Scanner(System.in);
        int villainID = Integer.parseInt(scan.nextLine());

        final PreparedStatement selectVillain = connection.prepareStatement(GET_VILLAIN_BY_ID);
        selectVillain.setInt(1,villainID);
        ResultSet villainSet = selectVillain.executeQuery();
        if(!villainSet.next()){
            System.out.println("No such villain was found");
            connection.close();
            return;
        }
        String villainName = villainSet.getString(Utils.COLUMN_LABEL_NAME);

        connection.setAutoCommit(false);

        try {
            PreparedStatement ReleaseMinions = connection.prepareStatement(RELEASE_MINION_OF_VILLAIN);
            ReleaseMinions.setInt(1,villainID);
            int releasedCount = ReleaseMinions.executeUpdate();

            PreparedStatement deleteVillainStatement = connection.prepareStatement(DELETE_VILLAIN_BY_ID);
            deleteVillainStatement.setInt(1,villainID);
            deleteVillainStatement.executeUpdate();

            System.out.printf("%s was deleted%n%d minions released",villainName,releasedCount);
            connection.commit();

        }catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
        }
        connection.close();
    }
}
