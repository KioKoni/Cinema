package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        if (!scanner.hasNextInt()){
            System.out.println("wrong input!!!");
            return;
        }
        int rowsSize = scanner.nextInt();
        if (!scanner.hasNextInt()){
            System.out.println("wrong input!!!");
            return;
        }
        System.out.println("Enter the number of seats in each row:");
        int seatsSize = scanner.nextInt();

        int totalSeat = rowsSize * seatsSize;
        int[] currentIncome = {0};
        int[] purchased = {0};
        double[] percentage = {0.0};
        int totalIncome = countTotal(totalSeat, rowsSize, seatsSize);

        String[][] seats = totalSeats(rowsSize, seatsSize);

        for (int i = 0; i == 0; ) {
            System.out.println();

            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");

            int choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1 -> showTheSeats(seats, rowsSize, seatsSize);
                case 2 -> buyATicket(totalSeat, seats, rowsSize, seatsSize, currentIncome, purchased);
                case 3 -> statistics(purchased, percentage, currentIncome, totalIncome, totalSeat);
                case 0 -> i = 1;
            }
        }
    }


    public static void showTheSeats(String[][] seats, int rowsSize, int seatsSize) {
        System.out.println("Cinema:");
        for (int i = 0; i < rowsSize + 1; i++) {
            for (int j = 0; j < seatsSize + 1; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String[][] totalSeats(int rowsSize, int seatsSize) {
        String[][] seats = new String[rowsSize + 1][seatsSize + 1];

        for (int i = 0; i < rowsSize + 1; i++) {
            for (int j = 0; j < seatsSize + 1; j++) {
                if (i == 0 && j != 0) {
                    seats[i][j] = String.valueOf(j);
                } else if (i != 0 && j != 0) {
                    seats[i][j] = "S";
                } else if (i == 0) {
                    seats[i][j] = " ";
                } else {
                    seats[i][j] = String.valueOf(i);
                }
            }
        }
        return seats;
    }

    public static void buyATicket(int totalSeat, String[][] seats, int rowsSize, int seatsSize, int[] currentIncome, int[] purchased) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i == 0; ) {
            System.out.println("\nEnter a row number:");
            int numberOfRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();

            if (numberOfRow > rowsSize || seatNumber > seatsSize || numberOfRow <= 0 || seatNumber <= 0) {
                System.out.println("\nWrong input!\n");
            } else if (seats[numberOfRow][seatNumber].equals("B")) {
                System.out.println("\nThat ticket has already been purchased!\n");
            } else {

                int priceOfSeat = 10;

                if ((totalSeat >= 60) && (numberOfRow > rowsSize / 2)) {
                    priceOfSeat = 8;
                }
                System.out.println("\nTicket price: $" + priceOfSeat);
                currentIncome[0] += priceOfSeat;

                seats[numberOfRow][seatNumber] = "B";
                purchased[0] += 1;
                i = 1;


            }
        }

    }

    public static void statistics(int[] purchased, double[] percentage, int[] currentIncome, int totalIncome, int totalSeat) {
        System.out.println();
        percentage[0] = purchased[0] / (double) totalSeat * 100;
        System.out.printf("""
                Number of purchased tickets: %d
                Percentage: %.2f%s
                Current income: $%d
                Total income: $%d
                %n""", purchased[0], percentage[0], "%", currentIncome[0], totalIncome);

    }

    public static int countTotal(int totalSeat, int rowsSize, int seatsSize) {
        int total;
        if (totalSeat >= 60) {
            if (rowsSize % 2 == 0) {
                total = (rowsSize / 2) * seatsSize * 10 + (rowsSize / 2) * seatsSize * 8;
            } else {
                total = (rowsSize / 2) * seatsSize * 10 + (rowsSize / 2 + 1) * seatsSize * 8;
            }
        } else {
            total = rowsSize * seatsSize * 10;
        }
        return total;
    }
}