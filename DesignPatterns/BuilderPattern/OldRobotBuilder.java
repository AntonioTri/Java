package DesignPatterns.BuilderPattern;

public class OldRobotBuilder implements RobotBuilder {

    private Robot robot;

    public OldRobotBuilder(){
        this.robot = new Robot();
    }

    @Override
    public void buildRobotHead() {
        robot.setRobotHead("Tin Head");
    }

    @Override
    public void buildRobotArms() {
        robot.setRobotArms("BlowTorch Arms");
    }

    @Override
    public void buildRobotTorso() {
        robot.setRobotTorso("Wood Torso");
    }

    @Override
    public void buildRobotLegs() {
        robot.setRobotLegs("Copper legs");
    }

    @Override
    public Robot getRobot(){
        return this.robot;
    }
    
}
