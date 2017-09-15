package com.jiuzhi.common.resource.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jiuzhi.common.resource.entity.ImgRefer;


@Repository
public interface ImgReferDao {
	
	List<ImgRefer> getImgRefer();
}
