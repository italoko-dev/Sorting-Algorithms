package applista;

public class Principal 
{
    private Lista l;
    
    public Principal () {l = new Lista();}
    
    public void executa()
    {
        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.InsercaoDireta();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO INSERÇÃO DIRETA ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}
       
        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.InsercaoBinaria();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO INSERÇÃO BINÁRIA ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}
        
        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.SelecaoDireta();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO SELEÇÃO DIRETA  ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}
        
        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.BubbleSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO BUBBLE SORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}
        
        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.ShakeSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO SHAKE SORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}
        
        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.ShellSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO SHELL SORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}

        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.HeapSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO HEAP SORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}

        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.QuickComPivo();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO QUICK SORT COM PIVÔ ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}

        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.QuickComPivo();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO QUICK SORT COM PIVÔ ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}

        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.QuickSemPivo();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO QUICK SORT SEM PIVÔ ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}
       
        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.MergeSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO MERGE SORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}

        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.MergeSortComSeq();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO MERGE SORT COM SEQUÊNCIA ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}

        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.CountingSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO COUNTING ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");} 

        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.BucketSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO BUCKETSORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");} 

        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.RadixSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO RADIXSORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");} 
        
        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.CombSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO COMBSORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");} 

        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.GnomeSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO GNOMESORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}
        
        if(l.GeraLista())
        {
            System.out.println("<-------  DADOS DESORDENADOS    ------->");
            l.exibe();
            l.TimSort();
            System.out.println("<-------  DADOS ORDENADOS MÉTODO TIMSORT ------->");
            l.exibe(); 
        }else{System.out.println("Problemas ao gerar lista...");}
        
    }
    
    public static void main(String[] args)
    {
       Principal app = new Principal();
       app.executa();
    }
}
