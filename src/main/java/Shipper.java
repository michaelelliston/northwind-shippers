public class Shipper {

    private final long shipperId;
    private final String shipperName;
    private final String phoneNumber;

    public Shipper(long shipperId, String shipperName, String phoneNumber) {
        this.shipperName = shipperName;
        this.phoneNumber = phoneNumber;
        this.shipperId = shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getShipperId() {
        return shipperId;
    }

    @Override
    public String toString() {
        return String.format("Shipper ID: %d | Shipper Name: %s | Shipper Phone Number: %s", this.getShipperId(), this.getShipperName(), this.getPhoneNumber());
    }
}
