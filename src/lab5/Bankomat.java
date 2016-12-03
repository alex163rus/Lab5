/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Алексей
 */
public class Bankomat implements Terminal{
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
        if(!checkInputAmountMoney(amount)) throw new FormatDataException("Снимать деньги можно только, если сумма кратна 100");
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
        System.out.println(number);
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

//    public void wriete
    @Override
    public String toString() {
        StringBuilder db = new StringBuilder();
        for (Client client : clients) {
            db.append(client+" \n");
        }
        return  db.toString();
    }
    
}
