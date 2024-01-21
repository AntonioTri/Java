package DesignPatterns.StatePatern;

public class NoCash implements ATMState {
    ATMMachine atmMachine;

    public NoCash(ATMMachine newAtmMachine){
        atmMachine = newAtmMachine;
    }

    @Override
    public void insertCard() {
        System.out.println("We don't have money!");
    }

    @Override
    public void ejectcard() {
        System.out.println("We don't have money!");
    }

    @Override
    public void insertPin(int pinentered) {
        System.out.println("We don't have money!");
    }

    @Override
    public void requestCash(int cashToWithdraw) {
        System.out.println("We don't have money!");
    }
}
