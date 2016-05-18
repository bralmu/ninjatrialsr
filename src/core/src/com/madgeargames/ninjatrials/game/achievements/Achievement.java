/*
 * Ninja Trials is an old school style Android Game developed for OUYA & using
 * AndEngine. It features several minigames with simple gameplay.
 * Copyright 2013 Mad Gear Games <madgeargames@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.madgeargames.ninjatrials.game.achievements;

import java.io.Serializable;

import com.madgeargames.ninjatrials.util.UserData;

public class Achievement implements Serializable {

    public String name = "Achiev. Name";
    public String description = "Achiev. Description";
    public String successSentence = "Success!";

    public boolean isProgressive = false;
    public int progress = 0;
    public int progressTotal = 100;

    public boolean isSecret = false;
    public String clueTittle = "???";
    public String clueDescription = "???";

    public boolean completed = false;

    /**
     * Serial version:
     */
    private static final long serialVersionUID = 1L;


    // CONSTRUCTORS ---------------------------

    /**
     * Standard achievement.
     * @param name
     * @param description
     * @param successSentence
     */
    public Achievement(String name, String description, String successSentence) {
        this.name = name;
        this.description = description;
        this.successSentence = successSentence;
    }

    /**
     * Progressive achiev.
     * @param name
     * @param description
     * @param successSentence
     * @param progress
     * @param progressTotal
     */
    public Achievement(
            String name, String description, String successSentence,
            int progress, int progressTotal) {
        this.name = name;
        this.description = description;
        this.successSentence = successSentence;
        this.isProgressive = true;
        this.progress = progress;
        this.progressTotal = progressTotal;
    }

    /**
     * Secret achiev.
     * @param name
     * @param description
     * @param successSentence
     * @param clueTittle
     * @param clueDescription
     */
    public Achievement(
            String name, String description, String successSentence,
            String clueTittle, String clueDescription) {
        this.name = name;
        this.description = description;
        this.successSentence = successSentence;
        this.isSecret = true;
        this.clueTittle = clueTittle;
        this.clueDescription = clueDescription;
    }

    /**
     * Progresive & secret achiev.
     * @param name
     * @param description
     * @param successSentence
     * @param progress
     * @param progressTotal
     * @param clueTittle
     * @param clueDescription
     */
    public Achievement(
            String name, String description, String successSentence,
            int progress, int progressTotal,
            String clueTittle, String clueDescription) {
        this.name = name;
        this.description = description;
        this.successSentence = successSentence;
        this.isProgressive = true;
        this.progress = progress;
        this.progressTotal = progressTotal;
        this.isSecret = true;
        this.clueTittle = clueTittle;
        this.clueDescription = clueDescription;
    }


    // METHODS -----------------

    /**
     * Increments the progress of an achiev.
     * @param i amount to increment.
     */
    public void progressIncrement(int i) {
        if(isProgressive) {
            progress = progress + i;
            if (progress >= progressTotal) {
                progress = progressTotal;
                completed = true;
            }
        }
    }


    public boolean isCompleted() {
        return completed;
    }

    /**
     * Show a clue if is secret achiev and not completed, else show the name.
     * @return the name.
     */
    public String showName() {
        if(isSecret && !completed) return clueTittle;
        else return name;
    }


    /**
     * Show a clue if is secret achiev and not completed, else show the description.
     * @return the description.
     */
    public String showDescription() {
        if(isSecret && !completed) return clueDescription;
        else return description;
    }


    public void unlock() {
        completed = true;
        //UserData.saveAchiev(ResourceManager.getInstance().context);
        UserData.saveAchievements();
    }
}
