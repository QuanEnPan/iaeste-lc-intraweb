package cat.udl.iaeste.intraweb.repositories;

import cat.udl.iaeste.intraweb.models.User;
import cat.udl.iaeste.intraweb.models.WorkOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by eduard on 23/03/15.
 */
public interface WorkOfferRepository extends JpaRepository<WorkOffer, Long> {

    List<WorkOffer> findWorkOffersByCountry(@Param("country") String country);

}
