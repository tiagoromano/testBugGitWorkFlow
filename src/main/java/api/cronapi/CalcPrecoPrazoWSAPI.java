package api.cronapi;

import org.apache.commons.httpclient.protocol.Protocol;
import cronapi.*;
import cronapi.CronapiMetaData.*;

/**
* Controller para expor serviços de CalcPrecoPrazoWSStub
*
* @generated
**/
@CronapiMetaData(categoryName = "CalcPrecoPrazoWS")
public class CalcPrecoPrazoWSAPI {

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcPrazoRestricao (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepOrigemVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepDestinoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDtCalculoVar
) throws Exception {

    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepOrigem = sCepOrigemVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepDestino = sCepDestinoVar.getTypedObject(java.lang.String.class);
    java.lang.String sDtCalculo = sDtCalculoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcPrazoRestricaoResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcPrazoRestricao calcPrazoRestricao00 = new org.tempuri.CalcPrecoPrazoWSStub.CalcPrazoRestricao();
                calcPrazoRestricao00.setNCdServico(nCdServico);
                calcPrazoRestricao00.setSCepOrigem(sCepOrigem);
                calcPrazoRestricao00.setSCepDestino(sCepDestino);
                calcPrazoRestricao00.setSDtCalculo(sDtCalculo);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcPrazoRestricao(calcPrazoRestricao00);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcPrecoFAC (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlPesoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var strDataCalculoVar
) throws Exception {

    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String nVlPeso = nVlPesoVar.getTypedObject(java.lang.String.class);
    java.lang.String strDataCalculo = strDataCalculoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoFACResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoFAC calcPrecoFAC21 = new org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoFAC();
                calcPrecoFAC21.setNCdServico(nCdServico);
                calcPrecoFAC21.setNVlPeso(nVlPeso);
                calcPrecoFAC21.setStrDataCalculo(strDataCalculo);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcPrecoFAC(calcPrecoFAC21);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcPreco (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdEmpresaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDsSenhaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepOrigemVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepDestinoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlPesoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdFormatoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlComprimentoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlAlturaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlLarguraVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlDiametroVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdMaoPropriaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlValorDeclaradoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdAvisoRecebimentoVar
) throws Exception {

    java.lang.String nCdEmpresa = nCdEmpresaVar.getTypedObject(java.lang.String.class);
    java.lang.String sDsSenha = sDsSenhaVar.getTypedObject(java.lang.String.class);
    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepOrigem = sCepOrigemVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepDestino = sCepDestinoVar.getTypedObject(java.lang.String.class);
    java.lang.String nVlPeso = nVlPesoVar.getTypedObject(java.lang.String.class);
    java.lang.Integer nCdFormato = nCdFormatoVar.getTypedObject(java.lang.Integer.class);
    java.math.BigDecimal nVlComprimento = nVlComprimentoVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlAltura = nVlAlturaVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlLargura = nVlLarguraVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlDiametro = nVlDiametroVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdMaoPropria = sCdMaoPropriaVar.getTypedObject(java.lang.String.class);
    java.math.BigDecimal nVlValorDeclarado = nVlValorDeclaradoVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdAvisoRecebimento = sCdAvisoRecebimentoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcPreco calcPreco42 = new org.tempuri.CalcPrecoPrazoWSStub.CalcPreco();
                calcPreco42.setNCdEmpresa(nCdEmpresa);
                calcPreco42.setSDsSenha(sDsSenha);
                calcPreco42.setNCdServico(nCdServico);
                calcPreco42.setSCepOrigem(sCepOrigem);
                calcPreco42.setSCepDestino(sCepDestino);
                calcPreco42.setNVlPeso(nVlPeso);
                calcPreco42.setNCdFormato(nCdFormato);
                calcPreco42.setNVlComprimento(nVlComprimento);
                calcPreco42.setNVlAltura(nVlAltura);
                calcPreco42.setNVlLargura(nVlLargura);
                calcPreco42.setNVlDiametro(nVlDiametro);
                calcPreco42.setSCdMaoPropria(sCdMaoPropria);
                calcPreco42.setNVlValorDeclarado(nVlValorDeclarado);
                calcPreco42.setSCdAvisoRecebimento(sCdAvisoRecebimento);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcPreco(calcPreco42);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcPrazo (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepOrigemVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepDestinoVar
) throws Exception {

    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepOrigem = sCepOrigemVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepDestino = sCepDestinoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcPrazoResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcPrazo calcPrazo63 = new org.tempuri.CalcPrecoPrazoWSStub.CalcPrazo();
                calcPrazo63.setNCdServico(nCdServico);
                calcPrazo63.setSCepOrigem(sCepOrigem);
                calcPrazo63.setSCepDestino(sCepDestino);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcPrazo(calcPrazo63);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var verificaModal (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepOrigemVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepDestinoVar
) throws Exception {

    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepOrigem = sCepOrigemVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepDestino = sCepDestinoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.VerificaModalResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.VerificaModal verificaModal84 = new org.tempuri.CalcPrecoPrazoWSStub.VerificaModal();
                verificaModal84.setNCdServico(nCdServico);
                verificaModal84.setSCepOrigem(sCepOrigem);
                verificaModal84.setSCepDestino(sCepDestino);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.verificaModal(verificaModal84);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var listaServicos (
) throws Exception {


org.tempuri.CalcPrecoPrazoWSStub.ListaServicosResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.ListaServicos listaServicos105 = new org.tempuri.CalcPrecoPrazoWSStub.ListaServicos();

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.listaServicos(listaServicos105);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var listaServicosSTAR (
) throws Exception {


org.tempuri.CalcPrecoPrazoWSStub.ListaServicosSTARResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.ListaServicosSTAR listaServicosSTAR126 = new org.tempuri.CalcPrecoPrazoWSStub.ListaServicosSTAR();

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.listaServicosSTAR(listaServicosSTAR126);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcPrecoPrazo (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdEmpresaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDsSenhaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepOrigemVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepDestinoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlPesoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdFormatoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlComprimentoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlAlturaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlLarguraVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlDiametroVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdMaoPropriaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlValorDeclaradoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdAvisoRecebimentoVar
) throws Exception {

    java.lang.String nCdEmpresa = nCdEmpresaVar.getTypedObject(java.lang.String.class);
    java.lang.String sDsSenha = sDsSenhaVar.getTypedObject(java.lang.String.class);
    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepOrigem = sCepOrigemVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepDestino = sCepDestinoVar.getTypedObject(java.lang.String.class);
    java.lang.String nVlPeso = nVlPesoVar.getTypedObject(java.lang.String.class);
    java.lang.Integer nCdFormato = nCdFormatoVar.getTypedObject(java.lang.Integer.class);
    java.math.BigDecimal nVlComprimento = nVlComprimentoVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlAltura = nVlAlturaVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlLargura = nVlLarguraVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlDiametro = nVlDiametroVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdMaoPropria = sCdMaoPropriaVar.getTypedObject(java.lang.String.class);
    java.math.BigDecimal nVlValorDeclarado = nVlValorDeclaradoVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdAvisoRecebimento = sCdAvisoRecebimentoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoPrazoResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoPrazo calcPrecoPrazo147 = new org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoPrazo();
                calcPrecoPrazo147.setNCdEmpresa(nCdEmpresa);
                calcPrecoPrazo147.setSDsSenha(sDsSenha);
                calcPrecoPrazo147.setNCdServico(nCdServico);
                calcPrecoPrazo147.setSCepOrigem(sCepOrigem);
                calcPrecoPrazo147.setSCepDestino(sCepDestino);
                calcPrecoPrazo147.setNVlPeso(nVlPeso);
                calcPrecoPrazo147.setNCdFormato(nCdFormato);
                calcPrecoPrazo147.setNVlComprimento(nVlComprimento);
                calcPrecoPrazo147.setNVlAltura(nVlAltura);
                calcPrecoPrazo147.setNVlLargura(nVlLargura);
                calcPrecoPrazo147.setNVlDiametro(nVlDiametro);
                calcPrecoPrazo147.setSCdMaoPropria(sCdMaoPropria);
                calcPrecoPrazo147.setNVlValorDeclarado(nVlValorDeclarado);
                calcPrecoPrazo147.setSCdAvisoRecebimento(sCdAvisoRecebimento);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcPrecoPrazo(calcPrecoPrazo147);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcPrecoPrazoRestricao (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdEmpresaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDsSenhaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepOrigemVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepDestinoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlPesoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdFormatoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlComprimentoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlAlturaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlLarguraVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlDiametroVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdMaoPropriaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlValorDeclaradoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdAvisoRecebimentoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDtCalculoVar
) throws Exception {

    java.lang.String nCdEmpresa = nCdEmpresaVar.getTypedObject(java.lang.String.class);
    java.lang.String sDsSenha = sDsSenhaVar.getTypedObject(java.lang.String.class);
    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepOrigem = sCepOrigemVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepDestino = sCepDestinoVar.getTypedObject(java.lang.String.class);
    java.lang.String nVlPeso = nVlPesoVar.getTypedObject(java.lang.String.class);
    java.lang.Integer nCdFormato = nCdFormatoVar.getTypedObject(java.lang.Integer.class);
    java.math.BigDecimal nVlComprimento = nVlComprimentoVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlAltura = nVlAlturaVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlLargura = nVlLarguraVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlDiametro = nVlDiametroVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdMaoPropria = sCdMaoPropriaVar.getTypedObject(java.lang.String.class);
    java.math.BigDecimal nVlValorDeclarado = nVlValorDeclaradoVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdAvisoRecebimento = sCdAvisoRecebimentoVar.getTypedObject(java.lang.String.class);
    java.lang.String sDtCalculo = sDtCalculoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoPrazoRestricaoResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoPrazoRestricao calcPrecoPrazoRestricao168 = new org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoPrazoRestricao();
                calcPrecoPrazoRestricao168.setNCdEmpresa(nCdEmpresa);
                calcPrecoPrazoRestricao168.setSDsSenha(sDsSenha);
                calcPrecoPrazoRestricao168.setNCdServico(nCdServico);
                calcPrecoPrazoRestricao168.setSCepOrigem(sCepOrigem);
                calcPrecoPrazoRestricao168.setSCepDestino(sCepDestino);
                calcPrecoPrazoRestricao168.setNVlPeso(nVlPeso);
                calcPrecoPrazoRestricao168.setNCdFormato(nCdFormato);
                calcPrecoPrazoRestricao168.setNVlComprimento(nVlComprimento);
                calcPrecoPrazoRestricao168.setNVlAltura(nVlAltura);
                calcPrecoPrazoRestricao168.setNVlLargura(nVlLargura);
                calcPrecoPrazoRestricao168.setNVlDiametro(nVlDiametro);
                calcPrecoPrazoRestricao168.setSCdMaoPropria(sCdMaoPropria);
                calcPrecoPrazoRestricao168.setNVlValorDeclarado(nVlValorDeclarado);
                calcPrecoPrazoRestricao168.setSCdAvisoRecebimento(sCdAvisoRecebimento);
                calcPrecoPrazoRestricao168.setSDtCalculo(sDtCalculo);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcPrecoPrazoRestricao(calcPrecoPrazoRestricao168);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcPrecoPrazoData (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdEmpresaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDsSenhaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepOrigemVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepDestinoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlPesoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdFormatoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlComprimentoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlAlturaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlLarguraVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlDiametroVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdMaoPropriaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlValorDeclaradoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdAvisoRecebimentoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDtCalculoVar
) throws Exception {

    java.lang.String nCdEmpresa = nCdEmpresaVar.getTypedObject(java.lang.String.class);
    java.lang.String sDsSenha = sDsSenhaVar.getTypedObject(java.lang.String.class);
    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepOrigem = sCepOrigemVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepDestino = sCepDestinoVar.getTypedObject(java.lang.String.class);
    java.lang.String nVlPeso = nVlPesoVar.getTypedObject(java.lang.String.class);
    java.lang.Integer nCdFormato = nCdFormatoVar.getTypedObject(java.lang.Integer.class);
    java.math.BigDecimal nVlComprimento = nVlComprimentoVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlAltura = nVlAlturaVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlLargura = nVlLarguraVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlDiametro = nVlDiametroVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdMaoPropria = sCdMaoPropriaVar.getTypedObject(java.lang.String.class);
    java.math.BigDecimal nVlValorDeclarado = nVlValorDeclaradoVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdAvisoRecebimento = sCdAvisoRecebimentoVar.getTypedObject(java.lang.String.class);
    java.lang.String sDtCalculo = sDtCalculoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoPrazoDataResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoPrazoData calcPrecoPrazoData189 = new org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoPrazoData();
                calcPrecoPrazoData189.setNCdEmpresa(nCdEmpresa);
                calcPrecoPrazoData189.setSDsSenha(sDsSenha);
                calcPrecoPrazoData189.setNCdServico(nCdServico);
                calcPrecoPrazoData189.setSCepOrigem(sCepOrigem);
                calcPrecoPrazoData189.setSCepDestino(sCepDestino);
                calcPrecoPrazoData189.setNVlPeso(nVlPeso);
                calcPrecoPrazoData189.setNCdFormato(nCdFormato);
                calcPrecoPrazoData189.setNVlComprimento(nVlComprimento);
                calcPrecoPrazoData189.setNVlAltura(nVlAltura);
                calcPrecoPrazoData189.setNVlLargura(nVlLargura);
                calcPrecoPrazoData189.setNVlDiametro(nVlDiametro);
                calcPrecoPrazoData189.setSCdMaoPropria(sCdMaoPropria);
                calcPrecoPrazoData189.setNVlValorDeclarado(nVlValorDeclarado);
                calcPrecoPrazoData189.setSCdAvisoRecebimento(sCdAvisoRecebimento);
                calcPrecoPrazoData189.setSDtCalculo(sDtCalculo);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcPrecoPrazoData(calcPrecoPrazoData189);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var getVersao (
) throws Exception {


org.tempuri.CalcPrecoPrazoWSStub.GetVersaoResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.GetVersao getVersao2010 = new org.tempuri.CalcPrecoPrazoWSStub.GetVersao();

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.getVersao(getVersao2010);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcPrecoData (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdEmpresaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDsSenhaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepOrigemVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepDestinoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlPesoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdFormatoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlComprimentoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlAlturaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlLarguraVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlDiametroVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdMaoPropriaVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var nVlValorDeclaradoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCdAvisoRecebimentoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDtCalculoVar
) throws Exception {

    java.lang.String nCdEmpresa = nCdEmpresaVar.getTypedObject(java.lang.String.class);
    java.lang.String sDsSenha = sDsSenhaVar.getTypedObject(java.lang.String.class);
    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepOrigem = sCepOrigemVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepDestino = sCepDestinoVar.getTypedObject(java.lang.String.class);
    java.lang.String nVlPeso = nVlPesoVar.getTypedObject(java.lang.String.class);
    java.lang.Integer nCdFormato = nCdFormatoVar.getTypedObject(java.lang.Integer.class);
    java.math.BigDecimal nVlComprimento = nVlComprimentoVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlAltura = nVlAlturaVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlLargura = nVlLarguraVar.getTypedObject(java.math.BigDecimal.class);
    java.math.BigDecimal nVlDiametro = nVlDiametroVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdMaoPropria = sCdMaoPropriaVar.getTypedObject(java.lang.String.class);
    java.math.BigDecimal nVlValorDeclarado = nVlValorDeclaradoVar.getTypedObject(java.math.BigDecimal.class);
    java.lang.String sCdAvisoRecebimento = sCdAvisoRecebimentoVar.getTypedObject(java.lang.String.class);
    java.lang.String sDtCalculo = sDtCalculoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoDataResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoData calcPrecoData2211 = new org.tempuri.CalcPrecoPrazoWSStub.CalcPrecoData();
                calcPrecoData2211.setNCdEmpresa(nCdEmpresa);
                calcPrecoData2211.setSDsSenha(sDsSenha);
                calcPrecoData2211.setNCdServico(nCdServico);
                calcPrecoData2211.setSCepOrigem(sCepOrigem);
                calcPrecoData2211.setSCepDestino(sCepDestino);
                calcPrecoData2211.setNVlPeso(nVlPeso);
                calcPrecoData2211.setNCdFormato(nCdFormato);
                calcPrecoData2211.setNVlComprimento(nVlComprimento);
                calcPrecoData2211.setNVlAltura(nVlAltura);
                calcPrecoData2211.setNVlLargura(nVlLargura);
                calcPrecoData2211.setNVlDiametro(nVlDiametro);
                calcPrecoData2211.setSCdMaoPropria(sCdMaoPropria);
                calcPrecoData2211.setNVlValorDeclarado(nVlValorDeclarado);
                calcPrecoData2211.setSCdAvisoRecebimento(sCdAvisoRecebimento);
                calcPrecoData2211.setSDtCalculo(sDtCalculo);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcPrecoData(calcPrecoData2211);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcDataMaxima (
    @ParamMetaData(type = ObjectType.OBJECT) Var codigoObjetoVar
) throws Exception {

    java.lang.String codigoObjeto = codigoObjetoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcDataMaximaResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcDataMaxima calcDataMaxima2412 = new org.tempuri.CalcPrecoPrazoWSStub.CalcDataMaxima();
                calcDataMaxima2412.setCodigoObjeto(codigoObjeto);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcDataMaxima(calcDataMaxima2412);

return Var.valueOf(respn);
}

@CronapiMetaData(type = "function", returnType = ObjectType.OBJECT)
public static Var calcPrazoData (
    @ParamMetaData(type = ObjectType.OBJECT) Var nCdServicoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepOrigemVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sCepDestinoVar,
    @ParamMetaData(type = ObjectType.OBJECT) Var sDtCalculoVar
) throws Exception {

    java.lang.String nCdServico = nCdServicoVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepOrigem = sCepOrigemVar.getTypedObject(java.lang.String.class);
    java.lang.String sCepDestino = sCepDestinoVar.getTypedObject(java.lang.String.class);
    java.lang.String sDtCalculo = sDtCalculoVar.getTypedObject(java.lang.String.class);

org.tempuri.CalcPrecoPrazoWSStub.CalcPrazoDataResponse respn = null;


org.tempuri.CalcPrecoPrazoWSStub stub = new org.tempuri.CalcPrecoPrazoWSStub();

        org.tempuri.CalcPrecoPrazoWSStub.CalcPrazoData calcPrazoData2613 = new org.tempuri.CalcPrecoPrazoWSStub.CalcPrazoData();
                calcPrazoData2613.setNCdServico(nCdServico);
                calcPrazoData2613.setSCepOrigem(sCepOrigem);
                calcPrazoData2613.setSCepDestino(sCepDestino);
                calcPrazoData2613.setSDtCalculo(sDtCalculo);

org.apache.commons.httpclient.protocol.Protocol.unregisterProtocol("https");
org.apache.commons.httpclient.protocol.Protocol.registerProtocol("https",  new Protocol("https", new org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory(), 443));
respn = stub.calcPrazoData(calcPrazoData2613);

return Var.valueOf(respn);
}

}