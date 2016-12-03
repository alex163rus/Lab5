/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Алексей
 */
public class Lab5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BankCardException {
//        throw new InsufficientFundsException();
        Bankomat bankomat = new Bankomat();
        
        bankomat.createClient("fio1");
//        bankomat.createClient("fio2");
       
        bankomat.createCard("fio1", "1234 4545 4545 4545", "1234");
        
        for (int i = 0; i < 4000; i++) {
            try {
                System.out.println(bankomat.enterClient("1234 4545 4545 4545", "1334"));
//                System.out.println(bankomat.enterClient("1234 4545 4545 4545", "1434"));
//                System.out.println(bankomat.enterClient("1234 4545 4545 4545", "1534"));
//                System.out.println(bankomat.enterClient("1234 4545 4545 4545", "1534"));
            } catch (BankCardException ex) {
                System.out.println(ex);
            }

        }
      
//        System.out.println(bankomat.checkAmountMoney());
//        bankomat.putMoney(1020);
//        System.out.println(bankomat.checkAmountMoney());
//        bankomat.
//        bankomat.createCard("fio2", "1234 4565 4545 4545", "1234");
//        bankomat.createCard("fio2", "1234 4545 4545 4545", "1234");
// System.out.println(bankomat);
    }
}
