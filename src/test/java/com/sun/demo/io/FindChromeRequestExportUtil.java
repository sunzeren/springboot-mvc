package com.sun.demo.io;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Author by Sun, Date on 2020/11/14.
 * PS: Not easy to write code, please indicate.
 * 解析Google浏览器导出请求内容
 */
public class FindChromeRequestExportUtil {

    private List<String> resultList = new ArrayList<>();
    private String content;


    @Before
    public void init() throws IOException {
//        InputStream in = new ClassPathResource("file/log.can-dao.com.har").getInputStream();
//        FileInputStream inputStream = new FileInputStream("C:\\Users\\admin\\Desktop\\2020-11-21.har");
        FileInputStream inputStream = new FileInputStream("C:\\Users\\admin\\Desktop\\订单指派.har");
        content = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    }


    /**
     * 正则表达式匹配 响应正文
     *
     * @throws IOException
     */
    @Test
    public void findChromeRequestExport() throws IOException {
        String pattern = "(\"text\":\\s\")(\\{.*})";
        Pattern patternRex = Pattern.compile(pattern);
        Matcher matcher = patternRex.matcher(content);

        while (matcher.find()) {
            // group 0 代表整个表达式的匹配
            // ("text":\s")(\{.*}) group 0
            // ("text":\s") group 1
            // (\{.*}) group 2
            resultList.add(matcher.group(2));
        }

        if (!resultList.isEmpty()) {

            resultList.forEach((e) -> {
                JSONObject jsonObject = JSON.parseObject(e);
                //TODO Sun 2020/11/14 待匹配内容
                System.out.println(jsonObject);
            });
        } else {
            System.out.println("未有匹配结果!");
        }
    }


    @Test
    public void findLogFormHarFile() {

        //region 日志信息,关键信息匹配正则
        String msgKeyContentPattern = "orderId=\\[\\d*]";
        //endregion

        //region 自定义过滤条件
        // 需要解析的requestUrl

        // QC日志
        //        String requestUrl = "https://log.can-dao.com/log/syslog";
        // QC 标准版环境
        String requestUrl = "http://139.9.120.173:4000";
        // 过滤条件
        Predicate<String> predicate = s -> s.contains(requestUrl);
        //endregion


        // 读取文本json
        JSONObject jsonObject = JSON.parseObject(content);
        // 响应数据集合
        List<JSONObject> responseList = new ArrayList<>();

        // 响应条目
        JSONArray entriesArray = jsonObject.getJSONObject("log").getJSONArray("entries");

        // 条件过滤处理
        predicateProcess(predicate, entriesArray);

        //region 汇总响应数据列表
        // 获取响应正文 response
        for (int i = 0; i < entriesArray.size(); i++) {
            JSONObject entriesItem = entriesArray.getJSONObject(i);
            // 响应包装
            JSONObject response = entriesItem.getJSONObject("response");
            // 响应内容
            JSONObject jsonResponse = response.getJSONObject("content").getJSONObject("text");
            responseList.add(jsonResponse);
        }
        // 汇总解析接口响应数据
        responseList = mergeResponse(responseList);
        //endregion


        Pattern patternRex = Pattern.compile(msgKeyContentPattern);
        // 解析响应处理
        for (JSONObject responseItem : responseList) {
            // 待解析内容
            String msg = responseItem.getString("msg");
            Matcher matcher = patternRex.matcher(msg);
            while (matcher.find()) {
                String keyText = matcher.group();
                System.out.println(keyText);
            }
        }

    }

    @SuppressWarnings("all")
    private List<JSONObject> mergeResponse(List<JSONObject> responseList) {
        // 合并汇总为一个数组对象
        List<Object> collect = responseList.stream().flatMap(r -> {
            return r.getJSONObject("data").getJSONArray("rows").stream();
        }).collect(Collectors.toList());

        // 这里的Object其实也是JSONObject
        return ((List<JSONObject>) ((List) collect));
    }

    /**
     * 删除满足条件的类目
     *
     * @param predicate    条件
     * @param entriesArray 待处理类目
     */
    private void predicateProcess(Predicate<String> predicate, JSONArray entriesArray) {
        if (predicate == null) {
            return;
        }
        final Iterator<Object> each = entriesArray.iterator();
        while (each.hasNext()) {
            Object next = each.next();
            if (next instanceof JSONObject) {
                JSONObject entriesItem = (JSONObject) next;
                if (!predicate.test(entriesItem.getJSONObject("request").getString("url"))) {
                    each.remove();
                }
            }
        }
    }

}
