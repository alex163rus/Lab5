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
public class ClientNotFoundException extends BankCardException implements java.io.Serializable{

    public ClientNotFoundException(String msg) {
        super(msg);
    }

    public ClientNotFoundException(String msg, Exception ex) {
        super(msg, ex);
    }
}
