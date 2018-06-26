package cz.muni.fi.pv243.ars.batching;

import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchStatus;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * Created by mminatova on 6/24/18.
 */
@Named("ARSBatchletReport")
public class ARSBatchletReport implements Batchlet {


    @Inject
    private ARSJob arsJob;

    @Override
    public String process() throws Exception {
        arsJob.generateReport();
        return BatchStatus.COMPLETED.toString();
    }

    @Override
    public void stop() throws Exception {

    }
}
