package model;

import util.ImageSplitter;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PuzzleModel {
    private Tile[][] board; // 퍼즐 보드를 나타내는 2차원 배열
    private int rows;
    private int cols;
    private int emptyRow, emptyCol; // 빈 칸의 행과 열을 나타내는 변수
    private int moves; // 이동 횟수를 저장하는 변수
    private long startTime; // 게임 시작 시간을 저장하는 변수

    // 이미지 URL을 포함하는 생성자
    public PuzzleModel(int rows, int cols, URL imageUrl) {
        this.rows = rows; // 행 초기화
        this.cols = cols; // 열 초기화
        board = new Tile[rows][cols]; // 보드를 생성
        initializeBoard(imageUrl); // 보드 초기화
        startTime = System.currentTimeMillis(); // 시작 시간 설정
    }

    // 보드 초기화 메서드 
    public void initializeBoard(URL imageUrl) {
        List<ImageIcon> images = null;
        try {
            images = ImageSplitter.splitImage(imageUrl, rows, cols);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int k = 1; // 타일 번호 초기화
        moves = 0; // 이동 횟수 초기화
        for (int i = 0; i < rows; i++) { // 각 행 반복
            for (int j = 0; j < cols; j++) { // 각 열 반복
                if (i == rows - 1 && j == cols - 1) { // 빈 칸일 경우
                    board[i][j] = new Tile(0, null); // 빈 칸으로 설정
                    emptyRow = i; // 빈 칸의 행 저장
                    emptyCol = j; // 빈 칸의 열 저장
                } else {
                    board[i][j] = new Tile(k++, images.get((i * cols) + j)); // 이미지와 함께 타일 생성
                }
            }
        }
        shuffleBoard(); // 보드 섞기
    }

    // 퍼즐 보드 반환 메서드
    public Tile[][] getBoard() {
        return board;
    }

    // 이동 횟수 반환 메서드
    public int getMoves() {
        return moves;
    }

    // 경과 시간 반환 메서드
    public long getElapsedTime() {
        return (System.currentTimeMillis() - startTime) / 1000; // 밀리초를 초로 변환하여 반환
    }

    // 타일 이동 메서드
    public boolean moveTile(int row, int col) {
        if (Math.abs(emptyRow - row) + Math.abs(emptyCol - col) == 1) { // 이동 가능한 위치인지 확인
            // 타일 교환
            Tile temp = board[emptyRow][emptyCol];
            board[emptyRow][emptyCol] = board[row][col];
            board[row][col] = temp;

            // 빈 칸 위치 업데이트
            emptyRow = row;
            emptyCol = col;

            // 이동 횟수 증가
            moves++;
            return true; // 이동 성공을 알리는 값 반환
        }
        return false; // 이동 실패를 알리는 값 반환
    }
    

    // 퍼즐 보드 섞기 메서드
    public void shuffleBoard() {
        for (int i = 0; i < 1000; i++) { // 1000번 반복하여 보드 섞기
            int row = (int) (Math.random() * rows); // 랜덤한 행 선택
            int col = (int) (Math.random() * cols); // 랜덤한 열 선택
            moveTile(row, col); // 선택된 위치의 타일 이동
        }
        moves = 0; // 이동 횟수 초기화
    }
    
    // 퍼즐 완료 여부 확인 메서드
    public boolean isPuzzleCompleted() {
        int expectedNumber = 1;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                // 마지막 빈칸은 제외하고, 타일 번호가 예상하는 번호와 다른 경우 퍼즐 미완료
                if (y == rows - 1 && x == cols - 1) continue;
                if (board[y][x].getNumber() != expectedNumber++) {
                    return false;
                }
            }
        }
        return true;
    }
}

