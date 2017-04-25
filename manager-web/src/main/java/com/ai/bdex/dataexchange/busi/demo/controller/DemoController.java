package com.ai.bdex.dataexchange.busi.demo.controller;

import com.ai.bdex.dataexchange.usercenter.dubbo.dto.DemoDTO;
import com.ai.bdex.dataexchange.usercenter.dubbo.interfaces.IDemoRSV;
import com.ai.paas.util.ImageUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by fangyunfeng on 2017/4/12.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    //@DubboConsumer
    private IDemoRSV userCenterDemoRSV;

    @Autowired
    private SolrClient solrClient;

    @RequestMapping("/init")
    public String init(Model model){
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setAddr("gx");
        String userName = userCenterDemoRSV.callDemoApi(demoDTO);
        model.addAttribute("username",userName);
        return "/demo/demo";
    }

    @RequestMapping("/solr")
    @ResponseBody
    public String testSolrClient(){

        SolrQuery params = new SolrQuery();
        params.set("q","addparam_s:*");
        params.setStart(0);
        params.set("rows",5);
        params.set("sort","accesstime_s desc");
        QueryResponse rsp = null;
        try {
            rsp = solrClient.query(params);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SolrDocumentList docs = rsp.getResults();
        System.out.println("查询内容:" + params);
        System.out.println("文档数量：" + docs.getNumFound());
        System.out.println("查询花费时间:" + rsp.getQTime());
        return "OK";
    }

    @RequestMapping("/image")
    @ResponseBody
    public String restImageUtil(){
        String url = ImageUtil.getImageServer();
        return url;
    }
}
