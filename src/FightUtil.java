import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author:nanzhou
 */
public class FightUtil {
    /**
     * 游戏启动
     */
    public static void start() {
        Poker fight = new Poker(true, "Fight");
        prepare(fight.playingCardPrePreparation()).forEach(System.out::println);
    }

    /**
     * 游戏准备
     *
     * @param pokerCardsLists 牌堆
     * @return 分好序列的牌
     */
    public static List<List<String>> prepare(List<PokerCardsList.PokerCard> pokerCardsLists) {
        List<PokerCardsList.PokerCard> player1 = new ArrayList<>();
        List<PokerCardsList.PokerCard> player2 = new ArrayList<>();
        List<PokerCardsList.PokerCard> player3 = new ArrayList<>();
        List<PokerCardsList.PokerCard> bottom = new ArrayList<>();

        // 按照规则发牌
        deal(player1, player2, player3, bottom, pokerCardsLists);

        // 对各玩家和底牌排序
        return pokerSort(player1, player2, player3, bottom);
    }


    /**
     * 发牌
     *
     * @param player1 玩家1
     * @param player2 玩家2
     * @param player3 玩家3
     * @param bottom  底牌
     * @return 分好的牌
     */
    private static List<List<String>> pokerSort(List<PokerCardsList.PokerCard> player1, List<PokerCardsList.PokerCard> player2, List<PokerCardsList.PokerCard> player3, List<PokerCardsList.PokerCard> bottom) {
        List<String> p1 = sortAndMapToCards(player1);
        List<String> p2 = sortAndMapToCards(player2);
        List<String> p3 = sortAndMapToCards(player3);
        List<String> bot = bottom.stream()
                .map(PokerCardsList.PokerCard::getCard)
                .toList();
        // 结果
        return List.of(p1, p2, p3, bot);
    }

//    private static List<List<String>> pokerSort(List<PokerCardsList.PokerCard> player1, List<PokerCardsList.PokerCard> player2, List<PokerCardsList.PokerCard> player3, List<PokerCardsList.PokerCard> bottom) {
//        List<String> p1 = player1.stream()
//                .sorted(Comparator.comparingInt(PokerCardsList.PokerCard::getOder))
//                .map(PokerCardsList.PokerCard::getCard)
//                .toList();
//        List<String> p2 = player2.stream()
//                .sorted(Comparator.comparingInt(PokerCardsList.PokerCard::getOder))
//                .map(PokerCardsList.PokerCard::getCard)
//                .toList();
//        List<String> p3 = player3.stream()
//                .sorted(Comparator.comparingInt(PokerCardsList.PokerCard::getOder))
//                .map(PokerCardsList.PokerCard::getCard)
//                .toList();
//        List<String> bot = bottom.stream()
//                .map(PokerCardsList.PokerCard::getCard)
//                .toList();
//        // 结果
//        return List.of(p1, p2, p3, bot);
//    }

    /**
     * 操作流
     *
     * @param cards 牌堆
     * @return 操作返回值
     */
    private static List<String> sortAndMapToCards(List<PokerCardsList.PokerCard> cards) {
        return cards.stream()
                .sorted(Comparator.comparingInt(PokerCardsList.PokerCard::getOder))
                .map(PokerCardsList.PokerCard::getCard)
                .toList();
    }

    /**
     * 发牌
     *
     * @param player1         玩家1
     * @param player2         玩家2
     * @param player3         玩家3
     * @param bottom          底牌
     * @param pokerCardsLists 乱序牌堆
     */
    private static void deal(List<PokerCardsList.PokerCard> player1, List<PokerCardsList.PokerCard> player2, List<PokerCardsList.PokerCard> player3, List<PokerCardsList.PokerCard> bottom, List<PokerCardsList.PokerCard> pokerCardsLists) {
        for (int i = 0; i < pokerCardsLists.size(); i++) {
            if (pokerCardsLists.size() - 3 > i) {
                switch (i % 3) {
                    case 0 -> player1.add(pokerCardsLists.get(i));
                    case 1 -> player2.add(pokerCardsLists.get(i));
                    default -> player3.add(pokerCardsLists.get(i));
                }
            } else {
                bottom.add(pokerCardsLists.get(i));
            }
        }

    }

}
