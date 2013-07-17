
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;




public class GestioneGpio {
	private static String acceso = "è già accesa";
	private static String spento = "è già spento";
	int sec;
	int secAlarm;
	final GpioController gpio;
	public static Pin default0 ;		//Custom Relè GPIO_00------RELE' 1
	public static Pin default1 ;		//Custom Relè GPIO_01------RELE' 2
	public static Pin default2 ;		//Custom Relè GPIO_02------RELE' 3
	public static Pin default3 ;		//Custom Relè GPIO_03------RELE' 4
	public static Pin default4 ;	   	//Apertura Garage
	public static Pin default5 ;	   	//Chiusura Garage
	public static Pin default6 ;   		//Allarme  Garage
	public static Pin default7 ;     	//Allarme  casa 
	public static Pin default8 ;    	//Sensore acqua acquario
	public static Pin default9 ;       	//sensore acqua casa
	public static Pin default10;    	//sensore movimento casa
	public static GpioPinDigitalOutput gpio0 ;		//Custom Relè GPIO_00------RELE' 1
	public static GpioPinDigitalOutput gpio1 ;		//Custom Relè GPIO_01------RELE' 2
	public static GpioPinDigitalOutput gpio2 ;		//Custom Relè GPIO_02------RELE' 3
	public static GpioPinDigitalOutput gpio3 ;		//Custom Relè GPIO_03------RELE' 4
	public static GpioPinDigitalOutput gpio4 ;	   	//Apertura Garage
	public static GpioPinDigitalOutput gpio5 ;	   	//Chiusura Garage
	public static GpioPinDigitalOutput gpio6 ;   	//Allarme  Garage
	public static GpioPinDigitalOutput gpio7 ;     	//Allarme  casa 
	public static GpioPinDigitalOutput gpio8 ;    	//Sensore acqua acquario
	public static GpioPinDigitalOutput gpio9 ;       //sensore acqua casa
	public static GpioPinDigitalOutput gpio10;    	//sensore movimento casa
	ThreadListenGpio movimento;
	Garage GarageAllarme;
	
	
	@SuppressWarnings("static-access")
	public GestioneGpio() {
		this.gpio = GpioFactory.getInstance();
		this.sec = 11000;
		this.secAlarm = 4000;
		this.GarageAllarme = new Garage(gpio); 
		this.movimento = new ThreadListenGpio(gpio);
		this.default0 = MainServer.default0;
		this.default1 = MainServer.default1;
		this.default2 = MainServer.default2;
		this.default3 = MainServer.default3;
		this.default4 = MainServer.default4;
		this.default5 = MainServer.default5;
		this.default6 = MainServer.default6;
		this.default7 = MainServer.default7;
		this.default8 = MainServer.default8;
		this.default9 = MainServer.default9;
		this.default10 = MainServer.default10;
		this.gpio0 = gpio.provisionDigitalOutputPin(this.default0,PinState.LOW);
		this.gpio1 = gpio.provisionDigitalOutputPin(this.default1,PinState.LOW);
		this.gpio2 = gpio.provisionDigitalOutputPin(this.default2,PinState.LOW);
		this.gpio3 = gpio.provisionDigitalOutputPin(this.default3,PinState.LOW);
//		this.gpio4 = gpio.provisionDigitalOutputPin(this.default4,PinState.LOW);
//		this.gpio5 = gpio.provisionDigitalOutputPin(this.default5,PinState.LOW);
//		this.gpio6 = gpio.provisionDigitalOutputPin(this.default6,PinState.LOW);
//		this.gpio7 = gpio.provisionDigitalOutputPin(this.default7,PinState.LOW);
//		this.gpio8 = gpio.provisionDigitalOutputPin(this.default8,PinState.LOW);
//		this.gpio9 = gpio.provisionDigitalOutputPin(this.default9,PinState.LOW);
//		this.gpio10 = gpio.provisionDigitalOutputPin(this.default10,PinState.LOW);

	}

	
	
	public String accendi(int pin){
		switch (pin){
		
		case 0:
			if(!MainServer.GPIO_00){
			gpio0.high();
			MainServer.GPIO_00 = true;
			}
			else return acceso;
			break;
			
		case 1:
			if(!MainServer.GPIO_01){
				gpio1.high();
				MainServer.GPIO_01 = true;
				}
			else return acceso;
			break;
			
		case 2:
			if(!MainServer.GPIO_02){
				gpio2.high();
				MainServer.GPIO_02 = true;
				}
			else return acceso;
			break;
		
		case 3:
			if(!MainServer.GPIO_03){
				gpio3.high();
				MainServer.GPIO_03 = true;
				}
			else return acceso;
			break;
		/*
		case 4:
			if(!MainServer.GPIO_04){
				gpio4.high();
				MainServer.GPIO_04 = true;
				}
			else return acceso;
			break;
		
		case 5:
			if(!MainServer.GPIO_05){
				gpio5.high();
				MainServer.GPIO_05 = true;
				}
			else return acceso;
			break;*/
		default:break;
		}
		return "acceso";
	}
	
	
	
	public String spegni(int pin){
		switch (pin){
		
		case 0:
			if(MainServer.GPIO_00){
			gpio0.low();
			MainServer.GPIO_00 = false;
			}
			else return spento;
			break;
			
		case 1:
			if(MainServer.GPIO_01){
			gpio1.low();
			MainServer.GPIO_01 = false;
			}
			else return spento;
			break;
			
		case 2:
			if(MainServer.GPIO_02){
			gpio2.low();
			MainServer.GPIO_02 = false;
			}
			else return spento;
			break;
		
		case 3:
			if(MainServer.GPIO_03){
			gpio3.low();
			MainServer.GPIO_03 = false;
			}
			else return spento;
			break;
			
		/*
		case 4:
			if(MainServer.GPIO_04){
			gpio4.low();
			MainServer.GPIO_04 = false;
			}
			else return spento;
			break;
		
		case 5:
			if(MainServer.GPIO_05){
			gpio5.low();
			MainServer.GPIO_05 = false;
			}
			else return spento;
			break;
	*/		
		default:break;
		}
		return "spento";
	}

	public Garage getGarage(){
		return this.GarageAllarme;
	}
	
	 public String apriGarage(){
		 if(!MainServer.garage){
		 if (GarageAllarme.state != Garage.CLOSING){
			 if (GarageAllarme.state != Garage.OPENING){
				 GarageAllarme.apriGarage(sec);
			 }
			 else return "Non posso aprire, � gi� in apertura";
		 } 
		 else  return "Non posso aprire, � in chiusura";
		 }
		 else return "Il garage è già aperto";
		return "Ho aperto il garage";
	 	};
	 
	 	
	 	public String chiudiGarage(){
	 		if(MainServer.garage){
		 if (GarageAllarme.state != Garage.CLOSING){
			 if (GarageAllarme.state != Garage.OPENING){
				 GarageAllarme.chiudiGarage(sec);
			 }
			 else return "Non posso chiudere, � in apertura";
		 } 
		 else return "Non posso chiudere, � gi� in chiusura";
	 		}
	 		else return "Il garage è già chiuso";
	 		return "Ho chiuso il garage";
	 };
	 
	 public int statoGarage(){
		 return GarageAllarme.statoGarage();
	 };
	 
	 public String attivaAllarmeGarage(){
		 if(!MainServer.AllarmeGarage){
		 if (GarageAllarme.stateAlarm != Garage.DEACTIVE){
			 if (GarageAllarme.stateAlarm != Garage.ACTIVE){
				 GarageAllarme.attivaAlarmGarage(secAlarm);
			 }
			 else return "Non posso attivare l'allarme , si sta gia' attivando";
		 } 
		 else return "Non posso attivare l'allarme, si sta disattivando";
		 }
		 else return "L'allarme è già attiva"; 
			 return "Ho attivato l'allarme";
	 };
	
	 public String disattivaAllarmeGarage(){
		 if(MainServer.AllarmeGarage){
		 if (GarageAllarme.stateAlarm != Garage.DEACTIVE){
			 if (GarageAllarme.stateAlarm != Garage.ACTIVE){
				 GarageAllarme.disattivaAlarmGarage(secAlarm);
			 }
			 else return "Non posso disattivare l'allarme , si sta attivando";
		 } 
		 else return "Non posso disattivare l'allarme, si sta gia' disattivando";
		 }
		 else return "L'allarme è già disattivata";
		 return "Ho disattivato l'allarme";
	 };
	 
	 public String attivaAllarmeCasa(){
		 if(!MainServer.AllarmeCasa){
		 if (GarageAllarme.stateAlarm != Garage.DEACTIVE){
			 if (GarageAllarme.stateAlarm != Garage.ACTIVE){
				 GarageAllarme.attivaAlarmCasa(secAlarm);
			 }
			 else return "Non posso attivare l'allarme , si sta gia' attivando";
		 } 
		 else return "Non posso attivare l'allarme, si sta disattivando";
		 }
		 else return "L'allarme è già attiva"; 
			 return "Ho attivato l'allarme";
	 };
	
	 public String disattivaAllarmeCasa(){
		 if(MainServer.AllarmeCasa){
		 if (GarageAllarme.stateAlarm != Garage.DEACTIVE){
			 if (GarageAllarme.stateAlarm != Garage.ACTIVE){
				 GarageAllarme.disattivaAlarmCasa(secAlarm);
			 }
			 else return "Non posso disattivare l'allarme , si sta attivando";
		 } 
		 else return "Non posso disattivare l'allarme, si sta gia' disattivando";
		 }
		 else return "L'allarme è già disattivata";
		 return "Ho disattivato l'allarme";
	 };

	 public int statoAllarme(){
		 return GarageAllarme.statoAllarme();
	 };
	 
	 
	 
	 
	 
	
}
