package com.twu.app.service;

import com.twu.app.exception.LoginException;
import com.twu.app.exception.InfoException;
import com.twu.hot.Search;
import com.twu.user.AdminUser;
import com.twu.user.User;
import com.twu.user.base.AbstractUser;

import java.util.*;
import java.util.stream.Collectors;

public class AppService implements OperatorService {
    private AbstractUser currentLoinUser;

    private Map<String, AbstractUser> userMap;

    private Scanner scanner;

    private List<Search> targets;

    public AppService(Scanner scanner, List<Search> targets) {
        this.scanner = scanner;
        this.targets = targets;
        initUserMap();
    }

    private void initUserMap() {
        userMap = new HashMap<>(4);
        userMap.put("user", new User("user","user"));
        userMap.put("admin", new AdminUser("admin","admin"));
    }

    @Override
    public void login(String name, String pwd) throws LoginException {
        if (!userMap.containsKey(name)) {
            throw new LoginException("[system] username is not exist...");
        }
        AbstractUser user = userMap.get(name);
        if(!pwd.equals(user.getPwd().trim())) {
            throw new LoginException("[system] password is err...");
        }
        System.out.printf("[system] welcome %s back...\n", name);
        currentLoinUser = user;
    }

    @Override
    public void loginOut() {
        System.out.printf("[system] %s is logout...\n", currentLoinUser.getName());
        if (currentLoinUser != null) {
            currentLoinUser = null;
        }
    }

    @Override
    public boolean needPromiseChoose(int choose) {
        assert choose > 2;
        if (choose == 3) {
            if (currentLoinUser instanceof User) {
                System.out.println("[system] please input nameKey to vote ...");
                String nameKey = scanner.next();
                System.out.println("[system] please input vote count to vote ...");
                int voteCount = Integer.parseInt(scanner.next());
                voteInfo(nameKey, voteCount);
                System.out.println("[system] vote is ok ...");
                return false;
            }
            if (currentLoinUser instanceof AdminUser) {
                System.out.println("[system] please input nameKey to add spur ...");
                String nameKey = scanner.next();
                addSpur(nameKey);
                return false;
            }
        }
        if (choose == 4) {
            if (currentLoinUser instanceof AdminUser) {
                loginOut();
                return true;
            }
            System.out.println("[system] please input nameKey to buy ...");
            String nameKey = scanner.next();
            System.out.println("[system] please input rank to buy ...");
            int rank = Integer.parseInt(scanner.next());
            System.out.println("[system] please input price to buy ...");
            int price = Integer.parseInt(scanner.next());
            buyInfo(nameKey, rank, price);
            return false;
        }
        if (choose == 5) {
            loginOut();
            return true;
        }
        return false;
    }

    @Override
    public void printListInfo() throws InfoException {
        currentLoinUser.list(targets);
    }

    @Override
    public void addInfo(String nameKey) throws InfoException {
        List<Search> collect = targets.stream().filter(item -> item.getNameKey().equalsIgnoreCase(nameKey)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            throw new InfoException("info is exist");
        }
        Search target = new Search(nameKey);
        targets.add(target);
        System.out.printf("[system] add %s info is ok...\n", nameKey);
    }

    @Override
    public void voteInfo(String nameKey, int voteCount) throws InfoException {
        ((User) currentLoinUser).vote(searchSearchInfo(nameKey), voteCount);
        targets = targets.stream().sorted(Comparator.comparing(Search::getPrice).reversed().thenComparing(Search::getVoteNum).reversed()).collect(Collectors.toList());
    }

    private Search searchSearchInfo(String nameKey) {
        List<Search> collect = targets.stream().filter(item -> item.getNameKey().equalsIgnoreCase(nameKey)).collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new InfoException("info not exist");
        }
        assert collect.size() == 1;
        return collect.get(0);
    }

    @Override
    public void buyInfo(String nameKey, int rank, int price) throws InfoException {
        if (rank < 1 || rank > targets.size()) {
            throw new InfoException("rank is err number");
        }
        ((User) currentLoinUser).buy(targets, searchSearchInfo(nameKey), rank, price);
    }

    @Override
    public void addSpur(String nameKey) throws InfoException {
        Search search = new Search(nameKey);
        ((AdminUser)currentLoinUser).addSpur(targets,search);
    }

    @Override
    public void printMenu() {
        if (currentLoinUser == null) {
            System.out.println("current is empty");
            return;
        }
        if (currentLoinUser instanceof User) {
            System.out.print("1.列出所有热搜\n2.新增热搜\n3.点赞热搜\n4.购买热搜\n5.退出\n");
            return;
        }
        System.out.print("1.列出所有热搜\n2.新增热搜\n3.新增超级热搜\n4.退出\n");
        return;
    }
}
