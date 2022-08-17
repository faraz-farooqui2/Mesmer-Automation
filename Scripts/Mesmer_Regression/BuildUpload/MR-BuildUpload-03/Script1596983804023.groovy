import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.Utility
import com.mesmer.MesmerLogUtils

// MR-BuildUpload-03 | Verify if project detail gets updated and stays static right after switching between two different projects

CustomKeywords.'com.mesmer.Utility.goToTestResults'()
WebDriver driver = DriverFactory.getWebDriver()
try{

	

	if(WebUI.waitForElementPresent(projectBuildLable, 10)){

		CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)
		Utility.setPlatformName(platformName)
		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
		
		String projectBuildLabelXpath = findTestObject('Object Repository/OR_BuildUpload/projectList').findPropertyValue('xpath').toString()
		WebElement projectBuildLabel = driver.findElement(By.xpath(projectBuildLabelXpath))
		
		String projectBuildLabelText = projectBuildLabel.getText();
		MesmerLogUtils.logInfo("Project Name" + " : " + projectBuildLabelText)
		

	}else{
		MesmerLogUtils.markFailed("No project build label found")
	}

}
catch(Exception e){
	e.printStackTrace()
}
finally{
	
}