package DesignPatterns.BuilderPattern;

public interface RobotBuilder {
    public void buildRobotHead();
    public void buildRobotArms();
    public void buildRobotTorso();
    public void buildRobotLegs();
    public Robot getRobot();

}
