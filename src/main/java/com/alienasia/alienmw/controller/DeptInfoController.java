package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.DeptInfoDAO;
import com.alienasia.alienmw.dao.UserInfoDAO;
import com.alienasia.alienmw.dto.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/DeptInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class DeptInfoController {

    @Autowired
    private DeptInfoDAO service;

    @RequestMapping(value = "/list",  produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,	MediaType.APPLICATION_ATOM_XML_VALUE}  )
    public DeptInfoRes list(@RequestBody DeptInfoDTO DeptInfoDTO) {

        System.out.println("-------------list-------");
        System.out.println("--------------------" +  DeptInfoDTO );
        System.out.println("--------------------" );

        DeptInfoRes DeptInfoRes = new DeptInfoRes();
        DeptInfoRes.setDeptInfoDTO( service.getList(DeptInfoDTO) );

        DeptInfoRes.setResultCode(1);
        DeptInfoRes.setResultMessage("success");

        return DeptInfoRes   ;
    }


    @RequestMapping(value = "/update", produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,
            MediaType.APPLICATION_ATOM_XML_VALUE })
    public int update(@RequestBody List<DeptInfoDTO> DeptInfoDTO) {
        System.out.println("-------------modify-------");
        System.out.println("--------------------" + DeptInfoDTO.size());
        System.out.println("--------------------" + DeptInfoDTO);
        int res = 0;

        for (int i = 0; i < DeptInfoDTO.size(); i++) {

            res = res + service.update(DeptInfoDTO.get(i));
        }
        return res;
    }


}
