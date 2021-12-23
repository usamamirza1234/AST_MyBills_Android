package ast.billmanagment.mybills.Old_DesignAuxiliaries.MainAuxiliaries;

public class DModel_BillsHistory {
    String billType;
    String amount;
    String dueDate;
    String status;

    public DModel_BillsHistory(String billType, String amount, String dueDate, String status) {
        this.billType = billType;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public DModel_BillsHistory() {

    }
}
