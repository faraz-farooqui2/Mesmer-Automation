package com.mesmer

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.json.JSONArray
import org.json.JSONObject
import org.openqa.selenium.Keys

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import controllers.CreateTestController

public class MesmerRecordHelper {

	private static MesmerRecordHelper mInstance= null

	private MesmerRecordHelper(){
	}

	public static MesmerRecordHelper getInstance(){
		if(mInstance == null){
			mInstance = new MesmerRecordHelper()
		}

		return mInstance
	}

	/**
	 * Return the loaded device view
	 * @return
	 */
	public TestObject getDeviceView(){
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/div_device')
		return obj
	}

	/**
	 * Return the replay button
	 * @return
	 */
	public TestObject getReplyButton(){
		TestObject obj = findTestObject('Object Repository/OR_TestResult/btnReply')
		return obj
	}

	/**
	 * Return the replay button
	 * @return
	 */
	public TestObject getInputField(){
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/field_inputDialog')
		return obj
	}

	/**
	 * Return the landscape view
	 * @return
	 */
	public TestObject getMesmerAppLandscapeView(){
		TestObject obj = findTestObject('Object Repository/OR_CreateNewTestCase/check_landscapeImage')
		return obj
	}



	public void parseJsonAndPerformActions(String screenShotPath, String serialNum, String projectName, String platformName, String userName, String password){
		try{
			JSONObject obj = RecordJsonParser.getInstance().readJsonFile(projectName, platformName,"record_data.json")
			if(obj != null){
				JSONArray jsonArray = (JSONArray) obj.get("recording_Steps");
				if(jsonArray !=null && jsonArray.length() > 0){
					Utility.logCurrentUTCTime("Recording start time")
					CreateTestController.getInstance().enterFullScreenMode()
					TestObject divDevice = getDeviceView()
					TestObject fieldInput = getInputField()
					TestObject btnReply = getReplyButton()
					TestObject landscapeView = getMesmerAppLandscapeView()

					for(int i = 0; i < jsonArray.length(); i++){
						JSONObject stepObj = (JSONObject) jsonArray.get(i)
						if(stepObj != null){
							String stepStr = stepObj.optString("action")
							int x_Offset = Integer.parseInt(stepObj.optString("offset_x"))
							int y_Offset = Integer.parseInt(stepObj.optString("offset_y"))
							int delay = Integer.parseInt(stepObj.optString("action_delay"))
							switch(stepStr){
								case "tapAction":
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step1")
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step2")
									break
								case "input_username":
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step3")
									WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
									WebUI.delay(2)
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step4")
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
									WebUI.delay(2)
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step5")
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, userName))
									WebUI.delay(5)
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step6")
									WebUI.click(btnReply)
									WebUI.delay(delay)
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step7")
									break
								case "input_password":
									WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
									WebUI.delay(2)
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step8")
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
									WebUI.delay(2)
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, password))
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step9")
									WebUI.delay(5)
									WebUI.click(btnReply)
									WebUI.delay(delay)
									Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step10")
									break
								case "mesmer_app":
									if(WebUI.waitForElementVisible(landscapeView, 30)==true){

										MesmerLogUtils.markPassed("Device is successfully displayed in Landscape View")
										Utility.takeScreenshot(screenShotPath, serialNum +"-Recording-Step11")
										WebUI.delay(10)
										WebUI.clickOffset(divDevice, x_Offset, y_Offset)
										MesmerLogUtils.logInfo("Clicking Play button")
										WebUI.delay(10)
										Utility.takeScreenshot(screenShotPath, serialNum +"-After-Recording-Step12")
									}
									else{
										Utility.takeScreenshot(screenShotPath, serialNum +"-Device-not-inLandscapeview")
										MesmerLogUtils.markFailed("Device is not in Landscape View")
									}
									break

								default:
									MesmerLogUtils.logInfo("No step found in recording json")
							}
						}
					}
					CreateTestController.getInstance().exitFullScreenMode()
					Utility.logCurrentUTCTime("Recording end time")
				}
				else{
					MesmerLogUtils.logInfo("Recording steps not found")
				}
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
	}

	//	public void parseJsonAndPerformActions(String projectName, String platformName, String userName, String password){
	//		try{
	//			JSONObject obj = RecordJsonParser.getInstance().readJsonFile(projectName, platformName, "sandbox_record.json")
	//			if(obj != null){
	//				JSONArray jsonArray = (JSONArray) obj.get("recording_Steps");
	//				if(jsonArray !=null && jsonArray.length() > 0){
	//					Utility.logCurrentUTCTime("Recording start time")
	//					CreateTestController.getInstance().enterFullScreenMode()
	//					TestObject divDevice = getDeviceView()
	//					TestObject fieldInput = getInputField()
	//					TestObject btnReply = getReplyButton()
	//					for(int i = 0; i < jsonArray.length(); i++){
	//						JSONObject stepObj = (JSONObject) jsonArray.get(i)
	//						if(stepObj != null){
	//							String stepStr = stepObj.optString("action")
	//							int x_Offset = Integer.parseInt(stepObj.optString("offset_x"))
	//							int y_Offset = Integer.parseInt(stepObj.optString("offset_y"))
	//							int delay = Integer.parseInt(stepObj.optString("action_delay"))
	//							switch(stepStr){
	//								case "tapAction":
	//									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
	//									WebUI.delay(delay)
	//									MesmerLogUtils.logInfo("Tap Action "   +  WebUI.takeScreenshot())
	//									break
	//								case "input_username":
	//									WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	//									WebUI.delay(2)
	//									WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	//									WebUI.delay(2)
	//									WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, userName))
	//									WebUI.delay(15)
	//									WebUI.click(btnReply)
	//									WebUI.delay(delay)
	//									break
	//								case "input_password":
	//									WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
	//									WebUI.delay(2)
	//									WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
	//									WebUI.delay(2)
	//									WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, password))
	//									WebUI.delay(15)
	//									WebUI.click(btnReply)
	//									WebUI.delay(delay)
	//									break
	//								case "double_enter":
	//									WebUI.sendKeys(fieldInput, Keys.chord(Keys.ENTER))
	//									WebUI.delay(2)
	//									WebUI.sendKeys(fieldInput, Keys.chord(Keys.ENTER))
	//									WebUI.delay(2)
	//									WebUI.delay(delay)
	//									break
	//								case "longPress":
	//									CreateTestController.getInstance().checkAndClickLongPressGesture()
	//									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
	//									WebUI.delay(delay)
	//									MesmerLogUtils.logInfo("Long Press "   +  WebUI.takeScreenshot())
	//
	//									CreateTestController.getInstance().checkAndClickTapGesture()
	//
	//									break
	//								case "swipe":
	//									CreateTestController.getInstance().checkAndClickSwipeGesture()
	//
	//									WebUI.clickOffset(divDevice, 0, 100)
	//									WebUI.delay(10)
	//									TestObject linkBtn = findTestObject('Object Repository/OR_CreateNewTestCase/btn_tipDetailSwipeOk')
	//									if(WebUI.waitForElementPresent(linkBtn, 10)){
	//										WebUI.click(linkBtn)
	//										MesmerLogUtils.markPassed("Clicked on tooltip button")
	//									}else{
	//										MesmerLogUtils.logInfo("Could not click on tooltip button")
	//									}
	//									WebUI.clickOffset(divDevice, 0, -100)
	//									WebUI.delay(delay)
	//									MesmerLogUtils.logInfo("Swipe "   +  WebUI.takeScreenshot())
	//									CreateTestController.getInstance().checkAndClickTapGesture()
	//									break
	//								case "orientation":
	//									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
	//									WebUI.delay(delay)
	//									TestObject landScapeMode = findTestObject('Object Repository/OR_DeviceCertification/orientation_landscape')
	//									if(WebUI.waitForElementPresent(landScapeMode, 30)){
	//										MesmerLogUtils.markPassed("Screen rotate to landscape mode")
	//									}else{
	//										MesmerLogUtils.markFailed("Unable to rotate to landscape mode")
	//									}
	//									break
	//
	//								case "homebutton":
	//									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
	//									WebUI.delay(delay)
	//									break
	//
	//								case "backbutton":
	//									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
	//									WebUI.delay(delay)
	//									break
	//
	//								case "recentkey":
	//									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
	//									WebUI.delay(delay)
	//									break
	//								default:
	//									MesmerLogUtils.logInfo("No step found in recording json")
	//							}
	//						}
	//					}
	//					CreateTestController.getInstance().exitFullScreenMode()
	//					Utility.logCurrentUTCTime("Recording end time")
	//				}
	//				else{
	//					MesmerLogUtils.logInfo("Recording steps not found")
	//				}
	//			}
	//		}
	//		catch(Exception e){
	//			e.printStackTrace()
	//		}
	//	}

	public void parseJsonAndPerformActions(String projectName, String platformName, String deviceName , String userName, String password){
		try{
			JSONObject obj = RecordJsonParser.getInstance().readJsonFile(projectName, platformName, deviceName, "sandbox_record.json")
			if(obj != null){
				JSONArray jsonArray = (JSONArray) obj.get("recording_Steps");
				if(jsonArray !=null && jsonArray.length() > 0){
					Utility.logCurrentUTCTime("Recording start time")
					CreateTestController.getInstance().enterFullScreenMode()
					TestObject divDevice = getDeviceView()
					TestObject fieldInput = getInputField()
					TestObject btnReply = getReplyButton()
					for(int i = 0; i < jsonArray.length(); i++){
						JSONObject stepObj = (JSONObject) jsonArray.get(i)
						if(stepObj != null){
							String stepStr = stepObj.optString("action")
							int x_Offset = Integer.parseInt(stepObj.optString("offset_x"))
							int y_Offset = Integer.parseInt(stepObj.optString("offset_y"))
							int delay = Integer.parseInt(stepObj.optString("action_delay"))
							switch(stepStr){
								case "tapAction":
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									MesmerLogUtils.logInfo("Tap Action "   +  WebUI.takeScreenshot())
									break
								case "input_username":
									WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
									WebUI.delay(2)
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
									WebUI.delay(2)
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, userName))
									WebUI.delay(5)
									WebUI.click(btnReply)
									WebUI.delay(delay)
									break
								case "input_password":
									WebUI.sendKeys(divDevice, Keys.chord(Keys.INSERT, 'a'))
									WebUI.delay(2)
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.BACK_SPACE))
									WebUI.delay(2)
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.INSERT, password))
									WebUI.delay(5)
									WebUI.click(btnReply)
									WebUI.delay(delay)
									break
								case "double_enter":
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.ENTER))
									WebUI.delay(2)
									WebUI.sendKeys(fieldInput, Keys.chord(Keys.ENTER))
									WebUI.delay(2)
									WebUI.delay(delay)
									break
								case "longPress":
									CreateTestController.getInstance().checkAndClickLongPressGesture()
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									MesmerLogUtils.logInfo("Long Press "   +  WebUI.takeScreenshot())
									CreateTestController.getInstance().checkAndClickTapGesture()

									break
								case "swipe":
									CreateTestController.getInstance().checkAndClickSwipeGesture()

									WebUI.clickOffset(divDevice, 0, 100)
									WebUI.delay(10)
									TestObject linkBtn = findTestObject('Object Repository/OR_CreateNewTestCase/btn_tipDetailSwipeOk')
									if(WebUI.waitForElementPresent(linkBtn, 10)){
										WebUI.click(linkBtn)
										MesmerLogUtils.markPassed("Clicked on tooltip button")
									}else{
										MesmerLogUtils.logInfo("Could not click on tooltip button")
									}
									WebUI.clickOffset(divDevice, 0, -100)
									WebUI.delay(delay)
									MesmerLogUtils.logInfo("Swipe "   +  WebUI.takeScreenshot())
									CreateTestController.getInstance().checkAndClickTapGesture()
									break
								case "orientation":
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									TestObject landScapeMode = findTestObject('Object Repository/OR_DeviceCertification/orientation_landscape')
									if(WebUI.waitForElementPresent(landScapeMode, 30)){
										MesmerLogUtils.markPassed("Screen rotate to landscape mode")
									}else{
										MesmerLogUtils.markFailed("Unable to rotate to landscape mode")
									}

								case "homebutton":
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									break

								case "backbutton":
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									break

								case "recentkey":
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									break

								default:
									MesmerLogUtils.logInfo("No step found in recording json")
							}
						}
					}
					CreateTestController.getInstance().exitFullScreenMode()
					Utility.logCurrentUTCTime("Recording end time")
				}
				else{
					MesmerLogUtils.logInfo("Recording steps not found")
				}
			}else{
				MesmerLogUtils.markFailed("Required JSON Project for recording not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
	}

	public void parseJsonAndPerformActionsDeviceCert(String projectName, String platformName, String deviceName , String userName, String password, actionType){
		try{
			JSONObject obj = RecordJsonParser.getInstance().readJsonFileDeviceActions(projectName, platformName, deviceName, "androidactions_record.json", actionType)
			if(obj != null){
				JSONArray jsonArray = (JSONArray) obj.get("recording_Steps");
				if(jsonArray !=null && jsonArray.length() > 0){
					Utility.logCurrentUTCTime("Recording start time")
					CreateTestController.getInstance().enterFullScreenMode()
					TestObject divDevice = getDeviceView()
					TestObject fieldInput = getInputField()
					TestObject btnReply = getReplyButton()
					for(int i = 0; i < jsonArray.length(); i++){
						JSONObject stepObj = (JSONObject) jsonArray.get(i)
						if(stepObj != null){
							String stepStr = stepObj.optString("action")
							int x_Offset = Integer.parseInt(stepObj.optString("offset_x"))
							int y_Offset = Integer.parseInt(stepObj.optString("offset_y"))
							int delay = Integer.parseInt(stepObj.optString("action_delay"))
							switch(stepStr){
								case "homebutton":
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									break

								case "backbutton":
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									break

								case "recentkey":
									WebUI.clickOffset(divDevice, x_Offset, y_Offset)
									WebUI.delay(delay)
									break

								default:
									MesmerLogUtils.logInfo("No step found in recording json")
							}
						}
					}
					CreateTestController.getInstance().exitFullScreenMode()
					Utility.logCurrentUTCTime("Recording end time")
				}
				else{
					MesmerLogUtils.logInfo("Recording steps not found")
				}
			}else{
				MesmerLogUtils.markFailed("Required JSON Project for recording not found")
			}
		}
		catch(Exception e){
			e.printStackTrace()
		}
	}

	public void startWingstopAndroidRecording(String projectName, String platformName, String screenShotPath, String serialNum, String userName, String password){
		parseJsonAndPerformActions(projectName, platformName, userName, password)
	}

	public void swipeToGetStartedScreen(){
		TestObject divDevice = getDeviceView()
		WebUI.clickOffset(divDevice,130,200)
		WebUI.delay(4)
		CreateTestController.getInstance().checkAndClickOkToolTipInGestureView()
		WebUI.clickOffset(divDevice,-130,200)
		WebUI.delay(10)
		WebUI.clickOffset(divDevice,130,200)
		WebUI.delay(5)
		WebUI.clickOffset(divDevice,-130,200)
		WebUI.delay(10)
		WebUI.clickOffset(divDevice,130,200)
		WebUI.delay(5)
		WebUI.clickOffset(divDevice,-130,200)
		WebUI.delay(10)
		CreateTestController.getInstance().checkAndClickTapGesture()
		WebUI.delay(4)
		WebUI.clickOffset(divDevice,0,240)
		WebUI.delay(15)
	}

	public void startWingstopiOSRecording(String projectName, String platformName,String screenShotPath, String serialNum, String userName, String password){
		parseJsonAndPerformActions(projectName, platformName, userName, password)
	}

	public void startStarbucksAndroidRecording(String projectName, String platformName, String screenShotPath, String serialNum, String userName, String password){
		parseJsonAndPerformActions(screenShotPath, serialNum, projectName, platformName, userName, password)
	}

	public void startStarbucksiOSRecording(String projectName, String platformName, String screenShotPath, String serialNum, String userName, String password){
		parseJsonAndPerformActions(screenShotPath, serialNum, projectName, platformName, userName, password)
	}

	public void startMojioAndroid(String projectName, String platformName, String screenShotPath, String serialNum, String userName, String password){
		parseJsonAndPerformActions(projectName, platformName, userName, password)
	}

	public void startMojioiOS(String projectName, String platformName, String screenShotPath, String serialNum, String userName, String password){
		parseJsonAndPerformActions(projectName, platformName, userName, password)
	}

	public void startMesmeriOSRecording(String projectName, String platformName, String screenShotPath, String serialNum, String userName, String password){
		parseJsonAndPerformActions(screenShotPath, serialNum, projectName, platformName, userName, password)
	}
}
