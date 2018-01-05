public class InternationalCustomer extends Customer{
    private String country, city;

    public InternationalCustomer(int customerID, String name, String surname, String country, String city) {
        super(customerID, name, surname);
        this.country = country;
        this.city = city;
    }

    @Override
    public String toString() {
        return "InternationalCustomer{" +
                super.toString() +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
