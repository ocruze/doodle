package com.leffycruze.doodle.controller.api;

import com.leffycruze.doodle.entity.Doodle;
import com.leffycruze.doodle.entity.Proposition;
import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.exception.apirequestexception.BadParameterException;
import com.leffycruze.doodle.exception.apirequestexception.ResourceForbiddenException;
import com.leffycruze.doodle.service.DoodleService;
import com.leffycruze.doodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        String username = (String) httpRequest.getAttribute("username");
        String title = (String) request.get("title");
        String place = (String) request.get("place");
        List<Map<String, Object>> props = (List<Map<String, Object>>) request.get("propositions");
        User user = userService.findByUsername(username);

        if (props == null) {
            throw new BadParameterException("Please provide at least one proposition");
        }
        ArrayList<Proposition> propositions = new ArrayList<>();
        for (Map<String, Object> prop : props) {
            propositions.add(new Proposition((String) prop.get("date"), (String) prop.get("start"),
                    (String) prop.get("finish")));
        }

        return doodleService.create(title, place, propositions, user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doodle> getDoodle(@PathVariable(name = "id") String idDoodle) {
        try {
            Integer idInt = Integer.parseInt(idDoodle);
            Optional<Doodle> doodle = doodleService.findById(idInt);
            if (doodle.isEmpty())
                throw new ResourceForbiddenException("Doodle=" + idDoodle + " not found");

            return ResponseEntity.ok().body(doodle.get());

        } catch (NumberFormatException e) {
            throw new BadParameterException("Invalid id");
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getDoodles(HttpServletRequest httpRequest) {
        String username = (String) httpRequest.getAttribute("username");
        User organizer = userService.findByUsername(username);

        List<Doodle> doodleList = doodleService.getDoodlesByOrganizer(organizer);

        return ResponseEntity.ok(doodleList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String idDoodle, HttpServletRequest httpRequest) {
        String username = (String) httpRequest.getAttribute("username");

        Optional<Doodle> doodle = doodleService.findById(Integer.parseInt(idDoodle));
        if (doodle.isEmpty()) {
            throw new BadParameterException(String.format("idDoodle=%s does not exist", idDoodle));
        }

        User user = userService.findByUsername(username);
        if (!doodle.get().getOrganizer().equals(user)) {
            throw new ResourceForbiddenException("You are not the organizer of idDoodle=" + idDoodle);
        }

        doodleService.delete(doodle.get());
        return ResponseEntity.noContent().build();
    }
}
