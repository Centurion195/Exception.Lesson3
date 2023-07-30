import lombok.Getter;

import java.util.Objects;
import java.util.Scanner;

public class InputData {
    private final Scanner scanner;
    @Getter
    private String surname;
    @Getter
    private String name;
    @Getter
    private String patronymic;
    @Getter
    private String dateOfBirth;
    @Getter
    private String phone;
    @Getter
    private char sex;

    public InputData() {
        scanner = new Scanner(System.in);
        readData();
    }

    public InputData(String surname, String name, String patronymic, String dateOfBirth, String phone, char sex) {
        scanner = new Scanner(System.in);
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.sex = sex;
    }

    private void readData() {
        String[] data;
        while (true) {
            try {
                System.out.println("Введите данные через пробел:\n" +
                        "[ФИО, дата рождения (dd.mm.yyyy), номер телефона, пол (м/ж)]");
                data = scanner.nextLine().trim().split("\\s+");
                int dataCount = 6;
                if (data.length != dataCount) {
                    throw new RuntimeException(String.format("Неверное количество введенных данных! " +
                            "Введено: %s, требуется: %s", data.length, dataCount));
                }
                if (!data[0].matches("[a-zA-Zа-яА-Я]+")) {
                    throw new RuntimeException(String.format("Неверное задана фамилия! " +
                            "Введено: %s", data[0]));
                }
                if (!data[1].matches("[a-zA-Zа-яА-Я]+")) {
                    throw new RuntimeException(String.format("Неверное задано имя! " +
                            "Введено: %s", data[1]));
                }
                if (!data[2].matches("[a-zA-Zа-яА-Я]+")) {
                    throw new RuntimeException(String.format("Неверное задано отчество! " +
                            "Введено: %s", data[2]));
                }
                if (!data[3].matches("\\d{2}.\\d{2}.\\d{4}")) {
                    throw new RuntimeException(String.format("Неверное задана дата рождения! " +
                            "Введено: %s, требуется: dd.mm.yyyy", data[3]));
                }
                if (!data[4].matches("[0123456789]{10}")) {
                    throw new RuntimeException(String.format("Неверное задан номер телефона! " +
                            "Введено: %s, требуется 10 цифр", data[4]));
                }
                if (!data[5].matches("[мжМЖ]")) {
                    throw new RuntimeException(String.format("Неверное задан пол! " +
                            "Введено: %s, требуется: м/ж", data[5]));
                }
                break;
            } catch (RuntimeException ex) {
                System.err.println(ex);
            }
        }
        surname = data[0];
        name = data[1];
        patronymic = data[2];
        dateOfBirth = data[3];
        phone = data[4];
        sex = data[5].toUpperCase().charAt(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputData inputData = (InputData) o;
        return sex == inputData.sex && Objects.equals(surname, inputData.surname) &&
                Objects.equals(name, inputData.name) && Objects.equals(patronymic, inputData.patronymic) &&
                Objects.equals(dateOfBirth, inputData.dateOfBirth) && Objects.equals(phone, inputData.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic, dateOfBirth, phone, sex);
    }

    @Override
    public String toString() {
        return "InputData{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phone=" + phone +
                ", sex=" + sex +
                '}';
    }
}
