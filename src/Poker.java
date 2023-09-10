import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:nanzhou
 */
public class Poker {
    /**
     * 暂存poker
     */
    private PokerCardsList poker;
    /**
     * 洗牌次数
     */
    private final int pokerNum = 3;

    /**
     * 无限定大小王构造
     */
    public Poker(String gameStyle) {
        this(true, gameStyle);
    }

    /**
     * 限定大小王构造
     *
     * @param kingFlag true 为有，反之为无
     */
    public Poker(Boolean kingFlag, String gameStyle) {
        poker = new PokerCardsList(kingFlag, gameStyle);
    }

    /**
     * 扑克牌预准备
     *
     * @return 返回洗牌后牌堆
     */
    public List<PokerCardsList.PokerCard> playingCardPrePreparation() {
        // 扑克牌
        List<PokerCardsList.PokerCard> pokerCardsLists = this.poker.getPokers();
        // 洗牌
        for (int i = 0; i < pokerNum; i++) {
            Collections.shuffle(pokerCardsLists);
        }
        return pokerCardsLists;
    }
}

/**
 * 牌堆
 */
class PokerCardsList {
    /**
     * 花色
     */
    private static final List<String> SUITS = new ArrayList<>();
    /**
     * 点数
     */
    private static final List<String> RANKS = new ArrayList<>();

    //初始化基本牌花色
    static {
        // 黑桃 (Spade)
        SUITS.add("\u2660");
        // 红心 (Heart)
        SUITS.add("\u2665");
        // 梅花 (Club)
        SUITS.add("\u2663");
        // 方块 (Diamond)
        SUITS.add("\u2666");
    }

    /**
     * 暂存牌
     */
    private List<PokerCard> pokers = new ArrayList<>();
    ;

    public PokerCardsList(String gameStyle) {
        this(true, gameStyle);
    }

    /**
     * 参数构造
     *
     * @param kingFlag  是否包含king
     * @param gameStyle 游戏类型
     */
    public PokerCardsList(Boolean kingFlag, String gameStyle) {
        //牌序列
        initializeRanks(gameStyle);
        //初始化牌
        initializePokers(gameStyle);
        //添加大小王
        initializeking(kingFlag);
    }

    /**
     * 是否添加大小王
     *
     * @param kingFlag 是否需要
     */
    private void initializeking(Boolean kingFlag) {
        if (kingFlag) {
            pokers.add(new PokerCard(null, "大王", 0));
            pokers.add(new PokerCard(null, "小王", 1));
        }
    }

    /**
     * 初始化牌
     *
     * @param gameStyle 游戏类型
     */
    private void initializePokers(String gameStyle) {
        AtomicInteger orderCounter = new AtomicInteger(0);
        SUITS.forEach(suit -> {
            AtomicInteger rankCounter = new AtomicInteger(0);
            RANKS.forEach(rank -> {
                int order;
                if ("Fight".equals(gameStyle)) {
                    order = calculateFightOrder(rankCounter, orderCounter);
                } else if ("DouBiBan".equals(gameStyle)) {
                    order = calculateDouBiBanOrder(rank, rankCounter);
                } else {
                    order = 0; // 默认值
                }
                pokers.add(new PokerCard(suit, rank, order));
            });
        });
    }

    /**
     * Fight 点数初始化
     *
     * @param rankCounter  初始值
     * @param orderCounter 初始值
     * @return
     */
    private int calculateFightOrder(AtomicInteger rankCounter, AtomicInteger orderCounter) {
        return 4 * rankCounter.getAndIncrement() + orderCounter.getAndIncrement() + rankCounter.get() + 2;
    }

    /**
     * DouBiBan 点数初始化
     *
     * @param rank        点数牌
     * @param rankCounter 初始值
     * @return
     */
    private int calculateDouBiBanOrder(String rank, AtomicInteger rankCounter) {
        int order=rankCounter.getAndIncrement() + 1;
        if (order <=10){
            order = (order) % 10 * 10;
            if (order == 0) {
                order = 100;
            }
        }else if("J".equals(rank) || "Q".equals(rank) || "K".equals(rank)) {
            order = 5;
        }

        return order;
    }

    /**
     * 牌序列
     *
     * @param gameStyle 游戏类型
     */
    private void initializeRanks(String gameStyle) {
        //Todo 根据需求添加序列
        if ("Fight".equals(gameStyle)) {
            Collections.addAll(RANKS, "2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3");
        } else if ("DouBiBan".equals(gameStyle)) {
            Collections.addAll(RANKS, "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");
        }
    }

    /**
     * 返回填充牌堆
     *
     * @return 完成填充牌堆
     */
    public List<PokerCard> getPokers() {
        return pokers;
    }

    /**
     * 牌面
     */
    class PokerCard {
        //花色
        private String color;
        //点数
        private String point;
        //序列
        private Integer oder;

        //构造
        public PokerCard(String color, String point, Integer oder) {
            this.color = color;
            this.point = point;
            this.oder = oder;
        }

        //获取花色
        public String getColor() {
            return color;
        }

        //获取点数
        public String getPoint() {
            return point;
        }

        //获取序列
        public Integer getOder() {
            return oder;
        }

        //牌面
        public String getCard() {
            return color == null ? point : color + point;
        }
    }
}

