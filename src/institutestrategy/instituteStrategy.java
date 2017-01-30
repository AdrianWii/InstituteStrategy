/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package institutestrategy;

import java.util.Random;

/**
 *
 * @author mlyczkowska2
 */
public class instituteStrategy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 10;
        int [ ] [ ] C = new int [ n ] [ n ];
        double [] x = new double [n];
        
        Random generator = new Random();
        
        for (int i = 0; i < n; i++){
          x[i] = generator.nextDouble();
            
            for (int j = 0; j < n; j++){
                C[i][j] = generator.nextInt(21);
            }
        }
        
        double [] suma = new double [n];
        double wynik = 0;
        double tmp = 0;
        int q = 0;
        
        for (int j = 0; j < n; j++){
            for (int i = 0; i < n; i++){
                tmp += C[i][j] * x[i];
            }
            suma[j] = tmp;
            if(wynik < tmp){
                wynik = tmp;
                q = j+1;
            }
            tmp = 0;
        }
        
        for(int i = 0; i < n; i++){
            int ii = i + 1;
            System.out.println("x["+ ii + "]=" + x[i]);
        }
        
        for(int j = 0; j < n; j++){
            int jj = j + 1;
            System.out.println("q["+ jj + "]=" + suma[j]);
        }
        
        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                int ii = i + 1;
                int jj = j + 1;
                System.out.print("C["+ ii + "][" + jj + "]=" + C[i][j] + "     " );
            }
            System.out.println("");
        }

        System.out.println("Suma dla q["+ q + "] = 1: " + wynik);
 
    }
    
}
