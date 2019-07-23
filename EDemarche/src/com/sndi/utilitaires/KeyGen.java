package com.sndi.utilitaires;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sndi.service.Iservice;

@Component
public class KeyGen {
	@Autowired
	Iservice iservice;
	
	
	public String getFonctionID(String codeTypeFonc) {
		
		String pseudo = "FON-"+codeTypeFonc;//7 il reste 3 total 10
		String IdFonc = iservice.getCodeTable(pseudo, new Integer(GRFProperties.FON_COD_SIZE_PSEUDO), new Integer(GRFProperties.FON_COD_SIZE),
				"t_fonction", "fon_cod");
		System.out.println("****Id de la Fonction =" + IdFonc);
		return IdFonc;
	}
	
	public String getDemandeCode(String type, String exoCode) {
		//String exo=exoCode.substring(2);
		String pseudo = type+exoCode;//5 reste 4
		String code = iservice.getCodeTable(pseudo, 5, 9,
				"demande", "dem_code");
		System.out.println("****Code de la demande =" + code);
		return code;
	}
	


	
public String getTypeID(String pseudo, int taillePseudo, int taille, String table, String id) {
		
		String IdDoss = iservice.getCodeTable(pseudo, taillePseudo, taille,
				table, id);
		System.out.println("****Id du Type  =" + IdDoss);
		return IdDoss;
	}




/* c'est ici que le code de la demande est generé
 chaine est la partie chaine de caractere du code, dans mon cas c'est PCAMU-
 exo est le cote année + le systeme de comptage
 15 signifie on compte 10 chiffre des code demande de la gauche a la la doite
  au dela des 10 il commence a auto incrementer 
  20 signifie que le code aura une longueur de 15 caractere
  
 */

static public String dateToStringWithFormatAndTrim(Date dateInString, String customFormat) {
	
	SimpleDateFormat formatter = new SimpleDateFormat(customFormat);
	if (dateInString != null)
		return formatter.format(dateInString).trim().replaceAll("[^0-9.]", "");
	else
		return "";
}
static public String dateToStringWithFormat(Date dateInString, String customFormat) {
	
	SimpleDateFormat formatter = new SimpleDateFormat(customFormat);
	if (dateInString != null)
		return formatter.format(dateInString);
	else
		return "";
}




public String getOperateurCode() {
	
	String pseudo = "OPE-";//4 il reste 5 total 9
	String code = iservice.getCodeTable(pseudo, 4, 9,
			"T_OPERATEUR", "OPE_MATRICULE");
	System.out.println("****code T_OPERATEUR =" + code);
	return code;
}

public String getfonctionCode(String typefon) {
	String pseudo ="";
	int sizeTF = sizeOfString(typefon);
	int c = 0;
	int p = 0;
	if(sizeTF == 1) {pseudo = "FON-"+typefon; c = 5; p = 8;}
	else if(sizeTF == 1){pseudo = "FON-"+typefon; c = 6; p = 9;}else {pseudo = "FON-"+typefon; c = 7; p = 10;}
	String code = iservice.getCodeTable(pseudo, c, p,
			"T_FONCTION", "FON_COD");
	System.out.println("****code T_FONCTION =" + code);
	return code;
}
public int sizeOfString(String chaine) {
	int val = chaine.toCharArray().length;
	return val;
}

public String getNextOrdreMission(String num, int key) {

	BigDecimal	bv = new BigDecimal(num);
	String numOrd = "";
	String tC = String.valueOf(4);
		Integer v = bv.intValue();
		v = v +key;
		 numOrd= String.format("%0"+tC+"d", v);
		System.out.println("///////Verification requette numrd ="+numOrd);
	
	return numOrd;
}

	
}
