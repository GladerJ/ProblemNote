package top.mygld.problemnote.common;

import java.util.Random;

/**
 * 验证码工具类
 */
public class CaptchaUtil {
    
    private static final String NUMBERS = "0123456789";
    private static final int CAPTCHA_LENGTH = 4; // 验证码长度
    
    /**
     * 生成随机数字验证码
     * @return 4位数字验证码
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
     * 验证验证码
     * @param inputCaptcha 用户输入的验证码
     * @param sessionCaptcha 会话中存储的验证码
     * @return 验证是否通过
     */
    public static boolean validateCaptcha(String inputCaptcha, String sessionCaptcha) {
        if (inputCaptcha == null || sessionCaptcha == null) {
            return false;
        }
        
        return inputCaptcha.trim().equals(sessionCaptcha.trim());
    }
    
    /**
     * 生成验证码图片的SVG（简单实现）
     * @param captcha 验证码文本
     * @return SVG格式的验证码图片
     */
    public static String generateCaptchaSVG(String captcha) {
        if (captcha == null || captcha.length() != CAPTCHA_LENGTH) {
            return "";
        }
        
        StringBuilder svg = new StringBuilder();
        svg.append("<svg width='120' height='40' xmlns='http://www.w3.org/2000/svg' style='display:block;'>");
        svg.append("<rect width='120' height='40' fill='#f8f9fa' stroke='#dee2e6' stroke-width='1'/>");
        
        // 添加干扰线
        svg.append("<line x1='0' y1='10' x2='120' y2='10' stroke='#e9ecef' stroke-width='1'/>");
        svg.append("<line x1='0' y1='20' x2='120' y2='20' stroke='#e9ecef' stroke-width='1'/>");
        svg.append("<line x1='0' y1='30' x2='120' y2='30' stroke='#e9ecef' stroke-width='1'/>");
        
        // 添加验证码文本
        for (int i = 0; i < captcha.length(); i++) {
            int x = 20 + i * 20;
            int y = 25 + (int)(Math.random() * 6 - 3); // 随机垂直偏移
            int fontSize = 16 + (int)(Math.random() * 4 - 2); // 随机字体大小
            int rotation = (int)(Math.random() * 20 - 10); // 随机旋转角度
            
            svg.append("<text x='").append(x).append("' y='").append(y).append("' ")
               .append("font-family='Arial, sans-serif' font-size='").append(fontSize).append("' ")
               .append("fill='#495057' font-weight='bold' ")
               .append("transform='rotate(").append(rotation).append(" ").append(x).append(" ").append(y).append(")'>")
               .append(captcha.charAt(i))
               .append("</text>");
        }
        
        svg.append("</svg>");
        return svg.toString();
    }
} 