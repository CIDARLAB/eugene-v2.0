// $ANTLR 3.5.1 /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g 2015-05-18 21:24:15

/* Copyright (c) 2015, Boston University
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list 
 * of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without specific prior 
 * written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.eugene.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class EugeneLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__140=140;
	public static final int T__141=141;
	public static final int T__142=142;
	public static final int T__143=143;
	public static final int T__144=144;
	public static final int T__145=145;
	public static final int T__146=146;
	public static final int T__147=147;
	public static final int T__148=148;
	public static final int T__149=149;
	public static final int T__150=150;
	public static final int T__151=151;
	public static final int T__152=152;
	public static final int T__153=153;
	public static final int T__154=154;
	public static final int T__155=155;
	public static final int T__156=156;
	public static final int T__157=157;
	public static final int T__158=158;
	public static final int T__159=159;
	public static final int T__160=160;
	public static final int T__161=161;
	public static final int T__162=162;
	public static final int T__163=163;
	public static final int T__164=164;
	public static final int T__165=165;
	public static final int T__166=166;
	public static final int T__167=167;
	public static final int T__168=168;
	public static final int T__169=169;
	public static final int T__170=170;
	public static final int T__171=171;
	public static final int T__172=172;
	public static final int T__173=173;
	public static final int T__174=174;
	public static final int T__175=175;
	public static final int T__176=176;
	public static final int T__177=177;
	public static final int T__178=178;
	public static final int T__179=179;
	public static final int T__180=180;
	public static final int T__181=181;
	public static final int T__182=182;
	public static final int T__183=183;
	public static final int T__184=184;
	public static final int T__185=185;
	public static final int T__186=186;
	public static final int T__187=187;
	public static final int T__188=188;
	public static final int T__189=189;
	public static final int T__190=190;
	public static final int T__191=191;
	public static final int T__192=192;
	public static final int T__193=193;
	public static final int T__194=194;
	public static final int T__195=195;
	public static final int T__196=196;
	public static final int T__197=197;
	public static final int T__198=198;
	public static final int T__199=199;
	public static final int T__200=200;
	public static final int T__201=201;
	public static final int T__202=202;
	public static final int T__203=203;
	public static final int T__204=204;
	public static final int T__205=205;
	public static final int T__206=206;
	public static final int T__207=207;
	public static final int T__208=208;
	public static final int T__209=209;
	public static final int T__210=210;
	public static final int T__211=211;
	public static final int T__212=212;
	public static final int T__213=213;
	public static final int T__214=214;
	public static final int T__215=215;
	public static final int T__216=216;
	public static final int T__217=217;
	public static final int ADDPROPS=4;
	public static final int AMP=5;
	public static final int ARRAY=6;
	public static final int ARROW=7;
	public static final int ASSERT=8;
	public static final int BOOL=9;
	public static final int BOOLEAN=10;
	public static final int COLLECTION=11;
	public static final int COLON=12;
	public static final int COMMA=13;
	public static final int CREATE_LC=14;
	public static final int CREATE_UC=15;
	public static final int DELETE_LC=16;
	public static final int DELETE_UC=17;
	public static final int DEVICE=18;
	public static final int DIGIT=19;
	public static final int DIV=20;
	public static final int DOLLAR=21;
	public static final int DOT=22;
	public static final int DOTDOT=23;
	public static final int EQUALS=24;
	public static final int EXIT_LC=25;
	public static final int EXIT_UC=26;
	public static final int EXPORT_LC=27;
	public static final int EXPORT_UC=28;
	public static final int FALSE_LC=29;
	public static final int FALSE_UC=30;
	public static final int FLEXIBLE=31;
	public static final int GENBANK=32;
	public static final int GEQUAL=33;
	public static final int GRAMMAR=34;
	public static final int GTHAN=35;
	public static final int HASHMARK=36;
	public static final int ID=37;
	public static final int IMAGE=38;
	public static final int IMPORT_LC=39;
	public static final int IMPORT_UC=40;
	public static final int INCLUDE_LC=41;
	public static final int INCLUDE_UC=42;
	public static final int INTERACTION=43;
	public static final int LC_AND=44;
	public static final int LC_ELSE=45;
	public static final int LC_ELSEIF=46;
	public static final int LC_FOR=47;
	public static final int LC_FORALL=48;
	public static final int LC_IF=49;
	public static final int LC_INDUCES=50;
	public static final int LC_NOT=51;
	public static final int LC_ON=52;
	public static final int LC_OR=53;
	public static final int LC_PERMUTE=54;
	public static final int LC_PRODUCT=55;
	public static final int LC_REPRESSES=56;
	public static final int LC_SEQUENCE_OF=57;
	public static final int LC_WHILE=58;
	public static final int LEFTCUR=59;
	public static final int LEFTP=60;
	public static final int LEFTSBR=61;
	public static final int LEQUAL=62;
	public static final int LINE_COMMENT=63;
	public static final int LOG_AND=64;
	public static final int LOG_OR=65;
	public static final int LTHAN=66;
	public static final int MINUS=67;
	public static final int ML_COMMENT=68;
	public static final int MULT=69;
	public static final int NEQUAL=70;
	public static final int NEWLINE=71;
	public static final int NOTE=72;
	public static final int NUM=73;
	public static final int NUMBER=74;
	public static final int OP_NOT=75;
	public static final int PART=76;
	public static final int PART_TYPE=77;
	public static final int PIPE=78;
	public static final int PLUS=79;
	public static final int PRINTLN_LC=80;
	public static final int PRINTLN_UC=81;
	public static final int PRINT_LC=82;
	public static final int PRINT_UC=83;
	public static final int PROPERTY=84;
	public static final int QUERY_LC=85;
	public static final int QUERY_UC=86;
	public static final int RANDOM_LC=87;
	public static final int RANDOM_UC=88;
	public static final int READ_LC=89;
	public static final int READ_UC=90;
	public static final int REAL=91;
	public static final int REF=92;
	public static final int REGISTRY=93;
	public static final int RETURN_LC=94;
	public static final int RETURN_UC=95;
	public static final int RIGHTCUR=96;
	public static final int RIGHTP=97;
	public static final int RIGHTSBR=98;
	public static final int RULE=99;
	public static final int RULE_BUILDER=100;
	public static final int SAVE_LC=101;
	public static final int SAVE_UC=102;
	public static final int SBOL=103;
	public static final int SEMIC=104;
	public static final int SIZEOF_LC=105;
	public static final int SIZEOF_UC=106;
	public static final int SIZE_LC=107;
	public static final int SIZE_OF_LC=108;
	public static final int SIZE_OF_UC=109;
	public static final int SIZE_UC=110;
	public static final int STORE_LC=111;
	public static final int STORE_UC=112;
	public static final int STRICT=113;
	public static final int STRING=114;
	public static final int TRUE_LC=115;
	public static final int TRUE_UC=116;
	public static final int TXT=117;
	public static final int TYPE=118;
	public static final int UC_AND=119;
	public static final int UC_ELSE=120;
	public static final int UC_ELSEIF=121;
	public static final int UC_FOR=122;
	public static final int UC_FORALL=123;
	public static final int UC_IF=124;
	public static final int UC_INDUCES=125;
	public static final int UC_NOT=126;
	public static final int UC_ON=127;
	public static final int UC_OR=128;
	public static final int UC_PERMUTE=129;
	public static final int UC_PRODUCT=130;
	public static final int UC_REPRESSES=131;
	public static final int UC_SEQUENCE_OF=132;
	public static final int UC_WHILE=133;
	public static final int UNDERS=134;
	public static final int UPDATE_LC=135;
	public static final int UPDATE_UC=136;
	public static final int VISUALIZE_LC=137;
	public static final int VISUALIZE_UC=138;
	public static final int WS=139;

	class SaveStruct {
	    public CharStream input;
	    public int marker;
	    SaveStruct(CharStream input) {
	        this.input = input;
	        this.marker = input.mark();
	    }
	}
	 
	Stack<SaveStruct> includes = new Stack<SaveStruct>();
	 
	// We should override this method for handling EOF of included file
	public Token nextToken(){
	    Token token = super.nextToken();
	 
	    if(token.getType() == Token.EOF && !includes.empty()){
	        // We've got EOF and have non empty stack
	        SaveStruct ss = includes.pop();
	        setCharStream(ss.input);
	        input.rewind(ss.marker);

	        //this should be used instead of super [like below] to handle exits from nested includes
	        //it matters, when the 'include' token is the last in previous stream (using super, lexer 'crashes' returning EOF token)
	        token = this.nextToken();
	    }
	 
	    // Skip first token after switching on another input.
	    // You need to use this rather than super as there may be nested include files
	    if(((CommonToken)token).getStartIndex() < 0) {
	        token = this.nextToken();
	    }
	    
	    return token;
	}

	public void includeFile(String name) {
	    try {
	        // save current lexer's state
	        SaveStruct ss = new SaveStruct(input);
	        includes.push(ss);
	 
	        // switch on new input stream
	        setCharStream(new ANTLRFileStream(name));
	        reset();
	    } catch (Exception fnf) {
	        throw new IllegalArgumentException(fnf.getMessage());
	        //fnf.printStackTrace();
	    }
	}


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public EugeneLexer() {} 
	public EugeneLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public EugeneLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g"; }

	// $ANTLR start "ADDPROPS"
	public final void mADDPROPS() throws RecognitionException {
		try {
			int _type = ADDPROPS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:91:10: ( 'addProperties' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:91:12: 'addProperties'
			{
			match("addProperties"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ADDPROPS"

	// $ANTLR start "AMP"
	public final void mAMP() throws RecognitionException {
		try {
			int _type = AMP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:92:5: ( '&' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:92:7: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AMP"

	// $ANTLR start "ARRAY"
	public final void mARRAY() throws RecognitionException {
		try {
			int _type = ARRAY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:93:7: ( 'Array' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:93:9: 'Array'
			{
			match("Array"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARRAY"

	// $ANTLR start "ARROW"
	public final void mARROW() throws RecognitionException {
		try {
			int _type = ARROW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:94:7: ( '-->' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:94:9: '-->'
			{
			match("-->"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARROW"

	// $ANTLR start "ASSERT"
	public final void mASSERT() throws RecognitionException {
		try {
			int _type = ASSERT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:95:8: ( 'Assert' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:95:10: 'Assert'
			{
			match("Assert"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSERT"

	// $ANTLR start "BOOL"
	public final void mBOOL() throws RecognitionException {
		try {
			int _type = BOOL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:96:6: ( 'bool' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:96:8: 'bool'
			{
			match("bool"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOOL"

	// $ANTLR start "BOOLEAN"
	public final void mBOOLEAN() throws RecognitionException {
		try {
			int _type = BOOLEAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:97:9: ( 'boolean' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:97:11: 'boolean'
			{
			match("boolean"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOOLEAN"

	// $ANTLR start "COLLECTION"
	public final void mCOLLECTION() throws RecognitionException {
		try {
			int _type = COLLECTION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:98:12: ( 'Collection' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:98:14: 'Collection'
			{
			match("Collection"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLLECTION"

	// $ANTLR start "COLON"
	public final void mCOLON() throws RecognitionException {
		try {
			int _type = COLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:99:7: ( ':' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:99:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:100:7: ( ',' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:100:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "CREATE_LC"
	public final void mCREATE_LC() throws RecognitionException {
		try {
			int _type = CREATE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:101:11: ( 'create' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:101:13: 'create'
			{
			match("create"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CREATE_LC"

	// $ANTLR start "CREATE_UC"
	public final void mCREATE_UC() throws RecognitionException {
		try {
			int _type = CREATE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:102:11: ( 'CREATE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:102:13: 'CREATE'
			{
			match("CREATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CREATE_UC"

	// $ANTLR start "DELETE_LC"
	public final void mDELETE_LC() throws RecognitionException {
		try {
			int _type = DELETE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:103:11: ( 'delete' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:103:13: 'delete'
			{
			match("delete"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DELETE_LC"

	// $ANTLR start "DELETE_UC"
	public final void mDELETE_UC() throws RecognitionException {
		try {
			int _type = DELETE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:104:11: ( 'DELETE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:104:13: 'DELETE'
			{
			match("DELETE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DELETE_UC"

	// $ANTLR start "DEVICE"
	public final void mDEVICE() throws RecognitionException {
		try {
			int _type = DEVICE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:105:8: ( 'Device' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:105:10: 'Device'
			{
			match("Device"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DEVICE"

	// $ANTLR start "DIV"
	public final void mDIV() throws RecognitionException {
		try {
			int _type = DIV;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:106:5: ( '/' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:106:7: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIV"

	// $ANTLR start "DOLLAR"
	public final void mDOLLAR() throws RecognitionException {
		try {
			int _type = DOLLAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:107:8: ( '$' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:107:10: '$'
			{
			match('$'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOLLAR"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:108:5: ( '.' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:108:7: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "DOTDOT"
	public final void mDOTDOT() throws RecognitionException {
		try {
			int _type = DOTDOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:109:8: ( '..' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:109:10: '..'
			{
			match(".."); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOTDOT"

	// $ANTLR start "EQUALS"
	public final void mEQUALS() throws RecognitionException {
		try {
			int _type = EQUALS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:110:8: ( '=' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:110:10: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQUALS"

	// $ANTLR start "EXIT_LC"
	public final void mEXIT_LC() throws RecognitionException {
		try {
			int _type = EXIT_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:111:9: ( 'exit' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:111:11: 'exit'
			{
			match("exit"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXIT_LC"

	// $ANTLR start "EXIT_UC"
	public final void mEXIT_UC() throws RecognitionException {
		try {
			int _type = EXIT_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:112:9: ( 'EXIT' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:112:11: 'EXIT'
			{
			match("EXIT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXIT_UC"

	// $ANTLR start "EXPORT_LC"
	public final void mEXPORT_LC() throws RecognitionException {
		try {
			int _type = EXPORT_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:113:11: ( 'export' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:113:13: 'export'
			{
			match("export"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXPORT_LC"

	// $ANTLR start "EXPORT_UC"
	public final void mEXPORT_UC() throws RecognitionException {
		try {
			int _type = EXPORT_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:114:11: ( 'EXPORT' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:114:13: 'EXPORT'
			{
			match("EXPORT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXPORT_UC"

	// $ANTLR start "FALSE_LC"
	public final void mFALSE_LC() throws RecognitionException {
		try {
			int _type = FALSE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:115:10: ( 'false' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:115:12: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE_LC"

	// $ANTLR start "FALSE_UC"
	public final void mFALSE_UC() throws RecognitionException {
		try {
			int _type = FALSE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:116:10: ( 'FALSE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:116:12: 'FALSE'
			{
			match("FALSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE_UC"

	// $ANTLR start "FLEXIBLE"
	public final void mFLEXIBLE() throws RecognitionException {
		try {
			int _type = FLEXIBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:117:10: ( 'flexible' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:117:12: 'flexible'
			{
			match("flexible"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLEXIBLE"

	// $ANTLR start "GENBANK"
	public final void mGENBANK() throws RecognitionException {
		try {
			int _type = GENBANK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:118:9: ( 'Genbank' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:118:11: 'Genbank'
			{
			match("Genbank"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GENBANK"

	// $ANTLR start "GEQUAL"
	public final void mGEQUAL() throws RecognitionException {
		try {
			int _type = GEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:119:8: ( '>=' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:119:10: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GEQUAL"

	// $ANTLR start "GRAMMAR"
	public final void mGRAMMAR() throws RecognitionException {
		try {
			int _type = GRAMMAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:120:9: ( 'Grammar' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:120:11: 'Grammar'
			{
			match("Grammar"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GRAMMAR"

	// $ANTLR start "GTHAN"
	public final void mGTHAN() throws RecognitionException {
		try {
			int _type = GTHAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:121:7: ( '>' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:121:9: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GTHAN"

	// $ANTLR start "HASHMARK"
	public final void mHASHMARK() throws RecognitionException {
		try {
			int _type = HASHMARK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:122:10: ( '#' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:122:12: '#'
			{
			match('#'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HASHMARK"

	// $ANTLR start "IMAGE"
	public final void mIMAGE() throws RecognitionException {
		try {
			int _type = IMAGE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:123:7: ( 'Image' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:123:9: 'Image'
			{
			match("Image"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IMAGE"

	// $ANTLR start "IMPORT_LC"
	public final void mIMPORT_LC() throws RecognitionException {
		try {
			int _type = IMPORT_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:124:11: ( 'import' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:124:13: 'import'
			{
			match("import"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IMPORT_LC"

	// $ANTLR start "IMPORT_UC"
	public final void mIMPORT_UC() throws RecognitionException {
		try {
			int _type = IMPORT_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:125:11: ( 'IMPORT' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:125:13: 'IMPORT'
			{
			match("IMPORT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IMPORT_UC"

	// $ANTLR start "INCLUDE_LC"
	public final void mINCLUDE_LC() throws RecognitionException {
		try {
			int _type = INCLUDE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:126:12: ( 'include' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:126:14: 'include'
			{
			match("include"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INCLUDE_LC"

	// $ANTLR start "INCLUDE_UC"
	public final void mINCLUDE_UC() throws RecognitionException {
		try {
			int _type = INCLUDE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:127:12: ( 'INCLUDE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:127:14: 'INCLUDE'
			{
			match("INCLUDE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INCLUDE_UC"

	// $ANTLR start "INTERACTION"
	public final void mINTERACTION() throws RecognitionException {
		try {
			int _type = INTERACTION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:128:13: ( 'Interaction' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:128:15: 'Interaction'
			{
			match("Interaction"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTERACTION"

	// $ANTLR start "LC_AND"
	public final void mLC_AND() throws RecognitionException {
		try {
			int _type = LC_AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:129:8: ( 'and' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:129:10: 'and'
			{
			match("and"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_AND"

	// $ANTLR start "LC_ELSE"
	public final void mLC_ELSE() throws RecognitionException {
		try {
			int _type = LC_ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:130:9: ( 'else' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:130:11: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_ELSE"

	// $ANTLR start "LC_ELSEIF"
	public final void mLC_ELSEIF() throws RecognitionException {
		try {
			int _type = LC_ELSEIF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:131:11: ( 'elseif' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:131:13: 'elseif'
			{
			match("elseif"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_ELSEIF"

	// $ANTLR start "LC_FOR"
	public final void mLC_FOR() throws RecognitionException {
		try {
			int _type = LC_FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:132:8: ( 'for' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:132:10: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_FOR"

	// $ANTLR start "LC_FORALL"
	public final void mLC_FORALL() throws RecognitionException {
		try {
			int _type = LC_FORALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:133:11: ( 'forall' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:133:13: 'forall'
			{
			match("forall"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_FORALL"

	// $ANTLR start "LC_IF"
	public final void mLC_IF() throws RecognitionException {
		try {
			int _type = LC_IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:134:7: ( 'if' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:134:9: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_IF"

	// $ANTLR start "LC_INDUCES"
	public final void mLC_INDUCES() throws RecognitionException {
		try {
			int _type = LC_INDUCES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:135:12: ( 'induces' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:135:14: 'induces'
			{
			match("induces"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_INDUCES"

	// $ANTLR start "LC_NOT"
	public final void mLC_NOT() throws RecognitionException {
		try {
			int _type = LC_NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:136:8: ( 'not' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:136:10: 'not'
			{
			match("not"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_NOT"

	// $ANTLR start "LC_ON"
	public final void mLC_ON() throws RecognitionException {
		try {
			int _type = LC_ON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:137:7: ( 'on' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:137:9: 'on'
			{
			match("on"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_ON"

	// $ANTLR start "LC_OR"
	public final void mLC_OR() throws RecognitionException {
		try {
			int _type = LC_OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:138:7: ( 'or' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:138:9: 'or'
			{
			match("or"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_OR"

	// $ANTLR start "LC_PERMUTE"
	public final void mLC_PERMUTE() throws RecognitionException {
		try {
			int _type = LC_PERMUTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:139:12: ( 'permute' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:139:14: 'permute'
			{
			match("permute"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_PERMUTE"

	// $ANTLR start "LC_PRODUCT"
	public final void mLC_PRODUCT() throws RecognitionException {
		try {
			int _type = LC_PRODUCT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:140:12: ( 'product' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:140:14: 'product'
			{
			match("product"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_PRODUCT"

	// $ANTLR start "LC_REPRESSES"
	public final void mLC_REPRESSES() throws RecognitionException {
		try {
			int _type = LC_REPRESSES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:141:14: ( 'represses' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:141:16: 'represses'
			{
			match("represses"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_REPRESSES"

	// $ANTLR start "LC_SEQUENCE_OF"
	public final void mLC_SEQUENCE_OF() throws RecognitionException {
		try {
			int _type = LC_SEQUENCE_OF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:142:16: ( 'sequence_of' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:142:18: 'sequence_of'
			{
			match("sequence_of"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_SEQUENCE_OF"

	// $ANTLR start "LC_WHILE"
	public final void mLC_WHILE() throws RecognitionException {
		try {
			int _type = LC_WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:143:10: ( 'while' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:143:12: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LC_WHILE"

	// $ANTLR start "LEFTCUR"
	public final void mLEFTCUR() throws RecognitionException {
		try {
			int _type = LEFTCUR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:144:9: ( '{' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:144:11: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEFTCUR"

	// $ANTLR start "LEFTP"
	public final void mLEFTP() throws RecognitionException {
		try {
			int _type = LEFTP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:145:7: ( '(' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:145:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEFTP"

	// $ANTLR start "LEFTSBR"
	public final void mLEFTSBR() throws RecognitionException {
		try {
			int _type = LEFTSBR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:146:9: ( '[' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:146:11: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEFTSBR"

	// $ANTLR start "LEQUAL"
	public final void mLEQUAL() throws RecognitionException {
		try {
			int _type = LEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:147:8: ( '<=' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:147:10: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEQUAL"

	// $ANTLR start "LOG_AND"
	public final void mLOG_AND() throws RecognitionException {
		try {
			int _type = LOG_AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:148:9: ( '/\\\\' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:148:11: '/\\\\'
			{
			match("/\\"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOG_AND"

	// $ANTLR start "LOG_OR"
	public final void mLOG_OR() throws RecognitionException {
		try {
			int _type = LOG_OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:149:8: ( '\\\\/' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:149:10: '\\\\/'
			{
			match("\\/"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOG_OR"

	// $ANTLR start "LTHAN"
	public final void mLTHAN() throws RecognitionException {
		try {
			int _type = LTHAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:150:7: ( '<' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:150:9: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LTHAN"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:151:7: ( '-' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:151:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "MULT"
	public final void mMULT() throws RecognitionException {
		try {
			int _type = MULT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:152:6: ( '*' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:152:8: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MULT"

	// $ANTLR start "NEQUAL"
	public final void mNEQUAL() throws RecognitionException {
		try {
			int _type = NEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:153:8: ( '!=' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:153:10: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEQUAL"

	// $ANTLR start "NOTE"
	public final void mNOTE() throws RecognitionException {
		try {
			int _type = NOTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:154:6: ( 'Note' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:154:8: 'Note'
			{
			match("Note"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOTE"

	// $ANTLR start "NUM"
	public final void mNUM() throws RecognitionException {
		try {
			int _type = NUM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:155:5: ( 'num' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:155:7: 'num'
			{
			match("num"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUM"

	// $ANTLR start "OP_NOT"
	public final void mOP_NOT() throws RecognitionException {
		try {
			int _type = OP_NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:156:8: ( '!' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:156:10: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OP_NOT"

	// $ANTLR start "PART"
	public final void mPART() throws RecognitionException {
		try {
			int _type = PART;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:157:6: ( 'Part' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:157:8: 'Part'
			{
			match("Part"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PART"

	// $ANTLR start "PART_TYPE"
	public final void mPART_TYPE() throws RecognitionException {
		try {
			int _type = PART_TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:158:11: ( 'PartType' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:158:13: 'PartType'
			{
			match("PartType"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PART_TYPE"

	// $ANTLR start "PIPE"
	public final void mPIPE() throws RecognitionException {
		try {
			int _type = PIPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:159:6: ( '|' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:159:8: '|'
			{
			match('|'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PIPE"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:160:6: ( '+' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:160:8: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "PRINTLN_LC"
	public final void mPRINTLN_LC() throws RecognitionException {
		try {
			int _type = PRINTLN_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:161:12: ( 'println' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:161:14: 'println'
			{
			match("println"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRINTLN_LC"

	// $ANTLR start "PRINTLN_UC"
	public final void mPRINTLN_UC() throws RecognitionException {
		try {
			int _type = PRINTLN_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:162:12: ( 'PRINTLN' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:162:14: 'PRINTLN'
			{
			match("PRINTLN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRINTLN_UC"

	// $ANTLR start "PRINT_LC"
	public final void mPRINT_LC() throws RecognitionException {
		try {
			int _type = PRINT_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:163:10: ( 'print' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:163:12: 'print'
			{
			match("print"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRINT_LC"

	// $ANTLR start "PRINT_UC"
	public final void mPRINT_UC() throws RecognitionException {
		try {
			int _type = PRINT_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:164:10: ( 'PRINT' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:164:12: 'PRINT'
			{
			match("PRINT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRINT_UC"

	// $ANTLR start "PROPERTY"
	public final void mPROPERTY() throws RecognitionException {
		try {
			int _type = PROPERTY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:165:10: ( 'Property' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:165:12: 'Property'
			{
			match("Property"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PROPERTY"

	// $ANTLR start "QUERY_LC"
	public final void mQUERY_LC() throws RecognitionException {
		try {
			int _type = QUERY_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:166:10: ( 'query' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:166:12: 'query'
			{
			match("query"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "QUERY_LC"

	// $ANTLR start "QUERY_UC"
	public final void mQUERY_UC() throws RecognitionException {
		try {
			int _type = QUERY_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:167:10: ( 'QUERY' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:167:12: 'QUERY'
			{
			match("QUERY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "QUERY_UC"

	// $ANTLR start "RANDOM_LC"
	public final void mRANDOM_LC() throws RecognitionException {
		try {
			int _type = RANDOM_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:168:11: ( 'random' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:168:13: 'random'
			{
			match("random"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RANDOM_LC"

	// $ANTLR start "RANDOM_UC"
	public final void mRANDOM_UC() throws RecognitionException {
		try {
			int _type = RANDOM_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:169:11: ( 'RANDOM' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:169:13: 'RANDOM'
			{
			match("RANDOM"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RANDOM_UC"

	// $ANTLR start "READ_LC"
	public final void mREAD_LC() throws RecognitionException {
		try {
			int _type = READ_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:170:9: ( 'read' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:170:11: 'read'
			{
			match("read"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "READ_LC"

	// $ANTLR start "READ_UC"
	public final void mREAD_UC() throws RecognitionException {
		try {
			int _type = READ_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:171:9: ( 'READ' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:171:11: 'READ'
			{
			match("READ"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "READ_UC"

	// $ANTLR start "REF"
	public final void mREF() throws RecognitionException {
		try {
			int _type = REF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:172:5: ( 'ref' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:172:7: 'ref'
			{
			match("ref"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REF"

	// $ANTLR start "REGISTRY"
	public final void mREGISTRY() throws RecognitionException {
		try {
			int _type = REGISTRY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:173:10: ( 'Registry' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:173:12: 'Registry'
			{
			match("Registry"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REGISTRY"

	// $ANTLR start "RETURN_LC"
	public final void mRETURN_LC() throws RecognitionException {
		try {
			int _type = RETURN_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:174:11: ( 'return' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:174:13: 'return'
			{
			match("return"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RETURN_LC"

	// $ANTLR start "RETURN_UC"
	public final void mRETURN_UC() throws RecognitionException {
		try {
			int _type = RETURN_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:175:11: ( 'RETURN' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:175:13: 'RETURN'
			{
			match("RETURN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RETURN_UC"

	// $ANTLR start "RIGHTCUR"
	public final void mRIGHTCUR() throws RecognitionException {
		try {
			int _type = RIGHTCUR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:176:10: ( '}' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:176:12: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RIGHTCUR"

	// $ANTLR start "RIGHTP"
	public final void mRIGHTP() throws RecognitionException {
		try {
			int _type = RIGHTP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:177:8: ( ')' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:177:10: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RIGHTP"

	// $ANTLR start "RIGHTSBR"
	public final void mRIGHTSBR() throws RecognitionException {
		try {
			int _type = RIGHTSBR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:178:10: ( ']' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:178:12: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RIGHTSBR"

	// $ANTLR start "RULE"
	public final void mRULE() throws RecognitionException {
		try {
			int _type = RULE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:179:6: ( 'Rule' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:179:8: 'Rule'
			{
			match("Rule"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RULE"

	// $ANTLR start "RULE_BUILDER"
	public final void mRULE_BUILDER() throws RecognitionException {
		try {
			int _type = RULE_BUILDER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:180:14: ( 'RuleBuilder' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:180:16: 'RuleBuilder'
			{
			match("RuleBuilder"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RULE_BUILDER"

	// $ANTLR start "SAVE_LC"
	public final void mSAVE_LC() throws RecognitionException {
		try {
			int _type = SAVE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:181:9: ( 'save' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:181:11: 'save'
			{
			match("save"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SAVE_LC"

	// $ANTLR start "SAVE_UC"
	public final void mSAVE_UC() throws RecognitionException {
		try {
			int _type = SAVE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:182:9: ( 'SAVE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:182:11: 'SAVE'
			{
			match("SAVE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SAVE_UC"

	// $ANTLR start "SBOL"
	public final void mSBOL() throws RecognitionException {
		try {
			int _type = SBOL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:183:6: ( 'SBOL' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:183:8: 'SBOL'
			{
			match("SBOL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SBOL"

	// $ANTLR start "SEMIC"
	public final void mSEMIC() throws RecognitionException {
		try {
			int _type = SEMIC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:184:7: ( ';' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:184:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMIC"

	// $ANTLR start "SIZEOF_LC"
	public final void mSIZEOF_LC() throws RecognitionException {
		try {
			int _type = SIZEOF_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:185:11: ( 'sizeof' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:185:13: 'sizeof'
			{
			match("sizeof"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SIZEOF_LC"

	// $ANTLR start "SIZEOF_UC"
	public final void mSIZEOF_UC() throws RecognitionException {
		try {
			int _type = SIZEOF_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:186:11: ( 'SIZEOF' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:186:13: 'SIZEOF'
			{
			match("SIZEOF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SIZEOF_UC"

	// $ANTLR start "SIZE_LC"
	public final void mSIZE_LC() throws RecognitionException {
		try {
			int _type = SIZE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:187:9: ( 'size' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:187:11: 'size'
			{
			match("size"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SIZE_LC"

	// $ANTLR start "SIZE_OF_LC"
	public final void mSIZE_OF_LC() throws RecognitionException {
		try {
			int _type = SIZE_OF_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:188:12: ( 'size_of' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:188:14: 'size_of'
			{
			match("size_of"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SIZE_OF_LC"

	// $ANTLR start "SIZE_OF_UC"
	public final void mSIZE_OF_UC() throws RecognitionException {
		try {
			int _type = SIZE_OF_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:189:12: ( 'SIZE_OF' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:189:14: 'SIZE_OF'
			{
			match("SIZE_OF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SIZE_OF_UC"

	// $ANTLR start "SIZE_UC"
	public final void mSIZE_UC() throws RecognitionException {
		try {
			int _type = SIZE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:190:9: ( 'SIZE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:190:11: 'SIZE'
			{
			match("SIZE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SIZE_UC"

	// $ANTLR start "STORE_LC"
	public final void mSTORE_LC() throws RecognitionException {
		try {
			int _type = STORE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:191:10: ( 'store' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:191:12: 'store'
			{
			match("store"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STORE_LC"

	// $ANTLR start "STORE_UC"
	public final void mSTORE_UC() throws RecognitionException {
		try {
			int _type = STORE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:192:10: ( 'STORE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:192:12: 'STORE'
			{
			match("STORE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STORE_UC"

	// $ANTLR start "STRICT"
	public final void mSTRICT() throws RecognitionException {
		try {
			int _type = STRICT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:193:8: ( 'strict' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:193:10: 'strict'
			{
			match("strict"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRICT"

	// $ANTLR start "TRUE_LC"
	public final void mTRUE_LC() throws RecognitionException {
		try {
			int _type = TRUE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:194:9: ( 'true' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:194:11: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE_LC"

	// $ANTLR start "TRUE_UC"
	public final void mTRUE_UC() throws RecognitionException {
		try {
			int _type = TRUE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:195:9: ( 'TRUE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:195:11: 'TRUE'
			{
			match("TRUE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE_UC"

	// $ANTLR start "TXT"
	public final void mTXT() throws RecognitionException {
		try {
			int _type = TXT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:196:5: ( 'txt' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:196:7: 'txt'
			{
			match("txt"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TXT"

	// $ANTLR start "TYPE"
	public final void mTYPE() throws RecognitionException {
		try {
			int _type = TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:197:6: ( 'Type' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:197:8: 'Type'
			{
			match("Type"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TYPE"

	// $ANTLR start "UC_AND"
	public final void mUC_AND() throws RecognitionException {
		try {
			int _type = UC_AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:198:8: ( 'AND' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:198:10: 'AND'
			{
			match("AND"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_AND"

	// $ANTLR start "UC_ELSE"
	public final void mUC_ELSE() throws RecognitionException {
		try {
			int _type = UC_ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:199:9: ( 'ELSE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:199:11: 'ELSE'
			{
			match("ELSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_ELSE"

	// $ANTLR start "UC_ELSEIF"
	public final void mUC_ELSEIF() throws RecognitionException {
		try {
			int _type = UC_ELSEIF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:200:11: ( 'ELSEIF' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:200:13: 'ELSEIF'
			{
			match("ELSEIF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_ELSEIF"

	// $ANTLR start "UC_FOR"
	public final void mUC_FOR() throws RecognitionException {
		try {
			int _type = UC_FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:201:8: ( 'FOR' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:201:10: 'FOR'
			{
			match("FOR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_FOR"

	// $ANTLR start "UC_FORALL"
	public final void mUC_FORALL() throws RecognitionException {
		try {
			int _type = UC_FORALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:202:11: ( 'FORALL' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:202:13: 'FORALL'
			{
			match("FORALL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_FORALL"

	// $ANTLR start "UC_IF"
	public final void mUC_IF() throws RecognitionException {
		try {
			int _type = UC_IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:203:7: ( 'IF' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:203:9: 'IF'
			{
			match("IF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_IF"

	// $ANTLR start "UC_INDUCES"
	public final void mUC_INDUCES() throws RecognitionException {
		try {
			int _type = UC_INDUCES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:204:12: ( 'INDUCES' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:204:14: 'INDUCES'
			{
			match("INDUCES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_INDUCES"

	// $ANTLR start "UC_NOT"
	public final void mUC_NOT() throws RecognitionException {
		try {
			int _type = UC_NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:205:8: ( 'NOT' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:205:10: 'NOT'
			{
			match("NOT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_NOT"

	// $ANTLR start "UC_ON"
	public final void mUC_ON() throws RecognitionException {
		try {
			int _type = UC_ON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:206:7: ( 'ON' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:206:9: 'ON'
			{
			match("ON"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_ON"

	// $ANTLR start "UC_OR"
	public final void mUC_OR() throws RecognitionException {
		try {
			int _type = UC_OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:207:7: ( 'OR' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:207:9: 'OR'
			{
			match("OR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_OR"

	// $ANTLR start "UC_PERMUTE"
	public final void mUC_PERMUTE() throws RecognitionException {
		try {
			int _type = UC_PERMUTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:208:12: ( 'PERMUTE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:208:14: 'PERMUTE'
			{
			match("PERMUTE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_PERMUTE"

	// $ANTLR start "UC_PRODUCT"
	public final void mUC_PRODUCT() throws RecognitionException {
		try {
			int _type = UC_PRODUCT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:209:12: ( 'PRODUCT' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:209:14: 'PRODUCT'
			{
			match("PRODUCT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_PRODUCT"

	// $ANTLR start "UC_REPRESSES"
	public final void mUC_REPRESSES() throws RecognitionException {
		try {
			int _type = UC_REPRESSES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:210:14: ( 'REPRESSES' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:210:16: 'REPRESSES'
			{
			match("REPRESSES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_REPRESSES"

	// $ANTLR start "UC_SEQUENCE_OF"
	public final void mUC_SEQUENCE_OF() throws RecognitionException {
		try {
			int _type = UC_SEQUENCE_OF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:211:16: ( 'SEQUENCE_OF' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:211:18: 'SEQUENCE_OF'
			{
			match("SEQUENCE_OF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_SEQUENCE_OF"

	// $ANTLR start "UC_WHILE"
	public final void mUC_WHILE() throws RecognitionException {
		try {
			int _type = UC_WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:212:10: ( 'WHILE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:212:12: 'WHILE'
			{
			match("WHILE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UC_WHILE"

	// $ANTLR start "UNDERS"
	public final void mUNDERS() throws RecognitionException {
		try {
			int _type = UNDERS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:213:8: ( '_' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:213:10: '_'
			{
			match('_'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNDERS"

	// $ANTLR start "UPDATE_LC"
	public final void mUPDATE_LC() throws RecognitionException {
		try {
			int _type = UPDATE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:214:11: ( 'update' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:214:13: 'update'
			{
			match("update"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UPDATE_LC"

	// $ANTLR start "UPDATE_UC"
	public final void mUPDATE_UC() throws RecognitionException {
		try {
			int _type = UPDATE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:215:11: ( 'UPDATE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:215:13: 'UPDATE'
			{
			match("UPDATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UPDATE_UC"

	// $ANTLR start "VISUALIZE_LC"
	public final void mVISUALIZE_LC() throws RecognitionException {
		try {
			int _type = VISUALIZE_LC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:216:14: ( 'visualize' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:216:16: 'visualize'
			{
			match("visualize"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VISUALIZE_LC"

	// $ANTLR start "VISUALIZE_UC"
	public final void mVISUALIZE_UC() throws RecognitionException {
		try {
			int _type = VISUALIZE_UC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:217:14: ( 'VISUALIZE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:217:16: 'VISUALIZE'
			{
			match("VISUALIZE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VISUALIZE_UC"

	// $ANTLR start "T__140"
	public final void mT__140() throws RecognitionException {
		try {
			int _type = T__140;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:218:8: ( 'AFTER' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:218:10: 'AFTER'
			{
			match("AFTER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__140"

	// $ANTLR start "T__141"
	public final void mT__141() throws RecognitionException {
		try {
			int _type = T__141;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:219:8: ( 'ALL_AFTER' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:219:10: 'ALL_AFTER'
			{
			match("ALL_AFTER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__141"

	// $ANTLR start "T__142"
	public final void mT__142() throws RecognitionException {
		try {
			int _type = T__142;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:220:8: ( 'ALL_BEFORE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:220:10: 'ALL_BEFORE'
			{
			match("ALL_BEFORE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__142"

	// $ANTLR start "T__143"
	public final void mT__143() throws RecognitionException {
		try {
			int _type = T__143;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:221:8: ( 'ALL_FORWARD' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:221:10: 'ALL_FORWARD'
			{
			match("ALL_FORWARD"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__143"

	// $ANTLR start "T__144"
	public final void mT__144() throws RecognitionException {
		try {
			int _type = T__144;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:222:8: ( 'ALL_NEXTTO' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:222:10: 'ALL_NEXTTO'
			{
			match("ALL_NEXTTO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__144"

	// $ANTLR start "T__145"
	public final void mT__145() throws RecognitionException {
		try {
			int _type = T__145;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:223:8: ( 'ALL_REVERSE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:223:10: 'ALL_REVERSE'
			{
			match("ALL_REVERSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__145"

	// $ANTLR start "T__146"
	public final void mT__146() throws RecognitionException {
		try {
			int _type = T__146;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:224:8: ( 'ALL_SAME_ORIENTATION' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:224:10: 'ALL_SAME_ORIENTATION'
			{
			match("ALL_SAME_ORIENTATION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__146"

	// $ANTLR start "T__147"
	public final void mT__147() throws RecognitionException {
		try {
			int _type = T__147;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:225:8: ( 'ALTERNATE_ORIENTATION' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:225:10: 'ALTERNATE_ORIENTATION'
			{
			match("ALTERNATE_ORIENTATION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__147"

	// $ANTLR start "T__148"
	public final void mT__148() throws RecognitionException {
		try {
			int _type = T__148;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:226:8: ( 'ALWAYS_NEXTTO' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:226:10: 'ALWAYS_NEXTTO'
			{
			match("ALWAYS_NEXTTO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__148"

	// $ANTLR start "T__149"
	public final void mT__149() throws RecognitionException {
		try {
			int _type = T__149;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:227:8: ( 'BEFORE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:227:10: 'BEFORE'
			{
			match("BEFORE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__149"

	// $ANTLR start "T__150"
	public final void mT__150() throws RecognitionException {
		try {
			int _type = T__150;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:228:8: ( 'CONTAINS' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:228:10: 'CONTAINS'
			{
			match("CONTAINS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__150"

	// $ANTLR start "T__151"
	public final void mT__151() throws RecognitionException {
		try {
			int _type = T__151;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:229:8: ( 'DRIVES' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:229:10: 'DRIVES'
			{
			match("DRIVES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__151"

	// $ANTLR start "T__152"
	public final void mT__152() throws RecognitionException {
		try {
			int _type = T__152;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:230:8: ( 'ENDSWITH' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:230:10: 'ENDSWITH'
			{
			match("ENDSWITH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__152"

	// $ANTLR start "T__153"
	public final void mT__153() throws RecognitionException {
		try {
			int _type = T__153;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:231:8: ( 'EQUALS' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:231:10: 'EQUALS'
			{
			match("EQUALS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__153"

	// $ANTLR start "T__154"
	public final void mT__154() throws RecognitionException {
		try {
			int _type = T__154;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:232:8: ( 'EXACTLY' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:232:10: 'EXACTLY'
			{
			match("EXACTLY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__154"

	// $ANTLR start "T__155"
	public final void mT__155() throws RecognitionException {
		try {
			int _type = T__155;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:233:8: ( 'FORWARD' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:233:10: 'FORWARD'
			{
			match("FORWARD"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__155"

	// $ANTLR start "T__156"
	public final void mT__156() throws RecognitionException {
		try {
			int _type = T__156;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:234:8: ( 'MATCHES' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:234:10: 'MATCHES'
			{
			match("MATCHES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__156"

	// $ANTLR start "T__157"
	public final void mT__157() throws RecognitionException {
		try {
			int _type = T__157;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:235:8: ( 'MORETHAN' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:235:10: 'MORETHAN'
			{
			match("MORETHAN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__157"

	// $ANTLR start "T__158"
	public final void mT__158() throws RecognitionException {
		try {
			int _type = T__158;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:236:8: ( 'NEXTTO' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:236:10: 'NEXTTO'
			{
			match("NEXTTO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__158"

	// $ANTLR start "T__159"
	public final void mT__159() throws RecognitionException {
		try {
			int _type = T__159;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:237:8: ( 'NOTCONTAINS' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:237:10: 'NOTCONTAINS'
			{
			match("NOTCONTAINS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__159"

	// $ANTLR start "T__160"
	public final void mT__160() throws RecognitionException {
		try {
			int _type = T__160;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:238:8: ( 'NOTEQUALS' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:238:10: 'NOTEQUALS'
			{
			match("NOTEQUALS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__160"

	// $ANTLR start "T__161"
	public final void mT__161() throws RecognitionException {
		try {
			int _type = T__161;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:239:8: ( 'NOTEXACTLY' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:239:10: 'NOTEXACTLY'
			{
			match("NOTEXACTLY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__161"

	// $ANTLR start "T__162"
	public final void mT__162() throws RecognitionException {
		try {
			int _type = T__162;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:240:8: ( 'NOTMATCHES' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:240:10: 'NOTMATCHES'
			{
			match("NOTMATCHES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__162"

	// $ANTLR start "T__163"
	public final void mT__163() throws RecognitionException {
		try {
			int _type = T__163;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:241:8: ( 'NOTMORETHAN' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:241:10: 'NOTMORETHAN'
			{
			match("NOTMORETHAN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__163"

	// $ANTLR start "T__164"
	public final void mT__164() throws RecognitionException {
		try {
			int _type = T__164;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:242:8: ( 'NOTTHEN' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:242:10: 'NOTTHEN'
			{
			match("NOTTHEN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__164"

	// $ANTLR start "T__165"
	public final void mT__165() throws RecognitionException {
		try {
			int _type = T__165;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:243:8: ( 'NOTWITH' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:243:10: 'NOTWITH'
			{
			match("NOTWITH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__165"

	// $ANTLR start "T__166"
	public final void mT__166() throws RecognitionException {
		try {
			int _type = T__166;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:244:8: ( 'REVERSE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:244:10: 'REVERSE'
			{
			match("REVERSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__166"

	// $ANTLR start "T__167"
	public final void mT__167() throws RecognitionException {
		try {
			int _type = T__167;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:245:8: ( 'SAME_COUNT' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:245:10: 'SAME_COUNT'
			{
			match("SAME_COUNT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__167"

	// $ANTLR start "T__168"
	public final void mT__168() throws RecognitionException {
		try {
			int _type = T__168;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:246:8: ( 'SAME_ORIENTATION' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:246:10: 'SAME_ORIENTATION'
			{
			match("SAME_ORIENTATION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__168"

	// $ANTLR start "T__169"
	public final void mT__169() throws RecognitionException {
		try {
			int _type = T__169;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:247:8: ( 'SOME_AFTER' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:247:10: 'SOME_AFTER'
			{
			match("SOME_AFTER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__169"

	// $ANTLR start "T__170"
	public final void mT__170() throws RecognitionException {
		try {
			int _type = T__170;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:248:8: ( 'SOME_BEFORE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:248:10: 'SOME_BEFORE'
			{
			match("SOME_BEFORE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__170"

	// $ANTLR start "T__171"
	public final void mT__171() throws RecognitionException {
		try {
			int _type = T__171;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:249:8: ( 'SOME_FORWARD' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:249:10: 'SOME_FORWARD'
			{
			match("SOME_FORWARD"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__171"

	// $ANTLR start "T__172"
	public final void mT__172() throws RecognitionException {
		try {
			int _type = T__172;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:250:8: ( 'SOME_NEXTTO' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:250:10: 'SOME_NEXTTO'
			{
			match("SOME_NEXTTO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__172"

	// $ANTLR start "T__173"
	public final void mT__173() throws RecognitionException {
		try {
			int _type = T__173;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:251:8: ( 'SOME_REVERSE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:251:10: 'SOME_REVERSE'
			{
			match("SOME_REVERSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__173"

	// $ANTLR start "T__174"
	public final void mT__174() throws RecognitionException {
		try {
			int _type = T__174;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:252:8: ( 'SOME_SAME_ORIENTATION' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:252:10: 'SOME_SAME_ORIENTATION'
			{
			match("SOME_SAME_ORIENTATION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__174"

	// $ANTLR start "T__175"
	public final void mT__175() throws RecognitionException {
		try {
			int _type = T__175;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:253:8: ( 'SOUNDSLIKE' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:253:10: 'SOUNDSLIKE'
			{
			match("SOUNDSLIKE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__175"

	// $ANTLR start "T__176"
	public final void mT__176() throws RecognitionException {
		try {
			int _type = T__176;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:254:8: ( 'STARTSWITH' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:254:10: 'STARTSWITH'
			{
			match("STARTSWITH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__176"

	// $ANTLR start "T__177"
	public final void mT__177() throws RecognitionException {
		try {
			int _type = T__177;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:255:8: ( 'THEN' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:255:10: 'THEN'
			{
			match("THEN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__177"

	// $ANTLR start "T__178"
	public final void mT__178() throws RecognitionException {
		try {
			int _type = T__178;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:256:8: ( 'WITH' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:256:10: 'WITH'
			{
			match("WITH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__178"

	// $ANTLR start "T__179"
	public final void mT__179() throws RecognitionException {
		try {
			int _type = T__179;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:257:8: ( 'after' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:257:10: 'after'
			{
			match("after"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__179"

	// $ANTLR start "T__180"
	public final void mT__180() throws RecognitionException {
		try {
			int _type = T__180;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:258:8: ( 'all_after' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:258:10: 'all_after'
			{
			match("all_after"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__180"

	// $ANTLR start "T__181"
	public final void mT__181() throws RecognitionException {
		try {
			int _type = T__181;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:259:8: ( 'all_before' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:259:10: 'all_before'
			{
			match("all_before"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__181"

	// $ANTLR start "T__182"
	public final void mT__182() throws RecognitionException {
		try {
			int _type = T__182;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:260:8: ( 'all_forward' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:260:10: 'all_forward'
			{
			match("all_forward"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__182"

	// $ANTLR start "T__183"
	public final void mT__183() throws RecognitionException {
		try {
			int _type = T__183;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:261:8: ( 'all_nextto' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:261:10: 'all_nextto'
			{
			match("all_nextto"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__183"

	// $ANTLR start "T__184"
	public final void mT__184() throws RecognitionException {
		try {
			int _type = T__184;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:262:8: ( 'all_reverse' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:262:10: 'all_reverse'
			{
			match("all_reverse"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__184"

	// $ANTLR start "T__185"
	public final void mT__185() throws RecognitionException {
		try {
			int _type = T__185;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:263:8: ( 'all_same_orientation' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:263:10: 'all_same_orientation'
			{
			match("all_same_orientation"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__185"

	// $ANTLR start "T__186"
	public final void mT__186() throws RecognitionException {
		try {
			int _type = T__186;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:264:8: ( 'alternate_orientation' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:264:10: 'alternate_orientation'
			{
			match("alternate_orientation"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__186"

	// $ANTLR start "T__187"
	public final void mT__187() throws RecognitionException {
		try {
			int _type = T__187;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:265:8: ( 'always_nextto' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:265:10: 'always_nextto'
			{
			match("always_nextto"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__187"

	// $ANTLR start "T__188"
	public final void mT__188() throws RecognitionException {
		try {
			int _type = T__188;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:266:8: ( 'before' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:266:10: 'before'
			{
			match("before"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__188"

	// $ANTLR start "T__189"
	public final void mT__189() throws RecognitionException {
		try {
			int _type = T__189;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:267:8: ( 'contains' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:267:10: 'contains'
			{
			match("contains"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__189"

	// $ANTLR start "T__190"
	public final void mT__190() throws RecognitionException {
		try {
			int _type = T__190;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:268:8: ( 'drives' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:268:10: 'drives'
			{
			match("drives"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__190"

	// $ANTLR start "T__191"
	public final void mT__191() throws RecognitionException {
		try {
			int _type = T__191;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:269:8: ( 'endswith' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:269:10: 'endswith'
			{
			match("endswith"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__191"

	// $ANTLR start "T__192"
	public final void mT__192() throws RecognitionException {
		try {
			int _type = T__192;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:270:8: ( 'equals' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:270:10: 'equals'
			{
			match("equals"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__192"

	// $ANTLR start "T__193"
	public final void mT__193() throws RecognitionException {
		try {
			int _type = T__193;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:271:8: ( 'exactly' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:271:10: 'exactly'
			{
			match("exactly"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__193"

	// $ANTLR start "T__194"
	public final void mT__194() throws RecognitionException {
		try {
			int _type = T__194;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:272:8: ( 'forward' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:272:10: 'forward'
			{
			match("forward"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__194"

	// $ANTLR start "T__195"
	public final void mT__195() throws RecognitionException {
		try {
			int _type = T__195;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:273:8: ( 'matches' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:273:10: 'matches'
			{
			match("matches"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__195"

	// $ANTLR start "T__196"
	public final void mT__196() throws RecognitionException {
		try {
			int _type = T__196;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:274:8: ( 'morethan' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:274:10: 'morethan'
			{
			match("morethan"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__196"

	// $ANTLR start "T__197"
	public final void mT__197() throws RecognitionException {
		try {
			int _type = T__197;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:275:8: ( 'nextto' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:275:10: 'nextto'
			{
			match("nextto"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__197"

	// $ANTLR start "T__198"
	public final void mT__198() throws RecognitionException {
		try {
			int _type = T__198;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:276:8: ( 'notcontains' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:276:10: 'notcontains'
			{
			match("notcontains"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__198"

	// $ANTLR start "T__199"
	public final void mT__199() throws RecognitionException {
		try {
			int _type = T__199;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:277:8: ( 'notequals' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:277:10: 'notequals'
			{
			match("notequals"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__199"

	// $ANTLR start "T__200"
	public final void mT__200() throws RecognitionException {
		try {
			int _type = T__200;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:278:8: ( 'notexactly' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:278:10: 'notexactly'
			{
			match("notexactly"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__200"

	// $ANTLR start "T__201"
	public final void mT__201() throws RecognitionException {
		try {
			int _type = T__201;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:279:8: ( 'notmatches' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:279:10: 'notmatches'
			{
			match("notmatches"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__201"

	// $ANTLR start "T__202"
	public final void mT__202() throws RecognitionException {
		try {
			int _type = T__202;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:280:8: ( 'notmorethan' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:280:10: 'notmorethan'
			{
			match("notmorethan"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__202"

	// $ANTLR start "T__203"
	public final void mT__203() throws RecognitionException {
		try {
			int _type = T__203;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:281:8: ( 'notthen' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:281:10: 'notthen'
			{
			match("notthen"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__203"

	// $ANTLR start "T__204"
	public final void mT__204() throws RecognitionException {
		try {
			int _type = T__204;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:282:8: ( 'notwith' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:282:10: 'notwith'
			{
			match("notwith"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__204"

	// $ANTLR start "T__205"
	public final void mT__205() throws RecognitionException {
		try {
			int _type = T__205;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:283:8: ( 'reverse' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:283:10: 'reverse'
			{
			match("reverse"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__205"

	// $ANTLR start "T__206"
	public final void mT__206() throws RecognitionException {
		try {
			int _type = T__206;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:284:8: ( 'same_count' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:284:10: 'same_count'
			{
			match("same_count"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__206"

	// $ANTLR start "T__207"
	public final void mT__207() throws RecognitionException {
		try {
			int _type = T__207;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:285:8: ( 'same_orientation' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:285:10: 'same_orientation'
			{
			match("same_orientation"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__207"

	// $ANTLR start "T__208"
	public final void mT__208() throws RecognitionException {
		try {
			int _type = T__208;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:286:8: ( 'some_after' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:286:10: 'some_after'
			{
			match("some_after"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__208"

	// $ANTLR start "T__209"
	public final void mT__209() throws RecognitionException {
		try {
			int _type = T__209;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:287:8: ( 'some_before' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:287:10: 'some_before'
			{
			match("some_before"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__209"

	// $ANTLR start "T__210"
	public final void mT__210() throws RecognitionException {
		try {
			int _type = T__210;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:288:8: ( 'some_forward' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:288:10: 'some_forward'
			{
			match("some_forward"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__210"

	// $ANTLR start "T__211"
	public final void mT__211() throws RecognitionException {
		try {
			int _type = T__211;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:289:8: ( 'some_nextto' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:289:10: 'some_nextto'
			{
			match("some_nextto"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__211"

	// $ANTLR start "T__212"
	public final void mT__212() throws RecognitionException {
		try {
			int _type = T__212;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:290:8: ( 'some_reverse' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:290:10: 'some_reverse'
			{
			match("some_reverse"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__212"

	// $ANTLR start "T__213"
	public final void mT__213() throws RecognitionException {
		try {
			int _type = T__213;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:291:8: ( 'some_same_orientation' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:291:10: 'some_same_orientation'
			{
			match("some_same_orientation"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__213"

	// $ANTLR start "T__214"
	public final void mT__214() throws RecognitionException {
		try {
			int _type = T__214;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:292:8: ( 'soundslike' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:292:10: 'soundslike'
			{
			match("soundslike"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__214"

	// $ANTLR start "T__215"
	public final void mT__215() throws RecognitionException {
		try {
			int _type = T__215;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:293:8: ( 'startswith' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:293:10: 'startswith'
			{
			match("startswith"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__215"

	// $ANTLR start "T__216"
	public final void mT__216() throws RecognitionException {
		try {
			int _type = T__216;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:294:8: ( 'then' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:294:10: 'then'
			{
			match("then"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__216"

	// $ANTLR start "T__217"
	public final void mT__217() throws RecognitionException {
		try {
			int _type = T__217;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:295:8: ( 'with' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:295:10: 'with'
			{
			match("with"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__217"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3515:2: ( ( DIGIT )+ )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3515:4: ( DIGIT )+
			{
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3515:4: ( DIGIT )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUMBER"

	// $ANTLR start "REAL"
	public final void mREAL() throws RecognitionException {
		try {
			int _type = REAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3519:2: ( NUMBER '.' NUMBER )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3519:4: NUMBER '.' NUMBER
			{
			mNUMBER(); 

			match('.'); 
			mNUMBER(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REAL"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3523:2: ( ( '\\t' | ' ' | '\\u000C' )+ )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3523:4: ( '\\t' | ' ' | '\\u000C' )+
			{
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3523:4: ( '\\t' | ' ' | '\\u000C' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0=='\t'||LA2_0=='\f'||LA2_0==' ') ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:
					{
					if ( input.LA(1)=='\t'||input.LA(1)=='\f'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			_channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "NEWLINE"
	public final void mNEWLINE() throws RecognitionException {
		try {
			int _type = NEWLINE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3527:2: ( ( '\\r' )? '\\n' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3527:4: ( '\\r' )? '\\n'
			{
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3527:4: ( '\\r' )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0=='\r') ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3527:4: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			_channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEWLINE"

	// $ANTLR start "LINE_COMMENT"
	public final void mLINE_COMMENT() throws RecognitionException {
		try {
			int _type = LINE_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3531:2: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' ) | '//' (~ ( '\\n' | '\\r' ) )* )
			int alt7=2;
			alt7 = dfa7.predict(input);
			switch (alt7) {
				case 1 :
					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3531:4: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' )
					{
					match("//"); 

					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3531:9: (~ ( '\\n' | '\\r' ) )*
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( ((LA4_0 >= '\u0000' && LA4_0 <= '\t')||(LA4_0 >= '\u000B' && LA4_0 <= '\f')||(LA4_0 >= '\u000E' && LA4_0 <= '\uFFFF')) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop4;
						}
					}

					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3531:24: ( '\\r\\n' | '\\r' | '\\n' )
					int alt5=3;
					int LA5_0 = input.LA(1);
					if ( (LA5_0=='\r') ) {
						int LA5_1 = input.LA(2);
						if ( (LA5_1=='\n') ) {
							alt5=1;
						}

						else {
							alt5=2;
						}

					}
					else if ( (LA5_0=='\n') ) {
						alt5=3;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 5, 0, input);
						throw nvae;
					}

					switch (alt5) {
						case 1 :
							// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3531:25: '\\r\\n'
							{
							match("\r\n"); 

							}
							break;
						case 2 :
							// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3531:34: '\\r'
							{
							match('\r'); 
							}
							break;
						case 3 :
							// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3531:41: '\\n'
							{
							match('\n'); 
							}
							break;

					}


								skip();
							
					}
					break;
				case 2 :
					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3535:4: '//' (~ ( '\\n' | '\\r' ) )*
					{
					match("//"); 

					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3535:9: (~ ( '\\n' | '\\r' ) )*
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= '\u0000' && LA6_0 <= '\t')||(LA6_0 >= '\u000B' && LA6_0 <= '\f')||(LA6_0 >= '\u000E' && LA6_0 <= '\uFFFF')) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop6;
						}
					}


								skip();
							
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LINE_COMMENT"

	// $ANTLR start "ML_COMMENT"
	public final void mML_COMMENT() throws RecognitionException {
		try {
			int _type = ML_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3542:2: ( '/*' ( options {greedy=false; } : . )* '*/' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3542:4: '/*' ( options {greedy=false; } : . )* '*/'
			{
			match("/*"); 

			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3542:9: ( options {greedy=false; } : . )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0=='*') ) {
					int LA8_1 = input.LA(2);
					if ( (LA8_1=='/') ) {
						alt8=2;
					}
					else if ( ((LA8_1 >= '\u0000' && LA8_1 <= '.')||(LA8_1 >= '0' && LA8_1 <= '\uFFFF')) ) {
						alt8=1;
					}

				}
				else if ( ((LA8_0 >= '\u0000' && LA8_0 <= ')')||(LA8_0 >= '+' && LA8_0 <= '\uFFFF')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3542:36: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop8;
				}
			}

			match("*/"); 

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ML_COMMENT"

	// $ANTLR start "DIGIT"
	public final void mDIGIT() throws RecognitionException {
		try {
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3546:2: ( '0' .. '9' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIGIT"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3550:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3550:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3550:27: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= '0' && LA9_0 <= '9')||(LA9_0 >= 'A' && LA9_0 <= 'Z')||LA9_0=='_'||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop9;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3554:2: ( '\"' ( options {greedy=false; } : . )* '\"' )
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3554:4: '\"' ( options {greedy=false; } : . )* '\"'
			{
			match('\"'); 
			// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3554:8: ( options {greedy=false; } : . )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0=='\"') ) {
					alt10=2;
				}
				else if ( ((LA10_0 >= '\u0000' && LA10_0 <= '!')||(LA10_0 >= '#' && LA10_0 <= '\uFFFF')) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:3554:35: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop10;
				}
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	@Override
	public void mTokens() throws RecognitionException {
		// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:8: ( ADDPROPS | AMP | ARRAY | ARROW | ASSERT | BOOL | BOOLEAN | COLLECTION | COLON | COMMA | CREATE_LC | CREATE_UC | DELETE_LC | DELETE_UC | DEVICE | DIV | DOLLAR | DOT | DOTDOT | EQUALS | EXIT_LC | EXIT_UC | EXPORT_LC | EXPORT_UC | FALSE_LC | FALSE_UC | FLEXIBLE | GENBANK | GEQUAL | GRAMMAR | GTHAN | HASHMARK | IMAGE | IMPORT_LC | IMPORT_UC | INCLUDE_LC | INCLUDE_UC | INTERACTION | LC_AND | LC_ELSE | LC_ELSEIF | LC_FOR | LC_FORALL | LC_IF | LC_INDUCES | LC_NOT | LC_ON | LC_OR | LC_PERMUTE | LC_PRODUCT | LC_REPRESSES | LC_SEQUENCE_OF | LC_WHILE | LEFTCUR | LEFTP | LEFTSBR | LEQUAL | LOG_AND | LOG_OR | LTHAN | MINUS | MULT | NEQUAL | NOTE | NUM | OP_NOT | PART | PART_TYPE | PIPE | PLUS | PRINTLN_LC | PRINTLN_UC | PRINT_LC | PRINT_UC | PROPERTY | QUERY_LC | QUERY_UC | RANDOM_LC | RANDOM_UC | READ_LC | READ_UC | REF | REGISTRY | RETURN_LC | RETURN_UC | RIGHTCUR | RIGHTP | RIGHTSBR | RULE | RULE_BUILDER | SAVE_LC | SAVE_UC | SBOL | SEMIC | SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_OF_LC | SIZE_OF_UC | SIZE_UC | STORE_LC | STORE_UC | STRICT | TRUE_LC | TRUE_UC | TXT | TYPE | UC_AND | UC_ELSE | UC_ELSEIF | UC_FOR | UC_FORALL | UC_IF | UC_INDUCES | UC_NOT | UC_ON | UC_OR | UC_PERMUTE | UC_PRODUCT | UC_REPRESSES | UC_SEQUENCE_OF | UC_WHILE | UNDERS | UPDATE_LC | UPDATE_UC | VISUALIZE_LC | VISUALIZE_UC | T__140 | T__141 | T__142 | T__143 | T__144 | T__145 | T__146 | T__147 | T__148 | T__149 | T__150 | T__151 | T__152 | T__153 | T__154 | T__155 | T__156 | T__157 | T__158 | T__159 | T__160 | T__161 | T__162 | T__163 | T__164 | T__165 | T__166 | T__167 | T__168 | T__169 | T__170 | T__171 | T__172 | T__173 | T__174 | T__175 | T__176 | T__177 | T__178 | T__179 | T__180 | T__181 | T__182 | T__183 | T__184 | T__185 | T__186 | T__187 | T__188 | T__189 | T__190 | T__191 | T__192 | T__193 | T__194 | T__195 | T__196 | T__197 | T__198 | T__199 | T__200 | T__201 | T__202 | T__203 | T__204 | T__205 | T__206 | T__207 | T__208 | T__209 | T__210 | T__211 | T__212 | T__213 | T__214 | T__215 | T__216 | T__217 | NUMBER | REAL | WS | NEWLINE | LINE_COMMENT | ML_COMMENT | ID | STRING )
		int alt11=213;
		alt11 = dfa11.predict(input);
		switch (alt11) {
			case 1 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:10: ADDPROPS
				{
				mADDPROPS(); 

				}
				break;
			case 2 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:19: AMP
				{
				mAMP(); 

				}
				break;
			case 3 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:23: ARRAY
				{
				mARRAY(); 

				}
				break;
			case 4 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:29: ARROW
				{
				mARROW(); 

				}
				break;
			case 5 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:35: ASSERT
				{
				mASSERT(); 

				}
				break;
			case 6 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:42: BOOL
				{
				mBOOL(); 

				}
				break;
			case 7 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:47: BOOLEAN
				{
				mBOOLEAN(); 

				}
				break;
			case 8 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:55: COLLECTION
				{
				mCOLLECTION(); 

				}
				break;
			case 9 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:66: COLON
				{
				mCOLON(); 

				}
				break;
			case 10 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:72: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 11 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:78: CREATE_LC
				{
				mCREATE_LC(); 

				}
				break;
			case 12 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:88: CREATE_UC
				{
				mCREATE_UC(); 

				}
				break;
			case 13 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:98: DELETE_LC
				{
				mDELETE_LC(); 

				}
				break;
			case 14 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:108: DELETE_UC
				{
				mDELETE_UC(); 

				}
				break;
			case 15 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:118: DEVICE
				{
				mDEVICE(); 

				}
				break;
			case 16 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:125: DIV
				{
				mDIV(); 

				}
				break;
			case 17 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:129: DOLLAR
				{
				mDOLLAR(); 

				}
				break;
			case 18 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:136: DOT
				{
				mDOT(); 

				}
				break;
			case 19 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:140: DOTDOT
				{
				mDOTDOT(); 

				}
				break;
			case 20 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:147: EQUALS
				{
				mEQUALS(); 

				}
				break;
			case 21 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:154: EXIT_LC
				{
				mEXIT_LC(); 

				}
				break;
			case 22 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:162: EXIT_UC
				{
				mEXIT_UC(); 

				}
				break;
			case 23 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:170: EXPORT_LC
				{
				mEXPORT_LC(); 

				}
				break;
			case 24 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:180: EXPORT_UC
				{
				mEXPORT_UC(); 

				}
				break;
			case 25 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:190: FALSE_LC
				{
				mFALSE_LC(); 

				}
				break;
			case 26 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:199: FALSE_UC
				{
				mFALSE_UC(); 

				}
				break;
			case 27 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:208: FLEXIBLE
				{
				mFLEXIBLE(); 

				}
				break;
			case 28 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:217: GENBANK
				{
				mGENBANK(); 

				}
				break;
			case 29 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:225: GEQUAL
				{
				mGEQUAL(); 

				}
				break;
			case 30 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:232: GRAMMAR
				{
				mGRAMMAR(); 

				}
				break;
			case 31 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:240: GTHAN
				{
				mGTHAN(); 

				}
				break;
			case 32 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:246: HASHMARK
				{
				mHASHMARK(); 

				}
				break;
			case 33 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:255: IMAGE
				{
				mIMAGE(); 

				}
				break;
			case 34 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:261: IMPORT_LC
				{
				mIMPORT_LC(); 

				}
				break;
			case 35 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:271: IMPORT_UC
				{
				mIMPORT_UC(); 

				}
				break;
			case 36 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:281: INCLUDE_LC
				{
				mINCLUDE_LC(); 

				}
				break;
			case 37 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:292: INCLUDE_UC
				{
				mINCLUDE_UC(); 

				}
				break;
			case 38 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:303: INTERACTION
				{
				mINTERACTION(); 

				}
				break;
			case 39 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:315: LC_AND
				{
				mLC_AND(); 

				}
				break;
			case 40 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:322: LC_ELSE
				{
				mLC_ELSE(); 

				}
				break;
			case 41 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:330: LC_ELSEIF
				{
				mLC_ELSEIF(); 

				}
				break;
			case 42 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:340: LC_FOR
				{
				mLC_FOR(); 

				}
				break;
			case 43 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:347: LC_FORALL
				{
				mLC_FORALL(); 

				}
				break;
			case 44 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:357: LC_IF
				{
				mLC_IF(); 

				}
				break;
			case 45 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:363: LC_INDUCES
				{
				mLC_INDUCES(); 

				}
				break;
			case 46 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:374: LC_NOT
				{
				mLC_NOT(); 

				}
				break;
			case 47 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:381: LC_ON
				{
				mLC_ON(); 

				}
				break;
			case 48 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:387: LC_OR
				{
				mLC_OR(); 

				}
				break;
			case 49 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:393: LC_PERMUTE
				{
				mLC_PERMUTE(); 

				}
				break;
			case 50 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:404: LC_PRODUCT
				{
				mLC_PRODUCT(); 

				}
				break;
			case 51 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:415: LC_REPRESSES
				{
				mLC_REPRESSES(); 

				}
				break;
			case 52 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:428: LC_SEQUENCE_OF
				{
				mLC_SEQUENCE_OF(); 

				}
				break;
			case 53 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:443: LC_WHILE
				{
				mLC_WHILE(); 

				}
				break;
			case 54 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:452: LEFTCUR
				{
				mLEFTCUR(); 

				}
				break;
			case 55 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:460: LEFTP
				{
				mLEFTP(); 

				}
				break;
			case 56 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:466: LEFTSBR
				{
				mLEFTSBR(); 

				}
				break;
			case 57 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:474: LEQUAL
				{
				mLEQUAL(); 

				}
				break;
			case 58 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:481: LOG_AND
				{
				mLOG_AND(); 

				}
				break;
			case 59 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:489: LOG_OR
				{
				mLOG_OR(); 

				}
				break;
			case 60 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:496: LTHAN
				{
				mLTHAN(); 

				}
				break;
			case 61 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:502: MINUS
				{
				mMINUS(); 

				}
				break;
			case 62 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:508: MULT
				{
				mMULT(); 

				}
				break;
			case 63 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:513: NEQUAL
				{
				mNEQUAL(); 

				}
				break;
			case 64 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:520: NOTE
				{
				mNOTE(); 

				}
				break;
			case 65 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:525: NUM
				{
				mNUM(); 

				}
				break;
			case 66 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:529: OP_NOT
				{
				mOP_NOT(); 

				}
				break;
			case 67 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:536: PART
				{
				mPART(); 

				}
				break;
			case 68 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:541: PART_TYPE
				{
				mPART_TYPE(); 

				}
				break;
			case 69 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:551: PIPE
				{
				mPIPE(); 

				}
				break;
			case 70 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:556: PLUS
				{
				mPLUS(); 

				}
				break;
			case 71 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:561: PRINTLN_LC
				{
				mPRINTLN_LC(); 

				}
				break;
			case 72 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:572: PRINTLN_UC
				{
				mPRINTLN_UC(); 

				}
				break;
			case 73 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:583: PRINT_LC
				{
				mPRINT_LC(); 

				}
				break;
			case 74 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:592: PRINT_UC
				{
				mPRINT_UC(); 

				}
				break;
			case 75 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:601: PROPERTY
				{
				mPROPERTY(); 

				}
				break;
			case 76 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:610: QUERY_LC
				{
				mQUERY_LC(); 

				}
				break;
			case 77 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:619: QUERY_UC
				{
				mQUERY_UC(); 

				}
				break;
			case 78 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:628: RANDOM_LC
				{
				mRANDOM_LC(); 

				}
				break;
			case 79 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:638: RANDOM_UC
				{
				mRANDOM_UC(); 

				}
				break;
			case 80 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:648: READ_LC
				{
				mREAD_LC(); 

				}
				break;
			case 81 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:656: READ_UC
				{
				mREAD_UC(); 

				}
				break;
			case 82 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:664: REF
				{
				mREF(); 

				}
				break;
			case 83 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:668: REGISTRY
				{
				mREGISTRY(); 

				}
				break;
			case 84 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:677: RETURN_LC
				{
				mRETURN_LC(); 

				}
				break;
			case 85 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:687: RETURN_UC
				{
				mRETURN_UC(); 

				}
				break;
			case 86 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:697: RIGHTCUR
				{
				mRIGHTCUR(); 

				}
				break;
			case 87 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:706: RIGHTP
				{
				mRIGHTP(); 

				}
				break;
			case 88 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:713: RIGHTSBR
				{
				mRIGHTSBR(); 

				}
				break;
			case 89 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:722: RULE
				{
				mRULE(); 

				}
				break;
			case 90 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:727: RULE_BUILDER
				{
				mRULE_BUILDER(); 

				}
				break;
			case 91 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:740: SAVE_LC
				{
				mSAVE_LC(); 

				}
				break;
			case 92 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:748: SAVE_UC
				{
				mSAVE_UC(); 

				}
				break;
			case 93 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:756: SBOL
				{
				mSBOL(); 

				}
				break;
			case 94 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:761: SEMIC
				{
				mSEMIC(); 

				}
				break;
			case 95 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:767: SIZEOF_LC
				{
				mSIZEOF_LC(); 

				}
				break;
			case 96 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:777: SIZEOF_UC
				{
				mSIZEOF_UC(); 

				}
				break;
			case 97 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:787: SIZE_LC
				{
				mSIZE_LC(); 

				}
				break;
			case 98 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:795: SIZE_OF_LC
				{
				mSIZE_OF_LC(); 

				}
				break;
			case 99 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:806: SIZE_OF_UC
				{
				mSIZE_OF_UC(); 

				}
				break;
			case 100 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:817: SIZE_UC
				{
				mSIZE_UC(); 

				}
				break;
			case 101 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:825: STORE_LC
				{
				mSTORE_LC(); 

				}
				break;
			case 102 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:834: STORE_UC
				{
				mSTORE_UC(); 

				}
				break;
			case 103 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:843: STRICT
				{
				mSTRICT(); 

				}
				break;
			case 104 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:850: TRUE_LC
				{
				mTRUE_LC(); 

				}
				break;
			case 105 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:858: TRUE_UC
				{
				mTRUE_UC(); 

				}
				break;
			case 106 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:866: TXT
				{
				mTXT(); 

				}
				break;
			case 107 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:870: TYPE
				{
				mTYPE(); 

				}
				break;
			case 108 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:875: UC_AND
				{
				mUC_AND(); 

				}
				break;
			case 109 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:882: UC_ELSE
				{
				mUC_ELSE(); 

				}
				break;
			case 110 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:890: UC_ELSEIF
				{
				mUC_ELSEIF(); 

				}
				break;
			case 111 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:900: UC_FOR
				{
				mUC_FOR(); 

				}
				break;
			case 112 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:907: UC_FORALL
				{
				mUC_FORALL(); 

				}
				break;
			case 113 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:917: UC_IF
				{
				mUC_IF(); 

				}
				break;
			case 114 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:923: UC_INDUCES
				{
				mUC_INDUCES(); 

				}
				break;
			case 115 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:934: UC_NOT
				{
				mUC_NOT(); 

				}
				break;
			case 116 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:941: UC_ON
				{
				mUC_ON(); 

				}
				break;
			case 117 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:947: UC_OR
				{
				mUC_OR(); 

				}
				break;
			case 118 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:953: UC_PERMUTE
				{
				mUC_PERMUTE(); 

				}
				break;
			case 119 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:964: UC_PRODUCT
				{
				mUC_PRODUCT(); 

				}
				break;
			case 120 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:975: UC_REPRESSES
				{
				mUC_REPRESSES(); 

				}
				break;
			case 121 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:988: UC_SEQUENCE_OF
				{
				mUC_SEQUENCE_OF(); 

				}
				break;
			case 122 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1003: UC_WHILE
				{
				mUC_WHILE(); 

				}
				break;
			case 123 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1012: UNDERS
				{
				mUNDERS(); 

				}
				break;
			case 124 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1019: UPDATE_LC
				{
				mUPDATE_LC(); 

				}
				break;
			case 125 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1029: UPDATE_UC
				{
				mUPDATE_UC(); 

				}
				break;
			case 126 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1039: VISUALIZE_LC
				{
				mVISUALIZE_LC(); 

				}
				break;
			case 127 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1052: VISUALIZE_UC
				{
				mVISUALIZE_UC(); 

				}
				break;
			case 128 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1065: T__140
				{
				mT__140(); 

				}
				break;
			case 129 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1072: T__141
				{
				mT__141(); 

				}
				break;
			case 130 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1079: T__142
				{
				mT__142(); 

				}
				break;
			case 131 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1086: T__143
				{
				mT__143(); 

				}
				break;
			case 132 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1093: T__144
				{
				mT__144(); 

				}
				break;
			case 133 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1100: T__145
				{
				mT__145(); 

				}
				break;
			case 134 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1107: T__146
				{
				mT__146(); 

				}
				break;
			case 135 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1114: T__147
				{
				mT__147(); 

				}
				break;
			case 136 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1121: T__148
				{
				mT__148(); 

				}
				break;
			case 137 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1128: T__149
				{
				mT__149(); 

				}
				break;
			case 138 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1135: T__150
				{
				mT__150(); 

				}
				break;
			case 139 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1142: T__151
				{
				mT__151(); 

				}
				break;
			case 140 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1149: T__152
				{
				mT__152(); 

				}
				break;
			case 141 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1156: T__153
				{
				mT__153(); 

				}
				break;
			case 142 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1163: T__154
				{
				mT__154(); 

				}
				break;
			case 143 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1170: T__155
				{
				mT__155(); 

				}
				break;
			case 144 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1177: T__156
				{
				mT__156(); 

				}
				break;
			case 145 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1184: T__157
				{
				mT__157(); 

				}
				break;
			case 146 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1191: T__158
				{
				mT__158(); 

				}
				break;
			case 147 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1198: T__159
				{
				mT__159(); 

				}
				break;
			case 148 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1205: T__160
				{
				mT__160(); 

				}
				break;
			case 149 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1212: T__161
				{
				mT__161(); 

				}
				break;
			case 150 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1219: T__162
				{
				mT__162(); 

				}
				break;
			case 151 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1226: T__163
				{
				mT__163(); 

				}
				break;
			case 152 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1233: T__164
				{
				mT__164(); 

				}
				break;
			case 153 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1240: T__165
				{
				mT__165(); 

				}
				break;
			case 154 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1247: T__166
				{
				mT__166(); 

				}
				break;
			case 155 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1254: T__167
				{
				mT__167(); 

				}
				break;
			case 156 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1261: T__168
				{
				mT__168(); 

				}
				break;
			case 157 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1268: T__169
				{
				mT__169(); 

				}
				break;
			case 158 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1275: T__170
				{
				mT__170(); 

				}
				break;
			case 159 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1282: T__171
				{
				mT__171(); 

				}
				break;
			case 160 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1289: T__172
				{
				mT__172(); 

				}
				break;
			case 161 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1296: T__173
				{
				mT__173(); 

				}
				break;
			case 162 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1303: T__174
				{
				mT__174(); 

				}
				break;
			case 163 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1310: T__175
				{
				mT__175(); 

				}
				break;
			case 164 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1317: T__176
				{
				mT__176(); 

				}
				break;
			case 165 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1324: T__177
				{
				mT__177(); 

				}
				break;
			case 166 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1331: T__178
				{
				mT__178(); 

				}
				break;
			case 167 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1338: T__179
				{
				mT__179(); 

				}
				break;
			case 168 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1345: T__180
				{
				mT__180(); 

				}
				break;
			case 169 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1352: T__181
				{
				mT__181(); 

				}
				break;
			case 170 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1359: T__182
				{
				mT__182(); 

				}
				break;
			case 171 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1366: T__183
				{
				mT__183(); 

				}
				break;
			case 172 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1373: T__184
				{
				mT__184(); 

				}
				break;
			case 173 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1380: T__185
				{
				mT__185(); 

				}
				break;
			case 174 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1387: T__186
				{
				mT__186(); 

				}
				break;
			case 175 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1394: T__187
				{
				mT__187(); 

				}
				break;
			case 176 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1401: T__188
				{
				mT__188(); 

				}
				break;
			case 177 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1408: T__189
				{
				mT__189(); 

				}
				break;
			case 178 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1415: T__190
				{
				mT__190(); 

				}
				break;
			case 179 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1422: T__191
				{
				mT__191(); 

				}
				break;
			case 180 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1429: T__192
				{
				mT__192(); 

				}
				break;
			case 181 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1436: T__193
				{
				mT__193(); 

				}
				break;
			case 182 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1443: T__194
				{
				mT__194(); 

				}
				break;
			case 183 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1450: T__195
				{
				mT__195(); 

				}
				break;
			case 184 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1457: T__196
				{
				mT__196(); 

				}
				break;
			case 185 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1464: T__197
				{
				mT__197(); 

				}
				break;
			case 186 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1471: T__198
				{
				mT__198(); 

				}
				break;
			case 187 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1478: T__199
				{
				mT__199(); 

				}
				break;
			case 188 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1485: T__200
				{
				mT__200(); 

				}
				break;
			case 189 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1492: T__201
				{
				mT__201(); 

				}
				break;
			case 190 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1499: T__202
				{
				mT__202(); 

				}
				break;
			case 191 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1506: T__203
				{
				mT__203(); 

				}
				break;
			case 192 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1513: T__204
				{
				mT__204(); 

				}
				break;
			case 193 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1520: T__205
				{
				mT__205(); 

				}
				break;
			case 194 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1527: T__206
				{
				mT__206(); 

				}
				break;
			case 195 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1534: T__207
				{
				mT__207(); 

				}
				break;
			case 196 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1541: T__208
				{
				mT__208(); 

				}
				break;
			case 197 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1548: T__209
				{
				mT__209(); 

				}
				break;
			case 198 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1555: T__210
				{
				mT__210(); 

				}
				break;
			case 199 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1562: T__211
				{
				mT__211(); 

				}
				break;
			case 200 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1569: T__212
				{
				mT__212(); 

				}
				break;
			case 201 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1576: T__213
				{
				mT__213(); 

				}
				break;
			case 202 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1583: T__214
				{
				mT__214(); 

				}
				break;
			case 203 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1590: T__215
				{
				mT__215(); 

				}
				break;
			case 204 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1597: T__216
				{
				mT__216(); 

				}
				break;
			case 205 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1604: T__217
				{
				mT__217(); 

				}
				break;
			case 206 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1611: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 207 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1618: REAL
				{
				mREAL(); 

				}
				break;
			case 208 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1623: WS
				{
				mWS(); 

				}
				break;
			case 209 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1626: NEWLINE
				{
				mNEWLINE(); 

				}
				break;
			case 210 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1634: LINE_COMMENT
				{
				mLINE_COMMENT(); 

				}
				break;
			case 211 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1647: ML_COMMENT
				{
				mML_COMMENT(); 

				}
				break;
			case 212 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1658: ID
				{
				mID(); 

				}
				break;
			case 213 :
				// /Users/eoberortner/Projects/CIDAR/Eugene/git/eugene/grammar/Eugene.g:1:1661: STRING
				{
				mSTRING(); 

				}
				break;

		}
	}


	protected DFA7 dfa7 = new DFA7(this);
	protected DFA11 dfa11 = new DFA11(this);
	static final String DFA7_eotS =
		"\2\uffff\2\5\2\uffff";
	static final String DFA7_eofS =
		"\6\uffff";
	static final String DFA7_minS =
		"\2\57\2\0\2\uffff";
	static final String DFA7_maxS =
		"\2\57\2\uffff\2\uffff";
	static final String DFA7_acceptS =
		"\4\uffff\1\1\1\2";
	static final String DFA7_specialS =
		"\2\uffff\1\1\1\0\2\uffff}>";
	static final String[] DFA7_transitionS = {
			"\1\1",
			"\1\2",
			"\12\3\1\4\2\3\1\4\ufff2\3",
			"\12\3\1\4\2\3\1\4\ufff2\3",
			"",
			""
	};

	static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
	static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
	static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
	static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
	static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
	static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
	static final short[][] DFA7_transition;

	static {
		int numStates = DFA7_transitionS.length;
		DFA7_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
		}
	}

	protected class DFA7 extends DFA {

		public DFA7(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 7;
			this.eot = DFA7_eot;
			this.eof = DFA7_eof;
			this.min = DFA7_min;
			this.max = DFA7_max;
			this.accept = DFA7_accept;
			this.special = DFA7_special;
			this.transition = DFA7_transition;
		}
		@Override
		public String getDescription() {
			return "3530:1: LINE_COMMENT : ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' ) | '//' (~ ( '\\n' | '\\r' ) )* );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA7_3 = input.LA(1);
						s = -1;
						if ( (LA7_3=='\n'||LA7_3=='\r') ) {s = 4;}
						else if ( ((LA7_3 >= '\u0000' && LA7_3 <= '\t')||(LA7_3 >= '\u000B' && LA7_3 <= '\f')||(LA7_3 >= '\u000E' && LA7_3 <= '\uFFFF')) ) {s = 3;}
						else s = 5;
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA7_2 = input.LA(1);
						s = -1;
						if ( ((LA7_2 >= '\u0000' && LA7_2 <= '\t')||(LA7_2 >= '\u000B' && LA7_2 <= '\f')||(LA7_2 >= '\u000E' && LA7_2 <= '\uFFFF')) ) {s = 3;}
						else if ( (LA7_2=='\n'||LA7_2=='\r') ) {s = 4;}
						else s = 5;
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 7, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA11_eotS =
		"\1\uffff\1\101\1\uffff\1\101\1\115\2\101\2\uffff\3\101\1\135\1\uffff\1"+
		"\137\1\uffff\5\101\1\160\1\uffff\10\101\3\uffff\1\u008a\2\uffff\1\u008c"+
		"\2\101\2\uffff\3\101\3\uffff\1\101\1\uffff\4\101\1\u00aa\7\101\1\u00b4"+
		"\4\uffff\11\101\2\uffff\14\101\6\uffff\17\101\2\uffff\4\101\1\u00e7\2"+
		"\101\1\u00eb\3\101\1\u00ef\1\u00f0\13\101\4\uffff\31\101\1\u0125\1\u0126"+
		"\2\101\1\uffff\11\101\2\uffff\1\101\1\u0133\6\101\1\u013a\36\101\1\u015b"+
		"\1\101\1\u015f\7\101\1\uffff\3\101\1\uffff\1\u016f\1\u0170\1\101\2\uffff"+
		"\5\101\1\u0177\17\101\1\u018c\31\101\1\u01a6\4\101\2\uffff\14\101\1\uffff"+
		"\6\101\1\uffff\4\101\1\u01cc\13\101\1\u01d8\2\101\1\u01dc\2\101\1\u01df"+
		"\2\101\1\u01e3\6\101\1\uffff\3\101\1\uffff\17\101\2\uffff\5\101\1\u0203"+
		"\1\uffff\4\101\1\u0208\1\101\1\u020c\6\101\1\u0213\1\u0214\5\101\1\uffff"+
		"\1\101\1\u021e\7\101\1\u0226\4\101\1\u022c\1\u022d\1\101\1\u022f\1\u0232"+
		"\5\101\1\u0238\1\uffff\1\u0239\1\u023a\1\u023b\1\u023c\1\101\1\u023e\12"+
		"\101\1\u0249\10\101\1\u0252\1\101\1\u0254\11\101\1\uffff\13\101\1\uffff"+
		"\3\101\1\uffff\2\101\1\uffff\3\101\1\uffff\2\101\1\u0273\3\101\1\u0277"+
		"\4\101\1\u027c\21\101\1\u028f\1\101\1\uffff\4\101\1\uffff\3\101\1\uffff"+
		"\1\u0299\4\101\1\u02a3\2\uffff\11\101\1\uffff\1\u02ae\3\101\1\u02b2\1"+
		"\u02b3\1\101\1\uffff\5\101\2\uffff\1\101\1\uffff\2\101\1\uffff\1\u02be"+
		"\4\101\5\uffff\1\u02c8\1\uffff\12\101\1\uffff\10\101\1\uffff\1\u02db\1"+
		"\uffff\11\101\1\u02e5\1\101\1\u02e7\1\101\1\u02e9\1\101\1\u02eb\1\u02ec"+
		"\1\u02ed\1\u02ee\1\u02ef\1\u02f0\1\101\1\u02f2\1\101\1\u02f4\1\u02f5\1"+
		"\101\1\u02f7\1\101\1\u02f9\1\uffff\1\101\1\u02fb\1\101\1\uffff\1\u02fd"+
		"\3\101\1\uffff\1\u0301\3\101\1\u0305\11\101\1\u030f\3\101\1\uffff\1\101"+
		"\1\u0314\1\101\1\u0316\3\101\1\u031a\1\101\1\uffff\1\u031c\10\101\1\uffff"+
		"\7\101\1\u032c\2\101\1\uffff\3\101\2\uffff\1\u0332\1\u0333\6\101\1\u033a"+
		"\1\101\1\uffff\11\101\1\uffff\1\u0345\1\u0346\2\101\1\u0349\15\101\1\uffff"+
		"\10\101\1\u035f\1\uffff\1\101\1\uffff\1\101\1\uffff\1\101\6\uffff\1\u0363"+
		"\1\uffff\1\101\2\uffff\1\u0365\1\uffff\1\101\1\uffff\1\101\1\uffff\1\u0368"+
		"\1\uffff\1\u0369\1\u036a\1\u036b\1\uffff\1\u036c\1\u036d\1\101\1\uffff"+
		"\1\u036f\1\u0370\5\101\1\u0376\1\u0377\1\uffff\1\u0378\1\u0379\1\u037a"+
		"\1\101\1\uffff\1\u037c\1\uffff\3\101\1\uffff\1\u0380\1\uffff\15\101\1"+
		"\u038e\1\u038f\1\uffff\1\101\1\u0391\1\u0392\1\101\1\u0394\2\uffff\1\101"+
		"\1\u0396\4\101\1\uffff\1\u039b\11\101\2\uffff\2\101\1\uffff\1\u03a7\1"+
		"\101\1\u03a9\22\101\1\uffff\1\101\1\u03bd\1\u03be\1\uffff\1\u03bf\1\uffff"+
		"\1\u03c0\1\u03c1\6\uffff\1\101\2\uffff\5\101\5\uffff\1\101\1\uffff\3\101"+
		"\1\uffff\15\101\2\uffff\1\u03d9\2\uffff\1\u03da\1\uffff\1\101\1\uffff"+
		"\1\u03dc\3\101\1\uffff\13\101\1\uffff\1\u03eb\1\uffff\1\u03ec\1\101\1"+
		"\u03ee\7\101\1\u03f6\10\101\5\uffff\2\101\1\u0401\3\101\1\u0405\14\101"+
		"\1\u0412\3\101\2\uffff\1\u0416\1\uffff\14\101\1\u0423\1\u0424\2\uffff"+
		"\1\101\1\uffff\1\u0426\1\101\1\u0428\4\101\1\uffff\1\u042d\1\101\1\u042f"+
		"\4\101\1\u0434\2\101\1\uffff\1\u0437\1\u0438\1\101\1\uffff\1\101\1\u043b"+
		"\1\101\1\u043d\1\u043e\5\101\1\u0444\1\101\1\uffff\1\u0446\1\u0447\1\101"+
		"\1\uffff\1\101\1\u044a\1\101\1\u044c\1\101\1\u044e\5\101\1\u0454\2\uffff"+
		"\1\101\1\uffff\1\u0456\1\uffff\1\u0457\3\101\1\uffff\1\u045b\1\uffff\1"+
		"\u045c\3\101\1\uffff\1\u0460\1\u0461\2\uffff\1\u0462\1\u0463\1\uffff\1"+
		"\101\2\uffff\1\u0465\1\101\1\u0467\2\101\1\uffff\1\u046a\2\uffff\1\u046b"+
		"\1\u046c\1\uffff\1\101\1\uffff\1\u046e\1\uffff\1\u046f\1\101\1\u0471\2"+
		"\101\1\uffff\1\101\2\uffff\3\101\2\uffff\3\101\4\uffff\1\101\1\uffff\1"+
		"\u047c\1\uffff\1\u047d\1\101\3\uffff\1\101\2\uffff\1\u0480\1\uffff\1\u0481"+
		"\1\101\1\u0483\2\101\1\u0486\2\101\1\u0489\1\101\2\uffff\2\101\2\uffff"+
		"\1\101\1\uffff\2\101\1\uffff\2\101\1\uffff\20\101\1\u04a2\1\101\1\u04a4"+
		"\5\101\1\uffff\1\101\1\uffff\15\101\1\u04b8\1\101\1\u04ba\3\101\1\uffff"+
		"\1\u04be\1\uffff\1\u04bf\1\u04c0\1\u04c1\4\uffff";
	static final String DFA11_eofS =
		"\u04c2\uffff";
	static final String DFA11_minS =
		"\1\11\1\144\1\uffff\1\106\1\55\1\145\1\117\2\uffff\1\157\1\145\1\105\1"+
		"\52\1\uffff\1\56\1\uffff\1\154\1\114\1\141\1\101\1\145\1\75\1\uffff\1"+
		"\106\1\146\1\145\1\156\1\145\2\141\1\150\3\uffff\1\75\2\uffff\1\75\2\105"+
		"\2\uffff\1\165\1\125\1\101\3\uffff\1\101\1\uffff\1\150\1\110\1\116\1\110"+
		"\1\60\1\160\1\120\1\151\1\111\1\105\1\101\1\141\1\56\4\uffff\2\144\1\164"+
		"\1\154\1\162\1\163\1\104\1\124\1\114\2\uffff\1\157\1\146\1\154\1\105\1"+
		"\116\1\145\1\156\1\154\1\151\1\114\1\166\1\111\6\uffff\1\141\1\163\1\144"+
		"\1\165\1\101\1\123\1\104\1\125\1\154\1\145\1\162\1\114\1\122\1\156\1\141"+
		"\2\uffff\1\141\1\120\1\103\1\164\1\60\1\160\1\143\1\60\1\164\1\155\1\170"+
		"\2\60\1\162\1\151\1\141\1\156\1\161\1\155\1\172\1\141\1\155\1\151\1\164"+
		"\4\uffff\1\164\1\124\1\130\1\162\1\111\1\157\1\122\1\145\1\105\1\116\1"+
		"\101\1\147\1\154\1\115\1\117\1\132\1\101\1\121\1\115\1\165\1\164\1\145"+
		"\1\125\1\160\1\105\2\60\1\111\1\124\1\uffff\1\144\1\104\1\163\1\123\1"+
		"\106\1\124\1\122\1\164\1\162\2\uffff\1\120\1\60\1\145\1\137\1\145\2\141"+
		"\1\145\1\60\1\105\1\137\1\105\1\101\1\154\1\157\1\154\1\101\1\124\1\141"+
		"\1\164\1\145\1\166\1\105\1\151\1\126\1\164\1\157\1\143\1\145\1\163\1\141"+
		"\1\124\1\117\1\103\1\105\1\123\1\101\1\163\1\170\1\60\1\123\1\60\1\142"+
		"\1\155\1\147\1\117\1\114\1\125\1\145\1\uffff\1\157\1\154\1\165\1\uffff"+
		"\2\60\1\164\2\uffff\1\155\1\144\1\156\1\162\1\144\1\60\1\165\1\145\1\144"+
		"\1\165\3\145\1\162\1\151\1\162\1\145\1\156\1\154\1\150\1\145\1\60\1\124"+
		"\1\164\1\116\1\104\1\160\1\115\1\162\1\122\2\104\1\125\1\122\1\105\1\151"+
		"\1\145\2\105\1\114\1\105\2\122\1\125\1\105\1\116\1\145\1\60\1\156\1\105"+
		"\1\145\1\116\2\uffff\1\114\1\110\1\141\1\101\1\165\1\125\1\117\1\103\1"+
		"\105\1\143\1\145\1\162\1\uffff\1\162\1\141\1\162\2\171\1\162\1\uffff\1"+
		"\122\1\101\1\122\1\131\1\60\1\162\1\145\1\124\1\101\1\164\1\141\1\164"+
		"\1\145\1\124\1\143\1\105\1\60\1\162\1\164\1\60\1\167\1\154\1\60\1\122"+
		"\1\124\1\60\1\127\1\114\1\145\1\151\1\154\1\141\1\uffff\1\105\1\114\1"+
		"\101\1\uffff\1\141\1\155\1\145\1\122\1\125\1\103\2\162\1\165\1\143\1\157"+
		"\1\161\1\141\1\150\1\151\2\uffff\1\164\2\165\1\164\1\145\1\60\1\uffff"+
		"\2\162\1\157\1\145\1\60\1\137\1\60\1\145\1\143\1\164\1\137\1\144\1\145"+
		"\2\60\1\117\1\121\1\101\1\110\1\111\1\uffff\1\124\1\60\1\124\1\125\1\145"+
		"\1\125\1\171\1\131\1\117\1\60\1\122\1\105\1\122\1\163\2\60\1\137\2\60"+
		"\1\105\1\124\1\105\1\137\1\104\1\60\1\uffff\4\60\1\105\1\60\1\164\1\124"+
		"\1\141\1\101\1\122\1\110\1\124\1\150\1\164\1\157\1\60\1\146\1\145\1\157"+
		"\2\145\1\141\1\156\1\163\1\60\1\164\1\60\1\106\1\105\1\117\2\105\1\101"+
		"\1\116\1\123\1\141\1\uffff\1\145\1\143\1\105\1\111\1\145\1\151\1\145\1"+
		"\163\1\105\1\145\1\123\1\uffff\1\164\1\154\1\146\1\uffff\1\151\1\163\1"+
		"\uffff\1\124\1\114\1\106\1\uffff\1\111\1\123\1\60\1\142\1\154\1\162\1"+
		"\60\1\114\1\122\1\156\1\141\1\60\1\124\1\104\1\105\1\141\1\164\1\144\1"+
		"\145\1\156\1\165\1\141\1\164\1\162\1\145\1\164\1\157\1\164\1\143\1\60"+
		"\1\163\1\uffff\1\156\1\163\1\155\1\156\1\uffff\1\143\1\146\1\157\1\uffff"+
		"\1\60\1\164\1\163\1\141\1\163\1\60\2\uffff\1\116\1\125\1\101\1\124\1\122"+
		"\1\105\1\124\1\117\1\171\1\uffff\1\60\1\103\1\162\1\124\2\60\1\115\1\uffff"+
		"\1\116\2\123\1\164\1\165\2\uffff\1\103\1\uffff\1\106\1\117\1\uffff\1\60"+
		"\1\123\1\116\1\101\1\123\5\uffff\1\60\1\uffff\1\145\1\105\1\154\1\114"+
		"\2\105\1\110\1\145\1\150\1\160\1\uffff\1\164\1\146\1\162\1\170\1\166\1"+
		"\155\1\141\1\137\1\uffff\1\60\1\uffff\1\124\1\106\1\122\1\130\1\126\1"+
		"\115\1\101\1\137\1\156\1\60\1\164\1\60\1\116\1\60\1\156\6\60\1\171\1\60"+
		"\1\164\2\60\1\131\1\60\1\124\1\60\1\uffff\1\154\1\60\1\144\1\uffff\1\60"+
		"\1\104\1\153\1\162\1\uffff\1\60\1\105\1\123\1\143\1\60\1\145\1\163\1\164"+
		"\1\141\2\143\1\145\1\156\1\150\1\60\1\145\1\164\1\156\1\uffff\1\163\1"+
		"\60\1\145\1\60\1\143\1\157\1\162\1\60\1\146\1\uffff\1\60\1\167\1\146\1"+
		"\145\1\157\2\145\1\141\1\154\1\uffff\1\124\1\101\2\103\1\105\1\116\1\110"+
		"\1\60\1\160\1\116\1\uffff\1\124\1\164\1\105\2\uffff\2\60\1\123\1\105\1"+
		"\162\1\151\1\117\1\122\1\60\1\106\1\uffff\1\127\1\103\1\106\1\105\1\117"+
		"\2\105\1\101\1\114\1\uffff\2\60\1\151\1\111\1\60\1\123\1\101\1\163\1\141"+
		"\2\145\1\157\1\167\1\164\2\145\1\164\1\156\1\uffff\1\105\1\117\1\127\1"+
		"\124\2\105\1\124\1\116\1\60\1\uffff\1\151\1\uffff\1\123\1\uffff\1\163"+
		"\6\uffff\1\60\1\uffff\1\150\2\uffff\1\60\1\uffff\1\110\1\uffff\1\145\1"+
		"\uffff\1\60\1\uffff\3\60\1\uffff\2\60\1\164\1\uffff\2\60\1\141\1\154\1"+
		"\164\1\150\1\164\2\60\1\uffff\3\60\1\145\1\uffff\1\60\1\uffff\1\145\1"+
		"\165\1\151\1\uffff\1\60\1\uffff\1\151\1\164\1\146\1\162\1\170\1\166\1"+
		"\155\1\151\1\101\1\114\1\124\1\110\1\124\2\60\1\uffff\1\145\2\60\1\171"+
		"\1\60\2\uffff\1\105\1\60\1\171\1\154\1\125\1\111\1\uffff\1\60\1\111\1"+
		"\105\1\124\1\106\1\122\1\130\1\126\1\115\1\111\2\uffff\1\172\1\132\1\uffff"+
		"\1\60\1\116\1\60\1\156\3\162\1\141\1\164\1\162\1\137\2\145\2\122\1\101"+
		"\1\124\1\122\1\137\2\105\1\uffff\1\157\2\60\1\uffff\1\60\1\uffff\2\60"+
		"\6\uffff\1\151\2\uffff\1\151\1\163\1\154\1\145\1\150\5\uffff\1\163\1\uffff"+
		"\1\137\1\156\1\145\1\uffff\1\164\1\145\1\157\1\167\1\164\2\145\1\153\1"+
		"\111\1\123\1\114\1\105\1\110\2\uffff\1\60\2\uffff\1\60\1\uffff\1\123\1"+
		"\uffff\1\60\1\144\1\116\1\105\1\uffff\1\124\1\137\1\105\1\117\1\127\1"+
		"\124\2\105\1\113\1\145\1\105\1\uffff\1\60\1\uffff\1\60\1\164\1\60\1\145"+
		"\1\162\1\157\1\163\1\157\1\137\1\170\1\60\1\105\1\122\1\117\1\123\1\117"+
		"\1\137\1\130\1\156\5\uffff\1\157\1\156\1\60\1\171\1\163\1\141\1\60\1\157"+
		"\1\164\1\156\1\150\2\162\1\141\1\164\1\162\1\137\1\145\1\116\1\60\1\131"+
		"\1\123\1\101\2\uffff\1\60\1\uffff\1\145\1\124\1\116\1\110\1\117\2\122"+
		"\1\101\1\124\1\122\1\137\1\105\2\60\2\uffff\1\151\1\uffff\1\60\1\144\1"+
		"\60\1\145\1\162\1\157\1\164\1\uffff\1\60\1\104\1\60\1\105\1\122\1\117"+
		"\1\124\1\60\1\156\1\163\1\uffff\2\60\1\156\1\uffff\1\146\1\60\1\164\2"+
		"\60\1\145\1\162\1\157\1\163\1\157\1\60\1\123\1\uffff\2\60\1\116\1\uffff"+
		"\1\162\1\60\1\124\1\60\1\106\1\60\1\105\1\122\1\117\1\123\1\117\1\60\2"+
		"\uffff\1\145\1\uffff\1\60\1\uffff\1\60\1\151\1\162\1\164\1\uffff\1\60"+
		"\1\uffff\1\60\1\111\1\122\1\124\1\uffff\2\60\2\uffff\2\60\1\uffff\1\141"+
		"\2\uffff\1\60\1\144\1\60\1\145\1\162\1\uffff\1\60\2\uffff\2\60\1\uffff"+
		"\1\101\1\uffff\1\60\1\uffff\1\60\1\104\1\60\1\105\1\122\1\uffff\1\163"+
		"\2\uffff\1\145\1\151\1\157\2\uffff\1\105\1\111\1\117\4\uffff\1\164\1\uffff"+
		"\1\60\1\uffff\1\60\1\151\3\uffff\1\124\2\uffff\1\60\1\uffff\1\60\1\111"+
		"\1\60\1\156\1\145\1\60\1\116\1\105\1\60\1\151\2\uffff\1\145\1\111\2\uffff"+
		"\1\105\1\uffff\1\164\1\156\1\uffff\1\124\1\116\1\uffff\1\157\1\156\1\117"+
		"\1\116\1\141\1\164\1\101\1\124\1\156\1\164\1\116\1\124\1\164\1\141\1\124"+
		"\1\101\1\60\1\141\1\60\1\101\1\151\1\164\1\111\1\124\1\uffff\1\164\1\uffff"+
		"\1\124\1\157\1\151\1\117\1\111\1\151\1\111\1\156\1\157\1\116\1\117\1\157"+
		"\1\117\1\60\1\156\1\60\1\116\1\156\1\116\1\uffff\1\60\1\uffff\3\60\4\uffff";
	static final String DFA11_maxS =
		"\1\175\1\156\1\uffff\1\163\1\55\2\157\2\uffff\2\162\1\145\1\134\1\uffff"+
		"\1\56\1\uffff\1\170\1\130\1\157\1\117\1\162\1\75\1\uffff\2\156\1\165\2"+
		"\162\1\145\1\164\1\151\3\uffff\1\75\2\uffff\1\75\1\157\1\162\2\uffff\1"+
		"\165\1\125\1\165\3\uffff\1\124\1\uffff\1\170\1\171\1\122\1\111\1\172\1"+
		"\160\1\120\1\151\1\111\1\105\1\117\1\157\1\71\4\uffff\2\144\1\164\1\167"+
		"\1\162\1\163\1\104\1\124\1\127\2\uffff\1\157\1\146\1\154\1\105\1\116\1"+
		"\145\1\156\1\154\1\151\1\114\1\166\1\111\6\uffff\1\160\1\163\1\144\1\165"+
		"\1\120\1\123\1\104\1\125\1\154\1\145\1\162\1\114\1\122\1\156\1\141\2\uffff"+
		"\1\141\1\120\1\104\1\164\1\172\1\160\1\144\1\172\1\164\1\155\1\170\2\172"+
		"\1\162\1\157\1\166\1\156\1\161\1\166\1\172\1\162\1\165\1\151\1\164\4\uffff"+
		"\1\164\1\124\1\130\1\162\1\117\1\157\1\122\1\145\1\105\1\116\1\126\1\147"+
		"\1\154\1\126\1\117\1\132\1\117\1\121\1\125\1\165\1\164\1\145\1\125\1\160"+
		"\1\105\2\172\1\111\1\124\1\uffff\1\144\1\104\1\163\1\123\1\106\1\124\1"+
		"\122\1\164\1\162\2\uffff\1\120\1\172\1\145\1\137\1\145\2\141\1\145\1\172"+
		"\1\105\1\137\1\105\1\101\1\154\1\157\1\154\1\101\1\124\1\141\1\164\1\145"+
		"\1\166\1\105\1\151\1\126\1\164\1\157\1\143\1\145\1\163\1\141\1\124\1\117"+
		"\1\103\1\105\1\123\1\101\1\163\1\170\1\172\1\123\1\172\1\142\1\155\1\147"+
		"\1\117\1\114\1\125\1\145\1\uffff\1\157\1\154\1\165\1\uffff\2\172\1\164"+
		"\2\uffff\1\155\1\144\1\156\1\162\1\144\1\172\1\165\1\145\1\144\1\165\3"+
		"\145\1\162\1\151\1\162\1\145\1\156\1\154\1\150\1\145\1\172\1\124\1\164"+
		"\1\116\1\104\1\160\1\115\1\162\1\122\2\104\1\125\1\122\1\105\1\151\1\145"+
		"\2\105\1\114\1\105\2\122\1\125\1\105\1\116\1\145\1\172\1\156\1\105\1\145"+
		"\1\116\2\uffff\1\114\1\110\1\141\1\101\1\165\1\125\1\117\1\103\1\105\1"+
		"\143\1\145\1\162\1\uffff\1\162\1\163\1\162\2\171\1\162\1\uffff\1\122\1"+
		"\123\1\122\1\131\1\172\1\162\1\145\1\124\1\101\1\164\1\141\1\164\1\145"+
		"\1\124\1\143\1\105\1\172\1\162\1\164\1\172\1\167\1\154\1\172\1\122\1\124"+
		"\1\172\1\127\1\114\1\145\1\151\1\154\1\141\1\uffff\1\105\1\114\1\101\1"+
		"\uffff\1\141\1\155\1\145\1\122\1\125\1\103\2\162\1\165\1\143\1\157\1\170"+
		"\1\157\1\150\1\151\2\uffff\1\164\2\165\1\164\1\145\1\172\1\uffff\2\162"+
		"\1\157\1\145\1\172\1\137\1\172\1\145\1\143\1\164\1\137\1\144\1\145\2\172"+
		"\1\117\1\130\1\117\1\110\1\111\1\uffff\1\124\1\172\1\124\1\125\1\145\1"+
		"\125\1\171\1\131\1\117\1\172\1\122\1\105\1\122\1\163\2\172\1\137\2\172"+
		"\1\105\1\124\1\105\1\137\1\104\1\172\1\uffff\4\172\1\105\1\172\1\164\1"+
		"\124\1\141\1\101\1\122\1\110\1\124\1\150\1\164\1\157\1\172\1\146\1\145"+
		"\1\157\2\145\1\141\1\156\1\163\1\172\1\164\1\172\1\106\1\105\1\117\2\105"+
		"\1\101\1\116\1\123\1\141\1\uffff\1\145\1\143\1\105\1\111\1\145\1\151\1"+
		"\145\1\163\1\105\1\145\1\123\1\uffff\1\164\1\154\1\146\1\uffff\1\151\1"+
		"\163\1\uffff\1\124\1\114\1\106\1\uffff\1\111\1\123\1\172\1\142\1\154\1"+
		"\162\1\172\1\114\1\122\1\156\1\141\1\172\1\124\1\104\1\105\1\141\1\164"+
		"\1\144\1\145\1\156\1\165\1\141\1\164\1\162\1\145\1\164\1\157\1\164\1\143"+
		"\1\172\1\163\1\uffff\1\156\1\163\1\155\1\156\1\uffff\1\157\1\146\1\157"+
		"\1\uffff\1\172\1\164\3\163\1\172\2\uffff\1\116\1\125\1\101\1\124\1\122"+
		"\1\105\1\124\1\117\1\171\1\uffff\1\172\1\103\1\162\1\124\2\172\1\115\1"+
		"\uffff\1\116\2\123\1\164\1\165\2\uffff\1\117\1\uffff\1\106\1\117\1\uffff"+
		"\1\172\1\123\1\116\2\123\5\uffff\1\172\1\uffff\1\145\1\105\1\154\1\114"+
		"\2\105\1\110\1\145\1\150\1\160\1\uffff\1\164\1\146\1\162\1\170\1\166\1"+
		"\155\1\141\1\137\1\uffff\1\172\1\uffff\1\124\1\106\1\122\1\130\1\126\1"+
		"\115\1\101\1\137\1\156\1\172\1\164\1\172\1\116\1\172\1\156\6\172\1\171"+
		"\1\172\1\164\2\172\1\131\1\172\1\124\1\172\1\uffff\1\154\1\172\1\144\1"+
		"\uffff\1\172\1\104\1\153\1\162\1\uffff\1\172\1\105\1\123\1\143\1\172\1"+
		"\145\1\163\1\164\1\141\2\143\1\145\1\156\1\150\1\172\1\145\1\164\1\156"+
		"\1\uffff\1\163\1\172\1\145\1\172\1\143\1\157\1\162\1\172\1\146\1\uffff"+
		"\1\172\1\167\1\146\1\145\1\157\2\145\1\141\1\154\1\uffff\1\124\1\101\2"+
		"\103\1\105\1\116\1\110\1\172\1\160\1\116\1\uffff\1\124\1\164\1\105\2\uffff"+
		"\2\172\1\123\1\105\1\162\1\151\1\117\1\122\1\172\1\106\1\uffff\1\127\1"+
		"\103\1\106\1\105\1\117\2\105\1\101\1\114\1\uffff\2\172\1\151\1\111\1\172"+
		"\1\123\1\101\1\163\1\141\2\145\1\157\1\167\1\164\2\145\1\164\1\156\1\uffff"+
		"\1\105\1\117\1\127\1\124\2\105\1\124\1\116\1\172\1\uffff\1\151\1\uffff"+
		"\1\123\1\uffff\1\163\6\uffff\1\172\1\uffff\1\150\2\uffff\1\172\1\uffff"+
		"\1\110\1\uffff\1\145\1\uffff\1\172\1\uffff\3\172\1\uffff\2\172\1\164\1"+
		"\uffff\2\172\1\141\1\154\1\164\1\150\1\164\2\172\1\uffff\3\172\1\145\1"+
		"\uffff\1\172\1\uffff\1\145\1\165\1\151\1\uffff\1\172\1\uffff\1\151\1\164"+
		"\1\146\1\162\1\170\1\166\1\155\1\151\1\101\1\114\1\124\1\110\1\124\2\172"+
		"\1\uffff\1\145\2\172\1\171\1\172\2\uffff\1\105\1\172\1\171\1\154\1\125"+
		"\1\111\1\uffff\1\172\1\111\1\105\1\124\1\106\1\122\1\130\1\126\1\115\1"+
		"\111\2\uffff\1\172\1\132\1\uffff\1\172\1\116\1\172\1\156\3\162\1\141\1"+
		"\164\1\162\1\137\2\145\2\122\1\101\1\124\1\122\1\137\2\105\1\uffff\1\157"+
		"\2\172\1\uffff\1\172\1\uffff\2\172\6\uffff\1\151\2\uffff\1\151\1\163\1"+
		"\154\1\145\1\150\5\uffff\1\163\1\uffff\1\137\1\156\1\145\1\uffff\1\164"+
		"\1\145\1\157\1\167\1\164\2\145\1\153\1\111\1\123\1\114\1\105\1\110\2\uffff"+
		"\1\172\2\uffff\1\172\1\uffff\1\123\1\uffff\1\172\1\144\1\116\1\105\1\uffff"+
		"\1\124\1\137\1\105\1\117\1\127\1\124\2\105\1\113\1\145\1\105\1\uffff\1"+
		"\172\1\uffff\1\172\1\164\1\172\1\145\1\162\1\157\1\163\1\157\1\137\1\170"+
		"\1\172\1\105\1\122\1\117\1\123\1\117\1\137\1\130\1\156\5\uffff\1\157\1"+
		"\156\1\172\1\171\1\163\1\141\1\172\1\157\1\164\1\156\1\150\2\162\1\141"+
		"\1\164\1\162\1\137\1\145\1\116\1\172\1\131\1\123\1\101\2\uffff\1\172\1"+
		"\uffff\1\145\1\124\1\116\1\110\1\117\2\122\1\101\1\124\1\122\1\137\1\105"+
		"\2\172\2\uffff\1\151\1\uffff\1\172\1\144\1\172\1\145\1\162\1\157\1\164"+
		"\1\uffff\1\172\1\104\1\172\1\105\1\122\1\117\1\124\1\172\1\156\1\163\1"+
		"\uffff\2\172\1\156\1\uffff\1\146\1\172\1\164\2\172\1\145\1\162\1\157\1"+
		"\163\1\157\1\172\1\123\1\uffff\2\172\1\116\1\uffff\1\162\1\172\1\124\1"+
		"\172\1\106\1\172\1\105\1\122\1\117\1\123\1\117\1\172\2\uffff\1\145\1\uffff"+
		"\1\172\1\uffff\1\172\1\151\1\162\1\164\1\uffff\1\172\1\uffff\1\172\1\111"+
		"\1\122\1\124\1\uffff\2\172\2\uffff\2\172\1\uffff\1\141\2\uffff\1\172\1"+
		"\144\1\172\1\145\1\162\1\uffff\1\172\2\uffff\2\172\1\uffff\1\101\1\uffff"+
		"\1\172\1\uffff\1\172\1\104\1\172\1\105\1\122\1\uffff\1\163\2\uffff\1\145"+
		"\1\151\1\157\2\uffff\1\105\1\111\1\117\4\uffff\1\164\1\uffff\1\172\1\uffff"+
		"\1\172\1\151\3\uffff\1\124\2\uffff\1\172\1\uffff\1\172\1\111\1\172\1\156"+
		"\1\145\1\172\1\116\1\105\1\172\1\151\2\uffff\1\145\1\111\2\uffff\1\105"+
		"\1\uffff\1\164\1\156\1\uffff\1\124\1\116\1\uffff\1\157\1\156\1\117\1\116"+
		"\1\141\1\164\1\101\1\124\1\156\1\164\1\116\1\124\1\164\1\141\1\124\1\101"+
		"\1\172\1\141\1\172\1\101\1\151\1\164\1\111\1\124\1\uffff\1\164\1\uffff"+
		"\1\124\1\157\1\151\1\117\1\111\1\151\1\111\1\156\1\157\1\116\1\117\1\157"+
		"\1\117\1\172\1\156\1\172\1\116\1\156\1\116\1\uffff\1\172\1\uffff\3\172"+
		"\4\uffff";
	static final String DFA11_acceptS =
		"\2\uffff\1\2\4\uffff\1\11\1\12\4\uffff\1\21\1\uffff\1\24\6\uffff\1\40"+
		"\10\uffff\1\66\1\67\1\70\1\uffff\1\73\1\76\3\uffff\1\105\1\106\3\uffff"+
		"\1\126\1\127\1\130\1\uffff\1\136\15\uffff\1\u00d0\1\u00d1\1\u00d4\1\u00d5"+
		"\11\uffff\1\4\1\75\14\uffff\1\72\1\u00d2\1\u00d3\1\20\1\23\1\22\17\uffff"+
		"\1\35\1\37\30\uffff\1\71\1\74\1\77\1\102\35\uffff\1\173\11\uffff\1\u00ce"+
		"\1\u00cf\61\uffff\1\161\3\uffff\1\54\3\uffff\1\57\1\60\64\uffff\1\164"+
		"\1\165\14\uffff\1\47\6\uffff\1\154\40\uffff\1\52\3\uffff\1\157\17\uffff"+
		"\1\56\1\101\6\uffff\1\122\24\uffff\1\163\31\uffff\1\152\45\uffff\1\6\13"+
		"\uffff\1\25\3\uffff\1\50\2\uffff\1\26\3\uffff\1\155\37\uffff\1\120\4\uffff"+
		"\1\133\3\uffff\1\141\6\uffff\1\u00cd\1\100\11\uffff\1\103\7\uffff\1\121"+
		"\5\uffff\1\131\1\134\1\uffff\1\135\2\uffff\1\144\5\uffff\1\150\1\u00cc"+
		"\1\151\1\153\1\u00a5\1\uffff\1\u00a6\12\uffff\1\u00a7\10\uffff\1\3\1\uffff"+
		"\1\u0080\36\uffff\1\31\3\uffff\1\32\4\uffff\1\41\22\uffff\1\111\11\uffff"+
		"\1\145\11\uffff\1\65\12\uffff\1\112\3\uffff\1\114\1\115\12\uffff\1\146"+
		"\11\uffff\1\172\22\uffff\1\5\11\uffff\1\u00b0\1\uffff\1\14\1\uffff\1\13"+
		"\1\uffff\1\15\1\u00b2\1\16\1\17\1\u008b\1\27\1\uffff\1\51\1\uffff\1\u00b4"+
		"\1\30\1\uffff\1\156\1\uffff\1\u008d\1\uffff\1\53\1\uffff\1\160\3\uffff"+
		"\1\43\3\uffff\1\42\11\uffff\1\u00b9\4\uffff\1\124\1\uffff\1\116\3\uffff"+
		"\1\137\1\uffff\1\147\17\uffff\1\u0092\5\uffff\1\117\1\125\6\uffff\1\140"+
		"\12\uffff\1\174\1\175\2\uffff\1\u0089\25\uffff\1\7\3\uffff\1\u00b5\1\uffff"+
		"\1\u008e\2\uffff\1\u00b6\1\u008f\1\34\1\36\1\45\1\162\1\uffff\1\44\1\55"+
		"\5\uffff\1\u00bf\1\u00c0\1\61\1\62\1\107\1\uffff\1\u00c1\3\uffff\1\142"+
		"\15\uffff\1\u0098\1\u0099\1\uffff\1\110\1\167\1\uffff\1\166\1\uffff\1"+
		"\u009a\4\uffff\1\143\13\uffff\1\u0090\1\uffff\1\u00b7\23\uffff\1\u008a"+
		"\1\u00b1\1\u00b3\1\u008c\1\33\27\uffff\1\104\1\113\1\uffff\1\123\16\uffff"+
		"\1\u0091\1\u00b8\1\uffff\1\u00a8\7\uffff\1\u0081\12\uffff\1\u00bb\3\uffff"+
		"\1\63\14\uffff\1\u0094\3\uffff\1\170\14\uffff\1\176\1\177\1\uffff\1\u00a9"+
		"\1\uffff\1\u00ab\4\uffff\1\u0082\1\uffff\1\u0084\4\uffff\1\10\2\uffff"+
		"\1\u00bc\1\u00bd\2\uffff\1\u00c2\1\uffff\1\u00cb\1\u00c4\5\uffff\1\u00ca"+
		"\1\uffff\1\u0095\1\u0096\2\uffff\1\u009b\1\uffff\1\u00a4\1\uffff\1\u009d"+
		"\5\uffff\1\u00a3\1\uffff\1\u00aa\1\u00ac\3\uffff\1\u0083\1\u0085\3\uffff"+
		"\1\46\1\u00ba\1\u00be\1\64\1\uffff\1\u00c5\1\uffff\1\u00c7\2\uffff\1\u0093"+
		"\1\u0097\1\132\1\uffff\1\171\1\u009e\1\uffff\1\u00a0\12\uffff\1\u00c6"+
		"\1\u00c8\2\uffff\1\u009f\1\u00a1\1\uffff\1\1\2\uffff\1\u00af\2\uffff\1"+
		"\u0088\30\uffff\1\u00c3\1\uffff\1\u009c\23\uffff\1\u00ad\1\uffff\1\u0086"+
		"\3\uffff\1\u00ae\1\u0087\1\u00c9\1\u00a2";
	static final String DFA11_specialS =
		"\u04c2\uffff}>";
	static final String[] DFA11_transitionS = {
			"\1\77\1\100\1\uffff\1\77\1\100\22\uffff\1\77\1\45\1\102\1\26\1\15\1\uffff"+
			"\1\2\1\uffff\1\40\1\56\1\44\1\51\1\10\1\4\1\16\1\14\12\76\1\7\1\61\1"+
			"\42\1\17\1\25\2\uffff\1\3\1\73\1\6\1\13\1\21\1\23\1\24\1\101\1\27\3\101"+
			"\1\74\1\46\1\64\1\47\1\53\1\54\1\60\1\63\1\70\1\72\1\65\3\101\1\41\1"+
			"\43\1\57\1\uffff\1\66\1\uffff\1\1\1\5\1\11\1\12\1\20\1\22\2\101\1\30"+
			"\3\101\1\75\1\31\1\32\1\33\1\52\1\34\1\35\1\62\1\67\1\71\1\36\3\101\1"+
			"\37\1\50\1\55",
			"\1\103\1\uffff\1\105\5\uffff\1\106\1\uffff\1\104",
			"",
			"\1\112\5\uffff\1\113\1\uffff\1\111\43\uffff\1\107\1\110",
			"\1\114",
			"\1\117\11\uffff\1\116",
			"\1\122\2\uffff\1\121\34\uffff\1\120",
			"",
			"",
			"\1\124\2\uffff\1\123",
			"\1\125\14\uffff\1\126",
			"\1\127\14\uffff\1\131\22\uffff\1\130",
			"\1\134\4\uffff\1\133\54\uffff\1\132",
			"",
			"\1\136",
			"",
			"\1\141\1\uffff\1\142\2\uffff\1\143\6\uffff\1\140",
			"\1\145\1\uffff\1\146\2\uffff\1\147\6\uffff\1\144",
			"\1\150\12\uffff\1\151\2\uffff\1\152",
			"\1\153\15\uffff\1\154",
			"\1\155\14\uffff\1\156",
			"\1\157",
			"",
			"\1\165\6\uffff\1\162\1\163\36\uffff\1\161\1\164",
			"\1\170\6\uffff\1\166\1\167",
			"\1\173\11\uffff\1\171\5\uffff\1\172",
			"\1\174\3\uffff\1\175",
			"\1\176\14\uffff\1\177",
			"\1\u0081\3\uffff\1\u0080",
			"\1\u0083\3\uffff\1\u0082\3\uffff\1\u0084\5\uffff\1\u0086\4\uffff\1\u0085",
			"\1\u0087\1\u0088",
			"",
			"",
			"",
			"\1\u0089",
			"",
			"",
			"\1\u008b",
			"\1\u008f\11\uffff\1\u008e\37\uffff\1\u008d",
			"\1\u0093\14\uffff\1\u0091\16\uffff\1\u0090\20\uffff\1\u0092",
			"",
			"",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096\3\uffff\1\u0097\37\uffff\1\u0098\17\uffff\1\u0099",
			"",
			"",
			"",
			"\1\u009a\1\u009b\2\uffff\1\u009e\3\uffff\1\u009c\5\uffff\1\u009f\4\uffff"+
			"\1\u009d",
			"",
			"\1\u00a2\11\uffff\1\u00a0\5\uffff\1\u00a1",
			"\1\u00a5\11\uffff\1\u00a3\46\uffff\1\u00a4",
			"\1\u00a6\3\uffff\1\u00a7",
			"\1\u00a8\1\u00a9",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u00ab",
			"\1\u00ac",
			"\1\u00ad",
			"\1\u00ae",
			"\1\u00af",
			"\1\u00b0\15\uffff\1\u00b1",
			"\1\u00b2\15\uffff\1\u00b3",
			"\1\u00b5\1\uffff\12\76",
			"",
			"",
			"",
			"",
			"\1\u00b6",
			"\1\u00b7",
			"\1\u00b8",
			"\1\u00b9\7\uffff\1\u00ba\2\uffff\1\u00bb",
			"\1\u00bc",
			"\1\u00bd",
			"\1\u00be",
			"\1\u00bf",
			"\1\u00c0\7\uffff\1\u00c1\2\uffff\1\u00c2",
			"",
			"",
			"\1\u00c3",
			"\1\u00c4",
			"\1\u00c5",
			"\1\u00c6",
			"\1\u00c7",
			"\1\u00c8",
			"\1\u00c9",
			"\1\u00ca",
			"\1\u00cb",
			"\1\u00cc",
			"\1\u00cd",
			"\1\u00ce",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u00d1\7\uffff\1\u00cf\6\uffff\1\u00d0",
			"\1\u00d2",
			"\1\u00d3",
			"\1\u00d4",
			"\1\u00d7\7\uffff\1\u00d5\6\uffff\1\u00d6",
			"\1\u00d8",
			"\1\u00d9",
			"\1\u00da",
			"\1\u00db",
			"\1\u00dc",
			"\1\u00dd",
			"\1\u00de",
			"\1\u00df",
			"\1\u00e0",
			"\1\u00e1",
			"",
			"",
			"\1\u00e2",
			"\1\u00e3",
			"\1\u00e4\1\u00e5",
			"\1\u00e6",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u00e8",
			"\1\u00e9\1\u00ea",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u00ec",
			"\1\u00ed",
			"\1\u00ee",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u00f1",
			"\1\u00f3\5\uffff\1\u00f2",
			"\1\u00f5\4\uffff\1\u00f6\11\uffff\1\u00f4\3\uffff\1\u00f7\1\uffff\1"+
			"\u00f8",
			"\1\u00f9",
			"\1\u00fa",
			"\1\u00fc\10\uffff\1\u00fb",
			"\1\u00fd",
			"\1\u0100\15\uffff\1\u00fe\2\uffff\1\u00ff",
			"\1\u0101\7\uffff\1\u0102",
			"\1\u0103",
			"\1\u0104",
			"",
			"",
			"",
			"",
			"\1\u0105",
			"\1\u0106",
			"\1\u0107",
			"\1\u0108",
			"\1\u0109\5\uffff\1\u010a",
			"\1\u010b",
			"\1\u010c",
			"\1\u010d",
			"\1\u010e",
			"\1\u010f",
			"\1\u0110\16\uffff\1\u0112\3\uffff\1\u0111\1\uffff\1\u0113",
			"\1\u0114",
			"\1\u0115",
			"\1\u0117\10\uffff\1\u0116",
			"\1\u0118",
			"\1\u0119",
			"\1\u011b\15\uffff\1\u011a",
			"\1\u011c",
			"\1\u011d\7\uffff\1\u011e",
			"\1\u011f",
			"\1\u0120",
			"\1\u0121",
			"\1\u0122",
			"\1\u0123",
			"\1\u0124",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0127",
			"\1\u0128",
			"",
			"\1\u0129",
			"\1\u012a",
			"\1\u012b",
			"\1\u012c",
			"\1\u012d",
			"\1\u012e",
			"\1\u012f",
			"\1\u0130",
			"\1\u0131",
			"",
			"",
			"\1\u0132",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0134",
			"\1\u0135",
			"\1\u0136",
			"\1\u0137",
			"\1\u0138",
			"\1\u0139",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u013b",
			"\1\u013c",
			"\1\u013d",
			"\1\u013e",
			"\1\u013f",
			"\1\u0140",
			"\1\u0141",
			"\1\u0142",
			"\1\u0143",
			"\1\u0144",
			"\1\u0145",
			"\1\u0146",
			"\1\u0147",
			"\1\u0148",
			"\1\u0149",
			"\1\u014a",
			"\1\u014b",
			"\1\u014c",
			"\1\u014d",
			"\1\u014e",
			"\1\u014f",
			"\1\u0150",
			"\1\u0151",
			"\1\u0152",
			"\1\u0153",
			"\1\u0154",
			"\1\u0155",
			"\1\u0156",
			"\1\u0157",
			"\1\u0158",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\1\u0159\25\101\1\u015a"+
			"\3\101",
			"\1\u015c",
			"\12\101\7\uffff\1\u015d\25\101\1\u015e\3\101\4\uffff\1\101\1\uffff\32"+
			"\101",
			"\1\u0160",
			"\1\u0161",
			"\1\u0162",
			"\1\u0163",
			"\1\u0164",
			"\1\u0165",
			"\1\u0166",
			"",
			"\1\u0167",
			"\1\u0168",
			"\1\u0169",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\2\101\1\u016a\1\101\1\u016b"+
			"\7\101\1\u016c\6\101\1\u016d\2\101\1\u016e\3\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0171",
			"",
			"",
			"\1\u0172",
			"\1\u0173",
			"\1\u0174",
			"\1\u0175",
			"\1\u0176",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0178",
			"\1\u0179",
			"\1\u017a",
			"\1\u017b",
			"\1\u017c",
			"\1\u017d",
			"\1\u017e",
			"\1\u017f",
			"\1\u0180",
			"\1\u0181",
			"\1\u0182",
			"\1\u0183",
			"\1\u0184",
			"\1\u0185",
			"\1\u0186",
			"\12\101\7\uffff\2\101\1\u0187\1\101\1\u0188\7\101\1\u0189\6\101\1\u018a"+
			"\2\101\1\u018b\3\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u018d",
			"\1\u018e",
			"\1\u018f",
			"\1\u0190",
			"\1\u0191",
			"\1\u0192",
			"\1\u0193",
			"\1\u0194",
			"\1\u0195",
			"\1\u0196",
			"\1\u0197",
			"\1\u0198",
			"\1\u0199",
			"\1\u019a",
			"\1\u019b",
			"\1\u019c",
			"\1\u019d",
			"\1\u019e",
			"\1\u019f",
			"\1\u01a0",
			"\1\u01a1",
			"\1\u01a2",
			"\1\u01a3",
			"\1\u01a4",
			"\1\u01a5",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u01a7",
			"\1\u01a8",
			"\1\u01a9",
			"\1\u01aa",
			"",
			"",
			"\1\u01ab",
			"\1\u01ac",
			"\1\u01ad",
			"\1\u01ae",
			"\1\u01af",
			"\1\u01b0",
			"\1\u01b1",
			"\1\u01b2",
			"\1\u01b3",
			"\1\u01b4",
			"\1\u01b5",
			"\1\u01b6",
			"",
			"\1\u01b7",
			"\1\u01b8\1\u01b9\3\uffff\1\u01ba\7\uffff\1\u01bb\3\uffff\1\u01bc\1\u01bd",
			"\1\u01be",
			"\1\u01bf",
			"\1\u01c0",
			"\1\u01c1",
			"",
			"\1\u01c2",
			"\1\u01c3\1\u01c4\3\uffff\1\u01c5\7\uffff\1\u01c6\3\uffff\1\u01c7\1\u01c8",
			"\1\u01c9",
			"\1\u01ca",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\4\101\1\u01cb\25\101",
			"\1\u01cd",
			"\1\u01ce",
			"\1\u01cf",
			"\1\u01d0",
			"\1\u01d1",
			"\1\u01d2",
			"\1\u01d3",
			"\1\u01d4",
			"\1\u01d5",
			"\1\u01d6",
			"\1\u01d7",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u01d9",
			"\1\u01da",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\10\101\1\u01db\21\101",
			"\1\u01dd",
			"\1\u01de",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u01e0",
			"\1\u01e1",
			"\12\101\7\uffff\10\101\1\u01e2\21\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u01e4",
			"\1\u01e5",
			"\1\u01e6",
			"\1\u01e7",
			"\1\u01e8",
			"\1\u01e9",
			"",
			"\1\u01ea",
			"\1\u01eb",
			"\1\u01ec",
			"",
			"\1\u01ed",
			"\1\u01ee",
			"\1\u01ef",
			"\1\u01f0",
			"\1\u01f1",
			"\1\u01f2",
			"\1\u01f3",
			"\1\u01f4",
			"\1\u01f5",
			"\1\u01f6",
			"\1\u01f7",
			"\1\u01f8\6\uffff\1\u01f9",
			"\1\u01fa\15\uffff\1\u01fb",
			"\1\u01fc",
			"\1\u01fd",
			"",
			"",
			"\1\u01fe",
			"\1\u01ff",
			"\1\u0200",
			"\1\u0201",
			"\1\u0202",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u0204",
			"\1\u0205",
			"\1\u0206",
			"\1\u0207",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0209",
			"\12\101\7\uffff\32\101\4\uffff\1\u020b\1\uffff\16\101\1\u020a\13\101",
			"\1\u020d",
			"\1\u020e",
			"\1\u020f",
			"\1\u0210",
			"\1\u0211",
			"\1\u0212",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0215",
			"\1\u0216\6\uffff\1\u0217",
			"\1\u0218\15\uffff\1\u0219",
			"\1\u021a",
			"\1\u021b",
			"",
			"\1\u021c",
			"\12\101\7\uffff\23\101\1\u021d\6\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u021f",
			"\1\u0220",
			"\1\u0221",
			"\1\u0222",
			"\1\u0223",
			"\1\u0224",
			"\1\u0225",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0227",
			"\1\u0228",
			"\1\u0229",
			"\1\u022a",
			"\12\101\7\uffff\1\101\1\u022b\30\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u022e",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\16\101\1\u0230\13\101\4\uffff\1\u0231\1\uffff\32\101",
			"\1\u0233",
			"\1\u0234",
			"\1\u0235",
			"\1\u0236",
			"\1\u0237",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u023d",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u023f",
			"\1\u0240",
			"\1\u0241",
			"\1\u0242",
			"\1\u0243",
			"\1\u0244",
			"\1\u0245",
			"\1\u0246",
			"\1\u0247",
			"\1\u0248",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u024a",
			"\1\u024b",
			"\1\u024c",
			"\1\u024d",
			"\1\u024e",
			"\1\u024f",
			"\1\u0250",
			"\1\u0251",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0253",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0255",
			"\1\u0256",
			"\1\u0257",
			"\1\u0258",
			"\1\u0259",
			"\1\u025a",
			"\1\u025b",
			"\1\u025c",
			"\1\u025d",
			"",
			"\1\u025e",
			"\1\u025f",
			"\1\u0260",
			"\1\u0261",
			"\1\u0262",
			"\1\u0263",
			"\1\u0264",
			"\1\u0265",
			"\1\u0266",
			"\1\u0267",
			"\1\u0268",
			"",
			"\1\u0269",
			"\1\u026a",
			"\1\u026b",
			"",
			"\1\u026c",
			"\1\u026d",
			"",
			"\1\u026e",
			"\1\u026f",
			"\1\u0270",
			"",
			"\1\u0271",
			"\1\u0272",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0274",
			"\1\u0275",
			"\1\u0276",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0278",
			"\1\u0279",
			"\1\u027a",
			"\1\u027b",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u027d",
			"\1\u027e",
			"\1\u027f",
			"\1\u0280",
			"\1\u0281",
			"\1\u0282",
			"\1\u0283",
			"\1\u0284",
			"\1\u0285",
			"\1\u0286",
			"\1\u0287",
			"\1\u0288",
			"\1\u0289",
			"\1\u028a",
			"\1\u028b",
			"\1\u028c",
			"\1\u028d",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\13\101\1\u028e\16\101",
			"\1\u0290",
			"",
			"\1\u0291",
			"\1\u0292",
			"\1\u0293",
			"\1\u0294",
			"",
			"\1\u0295\13\uffff\1\u0296",
			"\1\u0297",
			"\1\u0298",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u029a",
			"\1\u029b",
			"\1\u029c\1\u029d\3\uffff\1\u029e\7\uffff\1\u029f\3\uffff\1\u02a0\1\u02a1",
			"\1\u02a2",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"",
			"\1\u02a4",
			"\1\u02a5",
			"\1\u02a6",
			"\1\u02a7",
			"\1\u02a8",
			"\1\u02a9",
			"\1\u02aa",
			"\1\u02ab",
			"\1\u02ac",
			"",
			"\12\101\7\uffff\13\101\1\u02ad\16\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02af",
			"\1\u02b0",
			"\1\u02b1",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02b4",
			"",
			"\1\u02b5",
			"\1\u02b6",
			"\1\u02b7",
			"\1\u02b8",
			"\1\u02b9",
			"",
			"",
			"\1\u02ba\13\uffff\1\u02bb",
			"",
			"\1\u02bc",
			"\1\u02bd",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02bf",
			"\1\u02c0",
			"\1\u02c1\1\u02c2\3\uffff\1\u02c3\7\uffff\1\u02c4\3\uffff\1\u02c5\1\u02c6",
			"\1\u02c7",
			"",
			"",
			"",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u02c9",
			"\1\u02ca",
			"\1\u02cb",
			"\1\u02cc",
			"\1\u02cd",
			"\1\u02ce",
			"\1\u02cf",
			"\1\u02d0",
			"\1\u02d1",
			"\1\u02d2",
			"",
			"\1\u02d3",
			"\1\u02d4",
			"\1\u02d5",
			"\1\u02d6",
			"\1\u02d7",
			"\1\u02d8",
			"\1\u02d9",
			"\1\u02da",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u02dc",
			"\1\u02dd",
			"\1\u02de",
			"\1\u02df",
			"\1\u02e0",
			"\1\u02e1",
			"\1\u02e2",
			"\1\u02e3",
			"\1\u02e4",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02e6",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02e8",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02ea",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02f1",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02f3",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02f6",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02f8",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u02fa",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02fc",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u02fe",
			"\1\u02ff",
			"\1\u0300",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0302",
			"\1\u0303",
			"\1\u0304",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0306",
			"\1\u0307",
			"\1\u0308",
			"\1\u0309",
			"\1\u030a",
			"\1\u030b",
			"\1\u030c",
			"\1\u030d",
			"\1\u030e",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0310",
			"\1\u0311",
			"\1\u0312",
			"",
			"\1\u0313",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0315",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0317",
			"\1\u0318",
			"\1\u0319",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u031b",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u031d",
			"\1\u031e",
			"\1\u031f",
			"\1\u0320",
			"\1\u0321",
			"\1\u0322",
			"\1\u0323",
			"\1\u0324",
			"",
			"\1\u0325",
			"\1\u0326",
			"\1\u0327",
			"\1\u0328",
			"\1\u0329",
			"\1\u032a",
			"\1\u032b",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u032d",
			"\1\u032e",
			"",
			"\1\u032f",
			"\1\u0330",
			"\1\u0331",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0334",
			"\1\u0335",
			"\1\u0336",
			"\1\u0337",
			"\1\u0338",
			"\1\u0339",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u033b",
			"",
			"\1\u033c",
			"\1\u033d",
			"\1\u033e",
			"\1\u033f",
			"\1\u0340",
			"\1\u0341",
			"\1\u0342",
			"\1\u0343",
			"\1\u0344",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0347",
			"\1\u0348",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u034a",
			"\1\u034b",
			"\1\u034c",
			"\1\u034d",
			"\1\u034e",
			"\1\u034f",
			"\1\u0350",
			"\1\u0351",
			"\1\u0352",
			"\1\u0353",
			"\1\u0354",
			"\1\u0355",
			"\1\u0356",
			"",
			"\1\u0357",
			"\1\u0358",
			"\1\u0359",
			"\1\u035a",
			"\1\u035b",
			"\1\u035c",
			"\1\u035d",
			"\1\u035e",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u0360",
			"",
			"\1\u0361",
			"",
			"\1\u0362",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u0364",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u0366",
			"",
			"\1\u0367",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u036e",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0371",
			"\1\u0372",
			"\1\u0373",
			"\1\u0374",
			"\1\u0375",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u037b",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u037d",
			"\1\u037e",
			"\1\u037f",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u0381",
			"\1\u0382",
			"\1\u0383",
			"\1\u0384",
			"\1\u0385",
			"\1\u0386",
			"\1\u0387",
			"\1\u0388",
			"\1\u0389",
			"\1\u038a",
			"\1\u038b",
			"\1\u038c",
			"\1\u038d",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u0390",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0393",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"",
			"\1\u0395",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0397",
			"\1\u0398",
			"\1\u0399",
			"\1\u039a",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u039c",
			"\1\u039d",
			"\1\u039e",
			"\1\u039f",
			"\1\u03a0",
			"\1\u03a1",
			"\1\u03a2",
			"\1\u03a3",
			"\1\u03a4",
			"",
			"",
			"\1\u03a5",
			"\1\u03a6",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u03a8",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u03aa",
			"\1\u03ab",
			"\1\u03ac",
			"\1\u03ad",
			"\1\u03ae",
			"\1\u03af",
			"\1\u03b0",
			"\1\u03b1",
			"\1\u03b2",
			"\1\u03b3",
			"\1\u03b4",
			"\1\u03b5",
			"\1\u03b6",
			"\1\u03b7",
			"\1\u03b8",
			"\1\u03b9",
			"\1\u03ba",
			"\1\u03bb",
			"",
			"\1\u03bc",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u03c2",
			"",
			"",
			"\1\u03c3",
			"\1\u03c4",
			"\1\u03c5",
			"\1\u03c6",
			"\1\u03c7",
			"",
			"",
			"",
			"",
			"",
			"\1\u03c8",
			"",
			"\1\u03c9",
			"\1\u03ca",
			"\1\u03cb",
			"",
			"\1\u03cc",
			"\1\u03cd",
			"\1\u03ce",
			"\1\u03cf",
			"\1\u03d0",
			"\1\u03d1",
			"\1\u03d2",
			"\1\u03d3",
			"\1\u03d4",
			"\1\u03d5",
			"\1\u03d6",
			"\1\u03d7",
			"\1\u03d8",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u03db",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u03dd",
			"\1\u03de",
			"\1\u03df",
			"",
			"\1\u03e0",
			"\1\u03e1",
			"\1\u03e2",
			"\1\u03e3",
			"\1\u03e4",
			"\1\u03e5",
			"\1\u03e6",
			"\1\u03e7",
			"\1\u03e8",
			"\1\u03e9",
			"\1\u03ea",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u03ed",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u03ef",
			"\1\u03f0",
			"\1\u03f1",
			"\1\u03f2",
			"\1\u03f3",
			"\1\u03f4",
			"\1\u03f5",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u03f7",
			"\1\u03f8",
			"\1\u03f9",
			"\1\u03fa",
			"\1\u03fb",
			"\1\u03fc",
			"\1\u03fd",
			"\1\u03fe",
			"",
			"",
			"",
			"",
			"",
			"\1\u03ff",
			"\1\u0400",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0402",
			"\1\u0403",
			"\1\u0404",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0406",
			"\1\u0407",
			"\1\u0408",
			"\1\u0409",
			"\1\u040a",
			"\1\u040b",
			"\1\u040c",
			"\1\u040d",
			"\1\u040e",
			"\1\u040f",
			"\1\u0410",
			"\1\u0411",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0413",
			"\1\u0414",
			"\1\u0415",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u0417",
			"\1\u0418",
			"\1\u0419",
			"\1\u041a",
			"\1\u041b",
			"\1\u041c",
			"\1\u041d",
			"\1\u041e",
			"\1\u041f",
			"\1\u0420",
			"\1\u0421",
			"\1\u0422",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"",
			"\1\u0425",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0427",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0429",
			"\1\u042a",
			"\1\u042b",
			"\1\u042c",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u042e",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0430",
			"\1\u0431",
			"\1\u0432",
			"\1\u0433",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0435",
			"\1\u0436",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0439",
			"",
			"\1\u043a",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u043c",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u043f",
			"\1\u0440",
			"\1\u0441",
			"\1\u0442",
			"\1\u0443",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0445",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0448",
			"",
			"\1\u0449",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u044b",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u044d",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u044f",
			"\1\u0450",
			"\1\u0451",
			"\1\u0452",
			"\1\u0453",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"",
			"\1\u0455",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0458",
			"\1\u0459",
			"\1\u045a",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u045d",
			"\1\u045e",
			"\1\u045f",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u0464",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0466",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0468",
			"\1\u0469",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\1\u046d",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0470",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0472",
			"\1\u0473",
			"",
			"\1\u0474",
			"",
			"",
			"\1\u0475",
			"\1\u0476",
			"\1\u0477",
			"",
			"",
			"\1\u0478",
			"\1\u0479",
			"\1\u047a",
			"",
			"",
			"",
			"",
			"\1\u047b",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u047e",
			"",
			"",
			"",
			"\1\u047f",
			"",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0482",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0484",
			"\1\u0485",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u0487",
			"\1\u0488",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u048a",
			"",
			"",
			"\1\u048b",
			"\1\u048c",
			"",
			"",
			"\1\u048d",
			"",
			"\1\u048e",
			"\1\u048f",
			"",
			"\1\u0490",
			"\1\u0491",
			"",
			"\1\u0492",
			"\1\u0493",
			"\1\u0494",
			"\1\u0495",
			"\1\u0496",
			"\1\u0497",
			"\1\u0498",
			"\1\u0499",
			"\1\u049a",
			"\1\u049b",
			"\1\u049c",
			"\1\u049d",
			"\1\u049e",
			"\1\u049f",
			"\1\u04a0",
			"\1\u04a1",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u04a3",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u04a5",
			"\1\u04a6",
			"\1\u04a7",
			"\1\u04a8",
			"\1\u04a9",
			"",
			"\1\u04aa",
			"",
			"\1\u04ab",
			"\1\u04ac",
			"\1\u04ad",
			"\1\u04ae",
			"\1\u04af",
			"\1\u04b0",
			"\1\u04b1",
			"\1\u04b2",
			"\1\u04b3",
			"\1\u04b4",
			"\1\u04b5",
			"\1\u04b6",
			"\1\u04b7",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u04b9",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\1\u04bb",
			"\1\u04bc",
			"\1\u04bd",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"\12\101\7\uffff\32\101\4\uffff\1\101\1\uffff\32\101",
			"",
			"",
			"",
			""
	};

	static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
	static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
	static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
	static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
	static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
	static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
	static final short[][] DFA11_transition;

	static {
		int numStates = DFA11_transitionS.length;
		DFA11_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
		}
	}

	protected class DFA11 extends DFA {

		public DFA11(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 11;
			this.eot = DFA11_eot;
			this.eof = DFA11_eof;
			this.min = DFA11_min;
			this.max = DFA11_max;
			this.accept = DFA11_accept;
			this.special = DFA11_special;
			this.transition = DFA11_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( ADDPROPS | AMP | ARRAY | ARROW | ASSERT | BOOL | BOOLEAN | COLLECTION | COLON | COMMA | CREATE_LC | CREATE_UC | DELETE_LC | DELETE_UC | DEVICE | DIV | DOLLAR | DOT | DOTDOT | EQUALS | EXIT_LC | EXIT_UC | EXPORT_LC | EXPORT_UC | FALSE_LC | FALSE_UC | FLEXIBLE | GENBANK | GEQUAL | GRAMMAR | GTHAN | HASHMARK | IMAGE | IMPORT_LC | IMPORT_UC | INCLUDE_LC | INCLUDE_UC | INTERACTION | LC_AND | LC_ELSE | LC_ELSEIF | LC_FOR | LC_FORALL | LC_IF | LC_INDUCES | LC_NOT | LC_ON | LC_OR | LC_PERMUTE | LC_PRODUCT | LC_REPRESSES | LC_SEQUENCE_OF | LC_WHILE | LEFTCUR | LEFTP | LEFTSBR | LEQUAL | LOG_AND | LOG_OR | LTHAN | MINUS | MULT | NEQUAL | NOTE | NUM | OP_NOT | PART | PART_TYPE | PIPE | PLUS | PRINTLN_LC | PRINTLN_UC | PRINT_LC | PRINT_UC | PROPERTY | QUERY_LC | QUERY_UC | RANDOM_LC | RANDOM_UC | READ_LC | READ_UC | REF | REGISTRY | RETURN_LC | RETURN_UC | RIGHTCUR | RIGHTP | RIGHTSBR | RULE | RULE_BUILDER | SAVE_LC | SAVE_UC | SBOL | SEMIC | SIZEOF_LC | SIZEOF_UC | SIZE_LC | SIZE_OF_LC | SIZE_OF_UC | SIZE_UC | STORE_LC | STORE_UC | STRICT | TRUE_LC | TRUE_UC | TXT | TYPE | UC_AND | UC_ELSE | UC_ELSEIF | UC_FOR | UC_FORALL | UC_IF | UC_INDUCES | UC_NOT | UC_ON | UC_OR | UC_PERMUTE | UC_PRODUCT | UC_REPRESSES | UC_SEQUENCE_OF | UC_WHILE | UNDERS | UPDATE_LC | UPDATE_UC | VISUALIZE_LC | VISUALIZE_UC | T__140 | T__141 | T__142 | T__143 | T__144 | T__145 | T__146 | T__147 | T__148 | T__149 | T__150 | T__151 | T__152 | T__153 | T__154 | T__155 | T__156 | T__157 | T__158 | T__159 | T__160 | T__161 | T__162 | T__163 | T__164 | T__165 | T__166 | T__167 | T__168 | T__169 | T__170 | T__171 | T__172 | T__173 | T__174 | T__175 | T__176 | T__177 | T__178 | T__179 | T__180 | T__181 | T__182 | T__183 | T__184 | T__185 | T__186 | T__187 | T__188 | T__189 | T__190 | T__191 | T__192 | T__193 | T__194 | T__195 | T__196 | T__197 | T__198 | T__199 | T__200 | T__201 | T__202 | T__203 | T__204 | T__205 | T__206 | T__207 | T__208 | T__209 | T__210 | T__211 | T__212 | T__213 | T__214 | T__215 | T__216 | T__217 | NUMBER | REAL | WS | NEWLINE | LINE_COMMENT | ML_COMMENT | ID | STRING );";
		}
	}

}
