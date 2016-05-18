package com.madgeargames.ninjatrials.game;

public enum ETrial {
	RUN, CUT, JUMP, SHURIKEN;

	public ETrial next() {
		ETrial n = ETrial.values()[0];

		// Si es la última prueba devuelve la primera (0), si no la siguiente.
		if (this.ordinal() < ETrial.values().length - 1)
			n = ETrial.values()[this.ordinal() + 1];

		/*
		 * if(this == RUN) n = CUT; else if(this == CUT) n = JUMP; else if(this
		 * == JUMP) n = SHURIKEN; else if(this == SHURIKEN) n = RUN;
		 */

		return n;
	}

	/**
	 * Devuelve true si es la última prueba. False en otro caso.
	 * 
	 * @return
	 */
	public boolean isLastTrial() {
		return this == ETrial.values()[ETrial.values().length - 1];
	}
}
