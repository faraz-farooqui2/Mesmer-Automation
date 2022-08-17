import com.mesmer.KTRequestHandler
import com.mesmer.Utility
import com.mesmer.WriteExcelSheet

import internal.GlobalVariable as GlobalVariable
Utility.setPlatformName("ZephyrData")
try{
//	if(Utility.isSanityProfile()){
		//Fetch issues list from JIRA and save them to excel sheet
		fetchIssuesListFromJIRA()
//	}
}
catch(Exception e){
	e.printStackTrace()
}

private void fetchIssuesListFromJIRA(){
	List<Map<Object,Object>> issuesListMap = KTRequestHandler.getIssuesListDataFromJIRA(GlobalVariable.versionId,GlobalVariable.projectId);
	if(issuesListMap != null && issuesListMap.size() > 0){
		System.out.println("IssuesList Size : "+issuesListMap.size())
		WriteExcelSheet.saveDataToExcelFile(issuesListMap)
	}
}