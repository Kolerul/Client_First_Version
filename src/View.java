import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, отвечающий за взаимодействие с клиентом
 */
public class View {
    private Command output;
    private Connector connector;
    private Reporting report;


    public View() {
        connector = new Connector("localhost", 4444);

    }


    /**
     * Метод, создающий коллекцию из данных в изначальном файле
     */
    public void createCollection() {
        System.out.println("Укажите переменную окружения (или введите 'cancel' если хотите пропустить этот шаг)");
        Scanner scanner = new Scanner(System.in);
        try {
            do {
                String line = scanner.nextLine().trim();
                if (line.equals("cancel")) {
                    System.out.println("Шаг пропущен. Коллекция не была создана");
                    break;
                } else {

                    output = new Command(0, line);

                    connector.writeData(connector.serialize(output));
                    connector.firsCommand = connector.serialize(output);

                    report = (Reporting) connector.deserialize(connector.readData());

                    System.out.println(report.getText());
                }
            } while (report.getCode() != 0);
        /*}catch (NullPointerException e){
            System.err.println("Ошибка 1");
            createCollection();*/
       /* }catch (ClassNotFoundException e){
            System.err.println("Ответ с сервера пришел в неправильном формате");
        }catch (IOException e){
            System.err.println("Ошибка 2");
            createCollection();*/
        }catch (NoSuchElementException e){
            System.err.println("Умер от кринжа");
            System.exit(1);
        } finally {

        }
    }

    /**
     * Метод, который принимает команды от пользователя и выдает ему результат их действия
     */
    public void inputCircle() throws ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        String lineArg = null;
        do {
            try {
                lineArg = scanner.nextLine().trim();

                System.out.println(argCheck(lineArg));

            } catch (NumberFormatException e) {
                System.out.println("Команда не распознана, так как нее было необходимо указать число. Пожалуйста, заново ввидите команду");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка в распознании команды, повторите запрос");
            } catch (FileNotFoundException e) {
                System.out.println("Указанный файл не найден. Пожалуйста, повторите попытку");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException e){
                System.err.println("Произошла смерть");
                System.exit(1);
            }
        } while (!lineArg.trim().equals("exit"));
        scanner.close();
    }

    public String argCheck(String source) throws NumberFormatException, ArrayIndexOutOfBoundsException, IOException, ClassNotFoundException {
        String result = "";
        //source = source.trim();
        if (source.equals("help")) {
            output = new Command(1);
        } else if (source.equals("history")) {
            output = new Command(12);
        } else if (source.equals("info")) {
            output = new Command(2);
        } else if (source.equals("show")) {
            output = new Command(3);
        } else if (source.replaceAll(" ", "").equals("add{element}")) {
            output = new Command(4, AddMethods.addName(), AddMethods.addCoordinateX(), AddMethods.addCoordinateY(),
                    AddMethods.addArea(), AddMethods.addPopulation(), AddMethods.addMeters(), AddMethods.addTimeZone(),
                    AddMethods.addCapital(), AddMethods.addGovernment(), AddMethods.addGovernerName());
        } else if (source.replaceAll(" ", "").equals("updateid{element}")) {
            output = new Command(5, AddMethods.putId(), AddMethods.addName(), AddMethods.addCoordinateX(), AddMethods.addCoordinateY(),
                    AddMethods.addArea(), AddMethods.addPopulation(), AddMethods.addMeters(), AddMethods.addTimeZone(),
                    AddMethods.addCapital(), AddMethods.addGovernment(), AddMethods.addGovernerName());
        } else if (source.equals("remove_first")) {
            output = new Command(10);
        } else if (StringManipulation.commandWithArgumentsCheck(source, "remove_by_id")) {
            output = new Command(6, Long.parseLong(StringManipulation.returnLastWords(source)));
        } else if (source.equals("clear")) {
            output = new Command(7);
        } else if (StringManipulation.commandWithArgumentsCheck(source, "filter_contains_name")) {
            output = new Command(14, StringManipulation.returnLastWords(source));
        } else if (source.equals("print_field_ascending_capital")) {
            output = new Command(15);
        } else if (source.replaceAll(" ", "").equals("add_if_max{element}")) {
            output = new Command(11, AddMethods.addName(), AddMethods.addCoordinateX(), AddMethods.addCoordinateY(),
                    AddMethods.addArea(), AddMethods.addPopulation(), AddMethods.addMeters(), AddMethods.addTimeZone(),
                    AddMethods.addCapital(), AddMethods.addGovernment(), AddMethods.addGovernerName());
        } else if (StringManipulation.commandWithArgumentsCheck(source, "execute_script")) {
            //output = new Command(8);
            result = scriptPerform(StringManipulation.returnLastWords(source));
            return result;
        } else if (source.equals("group_counting_by_id")) {
            output = new Command(13);
        } else if (source.equals("exit")) {
            result = "Работа завершается...";
            return result;
        } else {
            return "Команда не распознана, повторите запрос";
        }
        connector.writeData(connector.serialize(output));
        /*outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        outputStream.writeObject(output);
        outputStream.flush();*/

        report = (Reporting) connector.deserialize(connector.readData());
        /*inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        report = (Reporting) inputStream.readObject();*/
        result = report.getText();
        return result;
    }

    public String scriptPerform(String path) throws IOException, ClassNotFoundException {
        String result = "";
        File script = new File(path);
        Scanner scriptScanner = new Scanner(script);

        while (scriptScanner.hasNextLine()) {
            String line = scriptScanner.nextLine();
            result = result + line + "\n" +
                    argCheck(line) + "\n";
        }

        scriptScanner.close();
        return result;
    }
}
