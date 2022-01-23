package demoyaml.controller;


import demoyaml.entity.DBConfig;
import demoyaml.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaosi on 2017/9/26.
 */
@RestController
public class DemoController {

    @Autowired
    private User user;

    @Autowired
    private DBConfig dbConfig;


    @RequestMapping("/getUser")
    public User getUser() {
        return user;
    }

    @RequestMapping("/getEnvConfig")
    public DBConfig getDbConfig() {
        return dbConfig;
    }

}
