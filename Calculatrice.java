package cms_calculatrice;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Calculatrice extends JFrame implements ActionListener{
	JButton bouton1=new JButton("1");
	JButton bouton2=new JButton("2");
	JButton bouton0=new JButton("0");
	JButton boutonPoint=new JButton(".");
	JButton bouton3=new JButton("3");
	JButton bouton4=new JButton("4");
	JButton bouton5=new JButton("5");
	JButton bouton6=new JButton("6");
	JButton bouton7=new JButton("7");
	JButton bouton8=new JButton("8");
	JButton bouton9=new JButton("9");
	JButton boutonEgal=new JButton("=");
	JButton boutonPlus=new JButton("+");
	JButton boutonMoins=new JButton("-");
	JButton boutonFois=new JButton("*");
	JButton boutonDiv=new JButton("/");
	String numS1="",numS2="";
	
	public Calculatrice() {
		setTitle("CALCULATRICE");
		//setSize(350,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		//JPanel pan=new JPanel(new GridLayout(0,4));
		
		Toolkit tkit=Toolkit.getDefaultToolkit();
		Dimension dimMoniteur=tkit.getScreenSize();
		setBounds(dimMoniteur.width/2-258/2,dimMoniteur.height/2-281/2,258,281);
		Container conPan=getContentPane();
		conPan.setLayout(new GridLayout(4,4));
		bouton1.addActionListener(this);
		bouton2.addActionListener(this);
		bouton3.addActionListener(this);
		bouton4.addActionListener(this);
		bouton5.addActionListener(this);
		bouton6.addActionListener(this);
		bouton7.addActionListener(this);
		bouton8.addActionListener(this);
		bouton9.addActionListener(this);
		boutonPlus.addActionListener(this);
		boutonMoins.addActionListener(this);
		boutonDiv.addActionListener(this);
		boutonFois.addActionListener(this);
		boutonEgal.addActionListener(this);
		bouton0.addActionListener(this);
		boutonPoint.addActionListener(this);
		conPan.add(bouton7);
		conPan.add(bouton8);
		conPan.add(bouton9);
		conPan.add(boutonEgal);
		conPan.add(bouton4);
		conPan.add(bouton5);
		conPan.add(bouton6);
		conPan.add(boutonMoins);
		conPan.add(bouton1);
		conPan.add(bouton2);
		conPan.add(bouton3);
		conPan.add(boutonPlus);
		conPan.add(bouton0);
		conPan.add(boutonPoint);
		conPan.add(boutonDiv);
		conPan.add(boutonFois);
		setVisible(true);
		
	}
	
	String labelStr="";
	char labelChar;
	char op='+';
	//String numS1,numS2;
	boolean enCours=false;
	boolean estVirgule=false;
	double num1,num2;
	boolean opBool=false;
	@Override
	public void actionPerformed(ActionEvent e) {
		labelStr=labelStr+((JButton)e.getSource()).getText().charAt(0);
		labelChar=((JButton)e.getSource()).getText().charAt(0);
		System.out.println(labelStr);
		
		if(labelChar>='0' && labelChar<='9' || labelChar=='.') {
			if(enCours==false)
				{
				if(labelChar=='.' && estVirgule)
				{
					System.out.println("Erreur! déjà un point!!\n");
					labelStr=labelStr.substring(0,labelStr.length()-1);
				}
				else {
					if(labelChar=='.')
						estVirgule=true;
					numS1+=labelStr;
					opBool=false;
					//System.out.print(labelChar);
					}
				}
			else if(enCours==true) {
				if(labelChar=='.' && estVirgule)
				{
					System.out.println("Erreur! déjà un point!!\n"+numS1+op+numS2);
					labelStr=labelStr.substring(0,labelStr.length()-1);
					System.out.println(labelStr);
				}
				else {
					if(labelChar=='.')
						estVirgule=true;
					numS2+=labelStr;
					opBool=false;
					//System.out.print(labelChar);
					}
				}
			}
		else if(labelChar=='+' || labelChar=='-' || labelChar=='*' || labelChar=='/') {
			if(numS1=="") {
				System.out.println("ERREUR!!premier numéro inconnu");
				labelStr=labelStr.substring(0,labelStr.length()-1);
				System.out.println(labelStr);
			}else if(numS2!="") {
				//num1=Double.parseDouble(numS1);
				//num2=Double.parseDouble(numS2);
				/*switch(op) {
				case '+':System.out.println("="+(num1+num2)+"\n"); num1=num1+num2; break;
				case '*':System.out.println("="+(num1*num2)); break;
				case '/':System.out.println("="+(num1/num2)); break;
				case '-':System.out.println("="+(num1-num2));
				*/
				estVirgule=false;
				op=labelChar;
				enCours=true;
				//System.out.print(op);
				}
			else {
				estVirgule=false;
				op=labelChar;
				enCours=true;
				
			}
			if(opBool) {
				System.out.println("ERREUR!! DEJA UN OPERATEUR");
				labelStr=labelStr.substring(0,labelStr.length()-1);
			}else{opBool=true;}

		}
			
		
		//****************if labelChar=='='***********************
		else {
			
			int occFois=nbOccurence(labelStr,'*');
			int occPlus=nbOccurence(labelStr,'+');
			int occDiv=nbOccurence(labelStr,'/');
			int occMoins=nbOccurence(labelStr,'-');

			//**************TRAITEMENT DU FOIS**************
			int ind=0;
			int ind1=-1;
			int ind2=labelStr.length();
			for(int i=0;i<occFois;i++) {
				ind=labelStr.indexOf("*", ind);
				
				//*****************num1************************
				int indexPlus=labelStr.lastIndexOf("+",ind-1);
				int indexMoins=labelStr.lastIndexOf("-",ind-1);
				int indexDiv=labelStr.lastIndexOf("/",ind-1);
				int indexFois=labelStr.lastIndexOf("*",ind-1);
				
				if(indexPlus==-1)
					indexPlus=0;
				if(indexDiv==-1)
					indexDiv=0;
				if(indexMoins==0)
					indexMoins=0;
				if(indexFois==-1)
					indexFois=0;

				int mp=Integer.max(indexMoins, indexPlus);
				int df=Integer.max(indexDiv, indexFois);
				ind1=Integer.max(mp, df);
				
				double num1=0;
				if(ind1!=0)
					num1=Double.parseDouble(labelStr.substring(ind1+1,ind));
				else num1=Double.parseDouble(labelStr.substring(ind1,ind));
				
				//***************num2***************************
				int indexPlus2=labelStr.indexOf("+",ind+1);
				int indexMoins2=labelStr.indexOf("-",ind+1);
				int indexDiv2=labelStr.indexOf("/",ind+1);
				int indexFois2=labelStr.indexOf("*",ind+1);
				
				if(indexPlus2==-1)
					indexPlus2=labelStr.length()-1;
				if(indexDiv2==-1)
					indexDiv2=labelStr.length()-1;
				if(indexMoins2==-1)
					indexMoins2=labelStr.length()-1;
				if(indexFois2==-1)
					indexFois2=labelStr.length()-1;
				
				int md=Integer.min(indexMoins2, indexDiv2);
				int pf=Integer.min(indexPlus2,indexFois2);
				ind2=Integer.min(md, pf);
				double num2=Double.parseDouble(labelStr.substring(ind+1,ind2));	
				
				//********************RESULTAT DE L'OPERATION PARTIELLE****************
				if(ind1!=0)
					labelStr=labelStr.replace(labelStr.substring((ind1+1),(ind2)),String.valueOf(num2*num1));
				else labelStr=labelStr.replace(labelStr.substring((ind1),(ind2)),String.valueOf(num2*num1));

				System.out.println(labelStr);
				
			}//********FIN TRAITEMENT DU FOIS************
			
			//*********************TRAITEMENT DU DIV*******************
			for(int i=0;i<occDiv;i++) {
				ind=labelStr.indexOf("/");
				
				//******************num1*********************
				int indexPlus=labelStr.lastIndexOf("+",ind-1);
				int indexMoins=labelStr.lastIndexOf("-",ind-1);
				int indexDiv=labelStr.lastIndexOf("/",ind-1);
				int indexFois=labelStr.lastIndexOf("*",ind-1);
				
				if(indexPlus==-1)
					indexPlus=0;
				if(indexDiv==-1)
					indexDiv=0;
				if(indexMoins==-1)
					indexMoins=0;
				if(indexFois==-1)
					indexFois=0;
				
				int mp=Integer.max(indexMoins, indexPlus);
				int df=Integer.max(indexDiv, indexFois);
				ind1=Integer.max(mp, df);
				double num1=0;
				if(ind1!=0)
					num1=Double.parseDouble(labelStr.substring(ind1+1,ind));
				else num1=Double.parseDouble(labelStr.substring(ind1,ind));
				
				//************************num2******************
				int indexPlus2=labelStr.indexOf("+",ind+1);
				int indexMoins2=labelStr.indexOf("-",ind+1);
				int indexDiv2=labelStr.indexOf("/",ind+1);
				int indexFois2=labelStr.indexOf("*",ind+1);

				if(indexPlus2==-1)
					indexPlus2=labelStr.length()-1;
				if(indexDiv2==-1)
					indexDiv2=labelStr.length()-1;
				if(indexMoins2==-1)
					indexMoins2=labelStr.length()-1;
				if(indexFois2==-1)
					indexFois2=labelStr.length()-1;

				int md=Integer.min(indexMoins2, indexDiv2);
				int pf=Integer.min(indexPlus2,indexFois2);
				ind2=Integer.min(md, pf);
				double num2=Double.parseDouble(labelStr.substring(ind+1,ind2));
				
				//***************RESULTAT DE L'OPERATION PARTIELLE******************
				if(ind1!=0)	
					labelStr=labelStr.replace(labelStr.substring((ind1+1),(ind2)),String.valueOf(num1/num2));
				else labelStr=labelStr.replace(labelStr.substring((ind1),(ind2)),String.valueOf(num1/num2));
				System.out.println(labelStr);
			}//********FIN TRAITEMENT DU DIV*******************
			
			
			//***********************TRAITEMENT DU RESTE DE L'OPERATION*****************
			int j=0;
			double res=0;
			//*************CAS OU IL Y AU MOINS UN + OU UN - ******************
			if(labelStr.indexOf("+")!=-1 || labelStr.indexOf("-")!=-1) {
				for(int i=0;i<occPlus+occMoins;i++){
					int indexPlus=labelStr.indexOf("+",j);
					int indexMoins= labelStr.indexOf("-",j);
					
					if(indexPlus==-1)
						indexPlus=labelStr.length()+1;
					if(indexMoins==-1)
						indexMoins=labelStr.length()+1;
					
					int min = Integer.min(indexPlus,indexMoins);
					num1=Double.parseDouble(labelStr.substring(j,min));
					
					char op=labelStr.charAt(min);
					
					j=min+1;
					
					indexPlus=labelStr.indexOf("+",j);
					indexMoins= labelStr.indexOf("-",j);
					
					if(indexPlus==-1)
						indexPlus=labelStr.length()+1;
					if(indexMoins==-1)
						indexMoins=labelStr.length()+1;
					//labelStr=labelStr.replaceAll("--","+ ");
					min = Integer.min(indexPlus,indexMoins);
					System.out.println(labelStr);
					
					if(min>labelStr.length())
						min=labelStr.length();
				
					try {
						num2=Double.parseDouble(labelStr.substring(j,min-1));
					}catch(NumberFormatException exp) {
						System.out.println(exp.getMessage());
						System.out.println(j+"  "+min);
					}
					switch(op) {
						case '+': res = res + num1+num2; break;
						case '-': res = res + num1-num2;
					}
					
				}System.out.println(res);	
			}

			//*************CAS OU L'OPERATION NE CONTIENT AUCUN + OU - *********************
			else {
				System.out.println(labelStr.substring(0,labelStr.length()-1));
			}
				
	};
};
	
	public int nbOccurence(String ch,char c) {
		int occ=0;
		
		for (int i=0;i<ch.length();i++) {
			if(ch.charAt(i)==c)
				occ++;
		}
		return occ;
	}
	
}	