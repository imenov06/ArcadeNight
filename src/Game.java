import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game {
    private String name;
    private int price;

    public Game(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void startGame(Card card) throws InterruptedException {
        String message;
        if (!isEnoughCoins(card)) {
            message = "На вашем счету не достаточно средств!";
            printDelayMessage(message, 250);
            return;
        }
        withdrawalCoins(card);
        message = String.format("Игра %s успешно запущена! С вашего баланса списано %d коинов. Остаток баланса: %d", this.name, this.price, card.getBalance());
        printDelayMessage(message, 150);

        System.out.print("[");
        for (int i = 0; i < 20; i++) {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.print(".");
        }
        System.out.print("]");
        System.out.println();

        int ticketsWin = getRandomTickets();
        topUpCardTickets(card, ticketsWin);
        message = String.format("Вы выиграли %d билет%s!!!", getRandomTickets(), ticketsWin > 1 ? "а" : "");
        printDelayMessage(message, 150);
    }

    private boolean isEnoughCoins(Card card) {
        return card.getBalance() - price >= 0;
    }

    private void withdrawalCoins(Card card) {
        card.setBalance(card.getBalance() - price);
    }

    private int getRandomTickets() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

    private void topUpCardTickets(Card card, int amount) {
        card.setTicketsCount(card.getTicketsCount() + amount);
    }

    private void printDelayMessage(String message, int delay) throws InterruptedException {
        for (String word : message.split(" ")) {
            TimeUnit.MILLISECONDS.sleep(delay);
            System.out.print(word + " ");
        }
        System.out.println();
    }
}
