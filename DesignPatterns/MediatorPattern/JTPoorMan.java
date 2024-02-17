package DesignPatterns.MediatorPattern;

public class JTPoorMan extends Colleague{

	public JTPoorMan(Mediator newMediator) {
		super(newMediator);
		
		System.out.println("JT Poorman signed up with the stockexchange\n");
		
	}
	
}