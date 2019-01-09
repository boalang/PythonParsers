package visitors;
import parsers.python2.*;
import parsers.python2.Python2Parser.And_exprContext;
import parsers.python2.Python2Parser.And_testContext;
import parsers.python2.Python2Parser.ArglistContext;
import parsers.python2.Python2Parser.ArgumentContext;
import parsers.python2.Python2Parser.Arith_exprContext;
import parsers.python2.Python2Parser.Assert_stmtContext;
import parsers.python2.Python2Parser.AtomContext;
import parsers.python2.Python2Parser.AugassignContext;
import parsers.python2.Python2Parser.Break_stmtContext;
import parsers.python2.Python2Parser.ClassdefContext;
import parsers.python2.Python2Parser.Comp_forContext;
import parsers.python2.Python2Parser.Comp_ifContext;
import parsers.python2.Python2Parser.Comp_iterContext;
import parsers.python2.Python2Parser.Comp_opContext;
import parsers.python2.Python2Parser.ComparisonContext;
import parsers.python2.Python2Parser.Compound_stmtContext;
import parsers.python2.Python2Parser.Continue_stmtContext;
import parsers.python2.Python2Parser.DecoratedContext;
import parsers.python2.Python2Parser.DecoratorContext;
import parsers.python2.Python2Parser.DecoratorsContext;
import parsers.python2.Python2Parser.Del_stmtContext;
import parsers.python2.Python2Parser.DictorsetmakerContext;
import parsers.python2.Python2Parser.Dotted_as_nameContext;
import parsers.python2.Python2Parser.Dotted_as_namesContext;
import parsers.python2.Python2Parser.Dotted_nameContext;
import parsers.python2.Python2Parser.Encoding_declContext;
import parsers.python2.Python2Parser.Eval_inputContext;
import parsers.python2.Python2Parser.Except_clauseContext;
import parsers.python2.Python2Parser.Exec_stmtContext;
import parsers.python2.Python2Parser.ExprContext;
import parsers.python2.Python2Parser.Expr_stmtContext;
import parsers.python2.Python2Parser.ExprlistContext;
import parsers.python2.Python2Parser.FactorContext;
import parsers.python2.Python2Parser.File_inputContext;
import parsers.python2.Python2Parser.Flow_stmtContext;
import parsers.python2.Python2Parser.For_stmtContext;
import parsers.python2.Python2Parser.FpdefContext;
import parsers.python2.Python2Parser.FplistContext;
import parsers.python2.Python2Parser.FuncdefContext;
import parsers.python2.Python2Parser.Global_stmtContext;
import parsers.python2.Python2Parser.If_stmtContext;
import parsers.python2.Python2Parser.Import_as_nameContext;
import parsers.python2.Python2Parser.Import_as_namesContext;
import parsers.python2.Python2Parser.Import_fromContext;
import parsers.python2.Python2Parser.Import_nameContext;
import parsers.python2.Python2Parser.Import_stmtContext;
import parsers.python2.Python2Parser.LambdefContext;
import parsers.python2.Python2Parser.List_forContext;
import parsers.python2.Python2Parser.List_ifContext;
import parsers.python2.Python2Parser.List_iterContext;
import parsers.python2.Python2Parser.ListmakerContext;
import parsers.python2.Python2Parser.Not_testContext;
import parsers.python2.Python2Parser.Old_lambdefContext;
import parsers.python2.Python2Parser.Old_testContext;
import parsers.python2.Python2Parser.Or_testContext;
import parsers.python2.Python2Parser.ParametersContext;
import parsers.python2.Python2Parser.Pass_stmtContext;
import parsers.python2.Python2Parser.PowerContext;
import parsers.python2.Python2Parser.Print_stmtContext;
import parsers.python2.Python2Parser.Raise_stmtContext;
import parsers.python2.Python2Parser.Return_stmtContext;
import parsers.python2.Python2Parser.Shift_exprContext;
import parsers.python2.Python2Parser.Simple_stmtContext;
import parsers.python2.Python2Parser.Single_inputContext;
import parsers.python2.Python2Parser.SliceopContext;
import parsers.python2.Python2Parser.Small_stmtContext;
import parsers.python2.Python2Parser.StmtContext;
import parsers.python2.Python2Parser.SubscriptContext;
import parsers.python2.Python2Parser.SubscriptlistContext;
import parsers.python2.Python2Parser.SuiteContext;
import parsers.python2.Python2Parser.TermContext;
import parsers.python2.Python2Parser.TestContext;
import parsers.python2.Python2Parser.Testlist1Context;
import parsers.python2.Python2Parser.TestlistContext;
import parsers.python2.Python2Parser.Testlist_compContext;
import parsers.python2.Python2Parser.Testlist_safeContext;
import parsers.python2.Python2Parser.TrailerContext;
import parsers.python2.Python2Parser.Try_stmtContext;
import parsers.python2.Python2Parser.VarargslistContext;
import parsers.python2.Python2Parser.While_stmtContext;
import parsers.python2.Python2Parser.With_itemContext;
import parsers.python2.Python2Parser.With_stmtContext;
import parsers.python2.Python2Parser.Xor_exprContext;
import parsers.python2.Python2Parser.Yield_exprContext;
import parsers.python2.Python2Parser.Yield_stmtContext;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

public class VisitPython2 implements Python2Listener {
	private static String readFile(File file, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, encoding);
    }

    public Python2Parser parsefile(File file) throws IOException {
        String code = readFile(file, Charset.forName("UTF-8"));
        return parse(code);
    }
    
    public Python2Parser parse(String code) {
    	Python2Lexer lexer = new Python2Lexer(new ANTLRInputStream(code));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Python2Parser parser = new Python2Parser(tokens);

        return parser;
    }
    
	public void visit(String source) {
        Python2Parser parser = parse(source);
		ParseTreeWalker.DEFAULT.walk(this, parser.file_input());
	}
	
	public void visit(File file) {
		try {
			Python2Parser parser = parsefile(file);
			ParseTreeWalker.DEFAULT.walk(this, parser.file_input());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSingle_input(Single_inputContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSingle_input(Single_inputContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterFile_input(File_inputContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitFile_input(File_inputContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterEval_input(Eval_inputContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitEval_input(Eval_inputContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDecorator(DecoratorContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDecorator(DecoratorContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDecorators(DecoratorsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDecorators(DecoratorsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDecorated(DecoratedContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDecorated(DecoratedContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterFuncdef(FuncdefContext ctx) {
		System.out.println("Function Name: "+ ctx.NAME().getText());
		
	}

	@Override
	public void exitFuncdef(FuncdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterParameters(ParametersContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitParameters(ParametersContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterVarargslist(VarargslistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitVarargslist(VarargslistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterFpdef(FpdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitFpdef(FpdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterFplist(FplistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitFplist(FplistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterStmt(StmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitStmt(StmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSimple_stmt(Simple_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSimple_stmt(Simple_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSmall_stmt(Small_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSmall_stmt(Small_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterExpr_stmt(Expr_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitExpr_stmt(Expr_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAugassign(AugassignContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitAugassign(AugassignContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterPrint_stmt(Print_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitPrint_stmt(Print_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDel_stmt(Del_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDel_stmt(Del_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterPass_stmt(Pass_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitPass_stmt(Pass_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterFlow_stmt(Flow_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitFlow_stmt(Flow_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterBreak_stmt(Break_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitBreak_stmt(Break_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterContinue_stmt(Continue_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitContinue_stmt(Continue_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterReturn_stmt(Return_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitReturn_stmt(Return_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterYield_stmt(Yield_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitYield_stmt(Yield_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterRaise_stmt(Raise_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitRaise_stmt(Raise_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterImport_stmt(Import_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitImport_stmt(Import_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterImport_name(Import_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitImport_name(Import_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterImport_from(Import_fromContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitImport_from(Import_fromContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterImport_as_name(Import_as_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitImport_as_name(Import_as_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDotted_as_name(Dotted_as_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDotted_as_name(Dotted_as_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterImport_as_names(Import_as_namesContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitImport_as_names(Import_as_namesContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDotted_as_names(Dotted_as_namesContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDotted_as_names(Dotted_as_namesContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDotted_name(Dotted_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDotted_name(Dotted_nameContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterGlobal_stmt(Global_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitGlobal_stmt(Global_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterExec_stmt(Exec_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitExec_stmt(Exec_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAssert_stmt(Assert_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitAssert_stmt(Assert_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterCompound_stmt(Compound_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitCompound_stmt(Compound_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterIf_stmt(If_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitIf_stmt(If_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterWhile_stmt(While_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitWhile_stmt(While_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterFor_stmt(For_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitFor_stmt(For_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTry_stmt(Try_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTry_stmt(Try_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterWith_stmt(With_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitWith_stmt(With_stmtContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterWith_item(With_itemContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitWith_item(With_itemContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterExcept_clause(Except_clauseContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitExcept_clause(Except_clauseContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSuite(SuiteContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSuite(SuiteContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTestlist_safe(Testlist_safeContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTestlist_safe(Testlist_safeContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterOld_test(Old_testContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitOld_test(Old_testContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterOld_lambdef(Old_lambdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitOld_lambdef(Old_lambdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTest(TestContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTest(TestContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterOr_test(Or_testContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitOr_test(Or_testContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAnd_test(And_testContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitAnd_test(And_testContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterNot_test(Not_testContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitNot_test(Not_testContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterComparison(ComparisonContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitComparison(ComparisonContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterComp_op(Comp_opContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitComp_op(Comp_opContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterExpr(ExprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitExpr(ExprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterXor_expr(Xor_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitXor_expr(Xor_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAnd_expr(And_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitAnd_expr(And_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterShift_expr(Shift_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitShift_expr(Shift_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterArith_expr(Arith_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitArith_expr(Arith_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTerm(TermContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTerm(TermContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterFactor(FactorContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitFactor(FactorContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterPower(PowerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitPower(PowerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterAtom(AtomContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitAtom(AtomContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterListmaker(ListmakerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitListmaker(ListmakerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTestlist_comp(Testlist_compContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTestlist_comp(Testlist_compContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterLambdef(LambdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitLambdef(LambdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTrailer(TrailerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTrailer(TrailerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSubscriptlist(SubscriptlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSubscriptlist(SubscriptlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSubscript(SubscriptContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSubscript(SubscriptContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterSliceop(SliceopContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitSliceop(SliceopContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterExprlist(ExprlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitExprlist(ExprlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTestlist(TestlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTestlist(TestlistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterDictorsetmaker(DictorsetmakerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitDictorsetmaker(DictorsetmakerContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterClassdef(ClassdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitClassdef(ClassdefContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterArglist(ArglistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitArglist(ArglistContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterArgument(ArgumentContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitArgument(ArgumentContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterList_iter(List_iterContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitList_iter(List_iterContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterList_for(List_forContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitList_for(List_forContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterList_if(List_ifContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitList_if(List_ifContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterComp_iter(Comp_iterContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitComp_iter(Comp_iterContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterComp_for(Comp_forContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitComp_for(Comp_forContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterComp_if(Comp_ifContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitComp_if(Comp_ifContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTestlist1(Testlist1Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitTestlist1(Testlist1Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterEncoding_decl(Encoding_declContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitEncoding_decl(Encoding_declContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterYield_expr(Yield_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitYield_expr(Yield_exprContext ctx) {
		// TODO Auto-generated method stub
		
	}

}
