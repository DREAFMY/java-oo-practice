package com.twu.hot;

public class Search {
    private String nameKey;
    private int voteNum;
    private int price;
    private int base;
    private int position;

    public Search(String nameKey) {
        this.nameKey = nameKey;
        this.voteNum = 0;
        this.price = 0;
        this.base = 1;
        this.position = 0;
    }

    public int getBase() { return base; }
    public void setBase(int base) { this.base = base; }

    public boolean isBuy() { return price != 0; }

    public String getNameKey() { return nameKey; }
    public void setNameKey(String nameKey) { this.nameKey = nameKey; }

    public int getVoteNum() { return voteNum; }
    public void setVoteNum(int voteNum) { this.voteNum = voteNum; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    @Override
    public String toString() {
        return "Search{" +
                "nameKey='" + nameKey + '\'' +
                ", voteNum=" + voteNum +
                ", price=" + price +
                '}';
    }
}
