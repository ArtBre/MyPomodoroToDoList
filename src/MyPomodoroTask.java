import java.time.Duration;


public class MyPomodoroTask {

	Duration workDuration;
	Duration breakDuration;
    int rep;
    
    
    public MyPomodoroTask(int workHours,int workMinutes,int workSeconds,int breakHours,int breakMinutes,int breakSeconds,int rep )
    {
    this.workDuration=Duration.ofSeconds(SuppFunct.precTimeToMs(workHours, workMinutes, workSeconds));
    this.breakDuration=Duration.ofSeconds(SuppFunct.precTimeToMs(breakHours, breakMinutes, breakSeconds));
    this.rep=rep;
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