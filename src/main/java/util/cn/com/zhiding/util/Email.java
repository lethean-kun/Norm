package cn.com.zhiding.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Properties;

import cn.com.zhiding.user.entity.SZhidingEmail;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Email {

    public static String DRIVER = "";
    public static String URL = "";
    public static String USERNAME = "";
    public static String PASSWORD = "";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;



    public Email() {
        try {
            URL reportUrl = this.getClass().getClassLoader()
                    .getResource("datasource.properties");
            InputStream in;
            in = reportUrl.openStream();
            Properties p = new Properties();
            p.load(in);
            DRIVER = p.getProperty("email.driver");
            URL =  p.getProperty("email.url");
            USERNAME =  p.getProperty("email.username");
            PASSWORD = p.getProperty("email.password");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        connection = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

    public ResultSet executeQuery(String sql) throws Exception {
        connection = this.getConnection();
        preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    public boolean execute(String sql) throws Exception {
        connection = this.getConnection();
        preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        return preparedStatement.execute();

    }

    public int executeUpdate(String sql, Object[] obj) throws Exception {
        connection = this.getConnection();
        preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
            preparedStatement.setObject(i + 1, obj[i]);
        }
        return preparedStatement.executeUpdate();
    }

    public void closeAll() throws Exception {
        if (null != resultSet) {
            resultSet.close();
        }
        if (null != preparedStatement) {
            preparedStatement.close();
        }
        if (null != connection) {
            connection.close();
        }
    }

    /**
     *
     * @param Email  邮件对象
//     * @param Mess  短信对象
//     * @param operflag 操作标识（0短信，1邮件）
     * @return
     */
    public static boolean InsertEmailAndMessage(SZhidingEmail Email){
        Email mysqlDao = new Email();
        StringBuffer sb = new StringBuffer();
        try {
            if (Email!=null && Email.getEmailAddr() != null) {
                sb.append("insert into s_zhiding_email (zdyy_result_id,email_addr, crt_dt, status,"
                        + "email_from,"
                        + "send_content,send_mail,send_account,send_password,send_way,"
                        + "send_email_title) values(");
                sb.append("'-1',");
                sb.append("'"+Email.getEmailAddr().trim()+"',");
                sb.append("'"+new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(Email.getCrtDt())+"',");
                sb.append("'"+Email.getStatus()+"',");
                sb.append("'"+Email.getEmailFrom().trim()+"',");
                sb.append("'"+Email.getSendContent()+"',");
                sb.append("'"+Email.getSendEmail()+"',");
                sb.append("'"+Email.getSendAccount()+"',");
                sb.append("'"+Email.getSendPassword()+"',");
                sb.append("'"+Email.getSendWay()+"',");
                sb.append("'"+Email.getSendEmailTitle()+"')");
                System.out.println(sb.toString());
                mysqlDao.execute(sb.toString());
            }

            mysqlDao.closeAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
