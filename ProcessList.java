import java.util.*;
/**
 * Contains an ArrayList of processes
 *
 * @Seth Malin
 * @version (a version number or a date)
 */
public class ProcessList
{
    private ArrayList <Process> processList;
    private ArrayList <Process> duplicate;
    int time;
    float completionTime = 0;
    float average = 0;
    public ProcessList()
    {
        processList = new ArrayList();
        time = 0;
    }
    
    public int getSize()
    {
        return processList.size();
    }

    public void add(Process toAdd)
    {
        processList.add(toAdd);
    }

    public Process get(int index)
    {
        return processList.get(index);
    }

    public void FCFS()
    {
        //using private helper methods, otherwise this code would be
        //extremely long and unreadable
        this.calculateCompletionTime();
        this.calculateAverageWaitTime();
    }
     
    public void SJF ()
    {
        this.arrange();
        this.calculateCompletionTime();
        this.calculateAverageWaitTime();
        this.unarrange();
    }

    public void RR (int ticks)
    {
        //duplicate the ArrayList for calculations
        duplicate = new ArrayList();
        for (int i = 0; i < processList.size(); i++)
        {
            duplicate.add(processList.get(i));
        }
        this.calculateCompletionTime(ticks);
        this.calculateAverageWaitTime();
    }

    private void arrange()
    {
        //Using selection sort to arrange in ascending order
        int max;
        Process temp;
        int count = 0;
        for (int i = 0; i < processList.size() -1; i++)
        {
            max = i;
            for (int scan = i+1; scan < processList.size(); scan++)
            {
                if (processList.get(scan).compareTo(processList.get(max)) > 0)
                {   
                    max = scan;
                }
                count ++;
            }
            swap(processList, max, i);
        }
    }

    private static void swap(ArrayList<Process> data, int index1, int index2)
    {
        Process temp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, temp);
    }
    
    //restores the original order of the processes
    private void unarrange ()
    {
        int max;
        Process temp;
        int count = 0;
        for (int i = 0; i < processList.size() -1; i++)
        {
            max = i;
            for (int scan = i+1; scan < processList.size(); scan++)
            {
                if (processList.get(scan).compareTo(processList.get(max), true) > 0)
                {   
                    max = scan;
                }
                count ++;
            }
            swap(processList, max, i);
        }
    }
    
    private void calculateCompletionTime()
    {
        int currentTime = time;
        Process temp = null;
        for (int i = 0; i < processList.size(); i++)
        {
            temp = processList.get(i);
            temp.setCompletionTime(temp.getBurstTime() + currentTime);
            currentTime = temp.getCompletionTime();
        }
        for (int i = 0; i < processList.size(); i++)
        {
            temp = processList.get(i);
            this.completionTime = this.completionTime + temp.getCompletionTime();
        }
    }
    
    //overload for RR
    private void calculateCompletionTime(int ticks)
    {
        Process temp = null;
        int currentTime = 0;
        boolean notDone = true;
        while (notDone)
        {
            for (int i = 0; i < duplicate.size(); i++)
            {
                temp = duplicate.get(i);
                if (!temp.checkFinished())
                {
                    currentTime = currentTime + ticks;
                    temp.setRemaining(temp.getRemaining()-ticks);
                    if (temp.getRemaining() == 0)
                    {
                        processList.get(i).setCompletionTime(currentTime);
                        temp.finished();
                    }
                    else if (temp.getRemaining() < 0)
                    {
                        currentTime = currentTime + temp.getRemaining();
                        processList.get(i).setCompletionTime(currentTime);
                        temp.finished();
                    }
                }
            }
            notDone = checkIfNotDone();
        }
        for (int i = 0; i < processList.size(); i++)
        {
            temp = processList.get(i);
            this.completionTime = this.completionTime + temp.getCompletionTime();
        }
    }
    
    private boolean checkIfNotDone()
    {
        for (int i = 0; i < duplicate.size(); i++)
        {
            if (!duplicate.get(i).checkFinished())
            {
                return true;
            }
        }
        return false;
    }
    
    private void calculateAverageWaitTime()
    {
        float totalWait = 0;
        Process temp = null;
        for (int i = 0; i < processList.size(); i++)
        {
            temp = processList.get(i);
            totalWait = totalWait + temp.getWaitTime();
        }
        average = totalWait / processList.size();
        this.average = average;
    }

    public float getCompletionTime()
    {
        return (completionTime/processList.size());
    }

    public float getWaitTime()
    {
        return average;
    }

}
