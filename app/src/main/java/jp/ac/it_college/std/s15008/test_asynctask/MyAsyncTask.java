package jp.ac.it_college.std.s15008.test_asynctask;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by s15008 on 17/01/25.
 */

public class MyAsyncTask extends AsyncTask<String, Integer, Long> implements DialogInterface.OnCancelListener {
    private static final String TAG = "MyAsyncTask";
    ProgressDialog dialog;
    Context context;

    public MyAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Please wait");
        dialog.setMessage("Please wait");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(this);
        dialog.setMax(100);
        dialog.setProgress(0);
        dialog.show();
    }

    @Override
    protected Long doInBackground(String... strings) {
        try {
            for (int i = 0; i < 10; i++) {
                if (isCancelled()) {
                    Log.d(TAG, "Cancelled");
                    break;
                }
                Thread.sleep(1000);
                publishProgress((i + 1) * 10);
            }
        } catch (InterruptedException e) {
            Log.d(TAG, e.getMessage());
        }

        return 123L;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        dialog.setProgress(values[0]);
    }

    @Override
    protected void onCancelled() {
        dialog.dismiss();
    }

    @Override
    protected void onPostExecute(Long result) {
        dialog.dismiss();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        cancel(true);
    }
}
