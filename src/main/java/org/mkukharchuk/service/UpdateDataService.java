package org.mkukharchuk.service;

import org.mkukharchuk.util.data.DataParser;
import org.mkukharchuk.util.data.DataWriter;
import org.mkukharchuk.util.data.ProcessExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateDataService {
    @Autowired
    private DataParser dataParser;
    @Autowired
    private DataWriter dataWriter;
    @Autowired
    private ProcessExecutor executor;

    public void updateData(){
        dataParser.getValues();
        dataWriter.writeData();
        executor.executeProcess();
    }


}
