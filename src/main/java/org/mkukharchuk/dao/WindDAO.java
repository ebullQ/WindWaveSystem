package org.mkukharchuk.dao;

import org.mkukharchuk.model.Wind;

public interface WindDAO {
    Wind getLastWind();
    void saveWind(Wind wind);
}
