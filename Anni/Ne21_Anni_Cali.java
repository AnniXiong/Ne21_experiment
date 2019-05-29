// Anni Xiong
// Calibrate ADC from each individual detector  
// to obtain one clover detector spectrum

import java.io.*;
import java.util.*;

public class Ne21_Anni_Cali {      
      public static void main (String [] args)throws FileNotFoundException {
         // Fake data from orevious sort routine
         int eCh5 = 20;
         int eCh6 = 300;
         int eCh7 = 0;
         int eCh8 = 4000;
         
         int eCh9 = 1000;
         int eCh10 = 450;
         int eCh11 = 1200;
         int eCh12 = 3000;
         
         /* 
         hCh0.inc(eCh0);//Ge #1
         hCh1.inc(eCh1);//Ge #2
         hCh2.inc(eCh2);//Ge #3
         hCh3.inc(eCh3);//Ge #4
         ...
         hCh9.inc(eCh5);//Ge #10
         hCh10.inc(eCh6);//Ge #11
         hCh11.inc(eCh7);//Ge #12
         */
         File cali_file = new File("Ne21_Anni_cali_para.txt");
         Scanner cail_para = new Scanner(cali_file);
         
         int [] clover_adc = {eCh5,eCh6,eCh7,eCh8,eCh9,eCh10,eCh11,eCh12};
         int i = 0;
         while(cail_para.hasNext()){
            //Read in gain and offset from Ne21_cail_para.txt
            String name = cail_para.next();
            double gain = cail_para.nextDouble();
            double  offset = cail_para.nextDouble();
            //Do correction and overwrite the original adc array 
            if (clover_adc[i] == 0) {
               clover_adc[i] = 0;
            } else {
               clover_adc[i] = (int)Math.round(gain * clover_adc[i] + offset);
            }
            System.out.println(name+" :"+gain+ ","+offset+"; corrected " +clover_adc[i]); 
            i++;     
          }
            //Sum calibrated data for 2 clover detectors
         int Eclover1 = clover_adc[0] + clover_adc[1]+ clover_adc[2] + clover_adc[3];
         int Eclover2 = clover_adc[4] + clover_adc[5]+ clover_adc[6] + clover_adc[7];
         System.out.println("clover 1 tot E "+Eclover1+"; clover 2 tot E "+Eclover2);     
        /* 
         clover_hCh1.inc(Eclover1);//Ge #1
         clover_hCh2.inc(Eclover2);//Ge #2
        */     
      }          
}