
<section >
   <h1>Calcul of your expenses</h1>
 <article>
   <h2>Who's paid what for whom?</h2>
<div id="divTab">

<TABLE id="tab" BORDER="1"> 
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
	<div class="button3D b3red" id="b1" onmouseover="changeCursor('b1')" onclick="addRow()"> Add new row </div>


		</br></br>


		<div class="line">
		<input class="textGrey inline" id="name" type="text"  value="add an extra person" onfocus="inputTextFocus('name','Red')" onblur="inputTextBlur('name','add an extra person')" >
		<div class="button3D b3red" id="b2" onmouseover="changeCursor('b2')" onclick="addExtraPerson()"> Add an extra person </div>
		</div>
<!--

		<input class="textGrey" id="name" type="text" name="nameExtraPerson" value="add an extra person" onfocus="inputTextFocus('name')" onblur="inputTextBlur('name','add an extra person')" > 
		<input type="button" name="addExtraPerson" value="add an extra person" onclick="addExtraPerson()">
-->
		</br>
</div>


	<div class="right" >
		<input  id="theCheckBoxMouseOver" type="checkbox" width="100%" height="200px">
		<label for="theCheckBoxMouseOver" > MouseOver actif ?</label>
	</div>
	<div id="buttonsBottom">
		<div class="button3D b3pink right" id="buttonReset" onmouseover="changeCursor('buttonReset')" onclick="reset()"> Empty the tab </div>
		<!--<input  type="button" name="reset" value="Clear the group" onclick="location.href='groupe.html';">-->
	  </br>
		<div class="button3D b3yellow" id="showResult" onmouseover="changeCursor('showResult')" onclick="calculationResult()">SHOW RESULTS</div>
		<div class="button3D b3yellow" id="af" onmouseover="changeCursor('af')" onclick="afficheCell()"> affiche cell</div>
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


 </article>
</section >

<script src="JS/expenses.js" type="text/javascript"></script>
<script src="JS/expensesResult.js" type="text/javascript"></script>
