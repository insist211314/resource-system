package com.jiuzhi.common.resource.manage;


import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface JobProcessRelationshipManage {

    Long save(Long jobId, Long processId);

    List<Long> findByProcessId(Long processId);

    Boolean isExists(Long jobId, Long processId);



}
