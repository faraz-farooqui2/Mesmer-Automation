package com.mesmer

import java.util.concurrent.TimeUnit
import java.util.logging.Level

//import org.apache.log4j.chainsaw.LoggingReceiver.Slurper
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.logging.LogEntries
import org.openqa.selenium.logging.LogEntry
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.chrome.ChromeDriver

import com.github.kklisura.cdt.protocol.commands.Fetch as Fetch
import com.github.kklisura.cdt.protocol.commands.Page as Page
import com.github.kklisura.cdt.services.ChromeDevToolsService as ChromeDevToolsService
import com.katalon.cdp.CdpUtils as CdpUtils
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.util.internal.Base64 as Base64
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable


class KTRequestHandler {
	private static String accessKey = "YmY1M2I0YTEtODc1Yi0zZTkyLThjODMtOTM1ZDQyYzRlNzI5IDVlMzE5NzJiNTIwOWRiMGM5Y2VlZTdlOSBVU0VSX0RFRkFVTFRfTkFNRQ"//"YmY1M2I0YTEtODc1Yi0zZTkyLThjODMtOTM1ZDQyYzRlNzI5IDVkOWM5NzRlMDI2NWNhMGRiOTU1YjczZiBVU0VSX0RFRkFVTFRfTkFNRQ";
	private static String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
	private static String secretKey = "W5qTLxolYXOx-dFZJ5GvfXbERkRsU-KqkWgoVpIFotg"//"Tj-keXyJaS1T--G4FELbGAKJpG09iZxbzMqhW66Xr5g"
	//	private static String accountId = "5d9c974e0265ca0db955b73f";// Zaman
	private static String accountId = "5e31972b5209db0c9ceee7e9";// MesmerAuto

	private static String mUserToken = "";

	private static String generateAccessToken(String endUrl, String requestMethod){
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId).build();
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		// API to which the JWT token has to be generated
		String createCycleUri = zephyrBaseUrl + endUrl;
		URI uri = new URI(createCycleUri);
		int expirationInSec = 6000;
		String jwt = jwtGenerator.generateJWT(requestMethod, uri, expirationInSec);
		// Print the URL and JWT token to be used for making the REST call
		//				System.out.println("FINAL API : " +uri.toString());
		//		System.out.println("JWT Token : " +jwt);
		return jwt;
	}

	public static ResponseObject executeApiRequest(String endPoint, String requestMethod, JSONObject body){
		String jwt = generateAccessToken(endPoint, requestMethod);
		TestObjectProperty header1 = new TestObjectProperty("zapiAccessKey", ConditionType.EQUALS, accessKey)
		TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, body == null? "text/plain":"application/json")
		TestObjectProperty header3 = new TestObjectProperty("Authorization", ConditionType.EQUALS, jwt)
		ArrayList defaultHeaders = Arrays.asList(header1, header2, header3)
		RequestObject ro = new RequestObject("objectId")
		ro.setRestUrl(zephyrBaseUrl + endPoint)
		ro.setHttpHeaderProperties(defaultHeaders)
		ro.setRestRequestMethod(requestMethod)
		if(body != null){
			ro.setBodyContent(new HttpTextBodyContent(body.toString(),"UTF-8"))
		}
		ResponseObject respObj = WS.sendRequest(ro)
		return respObj
	}

	public static List<Map<Object,Object>> getIssuesListDataFromJIRA(String vId, String pId){
		List<Map<Object,Object>> issuesListMap = new ArrayList();
		// Get CycleId
		String cycleIdEndPoint = "/public/rest/api/1.0/cycles/search?versionId=${vId}&expand=&projectId=${pId}"
		String cycleId = KTRequestHandler.getCycleId(cycleIdEndPoint, "GET");
		if(cycleId != null && !cycleId.equalsIgnoreCase("")){
			// Get folders list
			String foldersListEndpoint = "/public/rest/api/1.0/folders?versionId=${vId}&cycleId=${cycleId}&projectId=${pId}"
			List<String> foldersList = KTRequestHandler.getFoldersList(foldersListEndpoint, "GET");
			// Get issues list
			if(foldersList != null && foldersList.size() > 0){
				for(int i=0; i < foldersList.size(); i++){
					String folderId = foldersList.get(i).id
					String issuesListEndPoint = '/public/rest/api/2.0/executions/search/folder/'+folderId+'?projectId='+pId+'&versionId='+vId+'&cycleId='+cycleId+'&size=50'
					Map<Object, Object> issuesList = KTRequestHandler.getIssuesList(issuesListEndPoint, "GET")
					if(issuesList != null && !issuesList.isEmpty()){
						assert issuesList.get("searchResult") instanceof Map
						Map<Object, Object> searchResultsMap = issuesList.get("searchResult");
						if(searchResultsMap != null && searchResultsMap.get("searchObjectList") != null){
							assert searchResultsMap.get("totalCount").class == Integer
							int totalCount = searchResultsMap.get("totalCount");
							System.out.println("Total Count : "+totalCount)
							assert searchResultsMap.get("searchObjectList") instanceof List
							List<Object> searchObjectsList = searchResultsMap.get("searchObjectList");
							if(searchObjectsList != null && searchObjectsList.size() > 0){
								for(int j=0; j < searchObjectsList.size(); j++){
									if(searchObjectsList.get(j) != null){
										assert searchObjectsList.get(j) instanceof Map
										issuesListMap.add(searchObjectsList.get(j))
									}
								}
							}
						}
					}
					else{
						MesmerLogUtils.markError("Issues list is empty")
					}
					//				System.out.println("IssuesList : "+issuesList.get(0).toString())
				}
				issuesListMap = parseIssuesListData(issuesListMap);
			}
			else{
				MesmerLogUtils.markError("There is no folder for the given cycleId")
			}
		}
		else{
			MesmerLogUtils.markError("CycleId not found")
		}

		return issuesListMap;
	}

	private static List<Map<Object,Object>> parseIssuesListData(List<Map<Object, Object>> issuesListMap){
		List<Map<Object, Object>> dataList = new ArrayList()
		for(int i=0; i < issuesListMap.size(); i++){
			Map<Object,Object> item = issuesListMap.get(i)
			if(item != null && item.get("execution") != null){
				assert item.get("execution") instanceof Map
				Map<Object, Object> executionsMap = item.get("execution");
				if(executionsMap != null && !executionsMap.isEmpty()){
					Map<Object,Object> resultMap = new TreeMap();
					resultMap.put("id", executionsMap.get("id"))
					resultMap.put("issueId", executionsMap.get("issueId"))
					resultMap.put("versionId", executionsMap.get("versionId"))
					resultMap.put("projectId", executionsMap.get("projectId"))
					resultMap.put("cycleId", executionsMap.get("cycleId"))
					resultMap.put("cycleName", executionsMap.get("cycleName"))
					resultMap.put("folderId", executionsMap.get("folderId"))
					resultMap.put("folderName", executionsMap.get("folderName"))
					resultMap.put("issueKey", issuesListMap.get(i).get("issueKey"))
					resultMap.put("issueSummary", issuesListMap.get(i).get("issueSummary"))
					assert executionsMap.get("status") instanceof Map
					Map<Object, Object> statusMap = executionsMap.get("status")
					resultMap.put("statusName", statusMap.get("name"))
					resultMap.put("statusId", statusMap.get("id"))
					if(executionsMap.get("customFieldValues") != null){
						//						System.out.println("customFieldValues"+executionsMap.get("customFieldValues").toString());
						assert executionsMap.get("customFieldValues") instanceof List
						List<Object> customFieldsArray = executionsMap.get("customFieldValues");
						if(customFieldsArray != null && customFieldsArray.size() > 0){
							assert customFieldsArray.get(0) instanceof Map
							Map<Object, Object> customFieldsMap = customFieldsArray.get(0)
							//							System.out.println("customFieldsMap"+customFieldsMap.toString());
							resultMap.put("customFieldId",customFieldsMap.get("customFieldId"))
							assert customFieldsMap.get("value") as Map
							Map<Object, Object> valuesMap = customFieldsMap.get("value")
							if(valuesMap !=null && !valuesMap.isEmpty()){
								resultMap.put("customFieldValue",valuesMap.get("value"))
							}
						}
					}

					dataList.add(resultMap)
				}
			}
		}
		return dataList;
	}

	public static String getCycleId(String endPoint, String requestMethod){
		ResponseObject cycleIdResponse = executeApiRequest(endPoint, requestMethod, null);
		String cycleIndex = "";
		if(cycleIdResponse != null && cycleIdResponse.getStatusCode() == 200){
			String bodyContent = cycleIdResponse.getResponseBodyContent();
			if(bodyContent != null){
				def Slurper = new groovy.json.JsonSlurper()

				def result = Slurper.parseText(bodyContent)
				if(result != null){
					assert result instanceof List
					List<Object> cyclesList = result;
					if(cyclesList != null && cyclesList.size() > 1){
						for(int i=0; i < cyclesList.size(); i++){
							assert cyclesList.get(i) instanceof Map
							Map<Object, Object> cyclesMap = cyclesList.get(i)
							if(cyclesMap != null && !cyclesMap.isEmpty() && cyclesMap.get("name").equals(GlobalVariable.zephyrCycleName)){
								cycleIndex = cyclesMap.get("id");
								MesmerLogUtils.logInfo("CycleID :: "+cycleIndex)
							}
						}

					}
				}
			}
		}
		return cycleIndex;
	}

	public static String getFolderId(String endPoint, String requestMethod){
		ResponseObject folderIdResponse = executeApiRequest(endPoint, requestMethod, null);
		String folderId = "";
		if(folderIdResponse != null && folderIdResponse.getStatusCode() == 200){
			String folderContent = folderIdResponse.getResponseBodyContent();
			if(folderContent != null){
				def Slurper = new groovy.json.JsonSlurper()

				def folderResult = Slurper.parseText(folderContent);
				if(folderResult != null && folderResult[0] != null){
					folderId = folderResult[0].id;
					MesmerLogUtils.logInfo("FolderId :: "+folderId)
				}
			}
		}
		return folderId;
	}

	public static Map<Object,Object> getIssuesList(String endPoint, String requestMethod){
		ResponseObject issuesListResponse = executeApiRequest(endPoint, requestMethod, null);
		Map<Object,Object> result = null;
		if(issuesListResponse != null){
			String issuesListContent = issuesListResponse.getResponseBodyContent();
			if(issuesListContent != null){
				def Slurper = new JsonSlurper()
				def issuesListResult = Slurper.parseText(issuesListContent);
				assert issuesListResult instanceof Map
				result = issuesListResult
			}
		}
		return result;
	}

	public static List<String> getFoldersList(String endPoint, String requestMethod){
		ResponseObject foldersListResponse = executeApiRequest(endPoint, requestMethod, null);
		List<String> result = null;
		if(foldersListResponse != null && foldersListResponse.getStatusCode() == 200){
			String foldersListContent = foldersListResponse.getResponseBodyContent();
			if(foldersListContent != null){
				def Slurper = new JsonSlurper()
				def foldersResult = Slurper.parseText(foldersListContent);
				if(foldersResult != null){
					assert foldersResult instanceof ArrayList
					result = foldersResult;
					MesmerLogUtils.logInfo("FolderName :: "+foldersResult.get(0).name.toString())
				}
			}
		}
		return result;
	}

	public static void updateTaskOnZephyr(String testCaseId, String platFormName, int statusId){
		Row resultRow = WriteExcelSheet.readDataFromExcel(testCaseId, platFormName)
		if(resultRow != null){
			KTRequestHandler.updateTaskStatus(resultRow, statusId);
		}
	}

	public static void updateTaskStatus(Row row, int testCaseStatusId){
		String executionId = row.getCell(0)
		String executionEndPath = "/public/rest/api/1.0/execution/${executionId}"
		//		String body = '{"status":{"id":1},"projectId":10400,"issueId":36218,"cycleId":"75ec3cb7-57d8-4c6d-b380-bd47efd84091","versionId":10030}'
		JSONObject body = new JSONObject();
		JSONObject statusObj = new JSONObject()
		statusObj.put("id",testCaseStatusId)
		body.put("status",statusObj)
		body.put("projectId", row.getCell(3))
		body.put("issueId", row.getCell(1))
		body.put("cycleId",row.getCell(4))
		body.put("versionId",row.getCell(2))
		ResponseObject foldersListResponse = executeApiRequest(executionEndPath, "PUT", body);
		MesmerLogUtils.logInfo("Execution Update Response :: "+foldersListResponse.getResponseBodyContent())

	}


	private static String loginMesmerUser(String email, String password, String baseUrl, String requestMethod){
		TestObjectProperty header1 = new TestObjectProperty("Origin", ConditionType.EQUALS, baseUrl)
		TestObjectProperty header2 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		TestObjectProperty header3 = new TestObjectProperty("x-access-token", ConditionType.EQUALS, "")
		ArrayList defaultHeaders = Arrays.asList(header1,header2, header3)
		RequestObject ro = new RequestObject("objectId")
		ro.setRestUrl(baseUrl+"api/login")
		ro.setHttpHeaderProperties(defaultHeaders)
		ro.setRestRequestMethod(requestMethod)
		JSONObject body = new JSONObject();
		body.put("email", email)
		body.put("password", password)
		if(body != null){
			ro.setBodyContent(new HttpTextBodyContent(body.toString(),"UTF-8"))
		}
		String resultToken = ""
		ResponseObject respObj = WS.sendRequest(ro)
		if(respObj != null && respObj.getStatusCode() == 200){
			String userLoginDetails = respObj.getResponseBodyContent();
			MesmerLogUtils.logInfo("Login api response parsed :: "+ userLoginDetails)
			if(userLoginDetails != null){
				def Slurper = new JsonSlurper()
				def userObjResult = Slurper.parseText(userLoginDetails);
				assert userObjResult instanceof Map
				Map<Object,Object> result = userObjResult
				resultToken = result.get("token")
			}
		}
		return resultToken
	}

	public static String getTestCaseIdFromApi(String userName, String password, String baseUrl, String requestMethod, String projectId, String buildId, String testCaseId){
		String userToken = loginMesmerUser(userName, password,baseUrl, "POST")
		String testCaseResultID = ""
		if(userToken != null && !userToken.equalsIgnoreCase("")){
			TestObjectProperty header1 = new TestObjectProperty("Origin", ConditionType.EQUALS, baseUrl)
			TestObjectProperty header3 = new TestObjectProperty("x-access-token", ConditionType.EQUALS, userToken)
			ArrayList defaultHeaders = Arrays.asList(header1, header3)
			RequestObject ro = new RequestObject("objectId")
			ro.setRestUrl(baseUrl+"api/${projectId}/test-case/${testCaseId}/history?build=${buildId}")
			ro.setHttpHeaderProperties(defaultHeaders)
			ro.setRestRequestMethod(requestMethod)

			ResponseObject respObj = WS.sendRequest(ro)
			if(respObj != null && respObj.getStatusCode() == 200){
				String testCaseHistoryDetails = respObj.getResponseBodyContent();
				MesmerLogUtils.logInfo("History api response parsed ::\n"+ testCaseHistoryDetails)
				if(testCaseHistoryDetails != null){
					def Slurper = new JsonSlurper()
					def historyObjResult = Slurper.parseText(testCaseHistoryDetails);
					assert historyObjResult instanceof Map
					Map<Object,Object> result = historyObjResult
					assert result.get("data") instanceof ArrayList
					ArrayList<Object> dataArray = result.get("data")
					if(dataArray != null && dataArray.size() > 0){
						assert dataArray.get(0) instanceof Map
						Map<Object,Object> dataMap = dataArray.get(0)
						if(dataMap != null){
							testCaseResultID = dataMap.get("_id")
							MesmerLogUtils.logInfo("Test Case Result ID :: "+ testCaseResultID)
						}
					}
					else{
						MesmerLogUtils.markWarning("History api data not found")
					}
				}
			}
		}
		return testCaseResultID
	}

	public static String getDDCrawlLogs(String userName, String password, String selectedProject, String platformName){
		String projectId = getProjectFromList(userName,password, selectedProject, platformName)
		if(projectId != null && !projectId.isEmpty()){
			String lastCrawlId = getLastCrawlId(projectId)
			if(lastCrawlId != null && !lastCrawlId.isEmpty()){
				getCrawlLogsErrors(lastCrawlId)
			}
			else{
				MesmerLogUtils.logInfo("Last crawl id not found")
			}
		}
		else{
			MesmerLogUtils.logInfo("ProjectId not found")
		}
	}

	private static String getLastCrawlId(String projectId){
		String baseUrl = "https://qa.qa-auto.nonprod.appsight.us/api/${projectId}/build?limit=50"
		TestObjectProperty header1 = new TestObjectProperty("x-access-token", ConditionType.EQUALS, mUserToken)
		TestObjectProperty header3 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		ArrayList defaultHeaders = Arrays.asList(header1, header3)

		RequestObject ro = new RequestObject("objectId")
		ro.setRestUrl(baseUrl)
		ro.setHttpHeaderProperties(defaultHeaders)
		ro.setRestRequestMethod("GET")
		ResponseObject respObj = WS.sendRequest(ro)

		String lastCrawlId = "";
		if(respObj != null && respObj.getStatusCode() == 200){
			String lastCrawlObj = respObj.getResponseBodyContent();
			MesmerLogUtils.logInfo("Last crawl object information ::\n"+ lastCrawlObj)
			if(lastCrawlObj != null){
				def Slurper = new JsonSlurper()
				def historyObjResult = Slurper.parseText(lastCrawlObj);
				assert historyObjResult instanceof Map
				Map<Object, Object> resultObj = historyObjResult
				if(resultObj != null){
					assert resultObj.get("data") instanceof Map
					Map<Object, Object> dataObj = resultObj.get("data")
					if(dataObj != null){
						assert dataObj.get("docs") instanceof ArrayList
						ArrayList<Object> docsArrayList = dataObj.get("docs")
						if(docsArrayList != null && docsArrayList.size() > 0){
							assert docsArrayList.get(0) instanceof Map
							Map<Object, Object> docDataMap = docsArrayList.get(0)
							if(docDataMap != null){
								lastCrawlId = docDataMap.get("lastCrawl")
							}
						}

					}
				}
			}
		}
		return lastCrawlId
	}

	private static String getSelectedProjectId(String userName, String password, String selectedProject, String platformName){
		String userToken = loginMesmerUser(userName, password,Utility.getBaseUrl(), "POST")
		TestObjectProperty header1 = new TestObjectProperty("x-access-token", ConditionType.EQUALS, userToken)
		TestObjectProperty header3 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		ArrayList defaultHeaders = Arrays.asList(header1, header3)

		String queryStr = "https://qa.qa-auto.nonprod.appsight.us/api/me"
		RequestObject ro = new RequestObject("objectId")
		ro.setRestUrl(queryStr)
		ro.setHttpHeaderProperties(defaultHeaders)
		ro.setRestRequestMethod("GET")
		ResponseObject respObj = WS.sendRequest(ro)

		String selectProjectId = ""
		if(respObj != null && respObj.getStatusCode() == 200){
			String selectedProjectObj = respObj.getResponseBodyContent();
			MesmerLogUtils.logInfo("Get selected project api response parsed ::\n"+ selectedProjectObj)
			if(selectedProjectObj != null){
				def Slurper = new JsonSlurper()
				def historyObjResult = Slurper.parseText(selectedProjectObj);
				assert historyObjResult instanceof Map
				Map<Object, Object> resultObj = historyObjResult
				assert resultObj.get("user") instanceof Map
				Map<Object, Object> userObj = resultObj.get("user")
				if(userObj !=null && userObj.get("selectedProject") != null && !userObj.get("selectedProject").toString().isEmpty()){
					selectProjectId = userObj.get("selectedProject")
				}
			}
		}
		return selectProjectId
	}

	public static void getCrawlLogsErrors(String jobId){
		String queryParm = 'count:mesmer.crawl.driver{job-id:'+jobId+'}by{status,error_type}'
		String fromDate = getDateMilis("from");//"1582298347"
		String toDate = getDateMilis("to");//"1584007257"
		String queryStr = 'https://api.datadoghq.com/api/v1/query?query='+encode(queryParm)+'&from='+fromDate+'&to='+toDate
		String apiKey = "5a0b57f6ac12b4a3ae336685c098a2f6"
		String applicationKey = "d05789e8097c06434dbc776cbb556cec349808b5"
		TestObjectProperty header1 = new TestObjectProperty("DD-API-KEY", ConditionType.EQUALS, apiKey)
		TestObjectProperty header2 = new TestObjectProperty("DD-APPLICATION-KEY", ConditionType.EQUALS, applicationKey)
		TestObjectProperty header3 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		ArrayList defaultHeaders = Arrays.asList(header1,header2, header3)

		RequestObject ro = new RequestObject("objectId")
		ro.setRestUrl(queryStr)
		ro.setHttpHeaderProperties(defaultHeaders)
		ro.setRestRequestMethod("GET")

		ResponseObject respObj = WS.sendRequest(ro)
		if(respObj != null && respObj.getStatusCode() == 200){
			String crawlLogsError = respObj.getResponseBodyContent();
			MesmerLogUtils.logInfo("Crawl errors api response parsed ::\n"+ crawlLogsError)
			if(crawlLogsError != null){
				def Slurper = new JsonSlurper()
				def historyObjResult = Slurper.parseText(crawlLogsError);
				assert historyObjResult instanceof Map
				Map<Object,Object> result = historyObjResult
				assert result.get("series") instanceof ArrayList
				ArrayList<Object> seriesArray = result.get("series")
				if(seriesArray != null && seriesArray.size() > 0){
					for(int i = 0; i < seriesArray.size(); i++){
						assert seriesArray.get(i) instanceof Map
						Map<Object, Object> seriesObj = seriesArray.get(i)
						if(seriesObj != null){
							assert seriesObj.get("tag_set") instanceof ArrayList
							ArrayList<Object> tagSetArray = seriesObj.get("tag_set")
							if(tagSetArray != null && tagSetArray.size() > 0){
								String errorType = tagSetArray.get(0)
								if(!errorType.isEmpty() && !errorType.contains("N/A")){
									MesmerLogUtils.logInfo("Error type for this tag set is :"+errorType)
								}
							}
							else{
								MesmerLogUtils.logInfo("There is no tag set for "+(i+1)+" iteration")
							}
						}
					}
				}
				else{
					MesmerLogUtils.logInfo("There is no series data in the response")
				}
			}
		}
	}

	private static String getProjectFromList(String userName, String password,String projectName, String platformName){
		String baseUrl = 'https://qa.qa-auto.nonprod.appsight.us/api/project?limit=50'

		String userToken = loginMesmerUser(userName, password,Utility.getBaseUrl(), "POST")
		mUserToken = userToken
		TestObjectProperty header1 = new TestObjectProperty("x-access-token", ConditionType.EQUALS, userToken)
		TestObjectProperty header3 = new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")
		ArrayList defaultHeaders = Arrays.asList(header1, header3)

		RequestObject ro = new RequestObject("objectId")
		ro.setRestUrl(baseUrl)
		ro.setHttpHeaderProperties(defaultHeaders)
		ro.setRestRequestMethod("GET")

		ResponseObject respObj = WS.sendRequest(ro)
		String projectId = ""
		if(respObj != null && respObj.getStatusCode() == 200){
			String projectsListObj = respObj.getResponseBodyContent();
			MesmerLogUtils.logInfo("Projects list ::\n"+ projectsListObj)
			if(projectsListObj != null){
				def Slurper = new JsonSlurper()
				def historyObjResult = Slurper.parseText(projectsListObj);
				assert historyObjResult instanceof Map
				Map<Object, Object> resultObj = historyObjResult

				assert resultObj.get("data") instanceof ArrayList

				ArrayList<Object> projectsList = resultObj.get("data")
				if(projectsList != null && projectsList.size() > 0){
					for(int i=0; i< projectsList.size(); i++){
						assert projectsList.get(i) instanceof Map
						Map<Object,Object> projectDetails = projectsList.get(i)
						String pName = ""
						if(platformName.equalsIgnoreCase("apple")){
							pName = "iOS"
						}
						else{
							pName = "Android"
						}
						if(projectDetails != null && projectName.equalsIgnoreCase(projectDetails.get("name")) &&
						pName.equalsIgnoreCase(projectDetails.get("projectType"))){
							projectId = projectDetails.get("_id")
						}
					}
				}

			}
		}

		return projectId

	}

	private static String encode(String url)
	{
		try {
			String encodeURL=URLEncoder.encode( url, "UTF-8" );
			return encodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Issue while encoding" +e.getMessage();
		}
	}

	private static String getDateMilis(String dateType){
		String result = ""
		if(dateType.equalsIgnoreCase("from")){
			Calendar last7days=Calendar.getInstance();
			last7days.add(Calendar.DAY_OF_YEAR,-7);
			result = TimeUnit.MILLISECONDS.toSeconds(last7days.getTimeInMillis()).toString()
		}
		else{
			Calendar today=Calendar.getInstance();
			result = TimeUnit.MILLISECONDS.toSeconds(today.getTimeInMillis()).toString()
		}
		//		MesmerLogUtils.logInfo("Time in milis: "+result)

		return result
	}

	private static Calendar clearTimes(Calendar c) {
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);
		return c;
	}

	public static getBrowserConsoleLogs(){
		WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()
		// printing JavaScript Console Log
		if (executedBrowser == WebUIDriverType.CHROME_DRIVER) {
			WebDriver driver =  DriverFactory.getWebDriver()
			LogEntries logs = driver.manage().logs().get(LogType.BROWSER)
			MesmerLogUtils.logInfo("In Browser console Logs :"+logs.toString())
			if(logs != null){
				for (LogEntry entry : logs){
					MesmerLogUtils.logInfo(" " + entry.getLevel() + " " + entry.getMessage())
				}
			}
			else{
				MesmerLogUtils.logInfo("Browser logs not found")
			}
		}
	}

	public static clearBrowserConsoleLogs(){
		WebDriver driver = DriverFactory.getWebDriver()
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "console.clear();";
		js.executeScript(script);
	}

	public static getXHRCalls(){
		ChromeDevToolsService cdts = CdpUtils.getService()
		Page page = cdts.getPage()
		Fetch fetch = cdts.getFetch()
		fetch.onRequestPaused({ def requestIntercepted ->
			String interceptionId = requestIntercepted.getRequestId()
			String url = requestIntercepted.getRequest().getUrl()
			boolean isMocked = url.contains('api.php')
			String response = ''
			String rawResponse = Base64.encode(response)
			System.out.printf('%s - %s%s', isMocked ? 'MOCKED' : 'CONTINUE', url, System.lineSeparator())
			if (isMocked) {
				fetch.fulfillRequest(interceptionId, 401, new ArrayList(), rawResponse, null)
			} else {
				fetch.continueRequest(interceptionId)
			}
		})

		fetch.enable()
		page.enable()
	}
}