/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Алексей
 */
public class InsufficientFundsException extends BankCardException implements java.io.Serializable{
    
    public InsufficientFundsException() {
        super("Недостаточно средств на карте");
    }
    public InsufficientFundsException(String msg) {
        super(msg);
    }
    public InsufficientFundsException(String msg, Exception ex) {
        super(msg,ex);
    }
}
