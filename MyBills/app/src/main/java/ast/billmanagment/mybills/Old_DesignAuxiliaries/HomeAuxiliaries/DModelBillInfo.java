package ast.billmanagment.mybills.Old_DesignAuxiliaries.HomeAuxiliaries;

public class DModelBillInfo {
    public String  BillType;
    public String  City;

    public String getBillType() {
        return BillType;
    }

    public void setBillType(String billType) {
        BillType = billType;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String  Address;

    public DModelBillInfo(String billType, String city, String address) {
        BillType = billType;
        City = city;
        Address = address;
    }

    public DModelBillInfo() {
    }
}