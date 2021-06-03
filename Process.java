import java.util.*;
/**
 * Represents a process
 *
 * @Seth Malin
 * @version (a version number or a date)
 */
public class Process implements Comparable <Process>
{
    private int processNumber;
    private int burstTime;
    private int timeRemaining;
    private int completionTime;
    private boolean finished = false;
    public Process (int number, int time)
    {
        processNumber = number;
        burstTime = time;
        timeRemaining = time;
    }
    
    public int getProcessNumber ()
    {
        return processNumber;
    }
    
    public int getBurstTime ()
    {
        return burstTime;
    }
    
    public void finished()
    {
        finished = true;
    }
    
    public boolean checkFinished()
    {
        return finished;
    }
    
    public void setRemaining(int timeLeft)
    {
        timeRemaining = timeLeft;
    }
    
    public int getRemaining ()
    {
        return timeRemaining;
    }
    
    public int getWaitTime()
    {
        return completionTime - burstTime;
    }
    
    public void setCompletionTime(int time)
    {
        completionTime = time;
    }
    
    public int getCompletionTime()
    {
        return completionTime;
    }
    
    public int compareTo(Process aProcess)
    {
        if (aProcess.getBurstTime() > burstTime)
        {
            return 1;
        }
        else if (aProcess.getBurstTime() < burstTime)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
    //overload just to unsort, boolean goes unused
    public int compareTo (Process aProcess, boolean bool)
    {
        if (aProcess.getProcessNumber() > processNumber)
        {
            return 1;
        }
        else if (aProcess.getProcessNumber() < processNumber)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
