package com.twu.app.service;

import com.twu.app.exception.InfoException;
import com.twu.app.exception.LoginException;

public interface OperatorService {
    void login(String name, String pwd) throws LoginException;

    void loginOut();

    boolean needPromiseChoose(int choose);

    void printListInfo() throws InfoException;

    void addInfo(String nameKey) throws InfoException;

    void voteInfo(String nameKey, int voteCount) throws InfoException;

    void buyInfo(String nameKey, int rank, int price) throws InfoException;

    void addSpur(String nameKey) throws InfoException;

    void printMenu();
}
