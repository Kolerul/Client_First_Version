import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Connector {
    SocketChannel socketChannel;
    InetSocketAddress serverAddress;
    Selector selector;
    byte[] lastCommand;
    byte[] firsCommand;

    public Connector(String ADDR, int PORT){
        this.serverAddress = new InetSocketAddress(ADDR, PORT);
        Connect();

        /*try {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }*/

    }

    public void Connect(){
        int t = 0;
        while (true) {
            try {
                socketChannel = SocketChannel.open(serverAddress);
                System.out.println("Соединение установлено");
                break;
            } catch (IOException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                if (t == 10) {
                    System.err.println("Сервер недоступен");
                }
                if (t == 0) {
                    System.out.println("Подключение...");
                }
                t++;
            }
        }
        try {
            ///selector = Selector.open();

            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] serialize(Object obj) {
        //System.out.println("Внутри сереализатора");
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object deserialize (byte[] rawData) {
        //System.out.println("Внутри десерелизатора");
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(rawData);
            ObjectInputStream objectInputStream = new ObjectInputStream(bis);
            return objectInputStream.readObject();
        } catch (StreamCorruptedException e){
            e.printStackTrace();
            System.err.println("Соединение разорвано");
            System.exit(0);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public synchronized byte[] readData() {
        /*try {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }*/
        byte[] a = new byte[10000];
        ByteBuffer buffer = ByteBuffer.wrap(a);
        buffer.clear();
        try {
            while (socketChannel.read(buffer) == 0 || socketChannel.read(buffer) == -1);

        } catch (IOException e) {
            System.out.println("Нет соединения с сервером, чтобы получить данные");
            int t = 0;
            while (true) {
                try {
                    socketChannel = SocketChannel.open(serverAddress);
                    writeData(firsCommand);
                    writeData(lastCommand);
                    socketChannel.read(buffer);
                    /*connector.socketChannel.configureBlocking(false);
                    try {
                        connector.socketChannel.register(connector.selector, SelectionKey.OP_WRITE);
                    } catch (ClosedChannelException ex) {
                        ex.printStackTrace();
                    }*/
                    return a;
                } catch (IOException ex) {
                    if (t == 10) {
                        System.out.println("Сервер помер");
                        System.exit(1);
                    }

                    if (t == 0) {
                        System.out.println("Переподключение...");
                    }
                    t++;
                }
            }
        }
        buffer.flip();
        return a;
    }

    public synchronized void writeData(byte[] bytes) {

        //System.out.println("Внутри писателя");
        this.lastCommand = bytes;
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        try {
            socketChannel.write(buffer);
        } catch (IOException e) {
            System.out.println("Нет соединения с сервером, чтобы отправить данные");
            int t = 0;
            while (true) {
                try {
                    Connect();
                    //socketChannel = SocketChannel.open(serverAddress);
                    socketChannel.write(ByteBuffer.wrap(firsCommand));
                    readData();
                    socketChannel.write(buffer);

                    /*connector.socketChannel.configureBlocking(false);
                    try {
                        connector.socketChannel.register(connector.selector, SelectionKey.OP_READ);
                    } catch (ClosedChannelException ex) {
                        ex.printStackTrace();
                    }*/
                    break;
                } catch (IOException ex) {
                    if (t == 10) {
                        System.out.println("Сервер помер");
                        System.exit(1);
                    }

                    if (t == 0) {
                        System.out.println("Переподключение...");
                    }
                    t++;
                }
            }
        }
        buffer.flip();
        buffer.clear();
    }

}

