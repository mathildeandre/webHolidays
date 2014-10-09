
<section id="sectionExpenses" class="section">
   <h1 class="h1 text3Dfonce">Calcul of your expenses</h1>
 <article>
   <h2 class="text3Dfonce" >Who's paid what for whom?</h2>
<div id="divTab">

<TABLE id="tab" BORDER="1" onmouseout="calculationResult()"> 
		<CAPTION> Table of expenses </CAPTION> 
		<THEAD >
			<TR> 
			 <TH> Expense of </TH> 
			 <TH> Total </TH> 
			</TR>
		</THEAD>
		<TBODY id="tbody">
			
		</TBODY>
</TABLE> 

		</br></br>
	<!--<div>
	<input type="button" name="addRow" value="add new row" onclick="addRow()">
	</div>-->
	<input id="addRow" type="submit" value="Add new row" class="newButton3D buttonRedFonce" onmouseover="changeCursor('addRow')" onclick="addRow()"/>
       	


		<!-- --This part shouldn't appear in the real group version--
		</br></br>
		<div class="line">
		<input class="textGrey inline" id="name" type="text"  value="add an extra person" onfocus="inputTextFocus('name','Red')" onblur="inputTextBlur('name','add an extra person')" >
		<div class="button3D b3red" id="b2" onmouseover="changeCursor('b2')" onclick="addExtraPerson()"> Add an extra person </div>
		</div>
		-->
		</br>
</div>


	<div class="right" >
		<input  id="theCheckBoxMouseOver" type="checkbox" width="100%" height="200px">
		<label for="theCheckBoxMouseOver" > MouseOver actif ?</label>
	</div>
	<div id="buttonsBottom">
		<input id="emptyTab" type="submit" value="Empty the tab" class="newButton3D buttonRedFonce right" onmouseover="changeCursor('emptyTab')" onclick="reset()"/>
       <!--<input  type="button" name="reset" value="Clear the group" onclick="location.href='groupe.html';">-->
	  </br>
	  
	  
	  <!--  NOUS N'AVONS PLUS BESOIN DU BOUTON SHOW RESULT... -->
		 <!--<div class="button3D b3yellow" id="showResult" onmouseover="changeCursor('showResult')" onclick="calculationResult()">SHOW RESULTS</div>
		 <div class="button3D b3yellow" id="af" onmouseover="changeCursor('af')" onclick="afficheCell()"> affiche cell</div>
		-->
	</div>
	<!--
	<div>	
		</br></br>
		<input type="button" name="parcoursTab" value="parcours Tab" onclick="parcoursTab()">
		<input type="button" name="deleteDuplicate" value="deleteDuplicate" onclick="deleteDuplicate()">
	</div>
	-->

	<!--<div class="right">	
		<input   type="button" name="reset" value="Empty the tab" onclick="reset()">
	</div>
	<div>	
		</br></br>
		<input type="button" name="calculationResult" value="SHOW RESULTS" onclick="calculationResult()">
	</div>-->
		<fieldset> 
	    <legend><h2 class="text3Dfonce" >Result</h2></legend>
	    <div id="resultExpenses" class="aroundWhite">
		</div>
		</fieldset>

 </article>
</section >
<script>
$(document).click(function(e) {
	alert("baaa");
    // e.target is the element which has been clicked.
});
</script>
<script src="JS/expenses3.js" type="text/javascript"></script>
<script src="JS/expensesResult3.js" type="text/javascript"></script>
