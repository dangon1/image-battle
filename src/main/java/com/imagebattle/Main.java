package com.imagebattle;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class Main {

	@Inject
	private ImageBattle imageBattleGUI;
    
    public static void main(String[] args) {
        
        Main main = new Main();
        
        Injector injector = Guice.createInjector();
        injector.injectMembers(main);//injects the implementation of the service
        
        main.start();
    }

    public void start(){
    	
    	imageBattleGUI.start();
    	
    }
}
