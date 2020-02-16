/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.carlosjai.ad04.db;

import me.carlosjai.ad04.db.clientes.ClientesAccessor;
import me.carlosjai.ad04.db.empregados.EmpregadosAccessor;
import me.carlosjai.ad04.db.produtos.ProdutosAccessor;
import me.carlosjai.ad04.db.provincias.ProvinciasAccessor;
import me.carlosjai.ad04.db.tendas.TendasAccessor;
import me.carlosjai.ad04.obxectos.Franquicia;
import org.hibernate.HibernateException;

/**
 *
 * @author carlos
 */
public class FranquiciaDB {

    public static Franquicia cargarDatosFranquicia() throws HibernateException {
        Franquicia f = new Franquicia();
        f.setClientes(ClientesAccessor.getClientes());
        f.setProvincias(ProvinciasAccessor.getProvincias());
        f.setProdutos(ProdutosAccessor.obterProdutos());
        f.setTendas(TendasAccessor.getTendas());
        f.setEmpregados(EmpregadosAccessor.getEmpregados());
        return f;
    }

    public static void recargarDatosTendas(Franquicia f) throws HibernateException {
        f.setProdutos(ProdutosAccessor.obterProdutos());
        f.setTendas(TendasAccessor.getTendas());
    }

    public static void recargarDatosEmpregados(Franquicia f)  throws HibernateException {
         f.setTendas(TendasAccessor.getTendas());
        f.setEmpregados(EmpregadosAccessor.getEmpregados());
    }

    public static void recargarDatosProdutos(Franquicia f)  throws HibernateException {
        f.setProdutos(ProdutosAccessor.obterProdutos());
    }
}
