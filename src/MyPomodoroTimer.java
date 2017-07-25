import java.io.IOException;
import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class MyPomodoroTimer {

MyPomodoroTask myActualTask;

Duration duration;

JLabel myActualLabel;
Timer timer=new Timer();
TimerTask counterTask;
int tempRep=1;
boolean toSwitchWorkBreak=false;
String mode=Consts.Writings.work;

public MyPomodoroTimer(MyPomodoroTask actualTask,JLabel actualLabel)
{
this.myActualTask=actualTask;
this.myActualLabel=actualLabel;
this.duration=this.myActualTask.getWorkDur();
}

void bodyRun(){
	 
	 duration=duration.minus(Duration.ofSeconds(1));
	 
	 myActualLabel.setText(" "+duration.toHours()+Consts.Writings.colon+duration.toMinutes()%60+Consts.Writings.colon+duration.getSeconds()%60+mode+Consts.Writings.rep+Consts.Writings.space+tempRep);

		
		if(duration.toHours()==0 &&duration.toMinutes()==0 && duration.getSeconds()==0) {
			myActualLabel.setText(Consts.Writings.end);
			timer.cancel();
			timer.purge();
			timer=new Timer();
			if(toSwitchWorkBreak==false){
				try {
					SuppFunct.playCycleSound(Consts.Paths.shortSound);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				duration=this.myActualTask.getBreakDur();
				timerInitStart();
				toSwitchWorkBreak=true;
				mode=Consts.Writings.break_;
				
			}
			else
			{
				try {
					SuppFunct.playCycleSound(Consts.Paths.shortSound);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				duration=this.myActualTask.getWorkDur();
				timerInitStart();
				toSwitchWorkBreak=false;
				mode=Consts.Writings.work;
				tempRep++;
			}
				
		}
       if (tempRep>myActualTask.getRep()){
    	   myActualLabel.setText(Consts.Writings.end);
			timer.cancel();
			timer.purge();
			
			try {
				SuppFunct.playCycleSound(Consts.Paths.endSound);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
}


void timerInitStart(){
	duration=duration.plus(Duration.ofSeconds(1));
	
	counterTask= new TimerTask(){
		public void run()
		{
			
			bodyRun();
		}
	};	
	
	 timer.scheduleAtFixedRate(counterTask,500,1000);
    
}


void start(){
	
	counterTask= new TimerTask(){
		public void run()
		{
			
			bodyRun();
		}
	};
	timer.scheduleAtFixedRate(counterTask,500,1000);
}

void pause(){
	timer.cancel(); 
	timer.purge();
	timer=new Timer();
}



void timerReset(){
	myActualLabel.setText(Consts.Writings.end);
	timer.cancel();
	timer.purge();
}
}
