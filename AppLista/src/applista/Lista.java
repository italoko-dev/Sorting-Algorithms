
package applista;

import java.util.Random;

public class Lista 
{
    private No inicio;
    private No fim;
    private int qtd;

    public Lista()
    {
        inicio = fim = null;
        qtd = 0;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public No getInicio() {
        return inicio;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    public No getFim() {
        return fim;
    }

    public void setFim(No fim) {
        this.fim = fim;
    }
    
    public void inicializa()
    {
        System.gc();
        inicio = fim = null;
        qtd = 0;
    }
   
    public void inserirNoFinal(int info)
    {
        No nova = new No(fim,null,info);
        if(inicio == null)
            inicio = fim = nova;
        else
        {
            fim.setProx(nova);
            fim = nova;
        }
        qtd++;
    }
    
    public void exibe()
    {
        No aux = inicio;
        while(aux != null)
        {
            System.out.println(aux.getInfo());
            aux = aux.getProx();
        }
    }
    
    public boolean GeraLista()
    {
        inicializa();
        Random rand = new Random();
        for(int i = 0; i < 16 ; i++)
            inserirNoFinal(rand.nextInt(100)+1);
        return inicio!=fim;
    }
    
    /** METODOS AUXILIARES DAS ORDENAÇÕES ***/
    public void swap(No A, No B)
    {
        int aux;
        aux = A.getInfo();
        A.setInfo(B.getInfo());
        B.setInfo(aux);
    }
    
    public No BuscaBinaria(int chave,int TL,No pTL)// TL = indice , pTL == & do TL // adptada para inserçãoBinária()
    {
        int ini = 0,fim = TL-1,meio = fim /2;
        No pIni = getInicio(),pMeio = pIni,pFim = pTL;

        if(chave > pFim.getInfo())
            return pFim;
      
        pMeio = Posiciona(meio,getInicio());
        
        while(pMeio != null && ini < fim && chave != pMeio.getInfo())
        {
            if(chave < pMeio.getInfo())
            {
                fim = meio -1;
                pFim = pMeio.getAnt();
                meio = (ini+fim) / 2;  
                for(int i=fim+1; pMeio != null &&  i > meio; i--)
                    pMeio = pMeio.getAnt();
            } 
            else
            {
                ini = meio+1;
                pIni = pMeio.getProx();
                meio = (ini+fim) / 2;  
                for(int i = 0;pMeio != null && i < (meio-ini)+1;i++)
                    pMeio = pMeio.getProx();
            }
        }
        if(chave > pMeio.getInfo())
            return pMeio.getProx();
        return pMeio;
    } 
    
    public void QuickSemPivo()
    {
        QuickSP(0,getQtd()-1,getInicio(),getFim());
    }
    
    public void QuickComPivo()
    {
        QuickCP(0,getQtd()-1,getInicio(),getFim());
    }
    
    public void MergeSort()
    {
        Lista ListAux = new Lista();
        ListAux.GeraLista();
        Merge(ListAux,0,getQtd()-1);
    }
    
    public int MaiorElemento()
    {
        No aux = getInicio();
        int maior = 0;
        for(int i = 0; i < getQtd() ; i++,aux = aux.getProx())
        {
            if(maior < aux.getInfo())
                maior = aux.getInfo();
        }
        return maior;
    }
    
    public int MenorElemento()
    {
        No aux = getInicio();
        int menor = MaiorElemento();
        
        for(int i = 0; i < getQtd() ; i++,aux = aux.getProx())
        {
            if(menor > aux.getInfo())
                menor = aux.getInfo();
        }
        
        return menor;
    }
    
    public int Menor(int A,int B)
    {
        if(A < B)
            return A;
        return B;
    }
    
    public No Posiciona(int pos,No apartir)
    {
        No aux =  apartir;
        for(int i = 0 ; i < pos && aux != null ; i++,aux = aux.getProx()){}
        return aux;
    }
    
    public void CountingParaRadix(int exp)
    {
        int maior = MaiorElemento(), i, TL = getQtd();  
        No aux; 
        
        int c[] = new int[maior];
        int b[] = new int[TL];
        
        aux = getInicio();
        for(i = 0; i < TL; i++) // controla frequência
        {
            c[((aux.getInfo()-1)/exp)%10] += 1;
            aux = aux.getProx();
        }
        
        for(i = 1; i < maior; i++) // acumula
            c[i] += c[i-1];

        aux = getInicio();
        for(i = TL-1; i >=0 ; i--) // monta vetor b ordenado
        {
            b[c[((aux.getInfo()-1)/exp)%10]-1] = aux.getInfo();
            c[((aux.getInfo()-1)/exp)%10]--;
            aux = aux.getProx();
        }
        
        aux = getInicio();
        for(i = 0; i < TL; i++)
        {
            aux.setInfo(b[i]);
            aux = aux.getProx();
        }
    }
    
    public void InsercaoDireta()
    {
        No i = getInicio().getProx(),pos;
        int aux;
        while(i != null)
        {
            aux = i.getInfo();
            pos = i;
            while(pos != getInicio() && aux < pos.getAnt().getInfo())
            {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }
            pos.setInfo(aux);
            i = i.getProx();
        }
    }
    
    /** FIM METODOS AUXILIARES ***/
    
    /**** METODOS DE ORDENAÇÃO ******/
    public void InsercaoDirTim(No ini,No fim)
    {
        No i = ini,pos;
        int aux;
        while(i != fim)
        {
            aux = i.getInfo();
            pos = i;
            while(pos != getInicio() && aux < pos.getAnt().getInfo())
            {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }
            pos.setInfo(aux);
            i = i.getProx();
        }
    }
    
    public void InsercaoBinaria()
    {
        No i = getInicio().getProx(),pos;
        int aux,TL=1;
        while(i != null)
        {
            aux = i.getInfo();
            pos = BuscaBinaria(aux,TL,i);
            for(No j  = i; j != pos ;j = j.getAnt())
                j.setInfo(j.getAnt().getInfo());
            
            pos.setInfo(aux);
            i = i.getProx(); TL++;
        }
    }
    
    public void SelecaoDireta()
    {
	int menor;
	No pos=null,j,i = getInicio();
	
	while(i.getProx()!= null)
	{
            menor = i.getInfo();
            j = i.getProx();
            while(j != null)
            {
                if (j.getInfo() < menor) 
                {
                    menor = j.getInfo();
                    pos = j;
                }
                j = j.getProx();
            }		
            pos.setInfo(i.getInfo());
            i.setInfo(menor);
            i = i.getProx();
	}
    }
    
    public void BubbleSort()
    {
	for(No auxNo = getFim(); auxNo != getInicio(); auxNo = auxNo.getAnt())
            for(No i=getInicio(); i != auxNo;i = i.getProx())
                if( i.getInfo() > i.getProx().getInfo() )
                    swap(i.getProx(),i);          
    }
   
    public void ShakeSort()
    {
        int aux,auxini=0,auxfim = getQtd()-1;
        No iniNO = getInicio(),fimNO = getFim();
        while( auxini < auxfim )
        {
            for(No i = iniNO; i != fimNO;i = i.getProx() )
                if(i.getInfo() > i.getProx().getInfo())
                    swap(i.getProx(),i);

            auxfim--;fimNO = fimNO.getAnt();
            
            for(No i = fimNO;i != iniNO; i=i.getAnt())
                if(i.getInfo() < i.getAnt().getInfo())
                    swap(i.getAnt(),i);
              
            auxini++;iniNO = iniNO.getProx();
        }
    }
    
    public void ShellSort()
    {
	int dist = 4, i, j, k,temp;
	No iNO,jNO,kNO,distNO;
        while(dist>0)
	{
            for(i=0,iNO = inicio; i<dist; i++,iNO = iNO.getProx())
            {
                j = i; jNO = iNO;
                while(j+dist<qtd)
                {
                    for(temp = 0,distNO = jNO; temp < dist ; temp++,distNO = distNO.getProx()){} // posciona distNO p/ comparar ... 
                        
                    if(jNO.getInfo()>distNO.getInfo())
                    {
                        swap(jNO,distNO);

                        k = j; kNO = jNO;
                        
                        if(k-dist>=i)
                        {
                            for(temp = 0,distNO = kNO; temp < k ; temp++,distNO = distNO.getAnt()){}
                        
                            while(k-dist>=i &&  kNO.getInfo() < distNO.getInfo())
                            {
                                swap(kNO,distNO);
                                k-=dist;
                                for(temp = dist; temp>0; temp--,kNO = kNO.getAnt()){}
                            }
                        }     
                    }
                    j+=dist;
                    for(temp = 0; temp < dist; temp++,jNO = jNO.getProx()){}
                }
            }
            dist/=2;
	}
    }
    
    public void HeapSort() 
    {
	int TL = getQtd(),pai,dir;
	No paiNO,posMaior,fd,fe;
        
	while(TL>1)
	{
            pai = TL/2-1;
            paiNO = Posiciona(pai,getInicio());
            while(pai >= 0)
            {
                fe = Posiciona(pai+pai+1,getInicio());
                fd = fe.getProx(); dir = pai+pai+2;
                posMaior = fe;
                if(dir<TL && fd.getInfo() > fe.getInfo())
                    posMaior = fd;
                
                if(posMaior.getInfo() > paiNO.getInfo())
                    swap(posMaior,paiNO);
              
                paiNO = paiNO.getAnt();
                pai--;
            }
            TL--;
            swap(Posiciona(TL,getInicio()),getInicio());
	}
    }
    
    public void QuickSP(int ini, int TL,No iniNO,No fimNO) 
    {
	int i=ini,j=TL;
	boolean flag=true;
        No iNO = iniNO,jNO = fimNO;
	while(i < j)
	{
            if(flag)
                while(i < j && iNO.getInfo() <= jNO.getInfo()){i++;iNO = iNO.getProx();}     
            else
                while(j > i && jNO.getInfo() >= iNO.getInfo()){j--;jNO = jNO.getAnt();}
   
            swap(iNO,jNO);
            flag = !flag;
	}
	if(ini < i-1)
            QuickSP(ini,i-1,iniNO,iNO.getAnt());
	if(j+1 < TL)
            QuickSP(j+1,TL,jNO.getProx(),fimNO);
    }
    
    public void QuickCP(int ini, int TL,No iniNO,No fimNO) 
    {
        int i=ini,j=TL,pivo,temp;
        No pivoNO,iNO=iniNO,jNO=fimNO;
  
        pivoNO = Posiciona((ini+TL)/2,getInicio());
        pivo = pivoNO.getInfo();
        
        while(i<j)
        {
            while(iNO.getInfo() < pivo){i++;iNO = iNO.getProx();}

            while(jNO.getInfo() > pivo){j--;jNO = jNO.getAnt();}

            if(i<=j)
            {
                swap(iNO,jNO);
                i++;j--;
                iNO = iNO.getProx(); jNO = jNO.getAnt();
            }		
        }

        if(ini<j)
            QuickCP(ini,j,iniNO,jNO);
        if(i<TL)
            QuickCP(i,TL,iNO,fimNO);
    }
    
    /*** PRIMEIRA IMPLEMENTAÇÃO DO MERGE SORT ***/ 
     
    public void ParticaoCSeq(Lista p1,Lista p2)
    {
        int i;
        No aux = getInicio(),aux2 = getInicio();
        
        p1.inicializa();
        p2.inicializa();
        
        for(i= 0;i < getQtd()/2; i++,aux2 = aux2.getProx()){}

        for(i = 0; i < getQtd()/2 ; i++,aux = aux.getProx(),aux2 = aux2.getProx())
        {
            p1.inserirNoFinal(aux.getInfo());
            p2.inserirNoFinal(aux2.getInfo());
        }
    }
    
    public void FusaoCSeq(No p1,No p2,int seq)
    {
       int i = 0, j = 0,t_seq = seq; 
       No iNO = p1,jNO=p2,kNO=getInicio();
      
       while(kNO != null)
       {
           while( i < t_seq && j < t_seq)
           {
               if(iNO.getInfo() < jNO.getInfo())
               {    
                   kNO.setInfo(iNO.getInfo());
                   i++;iNO = iNO.getProx();
               }
               else
               {
                   kNO.setInfo(jNO.getInfo());
                   j++;jNO = jNO.getProx();
               }
               kNO = kNO.getProx();
           }
           
           while(i < t_seq )
           {
               kNO.setInfo(iNO.getInfo());
               kNO = kNO.getProx();
               i++;iNO = iNO.getProx();
           }
           
           while(j < t_seq )
           {
               kNO.setInfo(jNO.getInfo());
               kNO = kNO.getProx(); 
               j++;jNO = jNO.getProx();
           }
           t_seq = t_seq + seq;
       }       
    }
    
    public void MergeSortComSeq()
    {
        int seq=1;
        
        Lista p1 = new Lista();
        Lista p2 = new Lista();

        while(seq < getQtd())
        {
            ParticaoCSeq(p1,p2);
            FusaoCSeq(p1.getInicio(),p2.getInicio(),seq);
            seq = seq * 2;
        }
    
    }
    
    /*** SEGUNDA IMPLEMENTAÇÃO DO MERGE SORT ***/ 
    
    public void Fusao(Lista ListAux,int ini1, int fim1, int ini2, int fim2)
    {
        int i = ini1, j = ini2,k = 0;
        No iNO = Posiciona(ini1,getInicio()),jNO = Posiciona(ini2,getInicio()),aux = ListAux.getInicio();
        
        while(i <= fim1 && j<=fim2)
        {
            if(iNO.getInfo() < jNO.getInfo())
            {    
                aux.setInfo(iNO.getInfo());
                i++;iNO = iNO.getProx();
            }
            else
            {
                aux.setInfo(jNO.getInfo());
                j++;jNO = jNO.getProx();
            }
            aux = aux.getProx(); k++;
        }
        while(i <= fim1)
        {
            aux.setInfo(iNO.getInfo());
            aux = aux.getProx();
            i++;iNO = iNO.getProx();
            k++;
        }  
        while(j <= fim2)
        {
            aux.setInfo(jNO.getInfo());
            aux = aux.getProx(); 
            j++;jNO = jNO.getProx();
            k++;
        }
        
        aux = ListAux.getInicio();
        for(i = 0; i < k ; i++,aux = aux.getProx())
        {
            iNO = Posiciona(i+ini1,getInicio());
            iNO.setInfo(aux.getInfo());
        }
    }
    
    public void Merge(Lista listAux,int esq,int dir) 
    {
        int meio ; 
        if(esq < dir)
        {
            meio = (esq + dir) / 2; 
            Merge(listAux,esq,meio);
            Merge(listAux,meio+1,dir);
            Fusao(listAux,esq,meio,meio+1,dir);
        }
    }
    
    public void CountingSort()
    {
        int maior = MaiorElemento(), i, TL = getQtd();  
        No aux; 
        
        int c[] = new int[maior];
        int b[] = new int[TL];
        
        aux = getInicio();
        for(i = 0; i < TL ; i++) // controla frequência
        {
            c[aux.getInfo()-1] += 1;
            aux = aux.getProx();
        }
        
        for(i = 1; i < maior; i++) // acumula
            c[i] += c[i-1];

        aux = getInicio();
        for(i = 0; i < TL; i++) // monta vetor b ordenado
        {
            b[c[aux.getInfo()-1]-1] = aux.getInfo();
            c[aux.getInfo()-1]--;
            aux = aux.getProx();
        }
        
        aux = getInicio();
        for(i = 0; i < TL; i++)
        {
            aux.setInfo(b[i]);
            aux = aux.getProx();
        }
    }
    
    public void BucketSort()
    {
        int qtdbaldes = 3;
        No aux,auxBalde;
        /* sei que aqui poderia ser feito a divisão mais uniforme possível dos baldes, usando hash ou intervalos ... 
        mas para esse fim usei qtd de baldes fixos.. */
        
        Lista l0 = new Lista(); // 3 listas que servirão de baldes
        Lista l1 = new Lista();
        Lista l2 = new Lista();
            
        Lista baldes[] = new Lista[qtdbaldes]; //mapeamento dos baldes
        baldes[0] = l0;baldes[1] = l1; baldes[2] = l2;
        
        aux = getInicio();
        while(aux != null) // separa elementos nos respectivos baldes 
        {
            if(aux.getInfo() >=1 && aux.getInfo() <= 33)
                l0.inserirNoFinal(aux.getInfo());
            else
                if(aux.getInfo() >=34 && aux.getInfo() <= 66)
                    l1.inserirNoFinal(aux.getInfo());
                else
                    l2.inserirNoFinal(aux.getInfo());
            aux = aux.getProx();
        }
        
        for(int i = 0 ; i < qtdbaldes; i++) // ordena baldes 
            if(baldes[i].getQtd() >= 1)
                baldes[i].InsercaoDireta();
        
        for(int i = 0 ; i < qtdbaldes; i++) // Concatena baldes ordenados na lista principal 
        {   
            if(baldes[i].getQtd() >= 1)
            {   
                aux = getInicio();
                auxBalde = baldes[i].getInicio();
                while(auxBalde != null)
                {
                    aux.setInfo(auxBalde.getInfo());
                    auxBalde = auxBalde.getProx();
                    aux = aux.getProx();
                }
            }
        }   
        
    }
    
    public void RadixSort()
    {
        int maior = MaiorElemento();
        for(int exp =1; maior / exp >0 ; exp *=10) 
            CountingParaRadix(exp); 
    }
    
    public void CombSort()
    {
        int TL = getQtd(), gap = TL;
        No aux1,aux2;
        
        while(gap > 1)
        {
            gap = (gap*10)/13;
            for(int i = 0; i < TL - gap ; i++)
            {
               aux1 = Posiciona(i,getInicio());
               aux2 = Posiciona(i+gap,getInicio()); 
               if(aux1.getInfo() > aux2.getInfo())
                   swap(aux1,aux2);
            }
        }
    }
    
    public void GnomeSort()
    {
        int i=0;
        No aux = getInicio();
        
       while(i < getQtd())
       {
           if(i == 0)
           {aux = aux.getProx(); i++;}
           
           if(aux.getInfo() >= aux.getAnt().getInfo())
           {aux = aux.getProx(); i++;}    
           else
           {
               swap(aux,aux.getAnt());
               aux = aux.getAnt(); i--;
           }   
       }    
    }
    
    public void TimSort()
    {
        int total = getQtd(), r = 32, i, tam, meio, dir, esq; // r= tam da partição 
        Lista temp = new Lista();
        for (i = 0; i < total; i+=r) 
            InsercaoDirTim(Posiciona(i,getInicio()),Posiciona(Menor((i+31),total),getInicio())); 
        for (tam = r; tam < total; tam = 2*tam) 
        { 
            for (esq = 0; esq < total; esq += 2*tam) 
            {
                meio = esq + tam - 1; 
                dir = Menor((esq + 2*tam - 1), (total-1)); 
                Fusao(temp,esq,meio,dir,getQtd());
            }
        }    
    }
}

    
    

