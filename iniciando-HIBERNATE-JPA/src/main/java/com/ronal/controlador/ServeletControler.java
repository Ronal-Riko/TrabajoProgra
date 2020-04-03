package com.ronal.controlador;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.ronal.model.Inventario;

/**
 * Servlet implementation class ServeletControler
 */
public class ServeletControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeletControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			
		//doGet(request, response);
		String acction = request.getParameter("btn");
		
		EntityManager em;
		EntityManagerFactory emf;
		emf= Persistence.createEntityManagerFactory("iniciando-HIBERNATE-JPA");
		em = emf.createEntityManager();
		
		Inventario pr = new Inventario();
		try {
			
			String id = request.getParameter("Id");
			String nombrepr = request.getParameter("Nproductos");
			String preciopr = request.getParameter("Pproductos");
			String cantidadpr = request.getParameter("Cproductos");
			String totalpr = request.getParameter("Tproductos");
			
			
			pr.setId(Integer.parseInt(id));
			pr.setNombreProducto(nombrepr);
			pr.setPrecioProducto(Double.parseDouble(preciopr));
			pr.setCantidadProducto(Integer.parseInt(cantidadpr));
			pr.setTotalProducto(Double.parseDouble(totalpr));
			pr= em.getReference(Inventario.class, pr.getId());	
			
		}catch (Exception e) {
			
		}
			
			if(acction.equals("Agregar")) {	
			
				em.getTransaction().begin();
				
				
				em.persist(pr);
				em.flush();
				em.getTransaction().commit();
				
				
			
		}else if(acction.equals("Eliminar")) {
			pr = em.getReference(Inventario.class, pr.getId());
			em.getTransaction().begin();
			em.remove(pr);
			em.flush();
			em.getTransaction().commit();
			
		}else if(acction.equals("Modificar")) {
			
			em.getTransaction().begin();
			em.merge(pr);
			em.flush();
			em.getTransaction().commit();
			
		}
		
		
			response.sendRedirect("index.jsp"); 
		
	}

}
