package net.gdbmedia.jam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Laker77 on 9/9/2016.
 */
public class Constants {
    public static final String COMPANY_NAME = "companyName";
    public static final String JOB_TITLE = "jobTitle";
    public static final String STATUS = "status";
    public static final String FOLLOW_UP_TIME_FRAME = "followUpTimeFrame";
    public static final String APPLICATION_FORMAT = "applicationFormat";
    public static final String FOLLOW_UP_FORMAT = "followUpFormat";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String HIRING_MANAGER_NAME = "hiringManagerName";
    public static final String RANK = "rank";
    public static final String WAGE = "wage";
    public static final String INTERVIEW_DATE = "interviewDate";
    public static final String ALL_APPLICATIONS = "All Applications";
    public static final String ACCEPTED = "Accepted";
    public static final String REJECTED = "Rejected";
    public static final String AWAITING_RESPONSE = "Awaiting Response";
    public static final String HOPEFUL = "Hopeful";
    public static final ArrayList<String> APPLICATION_STATUSES = new ArrayList<String>(){{
        add(ALL_APPLICATIONS);
        add(ACCEPTED);
        add(REJECTED);
        add(AWAITING_RESPONSE);
        add(HOPEFUL);
    }};
    public static final Map<String, Integer> STATUS_MAP;
    public static final String STATUS_REF = "status";

    static{
        HashMap<String, Integer> status_map = new HashMap<>();
        status_map.put(ALL_APPLICATIONS, 0);
        status_map.put(ACCEPTED, 1);
        status_map.put(REJECTED, 2);
        status_map.put(AWAITING_RESPONSE, 3);
        status_map.put(HOPEFUL, 4);
        STATUS_MAP = Collections.unmodifiableMap(status_map);
    }
}
