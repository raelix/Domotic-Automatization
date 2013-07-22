package com.example.tablayout;
import com.example.tablayout.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Status extends Fragment implements OnClickListener{
	private static String nameFile = "jarvise.txt";
	TextView def1;
	TextView def2;
	TextView def3;
	TextView def4;
	String default1;
	String default2;
	String default3;
	String default4;
	Button getStatus;
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
	ToggleButton personalizzato1;
	static Activity thisActivity = null;
	Configuration readFile;
	View view;
	@SuppressWarnings("static-access")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Controlli", "Tutti");
		this.thisActivity = this.getActivity();
		this.readFile = new Configuration(nameFile);
		this.default1 = readFile.getDefault0(); 
		this.default2 = readFile.getDefault1();
		this.default3 = readFile.getDefault2();
		this.default4 = readFile.getDefault3();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.status0, container, false);
		TextView textView = (TextView) view.findViewById(R.id.infoStatus);
		textView.setText("Status Gpio");
		getStatus = (Button) view.findViewById(R.id.getStatus);
		getStatus.setOnClickListener(this);
		return view;
	}
	
	public static void log(String log){
		Toast.makeText(thisActivity, log, Toast.LENGTH_LONG).show();

	}
	
	public void changeInflate(){
		getActivity().getLayoutInflater().inflate(R.layout.status, ((ViewGroup)getView().getParent()), false);
	}

	@SuppressWarnings("static-access")
	@Override
	public void onClick(View button) {

		if(button == getStatus){
			log("Waiting refresh....");
			((ViewGroup) view).removeAllViews();
			view = getView().inflate(getView().getContext(), R.layout.status,((ViewGroup) view) );	
			personalizzato1 = (ToggleButton) view.findViewById(R.id.pesonalizzato1);
			personalizzato1.setOnClickListener(this);
//			personalizzato1.setClickable(false);
			personalizzato1.setChecked(true);
			if(personalizzato1.isChecked())
			{
				personalizzato1.setBackgroundResource(R.layout.acceso);
				personalizzato1.setText("Acceso");
			}
			else
			{
				personalizzato1.setBackgroundResource(R.layout.spento);
				personalizzato1.setText("Spento");
			}

		}
		
		
		
		
		
		
		if(button == personalizzato1){
			if(personalizzato1.isChecked())
			{
				personalizzato1.setBackgroundResource(R.layout.acceso);
				personalizzato1.setText("Acceso");
			}
			else
			{
				personalizzato1.setBackgroundResource(R.layout.spento);
				personalizzato1.setText("Spento");
			}
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
