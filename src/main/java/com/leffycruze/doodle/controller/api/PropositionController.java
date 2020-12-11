package com.leffycruze.doodle.controller.api;

import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.exception.apirequestexception.BadRequestException;
import com.leffycruze.doodle.service.DoodleService;
import com.leffycruze.doodle.service.PropositionService;
import com.leffycruze.doodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/prop")
public class PropositionController {

    @Autowired
    private DoodleService doodleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PropositionService propositionService;

    @PatchMapping("/{id}/vote/{going}") // yes, no
    public ResponseEntity<?> vote(@PathVariable(name = "id") String idPropSt,
            @PathVariable(name = "going") String going, HttpServletRequest httpRequest) {
        String username = (String) httpRequest.getAttribute("username");
        User user = userService.findByUsername(username);
        Integer idProp = Integer.parseInt(idPropSt);

        if (going.equals("yes")) {
            propositionService.voteGoing(idProp, user);
        } else if (going.equals("no")) {
            propositionService.voteNotGoing(idProp, user);
        } else {
            throw new BadRequestException("Only [yes, no] values are accepted");
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/vote")
    public ResponseEntity<?> voteDelete(@PathVariable(name = "id") String idPropSt, HttpServletRequest httpRequest) {
        String username = (String) httpRequest.getAttribute("username");
        User user = userService.findByUsername(username);
        Integer idProp = Integer.parseInt(idPropSt);

        propositionService.voteDelete(idProp, user);

        return ResponseEntity.noContent().build();
    }
}
