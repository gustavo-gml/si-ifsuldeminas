const minhaLista = new LinkedList();
let indiceSelecionado = null;
//---------------------------------------------------------------------------------------------
function limpaInputs() {
  document.getElementById("txtnovaTarefa").value = "";
  document.getElementById("txtnovaPrioridade").value = "";
  document.getElementById("txtnovaTarefa").focus();
}
//--------------------------------------------------------------------------------------------
function leiaDadosTarefa() {
  const descricao = document.getElementById("txtnovaTarefa").value.trim();
  const prioridade = document.getElementById("txtnovaPrioridade").value.trim();
  const numeroPrioridade = parseInt(prioridade, 10);

  if (descricao === "" || prioridade === "" || isNaN(numeroPrioridade)) {
    alert("Preencha os campos de descrição e prioridade!");
    return null;
  }
  return new Tarefa(descricao, numeroPrioridade, obterDataAtual(), obterHoraAtual());
}
/*--------------------------------------------------------------------------------------------
function adicionarElementoInicio() {
  const novaTarefa = leiaDadosTarefa()
  minhaLista.addFirst(novaTarefa);
  atualizarLista();
  alert("Inserido com sucesso !");
  limpaInputs();
}
------------------------------------------------------------------------------------------------------
function adicionarElementoFinal() {
  const novaTarefa = leiaDadosTarefa();
  minhaLista.addLast(novaTarefa);
  atualizarLista();
  alert("Inserido com sucesso !");
  limpaInputs();
}
//--------------------------------------------------------------------------------------------
function adicionarIndice() {
  const novaTarefa = leiaDadosTarefa();
  const indice = document.getElementById("txtIndice")
  minhaLista.addAtIndex(novaTarefa,  indice.value);
  atualizarLista();
  limpaInputs();
}
//--------------------------------------------------------------------------------------------*/


//--------------------------------------------------------------------------------------------
function adicionarPorPrioridade() {
  const novaTarefa = leiaDadosTarefa();
  let result = false
  if(!minhaLista.isEmpty()){
    if(novaTarefa.prioridade >= minhaLista.getLast().prioridade)
    result =  minhaLista.addLast(novaTarefa);
    else if(novaTarefa.prioridade < minhaLista.getFirst().prioridade)
    result = minhaLista.addFirst(novaTarefa)
  }else{
    result = minhaLista.addFirst(novaTarefa);
  }
  
  //percorrer
  if(result){
    
    atualizarLista();
    limpaInputs();
    return result;
  }
    
  let pos = 0;

  for(const tarefa of minhaLista){
    //prioridade nova tarefa >= prioridade atual
    if(novaTarefa.prioridade >= tarefa.prioridade){
      pos++
    }
    else{
      break;
    }
    
  }
  minhaLista.addAtIndex(novaTarefa, pos);

  atualizarLista();
  limpaInputs();
}
//--------------------------------------------------------------------------------------------

// Função para remover o primeiro elemento da lista
function removerElementoInicio() {
  if (!minhaLista.isEmpty()) {
    const tarefaRealizada = minhaLista.removeFirst();
    mostrarMensagemRemocao(tarefaRealizada);
    atualizarLista();
  }
  else {
    alert("Lista de Tarefas Vazia");
  }

}
//--------------------------------------------------------------------------------------------
// Função para remover o ultimo elemento da lista
function removerElementoSelecionado() {
  if (indiceSelecionado != null) {
    const tarefaRealizada = minhaLista.removeAtIndex(indiceSelecionado);
    mostrarMensagemRemocao(tarefaRealizada);
    atualizarLista();
  }
  else {
    alert("Selecione um índice");
  }
  indiceSelecionado = null;
}

//--------------------------------------------------------------------------------------------
function mostrarMensagemRemocao(tarefaRealizada) {
  const mensagem = document.getElementById("mensagem-remocao");
  let dias = calcularDiferencaDias(tarefaRealizada.data, obterDataAtual()) 
  let horas = calcularDiferencaHoras(tarefaRealizada.hora, obterHoraAtual())
  mensagem.innerHTML = "Tarefa '" + tarefaRealizada.descricao +"' realizada em " + dias + ' dias e ' + horas + " horas";
  mensagem.style.display = "block";
}
//-------------------------------------------------------------------------------------------- 
// Função para atualizar a exibição da fila
function atualizarLista() {
  let indexAtual = 0;
  const listaTarefas =
    document.getElementById("list_listadeTarefas");
  const lblTarefas =
    document.getElementById("lblmostraTarefas");
  listaTarefas.innerHTML = "";    // limpar antes de mostrar
  if (!minhaLista.isEmpty()) {
    lblTarefas.innerHTML = "Lista de Tarefas";
    for (const tarefa of minhaLista) {
      const novaLinha = document.createElement("li");
      novaLinha.innerHTML = tarefa.toString();
      const posicaoDestaLinha = indexAtual;

      novaLinha.onclick = function() {
      indiceSelecionado = posicaoDestaLinha; 
      console.log("O usuário selecionou a tarefa na posição: " + indiceSelecionado);
    };
      listaTarefas.appendChild(novaLinha);
      indexAtual++;
    }
  }
  else {
    lblTarefas.innerHTML = "Lista de Tarefas Vazia";
  }

}
