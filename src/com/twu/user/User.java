package com.twu.user;

import com.twu.app.exception.InfoException;
import com.twu.hot.Search;
import com.twu.options.UserOption;
import com.twu.user.base.AbstractUser;

import java.util.List;

public class User extends AbstractUser implements UserOption {
    private int voteCountTotal = 10;

    public User(String name, String pwd) { super(name, pwd, USER); }

    private void voteDecrement(int voteCount) { voteCountTotal -= voteCount; }

    @Override
    public void buy(List<Search> targets, Search target, int rank, int price) {
        if (targets.get(rank-1).getPrice() >= price) {
            throw new InfoException("price is too low,buy failed");
        }
        target.setPrice(price);
        target.setPosition(rank);
        targets.remove(target);
        targets.set(rank-1, target);
    }

    @Override
    public void vote(Search target, int voteCount) {
        if (voteCount > voteCountTotal || voteCountTotal <= 0) {
            throw new InfoException("no vote numbers or vote numbers more than your vote numbers");
        }
        voteDecrement(voteCount);
        target.setVoteNum(voteCount * target.getBase());
    }

    @Override
    public String toString() {
        return "[user] " + super.toString();
    }
 }
