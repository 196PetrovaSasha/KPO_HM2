import java.io.File;
import java.util.ArrayList;

// Класс для получения списка файлов для обработки
public class TakeFiles {
    // Исходная корневая папка
    File dir;
    // Список для зполнения файлами
    ArrayList<File> files = new ArrayList<>();
    //Лист для проверки
    private ArrayList<String> list = new ArrayList<String>();

    TakeFiles(File dir) {
        this.dir = dir;
    }

    /**
     * Метод для получения списка уникальных файлов
     * @param folder Исходная корневая папка
     */
    public void Take(File folder) {
        // Получаем список
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            // Если папка, идем дальше
            if (entry.isDirectory()) {
                Take(entry);
                continue;
            }
            // иначе вам попался файл, добавляем
            if (!entry.getName().equals("result.txt") & !list.contains(entry.getAbsolutePath())) {
                files.add(entry);
                list.add(entry.getAbsolutePath());
            }
        }
    }
}
