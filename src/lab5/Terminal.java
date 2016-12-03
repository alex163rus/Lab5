/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

/**
 *
 * @author Алексей
 */
public interface Terminal {
    public int checkAmountMoney() throws BankCardException;//проверить  состояние счета
    
    public void withdrawMoney(int amount)throws BankCardException;//снять деньги

    public void putMoney(int amount) throws BankCardException;//положитьденьги

    public void createClient(String fio) throws BankCardException;//создать клиента

    public void deleteClient()throws BankCardException;//удалить клиента

    public void createCard(String fio, String number, String pincode) throws BankCardException;//создать карту

    public void deleteCard()throws BankCardException ;//удалить карту
    
}
