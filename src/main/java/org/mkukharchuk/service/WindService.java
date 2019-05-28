package org.mkukharchuk.service;

import org.mkukharchuk.model.Wind;

public interface WindService {
    Wind getLastWind();
    void saveWind(Wind wind);
}
