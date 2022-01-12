import java.io.*;
public class CancelOrder 
{
    public void removeOrder(String ID, user tr)
    {
        int positionID = 0;
        int positionUser = 7;
        String tempFile = "temp.txt";
        String filepath = "orderbook.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String currentLine;
        String data[];
        try 
        {

        FileWriter fw = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);

        while((currentLine = br.readLine()) != null)
        {
            data = currentLine.split(" ");
            if(!(data[positionID].equalsIgnoreCase(ID)) && !data[positionUser].equalsIgnoreCase(tr.retrieveUsername()))
            {
                pw.println(currentLine);
            }
        }

        pw.flush();
        pw.close();
        fr.close();
        br.close();
        bw.close();
        fw.close();

        oldFile.delete();
        File dump = new File (filepath);
        newFile.renameTo(dump);

        } 
        catch (Exception e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
}

}
