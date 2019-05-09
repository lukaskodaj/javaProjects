package lukaskodaj.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.SessionAttribute;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpSession session;


    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {

            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
                SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
                sessionCookieConfig.setHttpOnly(true);
            }
        };

    }

    @RequestMapping("/")
    public String initPage(User user) {

        if (session.getAttribute("email") != null) {
            return "redirect:/my-notes";
        }

        return "sign-in";
    }

    //    @SessionAttribute('username', )
    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public String signin(User user, Model model) {

        if (session.getAttribute("email") != null) {
            return "redirect:/my-notes";
        }

        List<User> foundUsers = (List<User>) repository.findUserByEmail(user.getEmail());

        if (foundUsers.size() > 0) {

            for (User foundUser : foundUsers) {

                boolean result = PasswordUtility.verifyUserPassword(user.getPassword(), foundUser.getPassword(), foundUser.getSalt());


                if (foundUser.getEmail().equals(user.getEmail()) && PasswordUtility.verifyUserPassword(user.getPassword(), foundUser.getPassword(), foundUser.getSalt()) == true) {

                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("userId", user.getId());

                    return "redirect:/my-notes";
                }
            }

            model.addAttribute("message", "Incorrect email or password.");
            model.addAttribute("messageType", "alert-danger");

            return "sign-in";

        } else {
            return "sign-in";
        }


    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long userId, Model model) {
        repository.deleteById(userId);
        return "users-crud";
    }

    @RequestMapping(value = "/sign-up")
    public String signUp(User user) {

        if (session.getAttribute("email") != null) {
            return "redirect:/my-notes";
        }

        return "sign-up";
    }

    @RequestMapping(value = "/sign-in")
    public String signIn(User user) {

        if (session.getAttribute("email") != null) {
            return "redirect:/my-notes";
        }

        return "sign-in";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(User user) {

        if (session.getAttribute("email") != null) {
            return "redirect:/my-notes";
        }

        String salt = PasswordUtility.getSalt(30);

        user.setSalt(salt);
        user.setPassword(PasswordUtility.generateSecurePassword(user.getPassword(), salt));

        repository.save(user);
        return "sign-in";
    }

    @RequestMapping(value = "/sign-out")
    public String signOut(User user, Model model) {

        session.setAttribute("email", null);
        session.setAttribute("userId", null);

        model.addAttribute("message", "You have been sign out.");
        model.addAttribute("messageType", "alert-success");

        return "sign-in";
    }


}
