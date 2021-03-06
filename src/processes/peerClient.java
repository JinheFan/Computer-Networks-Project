package processes;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import connection.IncomingRequest;
import connection.outcomingRequest;

import behavior.RemotePeerInfo;

public class peerClient implements Runnable {
	private Map<Integer, RemotePeerInfo> PeerConnectTo;
	
	private ExecutorService outThreadPool;
	private Thread clientThread;
    
	// Client is created with list of peers to connect to
    peerClient(Map<Integer, RemotePeerInfo> PeerConnectTo, int numPeerConnectTo) {
    	this.PeerConnectTo = PeerConnectTo;
    	// Create enough threads in pool for the number of peers that will be connected to
    	this.outThreadPool = Executors.newFixedThreadPool(numPeerConnectTo);
    }
    
    public void run() {
    	// Synchronize so multiple clients do not get assigned the same thread
    	synchronized (this) {
    		// Assign the current thread to this process
    		this.clientThread = Thread.currentThread();
    	}
    	// Go through remote peers in list and start a connection process for each
    	for (RemotePeerInfo remotePeer : this.PeerConnectTo.values()) {
    		this.outThreadPool.execute(new outcomingRequest(remotePeer));
    	}
    }
}