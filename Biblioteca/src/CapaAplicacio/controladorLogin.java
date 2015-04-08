package CapaAplicacio;

import Persistencia.BBDDBibliotecari;
import Persistencia.ConnexioJPA;

public class controladorLogin {

	private ConnexioJPA connexio;
	private BBDDBibliotecari bbddBibliotecari;
	
	public controladorLogin() throws Exception{
		bbddBibliotecari = new BBDDBibliotecari();
		try{
		ConnexioJPA.getInstancia("G5GEILAB2","G5P15"); //Crear una connexio amb el nostre usuari i contrasenya
		} catch (Exception e){
			throw new Exception("Error al fer get Instancaia: "+e.getMessage());
		}
		this.connexio = connexio.getInstancia();
	}
	
	public void comprovar(String nom, String contrasenya) throws Exception{
		bbddBibliotecari.find(nom, contrasenya);
	}
	
	public void logarse(String usuari, String password) throws Exception {
		ConnexioJPA.getInstancia(usuari, password);
	}
}
