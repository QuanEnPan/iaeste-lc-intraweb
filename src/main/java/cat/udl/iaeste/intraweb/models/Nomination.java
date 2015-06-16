package cat.udl.iaeste.intraweb.models;


import org.hibernate.validator.constraints.NotEmpty;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

/**
 * Created by eduard on 20/04/15.
 */
@Entity
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty
    @ManyToOne
    private User nominated;

    @NotEmpty
    @ManyToOne
    private WorkOffer workOffer;

    @NotEmpty
    @ManyToOne
    private OfficerDelegate officerDelegate;

    @NotEmpty
    private Boolean isAccepted = false;

    public Nomination() {}

    public Long getId() {
        return Id;
    }

    public User getNominated() {
        return nominated;
    }

    public void setNominated(User nominated) {
        this.nominated = nominated;
    }

    public WorkOffer getWorkOffer() {
        return workOffer;
    }

    public void setWorkOffer(WorkOffer workOffer) {
        this.workOffer = workOffer;
    }

    public OfficerDelegate getOfficerDelegate() {
        return officerDelegate;
    }

    public void setOfficerDelegate(OfficerDelegate officerDelegate) {
        this.officerDelegate = officerDelegate;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}
