//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * @title:
// * @author:nanzhou
// * @date:
// */
//public class DouBiBanUtil {
//    static Scanner scanner = new Scanner(System.in);
//    static int countPlayer1 = 0;
//    static int countPlayer2 = 0;
//    static int cardNumPlayer1 = 0;
//    static int cardNumPlayer2 = 0;
//
//    /**
//     * 游戏启动
//     */
//    public static void start() {
//        Poker fight = new Poker(false, "DouBiBan");
//        prepare(fight.PlayingCardPrePreparation());
//    }
//
//    /**
//     * 游戏准备
//     *
//     * @param pokerCards
//     * @return
//     */
//    private static void prepare(List<PokerCardsList.PokerCard> pokerCards) {
//        /**
//         * player1 电脑
//         * player2 玩家
//         */
//        List<PokerCardsList.PokerCard> player1 = new ArrayList<>();
//        List<PokerCardsList.PokerCard> player2 = new ArrayList<>();
//        System.out.println(gameStart(player1, player2, pokerCards));
//    }
//
//    /**
//     * 游戏开始
//     *
//     * @param player1
//     * @param player2
//     * @param pokerCards
//     * @return
//     */
//    private static String gameStart(List<PokerCardsList.PokerCard> player1, List<PokerCardsList.PokerCard> player2, List<PokerCardsList.PokerCard> pokerCards) {
//        boolean flag1 = true;
//        boolean flag2 = true;
//        int judeplayer1 = 1;
//        int judeplayer2 = 2;
//        //发放牌的计数器
//        int pokerNum = 1;
//        while ((flag1 == true | flag2 == true)) {
//            if ((flag1 == false && flag2 == false)) {
//                break;
//            }
//            System.out.println("玩家1,您的拿牌指令为1，输入其他数字为不要");
//            flag1 = takePokers(player1, pokerCards, flag1, pokerNum, judeplayer1, cardNumPlayer1);
//            if (flag1 == false && flag2 == false) {
//                break;
//            }
//            System.out.println("玩家2，您的拿牌指令为2，输入其他数字为不要");
//            flag2 = takePokers(player2, pokerCards, flag2, pokerNum, judeplayer2, cardNumPlayer2);
//            if (flag1 == false && flag2 == false) {
//                break;
//            }
//        }
//        return stop();
//    }
//
//    /**
//     * 拿牌
//     *
//     * @param player
//     * @param pokerCards
//     * @param flag
//     * @param pokerNum
//     * @param judenum
//     * @param cardNumPlayer
//     * @return
//     */
//    private static Boolean takePokers(List<PokerCardsList.PokerCard> player, List<PokerCardsList.PokerCard> pokerCards, boolean flag, int pokerNum, int judenum, int cardNumPlayer) {
//        System.out.println("玩家请决定拿牌");
//        int jude = scanner.nextInt();
//        if (jude != judenum) {
//            flag = false;
//        } else {
//            flag = true;
//        }
//        while (jude == judenum) {
//            player.add(pokerCards.get(pokerNum++));
//            System.out.println(player.get(cardNumPlayer).getCard());
//            if (judenum ==1){
//                player.add(pokerCards.get(pokerNum++));
//                countPlayer1 += player.get(cardNumPlayer).getOder();
//            }else {
//                player.add(pokerCards.get(pokerNum++));
//                countPlayer2+= player.get(cardNumPlayer).getOder();
//            }
//            System.out.println("目前点数为" + (double)countPlayer1/10+" "+(double)countPlayer2/10);
//            cardNumPlayer++;
//            //check
//            //Todo
//            if (check())  {
//                stop();
//            }
//            jude = scanner.nextInt();
//        }
//        System.out.println("玩家结束拿牌");
//        return flag;
//    }
//
//    /**
//     * 停止
//     *
//     * @return
//     */
//    private static String stop() {
//        System.exit(0);
//        return "结束  \n" + "玩家1,点数为" + countPlayer1 + "，手中牌数为" + cardNumPlayer1 + "玩家2，点数为" + countPlayer2 + "，手中牌数为" + cardNumPlayer2;
//    }
//    /**
//     * check
//     * @return
//     */
//    private static boolean check() {
//        boolean player1Wins = countPlayer1 > 105;
//        boolean player2Wins = countPlayer2 > 105;
//        if (player1Wins || player2Wins) {
//            String winner;
//            double player1Points = (double) countPlayer1 / 10;
//            double player2Points = (double) countPlayer2 / 10;
//            int player1Cards = cardNumPlayer1;
//            int player2Cards = cardNumPlayer2;
//            if (player1Wins) {
//                winner = "玩家1";
//            } else {
//                winner = "玩家2";
//                // Swap player1 and player2 details
//                player1Points = (double) countPlayer2 / 10;
//                player2Points = (double) countPlayer1 / 10;
//                player1Cards = cardNumPlayer2;
//                player2Cards = cardNumPlayer1;
//            }
//            System.out.println(winner + "获胜,点数为" + player1Points + "，手中牌数为" + player1Cards +
//                    "，" + "玩家2点数为" + player2Points + "，手中牌数为" + player2Cards);
//
//            return true;
//        }
//        return false;
//    }
//
//}
