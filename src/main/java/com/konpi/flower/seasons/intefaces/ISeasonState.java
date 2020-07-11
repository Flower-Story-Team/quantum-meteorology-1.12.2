/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package com.konpi.flower.seasons.intefaces;


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
    int getSubSeasonDuration();

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
    int getSeasonCycleTicks();

    /**
     *
     *
     * @return The current day
     */
    int getDay();



}