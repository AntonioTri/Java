package DesignPatterns.StatePatern;

public class HasPin implements ATMState {

    ATMMachine atmMachine;

    public HasPin(ATMMachine newAtmMachine){
        atmMachine = newAtmMachine;
    }

    @Override
    public void insertCard() {
        System.out.println("You can't insert more than one card");
    }

    @Override
    public void ejectcard() {
        System.out.println("Card Ejected");
        atmMachine.setATMState(atmMachine.getNoCardState());
    }

    @Override
    public void insertPin(int pinentered) {
        System.out.println("Alredy entered a correct Pin");
    }

    @Override
    public void requestCash(int cashToWithdraw) {
        if (cashToWithdraw > atmMachine.cashInMachine) {
            System.out.println("ATM doesn't have enough cash");
            System.out.println("Card Ejected");
            atmMachine.setATMState(atmMachine.getNoCardState());
        } else {
            System.out.println(cashToWithdraw + " is provided by the machine");
            atmMachine.setCashInMachine(atmMachine.cashInMachine - cashToWithdraw);
            System.out.println("Card Ejected");
            atmMachine.setATMState(atmMachine.getNoCardState());

            if (atmMachine.cashInMachine <= 0) {
                atmMachine.setATMState(atmMachine.getNoCashState());
            }
        }
    }
    
}
