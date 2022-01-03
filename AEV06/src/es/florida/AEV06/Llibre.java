package es.florida.AEV06;

public class Llibre {
	private String identificador;
	private String titol;
	private String autor;
	private String any_naixement;
	private String any_publicacio;
	private String editorial;
	private String num_pags;

	
	Llibre() {
		
	}
	
	//	Métode: Llibre
	//	Descripció: creem els atributs d'un llibre
	//	Parametres d'entrada: titol, autor, any_naixement, any_publicacio, editorial, num_pags
	//	Parametres d'eixida: ningún
	Llibre (String identificador, String titol, String autor, String any_naixement ,String any_publicacio, String editorial, String num_pags){
		this.identificador = identificador;
		this.titol = titol;
		this.autor = autor;
		this.any_naixement = any_naixement;
		this.any_publicacio = any_publicacio;
		this.editorial = editorial;
		this.num_pags = num_pags;
	}

	//	Métode: getIdentificador
	//	Descripció: creem el getter
	//	Parametres d'entrada: ningún
	//	Parametres d'eixida: identificador
	public String getIdentificador() {
		return identificador;
	}

	//	Métode: setIdentificador
	//	Descripció: creem el setter
	//	Parametres d'entrada: identificador
	//	Parametres d'eixida: ningún
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	//	Métode: getTitol
	//	Descripció: creem el getter
	//	Parametres d'entrada: ningún
	//	Parametres d'eixida: titol
	public String getTitol() {
		return titol;
	}

	//	Métode: setTitol
	//	Descripció: creem el setter
	//	Parametres d'entrada: titol
	//	Parametres d'eixida: ningún
	public void setTitol(String titol) {
		this.titol = titol;
	}

	//	Métode: getAutor
	//	Descripció: creem el getter
	//	Parametres d'entrada: ningún
	//	Parametres d'eixida: autor
	public String getAutor() {
		return autor;
	}

	//	Métode: setAutor
	//	Descripció: creem el setter
	//	Parametres d'entrada: autor
	//	Parametres d'eixida: ningún
	public void setAutor(String autor) {
		this.autor = autor;
	}

	//	Métode: getAny_publicacio
	//	Descripció: creem el getter
	//	Parametres d'entrada: ningún
	//	Parametres d'eixida: any_naixement
	public String getAny_naixement() {
		return any_naixement;
	}

	//	Métode: setAny_publicacio
	//	Descripció: creem el setter
	//	Parametres d'entrada: any_naixement
	//	Parametres d'eixida: ningún
	public void setAny_naixement(String any_naixement) {
		this.any_naixement = any_naixement;
	}

	
	//	Métode: getAny_publicacio
	//	Descripció: creem el getter
	//	Parametres d'entrada: ningún
	//	Parametres d'eixida: any_publicacio
	public String getAny_publicacio() {
		return any_publicacio;
	}

	//	Métode: setAny_publicacio
	//	Descripció: creem el setter
	//	Parametres d'entrada: any_publicacio
	//	Parametres d'eixida: ningún
	public void setAny_publicacio(String any_publicacio) {
		this.any_publicacio = any_publicacio;
	}

	//	Métode: getEditoral
	//	Descripció: creem el getter
	//	Parametres d'entrada: ningún
	//	Parametres d'eixida: editoral
	public String getEditorial() {
		return editorial;
	}

	//	Métode: setEditoral
	//	Descripció: creem el setter
	//	Parametres d'entrada: editoral
	//	Parametres d'eixida: ningún
	public void setEditorial(String editoral) {
		this.editorial = editoral;
	}

	//	Métode: getPagines
	//	Descripció: creem el getter
	//	Parametres d'entrada: ningún
	//	Parametres d'eixida: num_pags
	public String getNum_pags() {
		return num_pags;
	}

	//	Métode: setPagines
	//	Descripció: creem el setter
	//	Parametres d'entrada: num_pags
	//	Parametres d'eixida: ningún
	public void setNum_pags(String num_pags) {
		this.num_pags = num_pags;
	}
}
