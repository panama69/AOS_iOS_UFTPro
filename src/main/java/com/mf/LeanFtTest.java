package com.mf;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.mobile.*;
import com.hp.lft.verifications.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {


    private static boolean noProblem;
    private static Device device;
    private static AppModelAOS appModel;
    MCUtils utils = new MCUtils();

    @BeforeClass
    public void beforeClass() throws Exception {
    }

    @AfterClass
    public void afterClass() throws Exception {
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        utils.logMessages("Enter setUp() method ", LOG_LEVEL.INFO );
        utils.INSTALL_APP = true;
        utils.UNINSTALL_APP = false;
        utils.HIGHLIGHT =true;
        utils.APP_IDENTIFIER = "com.Advantage.iShopping";
        utils.APP_VERSION = "1.1";
        utils.IS_PACKAGED = true;

        noProblem = true;

        try {
            DeviceDescription deviceDescription = new DeviceDescription();

            deviceDescription.setOsType("IOS");
            deviceDescription.setOsVersion(">=9.3.4");

            utils.setDevice(deviceDescription);

            if (utils.device != null) {
                appModel = new AppModelAOS(utils.device);
                utils.setApp();

                utils.logMessages ("Allocated device: \"" + utils.device.getName() + "\" (" + utils.device.getId() + "), Model :"
                        + utils.device.getModel() + ", OS: " + utils.device.getOSType() + " version: " + utils.device.getOSVersion()
                        + ", manufacturer: " + utils.device.getManufacturer() + ". App in use: \"" + utils.app.getName()
                        + "\" v" + utils.app.getVersion(), LOG_LEVEL.INFO);

                if (utils.INSTALL_APP) {
                    utils.logMessages ("Installing app: " + utils.app.getName(), LOG_LEVEL.INFO);
                    utils.app.install();
                } else {
                    utils.logMessages ("Restarting app: " + utils.app.getName(), LOG_LEVEL.INFO);
                    utils.app.restart();
                }
            } else {
                utils.logMessages ("Device couldn't be allocated, exiting script", LOG_LEVEL.ERROR);
                noProblem = false;
            }
        } catch (Exception ex) {
            utils.logMessages ("Exception in setup(): " + ex.getMessage(), LOG_LEVEL.ERROR);
            noProblem = false;
        }
    }

    @AfterMethod
    public void afterMethod() throws Exception {
    }

    @Test //(threadPoolSize = 10, invocationCount = 2)
    public void test() throws GeneralLeanFtException, InterruptedException {
        if (!noProblem) {
            Assert.fail();
            return;
        }

        try {
            utils.logMessages ("Tap 'Open Menu'", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().MenuButton().highlight();
            appModel.AdvantageShoppingApplication().MenuButton().tap();

            utils.logMessages ("Check if the user signed in", LOG_LEVEL.INFO);
            if (appModel.AdvantageShoppingApplication().SIGNOUTLabel().exists(5)) {
                signOut();
                utils.windowSync(2000);
                utils.logMessages ("Tap 'Open Menu (after sign-out)'", LOG_LEVEL.INFO);
                appModel.AdvantageShoppingApplication().MenuButton().tap();
                utils.windowSync(2000);
            }

            utils.logMessages ("Tap login label", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().LOGINLabel().highlight();
            appModel.AdvantageShoppingApplication().LOGINLabel().tap();

            utils.logMessages ("Type name", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().USERNAMEEditField().highlight();
            appModel.AdvantageShoppingApplication().USERNAMEEditField().setText("sshiff2");

            utils.logMessages ("Type password", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().PASSWORDEditField().highlight();
            appModel.AdvantageShoppingApplication().PASSWORDEditField().setSecure("97ededd61184a118aeb05c9627");

            utils.logMessages ("Tap login button", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().LOGINButton().highlight();
            appModel.AdvantageShoppingApplication().LOGINButton().tap();

            utils.logMessages ("Select 'laptop' category", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().LAPTOPSLabel().highlight();
            appModel.AdvantageShoppingApplication().LAPTOPSLabel().tap();

            utils.logMessages ("Select a laptop", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().SelectedLaptop4().highlight();
            appModel.AdvantageShoppingApplication().SelectedLaptop4().tap();

            utils.logMessages ("Tap 'Add to Cart' button", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().ADDTOCARTButton().highlight();
            appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();
            utils.windowSync(1500);

            utils.logMessages ("Tap the back button", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().BackButton().highlight();
            appModel.AdvantageShoppingApplication().BackButton().tap();

            utils.logMessages ("Tap 'Open Menu'", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().MenuButton().highlight();
            appModel.AdvantageShoppingApplication().MenuButton().tap();

            utils.logMessages ("Tap 'Open Cart'", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().OpenCart().highlight();
            appModel.AdvantageShoppingApplication().OpenCart().tap();

            utils.logMessages ("Tap the checkout button", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().highlight();
            appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().tap();

            utils.logMessages ("Tap the pay now button", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().PAYNOWButton().highlight();
            appModel.AdvantageShoppingApplication().PAYNOWButton().tap();

            utils.logMessages ("Tap OK", LOG_LEVEL.INFO);
            if (utils.HIGHLIGHT)
                appModel.AdvantageShoppingApplication().OkButton().highlight();
            appModel.AdvantageShoppingApplication().OkButton().tap();

            appModel.AdvantageShoppingApplication().MenuButton().tap();
            signOut();

            utils.logMessages ("********** Test completed successfully **********", LOG_LEVEL.INFO);

        } catch (ReplayObjectNotFoundException ronfex) {
            utils.logMessages ("error code: " + ronfex.getErrorCode() + " - " + ronfex.getMessage(), LOG_LEVEL.ERROR);
            Assert.fail();
        }
    }

    private void signOut() throws GeneralLeanFtException {
        if (utils.HIGHLIGHT)
            appModel.AdvantageShoppingApplication().SIGNOUTLabel().highlight();
        appModel.AdvantageShoppingApplication().SIGNOUTLabel().tap();

        if (utils.HIGHLIGHT)
            appModel.AdvantageShoppingApplication().YesButton().highlight();
        appModel.AdvantageShoppingApplication().YesButton().tap();
    }
}