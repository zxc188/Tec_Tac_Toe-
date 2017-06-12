import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tec_Tac_Toe_Server {
	public static int which=0;
	public static int count=0;
	public static char[] t={'O','X'};
	public static int[] wh={1,0};
	public static boolean one_win=false; 
	public static String[] s={"ping","ping"};
	public static int res=100;
	public static int bushu=0;
	public static void main(String[] args) {
		ServerSocket server;
		ExecutorService executor = Executors.newCachedThreadPool();
		try {
			server=new ServerSocket(3102);
			while(true){
				Socket client=server.accept();
				++count;
				InetAddress  inetadd=client.getInetAddress();
				System.out.println("\n链接成功\t"+inetadd.getHostAddress()+"\t"+new Date());
				executor.execute(new Socket_Thread(client,which));
				++which;
				try {
					Thread.sleep(2000);
					if(count==2){
						count=0;
						which=0;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (IOException e) {
			System.out.println("IO问题");
		}

	}

}
