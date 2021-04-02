package com.alienasia.alienmw.dao;

import com.alienasia.alienmw.dto.UserDTO;

import java.util.List;

public interface UserDAO {
    List<UserDTO> selectUsers(UserDTO param) throws Exception;
}
