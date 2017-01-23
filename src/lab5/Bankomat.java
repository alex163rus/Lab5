/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import Exceptions.CreateDuplicateException;
import Exceptions.BankCardException;
import Exceptions.ClientNotFoundException;
import Exceptions.InsufficientFundsException;
import Exceptions.FormatDataException;
import Exceptions.PinCodeException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Алексей
 */
public class Bankomat implements java.io.Serializable, Terminal{
    private Client user;
    private ArrayList<Client> clients;//все клиенты банка
    
    public Bankomat() {
        clients=new ArrayList<>();
    }

    public boolean enterClient(String number, String pincode) throws BankCardException{
        if (!checkNumber(number)) throw new FormatDataException("Неверный формат номера карты. Правильный формат:xxxx xxxx xxxx xxxx или xxxxxxxxxxxxxxxx");
        if (!checkPinCode(pincode)) throw new FormatDataException("Неверный формат пин-кода. Правильный формат:xxxx");
        
        Client client=getClientByNumber(number);
        if(client==null) throw new ClientNotFoundException("Клиент с таким номером карты не найден.");
        if(!client.getCard().isEnter(number, pincode)) throw new PinCodeException();
        user=client;
        return true;
    }
    
    @Override
    public int checkAmountMoney() throws BankCardException{//проверить  состояние счета
        if(user==null) throw new BankCardException("Для данного действия необходимо войти в систему.");
            return user.getMoney();
    }

    @Override
    public void withdrawMoney(int amount) throws BankCardException{//снять деньги
        if(user==null) throw new BankCardException("Для данного действия необходимо войти в систему.");
        if(!checkInputAmountMoney(amount)) throw new FormatDataException("Снимать деньги можно в том случае, если сумма кратна 100");
        if((user.getMoney())-amount<0) throw new InsufficientFundsException();
        
        user.withdrawMoney(amount);
    }

    @Override
    public void putMoney(int amount) throws BankCardException {//положитьденьги
        if(user==null) throw new BankCardException("Для данного действия необходимо войти в систему.");
        if(!checkInputAmountMoney(amount)) throw new FormatDataException("Вносить деньги можно только, если сумма кратна 100");
        user.putMoney(amount);
    }

    @Override
    public void createClient(String fio) throws BankCardException{
        if(getClientByFio(fio)!=null)throw new CreateDuplicateException(); 
        if(fio.length()==0||fio.length()>Client.FIO_MAX_LEN)throw new FormatDataException("Размер ФИО не должен првышать "+Client.FIO_MAX_LEN+" символов"); 
        clients.add(new Client(fio));
    }

    @Override
    public void deleteClient() throws BankCardException{
        if(user==null) throw new BankCardException("Для данного действия необходимо войти в систему.");
        clients.remove(user);
        user=null;
    }

    @Override
    public void createCard(String fio, String number, String pincode) throws BankCardException {
        Client client=getClientByFio(fio);
        if (client == null) throw new ClientNotFoundException("Клиент с таким именем не найден.");
        
        if(client.getCard()!=null)throw new CreateDuplicateException("У клиента уже имеется карта.");

        if (!checkNumber(number)) throw new FormatDataException("Неверный формат номера карты. Правильный формат:xxxx xxxx xxxx xxxx или xxxxxxxxxxxxxxxx");

        if (!checkPinCode(pincode)) throw new FormatDataException("Неверный формат пин-кода. Правильный формат:xxxx");

        if(getClientByNumber(number)!=null) throw new CreateDuplicateException("Карта с таким номером уже существует.");

        client.setCard(new Card(number.replaceAll(" ", ""),pincode));
    }

    @Override
    public void deleteCard() throws BankCardException {
        if(user==null) throw new BankCardException("Для данного действия необходимо войти в систему.");
        user.setCard(null);
        user=null;
    }
    
    public void writeClientsInByteStream(String fileName){
        try {
            RandomAccessFile file = new RandomAccessFile(fileName+".dat", "rw");
            for (Client client : clients) {
                file.write(client.getFio().getBytes());
                file.seek(file.getFilePointer()+Client.FIO_MAX_LEN-client.getFio().getBytes().length);//фио имеет максимальную длинну. пропускаем ее.
                file.writeInt(client.getMoney());
                file.write(client.getCard().getNumber().getBytes());
                file.write(client.getCard().getPincode().getBytes());
            }
            file.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void readClientsOutByteStream(String fileName){
        try {
            RandomAccessFile file = new RandomAccessFile(fileName + ".dat", "r");
            byte[] fio = new byte[Client.FIO_MAX_LEN];
            int money;
            byte[] number = new byte[Card.NUMBER_LEN];
            byte[] password = new byte[Card.PINCODE_LEN];
            while (file.getFilePointer() < file.length()) {
                file.read(fio);
                money = file.readInt();
                file.read(number);
                file.read(password);
                createClient((new String(fio)).trim());
                createCard((new String(fio)).trim(), new String(number), new String(password));
                enterClient(new String(number), new String(password));
                putMoney(money);
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (BankCardException e) {
            System.out.println(e);
        }
    }
    public void writeClientsInSymbolStream(String fileName){
        try {
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//для чтения с консоли
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName+".txt"));
            for (Client client : clients) {
                bw.write(client.getFio() + "\t" + client.getMoney() + "\t" + client.getCard().getNumber() + "\t" + client.getCard().getPincode());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
     
    public void readClientsOutSymbolStream(String fileName){
        try{
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//для чтения с консоли
            BufferedReader br = new BufferedReader(new FileReader(fileName+".txt"));
            clients=new ArrayList<>();
            String buf;
            String []values=null;
            while((buf=br.readLine())!=null){
                values=buf.split("\t");
                createClient(values[0]);
                createCard(values[0], values[2], values[3]);
                enterClient(values[2], values[3]);
                putMoney(Integer.parseInt(values[1]));
            }
            br.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Файл не найден!");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    private boolean checkNumber(String number){
        Pattern p = Pattern.compile("^[0-9]{16}$|^([0-9]{4}) ([0-9]{4}) ([0-9]{4}) ([0-9]{4})$");
        Matcher m = p.matcher(number);
        return m.matches();
    }
    private boolean checkPinCode(String pincode){
        Pattern p = Pattern.compile("^[0-9]{4}$");
        Matcher m = p.matcher(pincode);
        return m.matches();
    }
    private boolean checkInputAmountMoney(int amount ){
        return amount%100==0;
    }
    
    private Client getClientByNumber(String number) {
        number=number.replaceAll(" ", "");
        for (Client client : clients) {
            if (client.getCard() != null && client.getCard().getNumber().equals(number)) {
                return client;
            }
        }
        return null;
    }
    private Client getClientByFio(String fio){
        for (Client client : clients) {
            if(client.getFio().equals(fio)){
                return client;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder db = new StringBuilder();
        for (Client client : clients) {
            db.append(client+" \n");
        }
        return  db.toString();
    }
    
}
