package com.madgeargames.ninjatrials.game.records;

import java.io.Serializable;

import com.madgeargames.ninjatrials.game.ETrial;
import com.madgeargames.ninjatrials.util.Constants;

public class RecordsTableSet implements Serializable {

    public RecordsTable[] tables;

    private static final int SIZE = Constants.RECORDS_TABLE_SIZE;

    //  Serial version:
    private static final long serialVersionUID = 1L;


    public RecordsTableSet() {
        // nº de tablas = nº de pruebas + story mode
        tables = new RecordsTable[ETrial.values().length + 1];

        // Cada tabla tiene el nombre de su trial:
        for(int i = 0; i < tables.length - 1; i++)
            tables[i] = new RecordsTable(ETrial.values()[i].toString() + " TRIAL", SIZE);

        // La ultima es la modo story:
        tables[tables.length - 1] = new RecordsTable("STORY MODE", SIZE);
    }


    // Devuelve la tabla de records correspondiente a la trial indicada:
    public RecordsTable getTable(ETrial trial) {
        return tables[trial.ordinal()];
    }


    // Devuelve la tabla de records de modo historia:
    public RecordsTable getStoryModeTable() {
        return tables[tables.length - 1];
    }

}
