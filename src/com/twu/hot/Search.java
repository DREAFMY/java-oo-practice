package com.twu.hot;

public class Search {
    private String nameKey;
    private int voteNum;
    private int price;
    private int base;

    public Search(String nameKey) {
        this.nameKey = nameKey;
        this.voteNum = 0;
        this.price = 0;
        this.base = 1;
    }

    public int getBase() { return base; }
    public void setBase(int base) { this.base = base; }

    public String getNameKey() { return nameKey; }
    public void setNameKey(String nameKey) { this.nameKey = nameKey; }

    public int getVoteNum() { return voteNum; }
    public void setVoteNum(int voteNum) { this.voteNum = voteNum; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    @Override
    public String toString() {
        return "话题：" + nameKey + ", 票数：" + voteNum + ", 价格：" + price;
    }
}
