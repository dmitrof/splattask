package com.tmitri.splattask.client;

import com.tmitri.splattask.util.DatabaseHelper;
import com.tmitri.splattask.webservice.AccountService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Дмитрий on 15.10.2016.
 */
//Клиент JAX-WS
public class AccountClient {
    private static ExecutorService rExecutor;
    private static ExecutorService wExecutor;


    public static void main(String[] args) throws MalformedURLException {
        //перезаполняем БД, если нужно. Если не нужно, эти 2 строчки можно закомментировать.
        DatabaseHelper dh = new DatabaseHelper();
        dh.fillDatabase(20);
        //соединяемся с сервисом
        URL url = new URL("http://localhost:8000/wss/account_service?wsdl");
        QName qname = new QName("http://webservice.splattask.tmitri.com/", "AccountServiceImplService");
        Service service = Service.create(url, qname);
        AccountService accountService = service.getPort(AccountService.class);

        //Считываем параметры rCount и wCount и idList из консоли
        Scanner in = new Scanner(System.in);
        System.out.println("Введите rCount"); Integer rCount = in.nextInt();
        System.out.println("Введите wCount"); Integer wCount = in.nextInt();
        System.out.println("Начало диапазона idList"); Integer id1 = in.nextInt();
        System.out.println("Начало диапазона idList"); Integer id2 = in.nextInt();
        Set idList = new HashSet();
        for (int i = id1; i <= id2; i++) {
            idList.add(i);
        }
        //Тестируем веб-сервис
        rExecutor = Executors.newFixedThreadPool(rCount);
        wExecutor = Executors.newFixedThreadPool(wCount);
        makeRandomServiceCalls(rCount, wCount, idList, accountService );

    }


    //пример многопоточных вызовов методов службы
    //Создаем по rCount и wCount параллельных вызовов для getAmount и addAmount соответственно и выводим в консоль
    //ответы от веб-сервиса
    private static void makeRandomServiceCalls(Integer rCount, Integer wCount, Set idSet, AccountService accountService) {
        rExecutor = Executors.newFixedThreadPool(rCount);
        wExecutor = Executors.newFixedThreadPool(wCount);
        final AccountService _accountService = accountService;
        for (int i = 0; i < rCount; i++) {
            final int threadNum =  i;
            final Integer id = (Integer)getRandomElementFromSet(idSet);
            rExecutor.submit(new Runnable() {
                public void run() {
                    System.out.println("Reading thread #" + threadNum + ": id = " + id + " value = " +  _accountService.getAmount(id));
                }
            });
            wExecutor.submit(new Runnable() {
                public void run() {
                    System.out.println("Writing thread #" + threadNum + ": id = " + id + ", добавляем 100 к имеющемуся значению");
                    _accountService.addAmount(id, 100L);
                }
            });

            //System.out.println(accountService.getAmount(id));
        }
    }
    //
    private static Object getRandomElementFromSet(Set set) {
        Random rnd = new Random();
        int i = rnd.nextInt(set.size());
        return set.toArray()[i];
    }
}
