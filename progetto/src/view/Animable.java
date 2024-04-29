package view;

import controller.Controller;

public interface Animable {
	//default operations to do intra-class when component is updated
	public void update(float deltaTime);

	//default operations to do when this Animable Object need to add a updater
	public void addUpdater(Controller c);
	
	//default operations to do when this Animable Object need to remove a updater
	public void removeUpdater(Controller c);
}
