/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLIENT_VIEW;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author reyes
 */
public class CLASS_QNUM_CONSTRUCTOR {
    public static ArrayList<String> data;

    public CLASS_QNUM_CONSTRUCTOR(ArrayList<String> data) {
        this.data = data;
    }

    public String getQue() {
        return data.get(0);  // queue number
    }

    public String getType() {
        return data.get(1);  // type
    }

    public String getCounter() {
        return data.get(2);  // counter
    }

    // Optional: return the full ArrayList if needed
    public ArrayList<String> getData() {
        return data;
    }

}
