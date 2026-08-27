class No {
  #dado;
  #proximo;
  #anterior;

  constructor(novoDado) {
    this.#dado = novoDado;
    this.#anterior = null
    this.#proximo = null;
  }
  get dado() {
    return this.#dado;
  }
  set dado(novoDado) {
    this.#dado = novoDado;
  }

  get proximo() {
    return this.#proximo;
  }
  set proximo(novoProximo) {
    this.#proximo = novoProximo;
  }

  get anterior() {
    return this.#anterior;
  }

  set anterior(novoAnterior) {
    this.#anterior = novoAnterior;
  }


  toString() {
    return `<-| dado: ${this.#dado} |->`;
  }
}
