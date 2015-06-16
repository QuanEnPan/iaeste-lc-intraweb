package cat.udl.iaeste.intraweb.models;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by eduard on 23/03/15.
 */
@Entity
public class Delegate extends User {

    public Delegate() {
    }

    public Delegate(String username) {
        super(username);
    }
}
