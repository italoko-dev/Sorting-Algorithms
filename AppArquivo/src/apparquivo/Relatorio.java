/* @author: ITALO CESAR PIOVAN ROCHA  */
package apparquivo;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Relatorio 
{
    private int iniPag;
    private int fimPag;
    private int tamLegenda;
    private int tamNomeMet;
    private DataOutputStream wr;
    
    public Relatorio(String nomeArq) throws IOException
    {
        iniPag = 0;
        fimPag = Cabecalho().length();
        tamLegenda = Legenda().length();
        wr = AbreArquivoRelatorio(nomeArq);
        tamNomeMet = "Métodos de Ordenação |".length();
        MontarCabLeg();
    }
    
    private DataOutputStream AbreArquivoRelatorio(String nomeArq)
    {
        FileOutputStream arq = null; 
        
        try {
            arq = new FileOutputStream(nomeArq);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DataOutputStream(arq);
    }
         
    private String  Cabecalho()
    {
        return  "Métodos de Ordenação |  Arquivo Ordenado "
                + "                                              "
                + "|  Arquivo Ordem Reversa                                          "
                + "|  Arquivo Randômico                                             |";
    }
    
    private String Legenda()
    {
        return "|  *CompProg  |  #CompEqua  |  +Mov Prog  |  -Mov Equa  |  ?Tempo ";
    }
    
    private void MontarCabLeg() throws IOException
    {
        String esp="";
        
        for(int i = 1; i < tamNomeMet; i++)
            esp+=" ";
        
        GravaLinha(Cabecalho()+Underline()+esp+Legenda()+Legenda()+Legenda()+"|"+Underline());
    }
    
    private String Underline()
    {
        String u="\n";
        for(int i=iniPag; i < fimPag ; i++)
            u+="_";
        return u+"\n";
    }
    
    public void MontaLinha(String metodo,int compProg,int compEq,int movProg,int movEq,int temp,boolean fim) throws IOException
    {
       int i=0;
       String str = metodo;
       char leg[] = Legenda().toCharArray();   
      
       if(!metodo.isEmpty())
            for (int j = metodo.length(); j < tamNomeMet-1; j++ )
                str+=" ";
       
       while( i < tamLegenda )
       {
           if(Character.isLetter(leg[i]) || leg[i] == ' ')
           {
               str+=" ";
               i++;
           }    
           else
           {
               switch (leg[i])
               {
                   case '*':
                   {
                       str+=compProg;
                       i+= Integer.toString(compProg).length();
                       break;
                   }
                   case '#':
                   {
                       str+=compEq;
                       i+= Integer.toString(compEq).length();
                       break;
                   }
                   case '+':
                   {
                       str+=movProg;
                       i+= Integer.toString(movProg).length();
                       break;
                   }
                   case '-':
                   {
                       str+=movEq;
                       i+= Integer.toString(movEq).length();
                       break;
                   }
                   case '?':
                   {
                       str+=temp;
                       i+= Integer.toString(temp).length();
                       break;
                   }                   
                   case '|':
                   {
                       str+="|";
                       i++;
                       break;
                   }
               }
           }
       }
       if(fim)
       {
           GravaLinha(str+"|");
           GravaLinha(Underline());
       } 
       else
           GravaLinha(str);
    }
    
    private void GravaLinha(String linha) throws IOException
    {
        wr.writeBytes(linha); 
    }
}
