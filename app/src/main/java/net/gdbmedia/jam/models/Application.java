package net.gdbmedia.jam.models;

import com.google.firebase.database.Exclude;

import net.gdbmedia.jam.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Laker77 on 9/9/2016.
 */
public class Application {

    private String companyName;
    private String jobTitle;
    private double dateApplied;
    private int status;
    private double followUpTimeFrame;
    private String applicationFormat;
    private int followUpFormat;
    private String city;
    private String state;
    private String hiringManagerName;
    private String wage;
    private int rank;
    private double interviewDate;

    public Application(String companyName, String jobTitle, double dateApplied, int status, double followUpTimeFrame, String applicationFormat, int followUpFormat, String city, String state, String hiringManagerName, String wage, int rank, double interviewDate) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.dateApplied = dateApplied;
        this.status = status;
        this.followUpTimeFrame = followUpTimeFrame;
        this.applicationFormat = applicationFormat;
        this.followUpFormat = followUpFormat;
        this.city = city;
        this.state = state;
        this.hiringManagerName = hiringManagerName;
        this.wage = wage;
        this.rank = rank;
        this.interviewDate = interviewDate;
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

    public double getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(double dateApplied) {
        this.dateApplied = dateApplied;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getFollowUpTimeFrame() {
        return followUpTimeFrame;
    }

    public void setFollowUpTimeFrame(double followUpTimeFrame) {
        this.followUpTimeFrame = followUpTimeFrame;
    }

    public String getApplicationFormat() {
        return applicationFormat;
    }

    public void setApplicationFormat(String applicationFormat) {
        this.applicationFormat = applicationFormat;
    }

    public int getFollowUpFormat() {
        return followUpFormat;
    }

    public void setFollowUpFormat(int followUpFormat) {
        this.followUpFormat = followUpFormat;
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

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(double interviewDate) {
        this.interviewDate = interviewDate;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(Constants.COMPANY_NAME, companyName);
        result.put(Constants.JOB_TITLE, jobTitle);
        result.put(Constants.STATUS, status);
        result.put(Constants.FOLLOW_UP_TIME_FRAME, followUpTimeFrame);
        result.put(Constants.APPLICATION_FORMAT, applicationFormat);
        result.put(Constants.FOLLOW_UP_FORMAT, followUpFormat);
        result.put(Constants.CITY, city);
        result.put(Constants.STATE, state);
        result.put(Constants.HIRING_MANAGER_NAME, hiringManagerName);
        result.put(Constants.RANK, rank);
        result.put(Constants.WAGE, wage);
        result.put(Constants.INTERVIEW_DATE, interviewDate);

        return result;
    }


}
