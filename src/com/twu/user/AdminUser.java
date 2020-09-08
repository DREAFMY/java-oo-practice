package com.twu.user;

import com.twu.hot.Search;
import com.twu.options.AdminOption;
import com.twu.user.base.AbstractUser;

import java.util.List;

public class AdminUser extends AbstractUser implements AdminOption {
    public AdminUser(String name, String pwd) {
        super(name, pwd, ADMIN);
    }

    @Override
    public void addSpur(List<Search> targets, Search target) {
        target.setBase(2);
        targets.add(target);
    }

    @Override
    public String toString() {
        return "[admin] " + super.toString();
    }
}
