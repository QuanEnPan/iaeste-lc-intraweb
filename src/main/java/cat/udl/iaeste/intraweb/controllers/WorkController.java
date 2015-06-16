package cat.udl.iaeste.intraweb.controllers;

import cat.udl.iaeste.intraweb.models.Work;
import cat.udl.iaeste.intraweb.repositories.WorkRepository;
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
 * Created by eduard on 27/04/15.
 */
@Controller
@RequestMapping("/works")
public class WorkController {

    @Autowired
    WorkRepository workRepository;

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Work> list(@RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page, size);
        return workRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHtml(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size) {
        return new ModelAndView("works", "work", list(page, size));
    }

    // RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Work retrieve(@PathVariable("id") Long id) {
        Preconditions.checkNotNull(workRepository.findOne(id), "Work with id %s not found", id);
        return workRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable( "id" ) Long id) {
        return new ModelAndView("work", "work", retrieve(id));
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Work create(@Valid @RequestBody Work work, HttpServletResponse response) {
        return workRepository.save(work);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String createHtml(@Valid @ModelAttribute("work") Work work, BindingResult binding, HttpServletResponse response) {
        if(binding.hasErrors()) {
            return "workForm";
        }
        return "redirect:/api/works/" + create(work, response).getId();
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        Work emptyWork = new Work();
        return new ModelAndView("workForm", "work", emptyWork);
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Work update(@PathVariable("id") Long id, @Valid @RequestBody Work work) {
        Work oldWork = workRepository.findOne(id);
        oldWork.setFinished(work.isFinished());
        oldWork.setPoints(work.getPoints());
        oldWork.setWorker(work.getWorker());
        return workRepository.save(oldWork);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long id, @Valid @ModelAttribute("work") Work work,
                             BindingResult binding) {
        if (binding.hasErrors()) {
            return "workForm";
        }
        return "redirect:/api/works/" + update(id, work).getId();
    }

    // Update form
    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        return new ModelAndView("workForm", "work", workRepository.findOne(id));
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        workRepository.delete(workRepository.findOne(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {
        delete(id);
        return "redirect:/api/works";
    }
}
