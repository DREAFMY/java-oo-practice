package com.twu.options;

import com.twu.hot.Search;

import java.util.List;

public interface UserOption {
    void buy(List<Search> targets, Search target, int rank, int price);
    void vote(Search Target, int voteCount);
}
