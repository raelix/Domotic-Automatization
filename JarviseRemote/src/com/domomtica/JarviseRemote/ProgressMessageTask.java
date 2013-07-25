package com.domomtica.JarviseRemote;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;


public class ProgressMessageTask extends AsyncTask<String, String, String> {
	    String toastMessage;

	    @Override
	    protected String doInBackground(String... params) {
	        toastMessage = params[0];
	        return toastMessage;
	    }

	    protected void OnProgressUpdate(String... values) { 
	        super.onProgressUpdate(values);
	    }
	   // This is executed in the context of the main GUI thread
	    protected void onPostExecute(String result){
	    	if (result.length()<=0) result = "Contatto il Server..Attendere..";
	    	final	ProgressDialog  dialogo = ProgressDialog.show(AllControlli.thisActivity, "Connessione...",result , true);
			Handler handler = new Handler();
			dialogo.show();
			handler.postDelayed(new Runnable() {
				public void run() {
					dialogo.dismiss();
				}   
			}, 3000);
	    }}
