package top.mygld.problemnote.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class SimpleCaptchaUtil {
    
    private static final String NUMBERS = "0123456789";
    private static final int CAPTCHA_LENGTH = 4;
    private static final int IMAGE_WIDTH = 120;
    private static final int IMAGE_HEIGHT = 40;
    
    /**
     * 生成随机数字验证码
     */
    public static String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            int index = random.nextInt(NUMBERS.length());
            captcha.append(NUMBERS.charAt(index));
        }
        
        return captcha.toString();
    }
    
    /**
     * 生成验证码图片
     */
    public static BufferedImage generateCaptchaImage(String captcha) {
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // 设置背景色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        
        // 设置边框
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(0, 0, IMAGE_WIDTH - 1, IMAGE_HEIGHT - 1);
        
        // 设置字体
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.BLACK);
        
        // 绘制验证码文字
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(captcha);
        int x = (IMAGE_WIDTH - textWidth) / 2;
        int y = (IMAGE_HEIGHT + fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
        
        g2d.drawString(captcha, x, y);
        
        // 添加干扰线
        g2d.setColor(Color.LIGHT_GRAY);
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int x1 = random.nextInt(IMAGE_WIDTH);
            int y1 = random.nextInt(IMAGE_HEIGHT);
            int x2 = random.nextInt(IMAGE_WIDTH);
            int y2 = random.nextInt(IMAGE_HEIGHT);
            g2d.drawLine(x1, y1, x2, y2);
        }
        
        g2d.dispose();
        return image;
    }
    
    /**
     * 将BufferedImage转换为字节数组
     */
    public static byte[] imageToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", baos);
        return baos.toByteArray();
    }
} 