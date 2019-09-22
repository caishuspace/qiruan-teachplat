package team.qiruan.service;

import java.util.List;

import team.qiruan.jobseek.dao.Jobseek_less;

/**
 * SimpleSearch
 */
public interface SimpleSearchService {

    List<Jobseek_less> searchJobSeek(String word);

}