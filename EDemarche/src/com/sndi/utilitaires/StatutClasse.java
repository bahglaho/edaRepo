package com.sndi.utilitaires;

public class StatutClasse {
	
	
	static public String getMinCode(String fonCode) {return fonCode.substring(3, 6);}

	static public String  returnStatutEtape(String action, String foncCode){
		String val= "";
		
		foncCode= getMinCode(foncCode);
		switch(action){
		
		case "RF":
			// -- Action = 'RF': Ecran de Reception. Statut de Filtre
			
			//libelleEtape="Ecran de R�ception";
			
			 	 if(foncCode.equalsIgnoreCase("BUD")||foncCode.equalsIgnoreCase("213")){ val = "M1T";}
			else if(foncCode.equalsIgnoreCase("MFP")){ val = "MFT";} 	 
		    else if(foncCode.equalsIgnoreCase("MEF")){ val = "M2T";}
		    else if(foncCode.equalsIgnoreCase("MAE")||foncCode.equalsIgnoreCase("214")){ val = "M3T";}
		    else if(foncCode.equalsIgnoreCase("SGG")){ val = "M4T";}
		    else if(foncCode.equalsIgnoreCase("PRD")){ val = "M5T";}
		    else if(foncCode.equalsIgnoreCase("CTR")){ val = "M6T";}
		    else if(foncCode.equalsIgnoreCase("SLD")){ val = "M7T";}

			break;
			
		case "RT":
			//-- Action = 'RT': Ecran de Reception. Statut de Traitement
			//libelleEtape="Ecran de R�ception";
			//descripEtape= "des dossiers"
			
		 	 if(foncCode.equalsIgnoreCase("BUD")||foncCode.equalsIgnoreCase("213")){ val = "M2R";}
		 	else if(foncCode.equalsIgnoreCase("MFP")){ val = "MFR";} 	 
			    else if(foncCode.equalsIgnoreCase("MEF")){ val = "M3R";}
			    else if(foncCode.equalsIgnoreCase("MAE")||foncCode.equalsIgnoreCase("214")){ val = "M4R";}
			    else if(foncCode.equalsIgnoreCase("SGG")){ val = "M5R";}
			    else if(foncCode.equalsIgnoreCase("PRD")){ val = "M6R";}
			    else if(foncCode.equalsIgnoreCase("CTR")){ val = "M7R";}
			    else if(foncCode.equalsIgnoreCase("SLD")){ val = "M8R";}

			break;
			
		
		case "TF":
			//-- Action = 'TF': Ecran de Transmission. Statut de Filtre
			
			//libelleEtape="Ecran de Transmission";
			
				 if(foncCode.equalsIgnoreCase("BUD")||foncCode.equalsIgnoreCase("213")){ val = "M2V";}
				 else if(foncCode.equalsIgnoreCase("MFP")){ val = "MFV";} 	 	 
		    else if(foncCode.equalsIgnoreCase("MEF")){ val = "M3V";}
		    else if(foncCode.equalsIgnoreCase("MAE")||foncCode.equalsIgnoreCase("214")){ val = "M4V";}
		    else if(foncCode.equalsIgnoreCase("SGG")){ val = "M5V";}
		    else if(foncCode.equalsIgnoreCase("PRD")){ val = "M6V";}
		    else if(foncCode.equalsIgnoreCase("CTR")){ val = "M7V";}
		    else if(foncCode.equalsIgnoreCase("SLD")){ val = "M8V";}

			break;
			
		case "TT":
			//-- Action = 'TT': Ecran de Transmission. Statut de Traitement
			
			//libelleEtape="Ecran de Transmission";
			
		 	 if(foncCode.equalsIgnoreCase("BUD")||foncCode.equalsIgnoreCase("213")){ val = "M2T";}
		 	else if(foncCode.equalsIgnoreCase("MFP")){ val = "M1T";} 	 
			    else if(foncCode.equalsIgnoreCase("MEF")){ val = "M3T";}
			    else if(foncCode.equalsIgnoreCase("MAE")||foncCode.equalsIgnoreCase("214")){ val = "M4T";}
			    else if(foncCode.equalsIgnoreCase("SGG")){ val = "M5T";}
			    else if(foncCode.equalsIgnoreCase("PRD")){ val = "M6T";}
			    else if(foncCode.equalsIgnoreCase("CTR")){ val = "M7T";}
			    else if(foncCode.equalsIgnoreCase("SLD")){ val = "M8T";}

			break;	
			
		case "VF":
			//-- Action = 'VF': Ecran de Validation. Statut de Filtre
			//libelleEtape="Ecran de Validation";
			
		 	 if(foncCode.equalsIgnoreCase("BUD")||foncCode.equalsIgnoreCase("213")){ val = "M2R";}
		 	 	else if(foncCode.equalsIgnoreCase("MFP")){ val = "MFR";} 
			    else if(foncCode.equalsIgnoreCase("MEF")){ val = "M3R";}
			    else if(foncCode.equalsIgnoreCase("MAE")||foncCode.equalsIgnoreCase("214")){ val = "M4R";}
			    else if(foncCode.equalsIgnoreCase("SGG")){ val = "M5O";}
			    else if(foncCode.equalsIgnoreCase("PRD")){ val = "M6R";}
			    else if(foncCode.equalsIgnoreCase("CTR")){ val = "M7R";}
			    else if(foncCode.equalsIgnoreCase("SLD")){ val = "M8R";}

			break;
			
		case "VV":
			//Action = 'VS': Ecran de Validation. Statut de Traitement Visa
			//libelleEtape="Ecran de Validation";
			
		 	 if(foncCode.equalsIgnoreCase("BUD")||foncCode.equalsIgnoreCase("213")){ val = "M2V";}
		 	 	else if(foncCode.equalsIgnoreCase("MFP")){ val = "MFV";} 
			    else if(foncCode.equalsIgnoreCase("MEF")){ val = "M3V";}
			    else if(foncCode.equalsIgnoreCase("MAE")||foncCode.equalsIgnoreCase("214")){ val = "M4V";}
			    else if(foncCode.equalsIgnoreCase("SGG")){ val = "M5V";}
			    else if(foncCode.equalsIgnoreCase("PRD")){ val = "M6V";}
			    else if(foncCode.equalsIgnoreCase("CTR")){ val = "M7V";}
			    else if(foncCode.equalsIgnoreCase("SLD")){ val = "M8V";}

			break;
			
		case "PPF":
			// Action = 'PPF': Ecran de Projet de Programation. Statut de Filtre
			//libelleEtape="Ecran de projet de programmation";
		 	 if(foncCode.equalsIgnoreCase("SGG")) { val = "M5R";}
			break;
			
		case "PPV":
			// Action = 'PPV': Ecran de Projet de Programation. Statut de Validation
			
		 	 if(foncCode.equalsIgnoreCase("SGG")) { val = "M5P";}

			break;
		case "PF":
			// Action = 'PF': Ecran de Programation. Statut de Filtre
			//libelleEtape="Ecran de programmation";
		 	 if(foncCode.equalsIgnoreCase("SGG")) { val = "M5P";}
			break;	
		
		case "PV":
			// Action = 'PV': Ecran de Programation. Statut de Programmation
			//libelleEtape="Ecran de programmation";
		 	 if(foncCode.equalsIgnoreCase("SGG")) { val = "M5O";}
			break;	
		case "VD":
			// Action = 'VD': Ecran de Validation. Statut de Traitement Diff�r�
			//libelleEtape="Ecran de Validation";
			
		 	 if(foncCode.equalsIgnoreCase("BUD")||foncCode.equalsIgnoreCase("213")) { val = "M2D";}
		 	 	else if(foncCode.equalsIgnoreCase("MFP")){ val = "MFD";} 
			    else if(foncCode.equalsIgnoreCase("MEF")){ val = "M3D";}
			    else if(foncCode.equalsIgnoreCase("MAE")||foncCode.equalsIgnoreCase("214")) { val = "M4D";}
			    else if(foncCode.equalsIgnoreCase("SGG")) { val = "M5D";}
			    else if(foncCode.equalsIgnoreCase("PRD")) { val = "M6D";}
			    else if(foncCode.equalsIgnoreCase("CTR")) { val = "M7D";}
			    else if(foncCode.equalsIgnoreCase("SLD")) { val = "M8D";}

			break;
			
		case "PRF80":
			// Action = 'PV': Ecran de Programation. Statut de Programmation
			//libelleEtape="Ecran de programmation";
		 	 if(foncCode.equalsIgnoreCase("RGI")) { val = "M8T";}
			break;	
		
		case "PRP80":
			// Action = 'PV': Ecran de Programation. Statut de Programmation
			//libelleEtape="Ecran de programmation";
		 	 if(foncCode.equalsIgnoreCase("RGI")) { val = "M8P";}
			break;	
			
		case "F20":
			
			//libelleEtape="Ecran de Liquidation";
		 	 if(foncCode.equalsIgnoreCase("SLD")) { val = "M1R";}
			break;		
			
		case "VA20":
			
			//libelleEtape="Ecran de Liquidation";
		 	 if(foncCode.equalsIgnoreCase("SLD")) { val = "M9V";}
			break;	
			
		case "T20":
			
			//libelleEtape="Ecran de Liquidation";
		 	 if(foncCode.equalsIgnoreCase("SLD")) { val = "M9T";}
			break;	
			
		case "PRF20":
			// Action = 'PV': Ecran de Programation. Statut de Programmation
			//libelleEtape="Ecran de programmation";
		 	 if(foncCode.equalsIgnoreCase("RGI")) { val = "M9T";}
			break;	
		
		case "PRP20":
			// Action = 'PV': Ecran de Programation. Statut de Programmation
			//libelleEtape="Ecran de programmation";
		 	 if(foncCode.equalsIgnoreCase("RGI")) { val = "M9P";}
			break;	
		
					
	default:
		
		}
		
		System.out.println("--Retour action = "+action+"  Fonction code ="+foncCode);
		return val;
		}
	
	
	static public String  returnStatutEtapePreparation(String action, String foncCode){
		String val= "";
		
		foncCode= getMinCode(foncCode);
		switch(action){
		
		
		
		case "VF":
			//-- Action = 'TF': Ecran de Validation. Statut de Filtre
			if(foncCode.equalsIgnoreCase("SLD")){ val = "P1T";}
											else{ val = "P1N";}
			break;
			
		case "VV":
			//Action = 'VS': Ecran de Validation. Statut de Traitement Validation
			if(foncCode.equalsIgnoreCase("SLD")){ val = "P1V";}
											else{ val = "P1T";}
			break;	
			
		case "CV":
			val = "P1V";
			break;	
			
		case "DF":
			//Action = 'DF': Ecran de Validation. Statut  Diff�re Filtre
			if(!foncCode.equalsIgnoreCase("SLD")){ val = "P1D";}
			
			break;	
			
		case "VD":
			//Action = 'VD': Ecran de Validation. Statut de Traitement Diff�re
			if(foncCode.equalsIgnoreCase("SLD")){ val = "P1D";}
			
			break;	
		default:
			
		}
		
		System.out.println("--Retour action = "+action+"  Fonction code ="+foncCode);
		return val;
	}
	
	static public String  returnDelaiString(String min, String max){
		String val="";
		if(max.equalsIgnoreCase("1")) val= min+" jour"; else val= min +" � "+ max+" jours";
		
		return val;
	}
	
	static public String  returnDelai(String statut){
		String val= "";
		
		String redStatut="";
		
		redStatut=statut.substring(1);
		System.out.println(redStatut);
		switch(redStatut){
		
		case "FT":			
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_MFP"),GRFProperties.getProperty("DELAI_MAX_MFP"));
			break;
		case "1T":
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_BUD"),GRFProperties.getProperty("DELAI_MAX_BUD"));
			break;			
		case "2T":			
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_MEF"),GRFProperties.getProperty("DELAI_MAX_MEF"));
			break;						
		case "3T":			
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_MAE"),GRFProperties.getProperty("DELAI_MAX_MAE"));
			break;			
		case "4T":
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_SGG"),GRFProperties.getProperty("DELAI_MAX_SGG"));
			break;				
		case "5T":
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_PRD"),GRFProperties.getProperty("DELAI_MAX_PRD"));		
			break;			
		case "6T":
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_CTR"),GRFProperties.getProperty("DELAI_MAX_CTR"));
			break;			
		case "7T":
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_SLD"),GRFProperties.getProperty("DELAI_MAX_SLD"));
			break;		
		case "8T":
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_RGI"),GRFProperties.getProperty("DELAI_MAX_RGI"));		
			break;
		case "9T":
			val = returnDelaiString(GRFProperties.getProperty("DELAI_MIN_SLD"),GRFProperties.getProperty("DELAI_MAX_SLD"));	
			break;		
		}
		
		//System.out.println("--Retour action = "+action+"  Fonction code ="+foncCode);
		return val;
	}
	
	static public String  returnFoncValidateur(String statut){
		String val= "";
		
	
		switch(statut){
		
		case "M1T":
			
			val = "VALBUD";
			break;
			
		case "M2D":
			
			val = "VALBUD";
			break;
			
		case "M2T":
			
			val = "VALMEF";
			break;
			
		case "M3D":
			
			val = "VALMEF";
			break;
			
		case "M3T":
			
			val = "VALMAE";
			break;
			
		case "M4D":
			
			val = "VALMAE";
			break;
			
		case "M4T":
			val = "VALSGG";

			break;	
		case "M5D":
			val = "VALSGG";
	
			break;	
			
		case "M5T":
			val = "VALPRD";
	
			break;
		case "M6D":
			val = "VALPRD";

			break;
			
		case "M6T":
			val = "VALCTR";
	
			break;
		case "M7D":
			val = "VALCTR";
	
			break;
			
		case "M7T":
			val = "VALSLD";
			break;
			
		case "M8D":
			val = "VALSLD";
			break;
			
		case "M8T":
			val = "VALRGI";

			break;
		case "M9D":
			val = "VALRGI";
			
			break;
			
		case "M9T":
			val = "VALRGI";

			break;
			
		case "M9P":
			val = "PFC";

			break;
			
		case "MFT":	
			val= "VALMFP";
		break;
		
		case "MFD":
			val="PFC";
			break;
		}
		
		System.out.println("--Retour action = "+statut+"  Fonction code LIKE ="+val);
		return val;
	}
	
	
	
	static public String  returnLibelleEtape(String action){
		String val= "";
		switch(action){
		
		case "RF":
			// -- Action = 'RF': Ecran de Reception. Statut de Filtre
			
			val="Ecran de R�ception";

			break;
			
		case "RT":
			//-- Action = 'RT': Ecran de Reception. Statut de Traitement
			val="Ecran de R�ception";
	

			break;
			
		
		case "TF":
			//-- Action = 'TF': Ecran de Transmission. Statut de Filtre
			
			val="Ecran de Transmission";


			break;
			
		case "TT":
			//-- Action = 'TT': Ecran de Transmission. Statut de Traitement
			
			val="Ecran de Transmission";
	

			break;	
			
		case "VF":
			//-- Action = 'VF': Ecran de Validation. Statut de Filtre
			val="Ecran de Validation";


			break;
			
		case "VV":
			//Action = 'VS': Ecran de Validation. Statut de Traitement Visa
			val="Ecran de Validation";
	

			break;
			
		case "PPF":
			// Action = 'PPF': Ecran de Projet de Programation. Statut de Filtre
			val="Ecran de projet de programmation";

			break;
			
		case "PPV":
			// Action = 'PPV': Ecran de Projet de Programation. Statut de Validation
			

			break;
		case "PF":
			// Action = 'PF': Ecran de Programation. Statut de Filtre
			val="Ecran de programmation";

			break;	
		
		case "PV":
			// Action = 'PV': Ecran de Programation. Statut de Programmation
			val="Ecran de programmation";

			break;	
		case "VD":
			// Action = 'VD': Ecran de Validation. Statut de Traitement Diff�r�
			val="Ecran de Validation";
			

			break;
			
			
		
					
	default:
		
		}
		
     
		System.out.println("--Retour libelle etape = "+action);
		return val;
		}
	
	
	static public String  returnLibelleEtapePreparation(String action, String foncCode){
		String val= "";
		
		foncCode= getMinCode(foncCode);
		switch(action){
		
		case "VF":
			//-- Action = 'TF': Ecran de Validation. Statut de Filtre
			if(foncCode.equalsIgnoreCase("SLD")){ val = "Ecran de validation";}
											else{ val = "Ecran de transmission";}
			break;
			
		
		case "CV":
			//Action = 'DF': Ecran de Validation. Statut  Diff�re Filtre
			val = "Ecran des calendriers valid�s";
			
			break;	
		case "DF":
			//Action = 'DF': Ecran de Validation. Statut  Diff�re Filtre
			if(!foncCode.equalsIgnoreCase("SLD")){ val = "Ecran de taitement du differ�";}
			
			break;	
			
		
			
			
		default:
			
		}
		
		
		System.out.println("--Retour libelle etape = "+action);
		return val;
	}

	
}
