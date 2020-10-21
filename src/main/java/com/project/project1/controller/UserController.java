package com.project.project1.controller;

import com.project.project1.entity.User_s;
import com.project.project1.service.UserService;
import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String gologin(){
        return "login";
    }

    @RequestMapping("/register")
    public String goRegister(){
        return "register";
    }

    @RequestMapping("/gologin")
    public String login(HttpServletRequest request, HttpSession session, Model model, String studentId){
        String pass = userService.login(studentId);
        String getpass = request.getParameter("password");
        System.out.println("查询到的密码：" + pass);
        if(studentId.equals("") | getpass.equals("")){
            System.out.println("账号密码为空");
            model.addAttribute("msg","账号密码为空");
            return "redirect:/login";
        }
        else {
                String verify = request.getParameter("verify");
                String verifyCode = (String) session.getAttribute("verifyCode");
                System.out.println("验证码：" + verify + "  答案:" + verifyCode);

                if(verify.equals(verifyCode)){
                    if(pass.equals(getpass)){
                        session.setAttribute("studentId",studentId);
                        session.setAttribute("password",getpass);
                        session.setAttribute("realname",userService.getRealName(studentId,getpass));
                        System.out.println("真实姓名：" + session.getAttribute("realname"));
                        return "welcome";
                    }
                    else{
                        System.out.println("error");
                        return "redirect:/login";
                    }
                }
                else{
                    System.out.println("验证码错误");
                    model.addAttribute("msg","验证码错误");
                    return "redirect:/login";
                }
        }
    }

    @RequestMapping("/goregister")
    public String register(HttpServletRequest request,HttpSession session){
        String verify = request.getParameter("verify");
        String verifyCode = (String) session.getAttribute("verifyCode");
        if(verify.equals(verifyCode)){
            String studentId = request.getParameter("studentId");
            String realname = request.getParameter("realname");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");
            String isHadUser = userService.getRealNameById(studentId);
            System.out.println(isHadUser);
            if(password1.equals(password2))  {
                if(isHadUser == null){
                    User_s user = new User_s(studentId,realname,password1);
                    userService.register(user);
                    System.out.println("插入成功");
                    return "login";
                }
                else {
                    System.out.println("此用户已经存在");
                    return "redirect:/register";
                }
            }
            else {
                System.out.println("两次密码输入不正确！");
                return "redirect:/register";
            }
        }
        else {
            System.out.println("验证码错误");
            return "redirect:/register";
        }
    }

    @ResponseBody
    @RequestMapping("show")
    public String show(){
        return "hello baby";
    }



}
