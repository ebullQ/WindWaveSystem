package org.mkukharchuk.service.impl;

import org.mkukharchuk.dao.WindDAO;
import org.mkukharchuk.model.Wind;
import org.mkukharchuk.service.WindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WindServiceImpl implements WindService {
    @Autowired
    private WindDAO dao;

    @Override
    public Wind getLastWind() {
        return dao.getLastWind();
    }

    @Override
    @Transactional
    public void saveWind(Wind wind) {
        dao.saveWind(wind);
    }
}
