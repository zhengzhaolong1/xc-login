package cn.axy.xc.xcloginprovider.cn.pojo;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "user对象",description = "用户对象user")
public class User implements Serializable {
    @ApiModelProperty(value="用户ID",required = true)
    private Integer uid;
    @ApiModelProperty(value="用户账号",required = true)
    private String username;//帐号
    @ApiModelProperty(value="用户真实姓名",required = true)
    private String name;//名称（真实姓名，不同系统不同定义）
    @ApiModelProperty(value="用户密码",required = true)
    private String password; //密码;
    @ApiModelProperty(value="用户编号",required = true)
    private String userNumber;   //用户编号
    @ApiModelProperty(value="用户昵称",required = true)
    private String userPetName; //用户昵称
    @ApiModelProperty(value="用户手机号",required = true)
    private String userphone;  //用户手机号
    @ApiModelProperty(value="用户头像",required = true)
    private String headPhoto;  //用户头像
    @ApiModelProperty(value="用户地址",required = true)
    private String userAdress;  //用户地址
    @ApiModelProperty(value="用户创建时间",required = true)
    private String creationTime;  //用户创建时间
    @ApiModelProperty(value="用户性别",required = true)
    private int userSex;   //用户性别
    @ApiModelProperty(value="用户身份证号",required = true)
    private String userCardId;   //用户身份证号
    @ApiModelProperty(value="用户积分",required = true)
    private String userScores;   //用户积分
    @ApiModelProperty(value="加密",required = true)
    private String salt;    //加盐，加密

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserPetName() {
        return userPetName;
    }

    public void setUserPetName(String userPetName) {
        this.userPetName = userPetName;
    }

    public String getUserPhone() {
        return userphone;
    }

    public void setUserPhone(String userPhone) {
        this.userphone = userPhone;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(String userAdress) {
        this.userAdress = userAdress;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getUserScores() {
        return userScores;
    }

    public void setUserScores(String userScores) {
        this.userScores = userScores;
    }


}
