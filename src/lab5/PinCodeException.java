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
public class PinCodeException extends BankCardException{
    
    public PinCodeException() {
        super("Неправильный пин-код");
    }
    public PinCodeException(String msg) {
        super(msg);
    }
    public PinCodeException(String msg, Exception ex) {
        super(msg,ex);
    }
}
