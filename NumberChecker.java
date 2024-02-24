
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NumberChecker extends isNum{
    private static NumberChecker numberChecker;
    public static Map<String, Integer> scanText(String filePath) {
        Map<String, Integer> data;
        data = new HashMap<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            if (!file.exists() || !filePath.endsWith(".txt")) {
                throw new FileNotFoundException("文件类型错误或文件不存在：  " + filePath);
            }
            data.put(scanner.nextLine(), null);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (numberChecker.isNum(parts[i])) {
                        String key = String.join(" ", Arrays.copyOfRange(parts, 0, i));
                        int value = Integer.parseInt(parts[i]);
                        data.put(key, value);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
