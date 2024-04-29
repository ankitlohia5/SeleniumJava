package Baseclass;

	import org.testng.IRetryAnalyzer;
	import org.testng.ITestResult;

	public class RetryAnalyzer implements IRetryAnalyzer {
	    private int retryCount = 0;
	    private static final int maxRetryCount = 3; // Maximum number of retry attempts

	    @Override
	    public boolean retry(ITestResult result) {
	        // Check if retry count is less than the maximum retry count
	        if (retryCount < maxRetryCount) {
	            System.out.println("Retrying test '" + result.getName() + "' with status '" +
	                               getResultStatusName(result.getStatus()) + "' for the " +
	                               (retryCount + 1) + " time(s).");
	            retryCount++; // Increment retry count
	            return true; // Retry the test
	        }
	        return false; // Do not retry the test
	    }

	    // Method to get the name of the test result status
	    private String getResultStatusName(int status) {
	        switch (status) {
	            case ITestResult.SUCCESS:
	                return "SUCCESS";
	            case ITestResult.FAILURE:
	                return "FAILURE";
	            case ITestResult.SKIP:
	                return "SKIP";
	            default:
	                return "UNKNOWN";
	        }
	    }
	}


