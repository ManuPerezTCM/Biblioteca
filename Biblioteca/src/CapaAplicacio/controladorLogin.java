package CapaAplicacio;

import Domini.Biblioteca;
import Persistencia.BBDDBiblioteca;
import Persistencia.BBDDBibliotecari;
import Persistencia.ConnexioJPA;

public class controladorLogin {

	private ConnexioJPA connexio;
	private BBDDBibliotecari bbddBibliotecari;
	private Biblioteca biblioteca;
	private BBDDBiblioteca bibliotecaBBDD;
	
	public controladorLogin() throws Exception{
		bbddBibliotecari = new BBDDBibliotecari();
		bibliotecaBBDD = new BBDDBiblioteca();
		try{
		//Crear una connexio amb el nostre usuari i contrasenya
		ConnexioJPA.getInstancia("G5GEILAB2","G5P15");
		biblioteca = bibliotecaBBDD.find();
		} catch (Exception e){
			throw new Exception("Error al fer get Instancaia: "+e.getMessage());
		}
		this.connexio = connexio.getInstancia();
	}
	
	public void comprovar(String nom, String contrasenya) throws Exception{
		bbddBibliotecari.find(nom.toUpperCase(), contrasenya);
	}
	
	public void logarse(String usuari, String password) throws Exception {
		ConnexioJPA.getInstancia(usuari.toUpperCase(), password);
	}
	
	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}
}