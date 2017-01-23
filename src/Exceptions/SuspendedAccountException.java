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
public class SuspendedAccountException extends BankCardException implements java.io.Serializable{

    public SuspendedAccountException() {
        super("Аккаунт заблокирован");
    }

    public SuspendedAccountException(String msg) {
        super(msg);
    }

    public SuspendedAccountException(String msg, Exception ex) {
        super(msg, ex);
    }
}
