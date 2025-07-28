package top.mygld.problemnote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.mygld.problemnote.pojo.User;
import top.mygld.problemnote.service.UserService;
import top.mygld.problemnote.util.SimpleCaptchaUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // 登录页
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    // 生成验证码图片
    @GetMapping("/captcha")
    public void getCaptchaImage(HttpServletResponse response, HttpSession session) throws IOException {
        // 设置响应头
        response.setContentType("image/jpeg");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        
        // 生成验证码文本
        String capText = SimpleCaptchaUtil.generateCaptcha();
        session.setAttribute("captcha", capText);
        
        // 生成验证码图片
        BufferedImage bi = SimpleCaptchaUtil.generateCaptchaImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.flush();
        out.close();
    }

    // 登录表单提交
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String captcha,
                        HttpSession session,
                        Model model) {
        // 验证验证码
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captcha)) {
            model.addAttribute("error", "验证码错误");
            return "login";
        }
        
        // 验证用户名和密码
        User dbUser = userService.getUserByUsername(username);
        if (dbUser != null && dbUser.getPassword().equals(password)) {
            session.setAttribute("user", dbUser);
            // 登录成功后清除验证码
            session.removeAttribute("captcha");
            return "redirect:/";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // 主页面
    @GetMapping("/")
    public String index() {
        return "index";
    }
} 