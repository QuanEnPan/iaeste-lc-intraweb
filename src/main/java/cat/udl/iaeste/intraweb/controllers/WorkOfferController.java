package cat.udl.iaeste.intraweb.controllers;

import cat.udl.iaeste.intraweb.models.WorkOffer;
import cat.udl.iaeste.intraweb.repositories.WorkOfferRepository;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by q on 05/05/15.
 */

@Controller
@RequestMapping("/workOffers")
public class WorkOfferController {

    @Autowired
    WorkOfferRepository workOfferRepository;

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<WorkOffer> list(@RequestParam(required = false, defaultValue = "0") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page, size);
        return workOfferRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHtml(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size) {
        return new ModelAndView("workOffers", "workOffers", list(page, size));
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public WorkOffer create(@Valid @RequestBody WorkOffer workOffer, HttpServletResponse response) {
        return workOfferRepository.save(workOffer);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String createHtml(@Valid @ModelAttribute("workOffer") WorkOffer workOffer, BindingResult binding, HttpServletResponse response) {
        if (binding.hasErrors()) {
            return "workOfferForm";
        }
        return "redirect:/api/workOffers/" + create(workOffer, response).getId();
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        WorkOffer emptyWorkOffer = new WorkOffer();
        return new ModelAndView("workOfferForm", "workOffer", emptyWorkOffer);
    }

    // RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public WorkOffer retrieve(@PathVariable("id") Long id) {
        Preconditions.checkNotNull(workOfferRepository.findOne(id), "Work Offer with id %s not found", id);
        return workOfferRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable("id") Long id) {
        return new ModelAndView("workOffer", "workOffer", retrieve(id));
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WorkOffer update(@PathVariable("id") Long id, @Valid @RequestBody WorkOffer workOffer) {
        WorkOffer oldWorkOffer = workOfferRepository.findOne(id);
        oldWorkOffer.setCountry(workOffer.getCountry());
        oldWorkOffer.setMaxNumWeeks(workOffer.getMaxNumWeeks());
        oldWorkOffer.setMinNumWeeks(workOffer.getMinNumWeeks());
        return workOfferRepository.save(oldWorkOffer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long id, @Valid @ModelAttribute("workOffer") WorkOffer workOffer,
                             BindingResult binding) {
        if (binding.hasErrors()) {
            return "workOfferForm";
        }
        return "redirect:/api/workOffers/" + update(id, workOffer).getId();
    }

    // Update form
    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        return new ModelAndView("workOfferForm", "workOffer", workOfferRepository.findOne(id));
    }


}
