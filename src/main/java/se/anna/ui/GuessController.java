package se.anna.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.anna.business.GuessService;


@Controller
public class GuessController {
	
	
	@Autowired
    GuessService gissaService;

    @PostMapping("/login")
    public String login(@RequestParam("playername") String pname, Model m) {
        gissaService.login(pname);
        return "guesspage";
    }


     
     @PostMapping("/guess")
     public String fillForm(@RequestParam("guessing") int gissa, Model m) {
    	 		String svar = gissaService.guess(gissa);
    	 		m.addAttribute("reply", svar);
    	 		return "guesspage";
     }

     @GetMapping("/toplist")
    public String getTopList(Model m){
        m.addAttribute("toplist", gissaService.getTopList());
        return "toppage";
     }

}
