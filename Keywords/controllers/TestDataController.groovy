package controllers

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.mesmer.MesmerLogUtils
import com.mesmer.Utility
import org.openqa.selenium.JavascriptExecutor;
import internal.GlobalVariable

public class TestDataController {

	private static TestDataController mInstance= null

	private TestDataController(){
	}

	public static TestDataController getInstance(){
		if(mInstance == null){
			mInstance = new TestDataController()
		}

		return mInstance
	}

	public boolean checkIfProvideTestDataScreenAppears(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestData/title_ProvideTestData')
		if(WebUI.waitForElementVisible(obj,30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Provide test data screen not found")
		}
		return result
	}
	/**
	 * Check if manage test data pop up appears
	 * @return
	 */
	public boolean checkIfManageTestDataPopAppears(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestData/header_titlebar')
		if(WebUI.waitForElementVisible(obj,30)){
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Manage test data popup not found")
		}
		return result
	}

	/**
	 * Check and click the user login add button
	 * @return
	 */
	public boolean clickUserLoginPlusBtn(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestData/button_TestDataUserLogin')
		if(WebUI.waitForElementPresent(obj,30)){
			WebUI.scrollToElement(obj, 10)
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
			
		}
		else{
			MesmerLogUtils.markFailed("User login plus button not found")
		}

		return result
	}
	/**
	 * Add username and password into the respective input fields
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean addUserNamePassword(String username, String password , String UserLogin){
		boolean result = false
		if(clickUserLoginPlusBtn()){
			TestObject varLogin = findTestObject('Object Repository/OR_TestData/var_userLogin')
			if(WebUI.waitForElementPresent(varLogin,30)){
				WebUI.click(varLogin)
				WebUI.delay(1)
				WebUI.setText(varLogin, UserLogin)
				WebUI.delay(1)
				TestObject usernameObj = findTestObject('Object Repository/OR_TestData/textField_TestDataUsername')
				if(WebUI.waitForElementPresent(usernameObj,30)){
					WebUI.click(usernameObj)
					WebUI.delay(1)
					WebUI.setText(usernameObj, username)
					WebUI.delay(1)
					TestObject passwordObj = findTestObject('Object Repository/OR_TestData/textField_TestDataPassword')
					if(WebUI.waitForElementPresent(passwordObj,30)){
						WebUI.click(passwordObj)
						WebUI.delay(1)
						WebUI.setText(passwordObj, password)
						WebUI.delay(1)
						if(clickApplyBtn()){
							result = true
							WebUI.delay(6)
						}
						else{
							MesmerLogUtils.markFailed("Unable to click on apply button")
						}
					}
					else{
						MesmerLogUtils.markFailed("Password input field not found")
					}
				}
				else{
					MesmerLogUtils.markFailed("Username input field not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("User login input field not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("User login plus button not found")
		}
		return result
	}

	/**
	 * Check and click the credit card add button
	 * @return
	 */
	public boolean clickCreditCardPlusBtn(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestData/button_TestDataCreditCards')
		if(WebUI.waitForElementPresent(obj,30)){
			WebUI.click(obj)
			result = true
			WebUI.delay(2)
		}
		else{
			MesmerLogUtils.markFailed("Credit card plus button not found")
		}

		return result
	}
	/**
	 * Add the credit card input fields info
	 * @param cNumber
	 * @param expiry
	 * @param cvv
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public boolean addCreditCardInfo(String cNumber, String expiry, String cvv, String firstName, String lastName , String varCreditCard){
		boolean result = false
		if(clickCreditCardPlusBtn()){
			TestObject varCreditCardObj = findTestObject('Object Repository/OR_TestData/var_creditCard')
			if(WebUI.waitForElementPresent(varCreditCardObj,30)){
				WebUI.click(varCreditCardObj)
				WebUI.delay(1)
				WebUI.setText(varCreditCardObj, varCreditCard)
				WebUI.delay(1)
				TestObject cNumberObj = findTestObject('Object Repository/OR_TestData/textField_TestDataCardNumber')
				if(WebUI.waitForElementPresent(cNumberObj,30)){
					WebUI.click(cNumberObj)
					WebUI.delay(1)
					WebUI.setText(cNumberObj, cNumber)
					WebUI.delay(1)
					TestObject expiryObj = findTestObject('Object Repository/OR_TestData/textField_TestDataExpiry')
					if(WebUI.waitForElementPresent(expiryObj,30)){
						WebUI.click(expiryObj)
						WebUI.delay(1)
						WebUI.setText(expiryObj, expiry)
						WebUI.delay(1)
						TestObject cvvObj = findTestObject('Object Repository/OR_TestData/textField_TestDataCVV')
						if(WebUI.waitForElementPresent(cvvObj,30)){
							WebUI.click(cvvObj)
							WebUI.delay(1)
							WebUI.setText(cvvObj, cvv)
							WebUI.delay(1)
							TestObject fNameObj = findTestObject('Object Repository/OR_TestData/textField_TestDataFirstName')
							if(WebUI.waitForElementPresent(fNameObj,30)){
								WebUI.click(fNameObj)
								WebUI.delay(1)
								WebUI.setText(fNameObj, firstName)
								WebUI.delay(1)
								TestObject lNameObj = findTestObject('Object Repository/OR_TestData/textField_TestDataLastName')
								if(WebUI.waitForElementPresent(lNameObj,30)){
									WebUI.click(lNameObj)
									WebUI.delay(1)
									WebUI.setText(lNameObj, lastName)
									WebUI.delay(1)
									if(clickApplyBtn()){
										result = true
										WebUI.delay(6)
									}
									else{
										MesmerLogUtils.markFailed("Unable to click on apply button")
									}
								}
								else{
									MesmerLogUtils.markFailed("Lastname input field not found")
								}
							}
							else{
								MesmerLogUtils.markFailed("Firstname input field not found")
							}
						}
						else{
							MesmerLogUtils.markFailed("CVV input field not found")
						}
					}
					else{
						MesmerLogUtils.markFailed("Expiry input field not found")
					}
				}
				else{
					MesmerLogUtils.markFailed("Card number input field not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("Variable credit card input field not found")
			}
		}else{
			MesmerLogUtils.markFailed("Credit card plus button not found")
		}
		return result
	}

	/**
	 * Check and click the gps add button
	 * @return
	 */
	public boolean clickGPSPlusBtn(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestData/button_TestDataGPS')
		if(WebUI.waitForElementPresent(obj,30)){
			WebUI.click(obj)
			WebUI.delay(2)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("GPS plus button not found")
		}

		return result
	}
	/**
	 * Add GPS latitude and longitude in their input fields
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public boolean addLatLng(String latitude, String longitude , String varGPS){
		boolean result = false
		if(clickGPSPlusBtn()){
			TestObject varGPSObj = findTestObject('Object Repository/OR_TestData/var_gps')
			if(WebUI.waitForElementPresent(varGPSObj,30)){
				WebUI.click(varGPSObj)
				WebUI.delay(1)
				WebUI.setText(varGPSObj, varGPS)
				WebUI.delay(1)
				TestObject latObj = findTestObject('Object Repository/OR_TestData/textField_TestDataLatitude')
				if(WebUI.waitForElementPresent(latObj,30)){
					WebUI.click(latObj)
					WebUI.delay(1)
					WebUI.setText(latObj, latitude)
					WebUI.delay(1)
					TestObject lngObj = findTestObject('Object Repository/OR_TestData/textField_TestDataLongitutde')
					if(WebUI.waitForElementPresent(lngObj,30)){
						WebUI.click(lngObj)
						WebUI.delay(1)
						WebUI.setText(lngObj, longitude)
						WebUI.delay(1)
						if(clickApplyBtn()){
							result = true
							WebUI.delay(1)
						}
						else{
							MesmerLogUtils.markFailed("Unable to click on apply button")
						}
					}
					else{
						MesmerLogUtils.markFailed("Longitude input field not found")
					}
				}
				else{
					MesmerLogUtils.markFailed("Latitude input field not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("GPS input field not found")
			}
		}
		else{
			MesmerLogUtils.markFailed("GPS plus button not found")
		}
		return result
	}

	/**
	 * Check and click the search term add button
	 * @return
	 */
	public boolean clickSearchTermPlusBtn(){
		boolean result = false
		TestObject obj = findTestObject('Object Repository/OR_TestData/button_TestDataSearchTerm')
		if(WebUI.waitForElementPresent(obj,30)){
			WebUI.scrollToElement(obj, 10)
			WebUI.click(obj)
			WebUI.delay(2)
			result = true
		}
		else{
			MesmerLogUtils.markFailed("Search term plus button not found")
		}

		return result
	}
	/**
	 * Add search text in input field
	 * @param searchText
	 * @return
	 */
	public boolean addSearchTerm(String searchText , String varSearchTerm){
		boolean result = false
		if(clickSearchTermPlusBtn()){
			TestObject varSearchTermObj = findTestObject('Object Repository/OR_TestData/var_searchTerm')
			if(WebUI.waitForElementPresent(varSearchTermObj,30)){
				WebUI.click(varSearchTermObj)
				WebUI.delay(1)
				WebUI.setText(varSearchTermObj, varSearchTerm)
				WebUI.delay(1)
				TestObject searchTermObj = findTestObject('Object Repository/OR_TestData/textField_TestDataSearchTerms')
				if(WebUI.waitForElementPresent(searchTermObj,30)){
					WebUI.click(searchTermObj)
					WebUI.delay(1)
					WebUI.setText(searchTermObj, searchText)
					WebUI.delay(1)
					if(clickApplyBtn()){
						result = true
						WebUI.delay(1)
					}
					else{
						MesmerLogUtils.markFailed("Unable to click on apply button")
					}
				}
				else{
					MesmerLogUtils.markFailed("Search term input field not found")
				}
			}
			else{
				MesmerLogUtils.markFailed("Search term variable input field not found")
			}
			}
			else{
				MesmerLogUtils.markFailed("Search term plus button not found")
			}
				return result
			}

			/**
			 * Check and click the search term add button
			 * @return
			 */
			public boolean clickCustomPlusBtn(){
				boolean result = false
				TestObject obj = findTestObject('Object Repository/OR_TestData/button_TestDataCustom')
				if(WebUI.waitForElementPresent(obj,30)){
					WebUI.click(obj)
					result = true
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markFailed("Custom plus button not found")
				}

				return result
			}
			/**
			 * Add custom field key and value in input fields
			 * @param key
			 * @param value
			 * @return
			 */
			public boolean addCustomKeyValue(String varCustom , String customValue){
				boolean result = false
				if(clickCustomPlusBtn()){
					TestObject varCustomObj = findTestObject('Object Repository/OR_TestData/textField_TestDataKey')
					if(WebUI.waitForElementPresent(varCustomObj,30)){
						WebUI.click(varCustomObj)
						WebUI.delay(1)
						WebUI.setText(varCustomObj, varCustom)
						WebUI.delay(1)

						TestObject customValueObj = findTestObject('Object Repository/OR_TestData/textField_customValue')
						if(WebUI.waitForElementPresent(customValueObj,30)){
							WebUI.click(customValueObj)
							WebUI.delay(1)
							WebUI.setText(customValueObj, customValue)
							WebUI.delay(1)
							if(clickApplyBtn()){
								result = true
								WebUI.delay(1)
							}
							else{
								MesmerLogUtils.markFailed("Unable to click on apply button")
							}
						}
						else{
							MesmerLogUtils.markFailed("Custom value input field not found")
						}
					}
					else{
						MesmerLogUtils.markFailed("Custom key input field not found")
					}
				}else{
					MesmerLogUtils.markFailed("Unable to click on custom plus button")
				}
				return result
			}

			/**
			 * Check and click the done button
			 * @return
			 */
			public boolean clickDoneBtn(){
				boolean result = false
				TestObject obj = findTestObject('Object Repository/OR_TestData/btnDone')
				if(WebUI.waitForElementVisible(obj,30)){
					WebUI.click(obj)
					result = true
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markFailed("Done button not found")
				}

				return result
			}

			/**
			 * Check and click the upload pre condition button
			 * @return
			 */
			public boolean clickUploadPreConditionScriptBtn(){
				boolean result = false
				TestObject obj = findTestObject('Object Repository/OR_TestData/input_UploadPreConditionScript')
				if(WebUI.waitForElementVisible(obj,30)){
					WebUI.click(obj)
					result = true
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markFailed("Upload pre condition button not found")
				}

				return result
			}

			public boolean uploadPreConditionScript(String filePath){
				boolean result = false
				if(clickUploadPreConditionScriptBtn()){
					TestObject obj = findTestObject('Object Repository/OR_TestData/input_UploadPreConditionScript')
					if(Utility.isWindows()){
						String Slash = "\\\\"
						WebUI.uploadFile(obj, filePath, FailureHandling.STOP_ON_FAILURE)
						result = true
					}else if(Utility.isMac()){
						String Slash = "/"
						WebUI.uploadFile(obj, filePath, FailureHandling.STOP_ON_FAILURE)
						result = true
					}
				}
				return result
			}

			/**
			 * Check and click the Apply button
			 * @return
			 */
			public boolean clickApplyBtn(){
				boolean result = false
				TestObject obj = findTestObject('Object Repository/OR_TestResult/btnReply')
				if(WebUI.waitForElementVisible(obj,30)){
					WebUI.click(obj)
					result = true
					WebUI.delay(2)
				}
				else{
					MesmerLogUtils.markFailed("Unable to click on apply button")
				}

				return result
			}

			/**
			 * Click and input User name
			 * @return
			 */
			public boolean enterUsername(String varUsername , String valueUsername){
				boolean result = false
				WebDriver driver = DriverFactory.getWebDriver()

				TestObject plusSignObj = findTestObject('Object Repository/OR_TestData/plus_enterUsername')
				if(WebUI.waitForElementPresent(plusSignObj,20)){
					WebUI.scrollToElement(plusSignObj, 10)
					WebUI.click(plusSignObj)
					WebUI.delay(1)

					TestObject varUsernameObj = findTestObject('Object Repository/OR_TestData/variable_userName')
					if(WebUI.waitForElementVisible(varUsernameObj,30)){
						WebUI.click(varUsernameObj)
						WebUI.delay(1)
						WebUI.setText(varUsernameObj, varUsername)
						WebUI.delay(1)

						TestObject valueUsernameObj = findTestObject('Object Repository/OR_TestData/value_userName')
						if(WebUI.waitForElementVisible(valueUsernameObj,30)){
							WebUI.click(valueUsernameObj)
							WebUI.delay(1)
							WebUI.setText(valueUsernameObj, valueUsername)
							WebUI.delay(1)
							if(clickApplyBtn()){
								result = true
								WebUI.delay(6)
							}
							else{
								MesmerLogUtils.markFailed("Unable to click on apply button")
							}
						}
						else{
							MesmerLogUtils.markFailed("Unable to set text in value username input field")
						}
					}
					else{
						MesmerLogUtils.markFailed("Unable to set text in variable username input field")
					}
				}
				else{
					MesmerLogUtils.markFailed("Unable to click on plus button")
				}

				return result
			}

			/**
			 * Click and input Address
			 * @return
			 */
			public boolean enterAddress(String varAddress , String firstName , String lastName , String addressLine , String addressLineTwo , String city , String state , String zipCode , String country){
				boolean result = false
				WebDriver driver = DriverFactory.getWebDriver()

				TestObject plusSign = findTestObject('Object Repository/OR_TestData/plus_address')
				if(WebUI.waitForElementPresent(plusSign,20)){
					WebUI.scrollToElement(plusSign, 10)
					WebUI.click(plusSign)
					WebUI.delay(1)

					TestObject varAddressObj = findTestObject('Object Repository/OR_TestData/var_address')
					if(WebUI.waitForElementPresent(varAddressObj,30)){
						WebUI.click(varAddressObj)
						WebUI.delay(1)
						WebUI.setText(varAddressObj, varAddress)
						WebUI.delay(1)

						TestObject firstNameObj = findTestObject('Object Repository/OR_TestData/address_firstName')
						if(WebUI.waitForElementPresent(firstNameObj,30)){
							WebUI.click(firstNameObj)
							WebUI.delay(1)
							WebUI.setText(firstNameObj, firstName)
							WebUI.delay(1)

							TestObject lastNameObj = findTestObject('Object Repository/OR_TestData/address_lastName')
							if(WebUI.waitForElementPresent(lastNameObj,30)){
								WebUI.click(lastNameObj)
								WebUI.delay(1)
								WebUI.setText(lastNameObj, lastName)
								WebUI.delay(1)

								TestObject addressLineObj = findTestObject('Object Repository/OR_TestData/address_addressLine')
								if(WebUI.waitForElementPresent(addressLineObj,30)){
									WebUI.click(addressLineObj)
									WebUI.delay(1)
									WebUI.setText(addressLineObj, addressLine)
									WebUI.delay(1)

									TestObject addressLineTwoObj = findTestObject('Object Repository/OR_TestData/address_addressLineTwo')
									if(WebUI.waitForElementPresent(addressLineTwoObj,30)){
										WebUI.click(addressLineTwoObj)
										WebUI.delay(1)
										WebUI.setText(addressLineTwoObj, addressLineTwo)
										WebUI.delay(1)

										TestObject cityObj = findTestObject('Object Repository/OR_TestData/address_city')
										if(WebUI.waitForElementPresent(cityObj,30)){
											WebUI.click(cityObj)
											WebUI.delay(1)
											WebUI.setText(cityObj, city)
											WebUI.delay(1)

											TestObject stateObj = findTestObject('Object Repository/OR_TestData/address_state')
											if(WebUI.waitForElementPresent(stateObj,30)){
												WebUI.click(stateObj)
												WebUI.delay(1)
												WebUI.setText(stateObj, state)
												WebUI.delay(1)

												TestObject zipCodeObj = findTestObject('Object Repository/OR_TestData/address_zipCode')
												if(WebUI.waitForElementPresent(zipCodeObj,30)){
													WebUI.click(zipCodeObj)
													WebUI.delay(1)
													WebUI.setText(zipCodeObj, zipCode)
													WebUI.delay(1)

													TestObject countryObj = findTestObject('Object Repository/OR_TestData/address_country')
													if(WebUI.waitForElementPresent(countryObj,30)){
														WebUI.click(countryObj)
														WebUI.delay(1)
														WebUI.setText(countryObj, country)
														WebUI.delay(1)
														if(clickApplyBtn()){
															result = true
															WebUI.delay(6)
														}
														else{
															MesmerLogUtils.markFailed("Unable to click on apply button")
														}
													}
													else{
														MesmerLogUtils.markFailed("Unable to click on apply button")
													}
												}
												else{
													MesmerLogUtils.markFailed("Unable to set text in value username input field")
												}
											}
											else{
												MesmerLogUtils.markFailed("Unable to set text in variable username input field")
											}
										}
										else{
											MesmerLogUtils.markFailed("Unable to click on apply button")
										}
									}
									else{
										MesmerLogUtils.markFailed("Unable to set text in value username input field")
									}
								}
								else{
									MesmerLogUtils.markFailed("Unable to set text in variable username input field")
								}
							}
							else{
								MesmerLogUtils.markFailed("Unable to click on apply button")
							}
						}
						else{
							MesmerLogUtils.markFailed("Unable to set text in value username input field")
						}
					}
					else{
						MesmerLogUtils.markFailed("Unable to set text in variable username input field")
					}
				}
				else{
					MesmerLogUtils.markFailed("Unable to click on plus button")
				}

				return result
			}
		}
