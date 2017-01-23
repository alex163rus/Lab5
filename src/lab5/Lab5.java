/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import Exceptions.BankCardException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Алексей
 */
public class Lab5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BankCardException, FileNotFoundException, IOException, ClassNotFoundException {
        Bankomat bankomat = new Bankomat();
        bankomat.createClient("Ivanov Ivan Ivanovich");
        bankomat.createClient("Petrov Petr Petrobich");
        bankomat.createClient("Sidorov Alex Ivanovich");
        bankomat.createCard("Ivanov Ivan Ivanovich", "1234 1234 1234 1234", "1234");
        bankomat.createCard("Petrov Petr Petrobich", "7777777777777777", "7777");
        bankomat.createCard("Sidorov Alex Ivanovich", "6666666666666666", "6666");

//        for (int i = 0; i < 1500; i++) {//тест на блокировку карты при трех неправильных паролях. Блокируется на 3 секунды
//            try {
//                System.out.println(bankomat.enterClient("1234 1234 1234 1234", "1235"));
//            } catch (BankCardException ex) {
//                System.out.println(ex);
//            }
//        }
        bankomat.enterClient("7777777777777777", "7777");
        System.out.println(bankomat.checkAmountMoney());
        bankomat.putMoney(10300);
        System.out.println(bankomat.checkAmountMoney());
        bankomat.withdrawMoney(200);
        System.out.println(bankomat.checkAmountMoney());
//        bankomat.withdrawMoney(20000);//проверка исключения "Недостаточно средств"

        System.out.println(bankomat);//вывод всех клиентов банкомата
        bankomat.deleteClient();//удаление текущего клиента
        System.out.println(bankomat);//вывод всех клиентов банкомата
//          
//        
        bankomat.writeClientsInSymbolStream("bankomatSymbol");//запись данных банкомата в символьный файл
//        bankomat.readClientsOutSymbolStream("bankomatSymbol");//чтение данных банкомата из символьного файла
//        bankomat.writeClientsInByteStream("bankomatByte");//запись данных банкомата в бинарный файл
//        bankomat.readClientsOutByteStream("bankomatByte");//чтение данных банкомата из бинарного файла

//        System.out.println(bankomat);//вывод всех клиентов банкомата
//        
        //запись сериализованного объекта:
        FileOutputStream fos = new FileOutputStream("bankomat.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(bankomat);
        oos.flush();
        oos.close();

        //чтение сериализованного объекта:
//        FileInputStream fis = new FileInputStream("bankomat.out");
//        ObjectInputStream oin = new ObjectInputStream(fis);
//        bankomat = (Bankomat) oin.readObject();
//        oin.close();
//        System.out.println(bankomat);//вывод всех клиентов банкомата
    }
}
