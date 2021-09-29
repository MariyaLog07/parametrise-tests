package domain;

public enum UnderlineMenuItems {
    CODE("#pull-requests-tab"),
    ISSUES("#issues-tab");

    public final String desc;

    UnderlineMenuItems(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }
}
