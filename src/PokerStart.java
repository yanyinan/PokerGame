import java.util.Scanner;

/**
 * @author:nanzhou
 */
public class PokerStart {
    public static void main(String[] args) {
        System.out.println("斗地主发牌请输入 1 ，10点半请输入 2");
        int fightGame = 1;
        int douBiBanGame = 2;
        Scanner scanner = new Scanner(System.in);
        int judeGame = scanner.nextInt();
        if (judeGame == fightGame) {
            FightUtil.start();
        } else if (judeGame == douBiBanGame) {
            DouBiBanUtil.start();
        }
    }
}
