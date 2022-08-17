package com.mesmer

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import net.minidev.json.parser.JSONParser


public class RecordJsonParser {
	private static RecordJsonParser mInstance = null

	private RecordJsonParser(){
	}

	public static RecordJsonParser getInstance(){
		if(mInstance == null){
			mInstance = new RecordJsonParser()
		}
		return mInstance
	}

	public JSONObject readJsonFile(String projectName, String platformName, String fileName){
		JSONObject result = null
		try{
			// parsing file "record_data.json"
			String jsonFilePath = RunConfiguration.getProjectDir()+"/"+fileName
			Object obj = new JSONParser().parse(new FileReader(jsonFilePath));

			// typecasting obj to JSONObject
			JSONObject jo = (JSONObject) obj;
			if(jo != null){
				JSONArray ja = jo.get("recording_apps")
				if(ja != null && ja.length() > 0){
					for(int i = 0; i < ja.length(); i++){
						JSONObject recordAppObj = ja.getJSONObject(i)
						if(recordAppObj != null){
							MesmerLogUtils.logInfo("JSON Data :: "+recordAppObj.toString())
							String projName = recordAppObj.optString("projectName")
							String platName = recordAppObj.optString("platformName")
							if(projName.equals(projectName) && platName.equals(platformName)){
								result = recordAppObj
								MesmerLogUtils.logInfo("Result JSON Data :: "+result.toString())
							}
						}
					}
				}
			}
		}
		catch(JSONException e){
			e.printStackTrace()
		}
		catch(Exception e){
			e.printStackTrace()
		}

		return result
	}



	public JSONObject readJsonFile(String projectName, String platformName, String deviceName, String fileName){
		JSONObject result = null
		try{
			// parsing file "record_data.json"
			String jsonFilePath = RunConfiguration.getProjectDir()+"/"+fileName
			Object obj = new JSONParser().parse(new FileReader(jsonFilePath));

			// typecasting obj to JSONObject
			JSONObject jo = (JSONObject) obj;
			if(jo != null){
				JSONArray ja = jo.get("recording_apps")
				if(ja != null && ja.length() > 0){
					for(int i = 0; i < ja.length(); i++){
						JSONObject recordAppObj = ja.getJSONObject(i)
						if(recordAppObj != null){
							//	MesmerLogUtils.logInfo("JSON Data :: "+recordAppObj.toString())
							String projName = recordAppObj.optString("projectName")
							String platName = recordAppObj.optString("platformName")
							String device_Name = recordAppObj.optString("deviceName")
							if(projName.equals(projectName) && platName.equals(platformName) && device_Name.contains(deviceName)){
								result = recordAppObj
								MesmerLogUtils.logInfo("Device  ::" +  device_Name  +  " JSON Data :: "+result.toString())
							}
						}
					}
				}
			}
		}
		catch(JSONException e){
			e.printStackTrace()
		}
		catch(Exception e){
			e.printStackTrace()
		}

		return result
	}

	public JSONObject readJsonFileDeviceActions(String projectName, String platformName, String deviceName, String fileName, String actionType){
		JSONObject result = null
		try{
			// parsing file "record_data.json"
			String jsonFilePath = RunConfiguration.getProjectDir()+"/"+fileName
			Object obj = new JSONParser().parse(new FileReader(jsonFilePath));

			// typecasting obj to JSONObject
			JSONObject jo = (JSONObject) obj;
			if(jo != null){
				JSONArray ja = jo.get("recording_apps")
				if(ja != null && ja.length() > 0){
					for(int i = 0; i < ja.length(); i++){
						JSONObject recordAppObj = ja.getJSONObject(i)
						if(recordAppObj != null){
							//	MesmerLogUtils.logInfo("JSON Data :: "+recordAppObj.toString())
							String projName = recordAppObj.optString("projectName")
							String platName = recordAppObj.optString("platformName")
							String device_Name = recordAppObj.optString("deviceName")
							String device_action = recordAppObj.optString("actionType")
							if(projName.equals(projectName) && platName.equals(platformName) && device_Name.contains(deviceName) && device_action.contains(actionType)){
								result = recordAppObj
								MesmerLogUtils.logInfo("Device  ::" +  device_Name  +  " JSON Data :: "+result.toString())
							}
						}
					}
				}
			}
		}
		catch(JSONException e){
			e.printStackTrace()
		}
		catch(Exception e){
			e.printStackTrace()
		}

		return result
	}
}
