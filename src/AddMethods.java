import java.util.Scanner;

/**
 * Класс, содержащий методы для обработки данных,
 * вводимых при создании новых объектов для коллекции.
 */
public class AddMethods {
    /**
     * Метод, принимающий значение ID и обрабатывающий возможные исключения.
     * @return возвращает полученное значение ID в формате int.
     */
    public static long putId(){
        Scanner scanner = new Scanner(System.in);
        long id = 0;
        int check;
        do{
            try {
                check = 0;
                System.out.print("Укажите искомый id ");
                id = Long.parseLong(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Данные представлены в неправильном формате. Пожалуйста, повторите запрос ");
                check = -1;
            }
        }while (check != 0);
        return id;
    }

    /**
     * Метод, принимающий значение имени объекта и обрабатывающий возможные исключения.
     * @return возвращает имя объекта в формате String.
     */
    public static String addName(){
        Scanner scanner = new Scanner(System.in);
        String name = null;
        int check = 0;
        while (check != 1){
            System.out.print("Введите название города ");
            name = scanner.nextLine();
            check = 0;
            if (name.replaceAll(" ", "").equals("")) {
                System.out.println("Названием города не может быть пустая строка, пожалуйста, введите название еще раз");
            }else{
                check++;
            }
        }
        return name;
    }
    /**
     * Метод, принимающий значение координаты x объекта и обрабатывающий возможные исключения.
     * В совокупности с методом addCoordinateY создает объект класса Coordinates.
     * @return возвращает координату x в формате float.
     */
    public static Float addCoordinateX(){
        Scanner scanner = new Scanner(System.in);
        float x = 0f;
        int check = 0;
        while (check != 1) {
            try {
                System.out.print("Введите координату x (от 0 до 539) ");
                x = Float.parseFloat(scanner.nextLine());
                check = 0;
                if (x > 539 | x < 0) {
                    System.out.println("Указана неверная координата. Пожалуйста, повторите попытку");
                } else {
                    check++;
                }
            }catch (NumberFormatException e) {
                System.out.println("Если что, тут надо указывать число (да и еще в указанном дипозоне). Пожалуйста, повторите попытку");
            }
        }
        return x;
    }

    /**
     * Метод, принимающий значение координаты y объекта и обрабатывающий возможные исключения.
     * В совокупности с методом addCoordinateX создает объект класса Coordinates.
     * @return возвращает координату y в формате float.
     */
    public static Float addCoordinateY(){
        Scanner scanner = new Scanner(System.in);
        float y = 0f;
        int check = 0;

        while (check != 1) {
            try {
                System.out.print("Введите координату y (от 0 до 589) ");
                y = Float.parseFloat(scanner.nextLine());
                check = 0;
                if (y > 589 | y < 0) {
                    System.out.println("Указана неверная координата. Пожалуйста, повторите попытку");
                } else {
                    check++;
                }
            }catch (NumberFormatException e){
                System.out.println("Если что, тут надо указывать число (да и еще в указанном дипозоне). Пожалуйста, повторите попытку");

            }
        }
        return y;
    }
    /**
     * Метод, принимающий значение площади объекта и обрабатывающий возможные исключения.
     * @return возвращает площадь в формате int.
     */
    public static Integer addArea(){
        Scanner scanner = new Scanner(System.in);
        int check = 0;
        int area = 0;
        while (check != 1) {
            try{
                System.out.print("Введите площадь города (должна быть больше 0) ");
                area = Integer.parseInt(scanner.nextLine());
                if (area <= 0){
                    System.out.println("Указана неверная площадь. Пожалуйста, повторите попытку ");
                }else{
                    check++;
                }
            }catch (NumberFormatException e){
                System.out.println("Здесь необходимо указать целое число больше 0. Пожалуйста, повторите попытку");
            }
        }
        return area;
    }

    /**
     * Метод, принимающий значение населения объекта и обрабатывающий возможные исключения.
     * @return возвращает количество населения в формате int.
     */
    public static int addPopulation(){
        Scanner scanner = new Scanner(System.in);
        int check = 0;
        int population = 0;

        while (check != 1) {
            try{
                System.out.print("Введите количество населения города (должна быть больше 0) ");
                population = Integer.parseInt(scanner.nextLine());
                if (population <= 0){
                    System.out.println("Указана неверное количество населения. Пожалуйста, повторите попытку ");
                }else{
                    check++;
                }
            }catch (NumberFormatException e){
                System.out.println("Здесь необходимо указать целое число больше 0. Пожалуйста, повторите попытку");
            }
        }
        return population;
    }

    /**
     * Метод, принимающий значение высоты над уровнем моря объекта и обрабатывающий возможные исключения.
     * @return возвращает высоту над уровнем моря в формате float.
     */
    public static float addMeters(){
        Scanner scanner = new Scanner(System.in);
        float meters = 0;
        int check;
        do {
            try {
                check = 1;
                System.out.print("Введите высоту над уровнем моря ");
                meters = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Здесь необходимо указать число. Пожалуйста, повторите попытку");
                check = -1;
            }

        }while (check != 1);
        return meters;
    }

    /**
     * Метод, показывающий статус столицы объекта и обрабатывающий возможные исключения.
     * @return возвращает статус столицы в формате boolean.
     */
    public static int addCapital(){
        Scanner scanner = new Scanner(System.in);
        int check = 0;
        int bolCheck = 0;
        while (check != 1){
            System.out.print("Является ли город столицей (напишите 'столица' или 'не столица') ");
            String capital = scanner.nextLine();
            if (capital.equals("столица") | capital.equals("true")){
                bolCheck = 1;
                check++;
            }else if (capital.equals("не столица") | capital.equals("false")){
                bolCheck = 0;
                check++;
            }else if (capital.replaceAll(" ", "").equals("")){
                bolCheck = -1;
                check++;
            }else{
                System.out.println("Не удалось распознать ключевое слово. Пожалуйста, повторите попытку");
            }
        }
        return bolCheck;
    }


    /**
     * Метод, принимающий тип государственного строя объекта из списка enum Government и обрабатывающий возможные исключения.
     * @return возвращает тип государственного строя из списка в формате Government.
     */
    public static Government addGovernment(){
        Scanner scanner = new Scanner(System.in);
        int check = 0;
        Government gov = null;
        while (check != 1){
            System.out.print("Введите государственный строй (варианты: 'MONARCHY', 'PUPPET_STATE','TECHNOCRACY' ,'ANARCHY', 'TIMOCRACY') ");
            String goverment = scanner.nextLine();
            if (goverment.replaceAll(" ", "").equals("MONARCHY")){
                check++;
                gov = Government.MONARCHY;
            }else if (goverment.replaceAll(" ", "").equals("PUPPET_STATE")){
                check++;
                gov = Government.PUPPET_STATE;
            }else if (goverment.replaceAll(" ", "").equals("TECHNOCRACY")){
                check++;
                gov = Government.TECHNOCRACY;
            }else if (goverment.replaceAll(" ", "").equals("ANARCHY")){
                check++;
                gov = Government.ANARCHY;
            }else if (goverment.replaceAll(" ", "").equals("TIMOCRACY")){
                check++;
                gov = Government.TIMOCRACY;
            }else if (goverment.replaceAll(" ", "").equals("")){
                check++;
                gov = null;
            }else{
                System.out.println("Не удалось распознать ключевое слово. Пожалуйста, повторите попытку");
            }
        }
        return gov;
    }

    /**
     * Метод, принимающий имя правителя для создания объекта типа Human.
     * @return возвращает имя человека-правителя типа String.
     */
    public static String addGovernerName(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя правителя ");
        String name = scanner.nextLine();
        if (name.replaceAll(" ", "").equals("")){
            name = null;
        }
        return name;
    }

    /**
     * Метод, принимающий число для обозначения часового пояса.
     * @return возвращает часовой пояс типа int.
     */
    public static int addTimeZone(){
        Scanner scanner = new Scanner(System.in);
        int timezone = 0;
        int check = 0;
        while (check != 1) {
            try {
                System.out.print("Введите часовой пояс (число от -13 до 15) ");
                timezone = Integer.parseInt(scanner.nextLine());
                check = 0;
                if (timezone > 15 | timezone < -13) {
                    System.out.println("Указан неверный часовой пояс. Пожалуйста, повторите попытку");
                } else {
                    check++;
                }
            }catch (NumberFormatException e){
                System.out.println("Если что, тут надо указывать целое число (да и еще в указанном дипозоне). Пожалуйста, повторите попытку");

            }
        }
        return timezone;
    }
}
