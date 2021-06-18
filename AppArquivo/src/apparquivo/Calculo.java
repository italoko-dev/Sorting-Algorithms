/* @author: ITALO CESAR PIOVAN ROCHA  */
package apparquivo;


public class Calculo 
{
    private double e;
    private double g;
    
    public Calculo()
    {
        e = 2.71828; 
        g = 0.577216; // CONSTANTE DE EULER
    }
    
    private double log(double n) 
    {
        return Math.log((double)n);
    }
    
    private double pow(double x,double y)
    {
        return Math.pow((double)x, (double)y);
    }
    
    //ANÁLISE DE EFICIÊNCIA INSERÇÃO DIRETA 
    public int CompInsDirMin(int n)
    {
        return n-1;
    }
    
    public int CompInsDirMed(int n)
    {
        return (int)(pow(n,2)+n-2)/4;
    }
    
    public int CompInsDirMax(int n)
    {
        return (int)(pow(n,2)+n-4)/4;
    }
    
    public int MovInsDirMin(int n)
    {
        return 3*(n-1);
    }
    
    public int MovInsDirMed(int n)
    {
        return (int)(pow(n,2)+9*n-10)/4;
    }
    
    public int MovInsDirMax(int n)
    {
        return (int)(pow(n,2)+3*n-4)/2;
    }
    
    //ANÁLISE DE EFICIÊNCIA INSERÇÃO BINÁRIA
    
    public int CompInsBin(int n)
    {
        return (int)(n * (log(n) - log(e) + 0.5));
    }
    
    public int MovInsBinMin(int n)
    {
        return 3*(n-1);
    }
    
    public int MovInsBinMed(int n)
    {
        return (int)(pow(n,2)+9*n-10)/4;
    }
    
    public int MovInsBinMax(int n)
    {
        return (int)(pow(n,2)+3*n-4)/2;
    }
    
    //ANÁLISE DE EFICIÊNCIA SELEÇÃO DIRETA
    
    public int CompSelDir(int n)
    {
        return  (int)(pow(n,2)-n)/2;
    }
    
    public int MovSelDirMin(int n)
    {
        return  3*(n-1);
    }
    
    public int MovSelDirMed(int n)
    {
        return  (int)(n*(log(n)+g));
    }
    
    public int MovSelDirMax(int n)
    {
        return  (int)(pow(n,2)/4 + 3 * (n-1));
    }
    
    //ANÁLISE DE EFICIÊNCIA PERMUTAÇÃO DIRETA (BUBBLE E SHAKE)
    
    public int CompPerDir(int n)
    {
        return (int)((pow(n,2)- n)/2);
    }
    
    public int MovPerDirMin(int n)
    {
        return 0;
    }
    
    public int MovPerDirMed(int n)
    {
        return (int)(3*(pow(n,2)- n)/2);
    } 
    
    public int MovPerDirMax(int n)
    {
        return (int)(3*(pow(n,2)- n)/4);
    }  
}
