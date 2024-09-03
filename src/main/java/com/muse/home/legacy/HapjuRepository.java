package com.muse.home.legacy;

import com.muse.home.legacy.Hapju;

import java.util.Date;
import java.util.List;

public interface HapjuRepository {
    Hapju save(Hapju hapju);

    List<Hapju> getHapjuListByDate(Date date);

    List<Hapju> getAllHapjuList();
}
