import java.io.*;
import java.util.*;

// Класс, где читаются уже добцтие файлы и текст из них выводится в результирующую строку
public class FileRd {
    // Хранит в себе главную директории
    File mainDir = null;
    // Хранит в себе список текстовых файлов
    ArrayList<File> files = new ArrayList<File>();
    // Хранит результирующую строку
    String result;
    //Нужно будет для проверки смотрели мы этот файл или нет
    private ArrayList<String> list = new ArrayList<String>();

    FileRd(File dir, ArrayList<File> st) {
        mainDir = dir;
        files = st;
        result = "";
    }

    /**
     * Метод для чтения нужного файла
     * @param folder Файл который мы проверяем
     * @param forRead Файл из исходного списка, с которым мы сравниваем на совпадение
     * @throws IOException Ловим ошибку работы с файлами
     */
    void WalkAndRead(File folder, File forRead) throws IOException {
        // Составляем список файлов
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            // Если не текстовый файл, то идем дальше по рекурсии
            if (entry.isDirectory()) {
                WalkAndRead(entry, forRead);
                continue;
            }
            // Если файл, проверяем рна совпадение с исходным и записываем результат
            if (entry.compareTo(forRead) == 0 & Objects.equals(getFileExtension(entry.getAbsolutePath()), ".txt") & !(list.contains(entry.getAbsolutePath()))) {
                list.add(entry.getAbsolutePath());
                BufferedReader br = new BufferedReader(new FileReader(entry));
                String line;
                while((line = br.readLine()) != null) {
                    result += line;
                }
                br.close();
                result += '\n';
            }
        }
    }

    /**
     * Метод для обработки всего списка файлов
     * @param files Список файлов
     * @throws IOException Ловим ошибку
     */
    public void WalkForSet(ArrayList<File> files) throws IOException {
        // Вначале сортируем список
        File[] sortArr = files.toArray(new File[files.size()]);
        for (int i = 0; i < sortArr.length - 1; i++) {
            for(int j = 0; j < sortArr.length - i - 1; j++) {
                if(sortArr[j + 1].getName().compareTo(sortArr[j].getName()) < 0) {
                    File swap = sortArr[j];
                    sortArr[j] = sortArr[j + 1];
                    sortArr[j + 1] = swap;
                }
            }
        }
        files = new ArrayList<File>(List.of(sortArr));
        // Пробегаемся по нему
        for (File fl: files) {
            WalkAndRead(mainDir, fl);
        }
    }

    /**
     * Метод для обрезания формата файла
     * @param mystr Файл
     * @return
     */
    private static String getFileExtension(String mystr) {
        int index = mystr.indexOf('.');
        return index == -1? null : mystr.substring(index);
    }
}