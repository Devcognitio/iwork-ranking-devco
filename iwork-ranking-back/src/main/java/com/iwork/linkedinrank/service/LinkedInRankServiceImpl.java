package com.iwork.linkedinrank.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iwork.linkedinrank.model.Profile;
import com.iwork.linkedinrank.model.config.DataParameters;
import com.iwork.linkedinrank.model.config.LinkedInParameters;;

@Service
public class LinkedInRankServiceImpl implements LinkedInRankService {

	private final int FIELDS = 8;

	@Override
	public List<Profile> processRanking(MultipartFile uploader) {

		List<Profile> profiles = readFile(uploader);
		return rate(profiles);

	}

	private List<Profile> rate(List<Profile> profiles) {
		try {

			ObjectMapper mapper = new ObjectMapper();
			InputStream is = LinkedInParameters.class.getResourceAsStream("/config.json");

			LinkedInParameters configParameters = mapper.readValue(is, LinkedInParameters.class);

			profiles.forEach(profile -> {

				double rating = 0;
				DataParameters country = configParameters.getCountries().stream()
						.filter(x -> x.getName().toLowerCase().equals(profile.getCountry().toLowerCase())).findAny()
						.orElse(null);

				rating = country != null ? rating + country.getValue() : rating;

				DataParameters industry = configParameters.getIndustries().stream()
						.filter(x -> x.getName().toLowerCase().equals(profile.getIndustry().toLowerCase())).findAny()
						.orElse(null);

				rating = industry != null ? rating + industry.getValue() : rating;

				DataParameters role = configParameters.getRoles().stream()
						.filter(x -> x.getName().toLowerCase().equals(profile.getCurrentRole().toLowerCase())).findAny()
						.orElse(null);

				rating = role != null ? rating + role.getValue() : rating;

				profile.setRating(rating);

			});

			profiles.sort(Comparator.comparingDouble(Profile::getRating).reversed());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profiles.subList(0, profiles.size() > 100? 100 : profiles.size());
	}

	private List<Profile> readFile(MultipartFile uploader) {
		
		List<Profile> profiles = new ArrayList<Profile>();

		try {
			InputStream is = uploader.getInputStream();
			BufferedReader  br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {		     
				if (!StringUtils.isEmpty(line)) {

					String[] data = line.split("\\|");
					if (data.length >= FIELDS) {
						Profile profile = new Profile();
						profile.setPersonId(Long.parseLong(data[0]));
						profile.setName(!StringUtils.isEmpty(data[1]) ? data[1] : "");
						profile.setLastName(!StringUtils.isEmpty(data[2]) ? data[2] : "");
						profile.setCurrentRole(!StringUtils.isEmpty(data[3]) ? data[3] : "");
						profile.setCountry(!StringUtils.isEmpty(data[4]) ? data[4] : "");
						profile.setIndustry(!StringUtils.isEmpty(data[5]) ? data[5] : "");
						profile.setNumberOfRecommendations(
								!StringUtils.isEmpty(data[6]) ? Integer.parseInt(data[6]) : 0);
						profile.setNumberOfConnections(!StringUtils.isEmpty(data[7]) ? Integer.parseInt(data[7]) : 0);

						profiles.add(profile);
					}

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profiles;
	}

}
