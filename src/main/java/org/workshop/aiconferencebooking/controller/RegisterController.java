package org.workshop.aiconferencebooking.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.workshop.aiconferencebooking.exception.EmailTakenException;
import org.workshop.aiconferencebooking.exception.UsernameTakenException;
import org.workshop.aiconferencebooking.model.Person;
import org.workshop.aiconferencebooking.service.PersonService;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final PersonService personService;

    @Autowired
    public RegisterController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping
    public String register(@Valid Person person, BindingResult result, Model model, HttpServletRequest request) {
        String password = person.getPassword();
        String passwordConfirm = person.getPasswordConfirm();

        if (password.isEmpty()) {
            result.rejectValue("password", null, "Password cannot be empty");
        }

        if (passwordConfirm.isEmpty()) {
            result.rejectValue("passwordConfirm", null, "Password confirm cannot be empty");
        }

        if (!password.equals(passwordConfirm)) {
            result.rejectValue("passwordConfirm", null, "Password does not match");
        }

        boolean hasError = result.hasErrors();

        if (!hasError) {
            try {
                personService.registerNewPerson(person);
            } catch (EmailTakenException e) {
                hasError = true;
                result.rejectValue("email", "", "Email is already taken");
            } catch (UsernameTakenException e) {
                hasError = true;
                result.rejectValue("username", "", "Username is already taken");
            }
        }

        if (hasError) {
            model.addAttribute("person", person);
            return "register";
        }

        return "redirect:/";
    }

}