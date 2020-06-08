package com.peterstev;

import com.peterstev.models.Data;
import com.peterstev.models.UserData;
import com.peterstev.network.ApiGenerator;
import com.peterstev.network.ApiInterface;
import com.peterstev.utils.Functions;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

public class Main {

    private static List<UserData> userDataList = new ArrayList<>();

    public static void main(String[] args) {
        /*
         * Change page number to retrieve other pages from the API.
         * 1. The list of most active authors according to a set threshold
         * 2. The author with the highest comment count.
         * 3. The list of the authors sorted by when their record was created according to a set threshold.
         */

        System.out.println("Enter page number to fetch data (1-2)");
        Scanner scanner = new Scanner(System.in);
        getData(scanner.nextInt());

        if (!userDataList.isEmpty()){
            Scanner threshold = new Scanner(System.in);
            System.out.println("\n\nTo filter record by most active authors with the highest submission count, enter a threshold value");
            getUsernames(threshold.nextInt()).forEach(System.out::println);

            System.out.println("\n\nTo filter record by date created (old-new), enter a threshold value");
            getUsernamesSortedByRecordDate(threshold.nextInt()).forEach(System.out::println);

            System.out.println("\n\nThe user with the highest comment count is :: " + getUsernameWithHighestCommentCount());
        }else {
            System.out.println("Error occurred, check connection and retry");
        }
    }

    //fetch data from API
    private static void getData(int page) {
        System.out.println("fetching data...");
        ApiInterface apiInterface = ApiGenerator.getApiInterface();
        Call<Data> call = apiInterface.getUsers(page);
        try {
            Response<Data> response = call.execute();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    Data data = response.body();
                    userDataList.addAll(data.getUserData());
                    System.out.println(data.toString());
                } else {
                    System.out.println("Response is null");
                }
            } else {
                System.out.println("Response is unsuccessful");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //authors sorted by when their record was created
    public static List<String> getUsernamesSortedByRecordDate(int threshold) {
        List<UserData> userData = userDataList;
        userData.sort(Comparator.comparingInt(UserData::getCreatedAt));
        List<String> sortedList = new ArrayList<>();
        for (int x = 0; x < userData.size(); x++) {
            if ((x + 1) > threshold) {
                break;
            }
            sortedList.add(userData.get(x).getUsername() + "(" + Functions.longToDate(userData.get(x).getCreatedAt().longValue()) + ")");
        }
        return sortedList;
    }


    //most active authors(using submission_count as the criteria)
    public static List<String> getUsernames(int threshold) {
        List<UserData> userData = userDataList;
        userData.sort((data1, data2) -> data2.getSubmissionCount() - data1.getSubmissionCount());
        List<String> sortedList = new ArrayList<>();
        for (int x = 0; x < userData.size(); x++) {
            if ((x + 1) > threshold) {
                break;
            }
            sortedList.add(userData.get(x).getUsername() + "(" + userData.get(x).getSubmissionCount() + ")");
        }
        return sortedList;
    }

    //The author with the highest comment count.
    public static String getUsernameWithHighestCommentCount() {
        List<UserData> userData = userDataList;
        UserData user = Collections.max(userData, Comparator.comparingInt(UserData::getCommentCount));
        return user.getUsername() + "(" + user.getCommentCount() + ")";
    }
}
