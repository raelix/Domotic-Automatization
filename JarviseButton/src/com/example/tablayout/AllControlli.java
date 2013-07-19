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
	Button garageOn;
	Button garageOff;
	Button AllarmeCasaOn;
	Button AllarmeCasaOff;
	Button AllarmeGarageOn;
	Button AllarmeGarageOff;
	Button AcquaAcquario;
	Button AcquaCasa;
	Button movimento;


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
		button1on = (Button)view.findViewById(R.id.pesonalizzato1On);
		button1off = (Button)view.findViewById(R.id.pesonalizzato1Off);
		button2on = (Button)view.findViewById(R.id.pesonalizzato2On);
		button2off = (Button)view.findViewById(R.id.pesonalizzato2Off);
		button3on = (Button)view.findViewById(R.id.pesonalizzato3On);
		button3off = (Button)view.findViewById(R.id.pesonalizzato3Off);
		button4on = (Button)view.findViewById(R.id.pesonalizzato4On);
		button4off = (Button)view.findViewById(R.id.pesonalizzato4Off);
		garageOn = (Button)view.findViewById(R.id.ControlloGarageOn);
		garageOff = (Button)view.findViewById(R.id.ControlloGarageOff);
		AllarmeCasaOn = (Button)view.findViewById(R.id.ControlloAllarmeCasaOff);
		AllarmeCasaOff = (Button)view.findViewById(R.id.ControlloAllarmeCasaOn);
		AllarmeGarageOn = (Button)view.findViewById(R.id.ControlloAllarmeGarageOn);
		AllarmeGarageOff = (Button)view.findViewById(R.id.ControlloAllarmeGarageOff);
		AcquaAcquario = (Button)view.findViewById(R.id.ControlloPerditaAcquarioButton);
		AcquaCasa = (Button)view.findViewById(R.id.ControlloPerditaCasaButton);
		movimento = (Button)view.findViewById(R.id.ControlloMovimentoCasaButton);
		button1on.setOnClickListener(this);
		button1off.setOnClickListener(this);
		button2on.setOnClickListener(this);
		button2off.setOnClickListener(this);
		button3on.setOnClickListener(this);
		button3off.setOnClickListener(this);
		button4on.setOnClickListener(this);
		button4off.setOnClickListener(this);
		garageOn.setOnClickListener(this);
		garageOff.setOnClickListener(this);
		AllarmeCasaOn.setOnClickListener(this);
		AllarmeCasaOff.setOnClickListener(this);
		AllarmeGarageOn.setOnClickListener(this);
		AllarmeGarageOff.setOnClickListener(this);
		AcquaCasa.setOnClickListener(this);
		AcquaAcquario.setOnClickListener(this);
		movimento.setOnClickListener(this);
		/*devo aggiungere il caricamento del testo da file*/
		return view;
	}

	public void log(String log){
		Toast.makeText(this.getActivity(), log, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onClick(View button) {

		if(button == button1on){
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
		if(button == garageOn){
			
		}
		if(button == garageOff){
			
		}
		if(button == AllarmeCasaOn){
			
		}
		if(button == AllarmeCasaOff){
			
		}
		if(button == AllarmeGarageOff){
			
		}
		if(button == AllarmeGarageOn){
			
		}
		if(button == AcquaAcquario){
			
		}
		if(button == AcquaCasa){
			
		}
		if(button == movimento){
			
		}
	}
	// TODO Auto-generated method stub

}
