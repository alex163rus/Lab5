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
public class Card {
    private String number;
    private String pincode;
    private int countAttemptPinCode;//количество попыток ввода пинкода
    private long timeBlockStart;
    private final static long timeBlockLength=100;
    public final static int maxCountAttemptPinCode=2;
    
    public Card(String number, String pincode) {
        this.number = number;
        this.pincode = pincode;
        countAttemptPinCode=-1;
        timeBlockStart=0;
    }

    public boolean isEnter(String number, String pincode) throws SuspendedAccountException{
        if(cardIsTimeBlock()) throw new SuspendedAccountException("Превышен лимит неправильных паролей. Карта заблокированна на "+timeBlockLength/1000+" секунд!");
        countAttemptPinCode=countAttemptPinCode==(maxCountAttemptPinCode)?0:countAttemptPinCode+1;
        if(countAttemptPinCode==maxCountAttemptPinCode){
            timeBlockStart=System.currentTimeMillis();
        }
        return this.number.equals(number.replaceAll(" ", ""))&&this.pincode.equals(pincode);
    }
    public boolean cardIsTimeBlock(){
        return (System.currentTimeMillis()-timeBlockStart)<timeBlockLength;
    }
    public String getNumber() {
        return number;
    }
//    public int addCountAttemptPinCode(){
//        countAttemptPinCode=countAttemptPinCode==(maxCountAttemptPinCode-1)?0:countAttemptPinCode+1;
//        return countAttemptPinCode;
//    }
    @Override
    public String toString() {
        return "Card{" + "number=" + number + ", pincode=" + pincode + '}';
    }
    
    
}
