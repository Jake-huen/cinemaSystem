import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RunningInfoManage {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static String pathInfo = "." + File.separator + "resource" + File.separator + "info.json";
    private static ArrayList<RunningInfo> riArr;

    public static ArrayList<RunningInfo> getRiArr() {
        getJson();
        return riArr;
    }
//    public static void setRiArr(ArrayList<RunningInfo> riArr) {
//        test.riArr = riArr;
//    }

    public static List<RunningInfo> findByMovieName(String mName) {
        getJson();
        List<RunningInfo> a = riArr.stream().filter(m -> m.getMovieName().equals(mName)).collect(Collectors.toList());

        return a;
    }

    public static void removeRi(RunningInfo ri) {
        getJson();
        try {
            FileWriter fw = new FileWriter(pathInfo);
            for (int i = 0; i < riArr.size(); i++) {
                if (riArr.get(i).getMovieName().equals(ri.getMovieName())
                        && riArr.get(i).getTheater().equals(ri.getTheater())
                        && riArr.get(i).getTime().equals(ri.getTime())
                        && riArr.get(i).getDate().equals(ri.getDate())) {
                    riArr.remove(i);
                }
            }

            gson.toJson(riArr, fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRiArr(ArrayList<RunningInfo> riArr) {
        RunningInfoManage.riArr = riArr;
    }

    public static void getJson() {
        riArr = new ArrayList<RunningInfo>();
        RunningInfo[] riArrTmp;

        try {
            Reader reader = new FileReader(pathInfo);
            riArrTmp = gson.fromJson(reader, RunningInfo[].class);
            for (RunningInfo ri : riArrTmp) {
                riArr.add(ri);
                // System.out.println(ri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setJson(String date, String time, String movie, String theater, ArrayList<ReserveInfo> rsiArr) {
        getJson();
        try {
            FileWriter fw = new FileWriter(pathInfo);
            RunningInfo ri = new RunningInfo(date, time, movie, theater.replaceAll("\"", ""), rsiArr);
            riArr.add(ri);

            gson.toJson(riArr, fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void updateReserve(RunningInfo ri, ReserveInfo rsi) {
        getJson();
        try {
            FileWriter fw = new FileWriter(pathInfo);
            for (RunningInfo jsonRI : riArr) {
                if (ri.getMovieName().equals(jsonRI.getMovieName())
                        && ri.getTheater().equals(jsonRI.getTheater())
                        && ri.getTime().equals(jsonRI.getTime())
                        && ri.getDate().equals(jsonRI.getDate())) {
                    System.out.println("true");
                    ArrayList<ReserveInfo> jsonReserve = jsonRI.getReserve();
                    jsonReserve.add(rsi);
                }
            }
            gson.toJson(riArr, fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String[]> readDateRi(String date, int theaterindex) {//특정날짜에 상영하는 상영정보 뽑기
        getJson();
        String theaterName = (TheaterDataManage.readIndexTheaterName(theaterindex)).replaceAll("\"", "");
        ArrayList<String[]> rt = new ArrayList<String[]>(0);
        for (int i = 0; i < riArr.size(); i++) {
            String[] tmp = new String[2];//tmp[0]은 시간정보, [1]은 영화이름
            String getTheater = riArr.get(i).getTheater().replace("\"", "");
            if (riArr.get(i).getDate().equals(date) && getTheater.equals(theaterName)) {
                tmp[0] = riArr.get(i).getTime();
                tmp[1] = riArr.get(i).getMovieName();
                rt.add(tmp);
            }
        }
        return rt;
    }

//    public static void main(String[] args) {
//    	getJson();
//    }

    // 해당 날짜에 상영하는 상영정보 반환 (날짜만 고려) 
    public static ArrayList<RunningInfo> readDateRi(String date) {//특정날짜에 상영하는 상영정보 뽑기
        getJson();
        ArrayList<RunningInfo> rt = new ArrayList<RunningInfo>();
        for (int i = 0; i < riArr.size(); i++) {
            if (riArr.get(i).getDate().equals(date)) {
                rt.add(riArr.get(i));
            }
        }
        return rt;
    }

    // 예매 내역 수정하는 함수 
    public static void modifyReserve(RunningInfo ri, ReserveInfo rsi, String id) {
        getJson();
        try (FileWriter fw = new FileWriter(pathInfo)) {
            for (RunningInfo jsonRI : riArr) {
                if (ri.getMovieName().equals(jsonRI.getMovieName())
                        && ri.getTheater().equals(jsonRI.getTheater())
                        && ri.getTime().equals(jsonRI.getTime())
                        && ri.getDate().equals(jsonRI.getDate())) {

                    ArrayList<ReserveInfo> jsonReserve = jsonRI.getReserve();
                    int rsrvArrLen = jsonReserve.size();
                    for (int i = 0; i < rsrvArrLen; i++) {
                        // 해당 id 갖는 ReserveInfo 찾아서 내용변경
                        if (jsonReserve.get(i).getUserId().equals(id)) {
                            jsonReserve.set(i, rsi);
                        }
                    }
                }
            }
            gson.toJson(riArr, fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 예매 내역 삭제하는 함수 
    public static void removeReserve(RunningInfo ri, ReserveInfo rsi, String id) {
        getJson();
        try (FileWriter fw = new FileWriter(pathInfo)) {
            for (RunningInfo jsonRI : riArr) {
                if (ri.getMovieName().equals(jsonRI.getMovieName())
                        && ri.getTheater().equals(jsonRI.getTheater())
                        && ri.getTime().equals(jsonRI.getTime())
                        && ri.getDate().equals(jsonRI.getDate())) {

                    ArrayList<ReserveInfo> jsonReserve = jsonRI.getReserve();
                    for (ReserveInfo rsrvInfo : jsonReserve) {
                        // 해당 id 갖는 ReserveInfo 찾아서 삭제
                        if (rsrvInfo.getUserId().equals(id)) {
                            int rmIndex = jsonReserve.indexOf(rsrvInfo);
                            jsonReserve.remove(rmIndex);
                            break;
                        }

                    }
                }
            }
            gson.toJson(riArr, fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int count(String date, String movieName, String theaterName) {
        int count = 0;
        getJson();
        for (int i = 0; i < riArr.size(); i++) {
            if (riArr.get(i).getDate().equals(date) && riArr.get(i).getTheater().equals(theaterName) && riArr.get(i).getMovieName().equals(movieName)) {
                count++;
            }
        }
        return count;
    }

    //아래의 int flag 변수는 info.json에서 date, movieName, theaterName이 일치하는 RunningInfo가 여러개 들어가 있다면 그 모든 객체를 찾기 위한 변수
    public static String checkDateMovieTheater(String date, String movieName, String theaterName, int count) {
        //info.json에서 date, movieName, theaterName이 일치하는 RunningInfo을 찾아서 time출력하기
        getJson();
        int flag = 0;
        for (int i = 0; i < riArr.size(); i++) {
            if (riArr.get(i).getDate().equals(date) && riArr.get(i).getTheater().equals(theaterName) && riArr.get(i).getMovieName().equals(movieName)) {
                if (flag == count)
                    return riArr.get(i).getTime();
                else
                    flag++;
            }
        }
        return null;
    }

    public static int checkReservedSeatsNum(String date, String movieName, String theaterName, int count) {
        //info.json에서 date, movieName, theaterName이 일치하는 RunningInfo을 찾아서 예약된 자리가 몇자리인지 출력하기
        getJson();
        int sumReservedSeats = 0;
        int flag = 0;
        for (int i = 0; i < riArr.size(); i++) {
            if (riArr.get(i).getDate().equals(date) && riArr.get(i).getTheater().equals(theaterName) && riArr.get(i).getMovieName().equals(movieName)) {
                if (count == flag) {
                    ArrayList<ReserveInfo> reserveInfo = riArr.get(i).getReserve();
                    if (reserveInfo == null)
                        return 0;
                    for (int j = 0; j < reserveInfo.size(); j++) {
                        sumReservedSeats += reserveInfo.get(j).getSeat().size();
                    }
                    return sumReservedSeats;
                } else {
                    flag++;
                }
            }
        }
        return -1;//일치하는 부분 없음
    }

    public static boolean timeOverlapCheck(String movieName, int _time) { // 시간 중복시 false 반환
        getJson();
        String theater;
        String time;
        String date;
        for (int i = 0; i < riArr.size(); i++) {
            theater = riArr.get(i).getTheater();
            if (riArr.get(i).getMovieName().equals(movieName)) {
                time = riArr.get(i).getTime();  // 변경 대상 영화 시작 시간 및 날짜
                date = riArr.get(i).getDate();
                LocalDate lcDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
                LocalTime lcTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));

                for (int j = 0; j < riArr.size(); j++) {
                    if (riArr.get(j).getTheater().equals(theater)) {  // 영화관 이름이 같은 경우만 탐색
                        System.out.println(riArr.get(j).getCode());
                        LocalDate targetDate = LocalDate.parse(riArr.get(j).getDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
                        LocalTime targetTime = LocalTime.parse(riArr.get(j).getTime(), DateTimeFormatter.ofPattern("HHmm"));
                        LocalDateTime before = LocalDateTime.of(lcDate, lcTime);
                        LocalDateTime trg = LocalDateTime.of(targetDate, targetTime);
                        if (trg.isAfter(before)) {
                            LocalDateTime chg = before.plusMinutes(_time);

                            if (chg.isAfter(trg))
                                return false;
                        }
                    }
                }

            }
        }
        return true;

    }

    public static boolean check_reserveInfo_for_delete(String theaterName) {
        getJson();
        for (int i = 0; i < riArr.size(); i++) {
            if (riArr.get(i).getTheater().equals(theaterName)) {
                if (riArr.get(i).getReserve() == null || riArr.get(i).getReserve().isEmpty()) {
                    continue;
                } else {
                    return false; //수정 불가능
                }
            }
        }
        return true; //수정 가능
    }

    //영화제목 수정시 예매내역의 모든 영화 이름도 수정 옛날이름:oldT 바꿀 영화이름:newT
    public static void fixMovieName(String old, String newT) {
        getJson();
        String oldT = old.split("/")[0].trim();
        RunningInfo ri;
        for (int i = 0; i < riArr.size(); i++) {
            if (riArr.get(i).getMovieName().equals(oldT)) {
                ri = riArr.get(i);
                ri.setMovieName(newT);
                riArr.set(i, ri);
            }
        }
        setRiArr(riArr);
        try {
            FileWriter fw = new FileWriter(pathInfo);
            gson.toJson(riArr, fw);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("good");
    }

    //좌석수수정을 위한 상영등록정보 확인
    public static boolean check_reserveInfo_for_fix(String theaterName, int row, int col) {
        getJson();
        //true면 수정불가
        for (int i = 0; i < riArr.size(); i++) {
            if (riArr.get(i).getTheater().equals(theaterName)) { //info.json에서 해당 상영관 정보를 찾음
                if (riArr.get(i).check(row, col)) {    //상영관 크기를 줄이려고 할때 손실될 좌석이 있는지 확인
                    return true;//수정불가
                }
            }
        }
        return false;
    }
}

