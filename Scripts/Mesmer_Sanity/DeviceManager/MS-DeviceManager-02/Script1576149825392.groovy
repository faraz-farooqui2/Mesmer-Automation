import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.util.List
import java.util.ArrayList
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

/*TestObject pageDeviceManager = findTestObject('Object Repository/OR_Common/page_verifyDeviceManager')
boolean checkPageDeviceManager = WebUI.verifyElementVisible(pageDeviceManager)

if(checkPageDeviceManager == false){
CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
}*/
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
//Calling Go to Device Manager method
CustomKeywords.'com.mesmer.Utility.goToDeviceManager'()
WebDriver driver = DriverFactory.getWebDriver()
WebUI.delay(2)

try{
	
boolean checkAllDevices = WebUI.verifyElementVisible(allDevices)
boolean checkAndroidDevices = WebUI.verifyElementVisible(androidDevices)
boolean checkiOSDevices = WebUI.verifyElementVisible(iOSDevices)
boolean checkPhysicalDevices = WebUI.verifyElementVisible(physicalDevices)
boolean checkVirtualDevices = WebUI.verifyElementVisible(virtualDevices)

String AllDevices = findTestObject('Object Repository/OR_DeviceManager/list_allDevices').findPropertyValue('xpath').toString()
List<WebElement> numberOfALLDevicess = driver.findElements(By.xpath(AllDevices))


int countOfAllDevices = numberOfALLDevicess.size()
MesmerLogUtils.logInfo("Count of ALL devices = " + countOfAllDevices)

String androidDeviceNumber = WebUI.getText(androidDevices)
int numberOfAndroidDevices = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(androidDeviceNumber)
MesmerLogUtils.logInfo("Number of ANDROID devices = " + numberOfAndroidDevices)

String AllDeviceNumber = WebUI.getText(allDevices)
int numberOfAllDevices = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(AllDeviceNumber)
MesmerLogUtils.logInfo("Number of All devices = " + numberOfAllDevices)

String iOSDeviceNumber = WebUI.getText(iOSDevices)
int numberOfiOSDevices = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(iOSDeviceNumber)
MesmerLogUtils.logInfo("Number of iOS devices = " + numberOfiOSDevices)

String physicalDeviceNumber = WebUI.getText(physicalDevices)
int numberOfphysicalDevices = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(physicalDeviceNumber)
MesmerLogUtils.logInfo("Number of Physical devices = " + numberOfphysicalDevices)

String virtualDeviceNumber = WebUI.getText(virtualDevices)
int numberOfVirtualDevices = CustomKeywords.'com.mesmer.Utility.getNumberOutOfString'(virtualDeviceNumber)
MesmerLogUtils.logInfo("Number of Virtual devices = " + numberOfVirtualDevices)


//Check Android Devices
if(checkAndroidDevices == true){
	//3. Click on 'Android' filter under Device Manager heading
	MesmerLogUtils.logInfo("Clicking Android Option")
	WebUI.click(androidDevices)
	MesmerLogUtils.logInfo("Android Option is Clicked")
	
	String AllAndroidDevices = findTestObject('Object Repository/OR_DeviceManager/list_AllAndroidDevices').findPropertyValue('xpath').toString()
	List<WebElement> listAndroidDevices = driver.findElements(By.xpath(AllAndroidDevices))
	
	int countAndroidDevices = listAndroidDevices.size()
	
	//Check if total Android devices are equal to devices shown in list after Selecting Android option
	if(countAndroidDevices == 0){
		MesmerLogUtils.markFailed("No Android device available on the server")
	}
	
	else if(countAndroidDevices == numberOfAndroidDevices){
		MesmerLogUtils.markPassed('Android Device count is correct')
		WebUI.delay(1)
		checkAndroidVirtualDevices()
		checkAndroidPhysicalDevices()
		
	}
	else{
		MesmerLogUtils.markFailed('Android devices count is not correct')
	}
}
else{
	MesmerLogUtils.markFailed('Android devices option not found')
}

// Validation of IOS Devices
if(checkiOSDevices == true)
{
	//4. Click on 'iOS' filter under Device Manager heading
	MesmerLogUtils.logInfo("Clicking iOS devices option")
	WebUI.click(iOSDevices)
	MesmerLogUtils.logInfo("iOS devices option is clicked")
	WebUI.delay(1)
	List<WebElement> listiOSDevices = driver.findElements(By.xpath("//span[contains(text(),'iOS')]"))
	int countiOSDevices = listiOSDevices.size()				
	
	if(countiOSDevices==0){
		MesmerLogUtils.markFailed("iOS devices are not available on the server")
	}
	//Check if total iOS devices are equal to devices shown in list after Selecting iOS option
	else if(countiOSDevices == numberOfiOSDevices){
		MesmerLogUtils.markPassed('iOS Devices count is correct and selected successfully')
		WebUI.delay(1)
		
		checkiOSPhysicalDevices()
		checkiOSVirtualDevices()
	}
	else{
		MesmerLogUtils.markFailed('iOS devices count is not correct')
	}
	
}
else{
	MesmerLogUtils.markFailed('iOS devices option not found')
}
	
//Validation of Physical Devices //div[contains(@class,'virtual')]
if(checkPhysicalDevices == true){
	//5. Click on 'Physical' under Device Manager heading
	MesmerLogUtils.logInfo("Clicking Physical Devices option")
	WebUI.click(physicalDevices)
	MesmerLogUtils.logInfo("Physical Devices option is clicked")
	
	String AllPhysicalDevices = findTestObject('Object Repository/OR_DeviceManager/list_AllPhysicalDevices').findPropertyValue('xpath').toString()
		
	List<WebElement> listPhysicalDevices = driver.findElements(By.xpath(AllPhysicalDevices))
	int countPhysicalDevices = listPhysicalDevices.size()
	MesmerLogUtils.logInfo("Total " + countPhysicalDevices + " Physical are available")
	
	//Check if total Physical devices are equal to devices shown in list after Selecting Physical option
	if(countPhysicalDevices == numberOfphysicalDevices){
		WebUI.delay(1)
		MesmerLogUtils.markPassed('Physical Device count is correct')
	}
	else{
		MesmerLogUtils.markFailed('Physical devices count is not correct')
	}
}
else{
	MesmerLogUtils.markFailed('Physical devices option not found')
}

if(checkVirtualDevices == true){
	//6. Click on 'Virtual' under Device Manager heading
	MesmerLogUtils.logInfo("Clicking Virtual Devices options")
	WebUI.click(virtualDevices)
	MesmerLogUtils.logInfo("Virtual Devices options is clicked")
	WebUI.delay(1)
	String AllVirtualDevices = findTestObject('Object Repository/OR_DeviceManager/list_AllVirtualDevices').findPropertyValue('xpath').toString()
	
	List<WebElement> listVirtualDevices = driver.findElements(By.xpath(AllVirtualDevices))
	int countVirtualDevices = listVirtualDevices.size()
	MesmerLogUtils.logInfo("Total " + countVirtualDevices + " Virtual are available")
	
	//Check if total Virtual devices are equal to devices shown in list after Selecting Virtual option
	if(countVirtualDevices == numberOfVirtualDevices){
		WebUI.delay(1)
		MesmerLogUtils.markPassed('Virtual Device count is correct')
	}
	else{
		MesmerLogUtils.markFailed('Virtual devices count is not correct')
	}
	
}
else{
MesmerLogUtils.markFailed('Virtual devices option not found')
}

if(checkAllDevices == true){
	//7. Click on 'All' under Device Manager heading
	MesmerLogUtils.logInfo("All option is available")
	WebUI.click(allDevices)
	MesmerLogUtils.logInfo("All option is clicked")
	WebUI.delay(1)
	MesmerLogUtils.markPassed('All Devices option found successfully')
	
	//Check if total devices are equal to devices shown in list after Selecting All option
	if(countOfAllDevices == numberOfAllDevices){
		MesmerLogUtils.markPassed('All Devices count is correct')
	}
	else{
		MesmerLogUtils.markFailed('All devices count is not correct')
	}
}
else{
	MesmerLogUtils.markFailed('All devices option not found')
}
	
}catch(Exception e){
	e.printStackTrace()
}
finally{
	
}

def checkiOSPhysicalDevices(){
	WebDriver driver = DriverFactory.getWebDriver()
	String iOSPhyicalDevices = findTestObject('Object Repository/OR_DeviceManager/list_PhysicaliOSDevices').findPropertyValue('xpath').toString()
	List <WebElement> listiOSPhysicalDevices = driver.findElements(By.xpath(iOSPhyicalDevices))
	
	int countIOSPhysicalDevices = listiOSPhysicalDevices.size()
	
	if(countIOSPhysicalDevices > 0){
		MesmerLogUtils.logInfo(countIOSPhysicalDevices + " iOS Physical Devices are available")
		
	}
	else{
		MesmerLogUtils.markWarning("No iOS Physical Device available")
	}	
	
}

def checkiOSVirtualDevices(){
	WebDriver driver = DriverFactory.getWebDriver()
	String iOSVirtualDevices = findTestObject('Object Repository/OR_DeviceManager/list_VirtualiOSDevices').findPropertyValue('xpath').toString()
	
	List <WebElement> listiOSVirtualDevices = driver.findElements(By.xpath(iOSVirtualDevices))
	
	int countIOSVirtualDevices = listiOSVirtualDevices.size()
	
	if(countIOSVirtualDevices > 0){
		MesmerLogUtils.logInfo(countIOSVirtualDevices + " iOS Virtual Devices are available")
		
	}
	else{
		MesmerLogUtils.markWarning("No iOS Virtual Device available")
	}
	
}

def checkAndroidPhysicalDevices(){
	WebDriver driver = DriverFactory.getWebDriver()
	String AndroidPhysicalDevices = findTestObject('Object Repository/OR_DeviceManager/list_PhysicalAndroidDevices').findPropertyValue('xpath').toString()
	
	
	List <WebElement> listAndroidPhysicalDevices = driver.findElements(By.xpath(AndroidPhysicalDevices))
	
	int countAndroidPhysicalDevices = listAndroidPhysicalDevices.size()
	
	if(countAndroidPhysicalDevices > 0){
		MesmerLogUtils.logInfo(countAndroidPhysicalDevices + " Android Physical Devices are available")
		
	}
	else{
		MesmerLogUtils.markWarning("No Android Physical Device available")
	}
	
}

def checkAndroidVirtualDevices(){
	WebDriver driver = DriverFactory.getWebDriver()
	String AndroidVirtualDevices = findTestObject('Object Repository/OR_DeviceManager/list_VirtualAndroidDevices').findPropertyValue('xpath').toString()
	
	List <WebElement> listAndroidVirtualDevices = driver.findElements(By.xpath(AndroidVirtualDevices))
	
	int countAndroidVirtualDevices = listAndroidVirtualDevices.size()
	
	if(countAndroidVirtualDevices > 0){
		MesmerLogUtils.logInfo(countAndroidVirtualDevices + " Android Virtual Devices are available")
		
	}
	else{
		MesmerLogUtils.markWarning("No Android Virtual Device available")
	}
	
}

