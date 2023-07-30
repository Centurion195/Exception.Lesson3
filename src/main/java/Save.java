import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Save {
    private final InputData input;
    private final String output;

    public Save(InputData input) {
        this.input = input;
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(input.getSurname()).append(">");
        sb.append("<").append(input.getName()).append(">");
        sb.append("<").append(input.getPatronymic()).append(">");
        sb.append("<").append(input.getDateOfBirth()).append(">");
        sb.append("<").append(input.getPhone()).append(">");
        sb.append("<").append(input.getSex()).append(">").append("\n");
        output = sb.toString();
        createOrAppendInFile();
    }

    private void createOrAppendInFile() {
        Path file = Path.of(input.getSurname() + ".txt");
        if (Files.notExists(file)) {
            try {
                Files.createFile(file);
                System.out.println("Файл успешно создан!");
                saveFile(file, false);
            } catch (IOException e) {
                System.err.println("Не удалось создать файл: " + e.getMessage());
            }
        } else {
            System.out.println("Файл существует!");
            if (isDuplicate(file)) {
                System.out.println("Обнаружен дубликат записи! Запись не произведена!");
            } else {
                saveFile(file, true);
            }
        }
    }

    private boolean saveFile(Path file, boolean append) {
        try (FileWriter writer = new FileWriter(file.toFile(), append)) {
            writer.write(output);
            System.out.println("Запись в файл успешно произведена!");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isDuplicate(Path file) {
        boolean result = false;
        try (InputStream inputStream = Files.newInputStream(file)) {
            String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            String[] textSplit = text.split("\n");
            for (int i = 0; i < textSplit.length; i++) {
                String[] parts = textSplit[i].replace("<", "")
                        .replace(">", " ")
                        .replace("\n", "")
                        .trim()
                        .split(" ");

                InputData data = new InputData(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5].charAt(0));
                if (data.equals(input)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

