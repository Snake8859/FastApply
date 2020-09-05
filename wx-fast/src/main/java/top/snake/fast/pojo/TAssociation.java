package top.snake.fast.pojo;

public class TAssociation {
    private String assid;

    private String assname;

    private String slogan;

    private String contact;

    private String logo;

    private String creatorid;

    private String auditstatus;

    private String reason;

    private String templatecontent;

    public String getAssid() {
        return assid;
    }

    public void setAssid(String assid) {
        this.assid = assid == null ? null : assid.trim();
    }

    public String getAssname() {
        return assname;
    }

    public void setAssname(String assname) {
        this.assname = assname == null ? null : assname.trim();
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan == null ? null : slogan.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid == null ? null : creatorid.trim();
    }

    public String getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(String auditstatus) {
        this.auditstatus = auditstatus == null ? null : auditstatus.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getTemplatecontent() {
        return templatecontent;
    }

    public void setTemplatecontent(String templatecontent) {
        this.templatecontent = templatecontent == null ? null : templatecontent.trim();
    }
}