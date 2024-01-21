package DesignPatterns.StatePatern;

public class HasCard implements ATMState{

    ATMMachine atmMachine;

    public HasCard(ATMMachine newAtmMachine){
        atmMachine = newAtmMachine;
    }

    @Override
    public void insertCard() {
        System.out.println("You can't enter more than one card");
    }

    @Override
    public void ejectcard() {
        System.out.println("Card ejected");
        atmMachine.setATMState(atmMachine.getNoCardState());
    }

    @Override
    public void insertPin(int pinentered) {
        if (pinentered == 1234) {
            System.out.println("Correct Pin inserted!");
            atmMachine.correctPinEntered = true;
            atmMachine.setATMState(atmMachine.getHasPin());
        } else {
            System.out.println("Wrong Pin u itiod!");
            atmMachine.correctPinEntered = false;
            System.out.println("Card Ejected");
            atmMachine.setATMState(atmMachine.getNoCardState());
        }
    }

    @Override
    public void requestCash(int cashToWithdraw) {
        System.out.println("Enter Pin First");
    }
    
}
