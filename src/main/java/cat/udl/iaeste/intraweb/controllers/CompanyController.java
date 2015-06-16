package cat.udl.iaeste.intraweb.controllers;

import cat.udl.iaeste.intraweb.models.Company;
import cat.udl.iaeste.intraweb.repositories.CompanyRepository;
import cat.udl.iaeste.intraweb.services.XQueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eduard on 27/04/15.
 */
@Controller
@RequestMapping("/companies")
public class CompanyController {
    final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    CompanyRepository companyRepository;

    // LIST
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Company> list(@RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size) {
        PageRequest request = new PageRequest(page, size);
        return companyRepository.findAll(request).getContent();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView listHtml(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size) {
        return new ModelAndView("companies", "companies", list(page, size));
    }

    // RETRIEVE
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Company retrieve(@PathVariable("id") Long id) {
        Preconditions.checkNotNull(companyRepository.findOne(id), "Company with id %s not found", id);
        return companyRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView retrieveHTML(@PathVariable("id") Long id) {
        return new ModelAndView("company", "company", retrieve(id));
    }

    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Company> find(@PathVariable("name") String name) {
        Preconditions.checkNotNull(companyRepository.findByNameContainingIgnoreCase(name), "Company with name %s not found", name);
        return companyRepository.findByNameContainingIgnoreCase(name);
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Company create(@Valid @RequestBody Company company, HttpServletResponse response) {
        return companyRepository.save(company);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String createHtml(@Valid @ModelAttribute("company") Company company, BindingResult binding, HttpServletResponse response) {
        if (binding.hasErrors()) {
            return "companyForm";
        }
        return "redirect:/api/companies/" + create(company, response).getId();
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView createForm() {
        Company emptyCompany = new Company();
        return new ModelAndView("companyForm", "company", emptyCompany);
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Company update(@PathVariable("id") Long id, @Valid @RequestBody Company company) {
        Company oldCompany = companyRepository.findOne(id);
        oldCompany.setName(company.getName());
        oldCompany.setHeadquarters(company.getHeadquarters());
        oldCompany.setWorkPlace(company.getWorkPlace());
        return companyRepository.save(oldCompany);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public String updateHTML(@PathVariable("id") Long id, @Valid @ModelAttribute("company") Company company,
                             BindingResult binding) {
        if (binding.hasErrors()) {
            return "companyForm";
        }
        return "redirect:/api/companies/" + update(id, company).getId();
    }

    // Update form
    @RequestMapping(value = "/{id}/form", method = RequestMethod.GET, produces = "text/html")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        return new ModelAndView("companyForm", "company", companyRepository.findOne(id));
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        companyRepository.delete(companyRepository.findOne(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHTML(@PathVariable("id") Long id) {
        delete(id);
        return "redirect:/api/companies";
    }

    // SEARCH
    @RequestMapping(value = "/results", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView search(@RequestParam(required=true) String name)throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        logger.info("film search with content'{}'", name);
        List<Company> c = new ArrayList<>();
        if(companyRepository.findCompanyByName(name) == null){
            XQueryHelper x = new XQueryHelper(name);
            c = x.getCompanies();
            List<Company> supportList = new ArrayList<>();
            for (Company elem : c) {
                if (companyRepository.findCompanyByName(elem.getName()) != null) {
                    name = elem.getName();
                    supportList.add(elem);
                }
            }
            for (Company elem : supportList) {
                c.remove(elem);
            }
            if (c.isEmpty()) {
                Company company = companyRepository.findCompanyByName(name);
                if (company != null) {
                    return new ModelAndView("companyForm", "company", companyRepository.findCompanyByName(name));
                }
            }
            Map model = new HashMap<String, Object>();
            model.put("results", c);
            return new ModelAndView("companyResults", model);
        } else {
            return new ModelAndView("companyForm", "company", companyRepository.findCompanyByName(name));
        }
    }

    @RequestMapping(value = "/results/json",method = RequestMethod.GET)
    @ResponseBody
    public List<Company> searchJson(@RequestParam(required=true) String name)throws  ClassNotFoundException, InstantiationException, IllegalAccessException {
        logger.info("company search with content'{}'", name);
        List<Company> companyList;
        XQueryHelper x = new XQueryHelper(name);
        companyList = x.getCompanies();
        return companyList;
    }

}
