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
public class FormatDataException extends BankCardException{
    
    public FormatDataException() {
        super("Неправильный формат данных");
    }
    public FormatDataException(String msg) {
        super(msg);
    }
    public FormatDataException(String msg, Exception ex) {
        super(msg,ex);
    }
}
