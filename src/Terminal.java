import java.util.concurrent.TimeUnit;

public class Terminal {
    private Card card;

    public Terminal(Card card) {
        this.card = card;
    }

    public void checkCardInfo() throws InterruptedException {
        String message = String.format("На вашей карте с id: %d\nБаланс: %d\nБилеты: %d", card.getId(), card.getBalance(), card.getTicketsCount());
        printDelayMessage(message, 200);
    }

    public void topUpCoinsFromMoney(int money) {
        card.setBalance(card.getBalance() + money * 2);
    }

    private void topUpCoins(Card card1, Card card2, int amount) {
        card2.setBalance(card2.getBalance() + amount);
        card1.setBalance(card1.getBalance() - amount);
    }

    private void topUpTickets(Card card1, Card card2, int amount) {
        card2.setTicketsCount(card2.getTicketsCount() + amount);
        card1.setTicketsCount(card1.getTicketsCount() - amount);
    }

    public void tradeTicketsTo(Card card2, int amount) throws InterruptedException {
        String message;
        if (!ticketsIsEnough(card, amount)) {
            message = String.format("Не удалось перевести %d ,билетов с %d счета %d счет", amount, card.getId(), card2.getId());
            printDelayMessage(message, 200);
            return;
        }
        topUpTickets(card, card2, amount);
        message = String.format("Успешный перевод %d билетов с %d счета %d счет", amount, card.getId(), card2.getId());
        printDelayMessage(message, 200);
    }

    public void tradeTicketsFrom(Card card2, int amount) throws InterruptedException {
        String message;
        if (!ticketsIsEnough(card2, amount)) {
            message = String.format("Не удалось перевести %d билетов с %d счета %d счет", amount, card2.getId(), card.getId());
            printDelayMessage(message, 200);
            return;
        }
        topUpTickets(card, card2, amount);
        message = String.format("Успешный перевод %d билетов с %d счета %d счет", amount, card.getId(), card2.getId());
        printDelayMessage(message, 200);
    }


    public void tradeCoinsTo(Card card2, int amount) throws InterruptedException {
        String message;
        if (!coinsIsEnough(card, amount)) {
            message = String.format("Не удалось перевести %d коинов с %d счета %d счет", amount, card.getId(), card2.getId());
            printDelayMessage(message, 200);
            return;
        }
        topUpCoins(card, card2, amount);
        message = String.format("Успешный перевод %d коинов с %d счета %d счет", amount, card.getId(), card2.getId());
        printDelayMessage(message, 200);
    }

    public void tradeCoinsFrom(Card card2, int amount) throws InterruptedException {
        String message;
        if (!coinsIsEnough(card2, amount)) {
            message = String.format("Не удалось перевести %d коинов с %d счета %d счет", amount, card2.getId(), card.getId());
            printDelayMessage(message, 200);
            return;
        }
        topUpCoins(card2, card, amount);
        message = String.format("Успешный перевод %d коинов с %d счета %d счет", amount, card.getId(), card2.getId());
        printDelayMessage(message, 200);
    }


    private boolean coinsIsEnough(Card card, int amount) {
        return card.getBalance() - amount > 0;
    }

    private boolean ticketsIsEnough(Card card, int amount) {
        return card.getTicketsCount() - amount > 0;
    }

    private void printDelayMessage(String message, int delay) throws InterruptedException {
        for (String word : message.split(" ")) {
            TimeUnit.MILLISECONDS.sleep(delay);
            System.out.print(word + " ");
        }
        System.out.println();
    }
}
