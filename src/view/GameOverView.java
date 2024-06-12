package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class GameOverView extends JFrame {
    private JLabel timeLabel;
    private JLabel moveCountLabel;
    private JLabel scoreLabel;
    public GameOverView(long timeElapsed, int moveCount) {
        setTitle("Game Over");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/images/gameOverBackground.png")));
        background.setSize(600, 600);
        background.setLayout(null);
        add(background);

        // 총점 계산하기
        int score = 10000 - (int) (timeElapsed + moveCount);

        // 글자 폰트 설정
        Font labelFont = new Font("", Font.BOLD, 30);

        // 경과 시간 표시하기
        timeLabel = new JLabel("경과 시간: " + timeElapsed+" (초)");
        timeLabel.setBounds(200, 200, 300, 50);
        timeLabel.setFont(labelFont);
        background.add(timeLabel);

        // 이동횟수 표시
        moveCountLabel = new JLabel("이동 횟수: " + moveCount);
        moveCountLabel.setBounds(200, 250, 300, 50);
        moveCountLabel.setFont(labelFont);
        background.add(moveCountLabel);

        // 총점 표시
        scoreLabel = new JLabel("총점: " + score);
        scoreLabel.setBounds(200, 300, 300, 50);
        scoreLabel.setFont(labelFont);
        background.add(scoreLabel);
    }
}
