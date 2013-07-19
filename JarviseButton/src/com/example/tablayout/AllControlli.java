package com.example.tablayout;
import com.example.tablayout.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AllControlli extends Fragment implements OnClickListener{
	Button button1on;
	Button button1off;
	Button button2on;
	Button button2off;
	Button button3on;
	Button button3off;
	Button button4on;
	Button button4off;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Controlli", "Tutti");
	
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.allcontrolli, container, false);
		TextView textView = (TextView) view.findViewById(R.id.infoTuttiControlli);
		textView.setText("Tutti i Controlli");
		
		/*devo aggiungere il caricamento del testo da file*/
		return view;
	}

	public void log(String log){
		Toast.makeText(this.getActivity(), log, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onClick(View button) {
		
			
		
	}
	// TODO Auto-generated method stub

}
