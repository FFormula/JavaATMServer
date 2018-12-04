import server.*;

import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    public static void main(String[] args) {
        IStorage storage1 = new StorageFile("data");
        IStorage storage2 = new StorageBase("data/atm.db");
        Socketor socketor = new Socketor(8000);
        Context context = new Context();
        System.out.println("Laukiame klientu prisijungima");
        while (true) {
            socketor.accept();
            System.out.println("Klientas prisijunge");
            List<String> request = socketor.getRequest();
            String response = context.execute(storage2, request);
            socketor.sendResponse(response);
            try { Thread.sleep(500); } catch (InterruptedException e) { }
            socketor.closeClient();

            System.out.println(request.get(0));
            System.out.println(response);
        }
    }
}
