package ast.billmanagment.mybills.Old_DesignAuxiliaries.MainAuxiliaries;


public class DModel_Bills {


    public DModel_Bills() {
    }

    String BillType;
    String Refference_number;
    String Account_number;

    public DModel_Bills(String billType, String refference_number, String account_number) {
        BillType = billType;
        Refference_number = refference_number;
        Account_number = account_number;
    }
}

