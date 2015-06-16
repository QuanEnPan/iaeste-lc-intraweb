package cat.udl.iaeste.intraweb.models;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by eduard on 20/04/15.
 */
@Entity
public class SummerDelegate extends Delegate {

    public SummerDelegate() {
    }

    public SummerDelegate(String username) {
        super(username);
    }
}
