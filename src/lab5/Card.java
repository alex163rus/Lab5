/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import Exceptions.SuspendedAccountException;

/**
 *
 * @author Алексей
 */
public class Card implements java.io.Serializable{
    private String number;
    private String pincode;
    private int countAttemptPinCode;//количество попыток ввода пинкода
    private long timeBlockStart;
    
    private final static long TIME_BLOCK_LEN=3000;
    public final static int MAX_COUNT_ATTEMPT_PINCODE=2;
    public final static int NUMBER_LEN=16;
    public final static int PINCODE_LEN=4;
    
    public Card(String number, String pincode) {
        this.number = number;
        this.pincode = pincode;
        countAttemptPinCode=-1;
        timeBlockStart=0;
    }

    public boolean isEnter(String number, String pincode) throws SuspendedAccountException{
        if(cardIsTimeBlock()) throw new SuspendedAccountException("Превышен лимит неправильных паролей. Карта заблокированна на "+TIME_BLOCK_LEN/1000+" секунды!");
        countAttemptPinCode=countAttemptPinCode==(MAX_COUNT_ATTEMPT_PINCODE)?0:countAttemptPinCode+1;
        if(countAttemptPinCode==MAX_COUNT_ATTEMPT_PINCODE){
            timeBlockStart=System.currentTimeMillis();
        }
        return this.number.equals(number.replaceAll(" ", ""))&&this.pincode.equals(pincode);
    }
    public boolean cardIsTimeBlock(){
        return (System.currentTimeMillis()-timeBlockStart)<TIME_BLOCK_LEN;
    }
    public String getNumber() {
        return number;
    }

    public String getPincode() {
        return pincode;
    }
    
    @Override
    public String toString() {
        return "Card{" + "number=" + number + ", pincode=" + pincode + '}';
    }
    
}
