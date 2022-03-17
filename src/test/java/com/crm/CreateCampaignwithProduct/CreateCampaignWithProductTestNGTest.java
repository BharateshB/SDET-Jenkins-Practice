package com.crm.CreateCampaignwithProduct;


import org.testng.Reporter;
import org.testng.annotations.Test;
import com.crm.GenericLibrary.BaseClass;
import com.crm.objectRepository.CampaignInfoPage;
import com.crm.objectRepository.CampaignsPage;
import com.crm.objectRepository.CreateCampaignPage;
import com.crm.objectRepository.CreateProductspPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.ProdInfoPage;
import com.crm.objectRepository.ProductsPage;


public class CreateCampaignWithProductTestNGTest extends BaseClass {

	@Test
	
	public void createCampaignWithProduct() throws Throwable {
		
		
		
		/*Step 1: read all necessary data*/
		String excelcampaignName = eLib.readDataFromExcel("Campaign", 7, 2)+"_"+jLib.getRandomNumber();
		String excelprodName = eLib.readDataFromExcel("Campaign", 7, 3)+"_"+jLib.getRandomNumber();
		String excelProdCat = eLib.readDataFromExcel("Campaign", 7, 4);
		
		
		//Create Product
		/*Step2: Click on products link*/
		Homepage hp=new Homepage(driver);
		hp.ClickOnProductsLnk();
		
		/*Step3:Click on create Products link*/
		ProductsPage pp=new ProductsPage(driver);
		pp.createProdLink();
		
		/*Step4:Enter mandatory fields and save */
		CreateProductspPage cpp=new CreateProductspPage(driver);
		cpp.createproduct(excelprodName, excelProdCat);
		
		/*Step5: verify the product*/
		ProdInfoPage pip=new ProdInfoPage(driver);
		pip.productHeader(excelprodName);
		Reporter.log("product is verified successfully",true);
		
		// create a campaign with product 
		/*Step6: click on more link*/
		hp.ClickOnMoreLnk();
		
		/*Step7: click on campaign link*/
		hp.ClickOnCampaignLnk();
		
		/*Step8: click on create campaign link*/
		CampaignsPage cp= new CampaignsPage(driver);
		cp.createCampaignlink();
		
		/*Step9:Enter the mandatory fields and select product*/
		CreateCampaignPage ccp= new CreateCampaignPage(driver);
		ccp.createCampaign(driver, excelcampaignName, excelprodName);
		
		/*Step10: verify the campaign */
		CampaignInfoPage cip= new CampaignInfoPage(driver);
		cip.campHeader(excelcampaignName);
		Reporter.log("campaign is verified successfully",true);
		
	}
}
