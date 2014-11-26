/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salestax;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Item {

    public BigDecimal price=new BigDecimal(0);
    public String name;

    public enum ImportStatus {

        IMPORT, NON_IMPORT
    }
    ImportStatus myStatus;

    public BigDecimal basic_sales_tax=new BigDecimal(0);
    public BigDecimal import_tax=new BigDecimal(0);
    public BigDecimal total_tax=new BigDecimal(0);

    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice=price.add(total_tax);
        //totalPrice.setScale(2, RoundingMode.HALF_UP);
       // String totalPriceString = totalPrice.setScale(2).toPlainString();
        return totalPrice;
    }
    public Item(String name, float price, boolean imported) {
        this.name = name;
        this.price = new BigDecimal(price);
        if (imported) {
            myStatus = ImportStatus.IMPORT;
        } else {
            myStatus = ImportStatus.NON_IMPORT;
        }
    }
    
    
}
