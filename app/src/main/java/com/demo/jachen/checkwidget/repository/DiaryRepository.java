package com.demo.jachen.checkwidget.repository;

import com.demo.jachen.checkwidget.bean.Diary;
import com.demo.jachen.checkwidget.utils.Checker;
import com.demo.jachen.checkwidget.utils.FileUtil;
import com.demo.jachen.checkwidget.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JayChen on 2017/12/23.
 */

public class DiaryRepository {
    public static final String DIARIES_FILE_NAME = "Diaries.txt";

    public static List<Diary> getAll() {
        String result = FileUtil.read(DIARIES_FILE_NAME);
        if (Checker.isEmpty(result)) {
            return new ArrayList<>();
        } else {
            return GsonUtil.parseDiaries(result);
        }
    }

    public static void store(List<Diary> diaries) {
        FileUtil.save(DIARIES_FILE_NAME, GsonUtil.format(diaries));
    }
}
