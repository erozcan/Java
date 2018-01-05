import java.io.*;
import java.util.StringTokenizer;

public class FileReader {
    private File file;
    private DataInputStream inputStreamReader;
    private int productNumber;

    public int getProductNumber() {
        return productNumber;
    }

    public FileReader(File path) {
        this.file = path;
        try {
            inputStreamReader = new DataInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readLine() throws EOFException {
        String line= "";
        try {
            char aChar = 'a';
            do{
                if (inputStreamReader.available() <= 0) throw new EOFException();
                aChar = (char) inputStreamReader.read();
                line += aChar;
            }while (aChar != '\n');
            //remove \n from end
            line = line.replace("\n", "");

        } catch (EOFException e){
            throw e;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public String[] getProducts() {
        String firstLine = null;
        try {
            firstLine = readLine();
            StringTokenizer tokenizer = new StringTokenizer(firstLine, ",");

            int productNumber = Integer.valueOf(tokenizer.nextToken());
            this.productNumber = productNumber;

            String[] product = new String[productNumber];
            for (int i = 0; i < productNumber; i++) {
                product[i] = tokenizer.nextToken();
            }

            return product;
        } catch (EOFException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Customer getNextCustomer() throws EOFException {
        String line = readLine();
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        String customerType = tokenizer.nextToken();
        if (customerType.equals("n")) return createNationalCustomer(tokenizer);
        if (customerType.equals("i")) return createInterNationalCustomer(tokenizer);
        return null;
    }

    private InternationalCustomer createInterNationalCustomer(StringTokenizer tokenizer) {
        int id = Integer.valueOf(tokenizer.nextToken());
        String name = tokenizer.nextToken();
        String surname = tokenizer.nextToken();
        String country = tokenizer.nextToken();
        String city = tokenizer.nextToken();
        return new InternationalCustomer(id, name, surname, country, city);
    }

    private NationalCustomer createNationalCustomer(StringTokenizer tokenizer) {
        int id = Integer.valueOf(tokenizer.nextToken());
        String name = tokenizer.nextToken();
        String surname = tokenizer.nextToken();
        int licencePlateNumber = Integer.valueOf(tokenizer.nextToken());
        String occupation = tokenizer.nextToken();
        return new NationalCustomer(id, name, surname, licencePlateNumber, occupation);
    }

    public int[] getNextRatings() throws EOFException {
        String line = readLine();
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        int[] ratings = new int[productNumber];
        for (int i = 0; i < productNumber; i++) {
            ratings[i] = Integer.valueOf(tokenizer.nextToken());
        }
        return ratings;
    }
}
