import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author:nanzhou
 */
public class DouBiBanUtil {
    static Scanner scanner = new Scanner(System.in);
    static int countPlayer1 = 0;
    static int countPlayer2 = 0;
    static int cardNumPlayer1 = 0;
    static int cardNumPlayer2 = 0;
    static int compNum = 105;
    /**
     * 发放牌的计数器
     */
    static int pokerNum = 0;

    /**
     * 游戏启动
     */
    public static void start() {
        Poker douBiBan = new Poker(false, "DouBiBan");
        prepare(douBiBan.playingCardPrePreparation());
    }

    /**
     * 游戏准备
     *
     * @param pokerCards 牌堆就绪
     */
    private static void prepare(List<PokerCardsList.PokerCard> pokerCards) {

        List<PokerCardsList.PokerCard> player1 = new ArrayList<>();
        List<PokerCardsList.PokerCard> player2 = new ArrayList<>();
        gameStart(player1, player2, pokerCards);
    }

    /**
     * 游戏开始
     *
     * @param player1    玩家1
     * @param player2    玩家2
     * @param pokerCards 牌堆
     */
    private static void gameStart(List<PokerCardsList.PokerCard> player1, List<PokerCardsList.PokerCard> player2, List<PokerCardsList.PokerCard> pokerCards) {
        int judePlayer1 = 1;
        int judeplayer2 = 2;
        System.out.println("请问需要玩家2是电脑玩家吗？请选择 1 为 是，2 为 否");
        int needComp = scanner.nextInt();
        if (needComp==1){
            System.out.println("电脑上线");
        }
        while (true) {
            System.out.println("玩家1,您的拿牌指令为" + judePlayer1 + "，输入其他数字为不要");
            boolean flag1 = takePokers(player1, pokerCards, judePlayer1);

            if (!flag1) {
                break;
            }
            boolean flag2;
            if (needComp ==1){
                System.out.println("电脑在决定是否拿牌");
                flag2 = compTakePokers(player2,pokerCards);
            }else {
                System.out.println("玩家2，您的拿牌指令为" + judeplayer2 + "，输入其他数字为不要");
                flag2 = takePokers(player2, pokerCards, judeplayer2);
            }
            if (!flag2) {
                break;
            }
        }
        System.out.println("游戏结束" + "\n玩家1,点数为 " + countPlayer1 + "，手中牌数为 " + cardNumPlayer1 + "\n玩家2,点数为 " + countPlayer2 + "，手中牌数为 " + cardNumPlayer2);
        stop();
    }

    /**
     * @param player     玩家
     * @param pokerCards 牌堆
     * @param duodenum   玩家拿牌指令
     * @return 返回拿取标记
     */
    private static boolean takePokers(List<PokerCardsList.PokerCard> player, List<PokerCardsList.PokerCard> pokerCards, int duodenum) {
        System.out.println("玩家请决定拿牌");
        int jude = scanner.nextInt();
        boolean flag = (jude == duodenum);
        while (jude == duodenum) {
            PokerCardsList.PokerCard selectedCard = pokerCards.get(pokerNum++);
            player.add(selectedCard);
            System.out.println(selectedCard.getCard());

            if (duodenum == 1) {
                countPlayer1 += selectedCard.getOder();
                cardNumPlayer1++;
            } else {
                countPlayer2 += selectedCard.getOder();
                cardNumPlayer2++;
            }
            System.out.println("目前点数为" + (double) countPlayer1 / 10 + " " + (double) countPlayer2 / 10);
            if (check()) {
                stop();
            }
            System.out.println("补牌请继续输入您的拿牌指令，反之输入其他数字");
            jude = scanner.nextInt();
        }
        System.out.println("玩家结束拿牌");
        return flag;
    }

    /**
     * @param player     玩家
     * @param pokerCards 牌堆
     * @return 返回拿取标记
     */
    private static boolean compTakePokers(List<PokerCardsList.PokerCard> player, List<PokerCardsList.PokerCard> pokerCards) {
        System.out.println("电脑决定拿牌");
        boolean jude = false;
        boolean take = cardNumPlayer2 < compNum && cardNumPlayer2 < cardNumPlayer1;
        while (take) {
            PokerCardsList.PokerCard selectedCard = pokerCards.get(pokerNum++);
            player.add(selectedCard);
            System.out.println(selectedCard.getCard());
            countPlayer2 += selectedCard.getOder();
            cardNumPlayer2++;
            jude = true;
            System.out.println("目前点数为" + (double) countPlayer1 / 10 + " " + (double) countPlayer2 / 10);
            if (check()) {
                stop();
            }
            System.out.println("电脑决定再次拿牌");
            take = cardNumPlayer2 < compNum && cardNumPlayer2 < cardNumPlayer1;
        }
        System.out.println("电脑结束拿牌");
        return jude;
    }
    /**
     * 停止
     *
     */
    private static void stop() {
        System.exit(0);
    }

    /**
     * check
     *
     * @return 返回获胜者，无获胜者返回 false
     */
    private static boolean check() {
        boolean player1Wins = (countPlayer2 > 105) | (countPlayer1 == 105);
        boolean player2Wins = countPlayer1 > 105 | (countPlayer2 == 105);
        if (player1Wins || player2Wins) {
            String winner;
            String unwiser;
            double player1Points = (double) countPlayer1 / 10;
            double player2Points = (double) countPlayer2 / 10;
            int player1Cards = cardNumPlayer1;
            int player2Cards = cardNumPlayer2;
            if (player1Wins) {
                winner = "玩家1";
                unwiser = "玩家2";
            } else {
                winner = "玩家2";
                unwiser = "玩家1";
                player1Points = (double) countPlayer2 / 10;
                player2Points = (double) countPlayer1 / 10;
                player1Cards = cardNumPlayer2;
                player2Cards = cardNumPlayer1;
            }
            System.out.println(winner + "获胜,点数为" + player1Points + "，手中牌数为" + player1Cards +
                    "，" + unwiser + "点数为" + player2Points + "，手中牌数为" + player2Cards);
            return true;
        }
        return false;
    }

}
