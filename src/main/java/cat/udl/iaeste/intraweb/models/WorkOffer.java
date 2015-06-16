package cat.udl.iaeste.intraweb.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by eduard on 20/04/15.
 */
@Entity
public class WorkOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    private int minNumWeeks;

    @NotNull
    private int maxNumWeeks;

    @NotBlank(message = "This field cannot be blank")
    private String country;

    public WorkOffer() {
    }

    public Long getId() {
        return Id;
    }

    public int getMinNumWeeks() {
        return minNumWeeks;
    }

    public void setMinNumWeeks(int minNumWeeks) {
        this.minNumWeeks = minNumWeeks;
    }

    public int getMaxNumWeeks() {
        return maxNumWeeks;
    }

    public void setMaxNumWeeks(int maxNumWeeks) {
        this.maxNumWeeks = maxNumWeeks;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
