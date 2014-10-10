
 
 	<section id="sectionNavDoodle">
 		<h2 id="h2navDoodle">Choose your doodle</h2>
		<ul>
		  <li><a href="#burgers">Doodle1</a></li>
		  <li><a href="#lasagne">Doodle2</a></li>
		  <li><a href="#contact">Contact</a></li>
		  <li><a href="#news">News</a></li>
		</ul>
		
		
		<div  class="aroundWhite">Create new doodle : </div>
		<form method="post" action="createDoodle">
	      <input id="inputNewDoodle" class="textGrey"  name="inputNewDoodle" type="text" value="Name doodle" 
         onfocus="inputTextFocus('inputNewDoodle', 'Green')" onblur="inputTextBlur('inputNewDoodle','Name doodle')" required >
		<input id="createDoodle" type="submit" value="Create" class="newButton3D buttonGreenClair" onmouseover="changeCursor('createDoodle')"/>
       	</form>
	</section>


<section id="sectionDoodle" class="section">
	<h1 id="h1Doodle" class="h1">Doodle</h1> <!-- class="h1 text3Dfonce" -->
	
	<article>
	   <h2>Wishes of the group</h2>
		
		<TABLE id="tab" BORDER="1"> 
			<CAPTION>My Title</CAPTION> 
			<THEAD>
				<TR> 
				 <TH> Names </TH> 
			</THEAD>
			
			<TBODY>
				
				
			</TBODY>
		</TABLE>
	</article>
	
	
	
	
	<article id="articleAddColumn" >
	
		<div id="divAddColumn" class="aroundWhite">Add a column : </div>
		
		<div class="line">
			<div class="inline">
				<div id="divDate" class="line">
					<input id="date" type="radio" name="text" value="Du au">  
					From : <input type="text" id="debutC" class="datepick textGreen" onfocus="checkRadio('date')">  
					to : <input type="text" id="finC" class="datepick textGreen" onfocus="checkRadio('date')">
				</div>
				
				
				<div id="divTitle" class="line">
					<input id="text" type="radio" name="text" value="text"> 
					Column title : <input id="textCol" class="textGrey" type="texte"  value="column title" onfocus="checkRadio('text'); inputTextFocus('textCol', 'Green')" onblur="inputTextBlur('textCol','column title')"> 
				</div>
			</div>
			<div class="inline">
				<input id="addColumn" type="submit" value="Add" class="newButton3D buttonGreenClair" onmouseover="changeCursor('addColumn')" onclick="addColumn(); emptyText('textCol', 'debutC','finC');  inputTextBlur('textCol','column title');"/>
			</div>
		</div>
		<!--
		<div id="funcDate" class="line">
			
				<div id="champsDates" class="inline" >
	       	
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
			
			-->
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


<script src="JS/doodle1.js" type="text/javascript"></script>
<script src="JS/doodleTab.js" type="text/javascript"></script>
