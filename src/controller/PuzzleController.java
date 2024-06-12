package controller; // controller 패키지에 속함
import model.PuzzleModel; // PuzzleModel 모델을 임포트
import view.ChooseView; // ChooseView 뷰를 임포트
import view.GameOverView; // GameOverView 뷰를 임포트
import view.IntroView; // IntroView 뷰를 임포트
import view.PuzzleView; // PuzzleView 뷰를 임포트
import javax.swing.*; // javax.swing 패키지의 모든 클래스를 임포트
import java.io.IOException; // IOException을 임포트
import java.net.URL; // URL을 임포트

public class PuzzleController { // PuzzleController 클래스 선언
    private IntroView introView; // IntroView 객체 선언
    private ChooseView chooseView; // ChooseView 객체 선언
    private PuzzleView puzzleView; // PuzzleView 객체 선언
    private PuzzleModel model; // PuzzleModel 객체 선언
    private int moveCount; // 이동 횟수를 나타내는 변수 
    private long startTime; // 시작 시간을 나타내는 변수

	// 생성자 정의, IntroView와 ChooseView를 인자로 받음
    public PuzzleController(IntroView introView, ChooseView chooseView) { 
        this.introView = introView; // introView를 초기화
        this.chooseView = chooseView; // chooseView를 초기화
        this.introView.addStartButtonListener(e -> showChooseView()); // introView의 시작 버튼에 대한 리스너 추가
        this.introView.addExitButtonListener(e -> System.exit(0)); //exit버튼 클릭 시 게임 종료
        this.chooseView.addStartGameButtonListener(e -> { // chooseView의 게임 시작 버튼에 대한 리스너 추가
            String command = e.getActionCommand(); // 이벤트에서 명령어를 가져옴
            switch (command) { // 명령어에 따라 분기
                case "3x3": // 3x3일 경우
                    startGame(3, 3); // startGame 메소드 호출
                    break; // switch 문 종료
                case "4x4": // 4x4일 경우
                    startGame(4, 4); // startGame 메소드 호출
                    break; // switch 문 종료
                case "5x5": // 5x5일 경우
                    startGame(5, 5); // startGame 메소드 호출
                    break; // switch 문 종료
            }
        });
    }

	// 선택 뷰를 표시하는 메소드
    private void showChooseView() { 
        introView.setVisible(false); // introView를 숨김
        chooseView.setVisible(true); // chooseView를 보이게 함
    }

    // startGame 메소드 정의
    private void startGame(int rows, int cols) { // 게임 시작 메소드 정의, 행과 열의 인자를 받음
        chooseView.setVisible(false); // 선택 뷰 숨기기
        SwingUtilities.invokeLater(() -> { // 이벤트 디스패치 스레드에서 실행
            try {
                URL imageUrl = PuzzleView.class.getResource(PuzzleView.getRandomImage()); // 무작위 이미지의 URL을 가져옴
                if (imageUrl == null) { // 이미지가 없으면
                    throw new IOException("Image not found."); // IOException 발생
                }
                model = new PuzzleModel(rows, cols, imageUrl); // 새 퍼즐 모델 생성
                model.shuffleBoard(); // 퍼즐 섞기
                puzzleView = new PuzzleView(rows, cols, imageUrl); // 새 퍼즐 뷰 생성
                puzzleView.setVisible(true); // 퍼즐 뷰 보이기
                moveCount = 0; // 이동 횟수 초기화
                startTime = System.currentTimeMillis(); // 시작 시간 기록
                setupTimer(); // 타이머 설정
                setupButtonListeners(); // 버튼 리스너 설정
            } catch (IOException e) { // 입출력 예외 처리
                e.printStackTrace(); // 예외 출력
            } 
        });
    }

    private void setupTimer() { // 타이머를 설정하는 메소드
        Timer timer = new Timer(1000, e -> { // 1초마다 실행되는 타이머 생성
            long elapsed = (System.currentTimeMillis() - startTime) / 1000; // 경과 시간 계산
            puzzleView.updateTimer(elapsed); // 퍼즐 뷰의 타이머 업데이트
        });
        timer.start(); // 타이머 시작
    }

    private void setupButtonListeners() { // 버튼 리스너를 설정하는 메소드
        JButton[][] buttons = puzzleView.getButtons(); // 퍼즐 뷰에서 버튼 배열 가져오기
        for (int y = 0; y < buttons.length; y++) { // 버튼 배열의 행 반복
            for (int x = 0; x < buttons[y].length; x++) { // 버튼 배열의 열 반복
                int row = y; // 현재 행 저장
                int col = x; // 현재 열 저장
                buttons[y][x].addActionListener(e -> { // 버튼에 대한 액션 리스너 추가
                    if (model.moveTile(row, col)) { // 퍼즐 모델에서 타일을 이동할 수 있다면
                        puzzleView.updatePuzzle(); // 퍼즐 뷰 업데이트
                        moveCount++; // 이동 횟수 증가
                        puzzleView.updateMoveCount(moveCount); // 이동 횟수 업데이트
                        if (model.isPuzzleCompleted()) { // 퍼즐이 완료되었다면
                            puzzleView.setVisible(false); // 퍼즐 뷰 숨기기
                            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // 경과 시간 계산
                            GameOverView gameOverView = new GameOverView(elapsedTime, moveCount); // 게임 오버 뷰 생성
                            // gameOverView.setVisible(true); // 게임 오버 뷰 표시
                            
                        }
                    }
                });
            }
        }
    }
}
