package com.muse.home.legacy;

import com.muse.home.legacy.HapjuForm;
import com.muse.home.legacy.Hapju;

import java.util.Map;

public interface HapjuService {
    Hapju createHapju(HapjuForm hapjuform);
    Map<String,String> getHapjuList(String date);
    Map<String,String> getAllHapjuList();
}
