package com.neo.mapper.test1;

import com.neo.entity.NewRenderEntity;
import com.neo.entity.OldRenderEntity;
import com.neo.entity.UserEntity;

import java.util.List;

public interface User1Mapper {

    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(Long id);

    int selectCount();

    void insertValues(NewRenderEntity newRenderEntity);

    void insertIntoNewPlatform(OldRenderEntity oldRenderEntities);

    void insertIntoUserAccount(OldRenderEntity oldRenderEntities);

    void insertIntoUserDetails(OldRenderEntity oldRenderEntities);

    void insertIntoUserAdvanced(OldRenderEntity oldRenderEntities);


}