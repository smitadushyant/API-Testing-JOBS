import java.io.FileInputStream;
import java.util.HashMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import Utility.DataUtil;

public class JobScrapper_RestAssuredAutomation {
	public static String BASE_URL = "https://jobs123.herokuapp.com/Jobs";
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static FileInputStream ip;
	Response response;
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1", priority = 1)
	// Excel sheet name and method name must similar to match the data provider data
	public void jobapipost(String jobTitle, String jobCompanyName, String jobLocation, String jobType,
		String jobPostedTime, String jobDescription, String jobID) {

		System.out.println("company name  = " + jobCompanyName);
		RequestSpecification httprequest = RestAssured.given();
		int jobIDnumeric = (int) Math.round(Double.parseDouble(jobID));
		HashMap data = new HashMap();
		data.put("Job Title", jobTitle);
		data.put("Job Company Name", jobCompanyName);
		data.put("Job Location", jobLocation);
		data.put("Job Type", jobType);
		data.put("Job Posted time", jobPostedTime);
		data.put("Job Description", jobDescription);
		data.put("Job Id", jobIDnumeric);

		/*
		 * "Job Id": 4000, "Job Title": "SE", "Job Company Name": "posted by sam",
		 * "Job Location": "pune", "Job Type": "java", "Job Posted time": "today",
		 * "Job Description": "fgfgfgfg"
		 */
		httprequest.header("Content-Type", "application/json");
		httprequest.body(data);

		response = httprequest.request(Method.POST, "https://jobs123.herokuapp.com/Jobs");
		int statuscode = response.getStatusCode();
		
		validatePOST();
		System.out.println("Status code for POSt job = " + statuscode);
		Reporter.log("Status code for POSt job = " + statuscode);

	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1", priority = 2)
	// Excel sheet name and method name must similar to match the data provider data
	public void jobapiput(String jobTitle, String jobCompanyName, String jobLocation, String jobType,
			String jobPostedTime, String jobID) {

		System.out.println("company name  = " + jobCompanyName);
		RequestSpecification httprequest = RestAssured.given();
		int jobIDnumeric = (int) Math.round(Double.parseDouble(jobID));
		HashMap data = new HashMap();
		data.put("Job Title", jobTitle);
		data.put("Job Company Name", jobCompanyName);
		data.put("Job Location", jobLocation);
		data.put("Job Type", jobType);
		data.put("Job Posted time", jobPostedTime);
		data.put("Job Id", jobIDnumeric);

		// https://numpyninja-joblistapi.herokuapp.com/Jobs
		/*
		 * "Job Id": 4000, "Job Title": "SE", "Job Company Name": "posted by sam",
		 * "Job Location": "pune", "Job Type": "java", "Job Posted time": "today",
		 * 
		 */
		httprequest.header("Content-Type", "application/json");
		httprequest.body(data);

		response = httprequest.request(Method.PUT, "https://jobs123.herokuapp.com/Jobs");
		int statuscode = response.getStatusCode();
		
		validatePUT();
		System.out.println("Status code for PUt job = " + statuscode);
		Reporter.log("Status code for PUT job = " + statuscode);

	}

	// Delete post URL https://jobs123.herokuapp.com/Jobs?Job%20Id=1017
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1", priority = 3) 
	// Excel sheet name and method name must similar to match the data provider data
	public void deletejobapi( String jobID) {

		System.out.println("Job id  = " + jobID);
		RequestSpecification httprequest = RestAssured.given();
		//Double jobIDnumeric = Double.parseDouble(jobID);
		int jobIDnumeric = (int) Math.round(Double.parseDouble(jobID));
		
		System.out.println("interger id  = " + jobIDnumeric);
		HashMap data = new HashMap();
		data.put("Job Id", jobIDnumeric);

		// https://numpyninja-joblistapi.herokuapp.com/Jobs
		/*
		 * "Job Id": 4000, "Job Title": "SE", "Job Company Name": "posted by sam",
		 * "Job Location": "pune", "Job Type": "java", "Job Posted time": "today",
		 * 
		 */
		httprequest.header("Content-Type", "application/json");
		httprequest.body(data);
		//System.out.println("URL = " + "https://jobs123.herokuapp.com/Jobs?Job%20Id="+jobIDnumeric2);
		response = httprequest.request(Method.DELETE, "https://jobs123.herokuapp.com/Jobs");
		int statuscode = response.getStatusCode();
		
		validateDelete();
		System.out.println("Status code for delete job = " + statuscode);
		Reporter.log("Status code for Delete job = " + statuscode);

	}
	
	public void validateDelete() {
		System.out.println("Response Body is: " + response.getBody().asString());
		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
	
		Assert.assertEquals(statusCode, 200);
	}
	public void validatePOST() {
		System.out.println("Response Body is: " + response.getBody().asString());
		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
	}
	public void validatePUT() {
		System.out.println("Response Body is: " + response.getBody().asString());
		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
	}
	public void validateGET() {
		System.out.println("Response Body is: " + response.getBody().asString());
		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
	}

	// Delete post URL https://jobs123.herokuapp.com/Jobs?Job%20Id=1017


}
