// Craig provided the Original c++ code and input files 
// Anni Xiong translated the c++ code to java line by line

// this code takes in files with ADC and gain correction information
// then writes out the corrected ADC data according to which detector
// the ADC data comes from

import java.io.*;
import java.util.*;

public class Ne21_Craig_translate_calib {      
      public static void main (String [] args)throws FileNotFoundException {
         
         //open input files, create output file
         File f_data = new File("Ne21_Craig_detector_data.txt");
         File f_para = new File("Ne21_Craig_gain_offset_file.txt");
         PrintStream outputfile = new PrintStream("Ne21_Craig_output.txt");

         Scanner ADC_fileScan = new Scanner(f_data);
         Scanner Parameter_fileScan = new Scanner(f_para);
        
         double gain[] = new double[4];
         double offset[] = new double[4];
         int e_cor[] = new int[4];
         int energy, no;
         
         //Transport data from file to array
         for (int i = 0; i < 4; i++){
            gain[i] = Parameter_fileScan.nextDouble();
            offset[i] = Parameter_fileScan.nextDouble();
         }
         
         //input energy data, do gain correction then write to output file
         for (int i = 0; i < 25; i++) {
            energy = ADC_fileScan.nextInt();
            no = ADC_fileScan.nextInt();
            if(no == 1) {
                e_cor[0] = (int)Math.round(gain[0]*energy + offset[0]);
                outputfile.print(no+"\t"+e_cor[0]+"\n");
                //System.out.println(no+"\t"+e_cor[0]);
             } else if (no == 2) {
                e_cor[1] = (int)Math.round(gain[1]*energy + offset[1]);
                outputfile.print(no+"\t"+e_cor[1]+"\n");
                //System.out.println(no+"\t"+e_cor[1]);
            } else if (no == 3) {
                e_cor[2] = (int)Math.round(gain[2]*energy + offset[2]);
                outputfile.print(no+"\t"+e_cor[2]+"\n");
                //System.out.println(no+"\t"+e_cor[2]);
            } else {
                e_cor[3] = (int)Math.round(gain[3]*energy + offset[3]);
                outputfile.print(no+"\t"+e_cor[3]+"\n");
                //System.out.println(no+"\t"+e_cor[3]);
            }
        }
      }

}
        
                   
 