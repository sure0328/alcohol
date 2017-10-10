package com.sinolease;

import com.sinolease.base.util.IdGenerator;
import com.sinolease.dao.BiUserDao;
import com.sinolease.model.po.BiUserPo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SinoLeasePerfAppTest {

    @Autowired
    BiUserDao biUserDao;


    @Test
    public void contextLoads() {
    }



    @Test
    @Rollback
    @Ignore
    public void testAddBiUser() {
        BiUserPo biUserPo = new BiUserPo();
        biUserPo.setId(IdGenerator.next());
        biUserPo.setUserId(985034850);
        biUserPo.setUserName("tuhuake");
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("tuhuake");
        System.out.print("==========" + pwd);
        biUserPo.setPassword(pwd);
        biUserPo.setPhone("353453");
        int ret = biUserDao.insert(biUserPo);
        System.out.println(ret);
    }

    @Test
    public void testDom(){
        Document document = DocumentHelper.createDocument();
        //设置编码方式
        document.setXMLEncoding("UTF-8");
        //设置根节点
        Element element = document.addElement("request");
        //设置子节点并且设置文本
        element.addElement("kpzdbs").addText("11111");
        element.addElement("fplxdm").addText("11111");
        element.addElement("sblx").addText("0");
        Element element1 = element.addElement("skpkl");
        element1.addElement("skpkl").addText("11111");
        element1.addElement("keypwd").addText("11111");
        element1.addElement("qmcs").addText("11111");
        String xml = element.asXML();
    }

    @Test
    public void parseXml() throws DocumentException {
        try {
            Document document1 = DocumentHelper.createDocument();
            //设置编码方式
            document1.setXMLEncoding("UTF-8");
            //设置根节点
            Element element1 = document1.addElement("request");
            //设置子节点并且设置文本
            element1.addElement("kpzdbs").addText("11111");
            element1.addElement("fplxdm").addText("11111");
            element1.addElement("sblx").addText("0");
            Element element2 = element1.addElement("skpkl");
            element2.addElement("skpkl").addText("11111");
            element2.addElement("keypwd").addText("11111");
            element2.addElement("qmcs").addText("11111");
            String xml = element1.asXML();

            Document document = DocumentHelper.parseText(xml);
            Element rootElement = document.getRootElement();
            String kpzdbs = rootElement.elementTextTrim("kpzdbs");
            String fplxdm = rootElement.elementTextTrim("fplxdm");
            String sblx = rootElement.elementTextTrim("sblx");

            System.out.println(kpzdbs);

            Element skpkl1 = rootElement.element("skpkl");
            // List elements = skpkl1.elements("list1");  //获取list1节点所有的集合
            Iterator iterator = skpkl1.elementIterator("list1");
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                //List elements = element.elements();  //获取list1节点所有的集合
                String skpkl = element.elementTextTrim("skpkl");
                String keypwd = element.elementTextTrim("keypwd");
                String qmcs = element.elementTextTrim("qmcs");

                System.out.println(skpkl);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
