package org.mkukharchuk.config;

import org.mkukharchuk.service.ImageCreatorService;
import org.mkukharchuk.service.UpdateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleCfg {
    @Autowired
    ImageCreatorService imageService;

    @Autowired
    UpdateDataService dataService;

    @Scheduled(fixedRate = 2000000)
    public void scheduleFixedDelayTask() {
        dataService.executeProcess();
        imageService.writeImage();
    }
}
