package cz.muni.fi.pv243.ars.batching;

import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchStatus;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * Created by mminatova on 6/25/18.
 */
@Named("ARSBatchletCommentsRemoval")
public class ARSBatchletCommentsRemoval implements Batchlet {


    @Inject
    private ARSJob arsJob;

    @Override
    public String process() throws Exception {
        int days = 7;
        arsJob.removeComments(days);
        return BatchStatus.COMPLETED.toString();
    }

    @Override
    public void stop() throws Exception {

    }
}
