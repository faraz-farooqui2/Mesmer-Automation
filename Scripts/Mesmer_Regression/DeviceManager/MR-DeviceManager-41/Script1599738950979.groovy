import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

// MR-Device Manager-41 | Verify that the refreshing icon with Refreshing text is showing on the device manager
CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(2)

try{

	String statusRefreshingXpath = findTestObject('Object Repository/OR_DeviceCertification/deviceManager_listRefreshingText').findPropertyValue('xpath').toString()
	List <WebElement> statusRefreshing = driver.findElements(By.xpath(statusRefreshingXpath))

	if (statusRefreshing != null && statusRefreshing.size() > 0){
		for (WebElement webElement : statusRefreshing) {
			String statusRefreshingListText = webElement.getText();
			MesmerLogUtils.markPassed("Refreshing device text verified " + " : " + "  "  +  statusRefreshingListText)
		}
	}else{
		MesmerLogUtils.markWarning("No Refreshing device found")
	}


}catch(Exception e){
	e.printStackTrace()
}
finally{

}
