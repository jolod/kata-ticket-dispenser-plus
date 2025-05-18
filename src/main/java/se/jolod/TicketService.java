package se.jolod;

class TicketService {
    private static int number = System.currentTimeMillis() % 2 == 0 ? 0 : 1;

    public int getWaitingTimeInMinutes() {
        return (int) (System.currentTimeMillis() % 25L);
    }

    public int getTicketNumber() {
        number++;
        if (System.currentTimeMillis() % 2 == 0) {
            number++;
        }
        return number;
    }
}
