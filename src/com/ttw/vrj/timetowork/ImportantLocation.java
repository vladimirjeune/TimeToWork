/**
 * 
 */
package com.ttw.vrj.timetowork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vladimirjeune
 *
 */
public class ImportantLocation extends Location {
	
	private String mCategory;
	private ArrayList<CharSequence> mCategories;
	private String[] _categories = {
			"",
			"Work", 
			"School", 
			"Grocery",
			"Restaurant",
			"Gym", 
			"Park",
			"Other"
	};
	
	/**
	 * CONSTRUCTOR
	 */
	public ImportantLocation() {
		mCategory = "";
		mCategories = new ArrayList<CharSequence>(Arrays.asList(_categories));
	}
	
	/**
	 * GETCATEGORIES - returns a copy of the ArrayList of the categories that 
	 * 		describe this object. 
	 */
	@Override
	public List<CharSequence> getCategories() {
		return new ArrayList<CharSequence>(mCategories);
	}

	/**
	 * ADDCATEGORY - will add a category to the list of categories that 
	 *   	describe this object.  Only unique entries will be admitted.
	 * @param aCategory
	 */
	@Override
	public void addCategory(String aCategory) {
		
		if (false == mCategories.contains(aCategory)) {
			mCategories.add(aCategory);
		}
		
	}
	
	/**
	 * @return the User chosen category for this object
	 */
	public String getCategory() {
		return mCategory;
	}

	/**
	 * @param category the category to set this object to
	 */
	public void setCategory(String category) {
		mCategory = category;
	}
	
	/**
	 * TOSTRING - 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " [mCategories=" + mCategories + ""
				+ "\", Category=\"" + mCategory + "\"]";
	}
	
	/**
	 * CLEAR - will clear user entered values from this location.
	 */
	@Override
	public void clear() {
		super.clear();
		mCategory = "";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
