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

import com.madgeargames.ninjatrials.game.GameManager;
import com.madgeargames.ninjatrials.game.records.Record;
import com.madgeargames.ninjatrials.game.records.RecordsTable;

public class AchievementSetNinjaTrial extends AchievementSet implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int ACHIEV_NUM = 56;

    // ACHIEV. PROGRESSION VALUES:
    private static final int ACHIEV_01_TOTAL_TRIALS = GameManager.ACHIEV_TEST_MODE ? 1 : 8;
    private static final int ACHIEV_02_TOTAL_METERS = GameManager.ACHIEV_TEST_MODE ? 1500 : 10000;
    private static final int ACHIEV_05_TOTAL_JUMPS = GameManager.ACHIEV_TEST_MODE ? 3 : 10;
    private static final int ACHIEV_29_TOTAL_SECONDS = GameManager.ACHIEV_TEST_MODE ? 120 : 36000;

    // ACHIEV. DATA:
    public static final int ACHIEV_04_CUT_PRECISSION = GameManager.ACHIEV_TEST_MODE ? 80 : 100;


    public AchievementSetNinjaTrial() {
        super(ACHIEV_NUM);
        init();
    }


    /**
     * Initializes the Ninja Trials achiev. set.
     */
    public void init() {
        // Default setup
        for(int i = 0; i < ACHIEV_NUM; i++) {
            achievements[i] = new Achievement("Nombre"+(i+1), "Descrp"+(i+1), "Exito"+(i+1));
        }

        // ACHIEV 01: Ninja Trials
        // Completar con éxito al menos una vez los 8 tipos de trials que hay en el juego
        achievements[0] = new Achievement(
                "Ninja Trials",
                "Completar con éxito al menos una vez los 8 tipos de trials que hay en el juego",
                "Completado!",
                0, ACHIEV_01_TOTAL_TRIALS);
        
        // ACHIEV 02: ¡Corre, ninja! ¡Corre!
        // Correr un total de x metros (en total, no en una prueba)
        achievements[1] = new Achievement(
                "¡Corre, ninja! ¡Corre!",
                "Correr un total de x metros (en total, no en una prueba).",
                "¡Completado!",
                0, ACHIEV_02_TOTAL_METERS);
        
        // ACHIEV 03: Más rápido que el rayo
        // Correr a más de <x>km/h
        achievements[2] = new Achievement(
        		"Más rápido que el rayo",
        		"Correr a más de <x>km/h.",
        		"¡Completado!");
        
        // ACHIEV 05: <nombre del logro>
        // Dar x saltos perfectos (en total, no en una prueba).
        achievements[4] = new Achievement(
                "<nombre del logro>",
                "Dar x saltos perfectos (en total, no en una prueba).",
                "¡Completado!",
                0, ACHIEV_05_TOTAL_JUMPS);

        // ACHIEV 06: Acrofobia
        // No sobrepasar los xx metros en una prueba de salto
        achievements[5] = new Achievement(
                "Acrofobia",
                "No sobrepasar los xx metros en una prueba de salto.",
                "¡Completado!",
                "<clue tittle>", "<clue description>");

        // ACHIEV 08: Cut 01
        // Cortar con precisión del 100%
        achievements[7] = new Achievement(
                "Cut 01",
                "Cortar con precisión del xx%",
                "¡Completado!");
        
        // ACHIEV 26: Persistente
        // Gastar todas las continuaciones (jugando en normal).
        achievements[25] = new Achievement(
                "Persistente",
                "Gastar todas las continuaciones (jugando en normal).",
                "¡Completado!");
        
        // ACHIEV 27: Equipo Ninja
        // Completar el juego en modo 2 jugadores en nivel normal o superior (hay continues).
        achievements[26] = new Achievement(
                "Equipo Ninja",
                "Completar el juego en modo 2 jugadores en nivel normal o superior.",
                "¡Completado!");

        // ACHIEV 28: All your record are belong to us
        // Reemplazar todos los récords por defecto
        int recordsTotalCount = 0;
        for(RecordsTable rt: GameManager.records.tables) {
        	for(@SuppressWarnings("unused") Record r: rt.records) {
        		recordsTotalCount++;
        	}
        }
        achievements[27] = new Achievement(
                "All your record are belong to us",
                "Poseer todos los records",
                "¡Completado!",
                0, recordsTotalCount);
        
        // ACHIEV 29: Definitivamente me gusta este juego.
        // Jugar durante x horas
        achievements[28] = new Achievement(
                "Definitivamente me gusta este juego.",
                "Jugar durante x horas.",
                "¡Completado!",
                0, ACHIEV_29_TOTAL_SECONDS);
        
        // ACHIEV 30: Soy el mejor en lo que hago.
        // Reemplazar todos los récords por defecto
        achievements[29] = new Achievement(
                "Soy el mejor en lo que hago.",
                "Completar algún trial con puntuación perfecta",
                "¡Completado!");
        
        // ACHIEV 32: Fácil
        // Completar el juego en nivel fácil
        achievements[31] = new Achievement(
                "Fácil",
                "Completar el juego en nivel fácil",
                "¡Completado!");
        
        // ACHIEV 33: Normal
        // Completar el juego en nivel normal
        achievements[32] = new Achievement(
                "Normal",
                "Completar el juego en nivel normal",
                "¡Completado!");
        
        
        /* === EXAMPLES ===
        
        basic:
        achievements[0] = new Achievement("Basic", "Descripion basic", "Success basic!!!");

        basic success:
        achievements[1] = new Achievement("Basic", "Descripion basic", "Success basic!!!");
        achievements[1].completed = true;

        Progressive:
        achievements[2] = new Achievement("Progressive", "Description progressive",
                "Success progresive!!!", 0, 100);

        Progressive success:
        achievements[3] = new Achievement("Progressive", "Description progressive",
                "Success progresive!!!", 0, 100);
        achievements[3].completed = true;

        Secret:
        achievements[4] = new Achievement("Secret", "Description Secret", "Sucess secret!!!",
                "Clue name", "Clue description");

        Secret success:
        achievements[5] = new Achievement("Secret", "Description Secret", "Sucess secret!!!",
                "Clue name", "Clue description");
        achievements[5].completed = true;

        Progressive & Secret:
        achievements[6] = new Achievement("Prog and Sec", "Description progress and secret",
                "Sucess prog and secret!!!", 0, 100,
                "Clue name prog and sec", "Clue description prog and sec");

        Progressive & Secret sucess:
        achievements[7] = new Achievement("Prog and Sec", "Description progress and secret",
                "Sucess prog and secret!!!", 0, 100,
                "Clue name prog and sec", "Clue description prog and sec");
        achievements[7].completed = true;
        
        === END EXAMPLES === */
    }
}
