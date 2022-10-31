


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    private static final String UPDATE_TOWN_NAME = "update towns set name = upper(name) where country = ?";
    private static final String GET_TOWNS_BY_COUNTRY = "select name from towns where country = ?";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        Scanner scan = new Scanner(System.in);

        final String townName = scan.nextLine();

        PreparedStatement statement = connection.prepareStatement(UPDATE_TOWN_NAME);
        statement.setString(1,townName);
        int updatedCount = statement.executeUpdate();

        if(updatedCount == 0){
            System.out.println("No town names were affected");
            return;
        }
        System.out.printf("%d town names were affected.%n",updatedCount);

        PreparedStatement selectTowns = connection.prepareStatement(GET_TOWNS_BY_COUNTRY);
        selectTowns.setString(1,townName);
        ResultSet townsSet = selectTowns.executeQuery();
        ArrayList<String> towns = new ArrayList<>();
        while(townsSet.next()){
            towns.add(townsSet.getString(Utils.COLUMN_LABEL_NAME));
        }
        System.out.println(towns);


    }
}
