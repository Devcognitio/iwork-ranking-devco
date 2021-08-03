package com.iwork.linkedinrank.controllers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iwork.linkedinrank.model.Profile;
import com.iwork.linkedinrank.service.LinkedInRankService;

@RestController
@RequestMapping(value = "/linkedin-rank")
public class LinkedInRankController {	
	
	private final LinkedInRankService linkedInRankService;
	
	@Autowired
	public LinkedInRankController(LinkedInRankService linkedInRankService) {
		
		this.linkedInRankService = linkedInRankService;
		
	}
	
    @RequestMapping(value="uploadFile",method = RequestMethod.POST)
    public ResponseEntity<List<Profile>> processRanking(@RequestParam("file") MultipartFile uploader) throws IOException {    	
    	
    	List<Profile> topProfiles = linkedInRankService.processRanking(uploader);
    	
        return new ResponseEntity<List<Profile>>( topProfiles, HttpStatus.OK);
    }
}
