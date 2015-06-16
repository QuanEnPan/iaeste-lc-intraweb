package cat.udl.iaeste.intraweb.repositories;

import cat.udl.iaeste.intraweb.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by eduard on 23/03/15.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyByName(@Param("name") String name);

    Iterable<Company> findByNameContainingIgnoreCase(String name);

}
