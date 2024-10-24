import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String schema = """
                 ______________________________________________________
                |                    Игровой зал                       |
                |______________________________________________________|
                |                                                      |
                |     __________     ___________     __________        |
                |    |  Гонки   |   | Стрелялки |   |  Аркады  |       |
                |    |   100    |   |    150    |   |    140   |       |
                |    |__________|   |___________|   |__________|       |
                |                                                      |
                |     __________     ____________    ___________       |
                |    |  Спорт   |   | Симуляторы |   |  Пинбол  |      |
                |    |   100    |   |     200    |   |    50    |      |
                |    |__________|   |____________|   |__________|      |
                |                                                      |
                |------------------------------------------------------|
                |                  Терминалы для пополнения            |
                |______________________________________________________|
                |     ____________     _____________                   |
                |    | Терминал 1 |   |  Терминал 2 |                  |
                |    |            |   |             |                  |
                |    |____________|   |_____________|                  |
                |______________________________________________________|
                
                """;

        Game[] games = new Game[]{
                new Game("Гонки", 100),
                new Game("Стрелялки", 150),
                new Game("Аркады", 140),
                new Game("Спорт", 100),
                new Game("Симуляторы", 200),
                new Game("Пинбол", 50),
        };
        Card[] cards = new Card[]{
                new Card(),
                new Card()
        };

        Terminal[] terminals = new Terminal[]{
                new Terminal(cards[0]),
                new Terminal(cards[0]),
        };

        Prize[] prizes = new Prize[]{
                new Prize(3, "Наушники", 4),
                new Prize(4, "Смарт часы", 6),
                new Prize(10, "Телефон", 1),
        };

        String context = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Сделайте первоначальный депозит: ");
        terminals[0].topUpCoinsFromMoney(scanner.nextInt());
        System.out.println(schema);
        while (!context.equals("Стоп")) {
            context = scanner.nextLine();
            switch (context) {
                case "Играть" -> {
                    System.out.println("Вы зашли в игровой зал.\nВыберите игровой автомат: ");
                    while (!context.equals("выйти")) {
                        context = scanner.nextLine();
                        switch (context) {
                            case "Гонки" -> games[0].startGame(cards[0]);
                            case "Стрелялки" -> games[1].startGame(cards[0]);
                            case "Аркады" -> games[2].startGame(cards[0]);
                            case "Спорт" -> games[3].startGame(cards[0]);
                            case "Симуляторы" -> games[4].startGame(cards[0]);
                            case "Пинбол" -> games[5].startGame(cards[0]);
                            case "выйти" -> System.out.println("Вы вышли из игрового зала");
                            default -> System.out.println("Такой игры нет =(");
                        }
                    }
                }
                case "Терминал" -> {
                    while (!context.equals("выйти")) {
                        System.out.print("Введите ваше действие для терминала: ");
                        context = scanner.nextLine();
                        switch (context) {
                            case "инфо" -> terminals[0].checkCardInfo();
                            case "перевод коинов" -> {
                                System.out.print("Введите id карты получателя: ");
                                context = scanner.nextLine();
                                System.out.println("Введите сумму коинов для перевода: ");
                                int amount = scanner.nextInt();
                                terminals[0].tradeCoinsTo(cards[1], amount);
                            }
                            case "перевод билетов" -> {
                                System.out.print("Введите id карты получателя: ");
                                context = scanner.nextLine();
                                System.out.println("Введите сумму коинов для перевода: ");
                                int amount = scanner.nextInt();
                                terminals[0].tradeTicketsTo(cards[1], amount);
                            }
                            case "прием коинов" -> {
                                System.out.print("Введите id карты отправителя: ");
                                context = scanner.nextLine();
                                System.out.println("Введите сумму коинов для перевода: ");
                                int amount = scanner.nextInt();
                                terminals[0].tradeCoinsFrom(cards[0], amount);
                            }
                            case "прием билетов" -> {
                                System.out.print("Введите id карты отправителя: ");
                                context = scanner.nextLine();
                                System.out.println("Введите сумму коинов для перевода: ");
                                int amount = scanner.nextInt();
                                terminals[0].tradeTicketsFrom(cards[0], amount);
                            }
                            case "выйти" -> System.out.println("Вы вышли из зоны терминала");

                            default -> System.out.println("В терминале нет такой команды.");
                        }
                    }
                }
                case "Призы" -> {
                    while (!context.equals("выйти")) {
                        System.out.print("Какой приз вы хотите купить: ");
                        context = scanner.nextLine();
                        switch (context) {
                            case "Смарт часы" -> {
                                prizes[1].buyPrize(cards[0]);
                            }
                            case "Телефон" -> {
                                prizes[2].buyPrize(cards[0]);
                            }
                            case "Наушники" -> {
                                prizes[0].buyPrize(cards[0]);
                            }
                        }
                    }
                }
                case "стоп" -> System.out.println("Спасибо, что посетили нашу компанию.");
                case "" -> {
                    System.out.println("""
                            Комманды:
                            Играть -> Гонки, Стрелялки, Аркады, Спорт, Симуляторы, Пинбол, выйти
                            Терминал -> инфо, перевод коинов, перевод билетов, прием коинов, прием билетов, выйти
                            Призы -> Смарт часы, Телефон, Наушники, выйти
                            Стоп -> Остановка программы
                            """);
                }

                default -> System.out.println("Такой команды нет");
            }
            if (!context.equals("стоп"))
                System.out.print("Введите ваше действие: ");
        }
    }
}