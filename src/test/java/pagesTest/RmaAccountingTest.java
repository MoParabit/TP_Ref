package pagesTest;

import org.testng.annotations.Test;

import baseUtil.BaseClass;

public class RmaAccountingTest extends BaseClass {

	@Test(enabled = true, priority = 1)
	public void endOfLifeTest() {
		rmaAccounting.login();
		rmaAccounting.endOfLife();
	}
	
	@Test(enabled = true, priority = 2)
	public void evaluatedTest() {
		rmaAccounting.login();
		rmaAccounting.evaluated();
	}
	
	@Test(enabled = true, priority = 3)
	public void rejectedTest() {
		rmaAccounting.login();
		rmaAccounting.rejected();
	}

	@Test
	public void accountingModuleTest(){
		rmaAccounting.login();
		rmaAccounting.statusCheck();
		rmaAccounting.finalTest();
	}
}