
<section id="sectionDoodle" class="section">
	<h1 class="h1"> Doodle </h1>
	<article class="function">
	<div class="line">
			<div class="line inline" id="funcDate">
			<div class="inline" id="champsDates">
					<h4> Add a column : </h4> 
					<input id="date" type="radio" name="text" value="Du au">  
				 	From : <input type="text" id="debutC" class="datepick" onfocus="checkRadio('date')">  
				 	to : <input type="text" id="finC" class="datepick" onfocus="checkRadio('date')">
				 	</br> 
					<input id="text" type="radio" name="text" value="text"> 
					Column title : <input class="textGrey" type="texte" id="textCol" value="column title" onfocus="checkRadio('text'); inputTextFocus('textCol', 'green')" onblur="inputTextBlur('textCol','column title')"> 
			</div>
			
			<div class="inline" id="butAddCol">
				<div class="button3D b3green" id="addCol" name="addColumn" onmouseover="changeCursor('addCol')" onclick="addColumn(); emptyText('textCol', 'debutC','finC');  inputTextBlur('textCol','titre colonne');"> 
					OK
				</div>
			</div>
			</div>
			
			<div class="line inline" id="funcPers">
			<div class="inline namePers">
				<input class="textGrey " id="name" type="text" name="name" value="add person" onfocus="inputTextFocus('name','Orange')" onblur="inputTextBlur('name','add person')" >
			</div>
			
			<div class="inline">
			
				<div class="button3D b3green" id="addPers" onmouseover="changeCursor('addPers')" onclick="addRow()"> 
					Add 
				</div>
			</div>
			</div>
			
		
	</div> <!--fin div line-->

	</article>
	
   <h2>Wishes of the group</h2>
	
	<article class="table">
	<TABLE id="tab" BORDER="1"> 
		<CAPTION> Doodle table </CAPTION> 
		<THEAD>
			<TR> 
			 <TH> Names </TH> 
		</THEAD>
		
		<TBODY>
			
			
		</TBODY>
	</TABLE>


</article>
</section>



<style type="text/css">
.ui-datepicker {
	    background: green;
	        border: 5px solid lime;
		border-radius:20px;
		width: 50vw;
		    color: black;
 	font-size:100%;

  margin-left: 5px;
}
</style>


<script src="JS/doodle.js" type="text/javascript"></script>
<script src="JS/doodleTab.js" type="text/javascript"></script>
