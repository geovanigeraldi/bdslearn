package com.geraldi.bdslearn.observers;

import com.geraldi.bdslearn.entities.Deliver;

public interface DeliverRevisionObserver {

	void onSaveRevision(Deliver deliver);
	
}