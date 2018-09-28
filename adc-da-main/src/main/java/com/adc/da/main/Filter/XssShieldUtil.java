package com.adc.da.main.Filter;

import com.adc.da.util.utils.StringUtils;
import com.itextpdf.text.log.SysoCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssShieldUtil {

    private static final Logger logger = LoggerFactory.getLogger(XssShieldUtil.class);
    private static List<Pattern> patterns = null;

    private static List<Object[]> getXssPatternList()
    {
        List<Object[]> ret = new ArrayList();

        ret.add(new Object[] { "<(no)?script[^>]*>.*?</(no)?script>", Integer.valueOf(2) });
        ret.add(new Object[] { "eval\\((.*?)\\)", Integer.valueOf(42) });
        ret.add(new Object[] { "expression\\((.*?)\\)", Integer.valueOf(42) });
        ret.add(new Object[] { "(javascript:|vbscript:|view-source:)*", Integer.valueOf(2) });
//        ret.add(new Object[] { "<(\"[^\"]*\"|'[^']*'|[^'\">])*>", Integer.valueOf(42) });
        ret.add(new Object[] { "(window\\.location|window\\.|\\.location|document\\.cookie|document\\.|alert\\(.*?\\)|window\\.open\\()*", Integer.valueOf(42) });
        ret.add(new Object[] { "<+\\s*\\w*\\s*(oncontrolselect|oncopy|oncut|ondataavailable|ondatasetchanged|ondatasetcomplete|ondblclick|ondeactivate|ondrag|ondragend|ondragenter|ondragleave|ondragover|ondragstart|ondrop|onerror=|onerroupdate|onfilterchange|onfinish|onfocus|onfocusin|onfocusout|onhelp|onkeydown|onkeypress|onkeyup|onlayoutcomplete|onload|onlosecapture|onmousedown|onmouseenter|onmouseleave|onmousemove|onmousout|onmouseover|onmouseup|onmousewheel|onmove|onmoveend|onmovestart|onabort|onactivate|onafterprint|onafterupdate|onbefore|onbeforeactivate|onbeforecopy|onbeforecut|onbeforedeactivate|onbeforeeditocus|onbeforepaste|onbeforeprint|onbeforeunload|onbeforeupdate|onblur|onbounce|oncellchange|onchange|onclick|oncontextmenu|onpaste|onpropertychange|onreadystatechange|onreset|onresize|onresizend|onresizestart|onrowenter|onrowexit|onrowsdelete|onrowsinserted|onscroll|onselect|onselectionchange|onselectstart|onstart|onstop|onsubmit|onunload)+\\s*=+", Integer.valueOf(42) });
        return ret;
    }

    private static List<Pattern> getPatterns()
    {
        if (patterns == null)
        {
            List<Pattern> list = new ArrayList();

            String regex = null;
            Integer flag = null;
            int arrLength = 0;
            int i;
            Iterator localIterator = getXssPatternList().iterator();
            while (localIterator.hasNext()){
                Object[] arr = (Object[])localIterator.next();
                if(arr.length==0){
                    continue;
                }
                regex = (String)arr[0];
                flag = (Integer)arr[1];
                list.add(Pattern.compile(regex, flag.intValue()));
            }
/*            for (Iterator localIterator = getXssPatternList().iterator(); localIterator.hasNext();i < arrLength) {
                Object[] arr = (Object[])localIterator.next();
                arrLength = arr.length;
                i = 0;
                continue;
                regex = (String)arr[0];
                flag = (Integer)arr[1];
                list.add(Pattern.compile(regex, flag.intValue()));
                i++;
            }*/
            patterns = list;
        }
        return patterns;
    }

    public static String stripXss(String value)
    {
        if (StringUtils.isNotBlank(value))
        {
            Matcher matcher = null;
            for (Pattern pattern : getPatterns())
            {
                matcher = pattern.matcher(value);
                if (matcher.find()) {
                    value = matcher.replaceAll("");
                }
            }
//            value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

            String[] pattern = { "%", "select", "insert", "delete", "from",
                    "count\\(", "drop table", "update", "truncate", "asc\\(",
                    "mid\\(", "char\\(", "xp_cmdshell", "exec", "master",
                    "netlocalgroup administrators", "net user", "or", "and" };
            for (int i = 0; i < pattern.length; i++) {
                value = value.replace(pattern[i].toString(), "");
            }
        }
        return value;
    }
}
