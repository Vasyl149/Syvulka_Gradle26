package page_bo.main_pages.commonBO;

import org.testng.Assert;
import page.common.MainMenuList;
import page_bo.GeneralBO;
import page_bo.main_pages.DraftsPageBO;
import page_bo.main_pages.EmailComposePageBO;
import page_bo.main_pages.SentPageBO;
import page_bo.main_pages.UnreadPageBO;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuBO extends GeneralBO {

    MainMenuList mainMenuList = new MainMenuList();

    public EmailComposePageBO openComposePage(){
        logger.info("open Compose page");
        mainMenuList.composeButton();
        return new EmailComposePageBO();
    }

    public DraftsPageBO draftButton() {
        mainMenuList.draftsButton();
        return new DraftsPageBO();
    }

    public SentPageBO sentButton() {
        mainMenuList.sentItemsButton();
        return new SentPageBO();
    }
    public UnreadPageBO clickUnreadButton() throws InterruptedException {
        Thread.sleep(3000);
        mainMenuList.unreadButton();
        return new UnreadPageBO();
    }

    public void verifyComposeButton() {
        Assert.assertTrue(mainMenuList.getComposeButton().isDisplayed());
    }


    public MainMenuBO verifyInboxIsSelected(){
        logger.info("Check if 'Inbox' tab is selected");
        Assert.assertTrue(mainMenuList.inboxTab().getAttribute("Class").contains("current"));
        return this;
    }

    public MainMenuBO verifyNavigationMenu() throws InterruptedException, UnsupportedEncodingException {
        logger.info("Check if menu list contains 8 items");
        Assert.assertEquals(mainMenuList.menuList().size(), 8);
        logger.info("Check if menu consist of  'Inbox', 'Drafts', 'Sent', 'Spam' etc.");
        List<String> menuListNames = new ArrayList<>();

        mainMenuList.menuList().forEach(el -> menuListNames.add(el.getText()));

        Thread.sleep(5000);
        //logger.info(MenuListEnum.getNamesOfItems());
       // logger.info(menuListNames);
        //Assert.assertTrue(MenuListEnum.getNamesOfItems().equals(menuListNames));

        logger.info("Check if icons are displayed on left menu");
        mainMenuList.iconList().forEach(ic -> Assert.assertTrue(ic.isDisplayed()));
        return this;
    }

    public MainMenuBO verifyUnreadLettersNumber(int expectedNumber){
        logger.info("Check if message counter is equal to the number of messages");
        Assert.assertTrue(mainMenuList.getCounter().equals(String.valueOf(expectedNumber)));
        return this;
    }

    public int getDraftsNumber(){
        logger.info("Get drafts number");
        return mainMenuList.getCountOfDrafts();
    }

    public UnreadPageBO verifyDraftsNumber(int newValue, int oldValue){
        logger.info("Verify Drafts number");
        Assert.assertTrue(newValue==(oldValue+1));
        return new UnreadPageBO();
    }


}
