package ApacheMain.outputwrappers;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;

import atfImplementation.Location;

@XmlRootElement(name = "PRILocationList")
public class LocationList extends AbstractCollection<Location> implements Collection<Location> {
	Collection<Location> LocationList = null;
	
	LocationList() {
		LocationList = new ArrayList<Location>();
	}
	
	LocationList(ArrayList<Location> l) {
		LocationList = l;
	}
	
	@Override
	public Iterator<Location> iterator() {
		return LocationList.iterator();
	}

	@Override
	public int size() {
		return LocationList.size();
	}

	
	
}
