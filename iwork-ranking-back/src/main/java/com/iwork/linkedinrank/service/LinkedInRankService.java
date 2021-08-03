package com.iwork.linkedinrank.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iwork.linkedinrank.model.Profile;

public interface LinkedInRankService {

	List<Profile> processRanking(MultipartFile uploader);
	
}
