package com.wugui.dataxweb.controller;

import com.alibaba.datax.core.Engine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: datax-all
 * @description:
 * @author: huzekang
 * @create: 2019-05-05 14:21
 **/
@RestController
public class JobContentController {

    @GetMapping("/testStartJob")
    public void testStartJob() {
        // 指定获取作业配置json的接口，此处用下面mock出来的接口提供
        String jobPath = "http://localhost:8080/mock_stream2stream";
        Engine.testStartJob(jobPath);
    }

    @GetMapping("/mock_oracle2mongodb")
    public String mock() {
        return "{\n" +
                "  \"job\": {\n" +
                "    \"setting\": {\n" +
                "      \"speed\": {\n" +
                "        \"channel\": 5\n" +
                "      }\n" +
                "    },\n" +
                "    \"content\": [\n" +
                "      {\n" +
                "        \"reader\": {\n" +
                "          \"name\": \"oraclereader\",\n" +
                "          \"parameter\": {\n" +
                "            \"username\": \"GZFUYI20190301\",\n" +
                "            \"password\": \"yibo123\",\n" +
                "            \"column\": [\n" +
                "              \"REPORT_NUMBER\",\"REPORT_DATE_SERIAL\",\"EXAM_ITEM_NAME\",\"EXAM_RESULT\"\n" +
                "            ],\n" +
                "            \"connection\": [\n" +
                "              {\n" +
                "                \"table\": [\n" +
                "                  \"TB_LIS_INDICATORS\"\n" +
                "                ],\n" +
                "                \"jdbcUrl\": [\n" +
                "                  \"jdbc:oracle:thin:@192.168.1.130:1521:gzfy\"\n" +
                "                ]\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        },\n" +
                "        \"writer\": {\n" +
                "          \"name\": \"mongodbwriter\",\n" +
                "          \"parameter\": {\n" +
                "            \"address\": [\n" +
                "              \"192.168.1.226:27017\"\n" +
                "            ],\n" +
                "            \"userName\": \"\",\n" +
                "            \"userPassword\": \"\",\n" +
                "            \"dbName\": \"datax_gzfy\",\n" +
                "            \"collectionName\": \"indicator22\",\n" +
                "            \"column\":   [\n" +
                "              { \"name\" : \"reportNumber\"         , \"type\" : \"string\"},\n" +
                "              { \"name\" : \"reportDateSerial\"    , \"type\" : \"string\"},\n" +
                "              { \"name\" : \"examItemName\"        , \"type\" : \"string\"},\n" +
                "              { \"name\" : \"examResult\"           , \"type\" : \"string\"}]\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    @GetMapping("/mock_stream2stream")
    public String mock2() {
        return "{\n" +
                "  \"job\": {\n" +
                "    \"content\": [\n" +
                "      {\n" +
                "        \"reader\": {\n" +
                "          \"name\": \"streamreader\",\n" +
                "          \"parameter\": {\n" +
                "            \"sliceRecordCount\": 10,\n" +
                "            \"column\": [\n" +
                "              {\n" +
                "                \"type\": \"long\",\n" +
                "                \"value\": \"10\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"type\": \"string\",\n" +
                "                \"value\": \"hello，你好，世界-DataX\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        },\n" +
                "        \"writer\": {\n" +
                "          \"name\": \"streamwriter\",\n" +
                "          \"parameter\": {\n" +
                "            \"encoding\": \"UTF-8\",\n" +
                "            \"print\": true\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"setting\": {\n" +
                "      \"speed\": {\n" +
                "        \"channel\": 5\n" +
                "       }\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    /**
     * 通过接口传入json配置启动一个datax作业
     * @param jobJson
     * @return
     */
    @PostMapping("/runJob")
    public String runJob(@RequestBody String jobJson) {
        Engine.startJobByJsonStr(jobJson);
        return "success";
    }


}
