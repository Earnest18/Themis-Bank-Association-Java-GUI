package EMPLOYEE_VIEW;

import java.util.ArrayList;

public class CLASS_TRANSACTIONS {
    private ArrayList<String> data;

    public CLASS_TRANSACTIONS(ArrayList<String> data) {
        this.data = data;
    }

    public String getWithdraw() {
        return data.size() > 0 ? data.get(0) : "";
    }

    public String getDeposit() {
        return data.size() > 1 ? data.get(1) : "";
    }

    public String getLoan() {
        return data.size() > 2 ? data.get(2) : "";
    }

    public String getInquire() {
        return data.size() > 3 ? data.get(3) : "";
    }

    public ArrayList<String> getData() {
        return data;
    }
}
