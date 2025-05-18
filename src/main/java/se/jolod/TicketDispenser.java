package se.jolod;

public final class TicketDispenser {
    private static TicketDispenser instance;

    private final TicketService service;
    private final TicketPrinter printer;
    private final DotMatrixDisplayDriver display;

    private TicketDispenser(final TicketService service, final TicketPrinter printer, final DotMatrixDisplayDriver display) {
        this.service = service;
        this.printer = printer;
        this.display = display;
    }

    public static TicketDispenser getInstance() {
        if (instance == null) {
            instance = new TicketDispenser(new TicketService(), new TicketPrinter(), new DotMatrixDisplayDriver(16, 20));
        }
        return instance;
    }

    public void refreshWaitingTime() {
        int minutes = service.getWaitingTimeInMinutes();
        minutes = Math.max(1, Math.min(minutes, 99));
        if (minutes > 10) {
            final boolean[][] left = DIGITS[minutes / 10];
            final boolean[][] right = DIGITS[minutes % 10];
            final int rows = left.length;
            final int colsLeft = left[0].length;
            final int colsRight = right[0].length;
            final boolean[][] result = new boolean[rows][colsLeft + colsRight];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(left[i], 0, result[i], 0, colsLeft);
                System.arraycopy(right[i], 0, result[i], colsLeft, colsRight);
            }
            display.update(result);
        } else {
            display.update(DIGITS[minutes]);
        }
    }

    public void pressButton() {
        int number = service.getTicketNumber();
        if (number < 0) {
            printer.printTicket("??");
        } else if (number < 10) {
            printer.printTicket("0" + number);
        } else {
            printer.printTicket(String.valueOf(number));
        }
    }

    private static final boolean[][][] DIGITS = {
        // Digit 0
        {
            {false,true,true,true,true,false,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {false,true,true,true,true,false,false,false},
        },
        // Digit 1
        {
            {false,false,false,true,true,false,false,false},
            {false,false,true,true,true,false,false,false},
            {false,true,true,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {true,true,true,true,true,true,false,false},
        },
        // Digit 2
        {
            {false,true,true,true,true,false,false,false},
            {true,true,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,true,true,false,false,false,false},
            {false,true,true,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,true,true,true,true,false,false},
        },
        // Digit 3
        {
            {false,true,true,true,true,false,false,false},
            {true,true,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,true,true,true,false,false,false},
            {false,false,true,true,true,false,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {false,true,true,true,true,false,false,false},
        },
        // Digit 4
        {
            {false,false,false,false,true,true,false,false},
            {false,false,false,true,true,true,false,false},
            {false,false,false,true,true,true,false,false},
            {false,false,true,true,true,true,false,false},
            {false,false,true,false,true,true,false,false},
            {false,true,true,false,true,true,false,false},
            {false,true,true,false,true,true,false,false},
            {false,true,true,false,true,true,false,false},
            {false,true,true,false,true,true,false,false},
            {true,true,true,true,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,true,true,true,true,false},
        },
        // Digit 5
        {
            {true,true,true,true,true,true,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,true,true,true,false,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {false,true,true,true,true,false,false,false},
        },
        // Digit 6
        {
            {false,true,true,true,true,false,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false,false},
            {true,true,true,true,true,false,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {false,true,true,true,true,false,false,false},
        },
        // Digit 7
        {
            {true,true,true,true,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,false,true,true,false,false,false},
            {false,false,true,true,false,false,false,false},
            {false,false,true,true,false,false,false,false},
            {false,false,true,true,false,false,false,false},
            {false,false,true,true,false,false,false,false},
            {false,false,true,true,false,false,false,false},
            {false,false,true,true,false,false,false,false},
            {false,false,true,true,false,false,false,false},
            {false,false,true,true,false,false,false,false},
            {false,false,true,true,false,false,false,false},
        },
        // Digit 8
        {
            {false,true,true,true,true,false,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {false,true,true,true,true,false,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {false,true,true,true,true,false,false,false},
        },
        // Digit 9
        {
            {false,true,true,true,true,false,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {false,true,true,true,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {false,false,false,false,true,true,false,false},
            {true,true,false,false,true,true,false,false},
            {false,true,true,true,true,false,false,false},
        }
    };
}
