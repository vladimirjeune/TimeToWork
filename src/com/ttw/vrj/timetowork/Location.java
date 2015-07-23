/**
 * 
 */
package com.ttw.vrj.timetowork;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * LOCATION - Holds common code for ImportantLocation and PotentialResidence
 * @author vladimirjeune
 *
 */
public abstract class Location implements Serializable {

	private final UUID mId;

	private boolean mIsImportant;  // Will default to false
	private String mName="";
	private String mStreet="";
	private String mUnit="";
	private String mCity="";
	private String mState="";
	private String mZipcode="";
	
	private long mDate;
	
	/**
	 * CONSTRUCTOR - 
	 */
	public Location() {
		mId = UUID.randomUUID();
		mDate = new Date().getTime();
		setIsImportant(false);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": [UID=\"" + mId 
				+ "\", isStarred=\"" + mIsImportant 
				+ "\", Name=\"" + mName 
				+ "\",\nStreet=\"" + mStreet 
				+ "\", Unit=\"" + mUnit 
				+ "\", City=\"" + mCity 
				+ "\",\nState=\"" + mState
				+ "\", Zipcode=\"" + mZipcode
				+ "\", Date=\"" + mDate + "\"]" ;
	}

	/**
	 * CLEAR - will clear user entered values from this location.
	 */
	public void clear() {
		mIsImportant = false;
		mName = "";
		mStreet = "";
		mUnit = "";
		mCity = "";
		mState = "";
		mZipcode = "";
		mDate = 0L;
			
	}
	
	
	/**
	 * GETCATEGORIES - returns an ArrayList of the categories that describe this object 
	 */
	public abstract List<CharSequence> getCategories();
	
	/**
	 * ADDCATEGORY - will add a category to the list of categories that describe this object
	 * @param aCategory
	 */
	public abstract void addCategory(String aCategory);
	
	
	
	// TODO: Will have abstract() named Category.  Work(Work,Gym..), Home(Rental, Condo..)
	
	/**
	 * @return the User chosen category for this object
	 */
	public abstract String getCategory();

	/**
	 * @param category the category to set this object to
	 */
	public abstract void setCategory(String category);

	/**
	 * @return the date as milliseconds.
	 */
	public long getDate() {
		return mDate;
	}

	/**
	 * GETLABEL - will return the label
	 * @return the Label
	 */
	public String getName() {
		return mName;
	}



	/**
	 * SETLABEL - sets the label for the Location
	 * @param Label the Label to set
	 */
	public void setName(String aName) {
		mName = aName;
	}



	/**
	 * GETSTREET - will return the street information for the Location
	 * @return the Street
	 */
	public String getStreet() {
		return mStreet;
	}



	/**
	 * SETSTREET - will set the street for this Location
	 * @param mStreet the mStreet to set
	 */
	public void setStreet(String aStreet) {
		mStreet = aStreet;
	}



	/**
	 * GETUNIT - will return the Unit for this Location
	 * @return the Unit
	 */
	public String getUnit() {
		return mUnit;
	}



	/**
	 * SETUNIT - will set the Unit for this Location
	 * @param Unit the Unit to set
	 */
	public void setUnit(String aUnit) {
		mUnit = aUnit;
	}



	/**
	 * GETID - will return the Unique ID of this Location
	 * @return the Id
	 */
	public UUID getId() {
		return mId;
	}



	public boolean isImportant() {
		return mIsImportant;
	}

	public void setIsImportant(boolean isImportant) {
		mIsImportant = isImportant;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return mCity;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String aCity) {
		mCity = aCity;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return mState;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String aState) {
		mState = aState;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return mZipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String aZipcode) {
		mZipcode = aZipcode;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
