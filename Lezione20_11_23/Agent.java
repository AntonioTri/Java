package Lezione20_11_23;

import java.util.Queue;

public class Agent {

    private Queue<Order> coda;

    void placeOrder(Order order) {

        // aggiunger l'elemento alla coda
        // richiamare order.execute() sul primo elemento estratto

        coda.add(order);

        for (Order temp : coda) {
            temp.execute();
        }

    }

}
