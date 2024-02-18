package DesignPatterns.BuilderPattern;

public class Robot implements RobotPlan {

    private String head;
    private String arms;
    private String torso;
    private String legs;

    @Override
    public void setRobotHead(String head) {
        this.head = head;
    }

    @Override
    public void setRobotArms(String arms) {
        this.arms = arms;
    }

    @Override
    public void setRobotTorso(String torso) {
        this.torso = torso;
    }

    @Override
    public void setRobotLegs(String legs) {
        this.legs = legs;
    }


    public String getHead() {
        return head;
    }

    public String getArms() {
        return arms;
    }

    public String getTorso() {
        return torso;
    }

    public String getLegs() {
        return legs;
    }
    
}
