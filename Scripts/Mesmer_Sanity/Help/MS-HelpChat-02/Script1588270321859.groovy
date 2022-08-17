import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//WebUI.setViewPortSize(1034 , 708)
WebUI.delay(5)
try{
	if(WebUI.waitForElementPresent(helpIcon, 30)){
		WebUI.click(helpIcon)
		WebUI.delay(2)
//		WebUI.switchToFrame(iFrameHelpChat, 10)
//		WebUI.delay(2)
		startSearch()
	}
	else{
		MesmerLogUtils.markFailed("Help/Chat icon not found")
	}
}
catch(Exception e){
	e.printStackTrace()
}
finally{
//	WebUI.maximizeWindow()
	WebUI.refresh()
	WebUI.delay(2)
}
def startSearch(){
	
//	WebUI.setViewPortSize(1034 , 708)	
	WebDriver driver = DriverFactory.getWebDriver()
//	Actions builder = new Actions(driver);
//	String replyFromOperatorXpath = findTestObject('Object Repository/OR_Help/replyFromOperator').findPropertyValue('xpath').toString()
//	WebElement replyFromOperator = driver.findElement(By.xpath(replyFromOperatorXpath))
//	if(replyFromOperator != null){
//		builder.moveToElement(replyFromOperator).perform();
		
	
	if(WebUI.waitForElementPresent(inputSearchArticle, 30)){

		WebUI.setText(inputSearchArticle, "build upload")
		WebUI.delay(2)
		if(WebUI.waitForElementPresent(btnSearchArticle, 30)){
			WebUI.click(btnSearchArticle)
			WebUI.delay(5)

			MesmerLogUtils.markPassed("MS-HelpChat-02 successful")

		}
		else{
			MesmerLogUtils.markFailed("Button search article not found")
		}
	}
	else{
		MesmerLogUtils.markFailed("Input Search article not found")
	}
//	}else{
//	MesmerLogUtils.markWarning("No message from operator")
//	}
}