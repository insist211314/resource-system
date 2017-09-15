package com.jiuzhi.common.resource.dao.extend;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileJobExtendDao {

    public int updateByTaskWaitCount(@Param("id") Long id, @Param("processIds") List<Long> processIds);

    public int updateByCompletedDownload(@Param("processId")Long processId, @Param("isFail")Boolean isFail);

}