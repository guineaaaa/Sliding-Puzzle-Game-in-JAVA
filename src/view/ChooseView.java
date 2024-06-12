package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseView extends JFrame {
    private JButton button3x3; // 3x3 버튼
    private JButton button4x4; // 4x4 버튼
    private JButton button5x5; // 5x5 버튼

    public ChooseView() {
        setTitle("Choose Difficulty"); // 창 제목 설정
        setSize(900, 600); // 창 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을 때 프로그램 종료
        setLayout(null); // 레이아웃 매니저 설정: null

        // 배경 이미지 추가
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/images/choose.png"))); // 배경 이미지 설정
        background.setSize(900, 600); // 배경 크기 설정
        background.setLayout(null); // 배경 레이아웃 설정: null
        add(background); // 배경을 프레임에 추가

        // 3x3 버튼 설정
        button3x3 = new JButton(new ImageIcon(getClass().getResource("/images/3x3.png"))); // 3x3 이미지를 아이콘으로 하는 버튼 생성
        button3x3.setBounds(350, 120, 200, 80); // 버튼 위치 및 크기 설정
        button3x3.setBorderPainted(false); // 테두리 그리지 않음
        button3x3.setContentAreaFilled(false); // 내용 영역 채우지 않음
        button3x3.setFocusPainted(false); // 포커스 표시 안 함
        button3x3.addMouseListener(new ButtonHoverAdapter("/images/3x3Hovered.png", "/images/3x3.png")); // 마우스 이벤트 리스너 추가
        background.add(button3x3); // 버튼을 배경에 추가

        // 4x4 버튼 설정
        button4x4 = new JButton(new ImageIcon(getClass().getResource("/images/4x4.png"))); // 4x4 이미지를 아이콘으로 하는 버튼 생성
        button4x4.setBounds(350, 220, 200, 80); // 버튼 위치 및 크기 설정
        button4x4.setBorderPainted(false); // 테두리 그리지 않음
        button4x4.setContentAreaFilled(false); // 내용 영역 채우지 않음
        button4x4.setFocusPainted(false); // 포커스 표시 안 함
        button4x4.addMouseListener(new ButtonHoverAdapter("/images/4x4Hovered.png", "/images/4x4.png")); // 마우스 이벤트 리스너 추가
        background.add(button4x4); // 버튼을 배경에 추가

        // 5x5 버튼 설정
        button5x5 = new JButton(new ImageIcon(getClass().getResource("/images/5x5.png"))); // 5x5 이미지를 아이콘으로 하는 버튼 생성
        button5x5.setBounds(350, 320, 200, 80); // 버튼 위치 및 크기 설정
        button5x5.setBorderPainted(false); // 테두리 그리지 않음
        button5x5.setContentAreaFilled(false); // 내용 영역 채우지 않음
        button5x5.setFocusPainted(false); // 포커스 표시 안 함
        button5x5.addMouseListener(new ButtonHoverAdapter("/images/5x5Hovered.png", "/images/5x5.png")); // 마우스 이벤트 리스너 추가
        background.add(button5x5); // 버튼을 배경에 추가
    }

    // 3x3 버튼 리스너 추가 메서드
    public void add3x3ButtonListener(ActionListener listener) {
        button3x3.addActionListener(listener);
    }

    // 4x4 버튼 리스너 추가 메서드
    public void add4x4ButtonListener(ActionListener listener) {
        button4x4.addActionListener(listener);
    }

    // 5x5 버튼 리스너 추가 메서드
    public void add5x5ButtonListener(ActionListener listener) {
        button5x5.addActionListener(listener);
    }

    // StartGame 버튼 리스너 추가 메서드
    public void addStartGameButtonListener(ActionListener listener) {
        add3x3ButtonListener(e -> listener.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "3x3")));
        add4x4ButtonListener(e -> listener.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "4x4")));
        add5x5ButtonListener(e -> listener.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "5x5")));
    }
}
