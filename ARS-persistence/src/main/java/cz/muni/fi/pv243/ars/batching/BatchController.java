package cz.muni.fi.pv243.ars.batching;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by mminatova on 6/24/18.
 */
@Singleton
public class BatchController {

    @Inject
    private Logger log;

    @Schedule(hour = "23", minute = "59", second = "59", persistent = false)
    public void startJob() {
        BatchRuntime.getJobOperator().start("ARS-job", new Properties());
    }

}
