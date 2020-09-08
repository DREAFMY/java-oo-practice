package com.twu.options;

import com.twu.hot.Search;
import com.twu.options.base.BaseOption;

import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class AbstractBaseOptionImpl implements BaseOption {
    @Override
    public void list(List<Search> targets) {
        if (targets.isEmpty()) {
            System.out.println("[system] infos is empty...");
            return;
        }
        targets.forEach(System.out::println);
    }

    public void sort(List<Search> targets, Search target) {
        Collections.sort(targets, new Comparator<Search>() {
            @Override
            public int compare(Search t1, Search t2) {
                int dif = t1.getVoteNum() - t2.getVoteNum();
                if (dif > 0) { return 1; }
                else if ( dif < 0) { return -1; }
                else { return 0; }
            }
        });
        //todo using positon number to sort list
    }
}
