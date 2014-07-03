package com.naltel.recommendation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.*;

public class RecommendationApp {

	public static void main(String[] args) throws IOException, TasteException {
		DataModel  dataModel = new FileDataModel(new File("/home/vinaykk/machinelearning/data/intro.csv"));
		UserSimilarity userSimilarity =  new PearsonCorrelationSimilarity(dataModel);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, userSimilarity, dataModel);
		Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, userSimilarity);
		List<RecommendedItem> recommendedItems = recommender.recommend(1, 1);
		
		for(RecommendedItem recommendedItem: recommendedItems) {
			System.out.println(recommendedItem);
		}
	}
}
