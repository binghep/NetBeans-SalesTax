package salestax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import salestax.Item.ImportStatus;

public class SalesTax {

    public static void main(String[] args) throws IOException {
        //System.out.println("Thanks for the name, " + userName);
        ArrayList<Item> itemsList = new ArrayList<>();

        //Enter keywords representing items belonging to these categories:
        //books, food, and medical products:
        String[] basic_tax_exempt_keywords = {"book", "chocolates", "chocolate bar", "pills"};
        float basic_sales_tax_rate = .1F;
        float import_tax_rate = .05F;

        DecimalFormat df = new DecimalFormat("#0.00");

        itemsList = buildFromFile("Input1.txt");
        System.out.println("OUTPUT 1:");
        printBasket(itemsList, basic_tax_exempt_keywords);
        System.out.println("");
        
        itemsList = buildFromFile("Input2.txt");
        System.out.println("OUTPUT 2:");
        printBasket(itemsList, basic_tax_exempt_keywords);
        System.out.println("");
        
        itemsList = buildFromFile("Input3.txt");
        System.out.println("OUTPUT 3:");
        printBasket(itemsList, basic_tax_exempt_keywords);

    }

    public static ArrayList<Item> buildFromFile(String filename) throws IOException {
        ArrayList<Item> itemsList = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(filename));
        while (in.ready()) {
            String line = in.readLine();
            String[] tokens = line.split("\\s+at\\s+");
            Item item = new Item(tokens[0], Float.parseFloat(tokens[1]), tokens[0].contains("imported"));
            itemsList.add(item);
        }
        in.close();
        return itemsList;
    }

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }

    public static void printBasket(ArrayList<Item> itemsList, String[] basic_tax_exempt_keywords) {
        //--------print out each item with their price (include tax)-----------
        for (int i = 0; i < itemsList.size(); i++) {
            calcAndFillInTax(itemsList.get(i), basic_tax_exempt_keywords);
            System.out.println(itemsList.get(i).name + " : " + currencyFormat(itemsList.get(i).getTotalPrice()));
        }
        //--------total tax to pay--------------
        BigDecimal total_tax = new BigDecimal(0);
        for (int i = 0; i < itemsList.size(); i++) {
            total_tax = total_tax.add(itemsList.get(i).total_tax);
        }
        System.out.println("Sales Taxes: " + currencyFormat(total_tax));
        //--------total price to pay--------------
        BigDecimal total_price = new BigDecimal(0);
        for (int i = 0; i < itemsList.size(); i++) {
            total_price = total_price.add(itemsList.get(i).getTotalPrice());
        }
        System.out.println("Total: " + currencyFormat(total_price));
    }

    public static void calcAndFillInTax(Item item, String[] basic_tax_exempt_keywords) {
        boolean basic_tax_exempt = false;
        for (int i = 0; i < basic_tax_exempt_keywords.length; i++) {
            if (item.name.contains(basic_tax_exempt_keywords[i])) {
                //no basic tax
                basic_tax_exempt = true;
            }
        }
        if (!basic_tax_exempt) {
            //BigDecimal result = bd.multiply(BigDecimal.valueOf(d));

            item.basic_sales_tax = getRoundedTax(item.price.multiply(BigDecimal.valueOf(.1F)));
        }
        if (item.myStatus == ImportStatus.IMPORT) {
            item.import_tax = getRoundedTax(item.price.multiply(BigDecimal.valueOf(.05F)));
        }
//        float total_tax=item.basic_sales_tax.add(item.import_tax);
//        item.total_tax=getRoundFloat(total_tax);
        item.total_tax = getRoundedTax(item.basic_sales_tax.add(item.import_tax));
    }

    //round the total tax:
    public static BigDecimal getRoundedTax(BigDecimal tax) {
        //round BigDecimal to nearest 5 cents:http://stackoverflow.com/questions/2106615/round-bigdecimal-to-nearest-5-cents
        BigDecimal result = new BigDecimal(Math.ceil(tax.doubleValue() * 20) / 20);
        result.setScale(2, RoundingMode.HALF_UP);
        return result;
    }
}
