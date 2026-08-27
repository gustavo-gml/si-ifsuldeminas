class LinkedList {
    #head;
    #tail;
    #qtd;


    constructor() {
        this.#head = null;
        this.#tail = null;
        this.#qtd = 0;
    }


    isEmpty() {
        return this.#head === null;
    }


    addFirst(novoDado) {
        const novoNo = new No(novoDado);

        if (this.isEmpty()) {
            this.#tail = novoNo;
        } else {
            novoNo.proximo = this.#head;
            this.#head.anterior = novoNo;
            
        }
        this.#qtd++;
        this.#head = novoNo; // evita dry 
        return true;
    }


    addLast(novoDado) {
        const novoNo = new No(novoDado);
        if (this.isEmpty()) {// vazia
            this.#head = novoNo;
        }
        else {
            //TODO
            novoNo.anterior = this.#tail;
            this.#tail.proximo = novoNo; //homem aranha de apontamentos
            
        }
        this.#tail = novoNo;
        this.#qtd++;
        return true;
    }


    removeFirst() {
        if (this.isEmpty())
            return null;

        const dadoRemovido = this.#head.dado;
        this.#head = this.#head.proximo;
        if (this.#head === null)
            this.#tail = null;
        else
            this.#head.anterior = null;

        this.#qtd--;
        return dadoRemovido;
    }

    removeLast() {
        if (this.isEmpty())
            return null;

        const dadoRemovido = this.#tail.dado;
        this.#tail = this.#tail.anterior;
        
        if(this.#tail === null){
            this.#head = null;
        }
        else
            this.#tail.proximo = null;
    
        this.#qtd--;
        return dadoRemovido;
        
    }

    addAtIndex(novoDado, index){
        if(this.isEmpty() || index <= 0)
            return this.addFirst(novoDado);
        if(index >= this.#qtd)
            return this.addLast(novoDado);

        const novoNo = new No(novoDado);

        let aux = this.#head;
        let posAtual = 0;
        while(posAtual < index - 1){
            aux = aux.proximo;
            posAtual++;
        }
        let aux2 = aux.proximo;

        novoNo.anterior = aux;
        novoNo.proximo = aux.proximo;

        aux.proximo = novoNo;
        //novoNo.proximo.anterior = novoNo;
        aux2.anterior = novoNo;
        this.#qtd++;
        return true;
    }

    removeAtIndex(index){
        if(this.isEmpty() || index < 0 || index >= this.#qtd)
            return null
        if(index === 0){
            return this.removeFirst();
        } else if(index === this.#qtd - 1){
            return this.removeLast();
        }
        let aux = this.#head;
        
        let posAtual = 0;
        while(posAtual < index){
            aux = aux.proximo;
            posAtual++;
        }

        
        
        aux.anterior.proximo = aux.proximo;
        aux.proximo.anterior = aux.anterior;
        this.#qtd--;

        return aux.dado;
    }


    get length() {
        return this.#qtd;
    }


    //-------------------------------------
    //Quando um objeto tem um iterator, ele pode ser iterado com construções como [ for(const item of minhaLista)*/


    [Symbol.iterator]() {
        let noAtual = this.#head;
        return {
            next: function () {
                if (noAtual !== null) {
                    let valor = noAtual.dado;
                    noAtual = noAtual.proximo;
                    return { value: valor, done: false };
                } else {
                    return { done: true };
                }
            }
        };
    }
    //—----------------
    toString() {
        let retorno = ""
        let noAtual = this.#head;

        while (noAtual != null) {
            retorno += "| " + noAtual.dado;
            noAtual = noAtual.proximo;
        }
        return retorno;
    }

    getLast(){
        if(!this.isEmpty())
            return this.#tail.dado;

        return null
    }

    getFirst(){
        if(!this.isEmpty())
            return this.#head.dado;
        
        return null
    }

}
