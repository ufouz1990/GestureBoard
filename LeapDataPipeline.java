import java.util.ArrayDeque;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;


public class LeapDataPipeline {
	
	
	public static LeapDataPipeline pipeline = new LeapDataPipeline(new Controller());
	
	Controller controller;
	DataListener listener;
	ArrayDeque<VectorPacket> dataQueue;
	boolean typing;
	
	//int currentFingerID = -1;
	
	
	public LeapDataPipeline(Controller cntrl){
		
		
		controller = cntrl;
		listener = new DataListener();
		controller.addListener(listener);
		
		dataQueue = new ArrayDeque<VectorPacket>();
		
		
	}
	
	
	public VectorPacket getVectorPkt(){
		
		
		return dataQueue.poll();
	}
	public void addVectorPkt(VectorPacket pkt){
		
		dataQueue.add(pkt);
		
	}

}


class DataListener extends Listener{
	
	int currentFingerID = -1;
	
	public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        //controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        //controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        //controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        
  
        
    }

    
    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    
    public void onFrame(Controller controller) {
    	
        Frame frame = controller.frame();
        /*System.out.println("Frame id: " + frame.id()
                         + ", timestamp: " + frame.timestamp()
                         + ", hands: " + frame.hands().count()
                         + ", fingers: " + frame.fingers().count()
                         + ", tools: " + frame.tools().count()
                         + ", gestures " + frame.gestures().count());
        */
      
    	if((currentFingerID == -1 || !frame.finger(currentFingerID).isValid())){
    		
    		
    		if(!frame.fingers().isEmpty())
    			currentFingerID = frame.fingers().frontmost().id();
    		else
    			currentFingerID = -1;
    		
    	}
    	
    	if(currentFingerID != -1){
    		
    		VectorPacket vectorPacket;
    		Vector location = frame.finger(currentFingerID).stabilizedTipPosition();
    		Vector velocity = frame.finger(currentFingerID).tipVelocity();
    		vectorPacket = new VectorPacket(location, velocity);
    		LeapDataPipeline.pipeline.addVectorPkt(vectorPacket);
    		
    	}
    	
    	
    	
        
        
    }
	
	
}