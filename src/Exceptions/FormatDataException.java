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
public class FormatDataException extends BankCardException implements java.io.Serializable{
    
    public FormatDataException() {
        super("Неверный формат данных");
    }
    public FormatDataException(String msg) {
        super(msg);
    }
    public FormatDataException(String msg, Exception ex) {
        super(msg,ex);
    }
}
