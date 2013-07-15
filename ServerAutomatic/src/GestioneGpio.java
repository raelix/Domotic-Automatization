

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;




public class GestioneGpio {
	private static String acceso = "√® gi√† accesa";
	private static String spento = "√® gi√† spento";
	Pin rele;
	Pin tele;
	int sec;
	final GpioController gpio;
	GpioPinDigitalOutput luce;
	GpioPinDigitalOutput telecamera;
	ThreadListenGpio movimento;
	Garage GarageAllarme;
	public GestioneGpio() {
		this.gpio = GpioFactory.getInstance();
		this.sec = MainServer.sec;
		this.GarageAllarme = new Garage(gpio); 
		this.movimento = new ThreadListenGpio(gpio);
		this.rele = MainServer.rele;
		this.tele = MainServer.tele;
		this.luce = gpio.provisionDigitalOutputPin(this.rele,"rele",PinState.LOW); 
		this.telecamera = gpio.provisionDigitalOutputPin(this.tele,"telecamera",PinState.LOW); 

	}


	public String accendiluce(){
		if(MainServer.luce){
			return acceso;
		}
		else { 
			luce.high();
			MainServer.luce = true;
			return "Luce Accesa";
		}
	};
	public String spegniluce(){
		if(! MainServer.luce){
			return spento;
		}
		else { 
			luce.low();
			MainServer.luce = false;
			return "Luce Spenta";
		}

	};

	public String accenditelecamera(){
		if(MainServer.telecamera){
			return "Telecamera "+" gi√† accesa";
		}
		else { 
			telecamera.high();
			MainServer.telecamera = true;
			return "Telecamera Accesa";
		}
	};
	public String spegnitelecamera(){
		if(!MainServer.telecamera){
			return "Telecamera "+" gi√† spenta";
		}
		else { 
			telecamera.low();
			MainServer.telecamera = false;
			return "Telecamera Spenta";
		}
	};
	
	public boolean eAccesaLuce(){
		return MainServer.luce;
	};

	public boolean eSpentaLuce(){
		return !MainServer.luce;
	};
	
	public boolean eAccesaTelecamera(){
		return MainServer.telecamera;
	};

	public boolean eSpentaTelecamera(){
		return !MainServer.telecamera;
	};
	
	public Garage getGarage(){
		return this.GarageAllarme;
	}
	
	 public String apriGarage(){
		 if (GarageAllarme.state != Garage.CLOSING){
			 if (GarageAllarme.state != Garage.OPENING){
				 GarageAllarme.apriGarage(sec);
			 }
			 else return "Non posso aprire, Ë gi‡ in apertura";
		 } 
		 return "Non posso aprire, Ë in chiusura";
	 	};
	 
	 	
	 	public String chiudiGarage(){
		 if (GarageAllarme.state != Garage.CLOSING){
			 if (GarageAllarme.state != Garage.OPENING){
				 GarageAllarme.chiudiGarage(sec);
			 }
			 else return "Non posso chiudere, Ë in apertura";
		 } 
		 return "Non posso chiudere, Ë gi‡ in chiusura";
	 };
	 
	 public int statoGarage(){
		 return GarageAllarme.statoGarage();
	 };
	 
	 public String attivaAllarme(){
		 if (GarageAllarme.stateAlarm != Garage.DEACTIVE){
			 if (GarageAllarme.stateAlarm != Garage.ACTIVE){
				 GarageAllarme.attivaAlarm(sec);
			 }
			 else return "Non posso attivare l'allarme , si sta gia' attivando";
		 } 
		 return "Non posso attivare l'allarme, si sta disattivando";
	 };
	
	 public String disattivaAllarme(){
		 if (GarageAllarme.stateAlarm != Garage.DEACTIVE){
			 if (GarageAllarme.stateAlarm != Garage.ACTIVE){
				 GarageAllarme.disattivaAlarm(sec);
			 }
			 else return "Non posso disattivare l'allarme , si sta attivando";
		 } 
		 return "Non posso disattivare l'allarme, si sta gia' disattivando";
	 };

	 public int statoAllarme(){
		 return GarageAllarme.statoAllarme();
	 };
}
