package com.leffycruze.doodle.controller.api;

import com.leffycruze.doodle.entity.Doodle;
import com.leffycruze.doodle.entity.Proposition;
import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.service.DoodleService;
import com.leffycruze.doodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/doodle")
public class DoodleController {

    @Autowired
    private DoodleService doodleService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Doodle create(@RequestBody Map<String, Object> request, HttpServletRequest httpRequest) {
        Integer userId = (Integer) httpRequest.getAttribute("userId");
        String title = (String) request.get("title");
        String place = (String) request.get("place");
        List<Map<String, Object>> props = (List<Map<String, Object>>) request.get("propositions");
        Optional<User> user = userService.findById(userId);

        ArrayList<Proposition> propositions = new ArrayList<>();
        for (Map<String, Object> prop: props){
            propositions.add(new Proposition((String) prop.get("date"),(String)  prop.get("start"),(String)  prop.get("finish")));
        }

        return doodleService.create(title, place, propositions, user.get());
    }
}
