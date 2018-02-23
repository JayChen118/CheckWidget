package com.demo.jachen.checkwidget.library.repository;

import android.text.TextUtils;

import com.demo.jachen.checkwidget.library.bean.Diary;
import com.demo.jachen.checkwidget.library.utils.Checker;
import com.demo.jachen.checkwidget.library.utils.FileUtil;
import com.demo.jachen.checkwidget.library.utils.GsonUtil;
import com.demo.jachen.checkwidget.library.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JayChen on 2017/12/23.
 */

public class DiaryRepository {
    public static final String DIARIES_FILE_NAME = "Diaries.txt";

    private static List<Diary> diaries = new ArrayList<>();

    public static List<Diary> getAll() {
        if (Checker.isEmpty(diaries)) {
            String result = FileUtil.read(DIARIES_FILE_NAME);
            if (!Checker.isEmpty(result)) {
                diaries.addAll(GsonUtil.parseDiaries(result));
            }
        }
        return diaries;
    }

    public static void store(List<Diary> diaryList) {
        diaries.clear();
        diaries.addAll(diaryList);
        FileUtil.save(DIARIES_FILE_NAME, GsonUtil.format(diaryList));
    }

    public static boolean isTodayDairyFinished() {
        String today = TimeUtil.DATE.format(new Date());
        for (Diary diary : diaries) {
            if (TextUtils.equals(today, diary.getDate())) {
                return true;
            }
        }
        return false;
    }
}
