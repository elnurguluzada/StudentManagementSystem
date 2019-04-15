package az.edu.bsu.smsproject.controller;

import az.edu.bsu.smsproject.Service.RoleService;
import az.edu.bsu.smsproject.Service.UserService;
import az.edu.bsu.smsproject.domain.Role;
import az.edu.bsu.smsproject.domain.Student;
import az.edu.bsu.smsproject.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("user")
public class LoginController {

    private UserService userService;
    private RoleService roleService;
// First adds "user" attribute to the session and in the login method initializes it,
// if user isn't authenticated, cleans up session
    @ModelAttribute("user")
    public User addUserToSession(){
        User user = null;
        return user;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam(name="email") String email,
            @RequestParam(name="pass") String password,
            final RedirectAttributes redirectAttributes,
            @ModelAttribute(name="user") User user,
            SessionStatus sessionStatus
    ){

        String page = null;
        if ( email != null && password != null ){
            user = userService.authenticate( email, password );

            if ( user != null ){
                Role role = roleService.getRoleById( user.getRoleId() );
                String defaultController = role.getDefaultController();
                page = "redirect:/"+defaultController+"/";
            }
            else {
                sessionStatus.setComplete();
                redirectAttributes.addFlashAttribute( "errorMessage", "Email ve ya sifre yanlisdir" );
                page = "redirect:/login.jsp";
            }

        }

        return page;
    }


}
