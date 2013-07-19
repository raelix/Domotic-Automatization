package com.example.tablayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
 
public class Sensori extends Fragment implements OnClickListener{
	Button AcquaAcquario;
	Button AcquaCasa;
	Button movimento;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Personalizzato", "Selezionato");
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sensori, container, false);
        TextView textView = (TextView) view.findViewById(R.id.infoSensori);
        textView.setText("Sensori");
        AcquaAcquario = (Button)view.findViewById(R.id.ControlloPerditaAcquarioButton);
		AcquaCasa = (Button)view.findViewById(R.id.ControlloPerditaCasaButton);
		movimento = (Button)view.findViewById(R.id.ControlloMovimentoCasaButton);
		AcquaCasa.setOnClickListener(this);
		AcquaAcquario.setOnClickListener(this);
		movimento.setOnClickListener(this);
        return view;
    }

	@Override
	public void onClick(View button) {
		if(button == AcquaAcquario){
			
		}
		if(button == AcquaCasa){
			
		}
		if(button == movimento){
			
		}
		
	}
}