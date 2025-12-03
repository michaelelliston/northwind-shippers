import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;

public class ShipperDao {

    private static final String[] USER_INFO = {"root", "yearup"};
    private static final String DB_URL = "jdbc:mysql://localhost:3306/northwind";
    private BasicDataSource dataSource;

    public ShipperDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER_INFO[0]);
        dataSource.setPassword(USER_INFO[1]);

    }

    public void insertShipper(String shipperName, String phoneNumber) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shippers (CompanyName, Phone) VALUES (?, ?);",
             Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, shipperName);
            preparedStatement.setString(2, phoneNumber);

            int rows = preparedStatement.executeUpdate();

            if (rows > 1) {
                System.out.println(rows + " records were added.\n");
            } else {
                System.out.println(rows + " record was added.\n");
            }

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                while (keys.next()) {
                    System.out.printf("Primary key %d was added\n\n", keys.getLong(1));
                }
            }

        } catch (SQLException e) {
            System.err.println("An error occurred: " + e);
        }
    }

    public ArrayList<Shipper> getAllShippers() {

        ArrayList<Shipper> shippers = new ArrayList<Shipper>();

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shippers")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Shipper shipper = new Shipper(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
                    shippers.add(shipper);
                }
            }
        } catch (SQLException e) {
            System.err.println("An error occurred: " + e);
        }
        return shippers;
    }

    public void updateShipperPhoneNumber(long shipperId, String phoneNumber) {

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shippers SET Phone = ? WHERE ShipperID = ?;")) {

            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setLong(2, shipperId);

            int rows = preparedStatement.executeUpdate();

            if (rows > 1) {
                System.out.println(rows + " records were updated.\n");
            } else {
                System.out.println(rows + " record was updated.\n");
            }

        } catch (SQLException e) {
            System.err.println("An error occurred: " + e);
        }
    }

    public void removeShipper(long shipperId) {

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shippers WHERE ShipperID = ?;")) {

            preparedStatement.setLong(1, shipperId);

            int rows = preparedStatement.executeUpdate();

            if (rows > 1) {
                System.out.println(rows + " records were removed.\n");
            } else {
                System.out.println(rows + " record was removed.\n");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred: " + e);
        }
    }
}
