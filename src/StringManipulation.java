/**
 * Класс, отвечающий за обработку строк
 */
public class StringManipulation {
    /**
     * Массив строк, который много где применяется и чаще всего методы возвращают именно его
     */
    static String[] parts;

    /**
     * Метод, делящий csv строку на подстроки
     * @param line - исходная строка, над которой проводятся манипуляции
     * @return возвращает массив строк, полученных путем деления исходной строки
     */
    public static String[] parseToParts(String line) throws NullPointerException{
        String[] line1, line2;
        line1 = line.split(",");
        line2 = line.split(";");
        if (line1.length == 10) {
            parts = line.split(",");
        } else if (line2.length == 10) {
            parts = line.split(";");
        }else{
            throw new NullPointerException();
        }
        return parts;
    }

    /**
     * Метод, переводящий текстовое представление является ли город столицей в двоичное
     * @param line - исходная строка, над которой проводятся манипуляции
     * @return возвращает статус столицы в формате boolean
     */
    public static int capital(String line){
        int capital = -1;
        if (line.equals("столица") | line.equals("true")) {
            capital = 1;
        }else if (line.equals("не столица") | line.equals("false")) {
            capital = 0;
        }else if (line.replaceAll(" ", "").equals("")){
            capital = -1;
        }
        return capital;
    }

    /**
     * Метод, переводящий текстовое представление государственного строя в один из заготовленных enum типов
     * @param line - исходная строка, над которой проводятся манипуляции
     * @return возвращает тип гос. строя типа Government
     */
    public static Government government(String line){
        Government government = null;
        if (line.equals("анархия") | line.equals("ANARCHY")) {
            government = Government.ANARCHY;
        } else if (line.equals("марионеточное государство") | line.equals("PUPPET_STATE")) {
            government = Government.PUPPET_STATE;
        } else if (line.equals("монархия") | line.equals("MONARCHY")) {
            government = Government.MONARCHY;
        } else if (line.equals("технократия") | line.equals("TECHNOCRACY")) {
            government = Government.TECHNOCRACY;
        } else if (line.equals("тимократия") | line.equals("TIMOCRACY")) {
            government = Government.TIMOCRACY;
        }
        return government;
    }

    /**
     * Метод, сравниваюший комманду с аргументом с образцом
     * @param line - исходная строка, над которой проводятся манипуляции
     * @param example - строка-образец, на соответствие которой нужно проверит полученную строку
     * @return возвращает true или false в зависимости от результатов соответствия line и example
     * @throws ArrayIndexOutOfBoundsException
     */
    public static boolean commandWithArgumentsCheck(String line, String example) throws ArrayIndexOutOfBoundsException{
        int checkCounter = 0;
        parts = line.trim().split(" ");
        if (parts[0].equals(example)){
            checkCounter++;
        }
        if (checkCounter == 1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Метод, для обработки комманды с аргументом
     * @param lineArg - - исходная строка, над которой проводятся манипуляции
     * @return возвращает последнее слово в строке (по задумке, аргумент команды)
     * @deprecated по сути не особо нужен, так как есть метод returnLastWords, но видимо я про него забыл
     */
    public static String returnLastWord(String lineArg){
        String[] parts = lineArg.split(" ");
        String line = parts[parts.length - 1];
        return line;
    }
    /**
     * Метод, для обработки комманды с аргументом, состоящим не из одного слова
     * @param lineArg - - исходная строка, над которой проводятся манипуляции
     * @return возвращает последние слова в строке (по задумке, аргумент команды)
     */
    public static String returnLastWords(String lineArg){
        String[] parts = lineArg.split(" ", 2);
        String line = parts[1];
        return line;
    }

    /**
     * Метод, проверяющий строки изначального файла на соответствие нужному для заполнени коллекции формату
     * @param pieces - массив строк, кусочки строки из изначального фала
     * @return возвращает двоичный результат соответствия формату
     */
    public static int emptyCheck(String... pieces){
        int result = 1;
        if (pieces[1].replaceAll("", "").equals("") |  pieces[2].replaceAll("", "").equals("") |
                pieces[3].replaceAll("", "").equals("") | pieces[4].replaceAll("", "").equals("") |
                pieces[5].replaceAll("", "").equals("") | pieces[6].replaceAll("", "").equals("")){
            result = 0;
        }
        return result;
    }

}