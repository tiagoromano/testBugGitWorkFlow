
app.userEvents.calcPrazoRestricaoWS = function(nCdServicoParam, sCepOrigemParam, sCepDestinoParam, sDtCalculoParam, callback) {
   var request = {
     nCdServico: nCdServicoParam,
     sCepOrigem: sCepOrigemParam,
     sCepDestino: sCepDestinoParam,
     sDtCalculo: sDtCalculoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcPrazoRestricao",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.calcPrecoFACWS = function(nCdServicoParam, nVlPesoParam, strDataCalculoParam, callback) {
   var request = {
     nCdServico: nCdServicoParam,
     nVlPeso: nVlPesoParam,
     strDataCalculo: strDataCalculoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcPrecoFAC",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.calcPrecoWS = function(nCdEmpresaParam, sDsSenhaParam, nCdServicoParam, sCepOrigemParam, sCepDestinoParam, nVlPesoParam, nCdFormatoParam, nVlComprimentoParam, nVlAlturaParam, nVlLarguraParam, nVlDiametroParam, sCdMaoPropriaParam, nVlValorDeclaradoParam, sCdAvisoRecebimentoParam, callback) {
   var request = {
     nCdEmpresa: nCdEmpresaParam,
     sDsSenha: sDsSenhaParam,
     nCdServico: nCdServicoParam,
     sCepOrigem: sCepOrigemParam,
     sCepDestino: sCepDestinoParam,
     nVlPeso: nVlPesoParam,
     nCdFormato: nCdFormatoParam,
     nVlComprimento: nVlComprimentoParam,
     nVlAltura: nVlAlturaParam,
     nVlLargura: nVlLarguraParam,
     nVlDiametro: nVlDiametroParam,
     sCdMaoPropria: sCdMaoPropriaParam,
     nVlValorDeclarado: nVlValorDeclaradoParam,
     sCdAvisoRecebimento: sCdAvisoRecebimentoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcPreco",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.calcPrazoWS = function(nCdServicoParam, sCepOrigemParam, sCepDestinoParam, callback) {
   var request = {
     nCdServico: nCdServicoParam,
     sCepOrigem: sCepOrigemParam,
     sCepDestino: sCepDestinoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcPrazo",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.verificaModalWS = function(nCdServicoParam, sCepOrigemParam, sCepDestinoParam, callback) {
   var request = {
     nCdServico: nCdServicoParam,
     sCepOrigem: sCepOrigemParam,
     sCepDestino: sCepDestinoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/verificaModal",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.listaServicosWS = function(callback) {
   var request = {

   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/listaServicos",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.listaServicosSTARWS = function(callback) {
   var request = {

   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/listaServicosSTAR",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.calcPrecoPrazoWS = function(nCdEmpresaParam, sDsSenhaParam, nCdServicoParam, sCepOrigemParam, sCepDestinoParam, nVlPesoParam, nCdFormatoParam, nVlComprimentoParam, nVlAlturaParam, nVlLarguraParam, nVlDiametroParam, sCdMaoPropriaParam, nVlValorDeclaradoParam, sCdAvisoRecebimentoParam, callback) {
   var request = {
     nCdEmpresa: nCdEmpresaParam,
     sDsSenha: sDsSenhaParam,
     nCdServico: nCdServicoParam,
     sCepOrigem: sCepOrigemParam,
     sCepDestino: sCepDestinoParam,
     nVlPeso: nVlPesoParam,
     nCdFormato: nCdFormatoParam,
     nVlComprimento: nVlComprimentoParam,
     nVlAltura: nVlAlturaParam,
     nVlLargura: nVlLarguraParam,
     nVlDiametro: nVlDiametroParam,
     sCdMaoPropria: sCdMaoPropriaParam,
     nVlValorDeclarado: nVlValorDeclaradoParam,
     sCdAvisoRecebimento: sCdAvisoRecebimentoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcPrecoPrazo",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.calcPrecoPrazoRestricaoWS = function(nCdEmpresaParam, sDsSenhaParam, nCdServicoParam, sCepOrigemParam, sCepDestinoParam, nVlPesoParam, nCdFormatoParam, nVlComprimentoParam, nVlAlturaParam, nVlLarguraParam, nVlDiametroParam, sCdMaoPropriaParam, nVlValorDeclaradoParam, sCdAvisoRecebimentoParam, sDtCalculoParam, callback) {
   var request = {
     nCdEmpresa: nCdEmpresaParam,
     sDsSenha: sDsSenhaParam,
     nCdServico: nCdServicoParam,
     sCepOrigem: sCepOrigemParam,
     sCepDestino: sCepDestinoParam,
     nVlPeso: nVlPesoParam,
     nCdFormato: nCdFormatoParam,
     nVlComprimento: nVlComprimentoParam,
     nVlAltura: nVlAlturaParam,
     nVlLargura: nVlLarguraParam,
     nVlDiametro: nVlDiametroParam,
     sCdMaoPropria: sCdMaoPropriaParam,
     nVlValorDeclarado: nVlValorDeclaradoParam,
     sCdAvisoRecebimento: sCdAvisoRecebimentoParam,
     sDtCalculo: sDtCalculoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcPrecoPrazoRestricao",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.calcPrecoPrazoDataWS = function(nCdEmpresaParam, sDsSenhaParam, nCdServicoParam, sCepOrigemParam, sCepDestinoParam, nVlPesoParam, nCdFormatoParam, nVlComprimentoParam, nVlAlturaParam, nVlLarguraParam, nVlDiametroParam, sCdMaoPropriaParam, nVlValorDeclaradoParam, sCdAvisoRecebimentoParam, sDtCalculoParam, callback) {
   var request = {
     nCdEmpresa: nCdEmpresaParam,
     sDsSenha: sDsSenhaParam,
     nCdServico: nCdServicoParam,
     sCepOrigem: sCepOrigemParam,
     sCepDestino: sCepDestinoParam,
     nVlPeso: nVlPesoParam,
     nCdFormato: nCdFormatoParam,
     nVlComprimento: nVlComprimentoParam,
     nVlAltura: nVlAlturaParam,
     nVlLargura: nVlLarguraParam,
     nVlDiametro: nVlDiametroParam,
     sCdMaoPropria: sCdMaoPropriaParam,
     nVlValorDeclarado: nVlValorDeclaradoParam,
     sCdAvisoRecebimento: sCdAvisoRecebimentoParam,
     sDtCalculo: sDtCalculoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcPrecoPrazoData",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.getVersaoWS = function(callback) {
   var request = {

   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/getVersao",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.calcPrecoDataWS = function(nCdEmpresaParam, sDsSenhaParam, nCdServicoParam, sCepOrigemParam, sCepDestinoParam, nVlPesoParam, nCdFormatoParam, nVlComprimentoParam, nVlAlturaParam, nVlLarguraParam, nVlDiametroParam, sCdMaoPropriaParam, nVlValorDeclaradoParam, sCdAvisoRecebimentoParam, sDtCalculoParam, callback) {
   var request = {
     nCdEmpresa: nCdEmpresaParam,
     sDsSenha: sDsSenhaParam,
     nCdServico: nCdServicoParam,
     sCepOrigem: sCepOrigemParam,
     sCepDestino: sCepDestinoParam,
     nVlPeso: nVlPesoParam,
     nCdFormato: nCdFormatoParam,
     nVlComprimento: nVlComprimentoParam,
     nVlAltura: nVlAlturaParam,
     nVlLargura: nVlLarguraParam,
     nVlDiametro: nVlDiametroParam,
     sCdMaoPropria: sCdMaoPropriaParam,
     nVlValorDeclarado: nVlValorDeclaradoParam,
     sCdAvisoRecebimento: sCdAvisoRecebimentoParam,
     sDtCalculo: sDtCalculoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcPrecoData",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.calcDataMaximaWS = function(codigoObjetoParam, callback) {
   var request = {
     codigoObjeto: codigoObjetoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcDataMaxima",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};

app.userEvents.calcPrazoDataWS = function(nCdServicoParam, sCepOrigemParam, sCepDestinoParam, sDtCalculoParam, callback) {
   var request = {
     nCdServico: nCdServicoParam,
     sCepOrigem: sCepOrigemParam,
     sCepDestino: sCepDestinoParam,
     sDtCalculo: sDtCalculoParam
   };

   $.ajax({
     method: "GET", 
     url: "api/rest/webservices/CalcPrecoPrazoWS/calcPrazoData",
     data: request,
     success: function(data) {
       if (callback)
         callback(data);
       else
         console.log(data);
     }
   });
};
