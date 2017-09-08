import com.alibaba.fastjson.JSON;
import com.bjike.Application;
import com.bjike.common.exception.SerException;
import com.bjike.entity.comment.Shop;
import com.bjike.entity.user.User;
import com.bjike.ser.comment.ShopSer;
import com.bjike.ser.msg.UserMessageSer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-01 09:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JunitTest {
    @Autowired
    private ShopSer shopSer;
    @Autowired
    private UserMessageSer userMessageSer;

    @Test
    public void select()throws SerException{
        List<Shop> shops = shopSer.findAll();
        System.out.println(JSON.toJSONString(shops));
        userMessageSer.unreadList("1111");
    }
}
