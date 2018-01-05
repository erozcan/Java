public class Customer {
    private int customerID;
    private String name, surname;

    public Customer() {

    }

    public Customer(int customerID, String name, String surname) {
        this.customerID = customerID;
        this.name = name;
        this.surname = surname;
    }

    public Customer copy(){
        return new Customer(customerID, name, surname);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
