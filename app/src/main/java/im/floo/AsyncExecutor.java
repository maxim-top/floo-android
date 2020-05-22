package im.floo;

import android.os.AsyncTask;

import im.floo.floolib.BMXErrorCode;

public class AsyncExecutor {

    public void exec(final Task task) {
        if (task == null) {
            return;
        }
        ThreadPool.exec(new Runnable() {
            @Override
            public void run() {
                final BMXErrorCode error = task.exec();
                ThreadPool.postMain(new Runnable() {
                    @Override
                    public void run() {
                        task.onPostExecute(error);
                    }
                });
            }
        });
    }

    public void exec(final SimpleTask task){
        if (task == null) {
            return;
        }
        ThreadPool.exec(new Runnable() {
            @Override
            public void run() {
                task.exec();
            }
        });
    }
    public void exec1(final Task task, final BMXCallBack callback) {
        if (task == null) {
            return;
        }
        new AsyncTask<Void, Void, BMXErrorCode>() {
            @Override
            protected BMXErrorCode doInBackground(Void... arg0) {
                return task.exec();
            }

            @Override
            protected void onPostExecute(BMXErrorCode result) {
                task.onPostExecute(result);
            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public interface Task {
        BMXErrorCode exec();
        void onPostExecute(BMXErrorCode code);
    }

    public interface SimpleTask{
        void exec();
    }
}
