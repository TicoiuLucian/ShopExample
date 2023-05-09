package ro.itschool.mapper;

import ro.itschool.controller.model.MyUserDTO;
import ro.itschool.entity.MyUser;

public class MyUserMapper {

    public static MyUserDTO fromEntity(MyUser user) {
        return new MyUserDTO(user.getEmail(), user.getFullName());
    }

    public static MyUser toEntity(MyUserDTO userDTO) {
        MyUser myUser = new MyUser();
        myUser.setFullName(userDTO.getFullName());
        myUser.setPassword(userDTO.getPassword());
        myUser.setEmail(userDTO.getEmail());
        return myUser;
    }
}
