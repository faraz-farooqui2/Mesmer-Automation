package controllers

import java.util.regex.Pattern

import com.amazonaws.HttpMethod
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import com.amazonaws.services.s3.transfer.Upload
import com.kms.katalon.core.configuration.RunConfiguration
import com.mesmer.Utility

public class UploadFileController {

	private static UploadFileController mInstance = null

	private UploadFileController(){
	}

	public static UploadFileController getInstance(){
		if(mInstance == null){
			mInstance = new UploadFileController()
		}

		return mInstance
	}

	public String uploadFile(){
		String aws_access_key_id = "AKIAXJICXVGS2Z5IKTV6"
		String aws_secret_access_key = "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"
		Regions clientRegion = Regions.US_WEST_2;
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		String bucketName = "device-stability-reports";
		String keyName = "automation_report"+"_"+System.currentTimeMillis()
		String filePath = "";
		String fileUrl = ""
		String logFolderPath = RunConfiguration.getReportFolder()
		String fileSeprater = Pattern.quote(File.separator)
		String slash = "/"
		if(Utility.isWindows()){
			slash = "\\\\"
		}
		String[] logFileSplitter = logFolderPath.split(fileSeprater)
		if(logFileSplitter != null && logFileSplitter.length > 0){
			filePath = logFileSplitter[logFileSplitter.length-1]
			filePath = logFolderPath+slash+filePath+".html"
		}
		File file = new File(filePath);
		//		InputStream targetStream = new FileInputStream(file)
		//		long contentLength = file.length();
		//		long partSize = 5 * 1024 * 1024; // Set part size to 5 MB.

		try {
			// Set the presigned URL to expire after 72 hours.
			java.util.Date expiration = new java.util.Date();
			long expTimeMillis = expiration.getTime();
			expTimeMillis += 1000 * 60 * 60 * 72;
			expiration.setTime(expTimeMillis);

			BasicAWSCredentials awsCreds = new BasicAWSCredentials(aws_access_key_id, aws_secret_access_key);
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withRegion(Regions.fromName("us-west-2"))
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
					.disableChunkedEncoding()
					.build();

			TransferManager tm = TransferManagerBuilder.standard()
					.withS3Client(s3Client)
					.build();

			// TransferManager processes all transfers asynchronously,
			// so this call returns immediately.
			//			Upload upload = tm.upload(bucketName, keyName, targetStream, new ObjectMetadata())
			Upload upload = tm.upload(bucketName, keyName, file);
			System.out.println("Object upload started");

			// Optionally, wait for the upload to finish before continuing.
			upload.waitForCompletion();
			System.out.println("Object upload complete");
			//			fileUrl = s3Client.getUrl(bucketName, keyName)
			//			System.out.println("Object Url :"+fileUrl);
			// Generate the presigned URL.
			System.out.println("Generating pre-signed URL.");
			GeneratePresignedUrlRequest generatePresignedUrlRequest =
					new GeneratePresignedUrlRequest(bucketName, keyName)
					.withMethod(HttpMethod.GET)
					.withExpiration(expiration);
			URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
			fileUrl = url.toString()
			//			System.out.println("Pre-Signed URL: " + fileUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileUrl
	}

	public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
				folderName, emptyContent, metadata);
		// send request to S3 to create folder
		client.putObject(putObjectRequest);
	}
	/**
	 * This method first deletes all the files in given folder and than the
	 * folder itself
	 */
	public static void deleteFolder(String bucketName, String folderName, AmazonS3 client) {
		List fileList =
				client.listObjects(bucketName, folderName).getObjectSummaries();
		for (S3ObjectSummary file : fileList) {
			client.deleteObject(bucketName, file.getKey());
		}
		client.deleteObject(bucketName, folderName);
	}
}
