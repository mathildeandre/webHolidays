<section id="sectionExpenses" class="section" >
   <h1 id="h1Expenses" class="h1">Calcul of your expenses</h1>  <!-- class="h1 text3Dfonce" -->
 <article>
   <h2 >Who's paid what for whom?</h2>
   


	<form method="post" action="expensesServlet?action=saveTab">    	
	
	
		<TABLE  id="tab" BORDER="1" onmouseout="calculationResult()"> 
		
	<input type="hidden" id="nbLineHidden" name="nbLineHidden" value="${sessionScope.expenses.size}" >
	<input type="hidden" id="nbMemberHidden" name="nbMemberHidden" value="${sessionScope.group.nbPerson}" >
	<div id="hiddenRow"></div>
				<CAPTION> Table of expenses </CAPTION> 
				<THEAD >
					<TR> 
					 <TH id="thExpenseOf" >Expense of</TH> 
					 <TH id="thTotal">Total</TH> 
					 
			     	<c:forEach var="member" items="${sessionScope.group.listMembers}" varStatus="nbCol">
			    		<th class="thTotal" > ${member.name}</th> 
			    		<input id="th${nbCol.index}" name="th${nbCol.index}" type="hidden" value="${member.id}">
			    		<input id="thName${nbCol.index}" name="thName${nbCol.index}" type="hidden" value="${member.name}">
			    	</c:forEach>
			    	
					 <TH >Everybody</TH> 
					 <TH >Description</TH>
					</TR>
				</THEAD>
				<TBODY id="tbody">
				
					<c:forEach var="rowExpenses" items="${sessionScope.expenses.listRowExpenses}" varStatus="nbLine">
						<tr>
						
							<input type="hidden" name="${nbLine.index}idRow" value="${rowExpenses.id}" >
							
							
							<td><select id="${nbLine.index}select" name="${nbLine.index}select">
									<c:forEach var="member" items="${sessionScope.group.listMembers}" >
										
										<c:set var="selected" value=""></c:set>
										<c:if test="${rowExpenses.buyer.id == member.id}">
											<c:set var="selected" value="selected"></c:set>
										</c:if>
										
										<option value="${member.id}-${member.name}" ${selected}>${member.name}</option>
										
									</c:forEach>
								</select>
							</td>
							
							
							<td><input name="${nbLine.index}total" class="textRed"  type="number" value="${rowExpenses.amount}"></td>
							
							<c:forEach var="member" items="${sessionScope.group.listMembers}" varStatus="nbCol" >
								<c:set var="benef" value="${member.id}"> </c:set>
								<td><input id="${nbLine.index}${nbCol.index}" name="${nbLine.index}${nbCol.index}" type="checkbox" 
								onclick="verifAllRow('${nbLine.index}')" 
								onmouseover="checkBoxMouseOver('${nbLine.index}${nbCol.index}')" ${rowExpenses.mapCheckBox[benef]} ></td>
							</c:forEach>
							<td></td>
							<td><textarea  class="textRed" id="${nbLine.index}descript" name="${nbLine.index}descript" 
								rows="1" cols="17"  >${rowExpenses.description}</textarea>
							</td>
							
						</tr>
			    	</c:forEach>
					
					
					
				</TBODY>
		</TABLE> 
		
		<input id="saveTab" type="submit" value="Save Tab" class="newButton3D buttonRedFonce right" 
				onmouseover="changeCursor('saveTab')"/>
	</form>
	
	
	
<div id="buttonsForTab">


	<input id="addRow" type="submit" value="Add new row" class="newButton3D buttonRedFonce" onmouseover="changeCursor('addRow')" onclick="addRow()"/>
       	
	<input id="displayINNER" type="submit" value="Display innerTab" class="newButton3D buttonRedFonce" onmouseover="changeCursor('displayINNER')" onclick="alert(document.getElementById('tab').innerHTML)"/>

	
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

<script src="JS/expenses7.js" type="text/javascript"></script>
<script src="JS/expensesResult5.js" type="text/javascript"></script>
		