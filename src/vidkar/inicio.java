package vidkar;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import com.keysolutions.ddpclient.DDPClient;

import me.kutrumbos.DdpClient;
import me.kutrumbos.examples.SimpleDdpClientObserver;

public class inicio {

	public static void main(String[] args) {
		
		// specify location of Meteor server (assumes it is running locally) 
		String meteorIp = "localhost";
		Integer meteorPort = 3000;

		try {
			
			// create DDP client instance
			DDPClient ddp = new DDPClient(meteorIp, meteorPort);
			
			// create DDP client observer
			Observer obs = new Observer() {
				
				@Override
				public void update(Observable o, Object arg) {
					// TODO Auto-generated method stub
					if (arg instanceof String) {
						System.out.println("Received response: "+arg);
					}
				}
			};
			
			// add observer
			ddp.addObserver(obs);
						
			// make connection to Meteor server
			System.out.println("CONECTANDO");
			ddp.connect();
			
//			System.out.println("ENVIANDO CALL");
//			Object[] methodArgs = new Object[1];
//			methodArgs[0] = new Date().toString();
//			ddp.call("getusers", null);
			
//			System.out.println("DESCONECTANDO");
//			ddp.disconnect();
			
//			ddp.subscribe("user", null);
			
			while(true) {
				try {
					
					Thread.sleep(5000);
					System.out.println(ddp.getState());
//					switch (ddp.getState()) {
//					case 0: {
//						System.out.println("CONNECTING");
//						break;
//					}
//					case 1: {
//						System.out.println("OPEN");
//						break;
//					}
//					case 2: {
//						System.out.println("CLOSING");
//						
//						System.out.println("calling remote method...");
//						
//						Object[] methodArgs = new Object[1];
//						methodArgs[0] = new Date().toString();
//						ddp.call("getusers", new Object[]{});
//						
//						break;
//					}
//					case 3: {
//						System.out.println("CLOSED");
//						break;
//					}
//					default:
//						break;
//					}
					
					
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
					
		} catch (URISyntaxException|Error e) {
			e.printStackTrace();
		}			
	}

}
