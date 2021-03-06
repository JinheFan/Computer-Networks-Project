package behavior;

/*
 *                     CEN5501C Project2
 * This is the program starting remote processes.
 * This program was only tested on CISE SunOS environment.
 * If you use another environment, for example, linux environment in CISE 
 * or other environments not in CISE, it is not guaranteed to work properly.
 * It is your responsibility to adapt this program to your running environment.
 */

import Message.message_process;
import connection.msgType;
import connection.peer;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.BitSet;


public class RemotePeerInfo {
	private int peerID;
	private String peerAddress;
	private int peerPort;
	private int hasFile;
	private long downloadRate;
	private BitSet bitfield;
	
	private Enum state;
	private Socket socket;
	public BufferedInputStream bufferedInputStream;
	public BufferedOutputStream bufferedOutputStream;
	
	public RemotePeerInfo(int peerID, String peerAddress, int PeerPort, int hasFile) {
		this.peerID = peerID;
		this.peerAddress = peerAddress;
		this.peerPort = PeerPort;
		this.hasFile = hasFile;
		this.downloadRate = 0;              //not sure if i should use 0L
		this.bitfield = new BitSet(peer.getPeerInstance().getPieceCount());    //need to create a method later
		this.state = msgType.choke;      //need to create another file to explain
		this.bufferedInputStream = null;
		this.bufferedOutputStream = null;
		
		if(this.get_hasFile()==1) {
			for(int i=0;i<this.bitfield.size();i++) {
				this.bitfield.set(i);
			}
		}
	}
	
	
	public Socket getSocket() {
		return socket;
	}
	
	public Enum getState() {
		return state;
	}
	
	public void setState(Enum state) {
		this.state = state;
	}
	
	public long getDownloadRate() {
		return downloadRate;
	}
	
	public void setDownloadRate(long downloadRate) {
		this.downloadRate = downloadRate;
	}
	
	public BitSet getbitField() {
		return bitfield;
	}
	
	public void setBitField(int i) {
		this.bitfield.set(i);
	}
	
	public void setBitSet(BitSet i) {
		this.bitfield = i;
	}
	
	public int getPeerID(){
		return peerID;
	}
	
	public void setPeerID(int peerID) {
		this.peerID = peerID;
	}
	
	public String getPeerAddress() {
		return peerAddress;
	}
	
	public void setPeerAddress(String peerAddress) {
		this.peerAddress = peerAddress;
	}
	
	public int get_portNum() {
		return peerPort;
	}
	
	public void set_portNum(int PeerPort) {
		this.peerPort = PeerPort;
	}
	
	public int get_hasFile() {
		return hasFile;
	}
	
	public void set_hasFile(int hasFile) {
		this.hasFile = hasFile;
	}
}