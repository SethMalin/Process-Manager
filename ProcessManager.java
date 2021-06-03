import java.util.*;
/**
 * Executes processes in the given order
 *
 * @Seth Malin
 * @version (a version number or a date)
 */
public class ProcessManager
{
    //Using multiple ProcessLists as data gets modified
    static ProcessList SJFprocesses = new ProcessList();
    static ProcessList FCFSprocesses = new ProcessList();
    static ProcessList RRprocesses = new ProcessList();
    static Scanner scan = new Scanner (System.in);
    static int processLength = 0;
    //static int totalLength = 0;
    static int numberProcesses = 0;
    public static void main (String args[])
    {
        do
        {
            System.out.println("To add a process enter the execution/burst time");
            System.out.println("When there are no more to add input 0");
            processLength = scan.nextInt();
            scan.nextLine();
            if (processLength < 0)
            {
                System.out.println("Please enter a positive number");
            }
            else if (processLength == 0)
            {

            }
            else
            {
                numberProcesses++;
                Process SJFnewProcess = new Process (numberProcesses, processLength);
                Process FCFSnewProcess = new Process (numberProcesses, processLength);
                Process RRnewProcess = new Process (numberProcesses, processLength);
                SJFprocesses.add(SJFnewProcess);
                FCFSprocesses.add(FCFSnewProcess);
                RRprocesses.add(RRnewProcess);
                //totalLength = totalLength + processLength;
                //continue;
            }
        }
        while (0 != processLength);
        
        //SJF
        SJFprocesses.SJF();
        float SJFaverageLength = SJFprocesses.getCompletionTime();
        float SJFwait = SJFprocesses.getWaitTime();;
        
        //FCFS
        FCFSprocesses.FCFS();
        float FCFSaverageLength = FCFSprocesses.getCompletionTime();
        float FCFSwait = FCFSprocesses.getWaitTime();
        
        //RR
        int quantum = 0;
        System.out.println("Enter the quantum/time slice please");
        quantum = scan.nextInt();
        scan.nextLine(); 
        RRprocesses.RR(quantum);
        float RRaverageLength = RRprocesses.getCompletionTime();
        float RRwait = RRprocesses.getWaitTime();
        
        //create table of results
        System.out.println ("\t\t\t\t\t  Completion Time\t    Wait Time");
        System.out.println ("\t\t\t\t\t  ---------------\t    --------");
        System.out.println ("Process  \tBurst Time\t\tSJF\tFCFS\tRR\tSJF\tFCFS\tRR");
        System.out.println ("-------  \t----------\t\t-------------------\t-------------------");
        for (int i = 0; i < numberProcesses; i++)
        {
            System.out.print ("P" + FCFSprocesses.get(i).getProcessNumber());
            System.out.print ("\t\t");
            System.out.print(FCFSprocesses.get(i).getBurstTime());
            System.out.print("\t\t\t");
            System.out.print(SJFprocesses.get(i).getCompletionTime());
            System.out.print("\t");
            System.out.print(FCFSprocesses.get(i).getCompletionTime());
            System.out.print("\t");
            System.out.print(RRprocesses.get(i).getCompletionTime());
            System.out.print("\t");
            System.out.print(SJFprocesses.get(i).getWaitTime());
            System.out.print("\t");
            System.out.print(FCFSprocesses.get(i).getWaitTime());
            System.out.print("\t");
            System.out.print(RRprocesses.get(i).getWaitTime());
            System.out.print("\n");
        }
        System.out.println ("-------  \t----------\t\t-------------------\t-------------------");
        System.out.print (FCFSprocesses.getSize() + " processes \tAverages\t\t");
        System.out.printf ("%.2f" + "\t" + "%.2f" + "\t" + "%.2f",SJFaverageLength, FCFSaverageLength, RRaverageLength);
        System.out.printf ("\t" + "%.2f"  + "\t" + "%.2f" + "\t" + "%.2f", SJFwait, FCFSwait, RRwait);
    }
}

