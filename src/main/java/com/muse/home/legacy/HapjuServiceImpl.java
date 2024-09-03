package com.muse.home.legacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class HapjuServiceImpl implements HapjuService{
    private final HapjuRepository hapjuRepository;
    @Autowired
    public HapjuServiceImpl(HapjuRepository hapjuRepository){
        this.hapjuRepository = hapjuRepository;
    }
    @Override
    public Hapju createHapju(HapjuForm hapjuForm)
    {
        Hapju hapju = new Hapju();

        hapju.setDate(dateTimeStringToDate(hapjuForm.getDate(), hapjuForm.getTime()));

        hapju.setHapjuInfo(hapjuForm.getHapjuInfo());

        return hapjuRepository.save(hapju);
    }

    @Override
    public Map<String, String> getHapjuList(String date) {
        Map<String, String> returnMap = new LinkedHashMap<>(); // 1. 순서 보장을 위해 LinkedHashMap 생성
        List<Hapju> sortedList = hapjuRepository.getHapjuListByDate(dateStringToDate(date));
        sortedList.sort(Comparator.comparing(Hapju::getDate)); // 2. sort
        for(Hapju h: sortedList){ // 3. 정렬된 리스트를 순회하며 LinkedHashMap에 삽입
            returnMap.put(hapjuToDateString(h), h.getHapjuInfo());
        }
        return returnMap;
    }

    @Override
    public Map<String, String> getAllHapjuList() {
        Map<String, String> returnMap = new LinkedHashMap<>(); // 1. 순서 보장을 위해 LinkedHashMap 생성
        List<Hapju> sortedList = hapjuRepository.getAllHapjuList();
        sortedList.sort(Comparator.comparing(Hapju::getDate)); // 2. sort
        for (Hapju h : sortedList) { // 3. 정렬된 리스트를 순회하며 LinkedHashMap에 삽입
            returnMap.put(hapjuToDateString(h), h.getHapjuInfo());
        }
        return returnMap;
    }

    public Date dateStringToDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return formatter.parse(date);
        }catch(ParseException e){
            return new Date(0);
        }
    }

    public Date dateTimeStringToDate(String date, String time){
        String dateTime = date+time;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm");
        try{
            return formatter.parse(dateTime);
        }catch(ParseException e){
            return new Date(0);
        }
    }
    public String hapjuToDateString(Hapju hapju){
        Date date = hapju.getDate();
        String [] daysKorean = {"일","월","화","수","목","금","토"};
        return (date.getMonth()+1)+"/"+date.getDate()+" ("+daysKorean[date.getDay()]+") "+date.getHours()+":"+date.getMinutes();
    }
}
