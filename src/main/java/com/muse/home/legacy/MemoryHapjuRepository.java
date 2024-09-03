package com.muse.home.legacy;

import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

public class MemoryHapjuRepository implements HapjuRepository {
    private final Map<Date, String> hapjuStorage = new HashMap<>();
    @Override
    public Hapju save(Hapju hapju) {
        hapjuStorage.put(hapju.getDate(), hapju.getHapjuInfo());
        return hapju;
    }

    @Override
    public List<Hapju> getHapjuListByDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);
        List<Hapju> foundList = new ArrayList<>();

        for(Map.Entry<Date,String> entry : hapjuStorage.entrySet()){
            if(today.equals(formatter.format(entry.getKey()))){
                Hapju hapju = new Hapju();
                hapju.setDate(entry.getKey());
                hapju.setHapjuInfo(entry.getValue());
                foundList.add(hapju);
            }
        }
        return foundList;
    }

    @Override
    public List<Hapju> getAllHapjuList() {
        List<Hapju> foundList = new ArrayList<>();
        for(Map.Entry<Date, String> entry: hapjuStorage.entrySet()){
            Hapju hapju = new Hapju();
            hapju.setDate(entry.getKey());
            hapju.setHapjuInfo(entry.getValue());
            foundList.add(hapju);
        }
        return foundList;
    }
}
