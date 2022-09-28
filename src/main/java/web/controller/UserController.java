package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id){
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }


    // Метод отвечающий за то, что отображается в html странице
    //@RequestMapping("/users")
    //public String printUsers(@RequestParam)
}
/*
    Контроллер - это класс который дает ссылку на html страницу
	В контроллере В ОСНОВНОМ находятся разные методы и каждый метод соответствует одному url, и возвращают они В ОСНОВНОМ
	строку String с названием представления html которое надо показывать пользователю. Могут возвращать json
*/


    // Метод отвечающий за то, что отображается в html странице
    //@GetMapping(value = "/cars") // при переходе в браузере на "/cars" мы попадем на метод printCars и он вернет cars.html
    //public String printCars(@RequestParam(name = "count", required = false, defaultValue = "5") int count, ModelMap model) {
    //    model.addAttribute("cars", carService.getCarsList(count));
    //    return "cars";
        // required=false - значит в get запросе будет лежать null в случае, если нет второго параметра, чтобы не было 404
   // }
//}
