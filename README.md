# DesafioGoDev

* Antes de qualquer coisa, verifique a sua versão do JDK. A versão recomendada para esse projeto é a 15.
* O projeto utiliza de persistencia de dados, portanto vai precisar utilizar um servidor na sua maquina. (Recomendo muito o XAMPP)
* Após realizar o download do servidor, starte-o e em seguida.
* Clone o repositório, e abra na IDE de preferencia.
Caso utilize INTELIJ: File > New > Project for Existing Sources > (diretorio com a aplicação) > marque a caixa "Import Project from external model" e Selecione o Eclipse. Depois é  só ir dando Next até o fim.
Caso utilize ECLIPSE: File > Import > General > Exisiting Projects > into Workspace. Deixe marcado "Select root diretory", e selecione o Projeto, clique em FINISH.
* Adicione o plugin connector do banco: 
NO ECLIPSE: clique com o botão direito no plugin que está dentro da pasta "CONECTOR" depois "Build Path > e caso tenha a opção "Add to Build Path" selecione-a, caso tenha apenas a opção "Configure Build Path" significa que o driver já está na biblioteca.
NO INTELLIJ: Filr > Project Structure > Libraries e clique em "+" e selecione o plugin que está na pasta "CONECTOR".
* Após feito os procedimentos, starte o serviço "CONFIGURACAO" primeiro, para eles criar ou iniciar as informações de banco. E logo depois pode iniciar o Programa.
