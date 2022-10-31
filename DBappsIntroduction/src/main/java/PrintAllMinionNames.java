

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class PrintAllMinionNames {
    private static final String GET_ALL_MINIONS = "SELECT `name` FROM `minions`";
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();
        Scanner scan = new Scanner(System.in);
        PreparedStatement getAllMinions = connection.prepareStatement(GET_ALL_MINIONS);
        ResultSet rs = getAllMinions.executeQuery();

        ArrayDeque<String> minionsName = new ArrayDeque<>();

        while (rs.next()){
            minionsName.offer(rs.getString("name"));
        }
        while (minionsName.size() > 2){
            System.out.println(minionsName.pollFirst());
            System.out.println(minionsName.pollLast());
        }
        while(!minionsName.isEmpty()) {
            System.out.println(minionsName.poll());
        }
        connection.close();
    }
}
