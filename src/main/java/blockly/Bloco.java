package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;



@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Bloco {

public static final int TIMEOUT = 300;

/**
 *
 * @param valor
 * @param valor2
 * @return Var
 */
// Bloco
public static Var Executar(Var valor, Var valor2) throws Exception {
 return new Callable<Var>() {

   private Var item = Var.VAR_NULL;

   public Var call() throws Exception {
    item = Var.valueOf(cronapi.conversion.Operations.toBoolean(valor2));
    return Var.VAR_NULL;
   }
 }.call();
}

}

