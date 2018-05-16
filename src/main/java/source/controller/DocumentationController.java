package source.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController()
@RequestMapping("")
public class DocumentationController {

    @GetMapping(path = {"/docs"})
    public RedirectView docs(){
        return new RedirectView("/swagger-ui.html");
    }
}