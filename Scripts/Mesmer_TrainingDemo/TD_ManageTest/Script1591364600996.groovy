import com.mesmer.MesmerLogUtils
import com.mesmer.Utility

import controllers.ManageTestController
import controllers.TestDataController

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpString, platformName, ProjectName)

// Set the platformName for the testcase like, Generic/iOS/Android
Utility.setPlatformName(platformName)
CustomKeywords.'com.mesmer.Utility.goToManageTests'()
WebUI.waitForPageLoad(5)
try{
	selectTestCase("ManageTest-TrainingDemo")
	addTestData("ManageTest-TrainingDemo")
	addScript("FilePath")
}
catch(Exception e){
	e.printStackTrace()
}

private boolean selectTestCase(String testName){
	boolean result = false
	if(ManageTestController.getInstance().findTestCaseAndPerformAction(testName, "select")){
		MesmerLogUtils.logInfo("Test case selected successfully")
		result = true
	}
	else{
		MesmerLogUtils.logInfo("Test case name not found")
	}
	return result
}

private boolean addTestData(String testName){
	boolean result = false
	if(ManageTestController.getInstance().findTestCaseAndPerformAction(testName, "testData")){
		if(TestDataController.getInstance().checkIfManageTestDataPopAppears()){
			if(TestDataController.getInstance().clickUserLoginPlusBtn()){
				if(TestDataController.getInstance().addUserNamePassword("txtand@gmail.com", "asdf")){
					if(TestDataController.getInstance().clickCreditCardPlusBtn()){
						if(TestDataController.getInstance().addCreditCardInfo("123", "2022", "134", "abc", "def")){
							if(TestDataController.getInstance().clickGPSPlusBtn()){
								if(TestDataController.getInstance().addLatLng("-21.12568", "56.082162")){
									if(TestDataController.getInstance().clickSearchTermPlusBtn()){
										if(TestDataController.getInstance().addSearchTerm("test search")){
											if(TestDataController.getInstance().clickCustomPlusBtn()){
												if(TestDataController.getInstance().addCustomKeyValue("testKey", "testValue")){
													if(TestDataController.getInstance().clickDoneBtn()){
														result = true
													}
												}
												else{
													MesmerLogUtils.logInfo("Custom key value input fields not found")
												}
											}
											else{
												MesmerLogUtils.logInfo("Plus button to add custom key value info not found")
											}
										}
										else{
											MesmerLogUtils.logInfo("Search term input fields not found")
										}
									}
									else{
										MesmerLogUtils.logInfo("Plus button to add search info not found")
									}
								}
								else{
									MesmerLogUtils.logInfo("GPS input fields not found")
								}
							}
							else{
								MesmerLogUtils.logInfo("Plus button to add gps info not found")
							}
						}
						else{
							MesmerLogUtils.logInfo("Credit card input fields not found")
						}
					}
					else{
						MesmerLogUtils.logInfo("Plus button to add credit card info not found")
					}
				}
				else{
					MesmerLogUtils.logInfo("username and password input fields not found")
				}
			}
			else{
				MesmerLogUtils.logInfo("Plus button to add username and password not found")
			}
		}
		else{
			MesmerLogUtils.logInfo("Manage Test Data dialog not appeared")
		}
	}
	else{
		MesmerLogUtils.logInfo("Test case name not found")
	}
	
	return result
}

private boolean addScript(String testName, String filePath){
	boolean result = false
	if(ManageTestController.getInstance().findTestCaseAndPerformAction(testName, "testData")){
		if(TestDataController.getInstance().checkIfManageTestDataPopAppears()){
			if(TestDataController.getInstance().uploadPreConditionScript(filePath)){
				result = true
			}
		}
	}
	return result
}