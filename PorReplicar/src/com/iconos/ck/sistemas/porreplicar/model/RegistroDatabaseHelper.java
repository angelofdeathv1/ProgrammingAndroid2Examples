package com.iconos.ck.sistemas.porreplicar.model;

import android.content.Context;

import com.turbomanage.storm.DatabaseHelper;
import com.turbomanage.storm.api.Database;
import com.turbomanage.storm.api.DatabaseFactory;



@Database(name = "registrosDB", version = 1)
public class RegistroDatabaseHelper extends DatabaseHelper {

	public RegistroDatabaseHelper(Context ctx, DatabaseFactory dbFactory) {
		super(ctx, dbFactory);
	}

	@Override
	public UpgradeStrategy getUpgradeStrategy() {
		return UpgradeStrategy.DROP_CREATE;
	}

}
