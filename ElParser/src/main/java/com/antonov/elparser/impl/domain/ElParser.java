/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonov.elparser.impl.domain;

import com.antonov.elparser.pojo.PElParserParams;
import com.antonov.elparser.pojo.User;
import com.antonov.elparser.pojo.UserInfo;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlBold;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Antonov
 */
public class ElParser {

    private final PElParserParams params;

    private final ExcelWorker excelWorker;
    private final WebClient webClient;

    private static final String URL_START = "http://elibrary.ru/authors.asp";

    private final Logger logger = LoggerFactory.getLogger(getClass().getCanonicalName());

    public ElParser(PElParserParams params) {
        this.params = params;

        excelWorker = new ExcelWorker(params.getFILE_EXCEL_PATH());
        webClient = new WebClient();

//        webClient = new WebClient(BrowserVersion.getDefault(), "iron.inversion.ru", 8080);
//
//        final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
//        credentialsProvider.addCredentials("antonovdi", "rgdcjh4q");

    }

    public void doAction() throws Exception {

        String university = excelWorker.getUniversity();
        List<User> listUser = excelWorker.getUsers();

        getUserInfoFromWebSiteAndFillUserList(university, listUser);

        webClient.closeAllWindows();
        excelWorker.write(listUser);
    }

    private void getUserInfoFromWebSiteAndFillUserList(String university, List<User> listUser) throws Exception {

        try {
            for (User user : listUser) {

                HtmlPage page = webClient.getPage(URL_START);

                HtmlPage resultPage = fillFioAUserndClickSearch(page, user);

                List<UserInfo> listUserInfo = getListUserInfoOnResultPageByUniversity(resultPage, university);

                if (listUserInfo != null && listUserInfo.size() > 0) {
                    if (listUserInfo.size() > 1) {
                        user.setInfo(listUserInfo.get(0));   // доделать в будущем!!!!!!!!!!!!!!!!!!!!
                    } else {
                        user.setInfo(listUserInfo.get(0));
                    }
                }
            }
        } catch (Throwable ex) {
            String message = "В процессе работы с сайтом произошла ошибка";
            logger.error(message, ex);
            throw new Exception(message, ex);
        }
    }

    private HtmlPage fillFioAUserndClickSearch(HtmlPage page, User user) throws Exception {

        try {
            HtmlForm form = page.getFormByName("results");
            HtmlTextInput textField = form.getInputByName("surname");
            textField.setValueAttribute(user.getFIO());

            List<HtmlElement> listElements = form.getElementsByAttribute("div", "class", "butred");
            HtmlPage resultPage = listElements.get(0).click();
            return resultPage;
        } catch (Throwable ex) {
            String message = "При заполнении поля ФИО и нажатии на Поиск произошла ошибка";
            logger.error(message, ex);
            throw new Exception(message, ex);
        }
    }

    private List<UserInfo> getListUserInfoOnResultPageByUniversity(HtmlPage resultPage, String university) throws Exception {

        List<UserInfo> listUserInfo = new ArrayList<>();

        try {
            List<String> listLinkToUserInfo = new ArrayList<>();
            while (true) {
                fillListLinkUserFromPane(listLinkToUserInfo, resultPage, university);
                resultPage = getNextPage(resultPage);
                if (resultPage == null) {
                    break;
                }
            }

            listUserInfo = getUserInfoByLink(listLinkToUserInfo, webClient);
        } catch (Throwable ex) {
            String message = "При получении информации о пользователе произошла ошибка";
            logger.error(message, ex);
            throw new Exception(message, ex);
        }
        return listUserInfo;
    }

    private void fillListLinkUserFromPane(List<String> result, HtmlPage page, String university) throws Exception {

        try {

            final HtmlTable table = page.getHtmlElementById("restab");
            for (final HtmlTableRow row : table.getRows()) {
                if (row.getIndex() < 3) {
                    continue;
                }

                if (row.getCell(2).asXml().contains(university)) {
                    List a = row.getCell(3).getElementsByAttribute("a", "title", "Анализ публикационной активности автора");
                    HtmlAnchor anchor = (HtmlAnchor) a.get(0);
                    String value = anchor.getAttribute("href");
                    result.add("http://elibrary.ru/" + value);

                }
            }

        } catch (Throwable ex) {
            String message = "При заполнении массива ссылок на информацию о пользователях произошла ошибка";
            logger.error(message, ex);
            throw new Exception(message, ex);
        }
    }

    private HtmlPage getNextPage(HtmlPage page) {

        try {
            List b = page.getByXPath("//*[text()='Следующая страница']/@href");
            String link = ((DomAttr) b.get(0)).getNodeValue();

            List<HtmlElement> list = page.getFormByName("results").getElementsByAttribute("a", "href",
                    link);
            HtmlPage nextPage = list.get(0).click();
            return nextPage;
        } catch (Throwable ex) {
            return null;
        }
    }

    private List<UserInfo> getUserInfoByLink(List<String> listLinkUserInfo, WebClient webClient) throws Exception {

        List<UserInfo> result = new ArrayList<>();

        try {
            for (String urlUserInfo : listLinkUserInfo) {

                UserInfo info = new UserInfo();
                HtmlPage page = webClient.getPage(urlUserInfo);
                HtmlBold fioAtr = (HtmlBold) page.getByXPath("//*[@id=\"thepage\"]/table/tbody/tr/td/table[1]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[1]/table[1]/tbody/tr/td/div/font/b").get(0);
                String fio = fioAtr.asText();
                info.setFIO(fio);

                HtmlElement amountAtr = (HtmlElement) page.getByXPath("//*[@id=\"thepage\"]/table/tbody/tr/td/table[1]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[1]/table[2]/tbody/tr[4]/td[3]/font/a").get(0);
                Long amount = Long.valueOf(amountAtr.asText());
                info.setAMOUNT_LETTERS(amount);

                HtmlElement hirshAtr = (HtmlElement) page.getByXPath("//*[@id=\"thepage\"]/table/tbody/tr/td/table[1]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[1]/table[2]/tbody/tr[15]/td[3]/font").get(0);
                String hirsh = hirshAtr.asText();
                info.setHIRSH(hirsh);
                result.add(info);

                HtmlElement impact = (HtmlElement) page.getByXPath("//*[@id=\"thepage\"]/table/tbody/tr/td/table[1]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[1]/table[2]/tbody/tr[36]/td[3]/font").get(0);
                Double impactDouble = Double.valueOf(impact.asText().replaceAll(",", "."));
                info.setIMPACT_PUBLISH(impactDouble);
            }
        } catch (Throwable ex) {
            String message = "При получении информации о пользователе на странице информации произошла ошибка";
            logger.error(message, ex);
            throw new Exception(message, ex);
        }
        return result;
    }
}
