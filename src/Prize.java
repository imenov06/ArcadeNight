import java.util.concurrent.TimeUnit;

public class Prize {
    private String name;
    private int price;
    private int quantity;

    public Prize(int price, String name, int quantity) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public void buyPrize(Card card) throws InterruptedException {
        String message;
        if (quantity == 0) {
            message = "Извините " + name + " закончился";
            printDelayMessage(message, 250);
            return;
        }
        if (!isEnoughTickets(card)) {
            message = "У вас не достаточно билетов для покупки приза " + name + "!";
            printDelayMessage(message, 250);
            return;
        }
        topDownTicketCount(card);
        message = "Вы успешно приобрели приз: " + name + "!";
        printDelayMessage(message, 200);
        quantity--;
    }

    private boolean isEnoughTickets(Card card) {
        return card.getTicketsCount() - price >= 0;
    }

    private void printDelayMessage(String message, int delay) throws InterruptedException {
        for (String word : message.split(" ")) {
            TimeUnit.MILLISECONDS.sleep(delay);
            System.out.print(word + " ");
        }
        System.out.println();
    }

    private void topDownTicketCount(Card card) {
        card.setTicketsCount(card.getTicketsCount() - price);
    }
}
