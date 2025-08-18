/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.esercitazione.boundary;

import is.esercitazione.boundary.ApplicationConsoleBoundary.TerminationState;

/**
 *
 * @author nonplay
 */
public class EsercitazioneISMain {
    
    /**§
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationConsoleBoundary applicationConsoleBoundary = new ApplicationConsoleBoundary();
        TerminationState exitStatus = applicationConsoleBoundary.runApplication();
        
        if (exitStatus == TerminationState.CORRECT_TERMINATION) {
            System.exit(0);
        } else {
            System.exit(-1);
        }
    }
    
}
