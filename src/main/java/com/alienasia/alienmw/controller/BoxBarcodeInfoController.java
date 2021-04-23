package com.alienasia.alienmw.controller;

import com.alienasia.alienmw.dao.BoxBarcodeInfoDAO;
import com.alienasia.alienmw.dao.DeptInfoDAO;
import com.alienasia.alienmw.dto.BoxBarcodeInfoDTO;
import com.alienasia.alienmw.dto.BoxBarcodeInfoRes;
import com.alienasia.alienmw.dto.DeptInfoDTO;
import com.alienasia.alienmw.dto.DeptInfoRes;
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
@RequestMapping("/BoxBarcodeInfo/*")
@MapperScan(basePackages="com.alienasia.alienmw.dao")//탐색할 패키시 설정
public class BoxBarcodeInfoController {

    @Autowired
    private BoxBarcodeInfoDAO service;

    @RequestMapping(value = "/read",  produces = { MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE,	MediaType.APPLICATION_ATOM_XML_VALUE}  )
    public BoxBarcodeInfoRes read(@RequestBody BoxBarcodeInfoDTO BoxBarcodeInfoDTO) {

        System.out.println("-------------list-------");
        System.out.println("--------------------" +  BoxBarcodeInfoDTO );
        System.out.println("--------------------" );

        BoxBarcodeInfoRes boxBarcodeInfoRes = new BoxBarcodeInfoRes();
        boxBarcodeInfoRes.setBoxBarcodeInfoDTO( service.read(BoxBarcodeInfoDTO) );

        boxBarcodeInfoRes.setResultCode(1);
        boxBarcodeInfoRes.setResultMessage("success");

        return boxBarcodeInfoRes   ;
    }





}
