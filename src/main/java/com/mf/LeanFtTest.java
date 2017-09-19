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
    private enum LOG_LEVEL {INFO, ERROR}

    boolean INSTALL_APP = false;
    private static boolean UNINSTALL_APP;
    private static boolean HIGHLIGHT;
    private static boolean noProblem;
    private static Device device;
    private static String APP_IDENTIFIER;
    private static ApplicationDescription[] appDescription = new ApplicationDescription[1];
    private static Application app;
    private static String APP_VERSION;
    private static AppModelAOS appModel;

    @BeforeClass
    public void beforeClass() throws Exception {
    }

    @AfterClass
    public void afterClass() throws Exception {
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        logMessages("Enter setUp() method ", LOG_LEVEL.INFO);
        INSTALL_APP = false;
        UNINSTALL_APP = false;
        HIGHLIGHT = true;
        noProblem = true;
        APP_IDENTIFIER = "com.Advantage.iShopping";
        APP_VERSION = "1.1";

        try {
            device = initDevice();
            if (device != null) {
                appModel = new AppModelAOS(device);
                appDescription[0] = new ApplicationDescription.Builder().identifier(APP_IDENTIFIER).packaged(true).version(APP_VERSION).build();
                app = device.describe(Application.class, appDescription[0]);

                logMessages("Allocated device: \"" + device.getName() + "\" (" + device.getId() + "), Model :"
                        + device.getModel() + ", OS: " + device.getOSType() + " version: " + device.getOSVersion()
                        + ", manufacturer: " + device.getManufacturer() + ". App in use: \"" + app.getName()
                        + "\" v" + app.getVersion(), LOG_LEVEL.INFO);

                if (INSTALL_APP) {
                    logMessages("Installing app: " + app.getName(), LOG_LEVEL.INFO);
                    app.install();
                } else {
                    logMessages("Restarting app: " + app.getName(), LOG_LEVEL.INFO);
                    app.restart();
                }
            } else {
                logMessages("Device couldn't be allocated, exiting script", LOG_LEVEL.ERROR);
                noProblem = false;
            }
        } catch (Exception ex) {
            logMessages("Exception in setup(): " + ex.getMessage(), LOG_LEVEL.ERROR);
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
            logMessages("Tap 'Open Menu'", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().MenuButton().highlight();
            appModel.AdvantageShoppingApplication().MenuButton().tap();

            logMessages("Check if the user signed in", LOG_LEVEL.INFO);
            if (appModel.AdvantageShoppingApplication().SIGNOUTLabel().exists(5)) {
                signOut();
                windowSync(2000);
                logMessages("Tap 'Open Menu (after sign-out)'", LOG_LEVEL.INFO);
                appModel.AdvantageShoppingApplication().MenuButton().tap();
                windowSync(2000);
            }

            logMessages("Tap login label", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().LOGINLabel().highlight();
            appModel.AdvantageShoppingApplication().LOGINLabel().tap();

            logMessages("Type name", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().USERNAMEEditField().highlight();
            appModel.AdvantageShoppingApplication().USERNAMEEditField().setText("sshiff2");

            logMessages("Type password", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().PASSWORDEditField().highlight();
            appModel.AdvantageShoppingApplication().PASSWORDEditField().setSecure("97ededd61184a118aeb05c9627");

            logMessages("Tap login button", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().LOGINButton().highlight();
            appModel.AdvantageShoppingApplication().LOGINButton().tap();

            logMessages("Select 'laptop' category", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().LAPTOPSLabel().highlight();
            appModel.AdvantageShoppingApplication().LAPTOPSLabel().tap();

            logMessages("Select a laptop", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().SelectedLaptop4().highlight();
            appModel.AdvantageShoppingApplication().SelectedLaptop4().tap();

            logMessages("Tap 'Add to Cart' button", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().ADDTOCARTButton().highlight();
            appModel.AdvantageShoppingApplication().ADDTOCARTButton().tap();

            logMessages("Tap the back button", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().BackButton().highlight();
            appModel.AdvantageShoppingApplication().BackButton().tap();

            logMessages("Tap 'Open Menu'", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().MenuButton().highlight();
            appModel.AdvantageShoppingApplication().MenuButton().tap();

            logMessages("Tap 'Open Cart'", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().OpenCart().highlight();
            appModel.AdvantageShoppingApplication().OpenCart().tap();

            logMessages("Tap the checkout button", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().highlight();
            appModel.AdvantageShoppingApplication().CHECKOUTPAYButton().tap();

            logMessages("Tap the pay now button", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().PAYNOWButton().highlight();
            appModel.AdvantageShoppingApplication().PAYNOWButton().tap();

            logMessages("Tap OK", LOG_LEVEL.INFO);
            if (HIGHLIGHT)
                appModel.AdvantageShoppingApplication().OkButton().highlight();
            appModel.AdvantageShoppingApplication().OkButton().tap();

            appModel.AdvantageShoppingApplication().MenuButton().tap();
            signOut();

            logMessages("********** Test completed successfully **********", LOG_LEVEL.INFO);

        } catch (ReplayObjectNotFoundException ronfex) {
            logMessages("error code: " + ronfex.getErrorCode() + " - " + ronfex.getMessage(), LOG_LEVEL.ERROR);
            Assert.fail();
        }
    }

    private void windowSync(long millseconds) throws InterruptedException {
        Thread.sleep(millseconds);
    }

    private void logMessages(String message, LOG_LEVEL level) {
        String prefix = (level == LOG_LEVEL.INFO) ? "[INFO] " : "[ERROR] ";
        Status status = (level == LOG_LEVEL.INFO) ? Status.Passed : Status.Failed;
        System.out.println(prefix + " [" + getTimeStamp("dd/MM/yyyy HH:mm:ss") + "] " + message);
        try {
            Reporter.reportEvent(prefix, message, status);
        } catch (ReportException rex) {
            System.out.println("[ERROR] " + rex.getMessage());
        }
    }

    private String getTimeStamp(String pattern) {
        return new java.text.SimpleDateFormat(pattern).format(new java.util.Date());
    }

    private Device initDevice() {
        Device retDevice = null;
        try {
            logMessages("Init device capabilities...", LOG_LEVEL.INFO);
            DeviceDescription description = new DeviceDescription();

            description.setOsType("IOS");
            description.setOsVersion(">=9.3.4");
            description.setName("shiffs iPad");
            retDevice = MobileLab.lockDevice(description, appDescription, DeviceSource.MOBILE_CENTER);

        } catch (GeneralLeanFtException err) {
            logMessages("failed allocating device: " + err.getMessage(), LOG_LEVEL.ERROR);
        } catch (Exception ex) {
            logMessages("General error: " + ex.getMessage(), LOG_LEVEL.ERROR);
        } finally {
            logMessages("Exit initDevice()", LOG_LEVEL.INFO);
        }
        return retDevice;
    }

    private void signOut() throws GeneralLeanFtException {
        if (HIGHLIGHT)
            appModel.AdvantageShoppingApplication().SIGNOUTLabel().highlight();
        appModel.AdvantageShoppingApplication().SIGNOUTLabel().tap();

        if (HIGHLIGHT)
            appModel.AdvantageShoppingApplication().YesButton().highlight();
        appModel.AdvantageShoppingApplication().YesButton().tap();
    }
}