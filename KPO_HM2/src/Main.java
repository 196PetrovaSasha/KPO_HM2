import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Строка для имени файла
        String file;
        // Файл для запоминания корневой папки
        File directory;
        // Для чтения информации с консоли
        Scanner in = new Scanner(System.in);
        // Идем беспонечны циклом, пока не получим корректный путь к папке
        while (true) {
            System.out.println("Введите путь к директории");
            file = in.nextLine();
            directory = new File(file);
            // Ура! такая папка есть
            if (directory.exists()) {
                // Куда мы запишем ответ
                file+="/result.txt";
                TakeFiles take = new TakeFiles(directory);
                take.Take(take.dir);
                FileRd res = new FileRd(directory, take.files);
                res.WalkForSet(res.files);
                File output = new File(file);
                FileWriter wr = new FileWriter(output);
                wr.write(res.result);
                wr.close();
                System.out.println("Готово!");
                break;
            } else {
                // Не ура( такой папки нет
                System.out.println("Некорректный путь, нужно ввести корректный");
            }
            in.close();
        }
    }
}
