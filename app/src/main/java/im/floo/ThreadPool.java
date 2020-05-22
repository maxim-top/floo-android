
package im.floo;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description : 线程池
 */
public final class ThreadPool {

    /* 线程保活时间60s */
    private static final int KEEP_ALIVE_TIME = 60;

    private static final Handler sMainHandler;

    private static ThreadPoolExecutor mThreadPoolExecutor;

    static {
        int coreThreadCount = Runtime.getRuntime().availableProcessors();
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        mThreadPoolExecutor = new ThreadPoolExecutor(coreThreadCount, coreThreadCount + 1,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, new DefaultThreadFactory());
        mThreadPoolExecutor.allowCoreThreadTimeOut(true);
        sMainHandler = new Handler(Looper.getMainLooper());
    }

    public static void exec(Runnable task) {
        if (null != task) {
            mThreadPoolExecutor.execute(task);
        }
    }

    public static void postMain(Runnable task) {
        if (null != task) {
            sMainHandler.post(task);
        }
    }

    private static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);

        private final ThreadGroup group;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "ThreadPool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
