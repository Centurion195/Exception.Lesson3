import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            InputData input = new InputData();
            System.out.println(input);

            new Save(input);

            if (addAnother() == 'n') {
                System.out.println("Программа завершена!");
                break;
            }
        }
    }

    private static char addAnother() {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            try {
                System.out.print("\nДобавить еще запись? (y/n) ");
                command = scanner.nextLine().trim().toLowerCase();
                if (!command.matches("[yn]")) {
                    throw new RuntimeException("Ошибка! Повторите попытку");
                }
            } catch (RuntimeException ex) {
                System.err.println(ex);
                continue;
            }
            return command.charAt(0);
        }
    }
}
