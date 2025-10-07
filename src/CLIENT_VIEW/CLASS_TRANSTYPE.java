/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLIENT_VIEW;

import static CLIENT_VIEW.CLASS_QNUM_CONSTRUCTOR.data;
import java.util.ArrayList;

/**
 *
 * @author reyes
 */
public class CLASS_TRANSTYPE {
    public static ArrayList<String> TYPE;

    public CLASS_TRANSTYPE(ArrayList<String> TYPE) {
        this.TYPE = TYPE;
    }

    public String getTYPE() {
        return data.get(0); 
    }
}
