package DesignPatterns.StatePatern;

public interface ATMState {
    
    void insertCard();
    void ejectcard();
    void insertPin(int pinentered);
    void requestCash(int cashToWithdraw);

}
