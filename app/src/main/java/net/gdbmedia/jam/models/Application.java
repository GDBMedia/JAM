package net.gdbmedia.jam.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import net.gdbmedia.jam.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Laker77 on 9/9/2016.
 */
@IgnoreExtraProperties
public class Application {

    private String companyName;
    private String jobTitle;
    private long dateApplied;
    private int status;
    private long followUpTimeFrame;
    private String applicationFormat;
    private String url;
    private String city;
    private String state;
    private String hiringManagerName;
    private long interviewDate;
    private String key;

    public Application() {}

    public Application(String companyName, String jobTitle, long dateApplied, int status, long followUpTimeFrame, String applicationFormat, String url, String city, String state, String hiringManagerName, long interviewDate, String key) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.dateApplied = dateApplied;
        this.status = status;
        this.followUpTimeFrame = followUpTimeFrame;
        this.applicationFormat = applicationFormat;
        this.url = url;
        this.city = city;
        this.state = state;
        this.hiringManagerName = hiringManagerName;
        this.interviewDate = interviewDate;
        this.key = key;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public long getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(long dateApplied) {
        this.dateApplied = dateApplied;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getFollowUpTimeFrame() {
        return followUpTimeFrame;
    }

    public void setFollowUpTimeFrame(long followUpTimeFrame) {
        this.followUpTimeFrame = followUpTimeFrame;
    }

    public String getApplicationFormat() {
        return applicationFormat;
    }

    public void setApplicationFormat(String applicationFormat) {
        this.applicationFormat = applicationFormat;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHiringManagerName() {
        return hiringManagerName;
    }

    public void setHiringManagerName(String hiringManagerName) {
        this.hiringManagerName = hiringManagerName;
    }

    public long getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(long interviewDate) {
        this.interviewDate = interviewDate;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(Constants.COMPANY_NAME, companyName);
        result.put(Constants.JOB_TITLE, jobTitle);
        result.put(Constants.APPLY_DATE, dateApplied);
        result.put(Constants.STATUS, status);
        result.put(Constants.FOLLOW_UP_TIME_FRAME, followUpTimeFrame);
        result.put(Constants.APPLICATION_FORMAT, applicationFormat);
        result.put(Constants.URL, url);
        result.put(Constants.CITY, city);
        result.put(Constants.STATE, state);
        result.put(Constants.HIRING_MANAGER_NAME, hiringManagerName);
        result.put(Constants.INTERVIEW_DATE, interviewDate);
        result.put(Constants.KEY, key);

        return result;
    }


}
