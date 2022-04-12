package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import project.models.User;

import java.util.List;

@Controller
//@RequestMapping(value = "/signUp" , method = RequestMethod.GET)
public class SignUpController {
//    @Override
//    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("registration.jsp");
////       modelAndView.addObject("user",)
//        return modelAndView;
//    }

@RequestMapping(value = "/signUp" , method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
//        List<User> users = usersService.getUsers();
//        modelAndView.addObject("users", users);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

//public signUp()
//    @RequestMapping("/hello")
//    public  String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }
}
