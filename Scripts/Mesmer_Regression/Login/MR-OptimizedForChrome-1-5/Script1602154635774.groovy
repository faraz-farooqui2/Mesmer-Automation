import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils

//MR-Login-1 | Verify that user can successfully login after updating server build
 
WebDriver driver = null
try{

	WebUI.closeBrowser()
	MesmerLogUtils.markPassed("Closing Chrome Browser")

	driver = new FirefoxDriver();
	driver.get("https://qa.qa-auto.stage.mesmerhq.com/")
	MesmerLogUtils.markPassed("Firefox Browser opens")

	WebUI.delay(10)

	String userNameXpath = findTestObject('Object Repository/OR_LogIn/textField_Username').findPropertyValue('xpath').toString()
	WebElement userName = driver.findElement(By.xpath(userNameXpath))

	if(userName != null){
		userName.click()
		userName.sendKeys(user)
		userName.sendKeys(Keys.RETURN)
		MesmerLogUtils.markPassed("Entered Username  :  " + user)


		WebUI.delay(1)

		String passwordXpath = findTestObject('Object Repository/OR_LogIn/textField_Password').findPropertyValue('xpath').toString()
		WebElement password = driver.findElement(By.xpath(passwordXpath))

		if(password != null){
			password.click()
			password.sendKeys(pass)
			//	password.sendKeys(Keys.RETURN)
			MesmerLogUtils.markPassed("Entered Password  :  " + pass)


			WebUI.delay(2)

			String logInButtonXpath = findTestObject('Object Repository/OR_LogIn/button_Login').findPropertyValue('xpath').toString()
			WebElement logInButton = driver.findElement(By.xpath(logInButtonXpath))
			if(logInButton != null){
				logInButton.click()
				MesmerLogUtils.markPassed("Clicked on Login Button")
				WebUI.delay(10)
				
				String mesmerOptimizedXpath = findTestObject('Object Repository/OR_LogIn/msg_MesmerOptimizedFor').findPropertyValue('xpath').toString()
				WebElement mesmerOptimized = driver.findElement(By.xpath(mesmerOptimizedXpath))
				if(mesmerOptimized != null){
					MesmerLogUtils.markPassed("Mesmer Optimized for message appears [optimizedForChrome1]")
					WebUI.delay(2)
					
				}else{
					MesmerLogUtils.markFailed("Mesmer Optimized for message not appears [optimizedForChrome1]")
				}
				
				String closeRoundBtnXpath = findTestObject('Object Repository/OR_LogIn/btn_closeRound').findPropertyValue('xpath').toString()
				WebElement closeRoundBtn = driver.findElement(By.xpath(mesmerOptimizedXpath))
				if(closeRoundBtn != null){
					MesmerLogUtils.markPassed("Close Round button found [optimizedForChrome2]")
					
				}else{
					MesmerLogUtils.markFailed("Could not find close round button [optimizedForChrome2]")
				}
				
				
				String getChromeXpath = findTestObject('Object Repository/OR_LogIn/getChrome').findPropertyValue('xpath').toString()
				WebElement getChrome = driver.findElement(By.xpath(getChromeXpath))
				if(getChrome != null){
					MesmerLogUtils.markPassed("Get chrome button found [optimizedForChrome3]")
					WebUI.delay(2)
					
				}else{
					MesmerLogUtils.markFailed("Get chrome button not found [optimizedForChrome3]")
				}
				
				String mesmerOptimizedOperaXpath = findTestObject('Object Repository/OR_LogIn/msg_MesmerOptimizedFor-Opera').findPropertyValue('xpath').toString()
				WebElement mesmerOptimizedOpera = driver.findElement(By.xpath(mesmerOptimizedOperaXpath))
				if(mesmerOptimizedOpera != null){
					MesmerLogUtils.markPassed("Mesmer Optimized for message appears  [optimizedForChrome- Opera]")
					WebUI.delay(2)
					
				}else{
					MesmerLogUtils.markFailed("Mesmer Optimized for message not appears  [optimizedForChrome- Opera]")
				}
				
				String mesmerOptimizedMicrosoftEdgeXpath = findTestObject('Object Repository/OR_LogIn/msg_MesmerOptimizedFor-MicrosoftEdge').findPropertyValue('xpath').toString()
				WebElement mesmerOptimizedMicrosoftEdge = driver.findElement(By.xpath(mesmerOptimizedMicrosoftEdgeXpath))
				
				if(mesmerOptimizedMicrosoftEdge != null){
					MesmerLogUtils.markPassed("Mesmer Optimized for message appears  [optimizedForChrome- Microsoft Edge]")
					WebUI.delay(2)
					
				}else{
					MesmerLogUtils.markFailed("Mesmer Optimized for message not appears  [optimizedForChrome- Microsoft Edge]")
				}
				
				String mesmerOptimizedBraveXpath = findTestObject('Object Repository/OR_LogIn/msg_MesmerOptimizedFor-Brave').findPropertyValue('xpath').toString()
				WebElement mesmerOptimizedBrave = driver.findElement(By.xpath(mesmerOptimizedBraveXpath))
				
				if(mesmerOptimizedBrave != null){
					MesmerLogUtils.markPassed("Mesmer Optimized for message appears  [optimizedForChrome- Brave]")
					WebUI.delay(2)
					
				}else{
					MesmerLogUtils.markFailed("Mesmer Optimized for message not appears  [optimizedForChrome- Brave]")
				}
				
				driver.close()
				
//				if(optimizedForChrome1()){
//					MesmerLogUtils.markPassed("optimizedForChrome1 test case Passed")
//					WebUI.delay(5)
//					if(optimizedForChrome2()){
//						MesmerLogUtils.markPassed("optimizedForChrome2 test case Passed")
//						WebUI.delay(5)
//						if(optimizedForChrome3()){
//							MesmerLogUtils.markPassed("optimizedForChrome3 test case Passed")
//							WebUI.delay(5)
//							if(optimizedForChrome4()){
//								MesmerLogUtils.markPassed("optimizedForChrome4 test case Passed")
//							}else{
//								MesmerLogUtils.markFailed("optimizedForChrome4 test case Failed")
//							}
//						}else{
//							MesmerLogUtils.markFailed("optimizedForChrome3 test case Failed")
//						}
//					}else{
//						MesmerLogUtils.markFailed("optimizedForChrome2 test case Failed")
//					}
//				}else{
//					MesmerLogUtils.markFailed("optimizedForChrome1 test case Failed")
//				}
			}else{
				MesmerLogUtils.markFailed("Could not click Login Button")
			}
		}else{
			MesmerLogUtils.markFailed("Password not enetered")
		}
	}else{
		MesmerLogUtils.markFailed("Username not enetered")
	}

}catch(Exception e){
	e.printStackTrace()
} finally{

}

private boolean optimizedForChrome1(){
	boolean result = false
	WebDriver driver = DriverFactory.getWebDriver()
 
	String mesmerOptimizedXpath = findTestObject('Object Repository/OR_LogIn/msg_MesmerOptimizedFor').findPropertyValue('xpath').toString()
	WebElement mesmerOptimized = driver.findElement(By.xpath(mesmerOptimizedXpath))
	if(mesmerOptimized != null){
		MesmerLogUtils.markPassed("Mesmer Optimized for message appears")
		WebUI.delay(2)
		result = true
	}else{
		MesmerLogUtils.markFailed("Mesmer Optimized for message not appears")
	}

	return result
}
//
//
//private boolean optimizedForChrome2(){
//	boolean result = false
//	WebDriver driver = new FirefoxDriver()
//
//	String closeRoundBtnXpath = findTestObject('Object Repository/OR_LogIn/btn_closeRound').findPropertyValue('xpath').toString()
//	WebElement closeRoundBtn = driver.findElement(By.xpath(mesmerOptimizedXpath))
//	if(closeRoundBtn != null){
//		MesmerLogUtils.markPassed("Close Round button found")
//		WebUI.delay(2)
//		result = true
//	}else{
//		MesmerLogUtils.markFailed("Could not find close round button")
//	}
//	return result
//}
//
//
//private boolean optimizedForChrome3(){
//	boolean result = false
//	WebDriver driver = new FirefoxDriver()
//
//	String getChromeXpath = findTestObject('Object Repository/OR_LogIn/getChrome').findPropertyValue('xpath').toString()
//	WebElement getChrome = driver.findElement(By.xpath(getChromeXpath))
//	if(getChrome != null){
//		MesmerLogUtils.markPassed("Get chrome button found")
//		WebUI.delay(2)
//		result = true
//	}else{
//		MesmerLogUtils.markFailed("Get chrome button not found")
//	}
//
//	return result
//}
//
//private boolean optimizedForChrome4(){
//	boolean result = false
//	WebDriver driver = new FirefoxDriver()
//
//	String mesmerOptimizedXpath = findTestObject('Object Repository/OR_LogIn/msg_MesmerOptimizedFor').findPropertyValue('xpath').toString()
//	WebElement mesmerOptimized = driver.findElement(By.xpath(mesmerOptimizedXpath))
//	if(mesmerOptimized != null){
//		MesmerLogUtils.markPassed("Mesmer Optimized for message appears")
//		WebUI.delay(2)
//		result = true
//	}else{
//		MesmerLogUtils.markFailed("Mesmer Optimized for message not appears")
//	}
//
//	return result
//}