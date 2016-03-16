package security.openam.messages;

public class TokenValidationMessage {
    
    private Boolean valid;
    private String uid;
    private String realmn;
    
    
    public Boolean getValid() {
        return valid;
    }
    public void setValid(Boolean valid) {
        this.valid = valid;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getRealmn() {
        return realmn;
    }
    public void setRealmn(String realmn) {
        this.realmn = realmn;
    }
    
    
}
