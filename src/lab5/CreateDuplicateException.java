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
public class CreateDuplicateException extends BankCardException{

    public CreateDuplicateException() {
        super("Дубликат карты или клиента недопустим");
    }

    public CreateDuplicateException(String msg) {
        super(msg);
    }

    public CreateDuplicateException(String msg, Exception ex) {
        super(msg, ex);
    }
}
