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
public class Client implements java.io.Serializable{
    private String fio;
    private int money;
    private Card card;
    public static final int FIO_MAX_LEN=40;
    
    public Client(String fio) {
        this.fio = fio;
        this.money = 0;
        card=null;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getMoney() {
        return money;
    }

    public void putMoney(int money) {
        this.money += money;
    }
    public void withdrawMoney(int money) {
        this.money-= money;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Client{" + "fio=" + fio + ", money=" + money + ", card=" + card + '}';
    }
    
    
}
