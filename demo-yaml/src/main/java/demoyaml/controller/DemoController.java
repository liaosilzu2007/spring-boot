package demoyaml.controller;


import com.google.gson.Gson;
import demoyaml.entity.DBConfig;
import demoyaml.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaosi on 2017/9/26.
 */
@RestController
public class DemoController {

    private static Gson gson = new Gson();
    @Autowired
    private User user;
    @Autowired
    private DBConfig DBConfig;


    @RequestMapping("/getUser")
    String getUser() {
        return gson.toJson(user);
    }

    @RequestMapping("/getEnvConfig")
    String getDBConfig() {
        return gson.toJson(DBConfig);
    }

}
