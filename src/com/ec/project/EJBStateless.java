package com.ec.project;

import java.util.ArrayList;

import javax.ejb.Stateless;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Session Bean implementation class EJBStateless
 */
@Stateless
public class EJBStateless implements EJBStatelessLocal, EJBStatelessRemote {

	public EJBStateless() {

	}

	@Override
	public String predict(int age, int sex, int chestPain, int trestbps, int cholestrol, int fbs, int restecg,
			int thalach, int exang, double oldpeak, int slope, int ca, int thal) throws Exception {
		// predict
		String modelfilename = "heart_model.bin";
		Instance predicationDataInstance = getInstance(age, sex, chestPain, trestbps, cholestrol, fbs, restecg, thalach,
				exang, oldpeak, slope, ca, thal);

		Classifier cls1 = (Classifier) weka.core.SerializationHelper.read("C:/tmp/enterprise/model/" + modelfilename);
		double value = cls1.classifyInstance(predicationDataInstance);

		return Double.toString(value);
	}

	public Instance getInstance(int age, int sex, int chestPain, int trestbps, int cholestrol, int fbs, int restecg,
			int thalach, int exang, double oldpeak, int slope, int ca, int thal) {
		ArrayList<Attribute> atts = new ArrayList<Attribute>(14);

		ArrayList<String> classVal = new ArrayList<String>();
		classVal.add("0");
		classVal.add("1");

		atts.add(new Attribute("age", age));
		atts.add(new Attribute("sex", sex));
		atts.add(new Attribute("chestPain", chestPain));
		atts.add(new Attribute("trestbps", trestbps));
		atts.add(new Attribute("cholestrol", cholestrol));
		atts.add(new Attribute("fbs", fbs));
		atts.add(new Attribute("restecg", restecg));
		atts.add(new Attribute("thalach", thalach));
		atts.add(new Attribute("exang", exang));
		atts.add(new Attribute("oldpeak", (int) oldpeak));
		atts.add(new Attribute("slope", slope));
		atts.add(new Attribute("ca", ca));
		atts.add(new Attribute("thal", thal));

		atts.add(new Attribute("@@class@@", classVal));

		Instances dataRaw = new Instances("heart", atts, 14);

		double[] instanceValue1 = new double[dataRaw.numAttributes()];

		instanceValue1[0] = age;
		instanceValue1[1] = sex;
		instanceValue1[2] = chestPain;
		instanceValue1[3] = trestbps;
		instanceValue1[4] = cholestrol;
		instanceValue1[5] = fbs;
		instanceValue1[6] = restecg;
		instanceValue1[7] = thalach;
		instanceValue1[8] = exang;
		instanceValue1[9] = oldpeak;
		instanceValue1[10] = slope;
		instanceValue1[11] = ca;
		instanceValue1[12] = thal;

		dataRaw.add(new DenseInstance(1.0, instanceValue1));

		dataRaw.setClassIndex(dataRaw.numAttributes() - 1);
		System.out.println("After adding a instance");
		System.out.println("--------------------------");
		// System.out.println(dataRaw);
		System.out.println("--------------------------");

		return dataRaw.firstInstance();

	}
}
