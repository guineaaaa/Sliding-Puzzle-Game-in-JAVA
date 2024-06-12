package util;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageSplitter {
    public static List<ImageIcon> splitImage(URL imageUrl, int rows, int cols) throws IOException {
        if (imageUrl == null) {
            throw new IOException("URL은 null이 될 수 없습니다.");
        }

        BufferedImage originalImage = ImageIO.read(imageUrl);
        if (originalImage == null) {
            throw new IOException("URL로 이미지를 받아오는데 실패했습니다.");
        }

        // 이미지를 600x600 크기로 조정
        BufferedImage resizedImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 600, 600, null);
        g.dispose();

        List<ImageIcon> images = new ArrayList<>();

        int width = resizedImage.getWidth() / cols;
        int height = resizedImage.getHeight() / rows;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                BufferedImage subImage = resizedImage.getSubimage(x * width, y * height, width, height);
                images.add(new ImageIcon(subImage));
                System.out.println("Sub-image created: " + x + ", " + y); // 디버깅 출력
            }
        }
        return images;
    }
}
