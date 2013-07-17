

import java.util.Calendar;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;




	public class ThreadListenGpioAcquaCasa extends Thread {
		private GpioController sensore;
		private GpioPinDigitalInput movimento;
		private Pin pin;
		
		public ThreadListenGpioAcquaCasa (GpioController sensore) {
			super();
			this.pin = MainServer.acquaCasa;
			this.sensore = sensore;
			if(MainServer.AcquaCasa != null)
				System.out.println("Thread Gpio "+"ultima traccia di acqua "+MainServer.AcquaCasa);
			else System.out.println("Thread Gpio "+"nessuna traccia d'acqua fin'ora");
			this.run();
		};

		@Override
		public void run () {

			movimento = sensore.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
			
			movimento.addListener(new GpioPinListenerDigital() {
				@Override
				public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
					System.out.println("Il sensore ha cambiato stato : " + event.getPin() + " = " + event.getState());
					MainServer.file.write("Il sensore ha cambiato stato : " + event.getPin() + " = " + event.getState());
					MainServer.AcquaCasa = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
				}

			});

			/* keep program running until user aborts (CTRL-C)
			for (;;) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Thread sleep pause error");
				}
			}*/

		};

	

}
