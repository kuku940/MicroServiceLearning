package cn.xiaoyu.instrumetation.work;

import io.prometheus.client.Gauge;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class JobQueue {

    private final Gauge jobQueueSize = Gauge.build()
            .name("job_queue_size")
            .help("Current number of jobs waiting in queue")
            .register();

    private Queue<Job> queue = new LinkedBlockingQueue<>();

    public int size() {
        return queue.size();
    }

    public void push(Job job) {
        queue.offer(job);
        jobQueueSize.inc();
    }

    public Job pull() {
        Job job = queue.poll();
        if (job != null) {
            jobQueueSize.dec();
        }
        return job;
    }
}
