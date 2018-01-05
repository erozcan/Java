public class NationalCustomer extends Customer {
    private int licencePlateNumber;
    private String occupation;

    public NationalCustomer(int customerID, String name, String surname, int licencePlateNumber, String occupation) {
        super(customerID, name, surname);
        this.licencePlateNumber = licencePlateNumber;
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "NationalCustomer{" +
                super.toString() +
                "licencePlateNumber=" + licencePlateNumber +
                ", occupation='" + occupation + '\'' +
                '}';
    }

    public int getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public void setLicencePlateNumber(int licencePlateNumber) {
        this.licencePlateNumber = licencePlateNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
