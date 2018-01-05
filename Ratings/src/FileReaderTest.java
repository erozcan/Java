import org.junit.Test;

import java.io.File;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileReaderTest {
    @Test
    public void testFileExist() throws Exception {
        String path = ClassLoader.getSystemClassLoader().getResource("TestData.txt").getFile();
        File file = new File(path);
        assertTrue(file.exists());
    }

    @Test
    public void testReadWholeLine() throws Exception {
        String path = ClassLoader.getSystemClassLoader().getResource("TestData.txt").getFile();
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        assertEquals("5,A,B,C,D,E", fileReader.readLine());
    }

    @Test
    public void testParseString() throws Exception {
        String string = "5,A,B,C,D,E";
        StringTokenizer tokenizer = new StringTokenizer(string, ",");
        assertEquals(6, tokenizer.countTokens());

        int anInt = Integer.valueOf(tokenizer.nextToken());
        assertEquals(5, anInt);

        String[] product = new String[anInt];
        for (int i = 0; i < anInt; i++) {
            product[i] = tokenizer.nextToken();
        }

        assertEquals(product[0], "A");
        assertEquals(product[1], "B");
        assertEquals(product[2], "C");
        assertEquals(product[3], "D");
        assertEquals(product[4], "E");
    }

    @Test
    public void testReadProducts() throws Exception {
        String path = ClassLoader.getSystemClassLoader().getResource("TestData.txt").getFile();
        File file = new File(path);
        FileReader fileReader = new FileReader(file);

        String[] products = fileReader.getProducts();
        assertEquals(5, products.length);
        assertEquals(products[0], "A");
        assertEquals(products[1], "B");
        assertEquals(products[2], "C");
        assertEquals(products[3], "D");
        assertEquals(products[4], "E");
    }

    @Test
    public void getNextCustomer() throws Exception {
        String path = ClassLoader.getSystemClassLoader().getResource("TestData.txt").getFile();
        File file = new File(path);
        FileReader fileReader = new FileReader(file);

        fileReader.getProducts();
        Customer customer = fileReader.getNextCustomer();

        assertTrue(customer instanceof NationalCustomer);
        NationalCustomer nationalCustomer = (NationalCustomer) customer;
        assertEquals(101, nationalCustomer.getCustomerID());
        assertEquals("Ali", nationalCustomer.getName());
        assertEquals("Ceviz", nationalCustomer.getSurname());
        assertEquals(32, nationalCustomer.getLicencePlateNumber());
        assertEquals("Bilgisayar Muhendisi", nationalCustomer.getOccupation());
    }

    @Test
    public void testGetNextRatings() throws Exception {
        String path = ClassLoader.getSystemClassLoader().getResource("TestData.txt").getFile();
        File file = new File(path);
        FileReader fileReader = new FileReader(file);

        fileReader.getProducts();
        fileReader.getNextCustomer();
        int[] ratings = fileReader.getNextRatings();

        assertEquals(3, ratings[0]);
        assertEquals(4, ratings[1]);
        assertEquals(3, ratings[2]);
        assertEquals(5, ratings[3]);
        assertEquals(1, ratings[4]);

    }
}
