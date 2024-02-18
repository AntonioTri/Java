package DesignPatterns.BuilderPattern;

public class BuilderTest {
    
    public static void main(String[] args) {
        
        RobotBuilder oldStyleRobot = new OldRobotBuilder();

        RobotEngineer robotEngineer = new RobotEngineer(oldStyleRobot);

        Robot first = robotEngineer.getRobot();

        System.out.println("Robot creato!");
        System.out.println(first.getTorso());
        System.out.println(first.getHead());
        System.out.println(first.getArms());
        System.out.println(first.getLegs());

    }

}
