<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="sc"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:template>
	<div class=" col-md-9 col-lg-9">
		<div class="panel panel-default">
			<div class="panel-heading">Informations g�n�rales</div>
			<div class="panel-body">
			<table class="table table-user-information">
			<tbody>
			<tr>
			<td>Nombre de chaines/ segment:</td><td>${nbreChaines }</td>
			</tr>
			<tr>
			<td>Nombre de syst�mes install�s/segment :</td><td>${nbSysteme}</td>
			</tr>
			</tbody>
			</table>
			</div>
		</div>
	</div>

</t:template>