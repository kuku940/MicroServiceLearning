package cn.xiaoyu.hystrix.service;

import cn.xiaoyu.hystrix.domain.Horse;
import cn.xiaoyu.hystrix.domain.RaceCourse;

import java.util.List;


/**
 * Simulates the interface for a remote betting service.
 */
public interface BettingService {

    /**
     * Get a list the names of all Race courses with races on today.
     *
     * @return List of race course names
     */
    List<RaceCourse> getTodaysRaces();

    /**
     * Get a list of all Horses running in a particular race.
     *
     * @param raceCourseId Name of race course
     * @return List of the names of all horses running in the specified race
     */
    List<Horse> getHorsesInRace(String raceCourseId);

    /**
     * Get current odds for a particular horse in a specific race today.
     *
     * @param raceCourseId  Name of race course
     * @param horseId Name of horse
     * @return Current odds as a string (e.g. "10/1")
     */
    String getOddsForHorse(String raceCourseId, String horseId);
}
