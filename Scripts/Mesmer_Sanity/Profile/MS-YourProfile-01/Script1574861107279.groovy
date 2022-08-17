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
//import internal.GlobalVariable as GlobalVariable
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import internal.GlobalVariable


// MS-Your Profile-01 | Verify that user can Edit and Update profile in 'Your Profile' page successfully.

//1. Click on User Account/Avatar icon on top right corner of the mesmer console
//2. Click on Your profile option
Utility.setPlatformName("Generic")
MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())
CustomKeywords.'com.mesmer.Utility.goToUserProfile'()

try{
	//3. Click on Username field and Edit the name
	//4. Click on Email field
	if(WebUI.waitForElementPresent(inputNameField, 20)== true){
		String setName = GlobalVariable.username
		
//		int index = setName.indexOf('@');
//		setName = setName.substring(0,index);
		
		WebUI.click(inputNameField)
		WebUI.clearText(inputNameField)

		if(WebUI.waitForElementPresent(nameValidationError, 20) == true){
			MesmerLogUtils.markPassed("Name field cleared Successfully")
			WebUI.click(inputNameField)
			WebUI.setText(inputNameField, setName)
			
			//5. Click on Avatar icon besides Username field
			if(WebUI.waitForElementPresent(userCurrentAvatar, 20)== true){
				WebUI.click(userCurrentAvatar)
				WebUI.delay(2)
				//6. Click on Camera icon and set the custome image as profile image.
				if(WebUI.waitForElementPresent(headerTextChooseYourAvatar, 20) == true){

					if(WebUI.waitForElementPresent(userSelectNewAvatar, 20) == true){
						WebUI.click(userSelectNewAvatar)
						WebUI.delay(2)
						
						//7. Click on Update Profile
						if(WebUI.waitForElementPresent(buttonUpdateUserProfile, 20) == true){

							WebUI.click(buttonUpdateUserProfile)
							WebUI.delay(2)
							if(WebUI.waitForElementPresent(headerTextYourProfile, 20) == true){

								MesmerLogUtils.markPassed("Avatar Updated Successfully")
							}
							else{
								MesmerLogUtils.markFailed("Counter Total Test cases not found")
							}
						}
						else{
							MesmerLogUtils.markFailed("Button Update Profile not found")
						}

					}else{
						MesmerLogUtils.markFailed("Choose New User Avatar option not found")
					}

				}
				else{
					MesmerLogUtils.markFailed("Header Choose Your Avatar not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("User Avatar not found")
			}
		}else{
			MesmerLogUtils.markFailed('Name field not cleared')
		}
	}
}catch(Exception e){
	e.printStackTrace()
}finally{
WebUI.refresh()
}