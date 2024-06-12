package view;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import model.PuzzleModel;
import util.ImageSplitter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PuzzleView extends JFrame {
    private int rows;
    private int cols;
    private JPanel puzzlePanel;
    private JLabel timerLabel;
    private JLabel moveCountLabel;
    private JButton[][] buttons;
    private PuzzleModel model;

    // 퍼즐 게임 뷰 생성자
    public PuzzleView(int rows, int cols, URL imageUrl) throws IOException {
        this.rows = rows; 
        this.cols = cols;
        this.model = new PuzzleModel(rows, cols, imageUrl);

        setTitle("Puzzle Game"); // 제목 설정
        setSize(600, 600); // 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 설정
        setLayout(new BorderLayout()); // 레이아웃 설정

        puzzlePanel = new JPanel(); // 퍼즐 패널 생성
        puzzlePanel.setLayout(new GridLayout(rows, cols)); // 그리드 레이아웃 설정
        add(puzzlePanel, BorderLayout.CENTER); // 중앙에 추가

        JPanel infoPanel = new JPanel(); // 정보 패널 생성
        infoPanel.setLayout(new GridLayout(1, 2)); // 그리드 레이아웃 설정
        timerLabel = new JLabel("Time: 0"); // 타이머 라벨 생성
        moveCountLabel = new JLabel("Moves: 0"); // 이동 횟수 라벨 생성
        infoPanel.add(timerLabel); // 타이머 라벨 추가
        infoPanel.add(moveCountLabel); // 이동 횟수 라벨 추가
        add(infoPanel, BorderLayout.NORTH); // 위쪽에 추가

        initializePuzzle(); // 퍼즐 초기화 메서드 호출
    }

    // 퍼즐 초기화 메서드
    private void initializePuzzle() throws IOException {
        buttons = new JButton[rows][cols]; // 버튼 배열 생성

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                buttons[y][x] = new JButton(); // 버튼 생성
                buttons[y][x].setMargin(new Insets(0, 0, 0, 0)); // 버튼의 패딩 제거
                buttons[y][x].setContentAreaFilled(false); // 내용 영역 채우기 비활성화
                buttons[y][x].setBorderPainted(false); // 테두리 그리기 비활성화
                buttons[y][x].setFocusPainted(false); // 포커스 표시 비활성화

                int tileNumber = model.getBoard()[y][x].getNumber();
                if (tileNumber == 0) {
                    buttons[y][x].setVisible(false); // 빈 칸은 보이지 않도록 설정
                } else {
                    buttons[y][x].setIcon(model.getBoard()[y][x].getImage()); // 아이콘 설정
                    buttons[y][x].setPreferredSize(new Dimension(model.getBoard()[y][x].getImage().getIconWidth(),
                            model.getBoard()[y][x].getImage().getIconHeight())); // 버튼 크기 설정
                }

                puzzlePanel.add(buttons[y][x]); // 퍼즐 패널에 버튼 추가

                final int row = y;
                final int col = x;
                buttons[y][x].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (model.moveTile(row, col)) {
                            updatePuzzle(); // 퍼즐 업데이트
                            playSound("/music/buttonEnteredMusic.wav"); // 사운드 재생
                            updateMoveCount(model.getMoves()); // 이동 횟수 업데이트
                        }
                    }
                });
            }
        }
    }

    // 퍼즐 업데이트 메서드
    public void updatePuzzle() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                int tileNumber = model.getBoard()[y][x].getNumber();
                if (tileNumber == 0) {
                    buttons[y][x].setVisible(false); // 빈 칸은 보이지 않도록 설정
                } else {
                    buttons[y][x].setVisible(true); // 보이게 설정
                    buttons[y][x].setIcon(model.getBoard()[y][x].getImage()); // 아이콘 설정
                }
            }
        }
        if (model.isPuzzleCompleted()) {
            // 퍼즐이 완료되었을 때 GameOverView로 이동하는 로직 추가
            long elapsedTime = model.getElapsedTime(); // 경과 시간
            int moveCount = model.getMoves(); // 이동 횟수
            GameOverView gameOverView = new GameOverView(elapsedTime, moveCount);
            gameOverView.setVisible(true);
            dispose(); // 현재 PuzzleView 창 닫기
        }
    } 

    // 타이머 업데이트 메서드
    public void updateTimer(long seconds) {
        timerLabel.setText("Time: " + seconds);
    }

    // 이동 횟수 업데이트 메서드
    public void updateMoveCount(int moves) {
        moveCountLabel.setText("Moves: " + moves);
    }

    // 사운드 재생 메서드
    public void playSound(String soundFile) {
        try {
            Clip clip = AudioSystem.getClip();
            URL soundUrl = getClass().getResource(soundFile);
            if (soundUrl == null) {
                throw new IOException("음원 파일을 찾을 수 없습니다: " + soundFile);
            }
            clip.open(AudioSystem.getAudioInputStream(soundUrl));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 랜덤 이미지 경로 반환 메서드
    public static String getRandomImage() {
        String[] images = { "/images/1.png", "/images/2.png", "/images/3.png", "/images/4.png" };
        int randomIndex = (int) (Math.random() * images.length);
        return images[randomIndex];
    }

    // 버튼 배열 반환 메서드
    public JButton[][] getButtons() {
        return buttons;
    }
}
