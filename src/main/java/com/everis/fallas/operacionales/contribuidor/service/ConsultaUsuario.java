package com.everis.fallas.operacionales.contribuidor.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.everis.fallas.operacionales.contribuidor.bean.Auditoria;
import com.everis.fallas.operacionales.contribuidor.bean.Contributor;
import com.everis.fallas.operacionales.contribuidor.bean.Request;
import com.everis.fallas.operacionales.contribuidor.bean.Response;
import com.everis.fallas.operacionales.contribuidor.dao.FallasDB;
import com.everis.fallas.operacionales.contribuidor.dao.FallasDBImpl;
import com.everis.fallas.operacionales.contribuidor.util.Repository;
import com.ibm.team.repository.client.IContributorManager;
import com.ibm.team.repository.client.ITeamRepository;
import com.ibm.team.repository.common.IContributor;
import com.ibm.team.repository.common.TeamRepositoryException;

@Path("/contributor")
public class ConsultaUsuario {
	@POST
	@Path("/search")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Contributor> buscarContribuidor(Request request){
		List<Contributor> response = new ArrayList<Contributor>();
		Auditoria auditoria = request.getAuditoria();
		Repository conexion = new Repository(auditoria);
		try {
			conexion.login();
			response = run(request.getName(), conexion);
		} catch (TeamRepositoryException e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.logout();
		}
		return response;
	}
	@GET
	@Path("/v2/search")
	@Consumes({MediaType.TEXT_PLAIN})
	@Produces({MediaType.APPLICATION_JSON})
	public Response buscarContribuidor2(@QueryParam("q") String search){
		Response response = new Response();
		FallasDB db = new FallasDBImpl();
		try {
			List<Contributor> contribuidores = db.obtenerContribuidor(search);
			response.setContribuidores(contribuidores);
			response.setErrorCode("0");
			response.setErrorMsg("Consulta Exitosa");	
		} catch (Exception e) {
			response.setErrorCode("1");
			response.setErrorMsg(e.getMessage());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private List<Contributor> run(String name, Repository conexion) throws TeamRepositoryException {
		ITeamRepository teamRepository = conexion.getTeamrepo();
		IProgressMonitor monitor = new NullProgressMonitor();
		IContributorManager icm = teamRepository.contributorManager();
		List<Contributor> response = new ArrayList<Contributor>();
		Contributor contributor = new Contributor();
		try {
			IContributor c = icm.fetchContributorByUserId(name, monitor);
			contributor.setCodigo(c.getUserId());
			contributor.setNombre(c.getName());
			response.add(contributor);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			java.util.List<IContributor> temp = icm.fetchAllContributors(monitor);
			for (IContributor iContributor : temp) {
				String s1=iContributor.getName().toLowerCase();
				String s2=name.toLowerCase();
				if (s1.contains(s2)) {
					System.out.println(iContributor.getUserId());
					contributor = new Contributor();
					contributor.setCodigo(iContributor.getUserId());
					contributor.setNombre(iContributor.getName());
					response.add(contributor);
				}
			}
		}
		return response;
	}
}
