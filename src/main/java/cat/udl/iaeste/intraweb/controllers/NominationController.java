package cat.udl.iaeste.intraweb.controllers;

import cat.udl.iaeste.intraweb.models.Nomination;
import cat.udl.iaeste.intraweb.models.Nomination;
import cat.udl.iaeste.intraweb.repositories.NominationRepository;
import cat.udl.iaeste.intraweb.repositories.NominationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Preconditions;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
* Created by q on 07/05/15.
*/

@Controller
@RequestMapping("/nominations")
public class NominationController {

    @Autowired
    NominationRepository nominationRepository;

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Nomination> list(@RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page, size);
        return nominationRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHtml(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size) {
        return new ModelAndView("nominations", "nominations", list(page, size));
    }


    // RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Nomination retrieve(@PathVariable("id") Long id) {
        Preconditions.checkNotNull(nominationRepository.findOne(id), "Nomination with id %s not found", id);
        return nominationRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "id" ) Long id) {
        return new ModelAndView("nomination", "nomination", retrieve(id));
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Nomination create(@Valid @RequestBody Nomination nomination, HttpServletResponse response) {
        return nominationRepository.save(nomination);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String createHtml(@Valid @ModelAttribute("nomination") Nomination nomination, BindingResult binding, HttpServletResponse response) {
        if(binding.hasErrors()) {
            return "nominationForm";
        }
        return "redirect:/api/nominations/" + create(nomination, response).getId();
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        Nomination emptyNomination = new Nomination();
        return new ModelAndView("nominationForm", "nomination", emptyNomination);
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Nomination update(@PathVariable("id") Long id, @Valid @RequestBody Nomination nomination) {
        Nomination oldNomination = nominationRepository.findOne(id);
        oldNomination.setNominated(nomination.getNominated());
        oldNomination.setWorkOffer(nomination.getWorkOffer());
        oldNomination.setOfficerDelegate(nomination.getOfficerDelegate());
        oldNomination.setIsAccepted(nomination.getIsAccepted());
        return nominationRepository.save(oldNomination);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long id, @Valid @ModelAttribute("nomination") Nomination nomination,
                             BindingResult binding) {
        if (binding.hasErrors()) {
            return "nominationForm";
        }
        return "redirect:/api/nominations/" + update(id, nomination).getId();
    }

    // Update form
    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        return new ModelAndView("nominationForm", "nomination", nominationRepository.findOne(id));
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        nominationRepository.delete(nominationRepository.findOne(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {
        delete(id);
        return "redirect:/api/nominations";
    }
}
