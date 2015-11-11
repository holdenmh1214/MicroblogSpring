package com.theironyard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by holdenhughes on 11/9/15.
 */

@Controller
public class MBController {

    @Autowired
    MessageRepository messages;


    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("messages", messages.findAll());// Don't forget this!
        return "home";
    }

    @RequestMapping("/edit")
    public String edit(Model model, Integer id){
        Message message = messages.findOne(id);
        model.addAttribute("message", message);
        return "edit";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username){
        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        return "redirect:/";
    }

    @RequestMapping("/add-message")
    public String message(String text){
        Message message = new Message();
        message.text=text;
        messages.save(message);
        return "redirect:/";
    }


    @RequestMapping("/delete-message")
    public String delete(Integer id){
        messages.delete(id);
        return ("redirect:/");
    }

    @RequestMapping("/edit-message")
    public String editMessage(Integer id, String text){
        Message message = messages.findOne(id);
        if (message!=null){
            message.text = text;
        }
        messages.save(message);
        return "redirect:/";
    }

}
