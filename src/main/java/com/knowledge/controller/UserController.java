package com.knowledge.controller;
import com.knowledge.entity.User;
import com.knowledge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model, HttpSession session){
        if(username == null || username.trim().isEmpty() || password == null || password.isEmpty()){
            model.addAttribute("msg","用户名和密码不能为空");
            return "login";
        }
        User loginUser = userService.login(username,password);
        if(loginUser != null){
            session.setAttribute("loginUser",loginUser);
            return "redirect:/article/list";
        }
        // 登录失败：先判断用户是否存在
        if(userService.findByUsername(username) == null){
            // 用户不存在 → 返回注册页面
            model.addAttribute("username",username);
            model.addAttribute("msg","该用户名尚未注册，请先注册");
            return "register";
        }else{
            model.addAttribute("msg","密码错误，请重新输入");
            return "login";
        }
    }

    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String register(String username, String password, String confirmPassword,
                           Model model, RedirectAttributes redirectAttributes){
        if(username == null || username.trim().isEmpty() || password == null || password.isEmpty()){
            model.addAttribute("username",username);
            model.addAttribute("msg","用户名和密码不能为空");
            return "register";
        }
        if(!password.equals(confirmPassword)){
            model.addAttribute("username",username);
            model.addAttribute("msg","两次输入的密码不一致");
            return "register";
        }
        if(userService.findByUsername(username) != null){
            model.addAttribute("username",username);
            model.addAttribute("msg","用户名已存在，请直接登录");
            return "register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(userService.register(user)){
            redirectAttributes.addFlashAttribute("msg","注册成功，请登录");
            return "redirect:/user/toLogin";
        }else{
            model.addAttribute("username",username);
            model.addAttribute("msg","注册失败，请稍后重试");
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/user/toLogin";
    }
}
