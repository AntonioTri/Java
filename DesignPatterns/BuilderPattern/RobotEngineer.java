package DesignPatterns.BuilderPattern;

public class RobotEngineer {
    
    private RobotBuilder robotBuilder;

    public RobotEngineer(RobotBuilder rb){
        this.robotBuilder = rb;
    }

    public Robot getRobot(){
        makeRobot();
        return robotBuilder.getRobot();
    }

    public void makeRobot(){
        this.robotBuilder.buildRobotHead();
        this.robotBuilder.buildRobotArms();
        this.robotBuilder.buildRobotTorso();
        this.robotBuilder.buildRobotLegs();
    }

}
