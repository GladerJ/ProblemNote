package top.mygld.problemnote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.mygld.problemnote.util.SimpleCaptchaUtil;

@Controller
public class TestController {
    
    @GetMapping("/test-captcha")
    public String testCaptchaPage() {
        return "test-captcha";
    }
    
    @GetMapping("/test-captcha-text")
    @ResponseBody
    public String testCaptchaText() {
        String captcha = SimpleCaptchaUtil.generateCaptcha();
        return "生成的验证码: " + captcha;
    }
} 