package com.cucumber.testng.project_constants.enams;

public enum Frame {
    MENU("frameset|menu"), MAIN("frameset|main"), DATA_IFRAME("data-iframe"), DOCUMENT_VIEWER("frameset|main|documentviewer"), TABS("frameset|main|tabs"), TAB_FRAMES("frameset|main|tabsframe"), TAB_FRAMES_CLICKTHROUGH("main|tabsframe"), TAB_FRAMES_ORGIN("tabs"), PATIENT_DETAILS("frameset|main|patientdetails"), PATIENT_DETAILS_POPUP("patientdetails"), BACKGROUND("frameset|main|background"), RECORD_DATA("frameset|main|recorddata"), RECORD_DATA_CLICKTHROUGH("main|recorddata"), COVER_DOCUMENT_VIEWER("frameset|main|recorddata|coverdocumentviewer"),
    EXTEND_PATIENT_DETAILS("frameset|main|recorddata|extendedpatientdetails"), COVER_RECORD_DATA("frameset|main|recorddata|coverrecorddata"),
    DOCUMENT_CONTAINER("frameset|main|documentviewer|document-container"), DOCUMENT_CONTAINER_CLICKTHROUGH("main|documentviewer|document-container"), DOCUMENT_CONTAINER_POPUP("document-container"), PRINT_FORM("frameset|main|printforms|form"), FORM("frameset|main|form"), BLUE_HEADER("frameset|main|blueheader"), SEARCH_MAIN("frameset|main|searchmain"), MANAGE_PROFILE("frameset|main|manageprofile"),
    PDF_DISPLAY("frameset|main|pdfdisplay"), SEARCH_PATIENT_ACCESS("frameset|main|searchpatientaccess"), HM_CONTENT("frameset|main|hmcontent"), HM_CONTENT_POP_UP("hmcontent"), HM_NAVIGATION("frameset|main|hmnavigation"), ADD_PATIENTS_FRAME("frameset|main|manageprofile|addpatientsframe"), DRAFT_VIEW("draft-view"), PATIENT_MAIN("frameset|main|patientmain"), PRINT_FORMS("frameset|main|printforms"), RESULT_LIST("frameset|main|resultlist"),
    TARGET("frameset|main|target"), SOURCE("frameset|main|source"), TARGET_TABS("frameset|main|target|tabs"), SOURCE_TABS("frameset|main|source|tabs"), RECORD_DATA_SELECT_TARGET("frameset|main|target|recorddataselect_target"), REPORT_BODY("frameset|main|reportbody"), MANAGE_COLLECTORS("frameset|main|managecollectors"), RECORD_DATA_SELECT_SOURCE("frameset|main|source|recorddataselect_source"), RECORD_DATA_POPUP("recorddata"), SYSTEM_STATUS("frameset|main|systemstatus"), PATIENT_DETAILS_SELECT_SOURCE("frameset|main|source|patientdetailsselect_source"), MAIN_MAIN("frameset|main|main"), MAIN_DRAFT_VIEW("frameset|main|draft-view");
    private final String frame;

    private Frame(String frame) {
        this.frame = frame;
    }

    public String getFrameName() {
        return frame;
    }

}