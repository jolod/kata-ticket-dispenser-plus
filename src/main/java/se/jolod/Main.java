package se.jolod;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TicketDispenser dispenser = TicketDispenser.getInstance();
        while (true) {
            Thread.sleep(1000);
            if (System.currentTimeMillis() % 5 == 0) {
                dispenser.pressButton();
            } else {
                dispenser.refreshWaitingTime();
            }
        }
    }
}
