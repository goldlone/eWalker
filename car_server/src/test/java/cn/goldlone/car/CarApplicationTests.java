package cn.goldlone.car;

import cn.goldlone.car.model.User;
import cn.goldlone.car.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserService us;

//    @Test
    public void addUser() {
        User user = new User("18435187057", DigestUtils.sha256Hex("123"));
        System.out.println(user);
        System.out.println(user.getPassword().length());
        System.out.println(us.addUser(user));
    }

//    @Test
    public void selectUser() {
        User user = us.selectUser("18435187057");
        System.out.println(user.toString());
        User user1 = us.selectUser("123");
        System.out.println(user1==null);
    }

}
