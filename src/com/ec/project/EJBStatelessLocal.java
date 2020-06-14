package com.ec.project;

import javax.ejb.Local;

@Local
public interface EJBStatelessLocal {
	public String predict(int age, int sex, int chestPain, int trestbps, int cholestrol, int fbs, int restecg,
			int thalach, int exang, double oldpeak, int slope, int ca, int thal) throws Exception;
}
