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
}
