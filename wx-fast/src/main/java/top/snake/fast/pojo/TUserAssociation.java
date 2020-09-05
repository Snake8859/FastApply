package top.snake.fast.pojo;

public class TUserAssociation {
    private String uaid;

    private String openid;

    private String assid;

    public String getUaid() {
        return uaid;
    }

    public void setUaid(String uaid) {
        this.uaid = uaid == null ? null : uaid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getAssid() {
        return assid;
    }

    public void setAssid(String assid) {
        this.assid = assid == null ? null : assid.trim();
    }
}