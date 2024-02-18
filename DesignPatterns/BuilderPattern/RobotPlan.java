package DesignPatterns.BuilderPattern;

public interface RobotPlan {
    public void setRobotHead(String head);
    public void setRobotArms(String arms);
    public void setRobotTorso(String torso);
    public void setRobotLegs(String legs);
}
