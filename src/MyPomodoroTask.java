import java.time.Duration;



public class MyPomodoroTask {

	private Duration workDuration;
	private Duration breakDuration;
    int workHours, workMinutes, workSeconds, breakHours, breakMinutes, breakSeconds,rep;
  
    //---------------------------------------------------------------------------------------------------------------------------------------------  
    
    public MyPomodoroTask(int workHours,int workMinutes,int workSeconds,int breakHours,int breakMinutes,int breakSeconds,int rep )
    {
    this.workDuration=Duration.ofSeconds(SuppFunct.precTimeToMs(workHours, workMinutes, workSeconds));
    this.breakDuration=Duration.ofSeconds(SuppFunct.precTimeToMs(breakHours, breakMinutes, breakSeconds));
    this.rep=rep;
    this.workHours=workHours;
    this.workMinutes=workMinutes;
    this.workSeconds=workSeconds;
    this.breakHours=breakHours;
    this.breakMinutes=breakMinutes;
    this.breakSeconds=breakSeconds;
    }

    public Duration getWorkDur()
    {
    return this.workDuration;	
    }
    
    public Duration getBreakDur()
    {
    return this.breakDuration;	
    }
    public int getRep()
    {
    return this.rep;	
    }


   

}