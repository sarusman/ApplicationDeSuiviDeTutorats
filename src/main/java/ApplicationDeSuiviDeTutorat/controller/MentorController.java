package ApplicationDeSuiviDeTutorat.controller;

import ApplicationDeSuiviDeTutorat.model.Mentor;
import ApplicationDeSuiviDeTutorat.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Controller
public class MentorController
{

    @Autowired
    private MentorService mentorService;

    public MentorController(MentorService mentorService) { this.mentorService = mentorService;}

    @GetMapping("/{id}")
    public String getMentorById(@PathVariable("id") Integer id, Model model) {
        Optional<Mentor> mentor = mentorService.getMentorById(id);
        model.addAttribute("mentor", mentor.orElse(null));
        return "mentorDetails";
    }

    @PutMapping("/{id}")
    public String updateMentorById(@PathVariable Integer id,@ModelAttribute Mentor updatedMentor){
        mentorService.updateMentorById(id, updatedMentor);
        return "redirect:/mentorDetails/" + id;
    }


}
