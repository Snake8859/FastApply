package top.snake.fast.pojo;

public class TMember {
    private String aid;

    private String ismember;

    private String isasshead;

    private String assid;

    private String openid;

    private String templatedata;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public String getIsmember() {
        return ismember;
    }

    public void setIsmember(String ismember) {
        this.ismember = ismember == null ? null : ismember.trim();
    }

    public String getIsasshead() {
        return isasshead;
    }

    public void setIsasshead(String isasshead) {
        this.isasshead = isasshead == null ? null : isasshead.trim();
    }

    public String getAssid() {
        return assid;
    }

    public void setAssid(String assid) {
        this.assid = assid == null ? null : assid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getTemplatedata() {
        return templatedata;
    }

    public void setTemplatedata(String templatedata) {
        this.templatedata = templatedata == null ? null : templatedata.trim();
    }
}