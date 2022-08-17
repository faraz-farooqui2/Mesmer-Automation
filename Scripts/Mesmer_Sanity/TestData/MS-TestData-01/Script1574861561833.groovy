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
import org.openqa.selenium.Keys as Keys
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import controllers.ManageTestController
import controllers.TestDataController

//MS-Test Data (Project Level)-01 | Verify that user should be able to Add/Delete test data for all available options on the page. (i.e User login, Credit card,GPS,Search terms and Custom fields)
//1. Click on the Settings icon on the top right corner of the mesmer console in the middle of Bell icon and Avatar
//2. Click on the "Test Data" option


try{

	// Set the platformName for the testcase like, Generic/iOS/Android
	Utility.setPlatformName("Generic")

	//Calling Select Project Method
	if(CustomKeywords.'com.mesmer.Utility.selectProject'(projName, xpathString, platformName, ProjectName)){

		MesmerLogUtils.logInfo("Platform Name :: " + "  " + Utility.getPlatformName())

		if(CustomKeywords.'com.mesmer.Utility.goToTestData'()){

//			if(addTestData()){

				if(removeTestData()){
				}else{
					MesmerLogUtils.markFailed("Unable to remove test data")
				}
//			}else{
//				MesmerLogUtils.markFailed("Unable to add test data")
//			}
		}else{
			MesmerLogUtils.markFailed("Unable to navigate to test data")
		}
	}else{
		MesmerLogUtils.markFailed("Project   " +  ProjectName  + "  does not exist" )
	}
}
catch(Exception e){
	e.printStackTrace()
}

private boolean addTestData(){
	boolean result = false
	if(TestDataController.getInstance().enterAddress(varAddress , firstName , lastName , addressLine , addressLineTwo , city , state , zipCode , country)){
		if(TestDataController.getInstance().addCreditCardInfo(Card_Number, CardNumberExpiry, Card_Cvv, Card_FirstName, Card_LastName , variableCreditCard)){
			if(TestDataController.getInstance().addCustomKeyValue(Custome_Key , Custome_Value )){
				if(TestDataController.getInstance().addLatLng(GPS_Latitude, GPS_Longitude , varGPS)){
					if(TestDataController.getInstance().addUserNamePassword(userName , Password , UserLogin)){
						if(TestDataController.getInstance().addSearchTerm(Search_field , varSearchTerm)){
							if(TestDataController.getInstance().enterUsername(varUsername , valueUsername)){
								result = true
							}
							else{
								MesmerLogUtils.markFailed("Unable to set data in Username field")
							}
						}
						else{
							MesmerLogUtils.markFailed("Unable to set data in Search Term field")
						}
					}
					else{
						MesmerLogUtils.markFailed("Unable to set data in Login field")
					}

				}
				else{
					MesmerLogUtils.markFailed("Unable to set data in GPS field")
				}

			}else{
				MesmerLogUtils.markFailed("Unable to set data in Custom field")
			}
		}
		else{
			MesmerLogUtils.markFailed("Unable to set data in Credit Card field")
		}
	}
	else{
		MesmerLogUtils.markFailed("Unable to set data in Address field")
	}

	return result
}

//13. Click on x buttons for all input fields on Test Data page
private boolean removeTestData(){

	boolean result = false

	// Remove Address details
	if (WebUI.waitForElementPresent(buttonRemoveAddressDetails, 10) == true) {
		WebUI.click(buttonRemoveAddressDetails)
		WebUI.scrollToElement(buttonRemoveAddressDetails, 10)
		WebUI.click(removeTestDataDetails)
		WebUI.delay(2)
		MesmerLogUtils.logInfo("Removed Address details")


		// Remove Credit Card details
		if (WebUI.waitForElementPresent(buttonRemoveCreditCardDetails, 10) == true) {
			WebUI.click(buttonRemoveCreditCardDetails)
			WebUI.scrollToElement(buttonRemoveCreditCardDetails, 10)
			WebUI.click(removeTestDataDetails)
			WebUI.delay(2)
			MesmerLogUtils.logInfo("Removed Credit Card details")

			// Remove Custom details
			if (WebUI.waitForElementPresent(buttonRemoveCustomDetails, 10) == true) {
				WebUI.click(buttonRemoveCustomDetails)
				WebUI.scrollToElement(buttonRemoveCustomDetails, 10)
				WebUI.click(removeTestDataDetails)
				WebUI.delay(2)
				MesmerLogUtils.logInfo("Removed Custom details")

				// Remove GPS details
				if (WebUI.waitForElementPresent(buttonRemoveGPSCoordinatesDetails, 10) == true) {
					WebUI.click(buttonRemoveGPSCoordinatesDetails)
					WebUI.scrollToElement(buttonRemoveGPSCoordinatesDetails, 10)
					WebUI.click(removeTestDataDetails)
					WebUI.delay(2)
					MesmerLogUtils.logInfo("Removed GPS details")

					// Remove User Login details
					if (WebUI.waitForElementPresent(buttonRemoveUserLoginDetails, 10) == true) {
						WebUI.click(buttonRemoveUserLoginDetails)
						WebUI.scrollToElement(buttonRemoveUserLoginDetails, 10)
						WebUI.click(removeTestDataDetails)
						WebUI.delay(2)
						MesmerLogUtils.logInfo("Removed User Login details")

						// Remove Search Terms details
						if (WebUI.waitForElementPresent(buttonRemoveSearchTermsDetails, 10) == true) {
							WebUI.click(buttonRemoveSearchTermsDetails)
							WebUI.scrollToElement(buttonRemoveSearchTermsDetails, 10)
							WebUI.click(removeTestDataDetails)
							WebUI.delay(2)
							MesmerLogUtils.logInfo("Removed Search Term details")

							// Remove Username details
							if (WebUI.waitForElementPresent(buttonRemoveUsernameDetails, 10) == true) {
								WebUI.click(buttonRemoveUsernameDetails)
								WebUI.scrollToElement(buttonRemoveUsernameDetails, 10)
								WebUI.click(removeTestDataDetails)
								WebUI.delay(2)
								MesmerLogUtils.logInfo("Removed Username details")
								result = true
								
							}else{

								MesmerLogUtils.markFailed("Username details not removed")
							}
						}else{

							MesmerLogUtils.markFailed("Search Terms detail not removed")
						}
					}else{

						MesmerLogUtils.markFailed("User Login detail not removed")
					}
				}else{

					MesmerLogUtils.markFailed("GPS Coorcinates detail not removed")
				}
			}else{

				MesmerLogUtils.markFailed("Custom detail not removed")
			}

		}else{

			MesmerLogUtils.markFailed("Credit Card detail not removed")
		}

	}else{

		MesmerLogUtils.markFailed("Address detail not removed")
	}
	return result
}