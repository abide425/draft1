package pages;
import org.openqa.selenium.By;

public class aetnaPortalPage {

    public String quoteUrl = "https://www.aetna.com/insurance-producer/working-with-aetna/get-a-quote.html";
    public By individualDentalLocator = new By.ByCssSelector("#link_root_accordion_copy_copy_5") ;
    public By loginLocator = new By.ByCssSelector(".link__text[data-analytics-name='Log in to get a quote'][href='https://dentaldirect.aetna.com/#/']") ;
    public By zipCodeLocator = new By.ByCssSelector("#zipInput") ;
    public By emailLocator = new By.ByCssSelector("#Email") ;
    public By actualTextLocator = new By.ByCssSelector("h2[class='mb-3']") ;
    public By findAPlanLocator = new By.ByCssSelector("#next1") ;
    public By firstNameLocator = new By.ByCssSelector("#FirstName") ;
    public By MILocator = new By.ByCssSelector("#MiddleInitial") ;
    public By lastNameLocator = new By.ByCssSelector("#LastName") ;
    public By genderLocator = new By.ByCssSelector("#Gender") ;
    public By DOBLocator = new By.ByCssSelector("#DOB") ;
    public By optionLocator = new By.ByCssSelector("#BestOption") ;
    public By nextButtonLocator = new By.ByCssSelector("#addDependent") ;

    public By plansLocator = new By.ByCssSelector("div[id='divIndex0'] label");

    public By plansNextLocator = new By.ByCssSelector(".btn.btn-primary");
    public By homeAddressLocator = new By.ByCssSelector("#HomeAddress");
    public By aptLocator = new By.ByCssSelector("#AptNumber");
    public By cityLocator = new By.ByCssSelector("#City");
    //    public By stateLocator = new By.ByCssSelector(".btn.btn-primary");
//    public By zipLocator = new By.ByCssSelector(".btn.btn-primary");
//    public By emailAddressLocator = new By.ByCssSelector("#Email");
    public By confirmEmailLocator = new By.ByCssSelector("#ConfirmEmail");
    public By primaryPhoneLocator = new By.ByCssSelector("#PrimaryPhoneNumber");
    public By phoneTypeLocator = new By.ByCssSelector("#PrimaryPhoneType");
    public By carrierLocator = new By.ByCssSelector("#NameOfCarrier");
    public By residentLocator = new By.ByCssSelector("label[for='IsResident2']");
    public By readAndWriteLocator = new By.ByCssSelector("label[for='IsReadOrWriteEnglish2']");
    public By accountabilityLocator = new By.ByCssSelector("label[for='IsCompletedByApplicant2']");
    public By nextLocator = new By.ByCssSelector("button[name='btnContactInfoNext']");
























}

