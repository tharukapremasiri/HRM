/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ashane
 */
public class DBConnection {
    
    String Filter="Filter.txt";
    
    public void writeToDB(String string,String filename){
       FileWriter filewriter = null;
       BufferedWriter bufferedwriter = null;
        
       try {
            filewriter = new FileWriter(filename, true);
            bufferedwriter = new BufferedWriter(filewriter);
            
            bufferedwriter.write(string);
            bufferedwriter.newLine();
            bufferedwriter.close();
            filewriter.close();
            
            JOptionPane.showMessageDialog(null, "Successfully written to the file"); 
            
            
        } catch (IOException ex) {
            System.out.println("Can't write to the file");
        }
        
        
    }
    public void displayTable(JTable jTable, String fileName){
        
        File file = new File(fileName);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            DefaultTableModel model = (DefaultTableModel)jTable.getModel();
            model.setRowCount(0);
            Object[] tablerows = br.lines().toArray();

            for(int i=0; i<tablerows.length; i++){

                String line = tablerows[i].toString().trim();
                String[] dataRow = line.split(",");
                model.addRow(dataRow);
            }
            br.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found "+ex);
        } catch (IOException ex) {
            System.out.println(ex+"IO Exception occouerd ");
        }
    }
    public void fillComboFromFile(String filePath,JComboBox jComboBox1) {

        File file = new File(filePath);
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();
            
            for(int i=0;i<lines.length; i++) {
                String line = lines[i].toString();
                String[] words=line.split(",");
                jComboBox1.addItem(words[1]);
            }
            
        } catch (Exception e) {
            System.out.println("Error! Text file is not available");
        }
    }
    public void FilterData(String type,String fileName) {
        File FilterFile = new File(Filter);
        
        String oldContent="";
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            PrintWriter pw = new PrintWriter(new FileWriter(FilterFile));
            String Findline;
            
            while ((Findline = br.readLine())!=null) {

                String[]words = Findline.split(",");
                Loop:
                for (String word: words) {
                    if(word.equals(type)) {
                        oldContent = oldContent+ Findline + System.lineSeparator();
                        break Loop;
                    }
                }
            }
            pw.write(oldContent);
            pw.close();
            fr.close();
            br.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void editData( String oldCode, String newString,String filename)
    {
        File inputFile = new File(filename);
        File tempFile = new File(inputFile.getAbsolutePath()+".tmp");
        boolean found1=false;
        String oldString="";
        BufferedReader reader=null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String Findline;
            String[]words=null;
            while ((Findline = reader.readLine())!=null)
            {
                words = Findline.split(",");
                for (String word: words) {
                    
                    if(word.equals(oldCode)) 
                    {
                        found1=true;
                        oldString =Findline;
                        break;
                    }
                }
            }
            reader.close();
            if(found1) {
                
                try {    
                            reader = new BufferedReader(new FileReader(inputFile));
                            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                            String line1;
                            while ((line1 = reader.readLine())!=null) {
                                if (!line1.equals(oldString)) {
                                    pw.write(line1);
                                    pw.write("\n");
                                } else {
                                    pw.write(newString);
                                    pw.write("\n");
                                }
                            }
                            pw.flush();
                            reader.close();
                            pw.close();
                            if (!inputFile.delete()) {
                                System.out.println("Could not delete file");
                                return;
                            }
                            if (!tempFile.renameTo(inputFile))
                                System.out.println("Could not rename file");
                            JOptionPane.showMessageDialog(null, "Item details changed successfully!!",
                                    "Edit Item", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch (FileNotFoundException ex) {
                            System.out.println("File Not Found!");
                    }catch (IOException e) {
                            System.out.println("FIO Exception Occoured!");
                    }
           
            }else {
                JOptionPane.showMessageDialog(null, "The item entered doesn't exist in the list!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (HeadlessException | IOException e){
                System.out.println("FIO Exception Occoured!");
        }
        
    }
    public void deleteData(String uniqueKey,String fileName) {
        String input;
        File inputFile = new File(fileName);
        File tempFile = new File(inputFile.getAbsolutePath()+".tmp");
        boolean found=false;
        String oldString="";
        BufferedReader reader = null;
        try {
            input=uniqueKey;
            reader = new BufferedReader(new FileReader(inputFile));
            String Findline;
            while ((Findline = reader.readLine())!=null){
                String[]words = Findline.split(",");
                for (String word: words) {
                    if(word.equals(input)) {
                        found=true;
                        oldString =Findline;
                        break;
                    }
                }
            }
            reader.close();
            if(!found){
                 JOptionPane.showMessageDialog(null, "Invalid Existing Item code or Item Name!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else{
                try {    
                    reader = new BufferedReader(new FileReader(inputFile));
                    PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                    String line;
                    while ((line = reader.readLine())!=null) {
                        if (line.isEmpty()||line.equals(oldString)) {
                        } 
                        else {
                            pw.write(line);
                            pw.write("\n");
                            }
                    }
                    pw.close();
                    reader.close();
                    if (!inputFile.delete()) {
                        System.out.println("Could not delete file");
                        return;
                    }
                    if (!tempFile.renameTo(inputFile))
                        System.out.println("Could not rename file");
                        JOptionPane.showMessageDialog(null, "Item details deleted successfully!!", "Edit Item", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch (FileNotFoundException ex) {
                    System.out.println("File Not Found!");
                    }catch (IOException ex) {
                    ex.printStackTrace();
                    }  
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }  
          
    }
    public void deleteDes(String uniqueKey,String fileName) {
        String input;
        File inputFile = new File(fileName);
        File tempFile = new File(inputFile.getAbsolutePath()+".tmp");
        boolean found=false;
        String oldString="";
        BufferedReader reader = null;
        try {
            input=uniqueKey;
            reader = new BufferedReader(new FileReader(inputFile));
            String Findline;
            while ((Findline = reader.readLine())!=null){
                
                
                    if(Findline.equals(input)) {
                        found=true;
                        oldString =Findline;
                        break;
                    }
                
            }
            reader.close();
            if(!found){
                 JOptionPane.showMessageDialog(null, "Invalid Existing Item code or Item Name!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else{
                try {    
                    reader = new BufferedReader(new FileReader(inputFile));
                    PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                    String line;
                    while ((line = reader.readLine())!=null) {
                        if (line.isEmpty()||line.equals(oldString)) {
                        } 
                        else {
                            pw.write(line);
                            pw.write("\n");
                            }
                    }
                    pw.close();
                    reader.close();
                    if (!inputFile.delete()) {
                        System.out.println("Could not delete file");
                        return;
                    }
                    if (!tempFile.renameTo(inputFile))
                        System.out.println("Could not rename file");
                        JOptionPane.showMessageDialog(null, "Item details deleted successfully!!", "Edit Item", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch (FileNotFoundException ex) {
                    System.out.println("File Not Found!");
                    }catch (IOException ex) {
                    ex.printStackTrace();
                    }  
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }  
          
    }
    
}
