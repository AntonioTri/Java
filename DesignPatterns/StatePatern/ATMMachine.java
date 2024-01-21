package DesignPatterns.StatePatern;

public class ATMMachine {
    
    ATMState hasCard;
    ATMState noCard;
    ATMState hasCorrectPin;
    ATMState atmOutOfMooney;

    ATMState atmState;

    int cashInMachine = 2000;
    boolean correctPinEntered = false;

    public ATMMachine(){

        hasCard = new HasCard(this);
        noCard = new NoCard(this);
        hasCorrectPin = new HasPin(this);
        atmOutOfMooney = new NoCash(this);

        atmState = noCard;

        if (cashInMachine < 0) {
            atmState = atmOutOfMooney;
        }
    }

    void setATMState(ATMState newAtmState){
        atmState = newAtmState;
    }

    public void setCashInMachine(int newCachInMachine){
        cashInMachine =newCachInMachine;
    }

    public void insertCard(){
        atmState.insertCard();
    }

    public void ejectCard(){
        atmState.ejectcard();
    }

    public void requestCash(int cashToWithdraw){
        atmState.requestCash(cashToWithdraw);
    }

    public void insertPin(int pinEntered){
        atmState.insertPin(pinEntered);
    }

    public ATMState getYesCardState(){return hasCard;};
    public ATMState getNoCardState(){return noCard;};
    public ATMState getHasPin(){return hasCorrectPin;};
    public ATMState getNoCashState(){return atmOutOfMooney;};

}
