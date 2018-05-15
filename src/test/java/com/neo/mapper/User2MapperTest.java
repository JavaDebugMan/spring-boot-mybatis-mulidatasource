package com.neo.mapper;

import com.neo.entity.NewRenderEntity;
import com.neo.entity.OldRenderEntity;
import com.neo.entity.UserEntity;
import com.neo.enums.UserSexEnum;
import com.neo.mapper.test1.User1Mapper;
import com.neo.mapper.test2.User2Mapper;
import com.neo.utils.AESUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class User2MapperTest {

    //老平台
    @Autowired
    private User2Mapper oldMapper;

    //新平台
    @Autowired
    private User1Mapper newMapper;

    @Test
    public void testInsert() throws Exception {
        oldMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
        oldMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
        oldMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, oldMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<UserEntity> users = oldMapper.getAll();
        if (users == null || users.size() == 0) {
            System.out.println("is null");
        } else {
            System.out.println(users.toString());
        }
    }


    @Test
    public void testUpdate() throws Exception {
        UserEntity user = oldMapper.getOne(6l);
        System.out.println(user.toString());
        user.setNickName("neo");
        oldMapper.update(user);
        Assert.assertTrue(("neo".equals(oldMapper.getOne(6l).getNickName())));
    }

    @Test
    public void test() {
        int i = oldMapper.selectCount();
        NewRenderEntity newRenderEntity = new NewRenderEntity();
        newMapper.insertValues(newRenderEntity);

    }

    @Test
    public void testOld() {
        List<OldRenderEntity> oldRenderEntities = oldMapper.selectUsersFromOldPlatform();
        System.out.println(oldRenderEntities);

        for (OldRenderEntity oldRenderEntity : oldRenderEntities) {

            //老平台密码
            String oldPassword = oldRenderEntity.getPassword();

            //在老平台基础上再加一层AES密
            String newPassword = AESUtils.AESEncode(oldPassword);

            oldRenderEntity.setPassword(newPassword);

            //入用户基本信息
            newMapper.insertIntoNewPlatform(oldRenderEntity);
            //入用户账户信息
            newMapper.insertIntoUserAccount(oldRenderEntity);
            //入用户高级信息
            newMapper.insertIntoUserAdvanced(oldRenderEntity);
            //入用户详情
            newMapper.insertIntoUserDetails(oldRenderEntity);


        }


    }


}