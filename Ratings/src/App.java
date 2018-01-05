import java.io.EOFException;
import java.io.File;
import java.util.function.Predicate;

public class App {
    private static Customer[] customers = new Customer[200];
    private static int[][] ratings = new int[200][];
    private static String[] products;
    public static void main(String[] args) {
        //get data file
        String path = ClassLoader.getSystemClassLoader().getResource("Data.txt").getFile();
        File file = new File(path);
        FileReader fileReader = getFileReader();

        //read first line
        products = fileReader.getProducts();
        readAllData(fileReader);
        System.out.println("Her bir ürün için ortalama derecelendirme puanı");
        madde1();
        System.out.println("\n");
        System.out.println("Sadece ulusal müşteriler için ortalama derecelendirme puanı");
        madde2();
        System.out.println("\n");
        System.out.println("Sadece uluslararası müşteriler için ortalama derecelendirme puanı");
        madde3();
        System.out.println("\n");
        System.out.println("Sadece ulusal müşterilerden mesleği doktor olanların derecelendirme puanı");
        madde4();
        System.out.println("\n");
        System.out.println("Her bir ürün için,o ürüne ait ortalama derecelendirmenin altında puan vermiş olan ulusal\n" +
"müşterilerin bilgileri");
        madde5();
         System.out.println("\n");
        System.out.println("Her bir ürün için,o ürüne ait ortalama derecelendirmenin altında puan vermiş olan uluslararası\n" +
"müşterilerin bilgileri");
        madde6();
        System.out.println("\n");
        
    }

    private static void madde6(){
        float[] averages = new float[products.length];
        for (int i = 0; i < averages.length; i++) {
            averages[i] = getAverageOf(i);
        }

        for (int i = 0; i < products.length; i++) {
            System.out.println("Product: " + products[i]);
            printNationalCustomersBelowAverage_International(i, averages[i]);
            System.out.println("--------------------------------------------");
        }
    }

    private static void printNationalCustomersBelowAverage_International(int i, float average) {
        for (int j = 0; j < customers.length; j++) {
            if (customers[j] == null) break;
            else{
                if (customers[j] instanceof InternationalCustomer &&
                        ratings[j][i] > average){
                    System.out.println(customers[j]);
                }
            }
        }

    }

    private static void madde5(){
        float[] averages = new float[products.length];
        for (int i = 0; i < averages.length; i++) {
            averages[i] = getAverageOf(i);
        }

        for (int i = 0; i < products.length; i++) {
            System.out.println("Product: " + products[i]);
            printNationalCustomersBelowAverage(i, averages[i]);
            System.out.println("-------------------------------------");
        }

    }

    private static void printNationalCustomersBelowAverage(int i, float average) {
        for (int j = 0; j < customers.length; j++) {
            if (customers[j] == null) break;
            else{
                if (customers[j] instanceof NationalCustomer &&
                        ratings[j][i] > average){
                    System.out.println(customers[j]);
                }
            }
        }
    }

    private static void madde4(){
        for (int i = 0; i < products.length; i++) {
            System.out.println("Product: " + products[i] + " Average: " + getAverageOf_NationalCustomer_DoctorsOnly(i));
        }
    }

    private static float getAverageOf_NationalCustomer_DoctorsOnly(int i) {
        float total = 0;
        float ratingNumber = 0;
        for (int j = 0; j < 200; j++) {
            if ( ratings[j] != null &&
                    customers[j] instanceof NationalCustomer &&
                    ((NationalCustomer) customers[j]).getOccupation().equalsIgnoreCase("Doktor")){
                total += ratings[j][i];
                ratingNumber++;
            }
        }
        return total / ratingNumber;
    }

    private static void madde3(){
        for (int i = 0; i < products.length; i++) {
            System.out.println("Product: " + products[i] + " Average: " + getAverageOf_InternationalOnly(i));
        }
    }

    private static float getAverageOf_InternationalOnly(int i) {
        float total = 0;
        float ratingNumber = 0;
        for (int j = 0; j < 200; j++) {
            if ( ratings[j] != null){
                if (customers[j] instanceof InternationalCustomer) {
                    total += ratings[j][i];
                    ratingNumber++;
                }
            }
        }
        return total / ratingNumber;
    }

    private static void madde2(){
        for (int i = 0; i < products.length; i++) {
            System.out.println("Product: " + products[i] + " Average: " + getAverageOf_NationalOnly(i));
        }
    }

    private static float getAverageOf_NationalOnly(int i) {
        float total = 0;
        float ratingNumber = 0;
        for (int j = 0; j < 200; j++) {
            if ( ratings[j] != null){
                if (customers[j] instanceof NationalCustomer) {
                    total += ratings[j][i];
                    ratingNumber++;
                }
            }
        }
        return total / ratingNumber;
    }

    private static void madde1(){
        for (int i = 0; i < products.length; i++) {
            System.out.println("Product: " + products[i] + " Average: " + getAverageOf(i));
        }
    }

    private static float getAverageOf(int i) {
        float total = 0;
        float ratingNumber = 0;
        for (int j = 0; j < 200; j++) {
            if ( ratings[j] != null){
                total += ratings[j][i];
                ratingNumber++;
            }
        }
        return total / ratingNumber;
    }

    private static void readAllData(FileReader fileReader) {
        int index = 0;
        while (true){
            try {
                customers[index] = fileReader.getNextCustomer();
                ratings[index] = fileReader.getNextRatings();
                index++;
            }
            catch (EOFException e) {
                //end of file reached
                break;
            }
        }
    }

    public static FileReader getFileReader() {
        String path = ClassLoader.getSystemClassLoader().getResource("TestData.txt").getFile();
        File file = new File(path);
        return new FileReader(file);
    }


}
