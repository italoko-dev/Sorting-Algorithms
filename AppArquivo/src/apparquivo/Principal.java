/* @author: ITALO CESAR PIOVAN ROCHA  */
package apparquivo;

import java.io.IOException;

public class Principal 
{
    private Arquivo arq,arqOrd,arqRev,arqRand;
    private Calculo calc;
    private Relatorio rel;
    private long tIni,tFim,tempTot;
    
    public Principal() throws IOException
    {
       calc = new Calculo();
       rel = new Relatorio("Tabela.txt");
       
       arq     = new Arquivo("Arquivo.dat");
       arqOrd  = new Arquivo("ArquivoORDENADO.dat");
       arqRev  = new Arquivo("ArquivoREVERSO.dat");
       arqRand = new Arquivo("ArquivoRANDOMICO.dat");
    }
    
    private void initTemp()
    {
        tIni = System.currentTimeMillis();
        tFim = 0;
        tempTot = 0;
    }
    
    private void GeraTabela() throws IOException
    {  
        System.out.println("Inicializando ..... ");
        System.out.println("Gerando arquivo base ( randômico )");
        arq.geraArquivoRandomico();
        System.out.println("ARQUIVO BASE gerado com sucesso.");
        
        /* ############ INSERÇÃO DIRETA ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método INSERÇÃO DIRETA [ARQ.ORDENADO]...");
        arqOrd.InsercaoDireta();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Inserção Direta",arqOrd.getComp(),calc.CompInsDirMin(arqOrd.filesize()),arqOrd.getMov(),calc.MovInsDirMin(arqOrd.filesize()),(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método INSERÇÃO DIRETA [ARQ.REVERSO]...");
        arqRev.InsercaoDireta();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),calc.CompInsDirMax(arqRev.filesize()),arqRev.getMov(),calc.MovInsDirMax(arqRev.filesize()),(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método INSERÇÃO DIRETA [ARQ.RANDÔMICO]...");
        arqRand.InsercaoDireta();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),calc.CompInsDirMed(arqRand.filesize()),arqRand.getMov(),calc.MovInsDirMed(arqRand.filesize()),(int)tempTot,true);
    
        /* ############ INSERÇÃO BINÁRIA ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método INSERÇÃO BINÁRIA [ARQ.ORDENADO]...");
        arqOrd.InsercaoBinaria();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Inserção Binária",arqOrd.getComp(),calc.CompInsBin(arqOrd.filesize()),arqOrd.getMov(),calc.MovInsBinMin(arqOrd.filesize()),(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método INSERÇÃO BINÁRIA [ARQ.REVERSO]...");
        arqRev.InsercaoBinaria();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),calc.CompInsBin(arqRev.filesize()),arqRev.getMov(),calc.MovInsBinMax(arqRev.filesize()),(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método INSERÇÃO BINÁRIA [ARQ.RANDÔMICO] ...");
        arqRand.InsercaoBinaria();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),calc.CompInsBin(arqRand.filesize()),arqRand.getMov(),calc.MovInsBinMed(arqRand.filesize()),(int)tempTot,true);
    
        /* ############ SELEÇÃO DIRETA ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método SELEÇÃO DIRETA [ARQ.ORDENADO] ...");
        arqOrd.SelecaoDireta();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Seleção Direta",arqOrd.getComp(),calc.CompSelDir(arqOrd.filesize()),arqOrd.getMov(),calc.MovSelDirMin(arqOrd.filesize()),(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método SELEÇÃO DIRETA [ARQ.REVERSO]...");
        arqRev.SelecaoDireta();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),calc.CompSelDir(arqRev.filesize()),arqRev.getMov(),calc.MovSelDirMax(arqRev.filesize()),(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método SELEÇÃO DIRETA [ARQ.RANDÔMICO]	...");
        arqRand.SelecaoDireta();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),calc.CompInsDirMin(arqRand.filesize()),arqRand.getMov(),calc.MovSelDirMed(arqRand.filesize()),(int)tempTot,true);
    
    
        /* ############ BUBBLE SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método BUBBLE SORT [ARQ.ORDENADO] ...");
        arqOrd.BubbleSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Bubble",arqOrd.getComp(),calc.CompPerDir(arqOrd.filesize()),arqOrd.getMov(),calc.MovPerDirMin(arqOrd.filesize()),(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método BUBBLE SORT [ARQ.REVERSO]...");
        arqRev.BubbleSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),calc.CompPerDir(arqRev.filesize()),arqRev.getMov(),calc.MovPerDirMax(arqRev.filesize()),(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método BUBBLE SORT [ARQ.RANDÔMICO]...");
        arqRand.BubbleSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),calc.CompPerDir(arqRand.filesize()),arqRand.getMov(),calc.MovPerDirMed(arqRand.filesize()),(int)tempTot,true);
    
        /* ############ SHAKE SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método SHAKE SORT [ARQ.ORDENADO] ...");
        arqOrd.ShakeSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Shake",arqOrd.getComp(),calc.CompPerDir(arqOrd.filesize()),arqOrd.getMov(),calc.MovPerDirMin(arqOrd.filesize()),(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método SHAKE SORT [ARQ.REVERSO]...");
        arqRev.ShakeSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),calc.CompPerDir(arqRev.filesize()),arqRev.getMov(),calc.MovPerDirMax(arqRev.filesize()),(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método SHAKE SORT [ARQ.RANDÔMICO]...");
        arqRand.ShakeSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),calc.CompPerDir(arqRand.filesize()),arqRand.getMov(),calc.MovPerDirMed(arqRand.filesize()),(int)tempTot,true);
        
        /* ############ SHELL SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método SHELL SORT [ARQ.ORDENADO] ...");
        arqOrd.ShellSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Shell",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método SHELL SORT [ARQ.REVERSO]...");
        arqRev.ShellSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método SHELL SORT [ARQ.RANDÔMICO]...");
        arqRand.ShellSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
    
        /* ############ HEAP SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método HEAP SORT [ARQ.ORDENADO] ...");
        arqOrd.HeapSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Heap",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método HEAP SORT [ARQ.REVERSO]...");
        arqRev.HeapSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método HEAP SORT [ARQ.RANDÔMICO]...");
        arqRand.HeapSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
        
        /* ############ QUICK SORT S/ PIVO ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método QUICK SORT S/ PIVO [ARQ.ORDENADO] ...");
        arqOrd.QuickSemPivo();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Quick s/ pivô",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método QUICK SORT S/ PIVO [ARQ.REVERSO]...");
        arqRev.QuickSemPivo();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método QUICK SORT S/ PIVO [ARQ.RANDÔMICO]...");
        arqRand.QuickSemPivo();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
        
        /* ############ QUICK SORT C/ PIVO ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método QUICK SORT C/ PIVO [ARQ.ORDENADO] ...");
        arqOrd.QuickComPivo();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Quick c/ pivô",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método QUICK SORT C/ PIVO [ARQ.REVERSO]...");
        arqRev.QuickComPivo();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método QUICK SORT C/ PIVO [ARQ.RANDÔMICO]...");
        arqRand.QuickComPivo();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
        
        /* ############ MERGE SORT 1 IMP. ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método MERGE SORT 1 IMP. [ARQ.ORDENADO] ...");
        arqOrd.MergeSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Merge Sort 1ºImp",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método MERGE SORT 1 IMP. [ARQ.REVERSO]...");
        arqRev.MergeSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método MERGE SORT 1 IMP. [ARQ.RANDÔMICO]...");
        arqRand.MergeSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
        
        /* ############ MERGE SORT 1 IMP. ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método MERGE SORT 2 IMP. [ARQ.ORDENADO] ...");
        arqOrd.MergeSortSegImp();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Merge Sort 2ºImp",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método MERGE SORT 2 IMP. [ARQ.REVERSO]...");
        arqRev.MergeSortSegImp();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método MERGE SORT 2 IMP. [ARQ.RANDÔMICO]...");
        arqRand.MergeSortSegImp();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
        
        /* ############ COUNTING SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método COUNTING SORT [ARQ.ORDENADO] ...");
        arqOrd.CountingSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Counting",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método COUNTING SORT [ARQ.REVERSO]...");
        arqRev.CountingSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método COUNTING SORT [ARQ.RANDÔMICO]...");
        arqRand.CountingSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
        
        /* ############ BUCKET SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método BUCKET SORT [ARQ.ORDENADO] ...");
        arqOrd.BucketSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Bucket",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método BUCKET SORT [ARQ.REVERSO]...");
        arqRev.BucketSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método BUCKET SORT [ARQ.RANDÔMICO]...");
        arqRand.BucketSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
        
        /* ############ RADIX SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método RADIX SORT [ARQ.ORDENADO] ...");
        arqOrd.RadixSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Radix",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método RADIX SORT [ARQ.REVERSO]...");
        arqRev.RadixSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método RADIX SORT [ARQ.RANDÔMICO]...");
        arqRand.RadixSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);  
    
        /* ############ COMB SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método COMB SORT [ARQ.ORDENADO] ...");
        arqOrd.CombSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Comb",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método COMB SORT [ARQ.REVERSO]...");
        arqRev.CombSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método COMB SORT [ARQ.RANDÔMICO]...");
        arqRand.CombSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
        
        /* ############ GNOME SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método GNOME SORT [ARQ.ORDENADO] ...");
        arqOrd.GnomeSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Gnome",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método GNOME SORT [ARQ.REVERSO]...");
        arqRev.GnomeSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método GNOME SORT [ARQ.RANDÔMICO]...");
        arqRand.GnomeSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
        
        /* ############ TIM SORT ########### */
        arq.ConfiguraArquivos(arqRand, arqOrd, arqRev);
        
        // ARQ. ORDENADO
        arqOrd.initComp(); arqOrd.initMov();
        initTemp();
        
        System.out.println("Ordenando com método TIM SORT [ARQ.ORDENADO] ...");
        arqOrd.TimSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqOrd.getComp()+"|MOV: "+arqOrd.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("Tim",arqOrd.getComp(),0,arqOrd.getMov(),0,(int)tempTot,false);
        
        // ARQ. REVERSO
        arqRev.initComp(); arqRev.initMov();
        initTemp();
        
        System.out.println("Ordenando com método TIM SORT [ARQ.REVERSO]...");
        arqRev.TimSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRev.getComp()+"|MOV: "+arqRev.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRev.getComp(),0,arqRev.getMov(),0,(int)tempTot,false);
        
        // ARQ. RANDÔMICO
        arqRand.initComp(); arqRand.initMov();
        initTemp();
        
        System.out.println("Ordenando com método TIM SORT [ARQ.RANDÔMICO]...");
        arqRand.TimSort();
        
        tFim = System.currentTimeMillis();
        tempTot = tFim - tIni ;
        System.out.println("ARQUIVO ORDENADO");
        System.out.println("TEMPO: "+tempTot+"|COMP: "+arqRand.getComp()+"|MOV: "+arqRand.getMov());
        System.out.println("Gerando Relatório ...");
        rel.MontaLinha("",arqRand.getComp(),0,arqRand.getMov(),0,(int)tempTot,true);
    }

    public static void main(String args[]) throws IOException
    {
        Principal app = new Principal();
        app.GeraTabela();
    }  
}