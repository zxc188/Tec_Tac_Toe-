import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Socket_Thread extends Thread{
	private Socket client;
	private int which;
	private static char pan[]={'-','-','-','-','-','-','-','-','-'};
	private int result,i,j;
	Socket_Thread(Socket client,int which){
		this.client=client;
		this.which=which;
	}
	
	public void run() {
		try {	
			Scanner in=new Scanner(System.in);
			DataInputStream input=new  DataInputStream(client.getInputStream());
			DataOutputStream output=new DataOutputStream(client.getOutputStream());
			while(Tec_Tac_Toe_Server.count!=2){
				try {
					System.out.print(">>>");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("另一个玩家已经登陆");
			output.writeChar(Tec_Tac_Toe_Server.t[which]);
			while(!Tec_Tac_Toe_Server.one_win){
				if(Tec_Tac_Toe_Server.wh[which]==1){
					output.writeBoolean(Tec_Tac_Toe_Server.one_win);
					output.writeInt(Tec_Tac_Toe_Server.res);
					Tec_Tac_Toe_Server.res=input.readInt();
					pan[Tec_Tac_Toe_Server.res]=Tec_Tac_Toe_Server.t[which];
					++Tec_Tac_Toe_Server.bushu;
					if(you_win()){
						Tec_Tac_Toe_Server.one_win=true;
						Tec_Tac_Toe_Server.s[which]="i am win";
						if(which==1){
							Tec_Tac_Toe_Server.s[0]="you low";
						}else{
							Tec_Tac_Toe_Server.s[1]="you low";
						}
					}
					if(Tec_Tac_Toe_Server.bushu==9){
						Tec_Tac_Toe_Server.one_win=true;
					}
					exchange(Tec_Tac_Toe_Server.wh);
				}
			}
			output.writeBoolean(Tec_Tac_Toe_Server.one_win);
			output.writeUTF(Tec_Tac_Toe_Server.s[which]);
			output.writeInt(Tec_Tac_Toe_Server.res);
			Tec_Tac_Toe_Server.res=100;
			Tec_Tac_Toe_Server.one_win=false;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private boolean you_win(){
		int i;
		for(i=0;i<7;i=i+3){
			if(pan[i]==pan[i+1]&&pan[i]==pan[i+2]&&pan[i]==Tec_Tac_Toe_Server.t[which]){
				return true;
			}
		}
		for(i=0;i<3;i++){
			if(pan[i]==pan[i+3]&&pan[i]==pan[i+6]&&pan[i]==Tec_Tac_Toe_Server.t[which]){
				return true;
			}
		}
		if(pan[0]==pan[4]&&pan[0]==pan[8]&&pan[0]==Tec_Tac_Toe_Server.t[which]){
			return true;
		}
		if(pan[2]==pan[4]&&pan[2]==pan[6]&&pan[2]==Tec_Tac_Toe_Server.t[which]){
			return true;
		}
		return false;
	}
	private boolean not_full(){
		boolean b=false;
		for(int i=0;i<pan.length;i++){
			if(pan[i]!='X'&&pan[i]!='O'){
				b=true;
				break;
			}
		}
		System.out.println(b);
		return b;
	}
	private void exchange(int[] a){
		int t;
		t=a[1];
		a[1]=a[0];
		a[0]=t;
	}
}
