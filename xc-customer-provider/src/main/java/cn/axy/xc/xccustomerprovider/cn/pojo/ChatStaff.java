package cn.axy.xc.xccustomerprovider.cn.pojo;

public class ChatStaff {
    private Integer staffId; //客服id
    private String  staffName; //客服姓名
    private String staffTypes; //客服权限

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffTypes() {
        return staffTypes;
    }

    public void setStaffTypes(String staffTypes) {
        this.staffTypes = staffTypes;
    }
}
