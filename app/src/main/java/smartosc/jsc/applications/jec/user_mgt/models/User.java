package smartosc.jsc.applications.jec.user_mgt.models;

import lombok.Data;

@Data
public class User {
    public Integer userId;
    public String fullname;
    public String email;
}