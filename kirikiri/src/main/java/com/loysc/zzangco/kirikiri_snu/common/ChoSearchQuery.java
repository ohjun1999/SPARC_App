package com.loysc.zzangco.kirikiri_snu.common;

public class ChoSearchQuery {
    public static final int EVENT_CODE_LENGTH = 6;

    public static final int DIGIT_BEGIN_UNICODE = 0x30; //0
    public static final int DIGIT_END_UNICODE = 0x3A; //9

    public static final int QUERY_DELIM = 39;//'
    public static final int LARGE_ALPHA_BEGIN_UNICODE = 0;

    public static final int HANGUL_BEGIN_UNICODE = 0xAC00; // 가
    public static final int HANGUL_END_UNICODE = 0xD7A3; // ?
    public static final int HANGUL_CHO_UNIT = 588; //한글 초성글자간 간격
    public static final int HANGUL_JUNG_UNIT = 28; //한글 중성글자간 간격

    public static final char[] CHO_LIST = { 'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' };
    public static final boolean[] CHO_SEARCH_LIST = { true, false, true, true, false, true, true, true, false, true, false, true, true, false, true, true, true, true, true };

    public static int convertCharToUnicode(char ch) {
        return (int) ch;
    }

    private static String toHexString(int decimal) {
        Long intDec = Long.valueOf(decimal);
        return Long.toHexString(intDec);
    }

    public static char convertUnicodeToChar(String hexUnicode) {
        return (char) Integer.parseInt(hexUnicode, 16);
    }

    public static char convertUnicodeToChar(int unicode) {
        return convertUnicodeToChar(toHexString(unicode));
    }

    public static String makeQuery(String strSearch){
        strSearch = strSearch == null ? "null" : strSearch.trim();

        StringBuilder retQuery = new StringBuilder();

        int nChoPosition;
        int nNextChoPosition;
        int StartUnicode;
        int EndUnicode;

        int nQueryIndex = 0;
//            boolean bChosung = false;
        StringBuilder query = new StringBuilder();
        for( int nIndex = 0 ; nIndex < strSearch.length() ; nIndex++ ){
            nChoPosition = -1;
            nNextChoPosition = -1;
            StartUnicode = -1;
            EndUnicode = -1;

            if( strSearch.charAt(nIndex) == QUERY_DELIM )
                continue;

            if( nQueryIndex != 0 ){
                query.append(" AND ");
            }

            for( int nChoIndex = 0 ; nChoIndex < CHO_LIST.length ; nChoIndex++ ){
                if( strSearch.charAt(nIndex) == CHO_LIST[nChoIndex] ){
                    nChoPosition = nChoIndex;
                    nNextChoPosition = nChoPosition+1;
                    for( ; nNextChoPosition < CHO_SEARCH_LIST.length ; nNextChoPosition++ ){
                        if( CHO_SEARCH_LIST[nNextChoPosition] )
                            break;
                    }
                    break;
                }
            }

            if( nChoPosition >= 0 ){ //초성이 있을 경우
//                    bChosung = true;
                StartUnicode = HANGUL_BEGIN_UNICODE + nChoPosition*HANGUL_CHO_UNIT;
                EndUnicode = HANGUL_BEGIN_UNICODE + nNextChoPosition*HANGUL_CHO_UNIT;
            }
            else{
                int Unicode = convertCharToUnicode(strSearch.charAt(nIndex));
                if( Unicode >= HANGUL_BEGIN_UNICODE && Unicode <= HANGUL_END_UNICODE){
                    int Jong = ((Unicode-HANGUL_BEGIN_UNICODE)%HANGUL_CHO_UNIT)%HANGUL_JUNG_UNIT;

                    if( Jong == 0 ){// 초성+중성으로 되어 있는 경우
                        StartUnicode = Unicode;
                        EndUnicode = Unicode+HANGUL_JUNG_UNIT;
                    }
                    else{
                        StartUnicode = Unicode;
                        EndUnicode = Unicode;
                    }
                }
            }

            //Log.d("SearchQuery","query "+strSearch.codePointAt(nIndex));
            if( StartUnicode > 0 && EndUnicode > 0 ){
                if( StartUnicode == EndUnicode )
                    query.append("substr(name,"+(nIndex+1)+",1)='"+strSearch.charAt(nIndex)+"'");
                else
                    query.append("(substr(name,"+(nIndex+1)+",1)>='"+convertUnicodeToChar(StartUnicode)
                            +"' AND substr(name,"+(nIndex+1)+",1)<'"+convertUnicodeToChar(EndUnicode)+"')");
            }
            else{
                if( Character.isLowerCase(strSearch.charAt(nIndex))){ //영문 소문자
                    query.append("(substr(name,"+(nIndex+1)+",1)='"+strSearch.charAt(nIndex)+"'"
                            + " OR substr(name,"+(nIndex+1)+",1)='"+Character.toUpperCase(strSearch.charAt(nIndex))+"')");
                }
                else if( Character.isUpperCase(strSearch.charAt(nIndex))){ //영문 대문자
                    query.append("(substr(name,"+(nIndex+1)+",1)='"+strSearch.charAt(nIndex)+"'"
                            + " OR substr(name,"+(nIndex+1)+",1)='"+Character.toLowerCase(strSearch.charAt(nIndex))+"')");
                }
                else //기타 문자
                    query.append("substr(name,"+(nIndex+1)+",1)='"+strSearch.charAt(nIndex)+"'");
            }

            nQueryIndex++;
        }

        if(query.length() > 0 && strSearch != null && strSearch.trim().length() > 0) {
            retQuery.append("("+query.toString()+")");

            if(strSearch.indexOf(" ") != -1) {
                // 공백 구분 단어에 대해 단어 모두 포함 검색
                String[] tokens = strSearch.split(" ");
                retQuery.append(" OR (");
                for(int i=0, isize=tokens.length; i<isize; i++) {
                    String token = tokens[i];
                    if(i != 0) {
                        retQuery.append(" AND ");
                    }
                    retQuery.append("name like '%"+token+"%'");
                }
                retQuery.append(")");
            } else {
                // LIKE 검색 추가
                retQuery.append(" OR name like '%"+strSearch+"%'");
            }
        } else {
            retQuery.append(query.toString());
        }
//        }
        //Log.d("SearchQuery","query "+query.toString());
        return retQuery.toString();
    }

}
