package demoyaml.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liaosi on 2017/10/7.
 */
@Component
@ConfigurationProperties(prefix = "user")
public class User {

    private String username;
    private Integer age;
    private List<Address> contactAddress;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Address> getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(List<Address> contactAddress) {
        this.contactAddress = contactAddress;
    }
}
