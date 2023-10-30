package com.example.test.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.test.pojo.RequestData;
import com.example.test.service.UserService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

@RestController
public class ChatGPTController {
    @Autowired
    UserService userRequest;

    @GetMapping("/downloadTheSelect")
    public void downloadCsvSelect(HttpServletResponse response) {
        String[] SelectAndWhereParts = outputedGeneratedCode.split("where");
        System.out.println(Arrays.toString(SelectAndWhereParts));
        String outSelect = SelectAndWhereParts[1];
        String jdbcUrl = "jdbc:mysql://rm-c********.aliyuncs.com:3306/w******a";
        String jdbcUsername = "w*********a";
        String jdbcPassword = "******";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);

            String selectSql = "SELECT * FROM sheet where "+outSelect+";";
            PreparedStatement stmt = conn.prepareStatement(selectSql);
            ResultSet resultSet = stmt.executeQuery();

            StringBuilder csvData = new StringBuilder();

            for (int i = 2; i <= resultSet.getMetaData().getColumnCount(); i++) {
                String columnName = resultSet.getMetaData().getColumnName(i);
                csvData.append(columnName).append(",");
            }
            csvData.deleteCharAt(csvData.length() - 1);
            csvData.append("\n");

            // 获取表数据
            while (resultSet.next()) {
                for (int i = 2; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String value = resultSet.getString(i);
                    csvData.append(value).append(",");
                }
                csvData.deleteCharAt(csvData.length() - 1);
                csvData.append("\n");
            }
            stmt.close();
            conn.close();

            // 设置HTTP响应头
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=data.csv");

            // 将CSV数据写入HTTP响应输出流
            try (PrintWriter writer = response.getWriter()) {
                writer.write(csvData.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("CSV文件已成功生成并传输给客户端下载");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/downloadTheFile2")
    public void downloadCsv2(HttpServletResponse response) {
        String jdbcUrl = "jdbc:mysql://r*********.rds.aliyuncs.com:3306/*****";
        String jdbcUsername = "******";
        String jdbcPassword = "*******";

        try {
            // 连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);

            // 查询数据库表数据
            String selectSql = "SELECT * FROM user;";
            PreparedStatement stmt = conn.prepareStatement(selectSql);
            ResultSet resultSet = stmt.executeQuery();

            // 创建CSV文件并写入数据
            StringBuilder csvData = new StringBuilder();

            // 获取表头信息
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                String columnName = resultSet.getMetaData().getColumnName(i);
                csvData.append(columnName).append(",");
            }
            csvData.deleteCharAt(csvData.length() - 1);
            csvData.append("\n");

            // 获取表数据
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String value = resultSet.getString(i);
                    csvData.append(value).append(",");
                }
                csvData.deleteCharAt(csvData.length() - 1);
                csvData.append("\n");
            }
            stmt.close();
            conn.close();

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=data.csv");

            // 将CSV数据写入HTTP响应输出流
            try (PrintWriter writer = response.getWriter()) {
                writer.write(csvData.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("CSV文件已成功生成并传输给客户端下载");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/downloadTheFile")
    public void downloadCsv(HttpServletResponse response) {
        String jdbcUrl = "jdbc:mysql://************lb.rds.aliyuncs.com:3306/wuluuuer_de_data";
        String jdbcUsername = "***********";
        String jdbcPassword = "**********";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);

            String selectSql = "SELECT * FROM sheet;";
            PreparedStatement stmt = conn.prepareStatement(selectSql);
            ResultSet resultSet = stmt.executeQuery();

            StringBuilder csvData = new StringBuilder();

            for (int i = 2; i <= resultSet.getMetaData().getColumnCount(); i++) {
                String columnName = resultSet.getMetaData().getColumnName(i);
                csvData.append(columnName).append(",");
            }
            csvData.deleteCharAt(csvData.length() - 1);
            csvData.append("\n");

            while (resultSet.next()) {
                for (int i = 2; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String value = resultSet.getString(i);
                    csvData.append(value).append(",");
                }
                csvData.deleteCharAt(csvData.length() - 1);
                csvData.append("\n");
            }
            stmt.close();
            conn.close();

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=data.csv");

            try (PrintWriter writer = response.getWriter()) {
                writer.write(csvData.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("CSV文件已成功生成并传输给客户端下载");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/loadnow")
    public boolean loadthesql(){
        String jdbcUrl = "jdbc:mysql://rm-*************yuncs.com:3306/wuluuuer_de_data";
        String jdbcUsername = "***********";
        String jdbcPassword = "***********";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            String useDatabaseSql = "USE xxxxxx;";
            PreparedStatement useDatabaseStmt = conn.prepareStatement(useDatabaseSql);//事实上，这个也可以作为mysql动态操作的第二个方法
            useDatabaseStmt.execute();
            String clearTableSql = "TRUNCATE TABLE sheet;";
            PreparedStatement clearStmt = conn.prepareStatement(clearTableSql);
            clearStmt.executeUpdate();
            clearStmt.close();
            System.out.println("表格内容清理完成");
            DatabaseMetaData metaData1 = conn.getMetaData();
            ResultSet columnsResultSet = metaData1.getColumns(null, null, "sheet", null);
            boolean state = false;
            int columnCount = 0;
            while (columnsResultSet.next()) {
                columnCount++;
            }
            if (columnCount > 1)
                state = true;
            columnsResultSet.close();
            if (state) {
                DatabaseMetaData metaData = conn.getMetaData();
                ResultSet resultSet = metaData.getColumns(null, null, "sheet", null);
                StringBuilder alterTableSqlBuilder1 = new StringBuilder("ALTER TABLE sheet ");
                boolean isFirstColumn = true;
                while (resultSet.next()) {
                    String columnName = resultSet.getString("COLUMN_NAME");
                    if (isFirstColumn) {
                        isFirstColumn = false;
                    } else {
                        alterTableSqlBuilder1.append("DROP COLUMN ").append(columnName).append(", ");
                    }
                }
                resultSet.close();
                String alterTableSql1 = alterTableSqlBuilder1.substring(0, alterTableSqlBuilder1.length() - 2) + ";";
                PreparedStatement alterTableStmt1 = conn.prepareStatement(alterTableSql1);
                System.out.println("表头重置语句构建完成：\n" + alterTableSql1 + "\n\n");
                alterTableStmt1.executeUpdate();
                alterTableStmt1.close();
            }
            OSS ossClient = new OSSClientBuilder().build("https://oss-cn-qingdao.aliyuncs.com",
                    "************", "*********");
            InputStreamReader inputStreamReader = new InputStreamReader(ossClient.getObject("t*********", "filehere.csv").getObjectContent());
            CSVReader csvReader = new CSVReader(inputStreamReader);
            String[] columnNames = csvReader.readNext();
            StringBuilder alterTableSqlBuilder = new StringBuilder("ALTER TABLE sheet ");
            for (String newColumnName : columnNames) {
                alterTableSqlBuilder.append("ADD ").append(newColumnName).append(" VARCHAR(255), ");
            }
            String alterTableSql = alterTableSqlBuilder.substring(0, alterTableSqlBuilder.length() - 2) + ";";
            PreparedStatement alterTableStmt = conn.prepareStatement(alterTableSql);
            System.out.println("表头建造语句构建完成：\n" + alterTableSql + "\n\n");
            alterTableStmt.executeUpdate();
            alterTableStmt.close();
            state = true;
            StringBuilder sqlBuilder = new StringBuilder("INSERT INTO sheet (");
            for (int i = 0; i < columnNames.length; i++) {
                sqlBuilder.append(columnNames[i]);
                if (i < columnNames.length - 1) {
                    sqlBuilder.append(",");
                }
            }
            sqlBuilder.append(") VALUES (");
            for (int i = 0; i < columnNames.length; i++) {
                sqlBuilder.append("?");
                if (i < columnNames.length - 1) {
                    sqlBuilder.append(", ");
                }
            }
            sqlBuilder.append(")");
            String sql = sqlBuilder.toString();
            PreparedStatement stmt = conn.prepareStatement(sql);
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                for (int i = 0; i < columnNames.length; i++) {
                    stmt.setString(i + 1, nextLine[i]);
                }
                stmt.executeUpdate();
            }
            stmt.close();
            ossClient.shutdown();
            conn.close();
            System.out.println("数据导入成功!\n\n");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    String outputedGeneratedCode = "0";
    String outputedoperationType = "0";

    @PostMapping("/request")
    public List<Map<String, Object>> handleUserRequest(@RequestBody RequestData requestData) throws JSONException, IOException {
        String userMessage = requestData.getMessage();
        String statee = requestData.getstate();
        if (Objects.equals(statee, "1")){
            if (outputedoperationType.equalsIgnoreCase("delete")) {
                List<Map<String, Object>> userList = userRequest.theDelete(outputedGeneratedCode);
                boolean successFound = false;
                List<Map<String, Object>> successOutput = new ArrayList<>();
                Map<String, Object> successMap = new HashMap<>();

                for (Map<String, Object> userMap : userList) {
                    if (userMap.containsKey("result") && userMap.get("result").equals(3)) {
                        successFound = true;
                        break;
                    }
                }
                if (successFound) {
                    successMap.put("result", "3");
                    successOutput.add(successMap);
                    return successOutput;
                } else {
                    successMap.put("result", "4");
                    successOutput.add(successMap);
                    return successOutput;
                }
            } else if (outputedoperationType.equalsIgnoreCase("select")) {
                System.out.println(outputedoperationType);
                return userRequest.theSelect(outputedGeneratedCode);
            } else if (outputedoperationType.equalsIgnoreCase("update")) {
                List<Map<String, Object>> userList = userRequest.theUpdate(outputedGeneratedCode);
                boolean successFound = false;
                List<Map<String, Object>> successOutput = new ArrayList<>();
                Map<String, Object> successMap = new HashMap<>();

                for (Map<String, Object> userMap : userList) {
                    if (userMap.containsKey("result") && userMap.get("result").equals(5)) {
                        successFound = true;
                        break;
                    }
                }
                if (successFound) {
                    successMap.put("result", "5");
                    successOutput.add(successMap);
                    return successOutput;
                } else {
                    successMap.put("result", "6");
                    successOutput.add(successMap);
                    return successOutput;
                }


            } else if (outputedoperationType.equalsIgnoreCase("insert")) {
                List<Map<String, Object>> userList = userRequest.theInsert(outputedGeneratedCode);
                boolean successFound = false;
                List<Map<String, Object>> successOutput = new ArrayList<>();
                Map<String, Object> successMap = new HashMap<>();

                for (Map<String, Object> userMap : userList) {
                    if (userMap.containsKey("result") && userMap.get("result").equals(1)) {
                        successFound = true;
                        break;
                    }
                }
                if (successFound) {
                    successMap.put("result", "1");
                    successOutput.add(successMap);
                    return successOutput;
                } else {
                    successMap.put("result", "2");
                    successOutput.add(successMap);
                    return successOutput;

                }
            } else return new ArrayList<>();

        }
        else {
            List<String> sheetTableHeaders = userRequest.getSheetTableHeaders();
            System.out.println("提取表头信息如下：\n"+sheetTableHeaders);
            String userMessageMessage = "你现在是一个智能生成MySQL语句的机器人，在这个段落之后我会给你一个语句，格式是‘操作+列名的集合’，如'" +
                    "我想知道姓张的人的平均年龄是什么。[id, name, age, money, heigth]'，句子的内容是在人类对于表格sheet的语言操作，然后你" +
                    "应该严格根据我的语句和严格按照列名生成如下格式的回复：'操作类型+mysql代码'，如'select+select avg(age) from sheet where " +
                    "name like '张%''。我的表头可能有很多英文，所以我希望你可以自动将我需要查询的表头自动匹配对应的英文表头" +
                    "我输入的中文有些使用拼音，希望你可以判断出来对应的表头，sql语句前面必须加" +
                    "上操作类型和加号,只要一行代码，不要换行，不要说其他的无关的话,sql语句前面必须额外加上操作类型和加" +
                    "号,如果是新增数据，没有指出的地方应保持null。下面请你根据以下语句生成对应语句：" + userMessage + "。" + sheetTableHeaders;

            String pro = "127.0.0.1";
            int pro1 = 7890;
            HttpHost httpHost = new HttpHost(pro, pro1);
            RequestConfig build = RequestConfig.custom().setProxy(httpHost).build();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost("https://api.openai.com//v1/chat/completions");
            post.addHeader("Content-Type", "application/json");
            post.addHeader("Authorization", "Bearer sk-3Q*************V0");
            post.setConfig(build);
            String paramJson = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\",\"content\":\"" + userMessageMessage + "\"}], \"temperature\": 0.1}";
            StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
            post.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(post);

            HttpEntity entity = response.getEntity();
            String apiResponse = EntityUtils.toString(entity, "UTF-8");
            JSONObject jsonResponse = new JSONObject(apiResponse);
            JSONArray choicesArray = jsonResponse.getJSONArray("choices");
            JSONObject choiceObj = choicesArray.getJSONObject(0);
            JSONObject qmessageObj = choiceObj.getJSONObject("message");
            String generatedCode = qmessageObj.getString("content");

            System.out.println("返回语句:\n" + generatedCode + "\n\n");
            String[] parts = generatedCode.split("\\+");
            outputedoperationType = parts[0];
            System.out.println("操作类型:\n" + outputedoperationType + "\n\n");
            outputedGeneratedCode = parts[1];
            System.out.println("操作语句:\n" + outputedGeneratedCode + "\n\n\n\n");
            if(Objects.equals(outputedoperationType, "select")){
                return new ArrayList<>();
            }else if (Objects.equals(outputedoperationType, "insert")){
                List<Map<String, Object>> resultList =  new ArrayList<>();
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("result", 1);
                resultList.add(resultMap);
                return resultList;
            }else {
                String[] updateAndWhereParts = parts[1].split("where");
                String mass = "select * from sheet where "+ updateAndWhereParts[1];
                System.out.println(mass);
                List<java.util.Map<java.lang.String,java.lang.Object>> mas = userRequest.theSelect(mass);
                System.out.println(mas);
                return mas;
            }
        }
    }
}
