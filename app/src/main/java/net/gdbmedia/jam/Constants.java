package net.gdbmedia.jam;

import java.util.ArrayList;
import java.util.Arrays;
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
    public static final String ONE_WEEK = "1 Week";
    public static final String TWO_WEEKS = "2 Weeks";
    public static final String THREE_WEEKS = "3 Weeks";
    public static final String ONE_MONTH = "1 Month";
    public static final ArrayList<String> FOLLOWUP_TIMEFRAME_LIST = new ArrayList<String>(){{
        add(ONE_WEEK);
        add(TWO_WEEKS);
        add(THREE_WEEKS);
        add(ONE_MONTH);
    }};
    public static final String IN_PERSON = "In Person";
    public static final String MONSTER = "Monster";
    public static final String JOBDANGO = "Jobdango";
    public static final String INDEED = "Indeed";
    public static final String EMAIL = "Email";
    public static final String LINKED_IN = "LinkedIn";
    public static final String COMPANY_WEBPAGE = "Company Webpage";
    public static final String OTHER = "Other";
    public static final String[] STATES_STRING_ARRAY = {"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming","District of Columbia","Puerto Rico","Guam","American Samoa","U.S. Virgin Islands","Northern Mariana Islands"};
    public static final ArrayList<String> STATES_ARRAYLIST = new ArrayList<>(Arrays.asList(STATES_STRING_ARRAY));
    public static final ArrayList<String> APPLICATION_FORMAT_ARRAY = new ArrayList<String>(){{
        add(IN_PERSON);
        add(MONSTER);
        add(JOBDANGO);
        add(INDEED);
        add(EMAIL);
        add(LINKED_IN);
        add(COMPANY_WEBPAGE);
        add(OTHER);
    }};
    public static final ArrayList<String> APPLICATION_STATUSES = new ArrayList<String>(){{
        add(ALL_APPLICATIONS);
        add(ACCEPTED);
        add(REJECTED);
        add(AWAITING_RESPONSE);
        add(HOPEFUL);
    }};

    public static final String STATUS_REF = "status";
    public static final String URL = "url";
    public static final String INPUT_FORMAT = "E MMM dd H:mm:ss z yyyy";
    public static final String SOURCE = "M/d/yyyy";
    public static final String USER_ID_REF = "userID";
    public static final String APPLY_DATE = "dateApplied";
    public static final String KEY = "key";
    public static final String USERS_REF = "users/";
    public static final String APPLICATIONS_REF = "/applications";
    public static final String STATUS_DB_REF = "status";

    public static final Map<String, Integer> STATUS_MAP;
    static{
        HashMap<String, Integer> status_map = new HashMap<>();
        for(int i = 0; i< APPLICATION_STATUSES.size(); i++){
            status_map.put(APPLICATION_STATUSES.get(i), i);
        }
        STATUS_MAP = Collections.unmodifiableMap(status_map);
    }

    public static final Map<Integer, String> STATUS_MAP_REVERSE;
    static{
        HashMap<Integer, String> status_map = new HashMap<>();
        for(int i = 0; i< APPLICATION_STATUSES.size(); i++){
            status_map.put(i, APPLICATION_STATUSES.get(i));
        }
        STATUS_MAP_REVERSE = Collections.unmodifiableMap(status_map);
    }

    public static final Map<String,Long> FOLLOW_UP_TIMES_MAP;

    public static final long ONE_MONTH_UNIX =  2419200000L;
    public static final long THREE_WEEK_UNIX =  1814400000L;
    public static final long TWO_WEEK_UNIX =  1209600000L;
    public static final long ONE_WEEK_UNIX = 604800000L;
    public static final long ZERO =  0;

    public static final String EMPTY = "";

    static{
        HashMap<String, Long> follow_up_times = new HashMap<>();
        follow_up_times.put(ONE_WEEK, ONE_WEEK_UNIX);
        follow_up_times.put(TWO_WEEKS, TWO_WEEK_UNIX);
        follow_up_times.put(THREE_WEEKS, THREE_WEEK_UNIX);
        follow_up_times.put(ONE_MONTH, ONE_MONTH_UNIX);
        follow_up_times.put(EMPTY, ZERO);
        FOLLOW_UP_TIMES_MAP = Collections.unmodifiableMap(follow_up_times);
    }
    public static final String DATE_FORMAT_OUTPUT_YEAR = "MMMM d, yyyy";
}
