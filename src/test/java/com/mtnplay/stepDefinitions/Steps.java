package com.mtnplay.stepDefinitions;

import com.mtnplay.pageObjects.MyProfilePage;
import com.mtnplay.pageObjects.MyPlay_FilterContent;
import com.mtnplay.pageObjects.FrontEndPlayer_And_RelatedIntegrationPage;
import com.mtnplay.pageObjects.MyPlaySearchP_PurchasesPage;
import com.mtnplay.pageObjects.SubscribeToWatch_SubscriptionPage;
import com.mtnplay.pageObjects.PurchaseToWatch_OnceOffPage;
import com.mtnplay.pageObjects.UsefulMethods;
import com.mtnplay.pageObjects.Glob_LoginPage;
import com.mtnplay.pageObjects.MyPlay_Screen_Search_SubscriptionPage;
import com.mtnplay.pageObjects.MyPlay_Screen_Search_PurchasesPage;
import com.mtnplay.pageObjects.Full_SearchFunctionalityPage;
import com.mtnplay.pageObjects.MyPlay_Screen_View_SubscriptionPage;
import com.mtnplay.pageObjects.Unsubscribe_ContentPage;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
//import sun.font.SunFontManager;


public class Steps {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public Glob_LoginPage glob_loginPage;
    public MyProfilePage myprofilePage;
    public MyPlay_FilterContent myPlay_filterContent;
    public FrontEndPlayer_And_RelatedIntegrationPage frontEndPlayer_and_relatedIntegrationPage;
    public MyPlaySearchP_PurchasesPage myPlaySearchP_purchasesPage;
    public SubscribeToWatch_SubscriptionPage subscribeToWatch_subscriptionPage;
    public PurchaseToWatch_OnceOffPage purchaseToWatch_onceOffPage;
    public MyPlay_Screen_Search_SubscriptionPage myPlay_screen_searchSubscriptionPage;
    public MyPlay_Screen_Search_PurchasesPage myPlay_screen_search_purchasesPage;
    public Full_SearchFunctionalityPage full_searchFunctionalityPage;
    public MyPlay_Screen_View_SubscriptionPage myPlay_screen_view_subscriptionPage;
    public Unsubscribe_ContentPage unsubscribe_contentPage;

    private String readPropertiesFile() {

        Properties prop = new Properties();
        try {

            InputStream input = new FileInputStream("C:\\Users\\lunga.shibani\\Documents\\Lunga_Work(MTN)\\api-testing-rest-assured-java-framework-master\\application.properties");
            prop.load(input);
            System.out.println(prop.getProperty("CHROME_DRIVER"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty("CHROME_DRIVER");
    }

    @Before
    public void doSomethingBefore(Scenario scenario) {
        if(System.getProperty("env") == null){
            System.setProperty("env", "sit");
        }
        System.out.println("Environment set to: " + System.getProperty("env"));
        System.setProperty("webdriver.chrome.driver", readPropertiesFile());
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 30);
        actions = new Actions(driver);
        glob_loginPage = new Glob_LoginPage(driver, wait);
        myprofilePage = new MyProfilePage(driver, wait);
        myPlay_filterContent = new MyPlay_FilterContent(driver, wait);
        frontEndPlayer_and_relatedIntegrationPage = new FrontEndPlayer_And_RelatedIntegrationPage(driver, wait);
        myPlaySearchP_purchasesPage = new MyPlaySearchP_PurchasesPage(driver, wait);
        subscribeToWatch_subscriptionPage = new SubscribeToWatch_SubscriptionPage(driver, wait);
        purchaseToWatch_onceOffPage = new PurchaseToWatch_OnceOffPage(driver, wait);
        myPlay_screen_searchSubscriptionPage = new MyPlay_Screen_Search_SubscriptionPage(driver, wait);
        myPlay_screen_search_purchasesPage = new MyPlay_Screen_Search_PurchasesPage(driver, wait);
        full_searchFunctionalityPage = new Full_SearchFunctionalityPage(driver, wait);
        myPlay_screen_view_subscriptionPage = new MyPlay_Screen_View_SubscriptionPage(driver, wait);
        unsubscribe_contentPage = new Unsubscribe_ContentPage(driver, wait);
    }

    @After
    public void doSomethingAfter(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // embed it in the report.
            scenario.attach(screenshot, "image/png",scenario.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User launch Chrome browser")
    public void user_launch_chrome_browser() {
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Driver/chromedriver_v95.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /*
        THIS IS JUST FOR EXPERIMENTAL PURPOSES
     */

    @Given("I have opened the App.dev page")
    public void i_have_opened_the_app_dev_page() throws Exception{
        glob_loginPage.OpenPage();
    }


    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    /*
        THIS IS JUST FOR EXPERIMENTAL PURPOSES
     */


    @Given("I have Logged via the backend and stored id token")
    public void iHaveLoggedInViaTheBackEndAndStoredTheIdToken() {

        String baseURL = null;
        String clientID = null;
        if(System.getProperty("env").equals("dev")){
            baseURL = "https://www.ilabquality.com";
            clientID = "0bb1fef9-57a1-4b5d-809b-8e8d873f79d9";
        } else if (System.getProperty("env").equals("dev")) {
            baseURL = "https://www.ilabquality.com";
            clientID = "d13f746b-1ce3-4897-9b07-a73f78809fda";
        }
        RestAssured.baseURI = baseURL;
        Response response =
                given()
                        .params("grant_type", "password")
                        .params("client_id", clientID)
                        .params("loginId", "ckent@digitalzenith.io")
                        .params("password", "Superm@n")
                        .params("scope", "openid")
                        //.body(requestBody)
                        .when()
                        .post("/oauth2/token");

        UsefulMethods.access_token = response.body().jsonPath().getJsonObject("access_token");
        UsefulMethods.id_token = response.body().jsonPath().getJsonObject("id_token");
        Assert.assertEquals(200,response.statusCode());
        System.out.println("1. Login Success Details -----------------------------------------------------------");
        System.out.println(response.getBody().prettyPeek());
        System.out.println("------------------------------------------------------------------------------------");
    }

    @Given("I have deleted Candidate with the family name {string}")
    public void iHaveDeletedCandidateWithTheFamilyName(String familyName) {
        String baseURL = null;
        if(System.getProperty("env").equals("sit")){
            baseURL = "https://apisit.digitalzenith.io";
        } else if (System.getProperty("env").equals("dev")) {
            baseURL = "https://apidev.digitalzenith.io";
        }

        // This class is ued to create the GraphQL Query needed to seach for all Candidates with a specific family name
        // And to return all the ID's
        class GraphQLQuery {

            private String query;

            public void setQuery(String query) {
                this.query = query;
            }

            public String getQuery() {
                return query;
            }
        }

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("{ ksql_refactor_v_candidate_library {\n" +
                "        Candidates(where: {family_name: {_ilike: \"%"+familyName+"%\"}}) {\n" +
                "          id\n" +
                "        }\n" +
                "      } }");

        // This is currently hardcoded to DEV. A more elegant solution would be to store this somewhere else
        RestAssured.baseURI = baseURL;
        System.out.println("2. GraphQL Query -------------------------------------------------------------");
        System.out.println(query.getQuery());

        System.out.println("------------------------------------------------------------------------------");
        Response response =
                given()
                        .header("Authorization", "Bearer "+UsefulMethods.id_token)
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(query)
                        .when()
                        .post("/query/graphql/v1/graphql");
        System.out.println("3. GraphQL Response ----------------------------------------------------------");
        response.getBody().prettyPrint();
        System.out.println("------------------------------------------------------------------------------");
        Assert.assertEquals(200,response.statusCode());
        // Now we have a list of Candidate ID's
        List<Map<String,String>> candidateListIDs = response.jsonPath().getJsonObject("data.ksql_refactor_v_candidate_library[0].Candidates");

        // For each ID found we run the DELETE api call
        // Run the delete script if any Candidate ID's were found
        System.out.println("4. Records Deleted ---------------------------------------------------------");
        if(!candidateListIDs.equals(null)){
            for(Map<String,String> candidate : candidateListIDs){
                Response response2 =
                        given()
                                .header("Authorization", "Bearer "+UsefulMethods.access_token)
                                .when()
                                .delete("/command/candidates/"+candidate.get("id"));
                assertThat(response2.getStatusCode(),equalTo(202));
                System.out.println("Deleted ID = " + response2.getBody().prettyPrint());
            }
        }else{
            System.out.println("No Candidates found with family name - " + familyName);
        }
        System.out.println("----------------------------------------------------------------------------");
    }

    //#######################################################MTN_Play 2.0 Feature Definition############################################################

    @When("User navigates to their profile page and updates")
    public void user_navigates_to_their_profile_page_and_updates() {
        wait.until(ExpectedConditions.visibilityOf(myprofilePage.btnManage));
        myprofilePage.btnManage.click();

        wait.until(ExpectedConditions.visibilityOf(myprofilePage.btnMyProfile));
        myprofilePage.btnMyProfile.click();

        wait.until(ExpectedConditions.visibilityOf(myprofilePage.txtPhoneNumber));
        myprofilePage.txtPhoneNumber.sendKeys("0977832135");

        wait.until(ExpectedConditions.visibilityOf(myprofilePage.txtName));
        myprofilePage.txtName.sendKeys("Tester");

        wait.until(ExpectedConditions.visibilityOf(myprofilePage.txtEmailAddress));
        myprofilePage.txtEmailAddress.sendKeys("tester@gmail.com");

        wait.until(ExpectedConditions.visibilityOf(myprofilePage.btnBirthday));
        myprofilePage.btnBirthday.sendKeys("20010420");

        wait.until(ExpectedConditions.visibilityOf(myprofilePage.btnDropDownLocation));
        myprofilePage.btnDropDownLocation.click();

        //wait.until(ExpectedConditions.visibilityOf(myprofilePage.btnDropDownLocation_Item));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[7]/select/option[3]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        //option[contains(text(), 'Ghana')]

        wait.until(ExpectedConditions.visibilityOf(myprofilePage.btnDropDownLanguage));
        //myprofilePage.btnDropDownLanguage.click();
        wait.until(ExpectedConditions.visibilityOf(myprofilePage.btnDropDownLanguage_Item));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement eleme = driver.findElement(By.id("language"));
        JavascriptExecutor exece = (JavascriptExecutor)driver;
        exece.executeScript("arguments[0].click();", eleme);

        wait.until(ExpectedConditions.visibilityOf(myprofilePage.btnDropDownLanguage_Item));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement elem = driver.findElement(By.xpath("//option[contains(text(), 'Swahili')]"));
        JavascriptExecutor exec = (JavascriptExecutor)driver;
        exec.executeScript("arguments[0].click();", elem);
    }

    @When("User navigates to the My Play Screen and filters content")
    public void user_navigates_to_the_my_play_screen_and_filters_content() {
        wait.until(ExpectedConditions.visibilityOf(myPlay_filterContent.btnMyPlay));
        myPlay_filterContent.btnMyPlay.click();

//        wait.until(ExpectedConditions.visibilityOf(myPlay_filterContent.btnPurchases));
        myPlay_filterContent.btnPurchases.click();

//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        WebElement elem = driver.findElement(By.xpath("//div[contains(text(), 'PURCHASES')]"));
//        JavascriptExecutor exec = (JavascriptExecutor)driver;
//        exec.executeScript("arguments[0].click();", elem);

        wait.until(ExpectedConditions.visibilityOf(myPlay_filterContent.btnSubscriptions));
        myPlay_filterContent.btnSubscriptions.click();
    }

    @Then("User verifies content is only for subscription")
    public void user_verifies_content_is_only_for_subscription() {
        wait.until(ExpectedConditions.visibilityOf(myPlay_filterContent.txtContainsSubscription));
        assertThat(myPlay_filterContent.txtContainsSubscription.getText(), containsString("subscription"));
        System.out.println("true");
    }

    @When("User navigates to the My Play Screen to and plays content")
    public void user_navigates_to_the_my_play_screen_to_and_plays_content() {

        //wait.until(ExpectedConditions.visibilityOf(myPlay_filterContent.btnMyPlay));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement elem = driver.findElement(By.xpath("//span[contains(text(), 'my play')]"));
        JavascriptExecutor exec = (JavascriptExecutor)driver;
        exec.executeScript("arguments[0].click();", elem);
//        myPlay_filterContent.btnMyPlay.click();

        wait.until(ExpectedConditions.visibilityOf(myPlay_filterContent.btnPurchases));
        myPlay_filterContent.btnPurchases.click();

        wait.until(ExpectedConditions.visibilityOf(frontEndPlayer_and_relatedIntegrationPage.freeContentType));
        frontEndPlayer_and_relatedIntegrationPage.freeContentType.click();

        wait.until(ExpectedConditions.visibilityOf(frontEndPlayer_and_relatedIntegrationPage.btnWatchForFree));
        frontEndPlayer_and_relatedIntegrationPage.btnWatchForFree.click();

    }

    @When("User navigates to the My Play Screen and searches for content on purchases")
    public void user_navigates_to_the_my_play_screen_and_searches_for_content_on_purchases() {

        wait.until(ExpectedConditions.visibilityOf(myPlay_filterContent.btnMyPlay));
        myPlay_filterContent.btnMyPlay.click();

        wait.until(ExpectedConditions.visibilityOf(myPlay_filterContent.btnPurchases));
        myPlay_filterContent.btnPurchases.click();

        wait.until(ExpectedConditions.visibilityOf(myPlaySearchP_purchasesPage.btnSearch));
        myPlaySearchP_purchasesPage.btnSearch.click();

        wait.until(ExpectedConditions.visibilityOf(myPlaySearchP_purchasesPage.searchField));
        myPlaySearchP_purchasesPage.searchField.sendKeys("black Panther");
    }

    /**
     *This method
     */
    @When("User subscribes to content to watch")
    public void user_subscribes_to_content_to_watch() {

//        wait.until(ExpectedConditions.visibilityOf(subscribeToWatch_subscriptionPage.subscriptionItem));
//        subscribeToWatch_subscriptionPage.subscriptionItem.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement elem = driver.findElement(By.xpath("/html/body/div[2]/div/div[5]/div[2]/div[8]/div[1]"));
        JavascriptExecutor exec = (JavascriptExecutor)driver;
        exec.executeScript("arguments[0].click();", elem);

//        wait.until(ExpectedConditions.visibilityOf(subscribeToWatch_subscriptionPage.rdBtnChoosePlan));
//        subscribeToWatch_subscriptionPage.rdBtnChoosePlan.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement eleme = driver.findElement(By.xpath("//input[@value='Week']"));
        JavascriptExecutor exe = (JavascriptExecutor)driver;
        exe.executeScript("arguments[0].click();", eleme);

        wait.until(ExpectedConditions.visibilityOf(subscribeToWatch_subscriptionPage.btnSubscribeToWatch));
        subscribeToWatch_subscriptionPage.btnSubscribeToWatch.click();

        wait.until(ExpectedConditions.visibilityOf(subscribeToWatch_subscriptionPage.rdBtnAirtime));
        subscribeToWatch_subscriptionPage.rdBtnAirtime.click();

        wait.until(ExpectedConditions.visibilityOf(subscribeToWatch_subscriptionPage.btnConfirmPurchase));
        subscribeToWatch_subscriptionPage.btnConfirmPurchase.click();

//        wait.until(ExpectedConditions.visibilityOf(subscribeToWatch_subscriptionPage.btnConfirm));
//        subscribeToWatch_subscriptionPage.btnConfirm.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement ele = driver.findElement(By.xpath("//div[contains(text(), 'confirm')]"));
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", ele);

        wait.until(ExpectedConditions.visibilityOf(subscribeToWatch_subscriptionPage.btnWatchNow));
        subscribeToWatch_subscriptionPage.btnWatchNow.click();
    }

    @When("User subscribes to content to watch Once Off content")
    public void user_subscribes_to_content_to_watch_once_off_content() {

//        wait.until(ExpectedConditions.visibilityOf(subscribeToWatch_onceOffPage.subscriptionItem));
//        subscribeToWatch_onceOffPage.subscriptionItem.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement ele = driver.findElement(By.xpath("/html/body/div[2]/div/div[5]/div[2]/div[6]/div[1]"));
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", ele);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement elem = driver.findElement(By.xpath("//div[contains(text(), 'Purchase for R407')]"));
        JavascriptExecutor exe = (JavascriptExecutor)driver;
        exe.executeScript("arguments[0].click();", elem);

        wait.until(ExpectedConditions.visibilityOf(purchaseToWatch_onceOffPage.rdBtnAirtime));
        purchaseToWatch_onceOffPage.rdBtnAirtime.click();

        wait.until(ExpectedConditions.visibilityOf(purchaseToWatch_onceOffPage.btnConfirmPurchase));
        purchaseToWatch_onceOffPage.btnConfirmPurchase.click();

        wait.until(ExpectedConditions.visibilityOf(purchaseToWatch_onceOffPage.btnConfirm));
        purchaseToWatch_onceOffPage.btnConfirm.click();

        wait.until(ExpectedConditions.visibilityOf(purchaseToWatch_onceOffPage.btnWatchNow));
        purchaseToWatch_onceOffPage.btnWatchNow.click();
    }

    @When("User searches for content on my play screen using the search button")
    public void user_searches_for_content_on_my_play_screen_using_the_search_button() {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement ele = driver.findElement(By.xpath("//span[contains(text(), 'my play')]"));
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", ele);

//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        WebElement elem = driver.findElement(By.xpath("//input[@class='search-input']"));
//        JavascriptExecutor exe = (JavascriptExecutor)driver;
//        exe.executeScript("arguments[0].click();", elem);

        wait.until(ExpectedConditions.visibilityOf(myPlay_screen_searchSubscriptionPage.btnSearch));
        myPlay_screen_searchSubscriptionPage.btnSearch.click();

        wait.until(ExpectedConditions.visibilityOf(myPlay_screen_searchSubscriptionPage.txtSearchInputField));
        myPlay_screen_searchSubscriptionPage.txtSearchInputField.sendKeys("We");
    }

    @When("User searches for purchases content on my play screen using the search button")
    public void user_searches_for_purchases_content_on_my_play_screen_using_the_search_button() {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement ele = driver.findElement(By.xpath("//span[contains(text(), 'my play')]"));
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", ele);

//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        WebElement elem = driver.findElement(By.xpath("//input[@class='search-input']"));
//        JavascriptExecutor exe = (JavascriptExecutor)driver;
//        exe.executeScript("arguments[0].click();", elem);

        wait.until(ExpectedConditions.visibilityOf(myPlay_screen_search_purchasesPage.btnPurchases));
        myPlay_screen_search_purchasesPage.btnPurchases.click();

        wait.until(ExpectedConditions.visibilityOf(myPlay_screen_searchSubscriptionPage.btnSearch));
        myPlay_screen_searchSubscriptionPage.btnSearch.click();

        wait.until(ExpectedConditions.visibilityOf(myPlay_screen_searchSubscriptionPage.txtSearchInputField));
        myPlay_screen_searchSubscriptionPage.txtSearchInputField.sendKeys("We");
    }
    //full_searchFunctionalityPage
    @When("User searches full Search functionality from home screen")
    public void user_searches_full_search_functionality_from_home_screen() {

        wait.until(ExpectedConditions.visibilityOf(myPlay_screen_searchSubscriptionPage.btnSearch));
        myPlay_screen_searchSubscriptionPage.btnSearch.click();

        wait.until(ExpectedConditions.visibilityOf(myPlay_screen_searchSubscriptionPage.txtSearchInputField));
        myPlay_screen_searchSubscriptionPage.txtSearchInputField.sendKeys("Lasso");
    }
    //mtnPlay_screen_view_subscriptionPage
    @When("User view subscribed content on My Play screen")
    public void user_view_subscribed_content_on_my_play_screen() {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement ele = driver.findElement(By.xpath("//span[contains(text(), 'my play')]"));
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", ele);
    }

    @When("User unsubscribes content through My Profile screen")
    public void user_unsubscribes_content_through_my_profile_screen() {

//        wait.until(ExpectedConditions.visibilityOf(myprofilePage.btnManage));
//        myprofilePage.btnManage.click();
        wait.until(ExpectedConditions.visibilityOf(unsubscribe_contentPage.btnManage));
        unsubscribe_contentPage.btnManage.click();

        wait.until(ExpectedConditions.visibilityOf(unsubscribe_contentPage.lnkSubscription));
        unsubscribe_contentPage.lnkSubscription.click();

        wait.until(ExpectedConditions.visibilityOf(unsubscribe_contentPage.getLnkSubscriptionContent));
        unsubscribe_contentPage.getLnkSubscriptionContent.click();

        wait.until(ExpectedConditions.visibilityOf(unsubscribe_contentPage.btnUnsubscribe));
        unsubscribe_contentPage.btnUnsubscribe.click();

//        wait.until(ExpectedConditions.visibilityOf(unsubscribe_contentPage.btnConfirm));
//        unsubscribe_contentPage.btnConfirm.click();


    }


}


















