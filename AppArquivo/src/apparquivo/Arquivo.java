/* @author: ITALO CESAR PIOVAN ROCHA  */
 

package apparquivo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arquivo
{
    private int qtdReg = 512;
    private RandomAccessFile pArq;
    private int comp,mov; // contadores para tabela 
    
    
    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
    }
    
    public RandomAccessFile getFile() {
        return pArq;
    }
    
    public Arquivo(String nomearquivo) // p/ abrir arquivo 
    {
        try
        {
            pArq = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        { }
    }
    public Arquivo(RandomAccessFile arq)
    {
        if(arq != null)
            pArq = arq;
    }
   
     public void exibirArq() // exibe conteudo arquivo 
    {
        int i;
        Registro reg = new Registro();
        seekArq(0);
        i = 0;
        while (!eof())
        {
            System.out.println("Posicao " + i);
            reg.leDoArq(pArq);
            reg.exibirReg();
            i++;
        }
    }
    
    public void copiaArquivo(Arquivo arqDestino) // copia arquivo .origem -> destino
    {
        Registro reg = new Registro();
        seekArq(0);
        arqDestino.seekArq(0);
        while (!eof())
        {
            reg.leDoArq(pArq);
            reg.gravaNoArq(arqDestino.getFile());
        } 
    }

    public void truncate(int pos) //desloca eof
    {
        try
        {
            pArq.setLength(pos * Registro.length());
        } catch (IOException exc)
        { }
    }
 
    public boolean eof()  //verifica se o ponteiro esta no <EOF> do arquivo
    {
        boolean retorno = false;
        try
        {
            if (pArq.getFilePointer() == pArq.length())
                retorno = true;                               
        } catch (IOException e)
        { }
        return (retorno);
    }
    
    public void seekArq(int pos) // posiciona pArq
    {
        try
        {
            pArq.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }
    
    public int filesize() // retorna int do tamanho do arquivo pArq
    {
        try{
            return (int)pArq.length() / Registro.length();   
        }catch(IOException e){}
        return 0;
    }
  
    public void initComp(){this.comp = 0;} // inicializa comp
    
    public void initMov(){this.mov = 0;}  // inicaliza mov

    /** METODOS AUXILIARES DAS ORDENAÇÕES ***/
    
    private void swap(int A,int B)
    {
        Registro reg = new Registro();
        int aux;
        seekArq(A);reg.leDoArq(pArq);
        aux = reg.getNumero(); 
        seekArq(B);reg.leDoArq(pArq); 
        seekArq(A); reg.gravaNoArq(pArq); 
        seekArq(B); reg.setNumero(aux);reg.gravaNoArq(pArq);
    }
    
    public void QuickSemPivo()
    {
        QuickSP(0,filesize()-1);
    }
    
    public void QuickComPivo()
    {
        QuickCP(0,filesize()-1);
    }
    
    private int BuscaBinaria(int chave,int TL)
    {
        int ini = 0,fim = TL - 1, meio = fim / 2;
        Registro reg = new Registro();
        seekArq(meio); reg.leDoArq(pArq);
        comp++;
        while(ini < fim && chave != reg.getNumero()) 
        {
            if(chave < reg.getNumero())
                fim = meio - 1;
            else
                ini = meio + 1;

            meio = (ini + fim) / 2; 
            seekArq(meio); reg.leDoArq(pArq);
            comp+=2; // 1 do while , 1 do if
        }
        comp++;// do if abaixo 
        if(chave > reg.getNumero())
            return meio+1;
        return meio;
    }
    
    private void ApagarArq(String nomearquivo)
    {
        File f = new File(nomearquivo);
        f.delete();
    }
    
    private void FecharArq(RandomAccessFile arq) 
    {
        try {
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private int MaiorElemento()
    {
        int maior=0;
        Registro reg = new Registro();
        
        for (int i = 0; i < filesize(); i++) 
        {
            seekArq(i);
            reg.leDoArq(pArq);
            comp++;
            if (reg.getNumero() > maior)
                maior = reg.getNumero();
        }
        return maior;
    }
    
    private void CountingParaRadix(int exp)
    {
        Registro reg = new Registro();
        int num,maior = 10, TL = filesize();
	int c[] = new int[maior+1];
        int b[] = new int[TL];
        
	for (int i = 0; i < TL; i++) 
        {
            seekArq(i);
            reg.leDoArq(pArq);
            num=reg.getNumero()-1;
           c[(num/exp)%10]+=1;
        }
		
	for (int i = 1; i < maior; i++) 
            c[i] += c[i-1];

        for (int i = TL - 1; i >= 0; i--)
        {
            seekArq(i);
            reg.leDoArq(pArq);
            num = reg.getNumero();
          
           b[c[((num -1)/exp)%10] -1] = num;
            
           c[((num -1)/exp)%10]--;
        }
	
        seekArq(0);		
        for(int i=0; i < TL ; i++)
        {
            reg.setNumero(b[i]);
            reg.gravaNoArq(pArq);
        }
    }
    
    private int Menor(int A,int B)
    {
        if(A < B)
            return A;
        return B;
    }
    
    private void InsercaoDirTim(int ini,int fim)
    {
        int pos,TL = fim;
        Registro aux = new Registro();
        Registro reg = new Registro();
        for(int i=ini; i<TL;i++)
        {
            seekArq(i);
            aux.leDoArq(pArq);
            pos = i;
            seekArq(pos-1);
            reg.leDoArq(pArq);
            comp++;
            while(pos>0 && aux.getNumero()< reg.getNumero())
            { 
                reg.gravaNoArq(pArq);
                pos--;
                if(pos > 0)
                {
                    seekArq(pos-1);
                    reg.leDoArq(pArq);
                }
                comp++;
            }
            seekArq(pos);
            aux.gravaNoArq(pArq);
        }
    }
    
    /** FIM AUXILIARES DAS ORDENAÇÕES ***/
    
    /**** METODOS DE ORDENAÇÃO ******/ 
    
    public void InsercaoDireta()
    {
        int pos,TL = filesize();
        Registro aux = new Registro();
        Registro reg = new Registro();
        for(int i=1; i<TL;i++)
        {
            seekArq(i);
            aux.leDoArq(pArq);
            pos = i;
            seekArq(pos-1);
            reg.leDoArq(pArq);
            comp++;
            while(pos>0 && aux.getNumero()< reg.getNumero())
            {               
                reg.gravaNoArq(pArq); mov +=2;
                pos--;
                if(pos > 0)
                {
                    seekArq(pos-1);
                    reg.leDoArq(pArq);
                }
                comp++;
            }
            seekArq(pos);
            aux.gravaNoArq(pArq);
            System.out.println("COMP>"+comp+"MOV>"+mov);
        }
    }
    
    public void InsercaoBinaria()
    {
        int i = 1,aux,pos;
        Registro reg = new Registro();
       
	while(i < filesize())
	{
            seekArq(i); reg.leDoArq(pArq);
            aux = reg.getNumero();
            pos = BuscaBinaria(aux,i);
            
            for(int j = i; j>pos ; j--)
            {
                seekArq(j-1); reg.leDoArq(pArq);
                reg.gravaNoArq(pArq);
            }
            seekArq(pos);reg.setNumero(aux);
            reg.gravaNoArq(pArq);
            i++; mov+=2;
            System.out.println("COMP>"+comp+"MOV>"+mov);
	}
    }
    
    public void SelecaoDireta()
    {
        int menor,pos,TL = filesize();
        Registro reg = new Registro();
	
	for(int i = 0; i < TL -1; i++)
	{
            seekArq(i); reg.leDoArq(pArq);
            menor = reg.getNumero();
            pos = i;
            for(int j = i+1; j < TL; j++)
            {
                seekArq(j); reg.leDoArq(pArq);
                comp++;
                if(reg.getNumero() < menor) 
                {
                    menor = reg.getNumero();
                    pos = j; mov+=2;
                }
            }
            System.out.println("COMP>"+comp+"MOV>"+mov);
            seekArq(i); reg.leDoArq(pArq);
            seekArq(pos); reg.gravaNoArq(pArq);
            
            reg.setNumero(menor);
            seekArq(i); reg.gravaNoArq(pArq);
	}
    }
   
    public void BubbleSort()
    {
        int i, TL = filesize();

        Registro aux = new Registro();
        Registro reg = new Registro();

        while( TL > 1 )
        {
            for (i = 0; i < TL -1;i++)
            {
                seekArq(i);
                aux.leDoArq(pArq);
                reg.leDoArq(pArq);
                comp++;
                if( aux.getNumero() > reg.getNumero())
                {swap(i,i+1);mov +=2;}
            }
            TL--;
            System.out.println("COMP>"+comp+"MOV>"+mov);
        }
    }

    public void ShakeSort()
    {
        int inicio=0,fim = filesize()-1,i,aux;
        Registro reg = new Registro();
	
	while( inicio < fim )
        {
            for(i = inicio; i < fim; i++ )
            {
                seekArq(i); reg.leDoArq(pArq);
                aux = reg.getNumero();
                reg.leDoArq(pArq);
                comp++;
                if( aux > reg.getNumero())
                {swap(i+1,i); mov +=2;}
            }      
            fim--;
            for(i = fim; i > inicio; i--)
            {
                seekArq(i); reg.leDoArq(pArq);
                aux = reg.getNumero();
                seekArq(i-1);reg.leDoArq(pArq);
                comp++;
                if(aux < reg.getNumero())
                {swap(i-1,i); mov +=2;}
            }
            inicio++;
            System.out.println("COMP>"+comp+"MOV>"+mov);
        }
    }
    
    public void ShellSort()
    {
        int dist = 4, i, j, k, aux;
        Registro reg = new Registro();
	while(dist>0)
	{
            for(i=0; i<dist; i++)
            {
                j = i;
                while(j+dist < filesize())
                {   
                    seekArq(j);reg.leDoArq(pArq);
                    aux = reg.getNumero();
                    seekArq(j+dist);reg.leDoArq(pArq);
                    comp++;
                    if(aux > reg.getNumero())
                    {
                        swap(j,j+dist);mov +=2;
                        k = j;
                        if(k-dist >= 0)
                        {
                            seekArq(k);reg.leDoArq(pArq);
                            aux = reg.getNumero();
                            seekArq(k-dist);reg.leDoArq(pArq);
                            comp++;
                            while(k-dist >= i && aux < reg.getNumero())
                            {
                                swap(k,k-dist);mov +=2;
                                k-=dist; 
                                if(k-dist >= 0)
                                {
                                    seekArq(k);reg.leDoArq(pArq);
                                    aux = reg.getNumero();
                                    seekArq(k-dist);reg.leDoArq(pArq);
                                }
                                comp++;
                            }                        
                        }
                    }
                    j+=dist;
                }
            }
            dist/=2;
            System.out.println("COMP>"+comp+"MOV>"+mov);
	}
    }
    
    public void HeapSort()
    {
        int TL = filesize(), aux, posMaior;
	int pai, fe, fd;
        Registro reg = new Registro();
        
	while(TL>1)
	{
            pai = TL/2-1;
            while(pai >= 0)
            {
                fe = pai+pai+1;
                fd = fe+1;
                seekArq(fd); reg.leDoArq(pArq);
                aux = reg.getNumero();
                seekArq(fe); reg.leDoArq(pArq);
                comp++;
                if(fd<TL && aux > reg.getNumero())
                    posMaior = fd;
                else
                    posMaior = fe;
                
                seekArq(posMaior); reg.leDoArq(pArq);
                aux = reg.getNumero();
                seekArq(pai); reg.leDoArq(pArq);
                comp++;
                if(aux > reg.getNumero())
                {swap(posMaior,pai);mov +=2;}
                pai--;
            }
            TL--;
            swap(TL,0);mov +=2;
            System.out.println("COMP>"+comp+"MOV>"+mov);
	}
    }
    
    private void QuickSP(int ini,int fim)
    {
        int i = ini,j=fim;
        Registro regi = new Registro();
        Registro regj = new Registro();
        boolean flag = true;

        while(i < j)
        {
            seekArq(i);
            regi.leDoArq(pArq);
            seekArq(j);
            regj.leDoArq(pArq);
            if(flag)
            {
                comp++;
                while(i< j && regi.getNumero()<= regj.getNumero())
                {
                    comp++; i++;
                    if(i < j )
                    {
                        seekArq(i);
                        regi.leDoArq(pArq);
                    }
                }
            }    
            else
            {
                comp++;
                while(i < j && regj.getNumero()>= regi.getNumero())
                {
                    comp++; j--;
                    seekArq(i);
                    regj.leDoArq(pArq);
                }
            }
            if(i<j)
            {
                seekArq(j);
                regi.gravaNoArq(pArq);
                seekArq(i);
                regj.gravaNoArq(pArq);
            }
            flag = !flag;
            System.out.println("COMP>"+comp+"MOV>"+mov);
        }	
        if(ini<i-1)
            QuickSP(ini,i-1);
        if(j+1 < fim)
            QuickSP(j+1,fim);
    }
    
    private void QuickCP(int ini,int fim)
    { 
        int i=ini,j=fim,pivo;
	Registro reg = new Registro();
        seekArq((ini+fim)/2);reg.leDoArq(pArq);
        pivo = reg.getNumero();
	while(i<j)
	{
            seekArq(i);reg.leDoArq(pArq);
            comp++;
            while(reg.getNumero() < pivo)
            {
                i++;
                seekArq(i);reg.leDoArq(pArq);
                comp++;
            }   
            
            seekArq(j);reg.leDoArq(pArq);
            comp++;
            while(reg.getNumero() > pivo)
            {
                j--;
                seekArq(j);reg.leDoArq(pArq);
                comp++;
            }
                
            if(i<=j)
            {
                swap(i,j);mov +=2;
                i++;j--;
            }
            System.out.println("COMP>"+comp+"MOV>"+mov);
	}
	
	if(ini<j)
            QuickCP(ini,j);
	if(i<fim)
            QuickCP(i,fim);        
    }
    
    /* *******  1º IMPLEMENTAÇÃO MERGESORT ********* */

    private void Particao(Arquivo pt1,Arquivo pt2)
    {
        int meio = filesize()/2;
        Registro reg = new Registro();
        seekArq(0);
        pt1.seekArq(0);
        for(int i =0 ; i < meio;i++)
        {
            reg.leDoArq(pArq); reg.gravaNoArq(pt1.getFile());
        }
        
        for(pt2.seekArq(0); meio > 0;meio--)
        {
            reg.leDoArq(pArq);reg.gravaNoArq(pt2.getFile()); 
        }
    }

    private void Fusao(Arquivo pt1,Arquivo pt2, int seq)
    {
        int i = 0, j = 0,t_seq = seq;
        Registro reg = new Registro();
        Registro regi = new Registro();
        Registro regj = new Registro();
        seekArq(0);
        while(!eof())
        {
            while(i < seq && j < seq)
            {  
                pt1.seekArq(i); regi.leDoArq(pt1.getFile());
                pt2.seekArq(j); regj.leDoArq(pt2.getFile());
                comp++;
                if(regi.getNumero() < regj.getNumero())
                {reg.setNumero(regi.getNumero());i++;} 
                else
                {reg.setNumero(regj.getNumero());j++;}

                reg.gravaNoArq(pArq);  
            }
            pt1.seekArq(i);
            while(i < seq)
            {
                regi.leDoArq(pt1.getFile()); i++;
                reg.setNumero(regi.getNumero()); 
                reg.gravaNoArq(pArq);
            }
            
            pt2.seekArq(j); 
            while(j < seq)
            {
                regj.leDoArq(pt2.getFile()); j++; 
                reg.setNumero(regj.getNumero()); 
                reg.gravaNoArq(pArq);
            }
            seq = seq + t_seq;
        }
    }

    public void MergeSort()
    {
        int seq=1,TL = filesize();
        String pt1 = "part1.dat";
        String pt2 = "part2.dat";
        
        Arquivo part1 = new Arquivo(pt1);
        Arquivo part2 = new Arquivo(pt2);
       
        while(seq < TL)
        {
            Particao(part1, part2);
            Fusao(part1, part2, seq);
            seq = seq * 2;
            System.out.println("COMP>"+comp+"MOV>"+mov);
        }
        FecharArq(part1.getFile()); ApagarArq(pt1);
        FecharArq(part2.getFile()); ApagarArq(pt2);  
    }
    /* ******* FIM 1º IMPLEMENTAÇÃO MERGESORT ********* */
    
    /* *******  2º IMPLEMENTAÇÃO MERGESORT ********* */
    
    private void FusaoSegImp(Arquivo arqTemp, int ini1, int fim1, int ini2, int fim2)
    {
	int i = ini1, j = ini2,k=0;
        
	Registro regi= new Registro();
        Registro regj= new Registro();
        
        while(i <= fim1 && j<=fim2)
        {
            seekArq(i);regi.leDoArq(pArq);
            seekArq(j);regj.leDoArq(pArq);
            arqTemp.seekArq(k);
            comp++;
            if(regi.getNumero() < regj.getNumero())
            {regi.gravaNoArq(arqTemp.getFile());i++;}     
            else
            {regj.gravaNoArq(arqTemp.getFile());j++;}  
            k++;
        }
        
        seekArq(i);
	while(i<=fim1)
        {
            regi.leDoArq(pArq); i++;
            regi.gravaNoArq(arqTemp.getFile());k++;
        }
        
        seekArq(j);
	while(j<=fim2)
        {
            regj.leDoArq(pArq); j++;
            regj.gravaNoArq(arqTemp.getFile());k++;
        }
       
	for(i = 0; i < k; i++)
        {
            arqTemp.seekArq(i);regi.leDoArq(arqTemp.getFile());
            seekArq(i+ini1); regi.gravaNoArq(pArq);
        }
    }

    private void Merge(Arquivo arqTemp,int esq,int dir)
    {
        int meio ; 
        if(esq < dir)
        {
            meio = (esq + dir) / 2;
            Merge(arqTemp,esq,meio);
            Merge(arqTemp,meio+1,dir);
            FusaoSegImp(arqTemp,esq,meio,meio+1,dir);
            System.out.println("COMP>"+comp+"MOV>"+mov);
        }
    }

    public void MergeSortSegImp()
    {
        String nometemp = "arqTemp.dat";
        Arquivo arqTemp = new Arquivo(nometemp);
        Merge(arqTemp,0,filesize()-1);
        FecharArq(arqTemp.getFile()); ApagarArq(nometemp);
    }
    
    /* ******* FIM 2º IMPLEMENTAÇÃO MERGESORT ********* */
   
    public void CountingSort()
    {
        Registro reg = new Registro();
        int num, maior = MaiorElemento(), TL = filesize();
	int c[] = new int[maior];
        int b[] = new int[TL];
        
	for (int i = 0; i < TL; i++) // controla frequência
        {
            seekArq(i);
            reg.leDoArq(pArq);
            num=reg.getNumero()-1;
            c[num]+=1;
        }
		
	for (int i = 1; i < maior; i++) // acumula 
            c[i] += c[i-1];
		
	for (int i = 0; i < TL; i++) // monta vetor b ordenado 
        {
            seekArq(i);
            reg.leDoArq(pArq);
            num = reg.getNumero();
            b[c[num -1] -1] = num;
            c[num -1]--;
        }
	seekArq(0);	
	for (int i = 0; i < TL; i++) // escreve no arquivo ordenado
        {
            reg.setNumero(b[i]);
            reg.gravaNoArq(pArq);
           // mov++;
        }
    }
     
    public void BucketSort()
    {
        int num, qtdbaldes = 3;
        Registro reg = new Registro();
        
        Arquivo balde0 = new Arquivo("balde0"); // 3 arquivos que servirão de baldes
        Arquivo balde1 = new Arquivo("balde1");
        Arquivo balde2 = new Arquivo("balde2");
        
        Arquivo baldes[] = new Arquivo[qtdbaldes]; // mapeia os baldes 
        baldes[0] = balde0;baldes[1] = balde1;baldes[2] = balde2;
        
        seekArq(0);
        while(!eof()) // separa elementos nos respectivos baldes 
        {
            reg.leDoArq(pArq);
            num = reg.getNumero();
            
            if( num >=1 && num <= 33)
                reg.gravaNoArq(balde0.getFile());
            else
                if( num >=34 && num <= 66)
                    reg.gravaNoArq(balde1.getFile());
                else
                    reg.gravaNoArq(balde2.getFile());
        }
        
        for(int i = 0 ; i < qtdbaldes; i++) // ordena baldes 
            if(baldes[i].filesize() >= 1)
                baldes[i].InsercaoDireta();
        
        seekArq(0);
        for(int i = 0 ; i < qtdbaldes; i++) // Concatena baldes ordenados no arq principal 
        {
            if(baldes[i].filesize() >= 1)
            {   
                baldes[i].seekArq(0);
                while(!baldes[i].eof())
                {
                    reg.leDoArq(baldes[i].getFile());
                    reg.gravaNoArq(pArq);    
                }
            }
        }   
        FecharArq(balde0.getFile()); ApagarArq("balde0");
        FecharArq(balde1.getFile()); ApagarArq("balde1");
        FecharArq(balde2.getFile()); ApagarArq("balde2");
    }
    
    public void RadixSort()
    {
        int maior = MaiorElemento();
        for(int exp =1; maior / exp >0 ; exp *=10) 
            CountingParaRadix(exp); 
    }
    
    public void CombSort()
    {
        int TL = filesize(), gap = TL;
        Registro regi = new Registro();
        Registro regg = new Registro();

        while(gap > 1)
        {
            gap = (gap*10)/13;// 1.3 ... 
            for(int i = 0; i < TL-gap; i++)
            {
                seekArq(i); regi.leDoArq(pArq);
                seekArq(i+gap);regg.leDoArq(pArq);
                comp++;
                if(regi.getNumero() > regg.getNumero())
                {swap(i,i+gap);mov +=2;}
            }
            System.out.println("COMP>"+comp+"MOV>"+mov);
        }    
    }
    
    public void GnomeSort()
    {
        int i = 0,TL = filesize();
        Registro regi = new Registro();
        Registro regj = new Registro();
       
        while(i < TL)
        {
           if(i == 0)
            i++;
        
            seekArq(i);regi.leDoArq(pArq);
            seekArq(i-1);regj.leDoArq(pArq);
            comp++;
            if(regi.getNumero() >= regj.getNumero())
                i++;
            else
            {
                swap(i,i-1);mov +=2;
                i--;
            } 
            System.out.println("COMP>"+comp+"MOV>"+mov);
        }
        
    }
    
    public void TimSort()
    {
        int total = filesize(), r = 32, i, tam, meio, dir, esq; // r= tam da partição 
        Arquivo temp = new Arquivo("temp.dat");
        for (i = 0; i < total; i+=r) 
            InsercaoDirTim(i, Menor((i+31),(total))); 

        for (tam = r; tam < total; tam = 2*tam) 
        { 
            for (esq = 0; esq < total; esq += 2*tam) 
            { 
                meio = esq + tam - 1; 
                dir = Menor((esq + 2*tam - 1), (total-1)); 
                FusaoSegImp(temp, esq, meio, dir,filesize()); 
            } 
            System.out.println("COMP>"+comp+"MOV>"+mov);
        } 
        FecharArq(temp.getFile()); ApagarArq("temp.dat");
    }
    
    /**** FIM METODOS DE ORDENAÇÃO ******/ 
    
    
    private void geraArquivoOrdenado(Arquivo arq)
    {
        copiaArquivo(arq);
        arq.CountingSort(); // usando Counting pois é mais eficiente 
    } 
    private void geraArquivoReverso(Arquivo arqOrd) 
    {
        Registro reg = new Registro();
        seekArq(0);
        for(int i = arqOrd.filesize()-1; i > 0 ; i--)
        {
            arqOrd.seekArq(i);
            reg.leDoArq(arqOrd.getFile());
            reg.gravaNoArq(pArq);
        } 
        
    }  
    public void geraArquivoRandomico()
    {
        Registro reg = new Registro();
        Random rand = new Random();
        for (int i = 0; i < qtdReg; i++) 
        {
            reg.setNumero(rand.nextInt(100)+1);
            reg.gravaNoArq(pArq);
        }
    } 
    
    public void ConfiguraArquivos(Arquivo arqRand,Arquivo arqOrd,Arquivo arqRev)
    {
        System.out.println("Copiando arquivo base para arquivo randômico...");
        copiaArquivo(arqRand);
        System.out.println("ARQUIVO RANDÔMICO gerado com sucesso.");
        
        System.out.println("Gerando arquivo ordenado...");
        geraArquivoOrdenado(arqOrd);
        System.out.println("ARQUIVO ORDENADO gerado com sucesso.");
        
        System.out.println("Gerando arquivo reverso...");
        arqRev.geraArquivoReverso(arqOrd);
        System.out.println("ARQUIVO REVERSO gerado com sucesso.");
    }
}
