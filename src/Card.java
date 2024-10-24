    import java.util.Random;

public class Card {
    private int id = createRandomID();
    private int balance = 0;
    private int ticketsCount = 0;

    private static int createRandomID(){
        Random random = new Random();
        int id = random.nextInt(100_000)+ 100_000;
        return id;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

}
