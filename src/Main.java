import java.util.ArrayList;
import java.util.Scanner;

class Seat {
    int seatNumber;
    boolean isBooked;
    Seat prev;
    Seat next;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false;
        this.prev = null;
        this.next = null;
    }
}

class Theater extends Booking {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> theaters = new ArrayList<>();
    String[] times = {"10.30AM", "12.00PM", "3.00PM", "5.00PM"};

    public Theater() {
        theaters.add("SCOPE Cinema");
        theaters.add("PVR Cinema");
        theaters.add("Climax Cinema");
    }

    public void backwards() {
        System.out.println("1. Add Theaters");
        System.out.println("2. View Theaters");
        int EXNb = sc.nextInt();
        sc.nextLine(); // consume the newline
        if (EXNb == 1) {
            addTheater();
        } else if (EXNb == 2) {
            view();
        }
    }

    public void addTheater() {
        System.out.println("Enter Theater Name");
        String th = sc.nextLine();
        theaters.add(th);
        backwards();
    }

    public void view() {
        for (int t = 0; t < theaters.size(); t++) {
            System.out.println(t + ". " + theaters.get(t));
        }
        MovieTicket mk = new MovieTicket();
        mk.menus();
    }
}

class MovieTicket extends Theater {
    Scanner sc = new Scanner(System.in);
    String username;
    int password;
    ArrayList<String> movies = new ArrayList<>();

    public MovieTicket() {
        movies.add("Batman | PVR Cinemas | 10.00AM");
        movies.add("Avengers | Scope Cinemas | 12.45PM");
        movies.add("Madam Web | Climax Cinema | 1.00PM");
        movies.add("Hulk | PVR Cinemas | 10.30AM");
    }

    public void menus() {
        System.out.println("\n1. Movie Management");
        System.out.println("2. Theater Management");
        System.out.println("3. Booking Management");
        System.out.println("\nEnter Key Number");
        int num = sc.nextInt();
        sc.nextLine(); // consume the newline
        if (num == 1) {
            movieManagement();
        } else if (num == 2) {
            Theater the = new Theater();
            the.backwards();
        } else if (num == 3) {
            Booking book = new Booking();
            book.bookingMenu();
        } else {
            System.out.println("Please enter a valid key number");
        }
    }

    public void login() {
        System.out.println("If you are a customer please press 1");
        System.out.println("If you are an admin please press 2");
        int choice = sc.nextInt();

        if (choice == 2) {
            System.out.println("Hi Admin");
            menus();
        } else if (choice == 1) {
            User ur = new User();
            ur.movieList();
        } else {
            System.out.println("Please enter a valid key number");
        }

    }

    public void movieManagement() {
        System.out.println("1. Add Movies");
        System.out.println("2. Delete Movies");
        System.out.println("3. View Movies List");
        int dis = sc.nextInt();
        sc.nextLine(); // consume the newline
        if (dis == 1) {
            addMovie();
        } else if (dis == 2) {
            deleteMovie();
        } else if (dis == 3) {
            viewMovies();
        } else {
            System.out.println("Enter valid number");
        }
    }

    public void addMovie() {
        while (true) {
            System.out.println("Enter Movie Name or type 'end' to stop adding movies:");
            String mName = sc.nextLine();
            if (mName.equals("end")) break;

            System.out.println("Available Theaters:");
            for (int p = 0; p < theaters.size(); p++) {
                System.out.println(p + ". " + theaters.get(p));
            }
            System.out.println("Select the Theater");
            int y = sc.nextInt();
            sc.nextLine(); // consume the newline

            if (y >= theaters.size() || y < 0) {
                System.out.println("Invalid theater selection.");
                continue;
            }
            String tHall = theaters.get(y);

            for (int ti = 0; ti < times.length; ti++) {
                System.out.println(ti + ". " + times[ti]);
            }
            System.out.println("Pick the Time");
            String timo = sc.nextLine();
            movies.add(mName + " | " + tHall + " | " + timo);
        }
        movieManagement();
    }

    public void deleteMovie() {
        System.out.println("Enter Movie Listing Number");
        int mdel = sc.nextInt();
        sc.nextLine(); // consume the newline
        if (mdel >= 0 && mdel < movies.size()) {
            movies.remove(mdel);
            System.out.println("Delete Successful");
        } else {
            System.out.println("Invalid movie number.");
        }
        movieManagement();
    }

    public void viewMovies() {
        for (int x = 0; x < movies.size(); x++) {
            System.out.println(x + ". " + movies.get(x));
        }
        System.out.println("\nPress 0 to Main menu");
        int exit = sc.nextInt();
        sc.nextLine(); // consume the newline
        if (exit == 0) {
            menus();
        } else {
            login();
        }
    }
}

class Booking {
    Scanner sc = new Scanner(System.in);
    ArrayList<Seat> seats = new ArrayList<>();

    public Booking() {
        for (int i = 1; i <= 50; i++) {
            seats.add(new Seat(i));
        }
    }

    public void bookingMenu() {
        System.out.println("1. Book a Seat");
        System.out.println("2. View Booked Seats");
        System.out.println("3. Exit");
        int choice = sc.nextInt();
        sc.nextLine(); // consume the newline
        if (choice == 1) {
            bookSeat();
        } else if (choice == 2) {
            viewBookedSeats();
        } else if (choice == 3) {
            System.exit(0);
        } else {
            System.out.println("Please enter a valid number.");
            bookingMenu();
        }
    }

    public void bookSeat() {
        System.out.println("Available seats:");
        for (Seat seat : seats) {
            if (!seat.isBooked) {
                System.out.print(seat.seatNumber + " ");
            }
        }
        System.out.println("\nSelect your seat number:");
        int seatNum = sc.nextInt();
        sc.nextLine(); // consume the newline
        if(seatNum < 51) {
            Seat seat = seats.get(seatNum - 1);
            if (seat.isBooked) {
                System.out.println("Seat " + seatNum + " is already booked.");
            } else {
                seat.isBooked = true;
                System.out.println("Seat " + seatNum + " booked successfully.");
            }
        }
        else {
            System.out.println("Invalid seat number.");
        }
        bookingMenu();
    }

    public void viewBookedSeats() {
        System.out.println("Booked seats:");
        for (Seat seat : seats) {
            if (seat.isBooked) {
                System.out.print(seat.seatNumber + " ");
            }
        }
        System.out.println();
        bookingMenu();
    }
}

class Map {
    public void dijkstra(int graph[][], int src) {
        int dist[] = new int[4];
        Boolean b[] = new Boolean[4];
        for (int i = 0; i < 4; i++) {
            dist[i] = Integer.MAX_VALUE;
            b[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < 4; count++) {
            int u = minDistance(dist, b);
            b[u] = true;
            for (int x = 0; x < 4; x++) {
                if (!b[x] && graph[u][x] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][x] < dist[x]) {
                    dist[x] = dist[u] + graph[u][x];
                }
            }
        }
        printGraph(dist, 4);
    }

    public int minDistance(int dist[], Boolean b[]) {
        int min = Integer.MAX_VALUE, index = -1;
        for (int x = 0; x < 4; x++) {
            if (!b[x] && dist[x] <= min) {
                min = dist[x];
                index = x;
            }
        }
        return index;
    }

    public int[][] locations() {
        return new int[][] { { 0, 3, 5, 4 }, { 2, 3, 0, 5 }, { 0, 4, 5, 7 }, { 0, 3, 5, 2 } };
    }

    public void printGraph(int dist[], int n) {
        System.out.println("Distance from source to locations:");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "-----" + dist[i]);
        }
    }
}

class User extends MovieTicket {
    Seat head;
    Seat tail;
    ArrayList<Seat> seats = new ArrayList<>();

    public User() {
        for (int i = 1; i <= 50; i++) {
            addSeat(i);
        }
    }

    private void addSeat(int i) {
        Seat newSeat = new Seat(i);
        if (head == null) {
            head = newSeat;
            tail = newSeat;
        } else {
            tail.next = newSeat;
            newSeat.prev = tail;
            tail = newSeat;
        }
        seats.add(newSeat);
    }

    public void movieList() {
        for (int x = 0; x < movies.size(); x++) {
            System.out.println(x + ". " + movies.get(x));
        }

        System.out.println("Enter Movie Number ");
        int p = sc.nextInt();
        sc.nextLine(); // consume the newline

        System.out.println("For how many Persons?");
        int persons = sc.nextInt();
        sc.nextLine(); // consume the newline

        for (int i = 0; i < persons; i++) {
            bookSeat();
        }
        System.out.println("Check Booking Seats Yes (Y) or No (N)");
        String check = sc.nextLine();
        if (check.equals("Y")) {
            viewBookedSeats();
        }
        else if (check.equals("N")) {
            movieList();
        }
        else {
            System.out.println("Invalid Input Enter 'Y' or 'N'");
        }

    }

    private void booking(int seatNum) {
        Seat seat = seats.get(seatNum - 1);
        if (seat.isBooked) {
            System.out.println("Seat " + seatNum + " is already booked.");
        } else {
            seat.isBooked = true;
            System.out.println("Seat " + seatNum + " booked successfully.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MovieTicket mt = new MovieTicket();
        mt.login();
    }
}