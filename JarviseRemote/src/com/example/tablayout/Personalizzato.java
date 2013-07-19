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

public class Personalizzato extends Fragment implements OnClickListener{
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
		Log.e("Personalizzato", "Selezionato");
		log("eccomi");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.personalizzato, container, false);
		TextView textView = (TextView) view.findViewById(R.id.infoPersonalizzato);
		textView.setText("Relè Personalizzati");
		button1on = (Button)view.findViewById(R.id.pesonalizzato1On);
		button1off = (Button)view.findViewById(R.id.pesonalizzato1Off);
		button2on = (Button)view.findViewById(R.id.pesonalizzato2On);
		button2off = (Button)view.findViewById(R.id.pesonalizzato2Off);
		button3on = (Button)view.findViewById(R.id.pesonalizzato3On);
		button3off = (Button)view.findViewById(R.id.pesonalizzato3Off);
		button4on = (Button)view.findViewById(R.id.pesonalizzato4On);
		button4off = (Button)view.findViewById(R.id.pesonalizzato4Off);
		button1on.setOnClickListener(this);
		button1off.setOnClickListener(this);
		button2on.setOnClickListener(this);
		button2off.setOnClickListener(this);
		button3on.setOnClickListener(this);
		button3off.setOnClickListener(this);
		button4on.setOnClickListener(this);
		button4off.setOnClickListener(this);
		/*devo aggiungere il caricamento del testo da file*/
		return view;
	}

	public void log(String log){
		Toast.makeText(this.getActivity(), log, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onClick(View button) {
		if(button == button1on){
			log("bottone1");
			}
		if(button == button1off){
			
		}
		if(button == button2on){
			
		}
		if(button == button2off){
			
		}
		if(button == button3on){
			
		}
		if(button == button3off){
			
		}
		if(button == button4on){
			
		}
		if(button == button4off){
			
		}
	}
	// TODO Auto-generated method stub

}
