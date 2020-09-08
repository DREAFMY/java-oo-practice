package com.twu.app;

import com.twu.app.exception.*;
import com.twu.app.service.*;
import com.twu.hot.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DemoApp {
    private List<Search> targets;
    private OperatorService service;
    private Scanner scanner;
    private InfoExceptionHandle exceptionHandler;

    public DemoApp() {
        init();
        run();
    }

    private void init() {
        scanner = new Scanner(System.in);
        targets = new ArrayList<>();
        service = new AppService(scanner, targets);
        exceptionHandler = new InfoExceptionHandle();
    }

    private void run() {
        System.out.println("[system] welcome ...");
        while (true) {
            if (login()) {
                while (true) {
                    service.printMenu();
                    System.out.println("[system] choose your item ...");
                    int choose = Integer.parseInt(scanner.next());
                    if (loginOut(choose, service, targets)) {
                        break;
                    }
                }
            }
        }
    }

    private boolean loginOut(int choose, OperatorService service, List<Search> targets) {
        boolean out = false;
        try {
            switch (choose) {
                case 1:
                    service.printListInfo();
                    break;
                case 2:
                    System.out.println("[system] please input name of info ...");
                    String nameKey = scanner.next();
                    service.addInfo(nameKey);
                    break;
                default:
                    // other options
                    out = service.needPromiseChoose(choose);
            }
        } catch (InfoException e) {
            exceptionHandler.handle(e);
        }
        return out;
    }

    private boolean login() {
        System.out.println("[system] please input username...");
        String username = scanner.next();
        System.out.println("[system] please input password...");
        String pwd = scanner.next();
        try {
            service.login(username, pwd);
        } catch (LoginException e) {
            System.out.println("[system] username or password is err...");
            return false;
        }
        return true;
    }
}
