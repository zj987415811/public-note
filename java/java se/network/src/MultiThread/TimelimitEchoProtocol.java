package MultiThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class TimelimitEchoProtocol implements Runnable {

	private static final int BUFSIZE = 32;
	private static final String TIMELIMIT = "10000";
	private static final String TIMELIMITPROP = "Timelimit";
	
	private static int timelimit;
	private final Socket clntSock;
	private final Logger logger;
	
	public TimelimitEchoProtocol(Socket clntSock, Logger logger){
		this.clntSock = clntSock;
		this.logger = logger;
		timelimit = Integer.parseInt(System.getProperty(TIMELIMITPROP, TIMELIMIT));
	}
	
	
	//静态方法，该方法为单实例的
	public static void handleEchoClient(Socket clntSock, Logger logger) {
		
		try{
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();
			long endTime = System.currentTimeMillis() + timelimit;		//将开始时间加持续时间，得到终止时间
			int timeBoundMillis = timelimit;
			
			clntSock.setSoTimeout(timeBoundMillis);
			int recvMsgSize = 0;
			int totalBteEchoed = 0;
			byte[] echoBuffer = new byte[BUFSIZE];
			
			while((recvMsgSize = in.read(echoBuffer))!=-1){
				out.write(echoBuffer, 0, recvMsgSize);
				totalBteEchoed += recvMsgSize;
				timeBoundMillis = (int)(endTime-System.currentTimeMillis());	//得到剩余的时间
				clntSock.setSoTimeout(timeBoundMillis);							//重置等待时间
			}
			
			logger.info("Client " + clntSock.getRemoteSocketAddress() + ", echoed " + totalBteEchoed + " bytes.");	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	@Override
	public void run() {
		handleEchoClient(this.clntSock, this.logger);
	}

}
