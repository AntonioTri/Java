package DesignPatterns.StatePatern;

public class NoCard implements ATMState {

    ATMMachine atmMachine;

    public NoCard(ATMMachine newAtmMachine){
        atmMachine = newAtmMachine;
    }

    @Override
    public void insertCard() {
        System.out.println("Please Enter a PIN");
        atmMachine.setATMState(atmMachine.getYesCardState());
    }

    @Override
    public void ejectcard() {
        System.out.println("Please enter a card first!");
    }

    @Override
    public void insertPin(int pinentered) {
        System.out.println("Please enter a card first!");
    }

    @Override
    public void requestCash(int cashToWithdraw) {
        System.out.println("Please enter a card first!");
    }
    
}
