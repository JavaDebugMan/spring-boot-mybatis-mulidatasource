package com.neo.entity;

/**
 * @author pengzhe
 * @date 2018/4/13 11:29
 * @description
 */

public class OldRenderEntity {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 登录名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    private Integer zone;

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "OldRenderEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
