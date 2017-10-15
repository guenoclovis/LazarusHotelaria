package br.org.ufpr.tcc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;

public class Util {
	public static String likeFormat(String texto) {
        return String.format("%%%s%%", StringUtils.trim(texto));
    }

	public static Predicate[] add(Predicate[] predicados, Predicate novoPredicado) {
		List<Predicate> arrAux = Arrays.asList(predicados);
		arrAux.add(novoPredicado);
		return (Predicate[]) arrAux.toArray();
	}
}
