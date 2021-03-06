<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="sc"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:template>
	<div class=" col-md-9 col-lg-9">
		<div class="panel panel-default">
			<div class="panel-heading">Informations générales</div>
			<div class="panel-body">
				<table class="table table-user-information">
					<tbody>
						<tr>
							<td><strong>Total Segment installé :</strong></td>
							<td>${nbreSegment }</td>
						</tr>
						<tr>
							<td><strong>Total Chaine installé :</strong></td>
							<td>${nbreChaine}</td>
						</tr>
					</tbody>
				</table>
				<hr class="divider">
				<strong>Total Chaine par Segment</strong>
				<table class="table table-user-information">
				</table>
			</div>
		</div>
	</div>

</t:template>