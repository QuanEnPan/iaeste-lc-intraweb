package cat.udl.iaeste.intraweb.models;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by eduard on 20/04/15.
 */
@Entity
public class OfficerDelegate extends Delegate {

    public OfficerDelegate() {
    }

    public OfficerDelegate(String username) {
        super(username);
    }

}
