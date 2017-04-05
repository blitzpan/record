package com.dailyrecord.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2017-3-31.
 */

@Controller
@RequestMapping("")
public class SystemController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value={"/index","/"}, method=RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String add(){
        return "add";
    }
}
