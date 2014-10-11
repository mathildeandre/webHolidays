
<section id="sectionExpenses" class="section">
   <h1 id="h1Expenses" class="h1">Calcul of your expenses</h1>  <!-- class="h1 text3Dfonce" -->
 <article>
   <h2 >Who's paid what for whom?</h2>
   
   
<TABLE id="tab" BORDER="1" onmouseout="calculationResult()"> 
		<CAPTION> Table of expenses </CAPTION> 
		<THEAD >
			<TR> 
			 <TH id="thExpenseOf">Expense of</TH> 
			 <TH id="thTotal">Total</TH> 
			 
	     	<c:forEach var="members" items="${sessionScope.group.listMembers}">
	    		<th class="thTotal" > ${members.name}</th> 
	    	</c:forEach>
			</TR>
		</THEAD>
		<TBODY id="tbody">
			
		</TBODY>
</TABLE> 

<div id="buttonsForTab">


	<input id="addRow" type="submit" value="Add new row" class="newButton3D buttonRedFonce" onmouseover="changeCursor('addRow')" onclick="addRow()"/>
       	

	<input id="saveTab" type="submit" value="Save Tab" class="newButton3D buttonRedFonce right" onmouseover="changeCursor('saveTab')" onclick="alert('faire fonction save')"/>
    
	<div id="mouseOver" class="right" >
		<input  id="theCheckBoxMouseOver" type="checkbox" width="100%" height="200px">
		<label for="theCheckBoxMouseOver" > MouseOver actif ?</label>
	</div>
    <!-- pour vider le tab
    <input id="emptyTab" type="submit" value="Empty the tab" class="newButton3D buttonRedClair right" onmouseover="changeCursor('emptyTab')" onclick="reset()"/>
     -->
</div>

		<fieldset id="blockFieldResult"> 
	    <legend><h2 >Result</h2></legend>
	    <div id="fieldResultExpenses" class="aroundWhite">
		</div>
		</fieldset>

 </article>
</section >

<script src="JS/expenses4.js" type="text/javascript"></script>
<script src="JS/expensesResult3.js" type="text/javascript"></script>
