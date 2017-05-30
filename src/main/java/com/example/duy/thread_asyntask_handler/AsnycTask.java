package com.example.duy.thread_asyntask_handler;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by duy on 29/05/2017.
 */

public class AsnycTask extends AsyncTask<Void , Integer ,Void> {
    private Activity activity;

    public AsnycTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() { // hàm khởi tạo khi bắt đầu tiến
        super.onPreExecute();
        Toast.makeText(activity,"Start",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(int i = 0;i<=100;i++){
            SystemClock.sleep(500);
            publishProgress(i);
        }
        return null;
    }
    // hàm gọi khi kết thúc tiến
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(activity,"Game Over",Toast.LENGTH_SHORT).show();

}
    //cập nhật giao diện lúc run time
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //đối số truyền vào values là một giá trị integer nên chỉ có 1 phần tử
        int percent = values[0];
        //set giá trị phần trăm cho progres bar
        ((ProgressBar)activity.findViewById(R.id.id_pro)).setProgress(percent);
        //set gia trị phần trăm cho Text view
        ((TextView)activity.findViewById(R.id.id_async)).setText(percent+"%");
    }
}
