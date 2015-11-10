package com.theironyard;
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

    ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("messages", messages);// Don't forget this!
        return "home";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username){
        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        return "redirect:/";
    }

    @RequestMapping("/add-message")
    public String message(String text){
        int id = messages.size()+1;
        Message message = new Message(id, text);
        messages.add(message);
        return "redirect:/";
    }



    @RequestMapping("/delete-message")
    public String delete(Integer id){
        messages.remove(id-1);
        for (int i=0; i< messages.size(); i++){
            messages.get(i).id = i+1;
        }
        return ("redirect:/");
    }

}
