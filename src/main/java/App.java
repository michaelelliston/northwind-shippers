import org.apache.commons.dbcp2.BasicDataSource;
import utilities.InputGetter;

import java.util.ArrayList;

public class App {

    static ShipperDao shipperDao = new ShipperDao(new BasicDataSource());

    public static void main(String[] args) {

        ArrayList<Shipper> shippers = shipperDao.getAllShippers();

        for (Shipper shipper : shippers) {
            System.out.println(shipper);
        }

        shipperDao.insertShipper("Magician", "111-222-3345");

        shippers = shipperDao.getAllShippers();

        for (Shipper shipper : shippers) {
            System.out.println(shipper);
        }

        String userInput = InputGetter.getString("\nTo update a shipper's Phone Number, please input their Shipper ID: ");
        long parsedUserInput = Long.parseLong(userInput);

        shipperDao.updateShipperPhoneNumber(parsedUserInput,"999-888-7373");

        shippers = shipperDao.getAllShippers();

        for (Shipper shipper : shippers) {
            System.out.println(shipper);
        }

        userInput = InputGetter.getString("\nTo remove a shipper, please input their Shipper ID: ");
        parsedUserInput = Long.parseLong(userInput);

        shipperDao.removeShipper(parsedUserInput);

        shippers = shipperDao.getAllShippers();

        for (Shipper shipper : shippers) {
            System.out.println(shipper);
        }

    }
}
