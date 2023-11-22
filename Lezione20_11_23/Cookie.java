package Lezione20_11_23;

public class Cookie implements Cloneable {

    public Object clone() {

        try {
            Cookie copy = (Cookie) super.clone();
            System.out.println("Biscotto creato!");
            return copy;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;

        }

    }

    public void SayHello() {
        System.out.println("Ciao, sono un nuovo Biscotto clonato :D");
    }

}
