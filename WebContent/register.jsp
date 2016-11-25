<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Registration Page</title>
<s:head />

<!--<script type="text/javascript" src="js/filtro.js"> </script> -->

<link rel="stylesheet" href="style.css">
</head>
<body>
<div id="menu">
<h5>VERSÃO 1.0</h5>
<h1>SISRES ANIMAL</h1>
<h3>Manter Estabelecimento</h3>
</div>

<s:form action="saveOrUpdateEstabelecimento" id="formulario">
		<s:push value="estabelecimento">
		<s:hidden name="id"/>
		<s:textfield name="codigo" label="Código " id="codigo"/>
		<s:textfield name="descricao" label="Descrição " id="descricao" />
		<s:select name="situacao" list="{'Ativo','Inativo'}" headerKey="Ativo" headerValue="Selecionar" value="Ativo" label="Situação " id="situacao"/>
		<s:textfield onblur="pesquisacep(this.value);" name="cep" id="cep" label="CEP"/> 
		<s:textfield name="rua" id="rua" label="Rua " />
		<s:textfield name="bairro" id="bairro" label="Bairro " />
		<s:textfield name="cidade" id="cidade" label="Cidade " />
		<s:textfield name="uf" id="uf" label="UF " />
		<s:textfield name="ibge" id="ibge" label="IBGE " /> 
		<s:textfield name="createDate" id="createDate" label="Data de criação"/>
		<s:submit name="Gravar" value="Gravar" id="gravar"/>
		</s:push>
</s:form>

 <p>Buscar código: <input type="text" id="myInput" onkeyup="myFunction()"> </p>
 <div id="editor"></div>
	<button id="cmd">Gerar PDF</button>
<div id="content">  
<h2>Lista de Estabelecimentos</h2>

<s:if test="estabelecimentoList.size() > 0">
	<div class="content">
	<table class="estabelecimentoTable" cellpadding="5px" id="myTable">
		<tr class="even">
			<th>Código</th>
			<th>Situação</th>
			<th>Descrição</th>
			<th>CEP</th>
			<th>Rua</th>
			<th>Bairro</th>
			<th>Cidade</th>
			<th>UF</th>
			<!--  <th>Criação</th>  -->
			<th class="ignore">Editar</th>
			<th class="ignore">Deletar</th>
		</tr>
		<s:iterator value="estabelecimentoList" status="estabelecimentoStatus">
		<tbody>
			<tr	class="<s:if test="#estabelecimentoStatus.odd == true ">odd</s:if><s:else>even</s:else>">
				<td ><s:property value="codigo" /></td>
				<td><s:property value="situacao"  /></td>
				<td ><s:property value="descricao" /></td>
				<td ><s:property value="cep"/></td>
				<td ><s:property value="rua" /></td>
				<td ><s:property value="bairro" /></td>
				<td ><s:property value="cidade" /></td>
				<td ><s:property value="uf"/></td>
				<!--  <td ><s:property value="createDate"/></td> -->
				<td class="ignore"><s:url id="editURL" action="editEstabelecimento">
					<s:param name="id" value="%{id}"></s:param>
				</s:url> <s:a href="%{editURL}">Editar</s:a></td>
				<td class="ignore"><s:url id="deleteURL" action="deleteEstabelecimento">
					<s:param name="id" value="%{id}"></s:param>
				</s:url> <s:a href="%{deleteURL}">Deletar</s:a></td> 
			</tr>
		</tbody>
		</s:iterator>
	</table>
	</div>
</s:if>
</div>
<script type="text/javascript" src="js/componentes/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/date.js"> </script>
<script type="text/javascript" src="js/cep.js"> </script>
<script type="text/javascript" src="js/List.js"> </script>
<script type="text/javascript" src="js/pdfjs/jspdf.debug.js"> </script>
<script type="text/javascript" src="js/pdfjs/print.js"> </script>
<script type="text/javascript" src="js/pdfjs/jspdf.plugin.autotable.src.js"> </script>


</body>
</html>