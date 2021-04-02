package com.alienasia.alienmw.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserInfoRes extends CommonAPIResult {
	@Autowired
	private List<UserInfoDTO> userInfoDTO;
}
