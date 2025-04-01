package genericListenserUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenserImp  implements IRetryAnalyzer{

	int count=0;
	int limitCount=3;
	@Override
	public boolean retry(ITestResult result) {
		if(count<limitCount)
				{
			count++;
			return true;
			
				}
		else
		{
			
		
		return false;
	}
	
}
}