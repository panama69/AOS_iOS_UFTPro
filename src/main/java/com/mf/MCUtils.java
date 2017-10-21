package com.mf;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.mobile.*;

enum LOG_LEVEL {INFO, ERROR}

public class MCUtils {

    public boolean INSTALL_APP = false;
    public boolean UNINSTALL_APP = false;
    public boolean HIGHLIGHT = false;
    public boolean IS_PACKAGED = false;
    public String APP_IDENTIFIER = "";
    public String APP_VERSION = "";
    public Application app = null;
    public Device device = null;

    //private boolean noProblem;
    private ApplicationDescription[] appDescription = new ApplicationDescription[1];

    public void MCUtils()
    {}

    public void windowSync(long millseconds) throws InterruptedException { Thread.sleep(millseconds); }

    public void logMessages(String message, LOG_LEVEL level) {
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

    public void setDevice(DeviceDescription deviceDescription) {
        this.device = null;
        try {
            logMessages ("Init device capabilities...", LOG_LEVEL.INFO);

            this.device = MobileLab.lockDevice(deviceDescription, this.appDescription, DeviceSource.MOBILE_CENTER);

        } catch (GeneralLeanFtException err) {
            logMessages ("failed allocating device: " + err.getMessage(), LOG_LEVEL.ERROR);
        } catch (Exception ex) {
            logMessages ("General error: " + ex.getMessage(), LOG_LEVEL.ERROR);
        } finally {
            logMessages ("Exit initDevice()", LOG_LEVEL.INFO);
        }
    }

    public void setDeviceById (String deviceId) throws GeneralLeanFtException
    {
        this.device = MobileLab.lockDeviceById(deviceId);
    }


    public void setApp() throws GeneralLeanFtException{
        ApplicationDescription localAppDescription = new ApplicationDescription.Builder().identifier(APP_IDENTIFIER).
                packaged(IS_PACKAGED).version(APP_VERSION).build();

        app = device.describe(Application.class, localAppDescription);
    }
}
