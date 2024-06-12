package model;

import javax.swing.ImageIcon;

public class Tile {
    private int number;  // 타일의 숫자를 저장하는 변수
    private ImageIcon image;  // 타일의 이미지를 저장하는 변수

    // 생성자: 주어진 숫자와 이미지로 타일을 초기화합니다.
    public Tile(int number, ImageIcon image) {
        this.number = number;
        this.image = image;
    }

    // number 변수의 getter 메서드: 타일의 숫자를 반환합니다.
    public int getNumber() {
        return number;
    }

    // number 변수의 setter 메서드: 타일의 숫자를 설정합니다.
    public void setNumber(int number) {
        this.number = number;
    }

    // image 변수의 getter 메서드: 타일의 이미지를 반환합니다.
    public ImageIcon getImage() {
        return image;
    }

    // image 변수의 setter 메서드: 타일의 이미지를 설정합니다.
    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
