package com.example.demo.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson2.JSON;
import com.example.demo.dao.click.ExamDao;
import com.example.demo.entity.exam.Select;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class UploadSelectUtils<T> extends AnalysisEventListener<T> {


    /**
     * 每隔1000条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    private List<T> list = new ArrayList<T>(BATCH_COUNT);

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    @Autowired
    private ExamDao examDao;

    public UploadSelectUtils(ExamDao examDao) {
        this.examDao = examDao;
    }

    @Override
    public void invoke(T select, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(select));
        list.add(select);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        examDao.uploadSelect((List<Select>) list);
        log.info("存储数据库成功！");
    }
}
