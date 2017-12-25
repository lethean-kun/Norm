package cn.com.zhiding.user.service.impl;

import cn.com.zhiding.norm.entity.Norm;
import cn.com.zhiding.norm.mapper.NormMapper;
import cn.com.zhiding.user.entity.SZhidingEmail;
import cn.com.zhiding.user.service.SentEmail;
import cn.com.zhiding.util.Email;
import javafx.scene.input.DataFormat;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Component
public class SentEmailImpl implements SentEmail {

    @Resource
    private NormMapper normMapper;

    private static Logger log = Logger.getLogger(SentEmailImpl.class);

//    @Scheduled(cron = "0/1 0 * * * ?")
    @Override
    public void sEmail() {
        List<Norm> normList = normMapper.getNeedSent();
        log.info("待发送邮件人数为："+normList.size());
        for(Norm n:normList){
            SZhidingEmail sZhidingEmail = new SZhidingEmail();
            sZhidingEmail.setEmailAddr(n.getUser().getEmail());
            sZhidingEmail.setCrtDt(new Date());
            sZhidingEmail.setStatus("0");
            sZhidingEmail.setEmailFrom("<rencaiguan@zhiding.com.cn>");
            sZhidingEmail.setSendAccount("rencaiguan@zhiding.com.cn");
            sZhidingEmail.setSendEmail("mail.zhiding.com.cn");
            sZhidingEmail.setSendPassword("ZhiDing2016YouYuan!");
            sZhidingEmail.setSendEmailTitle("常模计算结果通知");
            sZhidingEmail.setSendWay("zd");
            if(n.getStatus().equals(2)){
                sZhidingEmail.setSendContent("<html>\n" +
                        "<body><span style=\"color: red\">"+n.getUser().getName()+"</span>您好！您计算的常模数据，已计算完成！请注意及时查看！<br />\n" +
                        "<p><span style=\"font-family:arial;\">详细信息如下：</span><br /></p>\n" +
                        "<table style=\"text-align: center\" border=\"1\" cellspacing=\"0\">\n" +
                        "    <thead>\n" +
                        "        <td>常模名称</td>\n" +
                        "        <td>常模版本</td>\n" +
                        "        <td>产品名称</td>\n" +
                        "        <td>计算时间</td>\n" +
                        "        <td>耗时</td>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n" +
                        "        <td>"+n.getNormName()+"</td>\n" +
                        "        <td>"+n.getVersion()+"</td>\n" +
                        "        <td>"+n.getProductName()+"</td>\n" +
                        "        <td>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(n.getFigureTime())+"</td>\n" +
                        "        <td>"+(Long)(n.getEndTime().getTime()-n.getStartTime().getTime())/1000+"秒</td>\n" +
                        "    </tbody>\n" +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>");
            }else {
                sZhidingEmail.setSendContent("<html>\n" +
                        "<body><span style=\"color: red\">"+n.getUser().getName()+"</span>您好！您计算的常模数据，计算失败！请注意及时查看！<br />\n" +
                        "<p><span style=\"font-family:arial;\">详细信息如下：</span><br /></p>\n" +
                        "<table style=\"text-align: center\" border=\"1\" cellspacing=\"0\">\n" +
                        "    <thead>\n" +
                        "        <td>常模名称</td>\n" +
                        "        <td>常模版本</td>\n" +
                        "        <td>产品名称</td>\n" +
                        "        <td>计算时间</td>\n" +
                        "        <td>耗时</td>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n" +
                        "        <td>"+n.getNormName()+"</td>\n" +
                        "        <td>"+n.getVersion()+"</td>\n" +
                        "        <td>"+n.getProductName()+"</td>\n" +
                        "        <td>"+n.getFigureTime()+"</td>\n" +
                        "        <td>"+(n.getEndTime().getTime()-n.getStartTime().getTime())/1000+"秒</td>\n" +
                        "    </tbody>\n" +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>");
            }
            System.out.println(n.getUser().getName());
            if(Email.InsertEmailAndMessage(sZhidingEmail)) {
                n.setIsSend(2);
                normMapper.updateByPrimaryKeySelective(n);
            }
        }

    }
}
