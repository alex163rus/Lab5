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
public class SuspendedAccountException extends BankCardException{

    public SuspendedAccountException() {
        super("Аккаунт является заблокированным");
    }

    public SuspendedAccountException(String msg) {
        super(msg);
    }

    public SuspendedAccountException(String msg, Exception ex) {
        super(msg, ex);
    }
}
