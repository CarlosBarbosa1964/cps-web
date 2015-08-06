package br.com.ibtechnology.cpsweb.model.business;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.enums.KeywordLog;
import br.com.ibtechnology.cpsweb.model.enums.LevelLog;

public interface ILogController {
	
	public boolean salvarEvtUser(UserEntity user, LevelLog level, KeywordLog message);
	
}
