
package com.konpi.flower.seasons.intefaces;


import com.konpi.flower.seasons.Season;

public interface ISeasonState
{
    /**
     * 一天的tick数.
     * 正常为24000tick
     *
     * @return The duration in ticks
     */
    int getDayDuration();

    /**
     * 一个季节的tick.
     *
     * @return The duration in ticks
     */
    int getSeasonDuration();

    /**
     *一年的tick
     *
     * @return The duration in ticks
     */
    int getCycleDuration();

    /**
     *
     * 一年的周期会重新有春夏秋冬
     * 333kopnpi
     *
     * @return The time in ticks
     */

    int getDay();

    /*
    *
    *
     */

    Season getSeason();


}