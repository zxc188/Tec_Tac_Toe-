import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Tec_Tac_Client {
	static char pan[]={'-','-','-','-','-','-','-','-','-'};
	public static void main(String[] args) {
		Socket client;
		char point,other_point;
		boolean one_win=false;
		
		int other,result,is_you=0,x,y,i;
		try {
			Scanner in=new Scanner(System.in);
			client = new Socket("localhost",3102);
			DataInputStream input=new  DataInputStream(client.getInputStream());
			DataOutputStream output=new DataOutputStream(client.getOutputStream());
			point=input.readChar();
			if(point=='X'){
				other_point='O';
			}else{
				other_point='X';
			}
			while(!one_win){
				    one_win=input.readBoolean();
				    if(one_win==true){
				    	break;
				    }
					other=input.readInt();
					if(other!=100){
						pan[other]=other_point;
					}
					show();
					System.out.println("it is your turn like:1 2");
					x=in.nextInt();
					y=in.nextInt();
					result=(x-1)*3+y-1;
					if(pan[result]!='-'){
						System.out.println("input error   please input again");
						x=in.nextInt();
						y=in.nextInt();
						result=(x-1)*3+y-1;
					}
					pan[result]=point;
					output.writeInt(result);
			}
			String as=input.readUTF();
			other=input.readInt();
			if(as.equals("i am win")||as.equals("ping")){
				show();
				System.out.println(as);
			}else{
				pan[other]=other_point;
				
				show();
				System.out.println(as);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void show(){
		for(int i=0;i<pan.length;i++){
			System.out.print(pan[i]+"  ");
			if((i+1)%3==0){
				System.out.println();
			}
		}
	}
}
