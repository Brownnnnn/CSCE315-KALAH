package kalahProject;

import java.util.Observable;

/**
 * Created by jhinchley on 3/14/17.
 */
public interface Observer {
    void update(Observable observable, Object object);
}